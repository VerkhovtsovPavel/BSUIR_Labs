library ieee;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity lfsr_in_tb is
	-- Generic declarations of the tested unit
		generic(
		n : INTEGER := 2 );
end lfsr_in_tb;

architecture TB_ARCHITECTURE of lfsr_in_tb is
	-- Component declaration of the tested unit
	component lfsr_in
		generic(
		n : INTEGER := 2 );
	port(
		CLK : in STD_LOGIC;
		RST : in STD_LOGIC;
		LS : in STD_LOGIC;
		Pin : in STD_LOGIC_VECTOR(0 to 2**n-1);
		Pout : out STD_LOGIC_VECTOR(0 to 2**n-1) );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal CLK : STD_LOGIC;
	signal RST : STD_LOGIC;
	signal LS : STD_LOGIC;
	signal Pin : STD_LOGIC_VECTOR(0 to 2**n-1);
	-- Observed signals - signals mapped to the output ports of tested entity
	signal Pout : STD_LOGIC_VECTOR(0 to 2**n-1);

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : lfsr_in
		generic map (
			n => n
		)

		port map (
			CLK => CLK,
			RST => RST,
			LS => LS,
			Pin => Pin,
			Pout => Pout
		);

	-- Add your stimulus here ...

end TB_ARCHITECTURE;


