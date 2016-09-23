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
begin
	WXY <= W and X and Y;
	YZ <= Y and Z;
	G <= WXY or YZ;
end Var7;  