package metrics;

import static utils.MapUtils.addToMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.CopyOfKeySizeComparator;
import utils.FileUtils;
import utils.KeySizeComparator;
import utils.MapUtils;

public class Halstead extends BaseMetrics {

	private SortedMap<String, Integer> statementsMap = new TreeMap<String, Integer>(new CopyOfKeySizeComparator());
	private HashMap<String, Integer> operandsMap = new HashMap<String, Integer>();

	private int statementCount;
	private int uniqueStatementCount;

	private int operandsCount;
	private int uniqueOperandsCount;

	public Halstead(String sourceCodeFile) {
		super(FileUtils.readFromFileToString(sourceCodeFile));
	}

	public void analyzeCode() {
		preparation();
		parseCode();
		printResult();
	}

	private void printResult() {
		System.out.println("Unique statement count = " + uniqueStatementCount);
		System.out.println("Statement count = " + statementCount);
		System.out.println("Unique operands count = " + uniqueOperandsCount);
		System.out.println("Operands count = " + operandsCount);
	}

	private void preparation() {
		preparationCode();
		initializationStatementsMap("resources/phpStatements.txt");
		//sortStatementMapByKeySize();
	}

	private void preparationCode() {
		removeComments();
		removeHTMLTags();
		removeKeyWords("resources/phpKeywords.txt");
	}

	private void removeHTMLTags() {
		sourceCode =sourceCode.replaceAll("<[\\?\\w*\\s*\\/]+>","");
		sourceCode =sourceCode.replaceAll("(<\\?php)|(\\?>)","");
	}

	private void removeKeyWords(String fileName) {
		ArrayList<String> keyWords = FileUtils.readFromFileToList(fileName);
		for (String keyWord : keyWords) {
			this.sourceCode = sourceCode.replaceAll(keyWord, "");
		}
	}

	private void parseCode() {
		parseStatements();
		parseOperands();
	}

	private void parseOperands() {
		findAllOperands();
		calculateOperands();
	}

	private void calculateOperands() {
		uniqueOperandsCount = operandsMap.size();
		operandsCount = MapUtils.findSumAllValuesInMap(operandsMap);
	}

	private void findAllOperands() {
		Pattern variablePattern = Pattern.compile("\\$[\\w\\d]+");
		Matcher matcher = variablePattern.matcher(sourceCode);
		while(matcher.find()) {
			addToOperandMap(matcher.group());
		}
		sourceCode = matcher.replaceAll("");
	}
	

	private void parseStatements() {
		findAllStatements();
		removeNotUsedStatement();
		calculateStatements();
	}

	private void calculateStatements() {
		uniqueStatementCount = statementsMap.size();
		statementCount = MapUtils.findSumAllValuesInMap(statementsMap);
	}

	private void findAllStatements() {
		for (Entry<String, Integer> entry : statementsMap.entrySet()) {
			String statement = entry.getKey();
			while (sourceCode.contains(statement)) {
				this.sourceCode = sourceCode.replaceFirst(statement, "");
				addToStatementMap(statement);
			}
		}
	}

	private void removeNotUsedStatement() {
		Set<String> keysToRemove = new HashSet<String>();
		for (Entry<String, Integer> entry : statementsMap.entrySet()) {
			if (entry.getValue() == 0) {
				keysToRemove.add(entry.getKey());
			}
		}

		for (String removedKey : keysToRemove) {
			statementsMap.remove(removedKey);
		}
	}

	private void initializationStatementsMap(String fileName) {
		ArrayList<String> statements = FileUtils.readFromFileToList(fileName);
		for (String statement : statements) {
			statementsMap.put(statement, 0);
		}
	}

	private void addToStatementMap(String key) {
		addToMap(key, statementsMap);
	}

	
	private void addToOperandMap(String key) {
		addToMap(key, operandsMap);
	}

	private void sortStatementMapByKeySize() {
		SortedMap<String, Integer> mapToSort = new TreeMap<>(new CopyOfKeySizeComparator());
		mapToSort.putAll(statementsMap);
	/*	ArrayList<Entry<String, Integer>> statementList = new ArrayList<Entry<String, Integer>>(statementsMap.entrySet());
		Collections.sort(statementList, new KeySizeComparator());*/
		
		
	}

}
