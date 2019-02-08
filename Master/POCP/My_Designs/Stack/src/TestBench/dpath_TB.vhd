library stack;
use stack.OneHotStack.all;
library ieee;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

	-- Add your library and packages declaration here ...

entity dpath_tb is
end dpath_tb;

architecture TB_ARCHITECTURE of dpath_tb is
	-- Component declaration of the tested unit
	component dpath
	port(
		EN : in STD_LOGIC;
		CLK : in STD_LOGIC;
		OT : in operation;
		OP : in operand;
		RES : out operand;
		ZF : out STD_LOGIC;
		Stop : out STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal EN : STD_LOGIC;
	signal CLK : STD_LOGIC;
	signal OT : operation;
	signal OP : operand;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal RES : operand;
	signal ZF : STD_LOGIC;
	signal Stop : STD_LOGIC;

	constant CLK_Period: time := 10 ns;
	constant Stop_WAIT: time := 5 * CLK_Period;

begin

	-- Unit Under Test port map
	UUT : dpath
		port map (
			EN => EN,
			CLK => CLK,
			OT => OT,
			OP => OP,
			RES => RES,
			ZF => ZF,
			Stop => Stop
		);

	 CLK_Process: process
    begin
        CLK <= '0';
        wait for CLK_Period/2;
        CLK <= '1';
        wait for CLK_Period/2;
    end process;

    MAIN: process
    begin
        wait for clk_period;
        en <= '0';
        op <= "0000000000000010";
		ot <= PUSH;
		wait for clk_period;
        en <= '1';       
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= ADD;
        wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= POP;
        wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= PUSH;
		wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= SUBT;
        wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= POP;
        wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= PUSH;
		wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT; 
		ot <= SHIFT;
        wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
		ot <= POP;
        wait for clk_period;
        en <= '1';
        wait for clk_period;
		en <= '0';
        wait for Stop_WAIT;
        wait;
end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_dpath of dpath_tb is
	for TB_ARCHITECTURE
		for UUT : dpath
			use entity work.dpath(beh_stack);
		end for;
	end for;
end TESTBENCH_FOR_dpath;

