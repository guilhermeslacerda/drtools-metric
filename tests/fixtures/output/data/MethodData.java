package fixtures.output.data;

public class MethodData {
	private String method;
	private int loc;
	private int cyclo;
	private int calls;
	private int nbd;
	private int param;
	
	public MethodData(String method, int loc, int cyclo, int calls, int nbd, int param) {
		this.method = method;
		this.loc = loc;
		this.cyclo = cyclo;
		this.calls = calls;
		this.nbd = nbd;
		this.param = param;
	}

	public String getMethod() {
		return method;
	}

	public int getLoc() {
		return loc;
	}

	public int getCyclo() {
		return cyclo;
	}

	public int getCalls() {
		return calls;
	}

	public int getNbd() {
		return nbd;
	}

	public int getParam() {
		return param;
	}
}
