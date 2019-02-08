SetActiveLib -work
comp -include "$dsn\src\MROM.vhd" 
comp -include "$dsn\src\TestBench\mrom_TB.vhd" 
asim +access +r TESTBENCH_FOR_mrom 
wave 
wave -noreg RE
wave -noreg ADDR
wave -noreg DOUT
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\mrom_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_mrom 
