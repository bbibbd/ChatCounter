package edu.handong.csee.java.HW3.ChatCounter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.*;

import java.io.*;

public class FileLoader {
	private File path;
	private HashMap<String, ArrayList<DataAccessor>> messages = new HashMap<String, ArrayList<DataAccessor>>();

	public FileLoader(String path) {
		this.path = new File(path);
	}

	public HashMap<String, ArrayList<DataAccessor>> getMessages() {
		return messages;
	}

	public HashMap<String, ArrayList<DataAccessor>> addUnique(HashMap<String, ArrayList<DataAccessor>> 
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
							}

							if(!messages.containsKey(name))
								messages.put(name, new ArrayList<DataAccessor>());
							messages = addUnique(messages, new DataAccessor(name, date, text));
						}
					} 
					br.close();
				}catch (UnsupportedEncodingException | FileNotFoundException e) {
					//System.out.println("Error 1");
					e.printStackTrace();
				} catch (IOException e) {
					//System.out.println("Error 2");
					e.printStackTrace();

				}

			}

		}

	}
	
	/**
	 * This class loads CSV files. If the files contains .csv, implement this method
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
						messages = addUnique(messages, new DataAccessor(name, time, text));
					}

				}catch(Exception e) {

				}

			}

		} 

	}

}