SetActiveLib -work
comp -include "$dsn\src\Max\MaxFinder.vhd" 
comp -include "$dsn\src\TestBenches\max_finder_T.vhd" 

asim +access +r config

#
# invoke Waveform Viewer window, add wave -noregs to Waveform
#
wave
wave -noreg rom_re
wave -noreg rom_adr
wave -noreg rom_dout
wave -noreg ram_rw
wave -noreg ram_adr
wave -noreg ram_din
wave -noreg ram_dout
wave -noreg dp_op1
wave -noreg dp_ot
wave -noreg dp_en
wave -noreg dp_res
wave -noreg dp_zf
wave -noreg dp_sbf

#
#
#
run 1500ns
