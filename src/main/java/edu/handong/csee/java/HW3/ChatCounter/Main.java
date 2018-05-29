package edu.handong.csee.java.HW3.ChatCounter;

import java.util.*;

import org.apache.commons.cli.CommandLine;

import org.apache.commons.cli.CommandLineParser;

import org.apache.commons.cli.DefaultParser;

import org.apache.commons.cli.HelpFormatter;

import org.apache.commons.cli.Option;

import org.apache.commons.cli.Options;

/**
 * This is main class
 * This will load csv and txt files, and make new list that has the name and size of the name, and print out the final result of chat count 
 * @author KKB
 */
public class Main {
	String path;
	boolean verbose;
	boolean help;
	/**
	 * This is main method and run the program
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		//main.run(args[1], args[3]);
		main.run();
	}

	/**
	 * This method (1) loads csv and txt files
	 * (2) makes a hashmap that has names, dates and messages
	 * (3) makes new list that has name and the size of name from hashmap
	 * (4) sorts the list
	 * (4) prints out the final result
	 */
	public void run(/*String input, String output*/) {

		FileLoader fileLoader = new FileLoader("C:\\git\\HW3\\chat_log");

		fileLoader.loadCSVFiles();
		fileLoader.loadTXTFiles();
		HashMap<String, ArrayList<DataAccessor>> nameAndMessage = fileLoader.getMessages();

		List<DataOfNameAndCount> chatCount = new ArrayList<DataOfNameAndCount>();

		for(String name : nameAndMessage.keySet()) {

			chatCount.add(new DataOfNameAndCount(name, nameAndMessage.get(name).size()));

		}
		

		
		Collections.sort(chatCount);
		
		System.out.println("result: ");
		for(DataOfNameAndCount chat : chatCount) 
			chat.printNameAndCount();
		FileWritter fileWritter = new FileWritter("C:\\git\\HW3\\output");
		fileWritter.makeItCSVFile(chatCount);
	}
	/*
	private void run(String[] args) {

		Options options = createOptions();

		

		if(parseOptions(options, args)){

			if (help){

				printHelp(options);

				return;

			}

			

			// path is required (necessary) data so no need to have a branch.

			System.out.println("You provided \"" + path + "\" as the value of the option p");

			

			// TODO show the number of files in the path

			

			if(verbose) {

				

				// TODO list all files in the path

				

				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");

			}

		}

	}



	private boolean parseOptions(Options options, String[] args) {

		CommandLineParser parser = new DefaultParser();



		try {



			CommandLine cmd = parser.parse(options, args);



			path = cmd.getOptionValue("p");

			verbose = cmd.hasOption("v");

			help = cmd.hasOption("h");



		} catch (Exception e) {

			printHelp(options);

			return false;

		}



		return true;

	}



	// Definition Stage

	private Options createOptions() {

		Options options = new Options();



		// add options by using OptionBuilder

		options.addOption(Option.builder("p").longOpt("path")

				.desc("Set a path of a directory or a file to display")

				.hasArg()

				.argName("Path name to display")

				.required()

				.build());



		// add options by using OptionBuilder

		options.addOption(Option.builder("v").longOpt("verbose")

				.desc("Display detailed messages!")

				//.hasArg()     // this option is intended not to have an option value but just an option

				.argName("verbose option")

				//.required() // this is an optional option. So disabled required().

				.build());

		

		// add options by using OptionBuilder

		options.addOption(Option.builder("h").longOpt("help")

		        .desc("Help")

		        .build());



		return options;

	}

	

	private void printHelp(Options options) {

		// automatically generate the help statement

		HelpFormatter formatter = new HelpFormatter();

		String header = "CLI test program";

		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";

		formatter.printHelp("CLIExample", header, options, footer, true);

	}
	*/
}