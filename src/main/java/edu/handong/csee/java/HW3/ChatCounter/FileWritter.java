package edu.handong.csee.java.HW3.ChatCounter;

import java.io.BufferedWriter;
import java.util.*;
import java.io.*;

/**
 * This class exports the final result of the chat counter 
 * @author KKB
 *
 */
public class FileWritter {
	String fileName = "TempName";

	/**
	 * this method set name of the output file
	 * @param name
	 */
	public FileWritter(String name) {
		fileName = name;
	}

/**
 * this method exports final results 
 * @param listOfNameAndCount
 */
	public void makeItCSVFile(List<DataOfNameAndCount> listOfNameAndCount) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), "UTF-8"));

			bufferedWriter.write("name, count\n");
			for(DataOfNameAndCount e:listOfNameAndCount) {
				bufferedWriter.write(e.getName() + ", " + e.getCount() + "\n");
			}
			bufferedWriter.close();
		} catch(FileNotFoundException e) {
			System.out.println("there is no file: " + fileName);
		} catch (IOException e) {
			System.out.println("Problem with " + fileName);
		}
	}
}
