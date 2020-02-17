package fixtures.output.data;

public class TypeResonanceData {
	private String type;
	private int sloc;
	private int wmc;
		
	public TypeResonanceData(String type, int sloc, int wmc) {
		this.type = type;
		this.sloc = sloc;
		this.wmc = wmc;
	}

	public String getType() {
		return type;
	}

	public int getSloc() {
		return sloc;
	}

	public int getWmc() {
		return wmc;
	}
}
