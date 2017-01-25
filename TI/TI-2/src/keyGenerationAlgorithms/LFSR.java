package keyGenerationAlgorithms;



public class LFSR implements BaseGenerationAlgorithm{
	private int[] polinome;
	private int[] currentState;
	
	public LFSR(int[] startState, int[] polinom){
		this.currentState = startState;
		this.polinome = polinom;
	}

	@Override
	public int generate() {
		int newBitKey = currentState[currentState.length];
		shiftArray();
		return newBitKey;
	}
	
	private int newBit(){
		int result = 0;
		for(Integer rank: polinome){
			result^=currentState[rank];
		}
		return result;
	}
	
	private void shiftArray(){
		for(int i=currentState.length;i>0; i--){
			currentState[i]=currentState[i-1];
		}
		currentState[0]=newBit();
	}
	
}
