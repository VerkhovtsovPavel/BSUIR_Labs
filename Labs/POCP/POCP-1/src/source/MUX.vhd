library ieee;
use ieee.std_logic_1164.all;
ENTITY mux IS port(
A,B,S: in std_logic;
Z: out std_logic
);
END mux;
--
ARCHITECTURE beh OF mux IS BEGIN
Z<=A when S='0' else B;
END beh;   
