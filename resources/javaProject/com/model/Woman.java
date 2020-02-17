package javaProject.com.model;

public class Woman extends Human {

	public Woman(String name) {
		super(name);
	}

	public String getName() {
		return "Mrs. " + super.getName();
	}

	public void foo() {
		boolean flag = false;
		if (true)
			flag = true;
		
		for (int x=0; x < 10; x++) {
		}
			
	}
}
