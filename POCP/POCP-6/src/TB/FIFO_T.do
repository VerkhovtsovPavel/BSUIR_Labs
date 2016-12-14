SetActiveLib -work
comp -include "$dsn\src\FIFO.vhd" 
comp -include "$dsn\src\TB\FIFO_T.vhd" 

asim +access +r config_L

#
# invoke Waveform Viewer window, add signals to Waveform
#
wave	   
wave -noreg CLK
wave -noreg WR
wave -noreg DB
wave -noreg EMPTY
wave -noreg FULL

#
#
#
run 100ns
