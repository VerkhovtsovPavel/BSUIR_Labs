SetActiveLib -work
comp -include "$dsn\src\SyncREGn.vhd" 
comp -include "$dsn\src\TestBench\syncregn_TB.vhd" 
asim +access +r TESTBENCH_FOR_syncregn 
wave 
wave -noreg Din
wave -noreg EN
wave -noreg C
wave -noreg Dout	 
run 250 ns
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\syncregn_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_syncregn 
