library stack;
use stack.OneHotStack.all;
library ieee;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity mrom_tb is
end mrom_tb;

architecture TB_ARCHITECTURE of mrom_tb is
	-- Component declaration of the tested unit
	component mrom
	port(
		RE : in STD_LOGIC;
		ADDR : in mem_addr;
		DOUT : out command );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal RE : STD_LOGIC;
	signal ADDR : mem_addr;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal DOUT : command;

	constant WAIT_period: time := 10 ns;

begin

	-- Unit Under Test port map
	UUT : mrom
		port map (
			RE => RE,
			ADDR => ADDR,
			DOUT => DOUT
		);

	-- Add your stimulus here ...
	
	main: process
    begin 
		re <= '0';
        addr <= "00010";
        wait for 1 * WAIT_period;
        re <= '1';
        wait for 1 * WAIT_period;
        addr <= "00000";
        re <= '1';
        wait for 1 * WAIT_period;
		re <= '0';
        wait for 100 * WAIT_period;
        wait;
	end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_mrom of mrom_tb is
	for TB_ARCHITECTURE
		for UUT : mrom
			use entity work.mrom(beh_stack);
		end for;
	end for;
end TESTBENCH_FOR_mrom;

