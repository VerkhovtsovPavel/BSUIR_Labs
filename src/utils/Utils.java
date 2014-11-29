package utils;


public class Utils {
	public static boolean intToBool(int value){
		if(value==0)
			return false;
		return true;
	}
	
	public static Boolean bitValue(byte b, int bit)
	{
	    return (b & (1 << bit)) != 0;
	}
}
