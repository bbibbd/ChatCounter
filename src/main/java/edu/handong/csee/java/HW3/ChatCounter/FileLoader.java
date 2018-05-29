package edu.handong.csee.java.HW3.ChatCounter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.*;

import java.io.*;

/**
 * This class will load csv and txt files and parse it at the same time.
 * Used commons CSV to paring csv files 
 * Used regular expression to paring txt files
 * @author KKB
 *
 */
public class FileLoader {
	private File path;
	private HashMap<String, ArrayList<DataAccessor>> messages = new HashMap<String, ArrayList<DataAccessor>>();

	/**
	 * sets path using constructor
	 * @param path
	 */
	public FileLoader(String path) {
		this.path = new File(path);
	}
	
	/**
	 * Declare 
	 * @return
	 */
	public HashMap<String, ArrayList<DataAccessor>> getMessages() {
		return messages;
	}
	
	/**
	 * add data to HashMap
	 * @param hashMap
	 * @param data
	 * @return
	 */
	public HashMap<String, ArrayList<DataAccessor>> addData(HashMap<String, ArrayList<DataAccessor>> 
	hashMap, DataAccessor data){
		for(String name : hashMap.keySet()) {
			for(DataAccessor d: hashMap.get(name)) {
				if(d.equalsTo(data))
					return hashMap;
			}
		}

		hashMap.get(data.getName()).add(data);
		return hashMap; 
	}

	/**
	 * This method loads txt files
	 * Using Regular Expression and grouping to make it easy to deal the data
	 * [NAME] [TIME] text => name has form of characters and integers, time has (integers + ':' +AM or PM) or, (오전/오후 +integers+:+integers)
	 * So, the pattern: \\[(.+)\\] \\[(.*[0-9]+:[0-9]+.*)\\] (.+)
	 * I declared another pattern which can deal the time: (..)\\s([0-9]+:[0-9]+)|([0-9]+:[0-9]+)\\s(..)
	 * if [오전/오후 hh:mm] group 3 and 4 are null, and group 1 becomes 오전/오후 and group 2 becomes time
	 * else, group 1 and 2 will be null and 3 becomes time, and 4 becomes AM or PM
	 */
	public void loadTXTFiles() {
		for(File file: path.listFiles()) {
			if(file.getName().contains(".txt")) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
					String thisLine;

					while((thisLine = br.readLine()) != null) {
						String pattern = "\\[(.+)\\] \\[(.*[0-9]+:[0-9]+.*)\\] (.+)";
						Pattern p = Pattern.compile(pattern);
						Matcher m = p.matcher(thisLine);

						if(m.find()) {
							String name = m.group(1);
							String date = m.group(2);
							String text = m.group(3);
							String patternOfTime = "(..)\\s([0-9]+:[0-9]+)|([0-9]+:[0-9]+)\\s(..)"; //오전|오후 + time or time + PM|AM

							Pattern p2 = Pattern.compile(patternOfTime);
							Matcher m2 = p2.matcher(date);
							String time;
							if(m2.find()) {
								int hour = 0;
								if(m2.group(1) != null) {	
									time = m2.group(2);
									hour = Integer.parseInt(time.substring(0, time.length() - 3));
									if(m2.group(1).equals("오후"))
										hour += 12;
								}
								else {
									time = m2.group(3);
									hour = Integer.parseInt(time.substring(0, time.length() - 3));
									if(m2.group(4).equals("PM"))
										hour += 12;
								}
								String strHour = Integer.toString(hour);
								if(hour < 10)
									strHour = "0" + strHour;
								date = strHour + time.substring(time.length() - 3);

							}

							if(!messages.containsKey(name))
								messages.put(name, new ArrayList<DataAccessor>());
							messages = addData(messages, new DataAccessor(name, date, text));
						}
					} 
					br.close();
				}catch (UnsupportedEncodingException | FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}

			}

		}

	}

	/**
	 * This method loads CSV files. If the files contains .csv, implement this method
	 * imported CommonsCSV
	 * get(0): time , substring(11,16) - get time to check the repeated message
	 * get(1): name
	 * get(2): get message
	 * CSVRecord: A CSV record parsed from a CSV file.
	 */
	public void loadCSVFiles() {
		for(File file: path.listFiles()) {
			if(file.getName().contains(".csv")) {
				Reader in;
				try {
					in = new FileReader(file);
					Iterable<CSVRecord> recordsOfCSVFiles = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
					for(CSVRecord record : recordsOfCSVFiles) {
						String time = record.get(0).substring(11,16); //get data from first column (7th letters to 16th letters) - time 
						String name = record.get(1);	//get data from second column - name
						String text = record.get(2);	//get data from third column - 
						if(!messages.containsKey(name))	//
							messages.put(name, new ArrayList<DataAccessor>());
						messages = addData(messages, new DataAccessor(name, time, text));
					}

				}catch(Exception e) {

				}

			}

		} 

	}

}