package fixtures.output.data;

public class CyclicDependencyData {
	private String from;
	private String to;
	private int number;

	public CyclicDependencyData(String from, String to, int number) {
		this.from = from;
		this.to = to;
		this.number = number;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public int getNumber() {
		return number;
	}
}
