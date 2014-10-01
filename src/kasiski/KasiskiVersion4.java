package kasiski;

import java.util.ArrayList;

public class KasiskiVersion4 {
	
	public static String removeSymbol(String cryptoText){
	ArrayList<Character> array = new ArrayList<Character>();
	for (int i=0; i< cryptoText.length(); i++){
		int currentChar = (int)cryptoText.charAt(i);
		if((currentChar>1040 && currentChar<1072) || currentChar == 1025){
			array.add((char)currentChar);
		}
	}
	char[] charArray = new char[array.size()];
	
	for(int i=0; i< array.size(); i++){
		charArray[i]=array.get(i);
	}
	
	return String.valueOf(charArray);
}

	public static int analize(String text){
		text = removeSymbol(text);
		boolean find;
		int i=-0, j;
		ArrayList<Integer> value = new ArrayList<Integer>();
		ArrayList<Integer> spisok = new ArrayList<Integer>();
		ArrayList<Integer> r = new ArrayList<Integer>();

		int exit = 0;
		while (i < text.length()-5)
    {
        find = false;
        j = i + 4;
        while (j < text.length()-3)
        {
            if (text.charAt(i) == text.charAt(j))
            {
                if ((i<text.length()-3)|(j<text.length()-3))
                    if (text.charAt(i+1)==text.charAt(j+1))
                        if (text.charAt(i+2)==text.charAt(j+2))
                            //if (text[i + 3] == text[j + 3])
                        {
                            find = true;
                            if (spisok.size() == 0)
                            {
                                spisok.add(i);
                                System.out.print(String.format("%c%c%c%c ", text.charAt(i),text.charAt(i+1),text.charAt(i+2),text.charAt(i+3)));
                            }
                            spisok.add(j);
                        }
                if (spisok.size() == 1)
                    break;
            }
            j++;

        }
        if (find)
            i += 4;
        else
            i++;

        int temp;
        if (spisok.size() > 1)
        {
            re(spisok, r);
        //    foreach (int a in spisok)
        //        Console.Write("{0}|", a);
            for(int index=0; index< r.size(); index++)
                System.out.print(String.format("%s ",r.get(index)));
            if (r.size() > 1)
            {
                temp = NODs(r);
                System.out.print(temp);
                value.add(temp);
            }
            System.out.println();
            spisok.clear();
            r.clear();
        }
        
        switch (value.size())
        {
            case 0:
                exit = 0;
                break;
            case 1:
                exit = value.get(0);
                break;
            default:
                exit = NODs(value);
                break;
        }
        
        

      
    }
    return exit;
    }

	private static void re(ArrayList<Integer> spisok, ArrayList<Integer> r) {
		for (int i = 0; i < spisok.size()-1; i++)
            r.add(spisok.get(i+1)-spisok.get(i));
		
	}
	
	private static int NODs(ArrayList<Integer> a)
    {
        int del;
        if (a.size() == 1)
            return a.get(0);
        del = nodEuclid(a.get(0), a.get(1));
        for (int i=2;i<a.size(); i++)
        {
            del = nodEuclid(del, a.get(i));
        }
        return del;
    }
	
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
}
