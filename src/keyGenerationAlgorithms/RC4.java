package keyGenerationAlgorithms;

public class RC4 implements BaseGenerationAlgorithm {
	private int[] key;
	private int[] sBlock = new int[256];

	private int i;
	private int j;

	public RC4(String text, String secretKey) {
		this.key = convertToIntArray(secretKey);
		makeSBlock();
	}

	private void makeSBlock() {
		for (int i = 0; i < sBlock.length; i++) {
			sBlock[i] = i;
		}
		int swapTemp = 0;
		for (int i = 0; i < sBlock.length; i++) {
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

	private int[] convertToIntArray(String text) {
		int[] temp = new int[text.length()];
		for (int i = 0; i < text.length(); i++) {
			temp[i] = (int) text.charAt(i);
		}
		return temp;
	}

	@Override
	public int generate() {
		i = (i + 1) % 256;
		j = (j + sBlock[i]) % 256;
		swapSBlock(i, j);
		return sBlock[(sBlock[i] + sBlock[j]) % 256];
	}
}
