package edu.handong.csee.java.HW3.ChatCounter;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
/**
 *
 */
public class CSVfileReader extends FileParser{
	/**
	 */
	public ArrayList<DataAccessor>csvParser(ArrayList<DataAccessor> messages, File file) {
		Reader in;
		try {
			in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record : records) {
				String time = record.get(0).substring(11,16);
				String user = record.get(1);
				String messageString = record.get(2);
				
				 //messages = addUnique(messages, new NDMdata(user, time, messageString));
				DataAccessor data = new DataAccessor(user, time, messageString);
				 messages.add(data);
			}
		}
		catch(Exception e) {
		}
		
		return messages;
	}
}
