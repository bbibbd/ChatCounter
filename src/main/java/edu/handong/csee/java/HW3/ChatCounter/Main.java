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
	String input;
	String output;
	boolean verbose;
	boolean help;

	/**
	 * This is main method and run the program
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.run(args);
	}

	/**
	 * This method (1) loads csv and txt files
	 * (2) makes a hashmap that has names, dates and messages
	 * (3) makes new list that has name and the size of name from hashmap
	 * (4) sorts the list
	 * (4) prints out the final result
	 */
	public void run(String[] args) {
		Options options = createOptions();
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}	

			FileLoader fileLoader = new FileLoader(input);

			fileLoader.loadCSVFiles();
			fileLoader.loadTXTFiles();

			HashMap<String, ArrayList<DataAccessor>> nameAndMessage = fileLoader.getMessages();
			List<DataOfNameAndCount> chatCount = new ArrayList<DataOfNameAndCount>();

			for(String name : nameAndMessage.keySet()) {
				if(!name.equals(""))
					chatCount.add(new DataOfNameAndCount(name, nameAndMessage.get(name).size()));
			}

			Collections.sort(chatCount);

			System.out.println("result: ");
			
			for(DataOfNameAndCount chat : chatCount) 
				chat.printNameAndCount();
			
			FileWritter fileWritter = new FileWritter(output);
			fileWritter.makeItCSVFile(chatCount);
		}
	}


	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
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
		options.addOption(Option.builder("i").longOpt("input")

				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());

		// add options by using OptionBuilder

		options.addOption(Option.builder("o").longOpt("output")

				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
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
		String header = "CLI for message counter";
		String footer ="end help";
		formatter.printHelp("CLIExample", header, options, footer, true);

	}
}