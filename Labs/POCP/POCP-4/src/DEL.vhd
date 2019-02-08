library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DEL is port(
		D : in std_logic;
		E : in std_logic;
		Q : out std_logic
		);
end DEL;

architecture behavior of DEL is
	signal S  : std_logic;
begin
	Main : process (D, E)
	begin
		if(E='1') then
			S <= D;
		end if;	
	end process;
	
	Q <= S;
end behavior;	

