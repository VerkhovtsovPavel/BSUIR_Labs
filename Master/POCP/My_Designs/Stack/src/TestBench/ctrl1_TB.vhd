library stack;
use stack.OneHotStack.all;
library ieee;
use ieee.STD_LOGIC_UNSIGNED.all;
use ieee.std_logic_1164.all;

	-- Add your library and packages declaration here ...

entity ctrl1_tb is
end ctrl1_tb;

architecture TB_ARCHITECTURE of ctrl1_tb is	  

	component MROM is
        port (
            RE: in std_logic;
            ADDR: in mem_addr;
            DOUT: out command
        );
    end component;

    component MRAM is
        port (
            CLK: in std_logic;
            RW: in std_logic;
            ADDR: in mem_addr;
            DIN: in operand;
            DOUT: out operand
        );
    end component;

    component DPATH is
        port(
            EN: in std_logic;
            CLK: in std_logic;
            OT: in operation;
            OP: in operand;
            RES: out operand;
            ZF: out std_logic;
            Stop: out std_logic
        );
    end component;

	-- Component declaration of the tested unit
	component ctrl1
	port(
		CLK : in STD_LOGIC;
		RST : in STD_LOGIC;
		Start : in STD_LOGIC;
		Stop : out STD_LOGIC;
		ROM_re : out STD_LOGIC;
		ROM_addr : out mem_addr;
		ROM_dout : in command;
		RAM_rw : out STD_LOGIC;
		RAM_addr : out mem_addr;
		RAM_din : out operand;
		RAM_dout : in operand;
		DP_op : out operand;
		DP_ot : out operation;
		DP_en : out STD_LOGIC;
		DP_res : in operand;
		DP_zf : in STD_LOGIC;
		DP_stop : in STD_LOGIC );
	end component;

	-- Stimulus signals - signals mapped to the input and inout ports of tested entity
	signal CLK : STD_LOGIC;
	signal RST : STD_LOGIC;
	signal Start : STD_LOGIC;
	signal ROM_dout : command;
	signal RAM_dout : operand;
	signal DP_res : operand;
	signal DP_zf : STD_LOGIC;
	signal DP_stop : STD_LOGIC;
	-- Observed signals - signals mapped to the output ports of tested entity
	signal Stop : STD_LOGIC;
	signal ROM_re : STD_LOGIC;
	signal ROM_addr : mem_addr;
	signal RAM_rw : STD_LOGIC;
	signal RAM_addr : mem_addr;
	signal RAM_din : operand;
	signal DP_op : operand;
	signal DP_ot : operation;
	signal DP_en : STD_LOGIC; 
	
	constant CLK_period: time := 10 ns;

begin

	-- Unit Under Test port map
	UUT : ctrl1
		port map (
			CLK => CLK,
			RST => RST,
			Start => Start,
			Stop => Stop,
			ROM_re => ROM_re,
			ROM_addr => ROM_addr,
			ROM_dout => ROM_dout,
			RAM_rw => RAM_rw,
			RAM_addr => RAM_addr,
			RAM_din => RAM_din,
			RAM_dout => RAM_dout,
			DP_op => DP_op,
			DP_ot => DP_ot,
			DP_en => DP_en,
			DP_res => DP_res,
			DP_zf => DP_zf,
			DP_stop => DP_stop
		); 
		
	UMRAM: MRAM
        port map(
            CLK => CLK,
            RW => ram_rw,
            ADDR => ram_addr,
            DIN => ram_din,
            DOUT => ram_dout
        );
    UMROM: entity MROM (Beh_Stack)
        port map (
            RE => rom_re,
            ADDR => rom_addr,
            DOUT => rom_dout
        );
    UDPATH: DPATH
        port map(
            EN => dp_en,
            CLK => CLK,
            OT => dp_ot,
            OP => dp_op,
            RES => dp_res,
            ZF => dp_zf,
            STOP => dp_stop
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
        rst <= '1';
        wait for 1 * CLK_PERIOD;
        rst <= '0';
        start <= '1';
        wait for 100 * CLK_PERIOD;
        wait;
    end process;

end TB_ARCHITECTURE;

configuration TESTBENCH_FOR_ctrl1 of ctrl1_tb is
	for TB_ARCHITECTURE
		for UUT : ctrl1
			use entity work.ctrl1(beh_stack);
		end for;
	end for;
end TESTBENCH_FOR_ctrl1;

