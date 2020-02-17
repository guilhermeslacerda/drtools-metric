package structures.metrics;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class MethodMetric implements Comparable<MethodMetric> {
	private String typeName;
	private String name;
	private int startLine;
	private int endLine;
	private int numOfParameters = 0;
	private int cyclo = 0;
	private int calls = 0;
	private int nestedBlockDepth = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLoc() {
		return getEndLine() - getStartLine();
	}

	public int getNumOfParameters() {
		return numOfParameters;
	}

	public void setNumOfParameters(int numOfParameters) {
		this.numOfParameters = numOfParameters;
	}

	public int getCyclo() {
		return cyclo;
	}

	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	@Override
	public int compareTo(MethodMetric other) {
		CompareToBuilder buider = new CompareToBuilder();

		return buider.append(this.getCyclo(), other.getCyclo())
				.append(this.getNestedBlockDepth(), other.getNestedBlockDepth())
				.append(this.getLoc(), other.getLoc())
				.append(this.getCalls(), other.getCalls())
				.append(this.getNumOfParameters(), other.getNumOfParameters())
				.append(other.getName(), this.getName()).toComparison();
	}

	public int getCalls() {
		return calls;
	}

	public void setCalls(int calls) {
		this.calls = calls;
	}

	public int getNestedBlockDepth() {
		return nestedBlockDepth > 0 ? nestedBlockDepth : 0;
	}

	public void setNestedBlockDepth(int nestedBlockDepth) {
		this.nestedBlockDepth = nestedBlockDepth;
	}
}
