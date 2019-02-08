library gpr;
use gpr.OneHotGPR.all;
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
		RW : in STD_LOGIC;
		CLK : in STD_LOGIC;
		ADDR1 : in mem_addr;
		ADDR2 : in mem_addr;
		ADDRW : in mem_addr;
		D1OUT : out operand;
		D2OUT : out operand;
		DWIN : in operand );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal RW : STD_LOGIC;
	signal CLK : STD_LOGIC;
	signal ADDR1 : mem_addr;
	signal ADDR2 : mem_addr;
	signal ADDRW : mem_addr;
	signal DWIN : operand;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal D1OUT : operand;
	signal D2OUT : operand;

	-- Add your code here ...
	 constant CLK_period: time := 10 ns;
begin

	-- Unit Under Test port map
	UUT : mram
		port map (
			RW => RW,
			CLK => CLK,
			ADDR1 => ADDR1,
			ADDR2 => ADDR2,
			ADDRW => ADDRW,
			D1OUT => D1OUT,
			D2OUT => D2OUT,
			DWIN => DWIN
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

        addrw <= "00010";
        dwin <= "0000000000000100";

        wait for 1 * CLK_PERIOD;

        addr1 <= "00001";
        addr2 <= "00010";
        rw <= '1';

        wait for 1 * CLK_PERIOD;

        addrw <= "00000";
        dwin <= "0000000000010000";
        rw <= '0';

        wait for 1 * CLK_PERIOD;

        wait for 100 * CLK_PERIOD;
        wait;
	end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_mram of mram_tb is
	for TB_ARCHITECTURE
		for UUT : mram
			use entity work.mram(beh_gpr);
		end for;
	end for;
end TESTBENCH_FOR_mram;

