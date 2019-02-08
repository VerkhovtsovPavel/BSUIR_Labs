library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library gpr;
use gpr.OneHotGPR.all;

entity CTRL1 is
    port(
        CLK, RST, Start: in std_logic;
        Stop: out std_logic;

        -- ROM
        ROM_re: out std_logic;
        ROM_addr: out mem_addr;
        ROM_dout: in command;

        -- RAM
        RAM_rw: out std_logic;
        RAM_addr1: out mem_addr;
        RAM_addr2: out mem_addr;
        RAM_addrw: out mem_addr;
        RAM_dwin: out operand;
        RAM_d1out: in operand;
        RAM_d2out: in operand;

        --datapath
        DP_op1: out operand;
        DP_op2: out operand;
        DP_ot: out operation;
        DP_en: out std_logic;
        DP_res: in operand;
        DP_zf: in std_logic
    );
end CTRL1;

architecture Beh_GPR of CTRL1 is
    type states is (I, F, D, R, W, A, SB, SH, H, M, CP, CPR, JNOTZ);
    -- I - idle
    -- F - fetch
    -- D - decode
    -- R - read
    -- W - write
    -- A - add
    -- SB - sub
	-- SH - shift
    -- H - halt
    -- M - move the value from RD_1 to RD_W
    -- CP - copy to indirect, CP addr1, [addrw], empty - load ra_2 from rd_2
    -- CPR - copy to indirect, read - RA_W = RA_2
    -- JNOTZ -  jump if zero bit not set
    signal nxt_state, cur_state: states;
    -- instruction register
    signal RI: std_logic_vector(17 downto 0);
    -- instruction counter
    signal IC: mem_addr;
    -- operation type register
    signal RO: std_logic_vector(2 downto 0);
    -- memory address register for the first operand
    signal RA_1: mem_addr;
    -- memory address register for the second operand
    signal RA_2: mem_addr;
    -- memory address register for the result
    signal RA_W: mem_addr;
    -- data register for the first operand
    signal RD_1: operand;
    -- data register for the second operand
    signal RD_2: operand;
    -- data register for the result
    signal RD_W: operand;
begin
    -- synchronous memory
    FSM: process(CLK, RST, nxt_state)
    begin
        if (RST = '1') then
            cur_state <= I;
        elsif rising_edge(CLK) then
            cur_state <= nxt_state;
        end if;
    end process;

    -- Next state
    COMB: process(cur_state, start, RO)
    begin
        case cur_state is
            when I =>
                if (start = '1') then
                    nxt_state <= F;
                else
                    nxt_state <= I;
                end if;
            when F => nxt_state <= D;
            when D =>
                if (RO = HALT) then
                    nxt_state <= H;
                elsif (RO = JNZ) then
                    nxt_state <= JNOTZ;
                else
                    nxt_state <= R;
                end if;
            when R =>
                if (RO = ADD) then
                    nxt_state <= A;
                elsif (RO = SUBT) then
                    nxt_state <= SB;  
				 elsif (RO = SHIFT) then
                    nxt_state <= SH;	
                elsif (RO = COPY) then
                    nxt_state <= CP;
                else
                    nxt_state <= I;
                end if;
            when CP => nxt_state <= CPR;
            when CPR => nxt_state <= M;
            when A | SB | SH | M => nxt_state <= W;
            when W | JNOTZ => nxt_state <= F;
            when H => nxt_state <= H;
            when others => nxt_state <= I;
        end case;
    end process;

    -- stop signal handler
    PSTOP: process (cur_state)
    begin
        if (cur_state = H) then
            stop <= '1';
        else
            stop <= '0';
        end if;
    end process;

    -- instruction counter
    PMC: process (CLK, RST, cur_state)
    begin
        if (RST = '1') then
            IC <= (others => '0');
        elsif falling_edge(CLK) then
            if (cur_state = D) then
                IC <= IC + 1;
            elsif (cur_state = JNOTZ and DP_ZF = '0') then
                IC <= RA_1;
            end if;
        end if;
    end process;

    ROM_addr <= IC;

    -- reading from ROM
    PROMREAD: process (nxt_state, cur_state)
    begin
        if (nxt_state = F or cur_state = F) then
            ROM_re <= '1';
        else
            ROM_re <= '0';
        end if;
    end process;

    -- reading the instruction from ROM and put it into RI
    PROMDAT: process (RST, cur_state, ROM_dout)
    begin
        if (RST = '1') then
            RI <= (others => '0');
        elsif (cur_state = F) then
            RI <= ROM_dout;
        end if;
    end process;

    -- fill RO and RA_1, RA_2, RA_W registers
    PRORA: process (RST, nxt_state, RI)
    begin
        if (RST = '1') then
            RO <= (others => '0');
            RA_1 <= (others => '0');
            RA_2 <= (others => '0');
            RA_W <= (others => '0');
        elsif (nxt_state = D) then
            RO <= RI (17 downto 15);
            RA_1 <= RI (14 downto 10);
            RA_2 <= RI (9 downto 5);
            RA_W <= RI (4 downto 0);
        elsif (nxt_state = CP) then
            RA_2 <= RD_2 (4 downto 0);
        elsif (nxt_state = CPR) then
            RA_W <= RA_2;
        end if;
    end process;

    PRAMST: process (RA_1, RA_2, RA_W)
    begin
        if (cur_state /= JNOTZ) then
            RAM_addr1 <= RA_1;
            RAM_addr2 <= RA_2;
            RAM_addrw <= RA_W;
        end if;
    end process;

    -- control read/write signal for RAM
    PRAMREAD: process (cur_state)
    begin
        if (cur_state = W) then
            RAM_rw <= '0';
        else
            RAM_rw <= '1';
        end if;
    end process;

    -- read values from RAM and store them in RD_1 and RD_2 registers
    PRAMDAR: process (cur_state)
    begin
        if (cur_state = R) then
            RD_1 <= RAM_d1out;
            RD_2 <= RAM_d2out;
        end if;
    end process;

    -- pass the value from data path to RAM data bus
    RAM_dwin <= DP_res;
    -- pass the value from RD_1 register to the data path first operand
    DP_op1 <= RD_1;
    -- pass the value from RD_2 register to the data path second operand
    DP_op2 <= RD_2;
    -- pass the value from RO register to the data path operation type
    DP_ot <= RO;

    paddsuben: process (cur_state)
    begin
        if (cur_state = A or cur_state = SB or cur_state = SH or cur_state = M) then
            DP_en <= '1';
        else
            DP_en <= '0';
        end if;
    end process;

end Beh_GPR;