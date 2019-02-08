library ieee;
use ieee.NUMERIC_STD.all;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity lifo_tb is
	-- Generic declarations of the tested unit
		generic(
		m : INTEGER := 5;
		n : INTEGER := 16 );
end lifo_tb;

architecture TB_ARCHITECTURE of lifo_tb is
	-- Component declaration of the tested unit
	component lifo
		generic(
		m : INTEGER := 5;
		n : INTEGER := 16 );
	port(
		EN : in STD_LOGIC;
		CLK : in STD_LOGIC;
		WR : in STD_LOGIC;
		RB : out STD_LOGIC_VECTOR(n-1 downto 0);
		WB : in STD_LOGIC_VECTOR(n-1 downto 0) );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal EN : STD_LOGIC;
	signal CLK : STD_LOGIC;
	signal WR : STD_LOGIC;
	signal WB : STD_LOGIC_VECTOR(n-1 downto 0);
	-- Observed signals - signals mapped to the output ports of tested entity
	signal RB : STD_LOGIC_VECTOR(n-1 downto 0);

	constant CLK_Period: time := 10 ns;

begin

	-- Unit Under Test port map
	UUT : lifo
		generic map (
			m => m,
			n => n
		)

		port map (
			EN => EN,
			CLK => CLK,
			WR => WR,
			RB => RB,
			WB => WB
		);

	CLK_Process: process
    begin
        CLK <= '0';
        wait for CLK_Period/2;
        CLK <= '1';
        wait for CLK_Period/2;
    end process;

    MAIN: process
    begin
        wait for clk_period;
        en <= '1';
        WR <= '0';
        wb <= "0000000000000001";
        wait for clk_period;
        wb <= "0000000000000010";
        wait for clk_period;
        wb <= "0000000000000011";
        wait for clk_period;
        WR <= '1';
        wait for clk_period;
        wait for clk_period;
        wait for clk_period;
        en <= '0';

        wait for clk_period;
        en <= '1';
        WR <= '0';
        wb <= "0000000000001001";
        wait for clk_period;
        wb <= "0000000000001010";
        wait for clk_period;
        wb <= "0000000000001011";
        wait for clk_period;
        wb <= "0000000000001100";
        wait for clk_period;
        wb <= "0000000000001101";
        wait for clk_period;
        en <= '0';
        wait for clk_period;
        WR <= '1';
        en <= '1';
        wait for clk_period;
        wait for clk_period;
        wait for clk_period;
        en <= '0';

        wait;
end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_lifo of lifo_tb is
	for TB_ARCHITECTURE
		for UUT : lifo
			use entity work.lifo(beh);
		end for;
	end for;
end TESTBENCH_FOR_lifo;

