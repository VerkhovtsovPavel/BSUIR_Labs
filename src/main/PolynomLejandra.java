package main;

class PolynomLejandra
{
    public static class Coefficients
    {
        public static double[] a;
        public static double[] x;
    }

    public Coefficients[] coefficients;
   
    public PolynomLejandra()
    {
        coefficients = new Coefficients[6];
        for (int i = 0; i < 6; i++)
        {
            coefficients[i].a = new double[7];
            coefficients[i].x = new double[7];
        }
 

        coefficients[0].a[0] = 1.000000;
        coefficients[0].a[1] = 1.000000;
        coefficients[0].x[0] = -0.577350;           
        coefficients[0].x[1] = 0.577350;

        coefficients[1].a[0] = 0.555556;
        coefficients[1].a[1] = 0.888889;
        coefficients[1].a[2] = 0.555556;
        coefficients[1].x[0] = -0.774597;
        coefficients[1].x[1] = 0.000000;
        coefficients[1].x[2] = 0.774597;

        coefficients[2].a[0] = 0.347855;
        coefficients[2].a[1] = 0.652145;
        coefficients[2].a[2] = 0.652145;
        coefficients[2].a[3] = 0.347855;
        coefficients[2].x[0] = -0.861136;
        coefficients[2].x[1] = -0.339981;
        coefficients[2].x[2] = 0.339981;
        coefficients[2].x[3] = 0.861136;

        coefficients[3].a[0] = 0.236927;
        coefficients[3].a[1] = 0.478629;
        coefficients[3].a[2] = 0.568889;
        coefficients[3].a[3] = 0.478629;
        coefficients[3].a[4] = 0.236927;
        coefficients[3].x[0] = -0.906180;
        coefficients[3].x[1] = -0.538469;
        coefficients[3].x[2] = 0.000000;
        coefficients[3].x[3] = 0.538469;
        coefficients[3].x[4] = 0.906180;

        coefficients[4].a[0] = 0.171324;
        coefficients[4].a[1] = 0.360761;
        coefficients[4].a[2] = 0.467913;
        coefficients[4].a[3] = 0.467913;
        coefficients[4].a[4] = 0.360761;
        coefficients[4].a[5] = 0.171324;
        coefficients[4].x[0] = -0.932469;
        coefficients[4].x[1] = -0.661209;
        coefficients[4].x[2] = -0.238619;
        coefficients[4].x[3] = 0.238619;
        coefficients[4].x[4] = 0.661209;
        coefficients[4].x[5] = 0.932469;

        coefficients[5].a[0] = 0.129484;
        coefficients[5].a[1] = 0.279705;
        coefficients[5].a[2] = 0.381830;
        coefficients[5].a[3] = 0.417959;
        coefficients[5].a[4] = 0.381830;
        coefficients[5].a[5] = 0.279705;
        coefficients[5].a[6] = 0.129484;
        coefficients[5].x[0] = -0.949107;
        coefficients[5].x[1] = -0.741531;
        coefficients[5].x[2] = -0.405845;
        coefficients[5].x[3] = 0.000000;
        coefficients[5].x[4] = 0.405845;
        coefficients[5].x[5] = 0.741531;
        coefficients[5].x[6] = 0.949107;

    }
}
