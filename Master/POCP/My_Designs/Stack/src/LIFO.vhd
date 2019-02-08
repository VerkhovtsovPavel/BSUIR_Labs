library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.NUMERIC_STD.all;

entity LIFO is
    generic(
        -- address bus
        m: integer := 5;
        -- data bus
        n: integer := 2
    );
    port (
        EN: in std_logic;
        -- synchronization
        CLK: in std_logic;
        -- write/read operation type
        WR: in std_logic;
        -- read data bus
        RB: out std_logic_vector(n-1 downto 0);
        -- write data bus
        WB: in std_logic_vector(n-1 downto 0)
    );
end LIFO;

architecture Beh of LIFO is
    -- word type
    subtype word is std_logic_vector (n-1 downto 0);
    -- storage
    type tRam is array (0 to 2**m - 1) of word;

    signal sRAM: tRam := (
       (others => (others => '0'))
    );
    signal head: unsigned(m - 1 downto 0) := (others => '0');
    signal data_rb: std_logic_vector(n-1 downto 0);
    signal data_wb: std_logic_vector(n-1 downto 0);

    constant Limit: unsigned(m - 1 downto 0) := to_unsigned(2 ** m -1, m);
Begin
    SH: process (CLK)
    begin
        if (EN = '1') then
            if rising_edge(CLK) then
                if (WR = '0') then
                    if (head  = Limit) then
                        head <= (others => '0');
                    else
                        head <= head + 1;
                    end if;
                elsif (WR = '1') then
                    if (head = 0) then
                        head <= Limit;
                    else
                        head <= head - 1;
                    end if;
                end if;
            end if;
        end if;
    end process;

    data_wb <= WB;

    WRP: process (CLK, head, data_wb)
    begin
        if (EN = '1') then
            if rising_edge(CLK) then
                if WR = '0' then
                    sRAM(to_integer(head)) <= data_wb;
                end if;
            end if;
        end if;
    end process;

    RDP: process(CLK, head)
    begin
        if (EN = '1') then
            if rising_edge(CLK) then
                if WR = '1' then
                    if (head = 0) then
                        data_rb <= sRAM (to_integer(Limit));
                    else
                        data_rb <= sRAM (to_integer(head - 1));
                    end if;
                end if;
            end if;
        end if;
    end process;

    RB <= data_rb;
end Beh;