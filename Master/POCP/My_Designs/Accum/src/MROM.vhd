library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library accum;
use accum.OneHotAccum.all;

entity MROM is
	port (
		RE: in std_logic;
		ADDR: in mem_addr;
		DOUT: out command
		);
end MROM;

architecture Beh of MROM is
	type tROM is array (0 to 31) of command;	
	
	constant STUB: mem_addr 			:= "00000"; 
	constant DATA_START: mem_addr 		:= "00000";	
	constant LENGTH :mem_addr 			:= "10000";
	constant COUNTER: mem_addr 			:= "10001";
	constant CURRENT_VALUE: mem_addr 	:= "10010";
	constant INIT_VALUE: mem_addr 		:= "10011";
	constant ZERO: mem_addr 			:= "10100";
	constant ONE: mem_addr 				:= "10101";	 
	
	constant LOOP_ADDR: mem_addr := "01000"; 
	
	constant ROM: tROM :=(
 -- OP CODE    | OP1 
 	-- Init
	LOAD    & ZERO,     
	STORE   & COUNTER,        
    LOAD    & ZERO,     
	STORE   & DATA_START,
	STORE   & CURRENT_VALUE,
	LOAD    & LENGTH,
	SUBT    & ONE,
	STORE   & LENGTH,
	-- Loop	 
	LOAD    & COUNTER,        
	ADD     & ONE,    
	STORE   & COUNTER,	       
	SHIFT   & STUB,
	STORE   & CURRENT_VALUE,
	STOREIN & COUNTER,  
	LOAD    & LENGTH,   
	SUBT    & COUNTER,        
	JNZ     & LOOP_ADDR,         
	others => HALT & STUB
	);	
	
	signal data: command;
begin
	data <= ROM(conv_integer(addr));
	
	zbufs: process (RE, data)
	begin
		if (RE = '1') then
			DOUT <= data;
		else
			DOUT <= (others => 'Z');
		end if;
	end process;
end Beh;