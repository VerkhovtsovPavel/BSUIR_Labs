library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity NAND3 is port(
 A,B,C: in std_logic;
 Z: out std_logic
);
end NAND3;

architecture NAND3 of NAND3 is 
begin
  Z<=(not A) and (not B) and (not C);
end NAND3;