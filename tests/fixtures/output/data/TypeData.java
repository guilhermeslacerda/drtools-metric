package fixtures.output.data;

public class TypeData {
	private String type;
	private int sloc;
	private int nom;
	private int npm;
	private int wmc;
	private int dep;
	private int iDep;
	private int fanIn;
	private int fanOut;
	private int noa;
		
	public TypeData(String type, int sloc, int nom, int npm, int wmc, int dep, int iDep, int fanIn, int fanOut, int noa) {
		this.type = type;
		this.sloc = sloc;
		this.nom = nom;
		this.npm = npm;
		this.wmc = wmc;
		this.dep = dep;
		this.iDep = iDep;
		this.fanIn = fanIn;
		this.fanOut = fanOut;
		this.noa = noa;
	}

	public String getType() {
		return type;
	}

	public int getSloc() {
		return sloc;
	}

	public int getNom() {
		return nom;
	}

	public int getNpm() {
		return npm;
	}

	public int getWmc() {
		return wmc;
	}

	public int getDep() {
		return dep;
	}

	public int getiDep() {
		return iDep;
	}

	public int getFanIn() {
		return fanIn;
	}

	public int getNoa() {
		return noa;
	}

	public int getFanOut() {
		return fanOut;
	}
}
