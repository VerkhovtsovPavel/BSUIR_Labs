library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_ARITH.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library stack;
use stack.OneHotStack.all;

entity DPATH is
    port(
        EN: in std_logic;
        -- synchronization
        CLK: in std_logic;
        -- operation type
        OT: in operation;
        -- operand
        OP: in operand;
        -- result
        RES: out operand;
        -- zero flag
        ZF: out std_logic;
        -- stop - the processing is finished
        Stop: out std_logic
    );
end DPATH;

architecture Beh_Stack of DPATH is
    component LIFO
        generic(
            -- address bus
            m: integer := 2;
            -- data bus
            n: integer := 2
        );
        port (
            EN: in std_logic;
            -- synchronization
            CLK: in std_logic;
            -- write/read operation type
            WR: in std_logic;
            -- read data bus
            RB: out std_logic_vector(n-1 downto 0);
            -- write data bus
            WB: in std_logic_vector(n-1 downto 0)
        );
    end component;


    type states is (I, IPOP1, IPOP2, A, SB, SH, IPUSH, MOVERES, MOVERESOP, H);
    -- I - Idle - the initial state for operations
    -- IPOP1 - POP 1 - pop the value and put it into the first internal operand i_op1
    -- IPOP2 - POP 2 - pop the value and put it into the second internal operand i_op2
    -- A - res_op = i_op1 + i_op2
    -- SB - res_op = i_op1 - i_op2
    -- IPUSH - PUSH - push the value of res_op to the stack
    -- MOVERES - i_res = i_op1 - used in external POP operation
    -- MOVERESOP - res_op = i_op - used in external push operation
    -- H - Halt - indicates that the processing has been completed
    signal nxt_state, cur_state: states;

    -- operation result
    signal res_op: operand;
    -- internal input operand value
    signal i_op: operand;
    -- internal first operand value
    signal i_op1: operand;
    -- internal second operand value
    signal i_op2: operand;
    -- the result of the data path
    signal i_res: operand;

    signal s_en: std_logic;
    signal s_wr: std_logic;
    signal s_res: operand;
    signal s_data: operand;

    signal t_zf: std_logic;
Begin
    USTACK: LIFO
        generic map(
            m => 5,
            n => 16
        )
        port map(
            CLK => CLK,
            EN => s_en,
            WR => s_wr,
            RB => s_res,
            WB => s_data
        );

    i_op <= OP;

    FSM: process(CLK, nxt_state)
    begin
        if rising_edge(CLK) then
            cur_state <= nxt_state;
        end if;
    end process;

    -- Next state
    COMB: process(cur_state, EN, OT)
    begin
        case cur_state is
            when I =>
				if (EN = '1') then	
					case OT is
						when  ADD | SUBT | SHIFT | POP | POPIN => nxt_state <= IPOP1; 
                    	when others => nxt_state <= MOVERESOP;
                    end case;
                else
                    nxt_state <= I;
                end if;
            when IPOP1 =>
                if (OT = POP or OT = POPIN) then
                    nxt_state <= MOVERES; 
				elsif (OT = SHIFT) then
                    nxt_state <= SH;	
                else
                    nxt_state <= IPOP2;
                end if;
            when IPOP2 =>
                if (OT = ADD) then
                    nxt_state <= A; 	
                else
                    nxt_state <= SB;
                end if;
            when A | SB | SH | MOVERESOP => nxt_state <= IPUSH;
            when MOVERES => nxt_state <= H;
            when IPUSH => nxt_state <= H;
            when H => nxt_state <= I;
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

    STACKCTRL: process (cur_state, nxt_state)
    begin
        if (nxt_state = IPOP1 or nxt_state = IPOP2) then
            s_wr <= '1';
            s_en <= '1';
        elsif (cur_state = IPUSH) then
            s_wr <= '0';
            s_en <= '1';
        else
            s_wr <= '1';
            s_en <= '0';
        end if;
    end process;

    OP1CTRL: process (cur_state, s_res)
    begin
        if (cur_state = IPOP1) then
            i_op1 <= s_res;
        end if;
    end process;

    OP2CTRL: process (cur_state, s_res)
    begin
        if (cur_state = IPOP2) then
            i_op2 <= s_res;
        end if;
    end process;

    OPRESULTCTRL: process (cur_state, i_op1, i_op2, i_op)
    begin
        if (cur_state = A) then
            res_op <= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(i_op1) + CONV_INTEGER(i_op2), 16);
        elsif (cur_state = SB) then
            res_op <= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(i_op1) - CONV_INTEGER(i_op2), 16);
		elsif (cur_state = SH) then	 
			for i in 0 to 14 loop
				res_op(i) <= i_op1(i) xor i_op1(i+1);
			end loop; 
			res_op(15) <= i_op1(15);
        elsif (cur_state = MOVERESOP) then
            res_op <= i_op;
        end if;
    end process;

    IRESCTRL: process (cur_state, i_op1)
    begin
        if (cur_state = MOVERES) then
            i_res <= i_op1;
        end if;
    end process;

    FLAGS: process(res_op)
    begin
        if res_op = (res_op'range => '0') then
            t_zf <= '1';
        else
            t_zf <= '0';
        end if;
    end process;

    s_data <= res_op;
    RES <= i_res;
    ZF <= t_zf;
End Beh_Stack;