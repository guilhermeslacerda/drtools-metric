package structures.metrics;

public class NamespaceMetric implements Comparable<NamespaceMetric> {
	private String name;
	private int numOfTypes = 1;
	private int numOfAbstractTypes = 0;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfTypes() {
		return numOfTypes;
	}

	public void setNumOfTypes(int numOfTypes) {
		this.numOfTypes = numOfTypes;
	}

	public void addNumOfTypes() {
		this.numOfTypes++;
	}
	
	public int getNumOfAbstractTypes() {
		return numOfAbstractTypes;
	}

	public void setNumOfAbstractTypes(int numOfAbstractTypes) {
		this.numOfAbstractTypes = numOfAbstractTypes;
	}

	@Override
	public int compareTo(NamespaceMetric other) {
		if (this.getNumOfTypes() < other.getNumOfTypes())
			return -1;
		if (this.getNumOfTypes() > other.getNumOfTypes())
			return 1;
		return 0;
	}
}
