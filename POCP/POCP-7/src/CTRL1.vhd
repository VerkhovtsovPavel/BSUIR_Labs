library IEEE;
use IEEE.STD_LOGIC_1164.all; 
use IEEE.STD_LOGIC_UNSIGNED.all;

entity CTRL1 is
	port(
		CLK, RST, Start: in std_logic;
		Stop: out std_logic;
		
		-- ПЗУ
		ROM_re: out std_logic;
		ROM_adr: out std_logic_vector(5 downto 0);
		ROM_dout: in std_logic_vector(8 downto 0);
		
		-- ОЗУ
		RAM_rw: out std_logic;
		RAM_adr: out std_logic_vector(5 downto 0);
		RAM_din: out std_logic_vector(7 downto 0);
		RAM_dout: in std_logic_vector(7 downto 0);
		--datapath
		DP_op1: out std_logic_vector(7 downto 0);
		DP_ot: out std_logic_vector(2 downto 0);
		DP_en: out std_logic;
		DP_res: in std_logic_vector(7 downto 0);
		DP_zf: in std_logic;
		DP_sbf: in std_logic
		);
end CTRL1;

architecture Beh of CTRL1 is
	type states is (I, F, D, R, L, S, A, SB, H, JZ, JSB);
	--I - idle -
	--F - fetch
	--D - decode
	--R - read
	--L - load
	--S - store
	--A - add
	--SB - sub
	--H - halt
	--JZ - jump if not zero flag
	--JSB - jump if not sign bit set
	signal nxt_state, cur_state: states;
	--регистр выбранной инструкции
	signal RI: std_logic_vector(8 downto 0);
	--регистр счетчика инструкций
	signal IC: std_logic_vector(5 downto 0);
	--регистр типа операции
	signal RO: std_logic_vector(2 downto 0);
	--регистр адреса памяти
	signal RA: std_logic_vector(5 downto 0);
	--регистр данных
	signal RD: std_logic_vector(7 downto 0);
	
	constant LOAD: std_logic_vector(2 downto 0) := "000";
	constant STORE: std_logic_vector(2 downto 0) := "001";
	constant ADD: std_logic_vector(2 downto 0) := "010";
	constant SUB: std_logic_vector(2 downto 0) := "011";
	constant HALT: std_logic_vector(2 downto 0) := "100";
	constant JNZ: std_logic_vector(2 downto 0) := "101";
	constant JNSB: std_logic_vector(2 downto 0) := "110";
begin
	--синхронная память
	FSM: process(CLK, RST, nxt_state)
	begin
		if (RST = '1') then
			cur_state <= I;
		elsif rising_edge(CLK) then
			cur_state <= nxt_state;
		end if;
	end process;
	
	-- Комбинационная часть. Выработка след. состояния
	COMB: process(cur_state, start, RO)
	begin
		case cur_state is 
			when I => 
				if (start = '1') then 
					nxt_state <= F;
				else
					nxt_state <= I;
			end if;
			when F => nxt_state <= D;
			when D => 
				if (RO = HALT) then
					nxt_state <= H;
				elsif (RO = STORE) then
					nxt_state <= S;
				elsif (RO = JNZ) then
					nxt_state <= JZ;
				elsif (RO = JNSB) then
					nxt_state <= JSB;
				else
					nxt_state <= R;
			end if;
			when R => 
				if (RO = LOAD) then 
					nxt_state <= L;
				elsif (RO = ADD) then
					nxt_state <= A;
				elsif (RO = SUB) then
					nxt_state <= SB;
				else
					nxt_state <= I;
			end if;
			when L | S | A | SB | JZ | JSB => nxt_state <= F;
			when H => nxt_state <= H;
			when others => nxt_state <= I;
		end case;
	end process;
	
	--выработка сигнала stop
	PSTOP: process (cur_state)
	begin
		if (cur_state = H) then
			stop <= '1';
		else
			stop <= '0';
		end if;
	end process;
	
	-- счетчик инструкций
	PMC: process (CLK, RST, cur_state)
	begin
		if (RST = '1') then
			IC <= "000000";
		elsif falling_edge(CLK) then
			if (cur_state = D) then
				IC <= IC + 1;
			elsif (cur_state = JZ and DP_ZF = '0') then
				IC <= RA;
			elsif (cur_state = JSB and DP_SBF = '0') then
				IC <= RA;
			end if;
		end if;
	end process;
	
	ROM_adr <= IC;
	
	--сигнал чтения из памяти ROM
	PROMREAD: process (nxt_state, cur_state)
	begin
		if (nxt_state = F or cur_state = F) then
			ROM_re <= '1';
		else
			ROM_re <= '0';
		end if;
	end process;
	
	--чтение выбранного значения инструкций и запись его в RI
	PROMDAT: process (RST, cur_state, ROM_dout)
	begin
		if (RST = '1') then
			RI <= "000000000";
		elsif (cur_state = F) then
			RI <= ROM_dout;
		end if;
	end process;
	
	-- схема управления регистрами RO и RA
	PRORA: process (RST, nxt_state, RI)
	begin
		if (RST = '1') then
			RO <= "000";
			RA <= "000000";
		elsif (nxt_state = D) then
			RO <= RI (8 downto 6);
			RA <= RI (5 downto 0);
		end if;
	end process;
	
	PRAMST: process (RA)
	begin
		if (cur_state /= JZ and cur_state /= JSB) then
			RAM_adr <= RA;
		end if;
	end process;
	
	--управляющий сигнал чтения/записи в RAM
	PRAMREAD: process (cur_state)
	begin
		if (cur_state = S) then
			RAM_rw <= '0';
		else
			RAM_rw <= '1';
		end if;
	end process;
	
	--запись значения из памяти RAM в регистр RD
	PRAMDAR: process (cur_state)
	begin
		if (cur_state = R) then
			RD <= RAM_dout;
		end if;
	end process;
	
	--передача результирующего значения тракта обработки данных на входную шину памяти RAM
	RAM_din <= DP_res;
	--передача значения регистра RD на вход первого операнда
	DP_op1 <= RD;
	--передача значения регистра RO на входную шину типа операций
	DP_ot <= RO;
	
	paddsuben: process (cur_state)
	begin
		if (cur_state = A or cur_state = SB or cur_state = L) then
			DP_en <= '1';
		else
			DP_en <= '0';
		end if;
	end process;
end Beh;