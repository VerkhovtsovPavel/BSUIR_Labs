library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity AND3 is port(
 A,B,C: in std_logic;
 Z: out std_logic
);
end AND3;

architecture AND3 of AND3 is 
begin
  Z<= A and B and C;
end AND3;