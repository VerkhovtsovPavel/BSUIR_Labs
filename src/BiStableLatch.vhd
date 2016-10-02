library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity BiStableLatch is port(
	Q  : out std_logic;
	nQ : out std_logic
	);
end BiStableLatch;

architecture structual of BiStableLatch is	   
component NOT1 is port(
			a: in std_logic;
			z: out std_logic);
end component;	
component REP is port(
			a: in std_logic;
			z: out std_logic);
end component;	

signal pointQ, pointnQ : std_logic; 

begin
	M1 : NOT1 port map (pointQ, pointnQ);	
	M2 : REP  port map (pointnQ, nQ);
	M3 : NOT1 port map (pointnQ, pointQ);
	M4 : REP  port map (pointQ, Q);
end structual; 
	