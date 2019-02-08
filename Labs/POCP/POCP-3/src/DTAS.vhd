library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DTAS is port(
		D : in std_logic;
		C : in std_logic;
		PRE : in std_logic;
		Q : out std_logic
	);
end DTAS;

architecture behavior of DTAS is	
signal S  : std_logic;
begin
	
 	Main : process (PRE, D, C, S)
	begin	 
		if PRE = '1' then
			S <= '1';
		elsif rising_edge(C) then
			S <= D;
		end if;	
	end process;

	Q <= S;
end behavior;
