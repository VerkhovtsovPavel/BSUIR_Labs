library ieee;
use ieee.std_logic_1164.all;
entity REGn is
	generic (n : integer := 4);
	port(
		Din : in std_logic_vector(n-1 downto 0);
		EN : in std_logic; 
		Dout : out std_logic_vector(n-1 downto 0)
		);
end REGn;

architecture behavior of REGn is
	signal reg : std_logic_vector(n-1 downto 0);
begin
	main : process(Din, EN)
	begin
		if EN='1' then
			reg <= Din;
		end if;
	end process;
	
	Dout <= reg;
end behavior;

architecture structual of REGn is
	component DEL
		port(D, E : in std_logic; Q: out std_logic);
	end component DEL;
begin
	G_1 : for I in 0 to N-1 generate
		DEL_I :
		DEL port map
			(Din(I), EN, Dout(I));
	end generate;
end architecture structual;

