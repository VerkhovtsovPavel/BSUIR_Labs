library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Var5 is port(
		W,X,Y,Z: in std_logic;
		F: out std_logic
		);
end Var5;
--
architecture Var5 of Var5 is	
	signal WX : std_logic; 	 
	signal WXY : std_logic; 
	signal WXY2 : std_logic;
	signal WZ : std_logic;
begin
	WX <= (not W) or X; 
	WXY <= not(WX and Y);
	WXY2 <= not((not W) or X or (not Y)); 
	WZ <= not(W or Z);
	F <= not(WZ) and not(WXY) and not(WXY2);
end Var5;  