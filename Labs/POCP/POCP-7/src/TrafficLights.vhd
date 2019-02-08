library IEEE;
use IEEE.STD_LOGIC_1164.all; 
use IEEE.STD_LOGIC_UNSIGNED.all;

entity TrafficLights is
	port(
		CLK: in std_logic;
		CWAIT: in std_logic;
		RST: in std_logic;
		START: in std_logic;
		R, Y, G: out std_logic
	);
end TrafficLights;

architecture Beh of TrafficLights is
	subtype tword is std_logic_vector(6 downto 0);
	type tprom is array (0 to 15) of tword;
	constant CROM: tprom := (
		"0000" & "000",
		"0010" & "010",
		"0011" & "100",
		"0100" & "100",
		"0101" & "100",
		"0110" & "110",
		"0111" & "001",
		"1000" & "001",
		"1001" & "001",
		"1010" & "000",
		"1011" & "001",
		"0001" & "000",
		others => "0000" & "000"
	);
	signal n_adr, c_adr: std_logic_vector(3 downto 0);
	signal data: std_logic_vector(2 downto 0);
	signal rom_data: std_logic_vector(6 downto 0);
Begin
	NEXTSTATE: process (cwait, start, rom_data)
	begin
		if (start = '1' and c_adr = "0000") then
			n_adr <= "0001";
		elsif (cwait = '1' and c_adr = "0001") then
			n_adr <= "0000";
		else
			n_adr <= rom_data(6 downto 3);
		end if;
	end process;
	
	data <= rom_data(2 downto 0);
	rom_data <= CROM(conv_integer(c_adr));
	
	PDFF: process (rst, clk, rom_data)
	begin
		if rst = '1' then
			c_adr <= "0000";
		else
			if rising_edge(CLK) then
				c_adr <= n_adr;
			end if;
		end if;
	end process;
	
	r <= data(2);
	y <= data(1);
	g <= data(0);
	
End Beh;