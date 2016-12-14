SetActiveLib -work
comp -include "$dsn\src\TrafficLights.vhd" 
comp -include "$dsn\src\TestBenches\traffic_lights_T.vhd" 

asim +access +r config

#
# invoke Waveform Viewer window, add signals to Waveform
#
wave
wave -noreg CLK
wave -noreg CWAIT
wave -noreg RST
wave -noreg START
wave -noreg R
wave -noreg Y
wave -noreg G

#
#
#
run 100ns
