library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity RSL is port(		
	S  : in  std_logic;
	R  : in	 std_logic;
	Q  : out std_logic;
	nQ : out std_logic
	);
end RSL;

architecture structual of RSL is	   
component NOR2 is port(
			a, b: in std_logic;
			z: out std_logic);
end component;	
component REP is port(
			a: in std_logic;
			z: out std_logic);
end component;

signal pointQ, pointNQ : std_logic;
begin
	M1 : NOR2 port map (S, pointQ, pointNQ);   
	M2 : REP  port map (pointNQ, nQ);
	M3 : NOR2 port map (pointNQ, R, pointQ);
	M4 : REP  port map (pointQ, Q);
	
end structual;