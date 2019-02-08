SetActiveLib -work
comp -include "$dsn\src\RegFile.vhd" 
comp -include "$dsn\src\TB\RegFile_T.vhd" 

asim +access +r config

#
# invoke Waveform Viewer window, add signals to Waveform
#
wave
wave -noreg WDP
wave -noreg WA
wave -noreg RA
wave -noreg WE
wave -noreg RDP	  

#
#
#
run -all
