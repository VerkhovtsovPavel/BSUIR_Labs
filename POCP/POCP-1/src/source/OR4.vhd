library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity OR4 is port(
 A,B,C,D: in std_logic;
 Z: out std_logic
);
end OR4;

architecture OR4 of OR4 is 
begin
  Z<=A or B or C or D;
end OR4;
