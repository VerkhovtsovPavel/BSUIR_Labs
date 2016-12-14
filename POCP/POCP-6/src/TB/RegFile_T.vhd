library ieee;
use ieee.std_logic_1164.all;

entity RegFile_T is
end RegFile_T;

architecture Beh of RegFile_T is
	component RegFile 
		generic (
			-- ������������� �������� ���� ��������� ���� ������
			INITREG: std_logic_vector := "0000";
			-- ����������� ���� ������
			a: integer := 2);
		port (
			-- ������ ������������� ���������
			INIT: in std_logic;				 
			-- ���� ������ ��� ������
			WDP: in std_logic_vector(INITREG'range);
			-- ���� ������ ��� ������
			WA: in std_logic_vector(a-1 downto 0);
			-- ���� ������ ��� ������
			RA: in std_logic_vector(a-1 downto 0);
			-- ������ ���������� ������
			WE: in std_logic;
			-- ����������� ������
			RDP: out std_logic_vector(INITREG'range)); 
	end component;
	signal init: std_logic := '0';
	signal wdp: std_logic_vector(3 downto 0):= "0000";
	signal wa: std_logic_vector(1 downto 0) := "00";
	signal ra: std_logic_vector(1 downto 0) := "00";
	signal we: std_logic := '0';
	signal rdp: std_logic_vector(3 downto 0) := "0000";
	constant WAIT_Period: time := 10 ns;
begin
	ufile: RegFile port map (
		init => init,
		wdp => wdp,
		wa => wa,
		ra => ra,
		we => we,
		rdp => rdp
		);
		
	main: process
	begin
		wait for wait_period;
		init <= '1';
		wait for wait_period / 2;
		init <= '0';
		wdp <= "1100";
		wa <= "00";
		we <= '1';
		wait for wait_period / 2;
		we <= '0';
		wdp <= "1010";
		wa <= "01";
		wait for wait_period / 2;
		we <= '1';
		wait for wait_period / 2;
		we <= '0';
		wait for wait_period / 2;
		ra <= "00";
		wait for wait_period;
		ra <= "01";
		wait;
	end process;
end Beh;	

configuration config of RegFile_T is
	for Beh
		for ufile : RegFile
			use entity work.regfile(Beh);
		end for;
	end for;
end config;