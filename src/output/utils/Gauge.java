package output.utils;

import output.MetricOutput;
import output.MetricResultConsole;

public class Gauge {
	private static MetricOutput outputGauge;
	public static void start(MetricOutput output) {
		outputGauge = output;
		if (outputGauge instanceof MetricResultConsole) 
			System.out.print("wait... ");
	}
	
	public static void progress(int number) {
		if (outputGauge instanceof MetricResultConsole) {
			if (number % 2 == 0) System.out.print("|\b");
			else System.out.print("-\b");
		}
	}
	
	public static void end() {
		if (outputGauge instanceof MetricResultConsole) { 
			System.out.print("\r");
			System.out.print("               \r");
		}
	}
}
