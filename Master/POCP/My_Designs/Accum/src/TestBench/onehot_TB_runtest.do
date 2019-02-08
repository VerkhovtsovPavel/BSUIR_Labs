SetActiveLib -work
comp -include "$dsn\src\OneHot.vhd" 
comp -include "$dsn\src\TestBench\onehot_TB.vhd" 
asim +access +r TESTBENCH_FOR_onehot 
wave 
wave -noreg CLK
wave -noreg RST
wave -noreg Start
wave -noreg Stop
