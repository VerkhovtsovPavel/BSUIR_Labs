library ieee;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity syncregn_tb is
	-- Generic declarations of the tested unit
		generic(
		n : INTEGER := 4 );
end syncregn_tb;

architecture TB_ARCHITECTURE of syncregn_tb is
	-- Component declaration of the tested unit
	component syncregn
		generic(
		n : INTEGER := 4 );
	port(
		Din : in STD_LOGIC_VECTOR(n-1 downto 0);
		EN : in STD_LOGIC;
		C : in STD_LOGIC;
		Dout : out STD_LOGIC_VECTOR(n-1 downto 0) );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal Din : STD_LOGIC_VECTOR(n-1 downto 0);
	signal EN : STD_LOGIC;
	signal C : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal Dout : STD_LOGIC_VECTOR(n-1 downto 0);  
	constant CLK_Period: time := 10 ns;

begin

	-- Unit Under Test port map
	UUT : syncregn
		generic map (
			n => n
		)

		port map (
			Din => Din,
			EN => EN,
			C => C,
			Dout => Dout
		);
		
	CLK_Process: process
	begin
		C <= '0';
		wait for CLK_Period/2;
		C <= '1';
		wait for CLK_Period/2;
	end process;	
		
	stim_proc: process
	begin
		wait for CLK_Period;
		Din <= "1111";
		EN <= '1'; wait for CLK_Period;
		EN <= '0'; wait for CLK_Period;
		Din <= "0000"; wait for 2*CLK_Period;
		EN <= '1'; wait for CLK_Period;
		Din <= "1111"; wait for CLK_Period;
	end process;
end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_syncregn of syncregn_tb is
	for TB_ARCHITECTURE
		for UUT : syncregn
			use entity work.syncregn(behavior);
		end for;
	end for;
end TESTBENCH_FOR_syncregn;

