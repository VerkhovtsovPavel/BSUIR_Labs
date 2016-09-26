SetActiveLib -work
comp -include "$dsn\src\structure\Var6.vhd" 
comp -include "$dsn\src\TestBench\var6_TB.vhd" 
asim +access +r TESTBENCH_FOR_var6 
wave 
wave -noreg W
wave -noreg X
wave -noreg Y
wave -noreg Z
wave -noreg F 
wave -noreg F1 
wave -noreg error
run  800.00 ns
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\var6_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_var6 
