package metrics;

public abstract class BaseMetrics {
	private String sourceCode;

	public BaseMetrics(String sourceCode){
		this.setSourceCode(sourceCode);
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
}
