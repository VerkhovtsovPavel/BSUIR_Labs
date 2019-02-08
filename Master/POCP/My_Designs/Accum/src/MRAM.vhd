library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library accum;
use accum.OneHotAccum.all;

entity MRAM is
    port (
        CLK: in std_logic;
        RW: in std_logic;
        ADDR: in mem_addr;
        DIN: in operand;
        DOUT: out operand
    );
end MRAM;

architecture Beh of MRAM is
    type tRAM is array (0 to 31) of operand;
    signal RAM: tRAM:= (
--      | BIN 					| ADR BIN 
        "0000000000000000", --  | 00000  |  
        "0000000000000000", --	| 00001  |   
        "0000000000000000", -- 	| 00010  |
        "0000000000000000", --  | 00011  | 
        "0000000000000000", --  | 00100  |
        "0000000000000000", --  | 00101  | 
        "0000000000000000", --  | 00110  |
        "0000000000000000", --  | 00111  | 
        "0000000000000000", --  | 01000  | 
        "0000000000000000", --  | 01001  | 
        "0000000000000000", --  | 01010  | 
        "0000000000000000", --  | 01011  | 
        "0000000000000000", --  | 01100  | 
        "0000000000000000", --  | 01101  |    
        "0000000000000000", --  | 01110  |   
        "0000000000000000", --  | 01111  |   
        "0000000000001000", --  | 10000  |    
        "0000000000000000", --  | 10001  | 
        "0000000000000000", --  | 10010  |	 
		"0000000000000001", -- 	| 10011  |
		"0000000000000000", -- 	| 10100  |
		"0000000000000001", -- 	| 10101  |
        others => "0000000000000000"
    );
    signal data_in: operand;
    signal data_out: operand;	  
Begin
    data_in <= Din;

    WRITE: process (CLK, RW, ADDR, data_in)
    begin
        if (RW = '0') then
            if (rising_edge(CLK)) then
                RAM(conv_integer(ADDR)) <= data_in;
             end if;
        end if;
    end process;

    data_out <= RAM (conv_integer(ADDR));

    RDP: process (RW, RAM, data_out)
    begin
        if (RW = '1') then
            DOUT <= data_out;
        else
            DOUT <= (others => 'Z');
        end if;
    end process;
end Beh;