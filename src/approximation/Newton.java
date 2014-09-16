package approximation;

public class Newton {
	private double[] arrayX;
	private double[] arrayY;
	private double eps = 0.00001;
	
	/**
	 * Constructor
	 * @param xArray Array of x coordinate
	 * @param yArray Array of y coordinate
	 */
	public Newton(final double[] xArray, final double[] yArray){
		arrayX=xArray;
		arrayY=yArray;
	}

	public void approximazeFunction(final double x) /*объявление функции "ньютон", входные данные:двумерный массив значений "Х" и "Y", значение "Х", введенное с клавиатуры, количество узлов интерполяции, заданная погрешность*/
	{
		double s,dX,q,h;  //локальные переменные
		int k=0, flag=0;
		//k=0;
		//flag=0;
		h=arrayY[1]-arrayX[1];
		for(int i=0;i<arrayX.length;i++)
		{
			if(x==arrayX[i]) /*проверка равенства введенного значения аргумента "х" со значением из массива табличных аргументов "х[]" если равенство выполняется, в переменную flag записывается номер элемента массива табличных значений, равного введенному с клавиатуры "х"*/
				flag=i;

	dX=x-arrayX[i];
	 
	  if((dX<h)&&(dX>0)) //нахождение ближайшего узла интерполяции
	k=i;
	}
	/*if (flag==0) //если совпадений, введенного с клавиатуры значения, "х" с массивом табличных значений нет, вычисляются конечные разности
	{
	for(int j=3;j<=n+2;j++) {
		for(int i=k;i<=n-j+2;i++)
		arg[i][j]=arg[i+1][j-1]-arg[i][j-1];   /* вычисление конечных разностей. Конечные разности записываются в двумерный массив
		  начиная с третьего столбца*/
	/*}

	q=(x-arg[k][1])/h;
	arg[0][0]=arg[1][0]=arg[max][max]=0;
	for(int m=1;m<=n-k;m++)
	{
	  arg[0][0]=arg[k][2]; //инициализация первого слагаемого (y0) полинома
	  for(int j=3;j<=m+2;j++)   //вычисление слагаемых полинома
	  {
	    s=arg[k][j]; //переменной s присваивается значение dY порядка j
	    for(int i=1;i<=j-2;i++)
	  s=s*((q-i+1)/i);
	arg[0][0]=arg[0][0]+s; //вычисление значения функции в заданной точке  
	}



	  arg[1][0]=s; //в элементе первой строки нулевого столбца записывается приблизительная погрешность интерполирования
	  arg[max][max]=m;  //в элемент последнего столбца нижней строки сохраняется порядок полинома

	  if (Math.abs(s)<eps)   //если заданная точность интерполирования достигнута процесс вычисления завершается
		  break;
	}
	}
	else
	{
	s=arg[flag][2];
	}
	}   //конец функции "ньютон"



	int main()   //начало основной программы

	{
	  float h;
	  //int n;
	  /*cout<<"Enter quantity of figures N=";
	  cin>>n;   //ввод количества узлов интерполяции
	  cout<<endl;*/
	  /*cout<<"Enter value of argument X=";
	  cin>>x;   //ввод значения "х"
	  cout<<endl;*/
	 /* cout<<"Enter an interpolation error E=";
	  cin>>e;   //ввод заданной погрешности интерполяции
	  cout<<endl;*/
	 /* cout<<"Enter distance between knots h=";
	  cin>>h;   //ввод расстояния между узлами
	  cout<<endl;*/

	/*for (int i=0;i<=n;i++)
	  {
	arg[i][1]=i*h; //заполнение массива табличных значений аргумента функции "x", аргументы функции заполняют первый столбец двумерного массива
	cout<<"Enter values of function Y"<<i<<"= ";
	cin>>arg[i][2];  //заполнение массива табличных значений функции "y", значения функции заполняют второй столбец двумерного массива
	cout<<"\n";
	}*/
	  
	  //TODO Input x and y

	//newton(arg,x,n,eps);
	//cout<<setw(5)<<"X(i)"<<setw(15)<<"Y(i)"<<endl;*/
	/*for(int i=0;i<=n;i++)
	cout<<setw(5)<<arg[i][1]<<setw(16)<<arg[i][2]<<"\n"; //вывод на экран табличных значений аргументов и функции

	cout<<endl;
	cout<<"X="<<x<<"  P("<<arg[max][max]<<")="<<arg[0][0]<<"   E="<<arg[1][0]<<endl; // вывод результатов вычислений на экран
	getch();
	return 0;*/
	}
}
