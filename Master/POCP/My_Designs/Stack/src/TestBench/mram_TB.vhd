library stack;
use stack.OneHotStack.all;
library ieee;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity mram_tb is
end mram_tb;

architecture TB_ARCHITECTURE of mram_tb is
	-- Component declaration of the tested unit
	component mram
	port(
		CLK : in STD_LOGIC;
		RW : in STD_LOGIC;
		ADDR : in mem_addr;
		DIN : in operand;
		DOUT : out operand );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal CLK : STD_LOGIC;
	signal RW : STD_LOGIC;
	signal ADDR : mem_addr;
	signal DIN : operand;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal DOUT : operand;

-- Add your code here ...  
	constant CLK_period: time := 10 ns;

begin

	-- Unit Under Test port map
	UUT : mram
		port map (
			CLK => CLK,
			RW => RW,
			ADDR => ADDR,
			DIN => DIN,
			DOUT => DOUT
		);

	CLK_Process: process
    begin
        CLK <= '0';
        wait for CLK_Period/2;
        CLK <= '1';
        wait for CLK_Period/2;
    end process;

    main: process
    begin
        wait for 1 * CLK_PERIOD;

        addr <= "00010";
        din <= "0000000000000100";

        wait for 1 * CLK_PERIOD;

        addr <= "00001";
        rw <= '1';

        wait for 1 * CLK_PERIOD;

        addr <= "00000";
        din <= "0000000000010000";
        rw <= '0';

        wait for 1 * CLK_PERIOD;

        wait for 100 * CLK_PERIOD;
        wait;
	end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_mram of mram_tb is
	for TB_ARCHITECTURE
		for UUT : mram
			use entity work.mram(beh);
		end for;
	end for;
end TESTBENCH_FOR_mram;

