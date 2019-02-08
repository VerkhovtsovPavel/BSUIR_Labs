
library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DL is port(
		D : in std_logic;
		Q, nQ : out std_logic
	);
end DL;

architecture DL of DL is	
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
signal nD : std_logic;
begin
	M1: NOT1 port map (D, nD);
   	M2: RSL  port map (D, nD, Q, nQ);
end DL;
