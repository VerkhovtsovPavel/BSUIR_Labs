library IEEE;
use IEEE.STD_LOGIC_1164.all;

package OneHotAccum is
	
	subtype operation 	is std_logic_vector(2 downto 0);
	subtype command 	is std_logic_vector(7 downto 0);  
	subtype mem_addr 	is std_logic_vector(4 downto 0);
	subtype operand		is std_logic_vector(15 downto 0); 
	
    constant LOAD: operation 	:= "000";
    constant STORE: operation 	:= "001";
    constant ADD: operation 	:= "010";
    constant SUBT: operation 	:= "011";
    constant STOREIN: operation := "100";
    constant SHIFT: operation 	:= "101";
    constant JNZ: operation 	:= "110";
    constant HALT: operation 	:= "111";

end OneHotAccum;