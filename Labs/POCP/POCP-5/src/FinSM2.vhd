library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

entity Task2 is 
	port (
		CLK: in STD_LOGIC;
		IP: in STD_LOGIC_VECTOR (3 downto 0);
		RST: in STD_LOGIC;
		OP: out STD_LOGIC_VECTOR (1 downto 0));
end Task2;

architecture Beh of Task2 is
	
	type state_type is (
	S0, S1, S2, S3, S4
	);
	
	signal state: state_type;
	
begin
	
	state_machine: process (CLK)
	begin
		if CLK'event and CLK = '1' then
			if RST='1' then	
				state <= S0;
			else
				case state is
					when S0 =>
						if IP="0011" then	
							state <= S1;
					end if;
					when S1 =>
						if IP="1111" then	
							state <= S2;
					end if;
					when S2 =>
						if IP="1100" then	
							state <= S3;
					end if;
					when S3 =>
						if IP="0000" then	
							state <= S4;
						end if;
					when others =>
						state <= S0;
				end case;
			end if;
		end if;
	end process;
	
	OP_assignment:
	OP <= "11" when (state = S0) else
	"01" when (state = S1) else
	"00" when (state = S2) else
	"00" when (state = S3) else
	"10" when (state = S4) else
	"11";
	
end Beh;
