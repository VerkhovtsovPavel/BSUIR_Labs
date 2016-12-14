library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DTAR is port(
		D : in std_logic;
		C : in std_logic;
		CLR : in std_logic;
		Q : out std_logic
	);
end DTAR;

architecture behavior of DTAR is	
signal S  : std_logic;
begin
	
 	Main : process (CLR, D, C, S)
	begin	 
		if CLR = '1' then
			S <= '0';
		elsif rising_edge(C) then
			S <= D;
		end if;	
	end process;

	Q <= S;
end behavior;
