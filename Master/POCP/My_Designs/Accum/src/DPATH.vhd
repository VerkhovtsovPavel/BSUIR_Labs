library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_ARITH.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library accum;
use accum.OneHotAccum.all;

entity DPATH is
    port(
        EN: in std_logic;
        -- operation type
        OT: in operation;
        -- operand
        OP1: in operand;
        RES: out operand;
        -- zero flag
        ZF: out std_logic
        );
end DPATH;

architecture Beh of DPATH is
    signal ACCUM: operand;
    signal res_add: operand;
    signal res_sub: operand;
	signal res_shift: operand;
    signal t_zf: std_logic;
Begin
    res_add 	<= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(ACCUM) + CONV_INTEGER(OP1), 16);
    res_sub 	<= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(ACCUM) - CONV_INTEGER(OP1), 16); 
		
    REGA: process (EN, OT, OP1, res_add, res_sub, res_shift)
    begin
        if rising_edge(EN) then
            case OT is
				when LOAD => ACCUM <= OP1;
                when ADD => ACCUM <= res_add;
                when SUBT => ACCUM <= res_sub;
				when SHIFT => ACCUM <= res_shift;
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
    end process;  
	
	GRAY: process(ACCUM)
	begin	
		for i in 0 to 14 loop
			res_shift(i) <= ACCUM(i) xor ACCUM(i+1);
		end loop; 
		res_shift(15) <= ACCUM(15);	
	end process; 

    RES <= ACCUM;
    ZF <= t_zf;
End Beh;