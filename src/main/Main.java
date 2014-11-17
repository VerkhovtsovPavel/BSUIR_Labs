package main;

import java.util.Scanner;

public class Main {
	double[] ErmithQuot = { 1, 4, 4, 16 };
	double[] WeightFactors = new double[4];
	double[] CurW = new double[4];
	double[] tmp = new double[4];
	double[][] StudySample = new double[4][2];
	double[] TestSample = new double[2];
	int IterCount;
	double minX, maxX, minY, maxY, ComX, ComY, shx, shy, it, jt, previt, prevjt;

	private Scanner in = new Scanner(System.in);

	private void Button1Click() {
		int i;
		boolean fnd, prox;
		double cks;
		String func;

		for (i = 0; i < 4; i++) {
			WeightFactors[i] = 0;
		}
		System.out.println("Enter study points");
		StudySample[0][0] = in.nextInt();
		StudySample[0][1] = in.nextInt();
		StudySample[1][0] = in.nextInt();
		StudySample[1][1] = in.nextInt();
		StudySample[2][0] = in.nextInt();
		StudySample[2][1] = in.nextInt();
		StudySample[3][0] = in.nextInt();
		StudySample[3][1] = in.nextInt();

		i = 0;
		IterCount = 0;
		WeightFactors[0] = 1;
		WeightFactors[1] = 4 * StudySample[i][0];
		WeightFactors[2] = 4 * StudySample[i][1];
		WeightFactors[3] = 16 * StudySample[i][0] * StudySample[i][1];
		do {
			i = 0;
			// if IterCount=0 i=0 else i=1;
			fnd = false;
			do {
				CurW[0] = WeightFactors[0];
				CurW[1] = WeightFactors[1] * StudySample[i + 1][0];
				CurW[2] = WeightFactors[2] * StudySample[i + 1][1];
				CurW[3] = WeightFactors[3] * StudySample[i + 1][0] * StudySample[i + 1][1];
				cks = 0;
				for (int k = 0; k < 4; k++)
					cks = cks + CurW[k];
				tmp[0] = 1;
				tmp[0] = 4 * StudySample[i + 1][0];
				tmp[0] = 4 * StudySample[i + 1][1];
				tmp[0] = 16 * StudySample[i + 1][0] * StudySample[i + 1][1];
				if ((i + 1 <= 3/*???*/) && (cks < 0/*???*/)) {
					fnd = true;
					for (int j = 0; j < 4; j++)
						WeightFactors[j] = WeightFactors[j] + tmp[j];
				} else if ((i + 1 > 1/*???*/) && (cks > 0/*???*/)) {
					fnd = true;
					for (int j = 0; j < 4; j++)
						WeightFactors[j] = WeightFactors[j] - tmp[j];
				}
				;
				if (i < 3/*???*/)
					i++;
				else
					i = 0;
			} while (i == 1);/*???*/
			IterCount++;
		} while ((!fnd) || (IterCount == 50));
		if ((cks <= 0) || (IterCount > 49)) {/*???*/
			System.out.println("With this training set build a separating function impossible");
		}
		// else
		{
			// for i=1 to 4 ShowMessage(FloatToStr(WeightFactors[i]));
			minX = StudySample[0][0];
			maxX = minX;
			minY = StudySample[0][1];
			maxY = minY;
			for (i = 1; i < 4; i++) {
				if (minX > StudySample[i][0])
					minX = StudySample[i][0];
				if (minY > StudySample[i][1])
					minY = StudySample[i][1];
				if (maxX < StudySample[i][0])
					maxX = StudySample[i][0];
				if (maxY < StudySample[i][1])
					maxY = StudySample[i][1];
			}
			maxX = maxX + 3;
			minX = minX - 3;
			maxY = maxY + 3;
			minY = minY - 3;
			ComX = 545 / (maxX - minX);
			ComY = 369 / (maxY - minY);
			// Draw some
			/*
			 * PaintBox1.Canvas.Pen.Color=clBlue;
			 * PaintBox1.Canvas.MoveTo(0,370-Round(-comy*minY));
			 * PaintBox1.Canvas.LineTo(545,370-Round(-comy*minY));
			 * PaintBox1.Canvas.MoveTo(Round(-minX*comx),0);
			 * PaintBox1.Canvas.LineTo(Round(-minX*comx),370);
			 * PaintBox1.Canvas.Pen.Color=clBlack;
			 * PaintBox1.Canvas.Brush.Color=clBlack;
			 */
			it = minX;
			if ((WeightFactors[2] != 0) || (WeightFactors[3] != 0))
				jt = -(WeightFactors[0] + WeightFactors[1] * it) / (WeightFactors[2] + WeightFactors[3] * it);
			else
				jt = -(WeightFactors[0] + WeightFactors[1] * it);
			/*
			 * PaintBox1.Canvas.MoveTo(Round((it-minX)*ComX),370-Round((jt-minY)*
			 * ComY));
			 */
			while (it <= maxX) {
				if (WeightFactors[3] != 0) {
					if (it != -WeightFactors[2] / WeightFactors[3]) {
						if ((WeightFactors[2] != 0) && (WeightFactors[3] != 0))
							jt = -(WeightFactors[0] + WeightFactors[1] * it) / (WeightFactors[2] + WeightFactors[3] * it);
						else
							jt = -(WeightFactors[0] + WeightFactors[1] * it);
					}
				} else if (WeightFactors[2] != 0)
					jt = -(WeightFactors[0] + WeightFactors[1] * it) / (WeightFactors[2] + WeightFactors[3] * it);
				else
					jt = -(WeightFactors[0] + WeightFactors[1] * it);
				/*
				 * if ((jt<=maxY)&&(jt>=minY))
				 * PaintBox1.Canvas.LineTo(Round((it-
				 * minX)*ComX),370-Round((jt-minY)*ComY)); else
				 * PaintBox1.Canvas.
				 * MoveTo(Round((it-minX)*ComX),370-Round((jt-minY)*ComY));
				 */
				it = it + 0.001;
			}
			;
			if (WeightFactors[0] / WeightFactors[2] == WeightFactors[1] / WeightFactors[3]) {
				/*
				 * PaintBox1.Canvas.MoveTo(Round((-minX+WeightFactors[1]/
				 * WeightFactors[3])*ComX),0);
				 * PaintBox1.Canvas.LineTo(Round((-minX
				 * +WeightFactors[1]/WeightFactors[3])*ComX),370);
				 */
			}
			;
			/*
			 * PaintBox1.Canvas.Pen.Color=clGreen;
			 * PaintBox1.Canvas.Brush.Color=clGreen;
			 */
			for (i = 0; i < 4; i++) {
				if (i == 2) {
					/*
					 * PaintBox1.Canvas.Pen.Color=clRed;
					 * PaintBox1.Canvas.Brush.Color=clRed;
					 */
				}
				/*
				 * PaintBox1.Canvas.Ellipse(Round((StudySample[i][0]-minX)*ComX)-
				 * 3
				 * ,370-Round((StudySample[i][1]-minY)*ComY)-3,Round((StudySample
				 * [
				 * i][0]-minX)*ComX)+3,370-Round((StudySample[i][1]-minY)*ComY)+
				 * 3);
				 */
			}
			func = "y=-(" + String.valueOf(WeightFactors[0]);
			if (WeightFactors[1] >= 0)
				func = func + '+' + String.valueOf(WeightFactors[1]);
			else
				func = func + String.valueOf(WeightFactors[1]);
			func = func + "x)";
			if (WeightFactors[2] != 0) {
				func = func + "/(" + String.valueOf(WeightFactors[2]);
				if (WeightFactors[3] < 0)
					func = func + String.valueOf(WeightFactors[3]) + "x";
				else if (WeightFactors[3] > 0)
					func = func + '+' + String.valueOf(WeightFactors[3]) + "x";
			} else if (WeightFactors[3] != 0)
				func = func + "/(" + String.valueOf(WeightFactors[3]) + "x";
			func = func + ')';
			/*
			 * Memo1.Clear; Memo1.Lines.Add(func);
			 */
		}
	}

