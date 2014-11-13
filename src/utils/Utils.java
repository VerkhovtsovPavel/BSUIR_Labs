package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

	public static ArrayList<String> makeList(String string) {
		String[] strings = string.split("[,+ +]+");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < strings.length; i++) {
			result.add(strings[i]);
		}
		return result;
	}
	
	public static HashMap<String,String> getConfigFromFile(){
		HashMap<String, String> result = new HashMap<>();
		File file = new File("connection.config");

		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					String[] currentParam = lineFromFile.split(":[\t ]+");
					if(currentParam.length>2){
						System.out.println("Error while parse string: "+lineFromFile);
					}
					else{
						result.put(currentParam[0], currentParam[1]);
					}
				}
			}finally{
				in.close();
			}
		}catch (IOException e) {
			System.out.println("Error while read file "+file.getAbsolutePath());
		}
		return result;
	}
}
