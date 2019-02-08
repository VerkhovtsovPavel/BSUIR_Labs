library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all; 
library accum;
use accum.OneHotAccum.all;

entity OneHot is
    port (
        CLK, RST, Start: in std_logic;
        Stop: out std_logic
    );
end OneHot;

architecture Beh of OneHot is
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
            -- operation type
            OT: in operation;
            -- operand
            OP1 : in operand;
            RES: out operand;
            -- zero flag
            ZF: out std_logic
        );
    end component;

    component CTRL1 is
        port(
            CLK, RST, Start: in std_logic;
            Stop: out std_logic;

            -- ROM
            ROM_re: out std_logic;
            ROM_addr: out mem_addr;
            ROM_dout: in command;

            -- RAM
            RAM_rw: out std_logic;
            RAM_addr: out mem_addr;
            RAM_din: out operand;
            RAM_dout: in operand;

            -- datapath
            DP_op1: out operand;
            DP_ot: out operation;
            DP_en: out std_logic;
            DP_res: in operand;
            DP_zf: in std_logic
        );
    end component;

    signal rom_re: std_logic;
    signal rom_addr: mem_addr;
    signal rom_dout: command;
    signal ram_rw: std_logic;
    signal ram_addr: mem_addr;
    signal ram_din: operand;
    signal ram_dout: operand;
    signal dp_op1: operand;
    signal dp_ot: operation;
    signal dp_en: std_logic;
    signal dp_res: operand;
    signal dp_zf: std_logic;
begin
    UMRAM: MRAM 
        port map(
            CLK => CLK,
            RW => ram_rw,
            ADDR => ram_addr,
            DIN => ram_din,
            DOUT => ram_dout
        );
    UMROM: MROM 
        port map (
            RE => rom_re,
            ADDR => rom_addr,
            DOUT => rom_dout
        );
    UDPATH: DPATH 
        port map(
            EN => dp_en,
            OT => dp_ot,
            OP1 => dp_op1,
            RES => dp_res,
            ZF => dp_zf
        );
    UCTRL1: CTRL1 
        port map(
            CLK => CLK,
            RST => RST,
            START => Start,
            STOP => STOP,
            ROM_RE => rom_re,
            ROM_ADDR => rom_addr,
            ROM_DOUT => rom_dout,
            RAM_RW => ram_rw,
            RAM_ADDR => ram_addr,
            RAM_DIN => ram_din,
            RAM_DOUT => ram_dout,
            DP_EN => dp_en,
            DP_OT => dp_ot,
            DP_OP1 => dp_op1,
            DP_RES => dp_res,
            DP_ZF => dp_zf
        );
end Beh;
