library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Var5 is port(
		W,X,Y,Z: in std_logic;
		F: out std_logic
		);
end Var5;
--
architecture Var5 of Var5 is	
	signal nW : std_logic; 	 
	signal nY : std_logic; 
	signal nWX : std_logic;
	signal nWXY : std_logic;
	signal WXY : std_logic;
	signal T : std_logic;
	signal nT : std_logic;
	signal WZ : std_logic;
	signal nWZ : std_logic;
begin
	nWX <= (not W) or X
	
end Var5;  