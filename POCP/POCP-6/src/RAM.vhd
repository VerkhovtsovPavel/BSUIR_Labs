library IEEE;
use IEEE.STD_LOGIC_1164.all; 
use IEEE.STD_LOGIC_ARITH.all;
use IEEE.STD_LOGIC_UNSIGNED.all;

entity RAM is
	generic(
		-- ���� ������
		m: integer := 2;
		-- ���� ������
		n: integer := 2
		);
	port (
		-- �������������
		CLK: in std_logic;
		-- ������ ���������� �������/�������
		WR: in std_logic;
		-- ���� ������
		AB: in std_logic_vector (m-1 downto 0);
		-- 	��������������� ���� ������
		DB: inout std_logic_vector (n-1 downto 0)
		);
end RAM;

architecture Beh of RAM is
	-- ��� ��������� �����
	subtype word is std_logic_vector (n-1 downto 0);
	-- ��������������� ��� ��������� ������
	type tram is array (0 to 2**m - 1) of word;
	signal sRAM: tram;
	signal addrreg: integer range 0 to 2**m - 1;
Begin
	addrreg <= CONV_INTEGER(AB);
	
	WRP: process (WR, CLK, addrreg, DB)
	begin
		if WR = '0' then
			if rising_edge(CLK) then
				sRAM(addrreg) <= DB;
			end if;
		end if;
	end process;
	
	RDP: process(WR, sRAM, addrreg)
	begin
		if WR = '1' then
			DB <= sRAM (addrreg);
		else
			DB <= (others => 'Z');
		end if;
	end process;
end Beh;