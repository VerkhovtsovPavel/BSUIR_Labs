library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_ARITH.all;
use IEEE.STD_LOGIC_UNSIGNED.all;

entity DPATH is
	port(
		EN: in std_logic;
		--operation type
		OT: in std_logic_vector(2 downto 0);
		--operand
		OP1: in std_logic_vector(7 downto 0);
		RES: out std_logic_vector(7 downto 0);
		--zero flag
		ZF: out std_logic;
		-- significant bit set flag
		SBF: out std_logic
		);
end DPATH;

architecture Beh of DPATH is
	signal ACCUM: std_logic_vector(7 downto 0);
	signal res_add: std_logic_vector(7 downto 0);
	signal res_sub: std_logic_vector (7 downto 0);
	signal t_zf, t_sbf: std_logic;
	
	constant LOAD: std_logic_vector(2 downto 0) := "000";
	constant ADD: std_logic_vector(2 downto 0) := "010";
	constant SUB: std_logic_vector(2 downto 0) := "011";
Begin
	res_add <= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(ACCUM) + CONV_INTEGER(OP1), 8);
	res_sub <= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(ACCUM) - CONV_INTEGER(OP1), 8);
	
	REGA: process (EN, OT, OP1, res_add, res_sub)
	begin
		if rising_edge(EN) then
			case OT is
				when LOAD => ACCUM <= OP1;
				when ADD => ACCUM <= res_add;
				when SUB => ACCUM <= res_sub;
				when others => null;
			end case;
		end if;
	end process;
	
	FLAGS: process(ACCUM)
	begin
		if ACCUM = (ACCUM'range => '0') then
			t_zf <= '1';
		else
			t_zf <= '0';
		end if;
		if ACCUM(7) = '1' then
			t_sbf <= '1';
		else
			t_sbf <= '0';
		end if;
	end process;
	
	RES <= ACCUM;
	ZF 	<= t_zf;
	SBF <= t_sbf;
End Beh;