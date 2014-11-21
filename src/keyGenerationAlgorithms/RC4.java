package keyGenerationAlgorithms;

public class RC4 {
	private int[] key;
	private int[] plainText;
	private int[] sBlock = new int[256];

	public RC4(String text, String secretKey){
		this.key=convertToIntArray(secretKey);
		this.plainText=convertToIntArray(text);
		makeSBlock();
	}
	
	private void makeSBlock(){
		for(int i=0; i< sBlock.length; i++){
			sBlock[i]=i;
		}
		int swapTemp = 0;
		for (int i = 0; i< sBlock.length; i++){
			swapTemp = (swapTemp + sBlock[i] + key[i % key.length]) % 256;
			swapSBlock(i, swapTemp);
		}	
	}
	
	private void swapSBlock(int i, int j) {
		int buf;
		buf = sBlock[j];
		sBlock[j] = sBlock[i];
		sBlock[i] = buf;
	}
	
	private int[] convertToIntArray(String text){
		int[] temp = new int[text.length()];
		for (int i=0; i< text.length(); i++){
			temp[i] = (int) text.charAt(i);
		}
		return temp;
	}
}	