	private void Button2Click() {
		double cks;
		int i;
		/*
		 * TestSample[1]=StrToFloat(Edit9.Text);
		 * TestSample[2]=StrToFloat(Edit10.Text);
		 */
		CurW[0] = WeightFactors[0];
		CurW[0] = WeightFactors[1] * TestSample[0];
		CurW[0] = WeightFactors[2] * TestSample[1];
		CurW[0] = WeightFactors[3] * TestSample[0] * TestSample[1];
		cks = 0;
		for (i = 0; i < 4; i++)
			cks = cks + CurW[i];
		if (cks <= 0) {
			/*
			 * if (cks==0) Edit11.Text='Ãðàíèöà';
			 */

			/*
			 * Edit11.Text='2'; PaintBox1.Canvas.Pen.Color=clRed;
			 * PaintBox1.Canvas.Brush.Color=clRed;
			 */
		} else {
			/*
			 * Edit11.Text='1'; PaintBox1.Canvas.Pen.Color=clGreen;
			 * PaintBox1.Canvas.Brush.Color=clGreen;
			 */
		}
		/*
		 * PaintBox1.Canvas.Ellipse(Round((TestSample[0]-minX)*ComX)-3,370-Round(
		 * (
		 * TestSample[1]-minY)*ComY)-3,Round((TestSample[0]-minX)*ComX)+3,370-Round
		 * ((TestSample[1]-minY)*ComY)+3);
		 */
	}

}
