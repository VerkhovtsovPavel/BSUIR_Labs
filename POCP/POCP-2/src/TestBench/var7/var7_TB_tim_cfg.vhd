configuration TIMING_FOR_var7 of var7_tb is
	for TB_ARCHITECTURE
		for UUT : var7
			use entity work.var7(behavior);
		end for;	
		for UUT2 : var7
			use entity work.var7(structual);
		end for;
	end for;
end TIMING_FOR_var7;

