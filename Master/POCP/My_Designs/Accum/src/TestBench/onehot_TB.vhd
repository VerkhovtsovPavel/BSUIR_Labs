library ieee;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity onehot_tb is
end onehot_tb;

architecture TB_ARCHITECTURE of onehot_tb is
	-- Component declaration of the tested unit
	component onehot
	port(
		CLK : in STD_LOGIC;
		RST : in STD_LOGIC;
		Start : in STD_LOGIC;
		Stop : out STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal CLK : STD_LOGIC;
	signal RST : STD_LOGIC;
	signal Start : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal Stop : STD_LOGIC;
   	constant CLK_period: time := 10 ns;
begin

	-- Unit Under Test port map
	UUT : onehot
		port map (
			CLK => CLK,
			RST => RST,
			Start => Start,
			Stop => Stop
		);

	CLK_Process: process
	begin
		CLK <= '0';
		wait for CLK_Period/2;
		CLK <= '1';
		wait for CLK_Period/2;
	end process;
	
	main: process
	begin
		rst <= '1';
		wait for 1 * CLK_PERIOD;
		rst <= '0';
		start <= '1';
		wait for 100 * CLK_PERIOD;
		wait;
	end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_onehot of onehot_tb is
	for TB_ARCHITECTURE
		for UUT : onehot
			use entity work.onehot(beh);
		end for;
	end for;
end TESTBENCH_FOR_onehot;

