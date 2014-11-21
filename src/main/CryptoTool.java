package main;

import keyGenerationAlgorithms.BaseGenerationAlgorithm;
import utils.FileUtils;

public class CryptoTool {
	private byte[] source;
	private String crypto;
	private int currentSymbol;
	private BaseGenerationAlgorithm keyGenerationMethod;
	
	public CryptoTool(String fileName, BaseGenerationAlgorithm generationMethod){
		this.source = FileUtils.readFromFileInByteArray(fileName);
		this.keyGenerationMethod = generationMethod;
	}
	
	public void encoded(){
		resetCurrentSymbol();
		while(currentSymbol*8<source.length){
			
		}
	}
	
	public void decoded(){
		resetCurrentSymbol();
		while(currentSymbol*8<crypto.length()){
			keyGenerationMethod.generate();
		}
	}
	
	private void resetCurrentSymbol(){
		this.currentSymbol=0;
	}
}
