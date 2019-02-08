library ieee;
use ieee.std_logic_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;

entity traffic_lights_T is
end traffic_lights_T;

architecture Beh of traffic_lights_t is
	component TrafficLights is
		port(
			CLK: in std_logic;
			CWAIT: in std_logic;
			RST: in std_logic;
			START: in std_logic;
			R, Y, G: out std_logic
			);
	end component;
	
	signal clk: std_logic := '0';
	signal cwait: std_logic := '0';
	signal rst: std_logic := '0';
	signal start: std_logic := '0';
	signal R, Y, G: std_logic; 
	constant CLK_period: time := 10 ns;
Begin
	UTRAFFIC: TrafficLights port map(
		CLK => CLK,
		CWAIT => cwait,
		RST => rst,
		START => start,
		R => r,
		Y => Y,
		G => g
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
		--cwait <= '1';
		wait for 2 * CLK_Period;
		start <= '1';
		wait for 2 * CLK_Period;
		cwait <= '1';
		wait;
	end process;
end Beh;	

configuration config of traffic_lights_t is
	for Beh
		for UTRAFFIC : trafficlights
			use entity work.TrafficLights(Beh);
		end for;
	end for;
end config;