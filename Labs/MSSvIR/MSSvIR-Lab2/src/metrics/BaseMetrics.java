package metrics;

public abstract class BaseMetrics {
	protected String sourceCode;

	private final String inLineCommentRegExp = "//.+";
	private final String unixCommentRegExp = "#.*;";
	private final String multiLineCommentRegExp = "\\/\\*[\\w\\W]*?\\*\\/";
	private final String htmlTagRegExp = "(<\\?php)|(\\?>)|(<[\\?\\w*\\s*\\/]+>)";
	private final String importAndPackageAndClassRegExp = "(package.*)|(import.*)|((public|private|protected) class.*)";
	private final String annotationRegExp = "@.*";
	private final String openingAndClosingBracesRegExp = "\\{|\\}";
	private final String typingRegExp ="\\<[\\w ,]*\\>";

	public BaseMetrics(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	protected void removeUnnecessaryElements() {
		removeComments();
		removeOtherUnnecessaryElements();
	}
	
	private void removeComments(){
		removeUNIXComments();
		removeInLineComments();
		removeMultiLineComments();
	}
	
	private void removeOtherUnnecessaryElements(){
		removeHTMLTags();
		removePackageAndImport();
		removeAnnotations();
		removeOpeningAndClosingBraces();
		removeTyping();
	}
	
	private void removeTyping(){
		this.sourceCode = this.sourceCode.replaceAll(typingRegExp, "");
	}

	private void removeInLineComments() {
		this.sourceCode = this.sourceCode.replaceAll(inLineCommentRegExp, "");
	}

	private void removeMultiLineComments() {
		this.sourceCode = this.sourceCode.replaceAll(multiLineCommentRegExp, "");
	}

	private void removeHTMLTags() {
		sourceCode = sourceCode.replaceAll(htmlTagRegExp, "");
	}

	private void removeUNIXComments() {
		this.sourceCode = this.sourceCode.replaceAll(unixCommentRegExp, "");
	}

	private void removePackageAndImport() {
		this.sourceCode = this.sourceCode.replaceAll(importAndPackageAndClassRegExp, "");
	}

	private void removeAnnotations() {
		this.sourceCode = this.sourceCode.replaceAll(annotationRegExp, "");
	}
	
	private void removeOpeningAndClosingBraces(){
		this.sourceCode = this.sourceCode.replaceAll(openingAndClosingBracesRegExp, "");
	}
	
	public abstract void analyzeCode();
}
