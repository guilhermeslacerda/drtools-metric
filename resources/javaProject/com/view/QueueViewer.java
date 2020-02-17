package javaProject.com.view;

import javaProject.com.controller.Dispatcher;

public class QueueViewer {
	
	/**
	 * Project sample to read by softwarepathfinder
	 * @param args
	 */
	public static void main(String[] args) {
		Dispatcher dispatcher = new Dispatcher();
		
		while(dispatcher.hasNext()) {
			System.out.println("The next is " + dispatcher.next());
		}
	}
}