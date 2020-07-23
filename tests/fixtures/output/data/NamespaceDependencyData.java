package fixtures.output.data;

public class NamespaceDependencyData { //implements Comparable<NamespaceDependencyData> {
	private String source;
	private String target;
	
	public NamespaceDependencyData(String source, String target) {
		this.source = source;
		this.target = target;
	}
	
	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}

//	@Override
//	public int compareTo(NamespaceDependencyData other) {
//		if (this.getTarget().compareToIgnoreCase(other.getTarget()) > 1)
//			return 1;
//		if (this.getTarget().compareToIgnoreCase(other.getTarget()) < 0)
//			return -1;
//		return 0;
//	}
}
