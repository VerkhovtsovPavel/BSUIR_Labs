library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_ARITH.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library gpr;
use gpr.OneHotGPR.all;

entity DPATH is
    port(
        EN: in std_logic;
        -- operation type
        OT: in operation;
        -- operand 1
        OP1: in operand;
        -- operand 2
        OP2: in operand;
        -- result
        RES: out operand;
        -- zero flag
        ZF: out std_logic
    );
end DPATH;

architecture Beh_GPR of DPATH is
    signal res_g: operand;
    signal res_add: operand;
    signal res_sub: operand;	
	signal res_shift: operand;
    signal res_copy: operand;
    signal t_zf: std_logic;
Begin
    res_add 	<= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(OP1) + CONV_INTEGER(OP2), 16);
    res_sub 	<= CONV_STD_LOGIC_VECTOR(CONV_INTEGER(OP1) - CONV_INTEGER(OP2), 16);
    res_copy 	<= OP1;

    REGA: process (EN, OT, OP1, res_add, res_sub, res_shift, res_copy)
    begin
        if rising_edge(EN) then
            case OT is
                when ADD => res_g <= res_add;
                when SUBT => res_g <= res_sub;
				when SHIFT => res_g <= res_shift;
                when COPY => res_g <= res_copy;
                when others => null;
            end case;
        end if;
    end process;

    FLAGS: process(res_g)
    begin
        if res_g = (res_g'range => '0') then
            t_zf <= '1';
        else
            t_zf <= '0';
        end if;
    end process; 
	
	GRAY: process(OP1)
	begin	
		for i in 0 to 14 loop
			res_shift(i) <= OP1(i) xor OP1(i+1);
		end loop; 
		res_shift(15) <= OP1(15);	
	end process; 

    RES <= res_g;
    ZF <= t_zf;
End Beh_GPR;