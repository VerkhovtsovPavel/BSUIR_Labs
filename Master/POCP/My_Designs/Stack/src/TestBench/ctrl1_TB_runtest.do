SetActiveLib -work
comp -include "$dsn\src\CTRL1.vhd" 
comp -include "$dsn\src\TestBench\ctrl1_TB.vhd" 
asim +access +r TESTBENCH_FOR_ctrl1 
wave 
wave -noreg CLK
wave -noreg RST
wave -noreg Start
wave -noreg Stop
wave -noreg ROM_re
wave -noreg ROM_addr
wave -noreg ROM_dout
wave -noreg RAM_rw
wave -noreg RAM_addr
wave -noreg RAM_din
wave -noreg RAM_dout
wave -noreg DP_op
wave -noreg DP_ot
wave -noreg DP_en
wave -noreg DP_res
wave -noreg DP_zf
wave -noreg DP_stop
# The following lines can be used for timing simulation
# acom <backannotated_vhdl_file_name>
# comp -include "$dsn\src\TestBench\ctrl1_TB_tim_cfg.vhd" 
# asim +access +r TIMING_FOR_ctrl1 
