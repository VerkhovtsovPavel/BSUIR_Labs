SetActiveLib -work
comp -include "$dsn\src\LIFO.vhd" 
comp -include "$dsn\src\TestBench\lifo_TB.vhd" 
asim +access +r TESTBENCH_FOR_lifo 
wave 
wave -noreg EN
wave -noreg CLK
wave -noreg WR
wave -noreg RB
wave -noreg WB
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\lifo_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_lifo 
