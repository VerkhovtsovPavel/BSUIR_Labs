library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Var6 is port(
		W,X,Y,Z: in std_logic;
		F: out std_logic
		);
end Var6;
--
architecture structual of Var6 is	
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
	M2: OR2   port map (nW, X, nWX);
	M3: AND2  port map (nWX, Y, nWXY);
	M4: NOT1  port map (nWXY, WXY);
	M5: NOT1  port map (Y, nY);
	M6: OR3   port map (nW, X, nY, T);
	M7: NOT1  port map (T, nT);
	M8: OR2   port map (W, Z, WZ);
	M9: NOT1  port map (WZ, nWZ);
	M10: AND3 port map (nWZ, nT, WXY, F); 
end structual; 

architecture behavior of Var6 is	
	signal WX : std_logic; 	 
	signal WXY : std_logic; 
	signal WXY2 : std_logic;
	signal WZ : std_logic;
begin
	WX <= (not W) or X; 
	WXY <= not(WX and Y);
	WXY2 <= not((not W) or X or (not Y)); 
	WZ <= not(W or Z);
	F <= WZ and WXY and WXY2;
end behavior; 