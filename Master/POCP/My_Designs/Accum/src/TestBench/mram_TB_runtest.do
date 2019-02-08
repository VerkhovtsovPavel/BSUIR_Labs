SetActiveLib -work
comp -include "$dsn\src\MRAM.vhd" 
comp -include "$dsn\src\TestBench\mram_TB.vhd" 
asim +access +r TESTBENCH_FOR_mram 
wave 
wave -noreg CLK
wave -noreg RW
wave -noreg ADDR
wave -noreg DIN
wave -noreg DOUT
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\mram_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_mram 
