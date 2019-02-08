library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
library gpr;
use gpr.OneHotGPR.all;

entity OneHot is
    port (
        CLK, RST, Start: in std_logic;
        Stop: out std_logic
    );
end OneHot;

architecture Beh_GPR of OneHot is
    component MROM is
        port (
            RE: in std_logic;
            ADDR: in mem_addr;
            DOUT: out command
            );
    end component;

    component MRAM is
        port (
            RW: in std_logic;
            CLK: in std_logic;
            ADDR1: in mem_addr;
            ADDR2: in mem_addr;
            ADDRW: in mem_addr;
            DWIN: in operand;
            D1OUT: out operand;
            D2OUT: out operand
        );
    end component;

    component DPATH is
        port(
            EN: in std_logic;
            -- operation type
            OT: in operation;
            -- operand 1
            OP1 : in operand;
            -- operand 2
            OP2 : in operand;
            -- result
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
            RAM_addr1: out mem_addr;
            RAM_addr2: out mem_addr;
            RAM_addrw: out mem_addr;
            RAM_dwin: out operand;
            RAM_d1out: in operand;
            RAM_d2out: in operand;

            -- datapath
            DP_op1: out operand;
            DP_op2: out operand;
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
    signal ram_addr1: mem_addr;
    signal ram_addr2: mem_addr;
    signal ram_addrw: mem_addr;
    signal ram_dwin: operand;
    signal ram_d1out: operand;
    signal ram_d2out: operand;
    signal dp_op1: operand;
    signal dp_op2: operand;
    signal dp_ot: operation;
    signal dp_en: std_logic;
    signal dp_res: operand;
    signal dp_zf: std_logic;
begin
    UMRAM: MRAM 
        port map(
            RW => ram_rw,
            CLK => CLK,
            ADDR1 => ram_addr1,
            ADDR2 => ram_addr2,
            ADDRW => ram_addrw,
            DWIN => ram_dWin,
            D1OUT => ram_d1out,
            D2OUT => ram_d2out
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
            OP2 => dp_op2,
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
            RAM_ADDR1 => ram_addr1,
            RAM_ADDR2 => ram_addr2,
            RAM_ADDRW => ram_addrw,
            RAM_DWIN => ram_dwin,
            RAM_D1OUT => ram_d1out,
            RAM_D2OUT => ram_d2out,
            DP_EN => dp_en,
            DP_OT => dp_ot,
            DP_OP1 => dp_op1,
            DP_OP2 => dp_op2,
            DP_RES => dp_res,
            DP_ZF => dp_zf
        );
end Beh_GPR;
