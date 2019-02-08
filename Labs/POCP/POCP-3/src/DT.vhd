library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DT is port(
		D : in std_logic;
		C : in std_logic;
		Q : out std_logic
	);
end DT;

architecture behavior of DT is	
signal S  : std_logic;
begin
	
 	Main : process (D, C, S)
	begin
		if rising_edge(C) then
			S <= D;
		end if;	
	end process;

	Q <= S;
end behavior;
