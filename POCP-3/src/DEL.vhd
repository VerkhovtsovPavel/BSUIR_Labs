
library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DEL is port(
		D : in std_logic;
		E : in std_logic;
		Q, nQ : out std_logic
	);
end DEL;

architecture DEL of DEL is	
component RSL  is port(
	S,R 	: in std_logic;
	Q, nQ 	: out std_logic
	); 
end component;	
component NOT1  is port(
	a	: in std_logic;
	z	: out std_logic
	); 
end component;	
component AND2 is port(
	a,b: in std_logic;
	z: out std_logic
	);	
end component;	
signal nD  : std_logic;
signal DE  : std_logic;
signal nDE : std_logic;
begin
	M1: NOT1 port map (D, nD);
	M2: AND2 port map (D, E, DE);
	M3: AND2 port map (nD, E, nDE);
   	M4: RSL  port map (DE, nDE, Q, nQ);
end DEL;
