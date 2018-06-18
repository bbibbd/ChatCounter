package edu.handong.csee.java.HW3.ChatCounter;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	int numOfThreads;

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

			File directory = new File(input);
			ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
			ArrayList<ThreadLoader> loaders = new ArrayList<ThreadLoader>();
			
			for(File file : directory.listFiles()) {
				Runnable loader = new ThreadLoader(file);
				executor.execute(loader); 
				loaders.add((ThreadLoader) loader);
			}
			
			

			while (!executor.isTerminated()) {
				
			}

			HashMap<String, ArrayList<DataAccessor>> pool = new HashMap<String, ArrayList<DataAccessor>>();
			List<DataOfNameAndCount> chatCount = new ArrayList<DataOfNameAndCount>();
			
			FileParser parser = new FileParser();
			
			for(ThreadLoader e : loaders) {
				for(DataAccessor data:e.getDataPool()) {
					pool = parser.addUnique(pool, data);
				}
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
			numOfThreads = Integer.parseInt(cmd.getOptionValue("c"));
			
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
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("c").longOpt("num-of-threads")
				.desc("Set number of threads to use")
				.hasArg()
				.argName("number of threads to load files ")
				.required()
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