-------------------------------------------------------------------------------
--
-- Title       : No Title
-- Design      : POCP-2
-- Author      : ws
-- Company     : 12
--
-------------------------------------------------------------------------------
--
-- File        : c:\My_Designs\POCP\POCP-2\compile\MUX2.vhd
-- Generated   : Thu Sep 22 19:37:39 2016
-- From        : c:\My_Designs\POCP\POCP-2\src\MUX2.bde
-- By          : Bde2Vhdl ver. 2.6
--
-------------------------------------------------------------------------------
--
-- Description : 
--
-------------------------------------------------------------------------------
-- Design unit header --
library IEEE;
use IEEE.std_logic_1164.all;


entity MUX2 is
  port(
       I0 : in STD_LOGIC;
       I1 : in STD_LOGIC;
       O : out STD_LOGIC
  );
end MUX2;

architecture MUX2 of MUX2 is

---- Signal declarations used on the diagram ----

signal NET120 : STD_LOGIC;
signal NET29 : STD_LOGIC;
signal NET38 : STD_LOGIC;

begin

----  Component instantiations  ----

NET29 <= NET120 and I1;

NET38 <= I0 and I1;

O <= NET38 or NET29;

NET120 <= not(I1);


end MUX2;
