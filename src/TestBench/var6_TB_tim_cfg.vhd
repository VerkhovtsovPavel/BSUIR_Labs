configuration TESTBENCH_FOR_var6 of var6_tb is
	for TB_ARCHITECTURE
		for UUT : var6
			use entity work.var6(behavior);
		end for;	
		for UUT2 : var6
			use entity work.var6(structual);
		end for;
	end for;
end TESTBENCH_FOR_var6;

