library IEEE;
use IEEE.STD_LOGIC_1164.all; 
use IEEE.STD_LOGIC_ARITH.all;
use IEEE.STD_LOGIC_UNSIGNED.all;

entity FIFO is
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
		-- 	��������������� ���� ������
		DB: inout std_logic_vector (n-1 downto 0);
		EMPTY: out std_logic;
		FULL: out std_logic
		);
end FIFO;

architecture Beh of FIFO is
	-- ��� ��������� �����
	subtype word is std_logic_vector (n-1 downto 0);
	-- ��������������� ��� ��������� ������
	type tram is array (0 to 2**m - 1) of word;
	signal sRAM: tram;
	signal head: integer := 0;
	constant Limit: integer := 2 ** m -1;
Begin
	SH: process (CLK)
	begin
		if rising_edge(CLK) then
			if (WR = '0') then
				if (head <= Limit) then
					head <= head + 1;
				end if;
			elsif (WR = '1') then
				if (head > 0) then
					head <= head - 1;
				end if;
			end if;
		end if;
		if (head = 0) then
			empty <= '1';
			full <= '0';
		elsif (head = Limit + 1) then
			empty <= '0';
			full <= '1';
		else
			empty <= '0';
			full <= '0';
		end if;
	end process;
	
	WRP: process (head)
	begin
		if WR = '0' then
			if (head > 0 and head <= Limit + 1) then 
				sRAM(head - 1) <= DB;
			end if;
		end if;
	end process;
	
	RDP: process(head)
	begin
		if WR = '1' then
			if (head >= 0 and head <= Limit) then
				DB <= sRAM (head);
			end if;
		else
			DB <= (others => 'Z');
		end if;
	end process;
end Beh;