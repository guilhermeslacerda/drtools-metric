package javaProject.com.model;

public abstract class Human implements Person {

	protected String name;

	public Human(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
