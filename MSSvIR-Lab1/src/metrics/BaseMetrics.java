package metrics;

public abstract class BaseMetrics {
	protected String sourceCode;
	
	private final String inLineCommentRegExp = "//.+";
	private final String unixCommentRegExp = "#.*;";
	private final String multiLineCommentRegExp = "\\/\\*[\\w\\W]*?\\*\\/";

	public BaseMetrics(String sourceCode){
		this.sourceCode = sourceCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	protected void removeComments(){
		removeUNIXComments();
		removeInLineComments();
		removeMultiLineComments();
	}
	
	private void removeInLineComments() {
		this.sourceCode=this.sourceCode.replaceAll(inLineCommentRegExp, "");
	}

	private void removeMultiLineComments() {
		this.sourceCode=this.sourceCode.replaceAll(multiLineCommentRegExp, "");
	}

	private void removeUNIXComments(){
		this.sourceCode=this.sourceCode.replaceAll(unixCommentRegExp, "");
	}
}
