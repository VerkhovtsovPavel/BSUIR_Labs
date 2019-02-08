package metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.FileUtils;
import utils.KeyComparatorLow;
import elements.Module;
import elements.Variable;

public class McClure extends BaseMetrics{
	
	private final String javaOwnMethodRegExp = "((private)|(public)|(protected))( static)* [<>\\w ]+\\w+\\(.*\\)";
	private final String controlKeyWordRegExp ="((for)|(while)|(if)|(switch)).*";
	private final String methodParamRegExp = "((\\w+\\()).*";
	private final String importClassRegExp = "import (?!static)[\\w\\.]+"; 
	
	private HashMap<String, String> modules = new HashMap<String, String>();
	private HashMap<String, Module> modulesComplexity = new HashMap<>();
	private ArrayList<String> visitedModules = new ArrayList<>();
	
	private TreeSet<String> variablesTypes = new TreeSet<String>(new KeyComparatorLow());
	
	private int maxLevel;
	private String javaVariableRegExp = "[ \\t(](%s).*";
	

	public McClure(String sourceCodeDirectory) {
		super(FileUtils.getSelfCode(sourceCodeDirectory));
	}

	public void analyzeCode() {
		preparation();
		buildCallTree("main", 0);
		printTotalCallComplexity();
		calculateComplexityGlobalVariable();
	}

	private void calculateComplexityGlobalVariable() {
		findGlobalVariable();
		calculateComplexity();
		printComplexityGlobalVariables();
		
	}

	private void printComplexityGlobalVariables() {
		// TODO Auto-generated method stub
		
	}

	private void calculateComplexity() {
		// TODO Auto-generated method stub
		
	}

	private void findGlobalVariable() {
		// TODO Auto-generated method stub
		
	}

	private void fillVariableTypes(){
		addStandartTypes();
		addImportTypes();
		createVaraibleRegExp();
	}
	
	private void createVaraibleRegExp() {
		String variable="";
		for(String type: variablesTypes){
			variable+="("+type+")|";
		}
		variable = variable.substring(0, variable.lastIndexOf("|"));
		this.javaVariableRegExp=String.format(javaVariableRegExp, variable);
	}

	private void addStandartTypes() {
		variablesTypes.addAll(FileUtils.readFromFileToList("resources/JAVA/javaStandartTypes.txt"));
	}

	private void addImportTypes() {
		Pattern functionPattern = Pattern.compile(importClassRegExp);
		Matcher matcher = functionPattern.matcher(sourceCode);
		while (matcher.find()) {
			variablesTypes.add(parseImportClassName(matcher.group()));
		}
	}
	
	private String parseImportClassName(String fullImport){
		String[] fullImportPath = fullImport.split("\\.");
		return fullImportPath[fullImportPath.length-1];
	}
	
	private void printTotalCallComplexity() {
		int totalComplexity = 0;
		for(String module : modules.keySet()){
			Module currentModule = modulesComplexity.get(module);
			totalComplexity+=currentModule.modulesBefore*currentModule.callComplexity;
			totalComplexity+=currentModule.modulesAfter*currentModule.manageComplexity;
		}
		System.out.println("Total complexity = "+totalComplexity);
	}
	
	private void buildCallTree(String startModule, int level){
		findMaxLevel(level);
		visitedModules.add(startModule);
		modulesComplexity.get(startModule).callComplexity = level;
		modulesComplexity.get(startModule).modulesBefore++;
		String moduleCode = modules.get(startModule);
		calculateVariables(moduleCode, startModule);
		for(String moduleName : modules.keySet()){
			if(moduleCode.contains(moduleName)&& !visitedModules.contains(moduleName)){
				moduleCode.replaceFirst(moduleName, "");
				modulesComplexity.get(startModule).modulesAfter++;
				buildCallTree(moduleName, level++);
			}
		}
		modulesComplexity.get(startModule).manageComplexity=maxLevel-level;
	}
	
	private void printVariable(HashMap<String, Variable> localVariable, String module) {
		for(String variable : localVariable.keySet()){
			Variable currentVariable = localVariable.get(variable);
			float variableComplexity = (float)currentVariable.complexityMeasure*currentVariable.scope/module.length();
			System.out.println(module+"."+variable+" ="+variableComplexity);
		}
	}

