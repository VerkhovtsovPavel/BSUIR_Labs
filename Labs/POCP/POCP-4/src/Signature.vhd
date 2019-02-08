library ieee;
use ieee.std_logic_1164.all;

entity Signature is
	generic (n:integer := 4);
	port(
		CLK: in std_logic;
		RST: in std_logic;
		Pin: in std_logic;
		Pout: out std_logic_vector(0 to 2**n-1)
		);
end Signature;

architecture behavior of Signature is
	signal sreg: std_logic_vector(0 to 2**n-1);
	signal sdat: std_logic_vector(0 to 2**n-1);
	signal buf: std_logic;
Begin
	Main: process (CLK, RST, sdat)
	begin
		if RST = '1' then
			sreg <= (others => '0');
		elsif rising_edge(CLK) then
			sreg <= sdat;
		end if;
	end process;
	
	Data: process (Pin, sreg)
	begin
		buf <= sreg(0) xor sreg(2**n-1);
		sdat <= (sreg(2**n-1) xor PIn) & sreg(0 to 2**n-2);
		sdat(2) <= buf;
	end process;
	
	Pout <= sreg;
End behavior;