SetActiveLib -work
comp -include "$dsn\src\OneHot.vhd" 
comp -include "$dsn\src\TestBench\onehot_TB.vhd" 
asim +access +r TESTBENCH_FOR_onehot 
wave 
wave -noreg CLK
wave -noreg RST
wave -noreg Start
wave -noreg Stop
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\onehot_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_onehot 
