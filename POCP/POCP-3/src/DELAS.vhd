library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DELAS is port(
		D : in std_logic;
		E : in std_logic;
		PRE : in std_logic;
		Q : out std_logic
	);
end DELAS;

architecture behavior of DELAS is	
signal S  : std_logic;
begin
	
 	Main : process (D, E, PRE)
	begin
		if(PRE = '1') then
			S <= '1';
		elsif(E='1') then
			S <= D;
		end if;	
	end process;

	Q <= S;
end behavior;
