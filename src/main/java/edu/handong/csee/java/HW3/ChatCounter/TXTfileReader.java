package edu.handong.csee.java.HW3.ChatCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class TXTfileReader extends FileParser{
	
	/**
	 * 
	 */
	public ArrayList<DataAccessor> lineParser(ArrayList<DataAccessor> messages, File file) {
		BufferedReader br;	
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String thisLine;
			while((thisLine = br.readLine()) != null) {
				String pattern = "\\[(.+)\\] \\[(.*[0-9]+:[0-9]+.*)\\] (.+)";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(thisLine);
				
				String name ="";
				String date="";
				String message="";
				if(m.find()) {
					name = m.group(1);
					date = m.group(2);
					message = m.group(3);
					date = dateParser(date);
				}
				
				DataAccessor data = new DataAccessor(name, date, message);
				messages.add(data);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	private String dateParser(String date) {
		String ampmPattern = "(..)\\s([0-9]+:[0-9]+)|([0-9]+:[0-9]+)\\s(..)";
		Pattern r2 = Pattern.compile(ampmPattern);
		Matcher m2 = r2.matcher(date);
		
		String time = "";
		if(m2.find()) {
			int hour = 0;
			if(m2.group(1) != null) {
				time = m2.group(2);
				hour = Integer.parseInt(time.substring(0, time.length() - 3));
				if(m2.group(1).equals("오후") && hour != 12)
					hour += 12;
				else if(m2.group(1).equals("오전") && hour == 12)
					hour = 0;
			}
			
			else {
				time = m2.group(3);
				hour = Integer.parseInt(time.substring(0, time.length() - 3));
				if(m2.group(4).equals("PM") && hour != 12)
					hour += 12;
				else if(m2.group(4).equals("AM") && hour == 12)
					hour = 0;
			}
			String strHour = Integer.toString(hour);
			if(hour < 10)
				 strHour = "0" + strHour;
			
			date = strHour + time.substring(time.length() - 3);
		}
		return date;
	}
}