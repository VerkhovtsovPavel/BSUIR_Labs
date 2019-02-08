library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity NOT1 is port(
	a: in std_logic;
	z: out std_logic
);
end NOT1;
--
architecture NOT1 of NOT1 is	
begin
	z <= not a;
end NOT1; 