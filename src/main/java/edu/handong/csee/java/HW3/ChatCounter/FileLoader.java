package edu.handong.csee.java.HW3.ChatCounter;
import java.util.*;
import java.io.*;

 /**
  * This class loads and parse txt files and csv files
  */
public class FileLoader {
	private ArrayList<DataAccessor> messages = new ArrayList<DataAccessor>();

	/**
	 * declare the getter method of the message.
	 * @return
	 */
	public ArrayList<DataAccessor> getMessages() {
		return messages;
	}
	
	/**
	 *this method loads and parses CSV files
	 */
	public ArrayList<DataAccessor> loadCSVFiles(File file) {
		CSVfileReader parser = new CSVfileReader();
		messages = parser.csvParser(messages, file);
		return messages;
	}
	
	/**
	 * This method loads TXT files and parse it
	 */
	public ArrayList<DataAccessor> loadTXTFiles(File file) {
		TXTfileReader parser = new TXTfileReader();
		messages = parser.lineParser(messages, file);
		return messages; 
	}

}