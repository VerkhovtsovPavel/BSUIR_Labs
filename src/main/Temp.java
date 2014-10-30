package main;

import java.util.Random;
import java.util.Scanner;

public class Temp {

	private static Scanner in = new Scanner(System.in);

	// FeatCol=array[1..11]of real; // max 10 features to each object

	int NumCl, NumObj, NumFeat;

	double[][][] StudySample = new double[10][15][11]; // max 15 objects in
														// study sample to (max)
														// 10 classes
	double[][] WeightFactors = new double[10][11]; // max 10 classes having
													// specific weight factors

	private double VecMult(double[] v1, double[] studySample2, int j) {
		double tmp;
		tmp = 0;
		for (int i = 0; i < j; i++)
			tmp = tmp + v1[i] * studySample2[i];
		return tmp;
	}

	private void Button1Click() {

		String s;
		double[] D = new double[15]; // storage for current multiplication
										// results
		int max;
		String str;
		boolean eq, WasPun, trymean;

		NumCl = in.nextInt();
		NumObj = in.nextInt();
		NumFeat = in.nextInt();
		Random random = new Random();
		for (int i = 0; i < NumCl; i++) {
			for (int j = 0; j < NumObj; j++) {
				for (int k = 0; k < NumFeat; k++) {
					StudySample[i][j][k] = random.nextInt(20) - 10;
				}
			}
		}

		for (int i = 0; i < NumCl; i++) {
			for (int j = 0; j < NumObj; j++) {
				StudySample[i][j][NumFeat + 1] = 1; // ??
			}
		}

		/*
		 * for (int i = 0 ; i<NumCl;i++){ Memo1.Lines.Add(IntToStr(i)+'
		 * êëàññ:'); for j = 1 to NumObj do { str='('; for k = 1 to NumFeat do {
		 * str=str+FloatToStr(StudySample[i,j,k])+';'; }; str=str+')';
		 * Memo1.Lines.Add(str); }; };
		 */// Output points

		for (int i = 0; i < NumCl; i++) {
			for (int j = 0; j < NumFeat; j++) {
				WeightFactors[i][j] = 0;
			}
		}
		int i = 1;
		int sur = 0;
		WasPun = false;
		trymean = false;
		do {
			// WasPun=False;
			int CurObj = 0;
			do {
				// WasPun=False;
				int CurCl = 0;
				do {
					WasPun = false;
					for (int j = 0; j < NumCl; j++) {
						D[j] = VecMult(WeightFactors[j], StudySample[CurCl][CurObj], NumFeat - 1);
						D[j] = D[j] + WeightFactors[j][NumFeat];
					}
					max = 1;
					for (int j = 0; j < NumCl; j++) {
						if (D[j] > D[max])
							max = j;
					}
					eq = false;
					for (int j = 0; j < NumCl; j++) {
						if ((D[j] == D[max]) && (j != max))
							eq = true; // ðåçóëüòàò êëàñcèôèêàöèè íåâåðíûé
					}
					if ((max != CurCl) || (eq)) // punishment
					{
						for (int j = 0; j < NumCl; j++) {
							if (j == CurCl)
								for (int k = 0; i < NumFeat + 1; i++)
									WeightFactors[j][k] = WeightFactors[j][k] + StudySample[CurCl][CurObj][k];
							else
								for (int k = 0; i < NumFeat + 1; i++)
									WeightFactors[j][k] = WeightFactors[j][k] - StudySample[CurCl][CurObj][k];
						}
						WasPun = true;
					}
					CurCl++;
				} while (CurCl != NumCl + 1);
				CurObj++;
			} while (CurObj != NumObj + 1);
			sur++;
		} while (WasPun && (sur < 100));
		if (sur == 100) {
			System.out.println("Error");
		}
	}
	// ShowMessage('Èòåðàöèîííûé ïðîöåññ íå ñõîäèòñÿ. Ðåøàþùèå ôóíêöèè,
	// âîçìîæíî, íåïðàâèëüíû.');
	/*
	 * for (int i = 0 ; i<NumCl;i++) {
	 * s='d'+IntToStr(i)+'(x)='+FloatToStr(WeightFactors[i,1])+'x1'; for j=2 to
	 * NumFeat+1 do { if WeightFactors[i,j]>=0 then s=s+'+'; if j<>NumFeat+1
	 * then s=s+FloatToStr(WeightFactors[i,j])+'x'+IntToStr(j) else
	 * s=s+FloatToStr(WeightFactors[i,j]); }; Memo1.Lines.Add(s); };
	 * StringGrid1.RowCount=NumFeat; };
	 */// Answer

	/*
	 * private void Button2Click() {
	 * 
	 * TestSample,D:FeatCol; int i,j,max; for i = 1 to NumFeat do
	 * TestSample[i]=StrToFloat(StringGrid1.Cells[0,i-1]); for i = 1 to NumCl do
	 * { D[i]=VecMult(TestSample,WeightFactors[i],NumFeat);
	 * D[i]=D[i]+WeightFactors[i][NumFeat+1]; }; max=1; for i = 2 to NumCl do if
	 * D[i]>D[max] then max=i; ShowMessage('Ïðåäñòàâëåííûé îáúåêò îòíîñèòñÿ ê
	 * '+IntToStr(max)+'-ìó êëàññó.'); }
	 * 
	 * }
	 */// Check point
}
