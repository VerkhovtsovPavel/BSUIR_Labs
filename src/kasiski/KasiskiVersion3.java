package kasiski;

public class KasiskiVersion3 {

	private final int NUMCHARS = 128;
	private final int TEXTLEN = 10000; /* Ciphertext array */
	char[] C;
	int Clen;

	char[] R;
	private final int RTHRESH = 3;

	public void main() {
		int i, j, k;
		i = 0;
		Clen = i;

		for (i = 0; i < Clen; i++) {
			j = i + 1;
			while (j < Clen) {
				k = 0;
				while (C[i + k] == C[j + k]) {
					R[k] = C[i + k];
					k++;
				}
				R[k] = '\0';
				if (k >= RTHRESH) {
					System.out.println(String.format("%s\tat %d and %d with diff %d\n", R, i, j, j - i));
				}
				j++;
			}
		}
	}

}
