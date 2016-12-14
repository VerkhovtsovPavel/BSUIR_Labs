library ieee;
use ieee.std_logic_1164.all;
entity SREGn is
	generic (n : integer := 4);
	port(
		Sin : in std_logic;
		SE : in std_logic; 
		CLK : in std_logic;
		RST : in std_logic;
		Pout : inout std_logic_vector(n-1 downto 0)
		);
end SREGn;

architecture behavior of SREGn is
signal reg : std_logic_vector(n-1 downto 0);
signal si : std_logic;
begin
	main : process(Sin, SE , CLK, RST)
	begin
		if rising_edge(CLK) then
			if SE='1' then
				reg(n-1 downto 1) <= reg(n-2 downto 0);
				reg(0) <= Sin;
			end if;	
		end if;
	end process;
	
	Pout <= reg;
end behavior;

architecture structual of SREGn is
	component DETAR
		port(D, E, C, CLR : in std_logic; Q: out std_logic);
	end component DETAR;
begin	 
	FST: DETAR port map (Sin, SE, CLK, RST, Pout(0));
	G_1 : for I in 1 to N-1 generate
		DETAR_I :
		DETAR port map
			(Pout(I-1), SE, CLK, RST, Pout(I));
	end generate;
end architecture structual;

