package kasiski;

import java.util.ArrayList;
import java.util.List;

public class Kasiski {
	        
	        private String text;
	        public static int digramLength = 3;
	        
	        public Kasiski(String cryptoText){
	        	this.text = cryptoText;
	        }
	       
	        public int kasiskiAlhoritm()
	        {
	        	int[] distance = new int[text.length()];
   	            List <Integer> repeatCount = new ArrayList<Integer>();
	            for (int i = 0; i < text.length() - digramLength + 1; i++)
	            {
	                String temp = text.substring(i, i+digramLength);
	                for (int j = i + 1; j < text.length() - digramLength + 1; j++)
	                {
	                    String temp2 = text.substring(j, j+digramLength);
	                    if(temp.equals(temp2))
	                    {
	                    	System.out.println(temp+" : "+String.valueOf(j-i));
	                        //repeatCount.add(j - i);
	                    	distance[j-i]++;
	                    }
	                }
	            }
	          /*  if(repeatCount.isEmpty()){
	            	System.out.println("No repetitive sequences");
	            	System.exit(1);
	            }*/
	            	
	           /* int[] nods = new int[text.length()];
	            for (int i = 0; i < repeatCount.size(); ++i)
	                for (int j = i + 1; j < repeatCount.size(); ++j)
	                    nods[nodEuclid(repeatCount.get(i), repeatCount.get(j))]++;
	            nods[0] = 0;*/
	            	            
	            int max = 0;
	            int maxIndex = 0;
	            for (int i = 1; i < distance.length; i++)
	            {
	                if(distance[i]>max){
	                	max=distance[i];
	                	maxIndex=i;
	                }
	            }
	            
	            //TODO Pasha remove this!!!!!!!!!!!!!!!!!!!!!!!!
	            int index = maxIndex;
	            distance[maxIndex]=0;
	            
	            max = 0;
	            maxIndex = 0;
	            for (int i = 1; i < distance.length; i++)
	            {
	                if(distance[i]>max){
	                	max=distance[i];
	                	maxIndex=i;
	                }
	            }
	            
	            return nodEuclid(index, maxIndex);
	        }
	        
	        //TODO Change method name
	        private int nodEuclid(int a, int b){
	            if (b == 0)
	            {
	                return a;
	            }
	            else
	            {
	                return nodEuclid(b, a % b);
	            }
	        }

	
	}


