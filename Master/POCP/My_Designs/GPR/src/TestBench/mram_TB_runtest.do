SetActiveLib -work
comp -include "$dsn\src\MRAM.vhd" 
comp -include "$dsn\src\TestBench\mram_TB.vhd" 
asim +access +r TESTBENCH_FOR_mram
wave 
wave -noreg RW
wave -noreg CLK
wave -noreg ADDR1
wave -noreg ADDR2
wave -noreg ADDRW
wave -noreg D1OUT
wave -noreg D2OUT
wave -noreg DWIN
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\mram_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_mram 
