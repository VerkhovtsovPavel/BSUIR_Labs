package approximation;

public abstract class Spline extends BaseApproximation{

	public Spline(double[] xArray, double[] yArray) {
		super(xArray, yArray);
	}

	public double approximazeFunction(/*N:integer;arrayX,Y:mas;S0,SN:real;Var A,B,C,D:mas*/){
	//Var F : Mas; firstStep,secondStep,p : real; i,j : integer;
	//Begin
		double firstStep=arrayX[1]-arrayX[0];
		double secondStep=arrayX[2]-arrayX[1];

		double a[] = new double[arrayX.length];
		double b[] = new double[arrayX.length];
		double c[] = new double[arrayX.length];
		double d[] = new double[arrayX.length];
		double f[] = new double[arrayX.length];

		a[0]=(2*(firstStep+secondStep))/secondStep;
	//	f[1]=(6/secondStep)*(((arrayY[3]-arrayY[2])/secondStep)-((arrayY[2]-arrayY[1])/firstStep))-(firstStep*s0)/secondStep;
		for(int i=3; i<arrayX.length-1; i++){
			firstStep=arrayX[i-1]-arrayX[i-2];
			secondStep=arrayX[i]-arrayX[i-1];
			a[i-2]=(2/secondStep)*(firstStep+secondStep);
			b[i-2]=firstStep/secondStep;
			f[i-2]=(6/secondStep)*(((arrayY[i]-arrayY[i-1])/secondStep-((arrayY[i-1]-arrayY[i-2])/firstStep)));
		}
		firstStep=arrayX[arrayX.length-1]-arrayX[arrayX.length-2];
		secondStep=arrayX[arrayX.length]-arrayX[arrayX.length-1];
		double p=2*(firstStep+secondStep);
		b[1]=firstStep/p;
	//	f[arrayX.length-2]=(6/p)*(((arrayY[arrayX.length]-arrayY[arrayX.length-1])/secondStep)-((arrayY[arrayX.length-1]-arrayY[arrayX.length-2])/firstStep))-(secondStep*sn)/p;
		d[1]=1/a[1];
		c[1]=f[1];
	/*	For i:=2 to n-3 do
		begin
			d[i]:=1/(a[i]-b[i]*d[i-1]);
			c[i]:=f[i]-b[i]*d[i-1]*c[i-1];
		end;
		d[n-2]:=(f[n-2]-b[1]*d[n-3]*c[n-3])/(1-b[1]*d[n-3]);
		For i:=n-3 downto 1 do
			d[i]:=d[i]*(c[i]-d[i+1]);
		c[1]:=s0; c[n]:=sn;
		For i:=2 to n-1 do
			c[i]:=d[i-1];
		For i:=1 to n do
		begin
			a[i]:=0; b[i]:=0;
			d[i]:=0; end;
				For i:=2 to n do
				begin
					firstStep:=arrayX[i]-arrayX[i-1];
					d[i]:=(c[i]-c[i-1])/firstStep;
					b[i]:=firstStep*c[i]/2-(Sqr(firstStep))*d[i]/6+(arrayY[i]-arrayY[i-1])/firstStep;
					a[i]:=arrayY[i];
				end;
		end;*/
		return 0;
	}
}
