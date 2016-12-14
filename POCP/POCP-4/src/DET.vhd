library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DET is port(
		D : in std_logic;
		E : in std_logic;  
		C : in std_logic;
		Q : out std_logic
		);
end DET;

architecture behavior of DET is
	signal S  : std_logic;
begin
	Main : process (D, E, C)
	begin 
		if(rising_edge(C)) then
			if(E='1') then
				S <= D;
			end if;	
		end if;
	end process;
	Q <= S;
end behavior;	

