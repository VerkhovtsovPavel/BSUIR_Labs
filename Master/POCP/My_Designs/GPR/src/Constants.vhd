library IEEE;
use IEEE.STD_LOGIC_1164.all;

package OneHotGPR is  
	subtype operation 	is std_logic_vector(2 downto 0);
	subtype command 	is std_logic_vector(17 downto 0);  
	subtype mem_addr 	is std_logic_vector(4 downto 0);
	subtype operand		is std_logic_vector(15 downto 0); 
	
    constant ADD: operation 	:= "001";
    constant SUBT: operation 	:= "010";
    constant SHIFT: operation 	:= "011";
    constant JNZ: operation 	:= "100";
    constant COPY: operation 	:= "101"; 
	constant HALT: operation 	:= "111";	  
end OneHotGPR;