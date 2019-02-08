library IEEE;
use IEEE.STD_LOGIC_1164.all;

package OneHotStack is	
	subtype operation 	is std_logic_vector(2 downto 0);
	subtype command 	is std_logic_vector(7 downto 0);  
	subtype mem_addr 	is std_logic_vector(4 downto 0);
	subtype operand		is std_logic_vector(15 downto 0); 
	
    constant ADD: operation 		:= "000";
    constant SUBT: operation 		:= "001";
    constant SHIFT: operation 		:= "010";
    constant JNZ: operation 		:= "011";
    constant PUSH: operation 		:= "100";
    constant POP: operation 		:= "101";
    constant POPIN: operation 		:= "110";
	constant HALT: operation 		:= "111";
end OneHotStack;