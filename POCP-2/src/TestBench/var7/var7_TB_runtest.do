SetActiveLib -work
comp -include "$dsn\src\structure\Var7.vhd" 
comp -include "$dsn\src\TestBench\var7\var7_TB.vhd" 
asim +access +r TESTBENCH_FOR_var7 
wave 
wave -noreg W
wave -noreg X
wave -noreg Y
wave -noreg Z
wave -noreg G 
wave -noreg G1
wave -noreg error
run  800.00 ns
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\var7_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_var7 
