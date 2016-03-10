package main;

import keyGenerationAlgorithms.BaseGenerationAlgorithm;
import keyGenerationAlgorithms.RC4;
import utils.FileUtils;

public class CryptoTool {
	private byte[] source;
	private int[] crypto;
	private int currentSymbol;
	private BaseGenerationAlgorithm keyGenerationMethod;
	
	public CryptoTool(String fileName, BaseGenerationAlgorithm generationMethod){
		this.source = FileUtils.readFromFileInByteArray(fileName);
		this.keyGenerationMethod = generationMethod;
	}
	
	public void encoded(){
		resetCurrentSymbol();
		while(currentSymbol<source.length){
			if (keyGenerationMethod instanceof RC4){
				this.crypto[currentSymbol]=(byte) (source[currentSymbol]^keyGenerationMethod.generate());
				currentSymbol++;
			}
			else{
				for(int i=0; i<8; i++){
					
				}
			}
			
		}
	}
	
	public void decoded(){
		resetCurrentSymbol();
		while(currentSymbol*8<crypto.length){
			keyGenerationMethod.generate();
		}
	}
	
	private void resetCurrentSymbol(){
		this.currentSymbol=0;
	}
}
