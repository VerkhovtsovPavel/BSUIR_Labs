library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity MUX4_2 is port(
	a,b,a1,b1,s: in std_logic;
	z, z1: out std_logic
);
end MUX4_2;
--
architecture MUX4_2 of MUX4_2 is	
component MUX2 is port(
	a,b,s: in std_logic;
	z: out std_logic);
end component;
begin
	M1: MUX2 port map (a, b, s, z);
	M2: MUX2 port map (a1, b1, s, z1);
end MUX4_2;  