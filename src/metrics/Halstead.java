package metrics;

import static utils.MapUtils.addToMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import utils.FileUtils;
import utils.KeySizeComparator;

public class Halstead extends BaseMetrics {

	private HashMap<String, Integer> statementsMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> operandsMap = new HashMap<String, Integer>();
	
	private int statementCount;
	private int uniqueStatementCount;

	public Halstead(String sourceCodeFile) {
		super(FileUtils.readFromFileToString(sourceCodeFile));
	}

	public void analyzeCode() {
		preparation();
		parseCode();
		printResult();
	}

	private void printResult() {
		System.out.println("Unique statement count = "+uniqueStatementCount);
		System.out.println("Statement count = "+statementCount);
	}

	private void preparation() {
		preparationCode();
		initializationStatementsMap("resources/phpStatements.txt");
		sortStatementMapByKeySize();
	}

	private void preparationCode() {
		removeComments();
		removeKeyWords("resources/phpKeywords.txt");
	}

	private void removeKeyWords(String fileName) {
		ArrayList<String> keyWords = FileUtils.readFromFileToList(fileName);
		for (String keyWord : keyWords) {
			this.sourceCode=sourceCode.replaceAll(keyWord, "");
		}
	}

	private void parseCode() {
		parseStatements();
	}

	private void parseStatements() {
		findAllStatements();
		removeNotUsedStatement();
		calculateStatements();
	}

	private void calculateStatements() {
		uniqueStatementCount=statementsMap.size();
		statementCount = calculateStatementCount();
	}

	private int calculateStatementCount() {
		int result =0;
		for (Entry<String, Integer> entry : statementsMap.entrySet()) {
			result+=entry.getValue();
		}
		return result;
	}

	private void findAllStatements() {
		for (Entry<String, Integer> entry : statementsMap.entrySet()) {
			String statement = entry.getKey();
			while (sourceCode.contains(statement)) {
				this.sourceCode=sourceCode.replace(statement, "");
				addToStatementMap(statement);
			}
		}
	}

	private void removeNotUsedStatement() {
		for (Entry<String, Integer> entry : statementsMap.entrySet()) {
			if (entry.getValue() == 0) {
				statementsMap.remove(entry.getKey());
			}
		}
	}

	private void initializationStatementsMap(String fileName) {
		ArrayList<String> statements = FileUtils.readFromFileToList(fileName);
		for (String statement : statements) {
			statementsMap.put(statement, 0);
		}

		// TODO Add in equalsStatement map all possible statements from file

	}

	private String getNextWord() {
		// TODO Return next word from source code(provide for {';' and ',' and
		// etc.)
		return null;
	}

	private void addToStatementMap(String key) {
		addToMap(key, statementsMap);
	}

	private void addToOperandMap(String key) {
		addToMap(key, operandsMap);
	}

	// TODO Remove all characters don't relevant under the notion of an
	// statement or operand.
	// TODO Remove next line characters. Add space before ';' and etc.

	private void sortStatementMapByKeySize() {
		ArrayList<Entry<String, Integer>> statementList = new ArrayList<Entry<String, Integer>>(statementsMap.entrySet());
		Collections.sort(statementList, new KeySizeComparator());
	}

}
