library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library stack;
use stack.OneHotStack.all;

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
        RAM_addr: out mem_addr;
        RAM_din: out operand;
        RAM_dout: in operand;

        --datapath
        DP_op: out operand;
        DP_ot: out operation;
        DP_en: out std_logic;
        DP_res: in operand;
        DP_zf:  in std_logic;
        DP_stop: in std_logic
    );
end CTRL1;

architecture Beh_Stack of CTRL1 is
    type states is (I, F, D, R, S, H, LIN, J, DP, DPW);
    -- I - idle
    -- F - fetch
    -- D - decode
    -- R - read
    -- S - store
    -- H - halt
    -- LIN - load indirect
    -- J - jump if zero bit not set
    -- DP - data path operation trigger
    -- DPW - data path wait
    signal nxt_state, cur_state: states;
    -- instruction register
    signal RI: command;
    -- instruction counter
    signal IC: mem_addr;
    -- operation type register
    signal RO: operation;
    -- memory address register
    signal RA: mem_addr;
    -- data register
    signal RD: operand;
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
    COMB: process(cur_state, start, RO, DP_stop)
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
				case RO is
				 		when ADD | SUBT | SHIFT | POP => nxt_state <= DP;
						when HALT => nxt_state <= H; 
						when JNZ => nxt_state <= J;
                      	when others => nxt_state <= R;
                end case;
            when R =>
                if (RO = PUSH) then
                    nxt_state <= DP;
                elsif (RO = POPIN) then
                    nxt_state <= LIN;
                else
                    nxt_state <= I;
                end if;
            when LIN => nxt_state <= DP;
            when DP => nxt_state <= DPW;
            when DPW =>
                if (DP_stop = '0') then
                    nxt_state <= DPW;
                else
                    if (RO = POP or RO = POPIN) then
                        nxt_state <= S;
                    else
                        nxt_state <= F;
                    end if;
                end if;
            when S | J => nxt_state <= F;
            when H => nxt_state <= H;
            when others => nxt_state <= I;
        end case;
    end process;

    -- stop signal
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
            IC <= "00000";
        elsif falling_edge(CLK) then
            if (cur_state = D) then
                IC <= IC + 1;
            elsif (cur_state = J and DP_ZF = '0') then
                IC <= RA; 
			end if;	
        end if;
    end process;

    ROM_addr <= IC;

    -- ROM read signal
    PROMREAD: process (nxt_state, cur_state)
    begin
        if (nxt_state = F or cur_state = F) then
            ROM_re <= '1';
        else
            ROM_re <= '0';
        end if;
    end process;

    -- read ROM value and put it into RI
    PROMDAT: process (RST, cur_state, ROM_dout)
    begin
        if (RST = '1') then
            RI <= (others => '0');
        elsif (cur_state = F) then
            RI <= ROM_dout;
        end if;
    end process;

    -- RO and RA control
    PRORA: process (RST, nxt_state, RI)
    begin
        if (RST = '1') then
            RO <= (others => '0');
            RA <= (others => '0');
        elsif (nxt_state = D) then
            RO <= RI (7 downto 5);
            RA <= RI (4 downto 0);
        elsif (nxt_state = LIN) then
            RA <= RD (4 downto 0);
        end if;
    end process;

    PRAMST: process (RA)
    begin
        if (cur_state /= J) then
            RAM_addr <= RA;
        end if;
    end process;

    -- RAM read/write control
    PRAMREAD: process (cur_state)
    begin
        if (cur_state = S) then
            RAM_rw <= '0';
        else
            RAM_rw <= '1';
        end if;
    end process;

    -- read value from RAM and put it into RD
    PRAMDAR: process (cur_state)
    begin
        if (cur_state = R) then
            RD <= RAM_dout;
        end if;
    end process;

    -- move the value from DPATH to RAM input bus
    RAM_din <= DP_res;
    -- move the value from RD to datapath
    DP_op <= RD;
    -- move RO value to DP operation bus
    DP_ot <= RO;

    pdpathen: process (cur_state)
    begin
        if (cur_state = DP) then
            DP_en <= '1';
        else
            DP_en <= '0';
        end if;
    end process;
end Beh_Stack;