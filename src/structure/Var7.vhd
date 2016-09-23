library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Var7 is port(
	W,X,Y,Z: in std_logic;
	G: out std_logic
);
end Var7;
--
architecture Var7 of Var7 is	
signal WXY : std_logic;
signal YZ : std_logic;
component AND2 is port(
	a,b: in std_logic;
	z: out std_logic);
end component;
component OR2 is port(
	a,b: in std_logic;
	z: out std_logic);
end component;	
component AND3 is port(
    A,B,C: in std_logic;
    Z: out std_logic);	
end component;		
begin
	M1: AND3  port map (W, X, Y, WXY); 
	M2: AND2  port map (Y, Z, YZ);
	M3: OR2   port map (WXY, YZ, G);
end Var7;  