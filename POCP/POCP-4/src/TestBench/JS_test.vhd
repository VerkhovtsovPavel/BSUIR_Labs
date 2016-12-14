library ieee;
use ieee.std_logic_1164.all;

entity JS_TEST is
end JS_TEST;

architecture Beh of JS_TEST is
	component JC
		port (
			CLK: in std_logic;
			RST: in std_logic;
			LS: in std_logic;
			Pin: in std_logic_vector(0 to 3);
			Pout: out std_logic_vector(0 to 3)
			);
	end component;
	signal CLK: std_logic := '0';
	signal RST: std_logic := '0';
	signal LS: std_logic := '0';
	signal Pin: std_logic_vector(0 to 3) := (others => '0');
	
	signal Pout: std_logic_vector(0 to 3);
	constant CLK_Period: time := 10 ns;
begin
	uut: JC port map (
		CLK => CLK,
		RST => RST,
		LS => LS,
		PIn => Pin,
		POut => POut
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
		RST <= '0'; wait for CLK_Period;
		
		PIn <= "1111"; wait for CLK_Period;
		LS <= '1'; wait for 8*CLK_period;
	end process;
end Beh;   

configuration TESTBENCH_FOR_JS of JS_TEST is
	for Beh
		for UUT : jc
			use entity work.jc(behavior);
		end for;
	end for;
end TESTBENCH_FOR_JS;