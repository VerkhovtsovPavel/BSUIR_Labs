package by.bsuir.verkpavel.db.logic;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class RequestGenerator {
    //private final String parametersPattern = "{((rStr)|(rInt)|(rDouble))\\((\\d{1,6}\\-\\d{1,6})\\)}";
    private final String doublePattern = "\\{(rDouble)\\((\\d{1,6}\\-\\d{1,6})\\)\\}";
    private final String stringPattern = "\\{(rStr)\\((\\d{1,6}\\-\\d{1,6})\\)\\}";
    private final String intPattern = "\\{(rInt)\\((\\d{1,6}\\-\\d{1,6})\\)\\}";
    private final String valuesPattern = "\\{(rValue)\\([\\'\\w\\d]+(,[\\w\\d\\']+)*\\)\\}";
    
    
    private final char[] symbols = {'a', 'e', 'i', 'o','u','A','E','I','O','U','1', '2', '3', '4', '5', '6', '7', '8', '9', '0','b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z','B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z'};
    
    private int countCommandInQuery;
    private String queryString;
    private Random random;
   
    private static Logger log = Logger.getLogger(RequestGenerator.class);
    

    public RequestGenerator(int countCommandInQuery, String queryString) {
        this.countCommandInQuery = countCommandInQuery;
        this.queryString = queryString;
        this.random = new Random();
    }
    
    
    public String getParametrazedQuery(){
        String result = "";
        for(int i=0;i< countCommandInQuery; i++){
            String preQuery = replaceStringParams(queryString);
            preQuery = replaceIntParams(preQuery);
            preQuery = replaceDoubleParams(preQuery);
            preQuery = replaceValueParams(preQuery);
            result+=preQuery+"\n";
        }
        log.info("Created query : "+result);
        return result;
        
    }


    private String replaceDoubleParams(String preQuery) {
        Pattern pattern = Pattern.compile(doublePattern);
        Matcher matcher = pattern.matcher(preQuery);
        if(matcher.find()){
          String[] matches =  matcher.group().split(" ");
          for(String param : matches){
             String[] params = param.split("\\-");
             int low = Integer.valueOf(params[0].replaceAll("\\D", ""));
             int high = Integer.valueOf(params[1].replaceAll("\\D", ""));
             preQuery = preQuery.replaceFirst(doublePattern,  generateDouble(low, high));
          }
        }
        return preQuery;
    }


    private String generateDouble(int low, int high) {
        return String.format(Locale.ENGLISH,"%.2f",low+random.nextDouble()*(high-low));
    }


    private String replaceIntParams(String preQuery) {
        Pattern pattern = Pattern.compile(intPattern);
        Matcher matcher = pattern.matcher(preQuery);
        if(matcher.find()){
          String[] matches =  matcher.group().split(" ");
          for(String param : matches){
             String[] params = param.split("\\-");
             int low = Integer.valueOf(params[0].replaceAll("\\D", ""));
             int high = Integer.valueOf(params[1].replaceAll("\\D", ""));
             preQuery = preQuery.replaceFirst(intPattern, generateInt(low, high));
          }
        }
        return preQuery;
    }
    
    private String replaceValueParams(String preQuery) {
        Pattern pattern = Pattern.compile(valuesPattern);
        Matcher matcher = pattern.matcher(preQuery);
        if(matcher.find()){
          String[] matches =  matcher.group().split(" ");
          for(String param : matches){
             String[] params = param.split("[,\\(\\)]");
             preQuery = preQuery.replaceFirst(valuesPattern, generateValue(params));
          }
        }
        return preQuery;
    }
    
    


    private String generateValue(String[] params) {
		int element = 1+random.nextInt(params.length-2);
		return params[element].trim();
	}


	private String generateInt(int low, int high) {
        return ""+(low+random.nextInt(high-low));
    }


    private String replaceStringParams(String preQuery) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(preQuery);
        if(matcher.find()){
          String[] matches =  matcher.group().split(" ");
          for(String param : matches){
             String[] params = param.split("\\-");
             int low = Integer.valueOf(params[0].replaceAll("\\D", ""));
             int high = Integer.valueOf(params[1].replaceAll("\\D", ""));
             preQuery = preQuery.replaceFirst(stringPattern, "'"+generateString(low, high)+"'");
          }
        }
        return preQuery;
    }


    private String generateString(int low, int high) {
        int countOfSymdol = Integer.valueOf(generateInt(low, high)); 
        String result = "";
        for(int i=0; i< countOfSymdol; i++){
            result+=symbols[random.nextInt(62)];
        }
        return result;
    }
    

}
