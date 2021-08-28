package main;

import java.io.File;

import output.InfoConsole;
import output.MetricOutput;
import output.MetricResultCSV;
import output.MetricResultConsole;
import output.MetricResultFile;
import output.MetricResultJSON;
import selection.ProjectInfo;

public class Bootstrapper {
	private static int number = 0;
	private static MetricOutput metricOutput;
	private static ProjectInfo project;
	private static String option;
	private static String argument;
	private static String outputFormat;

	public static void main(String[] args) {
		verifyNumberOfArguments(args);
		option = args[0];
		argument = args[1];
		outputFormat = args[2];
		runApp(args);
	}

	private static void runApp(String[] args) {
		verifyAllSituations(args);
		long startTime = 0;
		startTime = getOutputFormat(startTime);
		project = new ProjectInfo(option, metricOutput);
		verifyMetricThresholdsOption();
		verifyDevelopmentInformationOption();
		analyzeAndShow(startTime);
	}

	private static void verifyAllSituations(String[] args) {
		verifyValidDirectory();
		verifyInvalidOptions();
		verifyOptions();
		verifySaveOption();
		verifyOutput();
		verifyCouplingWithTopOption(args);
	}

	private static void analyzeAndShow(long startTime) {
		project.analyze();
		verifyUseOfTopOption();
		project.show(argument);
		InfoConsole.printElapsedTime(startTime);
	}

	private static void verifyUseOfTopOption() {
		if (number > 0)
			project.defineTop(number);
	}

	private static long getOutputFormat(long startTime) {
		if (outputFormat.equalsIgnoreCase("--csv"))
			metricOutput = new MetricResultCSV();
		else if (outputFormat.equalsIgnoreCase("--json"))
			metricOutput = new MetricResultJSON();
		else if (outputFormat.equalsIgnoreCase("--save")) {
			InfoConsole.print("Generating files to drtools-metric-visualization tool\n\n");		
			metricOutput = new MetricResultFile();
			startTime = System.currentTimeMillis();			
		}
		else {
			metricOutput = new MetricResultConsole();
			startTime = System.currentTimeMillis();
		}
		return startTime;
	}

	private static void verifyCouplingWithTopOption(String[] args) {
		if (args.length == 5) {
			String ranking = args[3];
			number = Integer.parseInt(args[4]);
			verifyTopOption(number, ranking, argument);
		}
	}

	private static void verifyMetricThresholdsOption() {
		if (argument.equalsIgnoreCase("-mt")) {
			project.show(argument);
			System.exit(0);
		}
	}

	private static void verifyDevelopmentInformationOption() {
		if (argument.equalsIgnoreCase("-i")) {
			InfoConsole.printDevelopmentInformation();
			System.exit(0);
		}
	}

	private static void verifyTopOption(int number, String ranking, String argument) {
		if (!ranking.equalsIgnoreCase("--top") && !(number < 1 || number > 500)) {
			InfoConsole.printUsage();
			System.exit(0);
		}

		if (ranking.equalsIgnoreCase("--top") && argument.equalsIgnoreCase("-nc")) {
			InfoConsole.printHeader("This option ('-nc' and '--top') is not available...");
			System.exit(0);
		}
	}

	private static void verifySaveOption() {
		if (argument.equalsIgnoreCase("-mv") && !outputFormat.equalsIgnoreCase("--save")) {
			InfoConsole.printHeader("This option ('-mv') is only available with --save output format...");
			System.exit(0);
		}

		if (!argument.equalsIgnoreCase("-mv") && outputFormat.equalsIgnoreCase("--save")) {
			InfoConsole.printHeader("This output option ('--save') is only available with -mv option...");
			System.exit(0);
		}
	}

	private static void verifyOutput() {
		if (!outputFormat.equalsIgnoreCase("--console") && !outputFormat.equalsIgnoreCase("--csv") 
				&& !outputFormat.equalsIgnoreCase("--json") && !outputFormat.equalsIgnoreCase("--save")) {
			InfoConsole.printUsage();
			System.exit(0);
		}
	}

	private static void verifyOptions() {
		String[] options = {"-a", "-s", "-n", "-t", "-m", "-d", "-cd", "-id", 
				"-nc", "-ac", "-mt", "-tc", "-i", "-mv", "-sn", "-st"};
		boolean invalidOption = true;
		
		for (String option : options) {
			if(argument.equalsIgnoreCase(option)) invalidOption = false;
		}
		if (invalidOption) {
			InfoConsole.printUsage();
			System.exit(0);
		}
	}

	private static void verifyInvalidOptions() {
		String[][] options = {{"-a", "--csv", "This option is only available in console format..."},
				{"-d", "--csv",   "This option is only available in console and JSON format..."},
				{"-id", "--csv",  "This option is only available in console and JSON format..."},
				{"-ac", "--csv",  "This option is only available in console format..."},
				{"-a", "--json",  "This option is only available in console format..."},
				{"-ac", "--json", "This option is only available in console format..."}};
		
		for (int indx = 0; indx < options.length; indx++) {
			if(argument.equalsIgnoreCase(options[indx][0]) 
					&& outputFormat.equalsIgnoreCase(options[indx][1])) { 
				InfoConsole.printHeader(options[indx][2]);
				System.exit(0);
			}
		}
	}
	
	private static void verifyValidDirectory() {
		if (!isValidDirectory(option)) {
			InfoConsole.printUsage();
			System.exit(0);
		}
	}

	private static void verifyNumberOfArguments(String[] args) {
		if ((args.length < 3 || args.length > 5) || args == null) {
			InfoConsole.printUsage();
			System.exit(0);
		}
	}

	private static boolean isValidDirectory(String option) {
		File file = new File(option);
		return file.isDirectory();
	}
}
