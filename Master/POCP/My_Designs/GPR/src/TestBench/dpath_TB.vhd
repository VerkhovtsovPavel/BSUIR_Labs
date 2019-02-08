library gpr;
use gpr.OneHotGPR.all;
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
		OT : in operation;
		OP1 : in operand;
		OP2 : in operand;
		RES : out operand;
		ZF : out STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal EN : STD_LOGIC;
	signal OT : operation;
	signal OP1 : operand;
	signal OP2 : operand;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal RES : operand;
	signal ZF : STD_LOGIC;

	-- Add your code here ...
	constant WAIT_period: time := 10 ns;
begin

	-- Unit Under Test port map
	UUT : dpath
		port map (
			EN => EN,
			OT => OT,
			OP1 => OP1,
			OP2 => OP2,
			RES => RES,
			ZF => ZF
		);

	-- Add your stimulus here ...	
	MAIN: process
    begin
        en <= '1';
        wait for WAIT_period;
        en <= '0';
		op1 <= "0000000000000010";	
		op2 <= "0000000000000010";	   
		ot <= ADD;
        wait for WAIT_period;
        en <= '1';
        wait for WAIT_period;
        en <= '0';	  
		op1 <= "0000000000000010";	
		op2 <= "0000000000000010";	   
		ot <= SUBT;
        wait for WAIT_period;
        en <= '1';
        wait for WAIT_period;
        en <= '0';	  
		op1 <= "0000000000010010";	   
		ot <= SHIFT;
        wait for WAIT_period;
        en <= '1';
        wait for WAIT_period;
        en <= '0'; 
		op1 <= "0000000001010010";	   
		ot <= COPY;
		wait for WAIT_period;
        en <= '1';
        wait for WAIT_period;
        en <= '0'; 
        wait for WAIT_period;
        wait;
  end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_dpath of dpath_tb is
	for TB_ARCHITECTURE
		for UUT : dpath
			use entity work.dpath(beh_gpr);
		end for;
	end for;
end TESTBENCH_FOR_dpath;

