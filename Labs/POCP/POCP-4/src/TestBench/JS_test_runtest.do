SetActiveLib -work
comp -include "$dsn\src\JC.vhd" 
comp -include "$dsn\src\TestBench\JS_test.vhd"
asim +access +r TESTBENCH_FOR_JS
wave 
wave -noreg CLK
wave -noreg RST
wave -noreg LS
wave -noreg Pin
wave -noreg Pout
run  150.00 ns
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\syncregn_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_syncregn 
