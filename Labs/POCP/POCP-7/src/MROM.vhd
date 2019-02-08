library IEEE;
use IEEE.STD_LOGIC_1164.all; 
use IEEE.STD_LOGIC_UNSIGNED.all;

-- LOAD - 000
-- STORE - 001
-- ADD - 010
-- SUB - 011
-- HALT - 100
-- JNZ - 101
-- JNSB - 110

entity MROM is 
	port (
		RE: in std_logic;
		ADR: in std_logic_vector(5 downto 0);
		DOUT: out std_logic_vector(8 downto 0)
		);
end MROM;

architecture Beh_Max of MROM is
	subtype inst is std_logic_vector(8 downto 0);
	type tROM is array (0 to 63) of inst;
	constant ROM: tROM :=(
--	OP_CODE | RAM ADR |  N Hex  | N DEC | instruction
	"000" & "000001", -- 000000 | 00 	|LOAD a[0]
	"001" & "000110", -- 000001 | 01 	|STORE res
	"011" & "000010", -- 000010 | 02 	|SUB a[1]
	"110" & "000110", -- 000011 | 03 	|JNSB m1
	"000" & "000010", -- 000100 | 04 	|LOAD a[1]
	"001" & "000110", -- 000101 | 05 	|STORE res
	"000" & "000110", -- 000110 | 06 	|LOAD res	: m1
	"011" & "000011", -- 000111 | 07 	|SUB a[2]
	"110" & "001011", -- 001000 | 08 	|JNSB m2
	"000" & "000011", -- 001001 | 09 	|LOAD a[2]
	"001" & "000110", -- 001010 | 10 	|STORE res
	"000" & "000110", -- 001011 | 11 	|LOAD res	: m2
	"011" & "000100", -- 001100 | 12 	|SUB a[3]
	"110" & "010000", -- 001101 | 13 	|JNSB m3
	"000" & "000100", -- 001110 | 14 	|LOAD a[3]
	"001" & "000110", -- 001111 | 15 	|STORE res
	"000" & "000110", -- 010000 | 16 	|LOAD res	: m3
	"011" & "000101", -- 010001 | 17 	|SUB a[4]
	"110" & "010101", -- 010010 | 18 	|JNSB m4
	"000" & "000101", -- 010011 | 19 	|LOAD a[4]
	"001" & "000110", -- 010100 | 20 	|STORE res
	"000" & "000110", -- 010101 | 21 	|LOAD res	: m4
	"100" & "000000", -- 010110 | 22 	|HALT
	others => "100" & "000000"
	);
	signal data: inst;
begin
	data <= ROM(conv_integer(adr));
	zbufs: process (RE, data)
	begin
		if (RE = '1') then
			DOUT <= data;
		else
			DOUT <= (others => 'Z');
		end if;
	end process;
end Beh_Max;

architecture Beh_Zer of MROM is
	subtype inst is std_logic_vector(8 downto 0);
	type tROM is array (0 to 63) of inst;
	constant ROM: tROM :=(
--	OP_CODE | RAM ADR |  N Hex  | N DEC | instruction
	"000" & "000001", -- 000000 | 00 	| LOAD a[0]
	"101" & "000101", -- 000001 | 01	| JNZ m1
	"000" & "000110", -- 000010 | 02	| LOAD res
	"010" & "000111", -- 000011 | 03	| ADD 1
	"001" & "000110", -- 000100 | 04	| STORE res
	"000" & "000010", -- 000101 | 05 	| LOAD a[1] : m1
	"101" & "001010", -- 000110 | 06	| JNZ m2
	"000" & "000110", -- 000111 | 07	| LOAD res
	"010" & "000111", -- 001000 | 08	| ADD 1
	"001" & "000110", -- 001001 | 09	| STORE res
	"000" & "000011", -- 001010 | 10 	| LOAD a[2] : m2
	"101" & "001111", -- 001011 | 11	| JNZ m3
	"000" & "000110", -- 001100 | 12	| LOAD res
	"010" & "000111", -- 001101 | 13	| ADD 1
	"001" & "000110", -- 001110 | 14	| STORE res
	"000" & "000100", -- 001111 | 15 	| LOAD a[3] : m3
	"101" & "010100", -- 010000 | 16	| JNZ m4
	"000" & "000110", -- 010001 | 17	| LOAD res
	"010" & "000111", -- 010010 | 18	| ADD 1
	"001" & "000110", -- 010011 | 19	| STORE res
	"000" & "000101", -- 010100 | 20 	| LOAD a[4] : m4
	"101" & "011001", -- 010101 | 21	| JNZ m5
	"000" & "000110", -- 010110 | 22	| LOAD res
	"010" & "000111", -- 010111 | 23	| ADD 1
	"001" & "000110", -- 011000 | 24	| STORE res
	"100" & "000000", -- 011001 | 25 	| HALT : m5
	others => "100" & "000000"
	);
	signal data: inst;
begin
	data <= ROM(conv_integer(adr));
	zbufs: process (RE, data)
	begin
		if (RE = '1') then
			DOUT <= data;
		else
			DOUT <= (others => 'Z');
		end if;
	end process;
end Beh_Zer;