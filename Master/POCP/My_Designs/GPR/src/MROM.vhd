library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library gpr;
use gpr.OneHotGPR.all;

entity MROM is
	port (
		RE: in std_logic;
		ADDR: in mem_addr;
		DOUT: out command
		);
end MROM;

architecture Beh_GPR of MROM is
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

	constant LOOP_ADDR: mem_addr 		:= "00100"; 
	
	constant ROM: tROM :=(
-- OP CODE	| OP1			| OP2       	| RESULT   
	-- Init
	ADD		& ZERO       	& ZERO		 	& DATA_START,	
	ADD		& ZERO       	& ZERO		 	& CURRENT_VALUE, 
	ADD    	& ZERO       	& ZERO 	  		& COUNTER, 
	SUBT	& LENGTH		& ONE			& LENGTH,
	-- Loop	
	ADD    	& COUNTER   	& ONE 	  	  	& COUNTER,	
	SHIFT	& COUNTER		& STUB			& CURRENT_VALUE,
	COPY	& CURRENT_VALUE	& COUNTER		& STUB,
	SUBT   	& LENGTH   		& COUNTER    	& EMPTY_SPACE, 
	JNZ     & LOOP_ADDR  	& STUB      	& STUB,
	others => HALT & STUB & STUB & STUB
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
end Beh_GPR;
