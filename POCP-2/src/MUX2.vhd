library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity mux2 is port(
	a,b,s: in std_logic;
	z: out std_logic
);
end mux2;
--
architecture mux2 of mux2 is	
signal nb : std_logic; 
signal v : std_logic;
signal w : std_logic;
component AND2 is port(
	a,b: in std_logic;
	z: out std_logic);
end component;
component OR2 is port(
	a,b: in std_logic;
	z: out std_logic);
end component;
component NOT1 is port(
	a: in std_logic;
	z: out std_logic);
end component;
begin
	M1: NOT1 port map (b, nb);
	M2: AND2 port map (a, nb, v);
	M3: AND2 port map (s, b, w);
	M4: OR2  port map (v, w, z);
end mux2;  