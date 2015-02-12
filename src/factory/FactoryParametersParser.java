package factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FactoryParametersParser {

	String getParams(String request) {
		if(request.contains("sweet")){
			return getSweetParams(request);
		}
		else{
			return getWrappingParams(request);
		}
	}
	
	String getObjectType(String request){
		if(request.contains("sweet")){
			return "Sweet";
		}
		else{
			return "Wrapping";
		}
	}
	
	String getWrappingParams(String request){
		String params ="%s %s";
		Pattern functionPattern = Pattern.compile("\\w+ patterns");
		Matcher matcher = functionPattern.matcher(request);
		String pattern = matcher.group().split("[ \\t]+")[0];
		
		functionPattern = Pattern.compile("\\w+ color");
		matcher = functionPattern.matcher(request);
		String color = matcher.group().split("[ \\t]+")[0];
		
		return String.format(params, pattern, color);
	}
	
	String getSweetParams(String request){
		if(request.contains("max")){
			return "100";
		}else{
			Pattern functionPattern = Pattern.compile("/sweetness \\d+/g");
			Matcher matcher = functionPattern.matcher(request);
			return matcher.group().split("[ \\t]+")[1];
		}
	}

}
