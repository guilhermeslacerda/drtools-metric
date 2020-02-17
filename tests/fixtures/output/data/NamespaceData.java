package fixtures.output.data;

public class NamespaceData {
	private String name;
	private int noc;
	private int nac;
	
	public NamespaceData(String name, int noc, int nac) {
		this.name = name;
		this.noc = noc;
		this.nac = nac;
	}

	public String getName() {
		return name;
	}

	public int getNoc() {
		return noc;
	}

	public int getNac() {
		return nac;
	}
}
