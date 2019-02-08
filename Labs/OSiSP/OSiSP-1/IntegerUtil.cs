/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 3/3/2015
 * Time: 19:05
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;

namespace OSiSP_lab1
{
    class IntegerUtil 
    {
    	public IntegerUtil(int i){}
    	
        public int ReturnFive(byte b){
            return 5;
        }

        public int ReturnTwo(int i) {
            return 2;
        }

       	public int ReturnOne(int i, byte b){
    		return 1;
    	}
    	
    	public int Sum(int i, int j){
    		return i+j;
    	}
    	
    	public static double Pow(double x, double power){
    		return Math.Pow(x, power);
    	}
    }
}