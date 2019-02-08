library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all; 
library gpr;
use gpr.OneHotGPR.all;

entity MRAM is
    port(
        -- 0 - write; 1 - read
        RW: in std_logic;
        CLK: in std_logic;
        -- address for the first operand
        ADDR1: in mem_addr;
        -- address for the second operand
        ADDR2: in mem_addr;
        -- address to write
        ADDRW: in mem_addr;
        -- first operand
        D1OUT: out operand;
        -- second operand
        D2OUT: out operand;
        -- data to write
        DWIN: in operand
    );
end MRAM;

architecture Beh_GPR of MRAM is
    type tRAM is array (0 to 31) of operand;
    signal RAM: tRAM:= (  
--      |    VALUE BIN			| ADR BIN 	|  		CONST NAME 		|
        "0000000000000000", --  | 00000  	| STUB, ADDR_DATA_START	|
        "0000000000000000", --	| 00001  	|   					|
        "0000000000000000", -- 	| 00010  	|						|
        "0000000000000000", --  | 00011  	| 						|
        "0000000000000000", --  | 00100  	|						|
        "0000000000000000", --  | 00101  	| 						|
        "0000000000000000", --  | 00110  	|						|
        "0000000000000000", --  | 00111  	| 						|
        "0000000000000000", --  | 01000  	| 						|
        "0000000000000000", --  | 01001  	| 						|
        "0000000000000000", --  | 01010  	| 						|
        "0000000000000000", --  | 01011  	| 						|
        "0000000000000000", --  | 01100  	| 						|
        "0000000000000000", --  | 01101  	|    					|
        "0000000000000000", --  | 01110  	|   					|
        "0000000000000000", --  | 01111  	|   					|
        "0000000000001000", --  | 10000  	| ADDR_LENGTH   		|
        "0000000000000000", --  | 10001  	| ADDR_COUNTER			|
        "0000000000000000", --  | 10010  	| ADDR_CURRENT_VALUE	|
		"0000000000000001", -- 	| 10011  	| ADDR_INIT_VALUE		|
		"0000000000000000", -- 	| 10100  	| ADDR_ZERO				|
		"0000000000000001", -- 	| 10101  	| ADDR_ONE				|
		"0000000000000000", -- 	| 10110  	| EMPTY_SPACE			|
        others => "0000000000000000"
    ); 

    signal data_win: operand;
    signal data_1out: operand;
    signal data_2out: operand;
Begin
    data_win <= DWIN;

    WRITE: process(CLK)
    begin
        if (rising_edge(CLK)) then
            if (RW = '0') then
                RAM(conv_integer(ADDRW)) <= data_win;
            end if;
        end if;
    end process;

    data_1out <= RAM (conv_integer(ADDR1));
    data_2out <= RAM (conv_integer(ADDR2));

    READ: process(CLK)
    begin
        if (rising_edge(CLK)) then
            if (RW = '1') then
                D1OUT <= data_1out;
                D2OUT <= data_2out;
            else
                D1OUT <= (others => 'Z');
                D2OUT <= (others => 'Z');
            end if;
        end if;
    end process;
End Beh_GPR;
