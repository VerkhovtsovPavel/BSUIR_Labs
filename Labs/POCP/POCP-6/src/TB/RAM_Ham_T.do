SetActiveLib -work
comp -include "$dsn\src\RAM_Ham.vhd" 
comp -include "$dsn\src\TB\RAM_Ham_T.vhd" 

asim +access +r config

#
# invoke Waveform Viewer window, add signals to Waveform
#
wave
wave -noreg CLK
wave -noreg WR
wave -noreg AB
wave -noreg DB
wave -noreg ER	  

#
#
#
run 100ns
