library ieee;
use ieee.std_logic_1164.all;
entity SyncREGn is
	generic (n : integer := 4);
	port(
		Din : in std_logic_vector(n-1 downto 0);
		EN : in std_logic; 
		C : in std_logic;
		Dout : out std_logic_vector(n-1 downto 0)
		);
end SyncREGn;

architecture behavior of SyncREGn is
	signal reg : std_logic_vector(n-1 downto 0);
begin
	main : process(Din, EN , C)
	begin
		if rising_edge(C) then
			if EN='1' then
				reg <= Din;
			end if;	
		end if;
	end process;
	
	Dout <= reg;
end behavior;

architecture structual of SyncREGn is
	component DET
		port(D, E, C : in std_logic; Q: out std_logic);
	end component DET;
begin
	G_1 : for I in 0 to N-1 generate
		DET_I :
		DET port map
			(Din(I), EN, C, Dout(I));
	end generate;
end architecture structual;

