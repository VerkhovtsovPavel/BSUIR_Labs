library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DETAR is port(
		D : in std_logic;
		C : in std_logic;
		E : in std_logic;
		CLR : in std_logic;
		Q : out std_logic
		);
end DETAR;

architecture behavior of DETAR is	
	signal S  : std_logic;
begin
	
	Main : process (CLR, E, D, C, S)
	begin	 
		if CLR = '1' then
			S <= '0';
		elsif rising_edge(C) then	 
			if(E = '1') then
				S <= D;			
			end if;
		end if;	
	end process;
	
	Q <= S;
end behavior;
