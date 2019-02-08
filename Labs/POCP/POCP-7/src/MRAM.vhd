library IEEE;
use IEEE.STD_LOGIC_1164.all; 
use IEEE.STD_LOGIC_UNSIGNED.all;

entity MRAM is
	port (
		RW: in std_logic;
		ADR: in std_logic_vector(5 downto 0);
		DIN: in std_logic_vector (7 downto 0);
		DOUT: out std_logic_vector (7 downto 0)
		);
end MRAM;

architecture Beh_Max of MRAM is
	subtype byte is std_logic_vector(7 downto 0);
	type tRAM is array (0 to 63) of byte;
	signal RAM : tRAM :=(
		"00000101",	-- 5 | 000000 | array length
		"00000011", -- 3 | 000001 | a[0]
		"00000001", -- 1 | 000010 | a[1]
		"00000010", -- 2 | 000011 | a[2]
		"00000100", -- 4 | 000100 | a[3]
		"00000101", -- 5 | 000101 | a[4]
		"00000000", -- 0 | 000110 |	result
		"00000001", -- 1 | 000111 | 1 for add and sub
		others => "00000000"
	);
	signal data_in: byte;
	signal data_out: byte;
Begin
	data_in <= Din;
	WRITE: process (RW, ADR, data_in)
	begin
		if (RW = '0') then
			RAM(conv_integer(adr)) <= data_in;
		end if;
	end process; 
	
	data_out <= RAM (conv_integer(adr));
	
	ZBUFS: process (RW, data_out)
	begin
		if (RW = '1') then
			DOUT <= data_out;
		else
			DOUT <= (others => 'Z');
		end if;
	end process;
end Beh_Max;

architecture Beh_Zer of MRAM is
	subtype byte is std_logic_vector(7 downto 0);
	type tRAM is array (0 to 63) of byte;
	signal RAM : tRAM :=(
		"00000101",	-- 5 | 000000 | array length
		"00000000", -- 0 | 000001 | a[0]
		"00000001", -- 1 | 000010 | a[1]
		"00000000", -- 0 | 000011 | a[2]
		"00000100", -- 4 | 000100 | a[3]
		"00000000", -- 5 | 000101 | a[4]
		"00000000", -- 0 | 000110 |	result
		"00000001", -- 1 | 000111 | 1 for add and sub
		others => "00000000"
	);
	signal data_in: byte;
	signal data_out: byte;
Begin
	data_in <= Din;
	WRITE: process (RW, ADR, data_in)
	begin
		if (RW = '0') then
			RAM(conv_integer(adr)) <= data_in;
		end if;
	end process; 
	
	data_out <= RAM (conv_integer(adr));
	
	ZBUFS: process (RW, data_out)
	begin
		if (RW = '1') then
			DOUT <= data_out;
		else
			DOUT <= (others => 'Z');
		end if;
	end process;
end Beh_Zer;