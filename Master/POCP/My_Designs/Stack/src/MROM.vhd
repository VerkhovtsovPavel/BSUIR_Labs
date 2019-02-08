library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library stack;
use stack.OneHotStack.all;

entity MROM is
    port (
        RE: in std_logic;
        ADDR: in mem_addr;
        DOUT: out command
    );
end MROM;

architecture Beh_Stack of MROM is
    type tROM is array (0 to 31) of command;
	
	constant STUB: mem_addr 			:= "00000"; 
	constant LENGTH: mem_addr 			:= "10000";
	constant COUNTER: mem_addr 			:= "10001";
	constant CURRENT_VALUE: mem_addr 	:= "10010";
	constant DATA_START: mem_addr 		:= "00000";	
	constant INIT_VALUE: mem_addr 		:= "10011";
	constant ZERO: mem_addr 			:= "10100";
	constant ONE: mem_addr 				:= "10101";
	constant EMPTY_SPACE: mem_addr 		:= "10110";

	constant LOADDR: mem_addr 			:= "01010"; 

    constant ROM: tROM :=(
    --  OP CODE   | RAM ADDR 
	-- Init
        PUSH 	& ZERO,     
        POP   	& COUNTER,      
        PUSH   	& ZERO,  
		POP    	& CURRENT_VALUE, 
		PUSH  	& ZERO,  
		POP    	& DATA_START,
		PUSH	& ONE,
		PUSH	& LENGTH,
		SUBT   	& STUB,
		POP	  	& LENGTH,	
    --  Loop
		PUSH   	& COUNTER,        
		PUSH   	& ONE,
		ADD		& STUB,	
		POP   	& COUNTER,	
		PUSH    & COUNTER,        
		SHIFT   & STUB,
		POP   	& CURRENT_VALUE,
		PUSH   	& CURRENT_VALUE,
		POPIN	& COUNTER,   
		PUSH    & LENGTH,
		PUSH    & COUNTER,
		SUBT   	& STUB,
		JNZ     & LOADDR,     
        others => HALT & STUB
    );
    signal data: command;
begin
    data <= ROM(conv_integer(ADDR));

    zbufs: process (RE, data)
    begin
        if (RE = '1') then
            DOUT <= data;
        else
            DOUT <= (others => 'Z');
        end if;
    end process;
end Beh_Stack;