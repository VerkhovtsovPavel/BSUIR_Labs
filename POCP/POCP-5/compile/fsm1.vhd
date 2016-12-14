-------------------------------------------------------------------------------
--
-- Title       : No Title
-- Design      : POCP-5
-- Author      : PavelVerk
-- Company     : Home
--
-------------------------------------------------------------------------------
--
-- File        : c:\My_Designs\POCP\POCP-5\compile\fsm1.vhd
-- Generated   : 11/17/16 19:05:07
-- From        : c:\My_Designs\POCP\POCP-5\src\fsm1.asf
-- By          : FSM2VHDL ver. 5.0.7.2
--
-------------------------------------------------------------------------------
--
-- Description : 
--
-------------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;

entity Task1 is 
	port (
		CLK: in STD_LOGIC;
		IP: in STD_LOGIC_VECTOR (3 downto 0);
		RST: in STD_LOGIC;
		OP: out STD_LOGIC_VECTOR (1 downto 0));
end Task1;

architecture Beh of Task1 is

-- SYMBOLIC ENCODED state machine: state
type state_type is (
    S0, S1, S2, S3, S4
);
-- attribute enum_encoding of state_type: type is ... -- enum_encoding attribute is not supported for symbolic encoding

signal state: state_type;

begin


----------------------------------------------------------------------
-- Machine: state
----------------------------------------------------------------------
state_machine: process (CLK)
begin
	if CLK'event and CLK = '1' then
		if RST='1' then	
			state <= S0;
			-- Set default values for outputs, signals and variables
			-- ...
		else
			-- Set default values for outputs, signals and variables
			-- ...
			case state is
				when S0 =>
					state <= S1;
				when S1 =>
					if IP="1101" then	
						state <= S2;
					end if;
				when S2 =>
					if IP="1111" then	
						state <= S3;
					elsif IP="0001" then	
						state <= S4;
					end if;
				when S4 =>
					if IP="1001" then	
						state <= S2;
					elsif IP="1011" then	
						state <= S0;
					end if;
--vhdl_cover_off
				when others =>
					null;
--vhdl_cover_on
			end case;
		end if;
	end if;
end process;

-- signal assignment statements for combinatorial outputs
OP_assignment:
OP <= "00" when (state = S0) else
      "01" when (state = S1) else
      "01" when (state = S2) else
      "11" when (state = S3) else
      "01" when (state = S4) else
      "00";

end Beh;
