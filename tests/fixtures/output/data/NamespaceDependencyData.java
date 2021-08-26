package fixtures.output.data;

public class NamespaceDependencyData { 
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
}
