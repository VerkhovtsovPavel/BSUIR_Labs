library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Var6 is port(
	W,X,Y,Z: in std_logic;
	F: out std_logic
);
end Var6;
--
architecture Var6 of Var6 is	
signal nW : std_logic; 	 
signal nY : std_logic; 
signal nWX : std_logic;
signal nWXY : std_logic;
signal WXY : std_logic;
signal T : std_logic;
signal nT : std_logic;
signal WZ : std_logic;
signal nWZ : std_logic;
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
component AND3 is port(
    A,B,C: in std_logic;
    Z: out std_logic);	
end component;	
component OR3 is port (	   
    A,B,C: in std_logic;
    Z: out std_logic);
end component;	
begin
	M1: NOT1  port map (W, nW);
	M2: OR2   port map (nW, Y, nWXY);
	M3: NOT1  port map (nWXY, WXY);
	M4: NOT1  port map (Y, nY);
	M5: OR3   port map (nW, X, nY, T);
	M6: NOT1  port map (T, nT);
	M7: OR2   port map (W, Z, WZ);
	M8: NOT1  port map (WZ, nWZ);
	M9: AND3 port map (nWZ, T, WXY, F); 
end Var6;  