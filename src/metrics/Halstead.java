package metrics;

import static utils.MapUtils.addToMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.FileUtils;
import utils.KeyComparatorHigh;
import utils.MapUtils;
import java.lang.String;
import java.lang.Integer;

public class Halstead extends BaseMetrics {

	private final String replateTo = " ";

	private final String javaVariableRegExp = "[ \\t][a-z]+([A-Z][a-z]+)*";
	private final String javaOwnMethodRegExp = "((private)|(public)|(protected))( static)* [<>\\w ]+\\w+\\(.*\\)";
	private final String standardMethodRegExp = "\\.\\w*\\(";
	private final String magicNumberAndConstStringRegExp = "[0-9]|(\".*\")";
	
	private ArrayList<String> importedClasses = new ArrayList<String>();

	private TreeMap<String, Integer> statementsMap = new TreeMap<String, Integer>(new KeyComparatorHigh());
	private TreeMap<String, Integer> operandsMap = new TreeMap<String, Integer>(new KeyComparatorHigh());

	private int statementCount;
	private int uniqueStatementCount;

	private int operandsCount;
	private int uniqueOperandsCount;

	private int halsteadProgramVocabulary;
	private int halsteadProgramLength;
	private int halsteadProgramVolume;
	private int halsteadDifficulty;
	private int halsteadEffort;

	public Halstead(String sourceCodeDirectory) {
		super(FileUtils.getSelfCode(sourceCodeDirectory));
	}

	@Override
	public void analyzeCode() {
		preparation();
		parseCode();
		calculateResult();
		printResult();
	}

	private void printResult() {

		System.out.println("Halstead Program Vocabulary = " + halsteadProgramVocabulary);
		System.out.println("Halstead Program Length = " + halsteadProgramLength);
		System.out.println("Halstead Program Volume = " + halsteadProgramVolume);
		System.out.println("Halstead Difficulty = " + halsteadDifficulty);
		System.out.println("Halstead Effort = " + halsteadEffort);

	}

	private void calculateResult() {

		halsteadProgramVocabulary = uniqueOperandsCount + uniqueStatementCount;
		halsteadProgramLength = statementCount + operandsCount;
		halsteadProgramVolume = halsteadProgramLength * (int) (Math.log(halsteadProgramLength) / Math.log(2));
		halsteadDifficulty = (uniqueStatementCount / 2) * (operandsCount / uniqueOperandsCount);
		halsteadEffort = halsteadDifficulty * halsteadProgramVolume;

	}

	private void addOwnMethodsInStatementMap() {
		Pattern functionPattern = Pattern.compile(javaOwnMethodRegExp);
		Matcher matcher = functionPattern.matcher(sourceCode);
		while (matcher.find()) {
			addToStatementMap(parseMethodName(matcher.group()));
		}
		sourceCode = matcher.replaceAll(replateTo);
	}

	private void addImportedClassesInList() {
		Pattern functionPattern = Pattern.compile("import (?!static)[\\w\\.]+");
		Matcher matcher = functionPattern.matcher(sourceCode);
		while (matcher.find()) {
			importedClasses.add(parseImportClassName(matcher.group()));
		}
	}
	
	private String parseImportClassName(String fullImport){
		String[] fullImportPath = fullImport.split("\\.");
		return fullImportPath[fullImportPath.length-1];
	}
	
	private void removeImportedClasses(){
		for(String importedClass:importedClasses){
			this.sourceCode = this.sourceCode.replaceAll(importedClass, replateTo);
		}
	}

	private void addStandardMethodsInStatementMap() {
		Pattern functionPattern = Pattern.compile(standardMethodRegExp);
		Matcher matcher = functionPattern.matcher(sourceCode);
		while (matcher.find()) {
			addToStatementMap(parseMethodName(matcher.group()));
		}
		sourceCode = matcher.replaceAll(replateTo);
	}

	private String parseMethodName(String fullMethodDeclaration) {
		String[] fullMethodName = fullMethodDeclaration.substring(0, fullMethodDeclaration.lastIndexOf("(")).trim().split(" ");
		String methodName = fullMethodName[fullMethodName.length - 1];
		return methodName.replace(".", "");
	}

	private void preparation() {
		addImportedClassesInList();
		initializationStatementsMap("resources/JAVA/javaStatements.txt");
		addOwnMethodsInStatementMap();
		addStandardMethodsInStatementMap();
		preparationCode();
	}

	private void preparationCode() {
		removeUnnecessaryElements();
		removeKeyWords("resources/JAVA/javaKeywords.txt");
		removeMagicNumberAndConstString();
		removeImportedClasses();
	}

	private void removeKeyWords(String fileName) {
		ArrayList<String> keyWords = FileUtils.readFromFileToList(fileName);
		for (String keyWord : keyWords) {
			this.sourceCode = sourceCode.replaceAll(String.format("[ (]*%s[\\. )]", keyWord), replateTo);
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
		Pattern variablePattern = Pattern.compile(javaVariableRegExp);
		Matcher matcher = variablePattern.matcher(sourceCode);
		while (matcher.find()) {
			addToOperandMap(matcher.group());
		}
		sourceCode = matcher.replaceAll(replateTo);
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
		Pattern statementPattern;
		Matcher statementMatcher;
		for (String statement : statementsMap.keySet()) {
			statementPattern = Pattern.compile(statement);
			statementMatcher = statementPattern.matcher(sourceCode);
			while (statementMatcher.find()) {
				addToStatementMap(statement);
			}
			this.sourceCode = statementMatcher.replaceAll(replateTo);
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

	private void removeMagicNumberAndConstString() {
		this.sourceCode = this.sourceCode.replaceAll(magicNumberAndConstStringRegExp, replateTo);
	}

}
