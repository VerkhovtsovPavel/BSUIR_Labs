library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity AND2 is port(
	a,b: in std_logic;
	z: out std_logic
);
end AND2;
--
architecture AND2 of AND2 is	
begin
	z <=a and b;
end AND2; 