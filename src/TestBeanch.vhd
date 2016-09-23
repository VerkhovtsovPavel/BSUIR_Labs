entity ODD_PARITY_TB is
end;
library ieee;
use	ieee.std_logic_1164.all;
architecture
	OP_TB_ARCH of ODD_PARITY_TB 
	is
	component 
		Parity_Generator1 
		port 
			(input_stream : in	input;
			clk : in  std_logic;
			parity : out  bit );
	end component;
	
	signal 	input_stream : input;
	signal 	clk :std_logic;
	signal 	parity :bit ;
begin 
	U1: Parity_Generator1 port map (input_stream,  clk,	 parity => parity );
	input1 :   process 	(clk)
	begin
		if clk <= 'U' 	then clk <= '0' after 1 ns;
		else clk <= not clk after 1 ns;
		end	if;
	end	process;
	
	input2: process (input_stream)
	begin
		input_stream <= "10100110" after 1 ns,
		"01111100" after 2 ns;
	end process;
end OP_TB_ARCH;
configuration cfg_op 	of 	 ODD_PARITY_TB 	is
	for  OP_TB_ARCH
	end	for;
end cfg_op;