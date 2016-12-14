library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity REP is port(
	a: in std_logic;
	z: out std_logic
);
end REP;
--
architecture REP of REP is	
begin
	z <= a;
end REP; 