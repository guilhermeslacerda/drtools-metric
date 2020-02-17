package fixtures.output.data;

public class NamespaceCouplingData {
	private String namespace;
	private int ca;
	private int ce;
	private double instability;
	private double abstractness;
	private double distance;
	
	public NamespaceCouplingData(String namespace, int ca, int ce, double instability, double abstractness, double distance) {
		this.namespace = namespace;
		this.ca = ca;
		this.ce = ce;
		this.instability = instability;
		this.abstractness = abstractness;
		this.distance = distance;
	}

	public String getNamespace() {
		return namespace;
	}

	public int getCa() {
		return ca;
	}

	public int getCe() {
		return ce;
	}

	public double getInstability() {
		return instability;
	}

	public double getAbstractness() {
		return abstractness;
	}

	public double getDistance() {
		return distance;
	}
}
