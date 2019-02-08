SetActiveLib -work
comp -include "$dsn\src\Signature.vhd" 
comp -include "$dsn\src\TestBench\signature_t.vhd" 
asim +access +r TESTBENCH_FOR_signature 
wave 
wave -noreg CLK
wave -noreg RST
wave -noreg Pin
wave -noreg Pout  
wave -noreg buf
run  250.00 ns
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\lfsr_in_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_lfsr_in 
