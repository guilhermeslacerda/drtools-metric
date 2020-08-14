package javaProject.com.controller;

import javaProject.com.model.Child;
import javaProject.com.model.Man;
import javaProject.com.model.Person;
import javaProject.com.model.Woman;


public class Dispatcher {
	private static int LENGTH = 4;
	private Person[] people;
	private int index = 0;
	
	public Dispatcher() {
		people = new Person[LENGTH];
		people[0] = new Man("Jonh");
		people[1] = new Woman("Mary");
		people[2] = new Man("Peter");
		people[3] = new Child();
	}

	public boolean hasNext() {
		return index < LENGTH;
	}

	public String next() {
		String name = people[index].getName();
		index++;
		// returns the next person
		return name;
	}
	
	public void foo(@parameterId("id") int id) {
		
	}
}