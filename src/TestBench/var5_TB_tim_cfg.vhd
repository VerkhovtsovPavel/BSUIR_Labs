configuration TESTBENCH_FOR_var5 of var5_tb is
	for TB_ARCHITECTURE
		for UUT : var5
			use entity work.var5(behavior);
		end for;	
		for UUT2 : var5
			use entity work.var5(structual);
		end for;
	end for;
end TESTBENCH_FOR_var5;