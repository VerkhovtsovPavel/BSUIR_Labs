SetActiveLib -work
comp -include "$dsn\src\DPATH.vhd" 
comp -include "$dsn\src\TestBench\dpath_TB.vhd" 
asim +access +r TESTBENCH_FOR_dpath 
wave 
wave -noreg EN
wave -noreg OT
wave -noreg OP1
wave -noreg OP2
wave -noreg RES
wave -noreg ZF
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\dpath_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_dpath 