	private void calculateVariables(String code, String moduleName) {
		HashMap<String, Variable> localVariable = new HashMap<>();
		ArrayList<String> allVariablesInModule = findAllVariable(code);
		initLocalVariableMap(localVariable, allVariablesInModule);
		Pattern functionPattern = Pattern.compile(controlKeyWordRegExp);
		Matcher matcher = functionPattern.matcher(code);
		while (matcher.find()) {
			checkControlVariables(matcher.group(), localVariable);
		}
		
		functionPattern = Pattern.compile(methodParamRegExp);
		matcher = functionPattern.matcher(code);
		while (matcher.find()) {
			checkMethodsParams(matcher.group(), localVariable);
		}
		printVariable(localVariable, moduleName);
	}

	private void checkMethodsParams(String controlKeyWord, HashMap<String, Variable> allVariablesInModule) {
		for(String variable : allVariablesInModule.keySet()){
			if(controlKeyWord.contains(variable)){
				allVariablesInModule.get(variable).scope++;
			}
		}
	}

	private void initLocalVariableMap(HashMap<String, Variable> localVariable, ArrayList<String> allVariablesInModule) {
		for(String variable : allVariablesInModule){
			localVariable.put(variable, new Variable());
		}	
		
	}

	private void checkControlVariables(String controlKeyWord, HashMap<String, Variable> allVariablesInModule) {
		for(String variable : allVariablesInModule.keySet()){
			if(controlKeyWord.contains(variable)){
				allVariablesInModule.get(variable).complexityMeasure++;
			}
		}
		
	}

	private ArrayList<String> findAllVariable(String code) {
		ArrayList<String> result = new ArrayList<>();
		Pattern functionPattern = Pattern.compile(javaVariableRegExp);
		Matcher matcher = functionPattern.matcher(code);
		while (matcher.find()) {
			result.addAll(parseVariable(matcher.group()));
		}
		return result;
	}

	private ArrayList<String> parseVariable(String fullVariablesDeclaration) {
		fullVariablesDeclaration = removeInitialization(fullVariablesDeclaration);
		
		for(String type: variablesTypes){
			fullVariablesDeclaration = fullVariablesDeclaration.replace(type, "");
		}
		
		String[] variables = fullVariablesDeclaration.split("[,:]");
		
		ArrayList<String> result = new ArrayList<String>();
		
		for(String variable: variables){
			result.add(variable.trim());
		}
		return result;
		
	}
	
	
	private String removeInitialization(String fullVariablesDeclaration) {
		if(fullVariablesDeclaration.contains("="))
		{
			fullVariablesDeclaration = fullVariablesDeclaration.substring(0, fullVariablesDeclaration.lastIndexOf("="));
		}
		
		if(fullVariablesDeclaration.contains(".")){
			fullVariablesDeclaration = fullVariablesDeclaration.substring(0, fullVariablesDeclaration.lastIndexOf("."));
		}
		 return fullVariablesDeclaration.replace("[\\[\\]\\(\\)]", " ");
	}

	

	private void findMaxLevel(int currenLevel){
		this.maxLevel = currenLevel<maxLevel ? maxLevel : currenLevel; 
	}

	private void disassembledSourceCodeToModules() {
		Pattern functionPattern = Pattern.compile(javaOwnMethodRegExp);
		Matcher matcher = functionPattern.matcher(sourceCode);
		
		int counter = 1; 
		String[] modulesArray = sourceCode.split(javaOwnMethodRegExp);
		
		while (matcher.find()) {
			modules.put(parseMethodName(matcher.group()), modulesArray[counter]);
			modulesComplexity.put(parseMethodName(matcher.group()), new Module());
			counter++;
		}
	}



	private String parseMethodName(String fullMethodDeclaration) {
		String[] fullMethodName = fullMethodDeclaration.substring(0, fullMethodDeclaration.lastIndexOf("(")).trim().split(" ");
		String methodName = fullMethodName[fullMethodName.length - 1];
		return methodName.replace(".", "");
	}

	private void preparation() {
		fillVariableTypes();
		removeUnnecessaryElements();
		disassembledSourceCodeToModules();
	}
}
