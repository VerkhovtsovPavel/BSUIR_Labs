library ieee;
use ieee.std_logic_1164.all;

entity signature_T is
end signature_T;

architecture Beh of signature_T is
	component Signature
		port (
			CLK: in std_logic;
			RST: in std_logic;
			Pin: in std_logic;
			Pout: out std_logic_vector(0 to 15)
			);
	end component;
	signal CLK: std_logic := '0';
	signal RST: std_logic := '0';
	signal Pin: std_logic := '0';
	signal Pout: std_logic_vector(0 to 15);
	constant CLK_Period: time := 10 ns;
begin
	uut: Signature port map (
		CLK => CLK,
		RST => RST,
		PIn => Pin,
		Pout => Pout
		);
	
	CLK_Process: process
	begin
		CLK <= '0';
		wait for CLK_Period/2;
		CLK <= '1';
		wait for CLK_Period/2;
	end process;
	
	stim_proc: process
	begin
		wait for CLK_Period;
		
		RST <= '0'; wait for CLK_Period;
		RST <= '1'; wait for 2*CLK_Period;	 
		PIn <= '1'; wait for CLK_Period;
		RST <= '0'; wait for CLK_Period;
		
		
		PIn <= '1'; wait for CLK_Period;
		PIn <= '0'; wait for CLK_Period;
		PIn <= '0'; wait for CLK_Period;
		PIn <= '0'; wait for CLK_Period;
		PIn <= '0'; wait for CLK_Period;
		PIn <= '1'; wait for CLK_Period;
		PIn <= '1'; wait for CLK_Period;
		wait for 8*CLK_period;
	end process;
end Beh;

configuration TESTBENCH_FOR_signature of signature_T is
	for Beh
		for UUT : Signature
			use entity work.signature(behavior);
		end for;
	end for;
end TESTBENCH_FOR_signature;