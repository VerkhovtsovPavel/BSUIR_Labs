library ieee;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity var7_tb is
end var7_tb;

architecture TB_ARCHITECTURE of var7_tb is
	-- Component declaration of the tested unit
	component var7
	port(
		W : in STD_LOGIC;
		X : in STD_LOGIC;
		Y : in STD_LOGIC;
		Z : in STD_LOGIC;
		G : out STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal W : STD_LOGIC;
	signal X : STD_LOGIC;
	signal Y : STD_LOGIC;
	signal Z : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal G, G1 : STD_LOGIC;  
	signal error : std_logic;

	-- Add your code here ...

begin

	-- Unit Under Test port map
	UUT : var7
		port map (
			W => W,
			X => X,
			Y => Y,
			Z => Z,
			G => G
		);	 
		
	UUT2 : var7
		port map (
			W => W,
			X => X,
			Y => Y,
			Z => Z,
			G => G1
		);	
		
		
	STIMULUS: process
	begin  -- of stimulus process
		--wait for <time to next event>; -- <current time>
		
		W <= '0';
		Y <= '0';
		Z <= '0';
		X <= '0';
		wait for 50 ns; --0 fs
		X <= '1';
		wait for 50 ns; --50 ns
		Y <= '1';
		X <= '0';
		wait for 50 ns; --100 ns
		X <= '1';
		wait for 50 ns; --150 ns
		Y <= '0';
		Z <= '1';
		X <= '0';
		wait for 50 ns; --200 ns
		X <= '1';
		wait for 50 ns; --250 ns
		Y <= '1';
		X <= '0';
		wait for 50 ns; --300 ns
		X <= '1';
		wait for 50 ns; --350 ns	
		W <= '1';
		Y <= '0';
		Z <= '0';
		X <= '0';
		wait for 50 ns; --400 ns
		X <= '1';
		wait for 50 ns; --450 ns
		Y <= '1';
		X <= '0';
		wait for 50 ns; --500 ns
		X <= '1';
		wait for 50 ns; --550 ns
		Y <= '0';
		Z <= '1';
		X <= '0';
		wait for 50 ns; --600 ns
		X <= '1';
		wait for 50 ns; --650 ns
		Y <= '1';
		X <= '0';
		wait for 50 ns; --700 ns
		X <= '1';
		wait for 50 ns; --750 ns 
		W <= '0';
		Y <= '0';
		Z <= '0';
		X <= '0';
		--	end of stimulus events 800 ns
		wait;
	end process; -- end of stimulus process
	
	error <= G1 xor G;

	-- Add your stimulus here ...

end TB_ARCHITECTURE;

