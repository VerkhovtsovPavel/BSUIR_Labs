library ieee;
use ieee.std_logic_1164.all;
ENTITY MUX4BY2 is port(
A,B: in std_logic_vector(0 to 1); 
S : in std_logic;
Z: out std_logic_vector(0 to 1)
);
END MUX4BY2;
--
ARCHITECTURE MUX4BY2 OF MUX4BY2 IS BEGIN
Z<=A when S='0' else B;
END MUX4BY2; 