package keyGenerationAlgorithms;

import utils.Utils;

public class Geffe implements BaseGenerationAlgorithm{
	private LFSR topLfsr;
	private LFSR lowerLfsr;
	private LFSR controlLfsr;
	
	public Geffe(LFSR topLfsr, LFSR lowerLfsr, LFSR controlLfsr){
		this.topLfsr=topLfsr;
		this.lowerLfsr=lowerLfsr;
		this.controlLfsr = controlLfsr;
	}

	@Override
	public int generate() {
		if(Utils.intToBool(controlLfsr.generate())){
			return topLfsr.generate();
		}
		return lowerLfsr.generate();
	}
	

}
