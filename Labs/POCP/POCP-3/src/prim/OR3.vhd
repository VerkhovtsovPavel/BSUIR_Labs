library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity OR3 is port(
 A,B,C: in std_logic;
 Z: out std_logic
);
end OR3;

architecture OR3 of OR3 is 
begin
  Z<=A or B or C;
end OR3;