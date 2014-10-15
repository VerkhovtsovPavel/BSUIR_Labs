package metrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import static utils.MapUtils.addToMap;


public class Halstead extends BaseMetrics{
	
	private HashMap<String, Integer> statementsMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> operandsMap = new HashMap<String, Integer>();
	private String sourceCode;

	public Halstead(String sourceCode) {
		super(sourceCode);
		this.sourceCode=preparationOfTheCode(super.getSourceCode());
		// TODO Maybe make find protected
	}
	
	public void analyzeCode(){
		initializationStatementsMap("resources/phpStatments.txt");
		parseCode();
	}
	
	private void parseCode(){
		String currentWord = getNextWord();
		if(statementsMap.containsKey(currentWord)){
			addToStatementMap(currentWord);
		}
		else{
			addToOperandMap(currentWord);
		}
	}
	
	private void initializationStatementsMap(String fileName){
		File file = new File(fileName);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					statementsMap.put(lineFromFile,0);
				}
			}finally{
				in.close();
			}
		}catch (IOException e) {
			//TODO Add handling
			e.printStackTrace();
		}
		//TODO Add in equalsStatement map all possible statements from file
		
	}
	
	private String getNextWord(){
		//TODO Return next word from source code(provide for {';' and ',' and etc.)
		return null;
	}
	
	private void addToStatementMap(String key){
		addToMap(key, statementsMap);
	}
	
	private void addToOperandMap(String key){
		addToMap(key, operandsMap);
	}
	
	private String preparationOfTheCode(String sourceCode){
		return sourceCode;
		//TODO Remove all characters don't relevant under the notion of an statement or operand.
		//TODO Remove next line characters. Add space before ';' and etc.
	}
	
	
	
}
