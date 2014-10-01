package kasiski;

import java.util.ArrayList;

public class KasiskiVersion2 {

	private static int nl=0;


	private static int nodEuclid(int a, int b){
        if (b == 0)
        {
            return a;
        }
        else
        {
            return nodEuclid(b, a % b);
        }
    }



	public static int kasiskiAlhoritm(String s)
	{    
	    char c;
	    int n=0,i,j,keylen;
	    int[] nods = new int[1000000];
	   ArrayList<Integer> l = new ArrayList<Integer>();
	   
	    
	    for (i=0;i<500;++i)
	        nods[i]=0;

	    

	    String str1,str2;
	    
	    for (i=0;i<s.length()-3;++i)
	    {
	        str1 = s.substring(i, i+3);
	        
	        for (j=i+1;j<s.length()-3;++j)
	        {
	            str2 = s.substring(i, i+3);
	            
	            if (str1.equals(str2)){
	            	l.add(j-i);
	            }
	            }
	            
	        }

	    
	    for (i=0;i<nl;++i)
	        for (j=i+1;j<nl;++j)
	            nods[nodEuclid(l.get(i),l.get(j))]++;


	    keylen=1;
	    for (i=2;i<nods.length;++i)
	        if (nods[keylen]<nods[i]) keylen=i;
	        
	    System.out.println(String.format("%s\n",keylen));
	    

	    return 0;
	}
}
