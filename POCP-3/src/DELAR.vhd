library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DELAR is port(
		D : in std_logic;
		E : in std_logic;
		CLR : in std_logic;
		Q : out std_logic
	);
end DELAR;

architecture behavior of DELAR is	
signal S  : std_logic;
begin
	
 	Main : process (D, E, CLR)
	begin
		if(CLR = '1') then
			S <= '0';
		elsif(E='1') then
			S <= D;
		end if;	
	end process;

	Q <= S;
end behavior;
