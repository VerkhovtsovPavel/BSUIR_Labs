library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity TT is port(
		T : in  std_logic;
		C : in  std_logic;
		Q : out std_logic
	);
end TT;

architecture behavior of TT is	
signal S : std_logic := '0';
begin
	
 	Main : process (T, C, S)
	begin
		if rising_edge(C) then
			S <= T xor S;
		end if;	
	end process;

	Q <= S;
end behavior;
