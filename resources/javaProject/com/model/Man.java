package javaProject.com.model;

import java.util.ArrayList;

public class Man extends Human {

	public Man(String name) {
		super(name);
	}

	public String getName() {
		return "Mr. " + super.getName();
	}

	public String searchFor(int code) {
		return null;
	}

	public String searchFor(String name) {
		return null;
	}

	public void foo() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("john");
		
		for (String name : names) {
		}
		
		boolean flag = (true)? true: false; 
		
		int option = 1;
		switch(option) {
			case 1: break;
			case 2: break;
			default: break;
		}
	}
}
