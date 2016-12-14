library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity mux_2 is port(
	in1,in2,in3: in std_logic;
	Q, nQ: out std_logic
);
end mux_2;
--
architecture mux_2 of mux_2 is	
signal result : std_logic;
begin
	result  <=(in1 and in2) or (in3 and (not in2));
	Q <= result;
	nQ <= (not result);
end mux_2;  