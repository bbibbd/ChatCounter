package edu.handong.csee.java.HW3.ChatCounter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */
public class FileParser {
	
	/**
	 *declare HashMap including value of NDM data
	 */
	public HashMap<String, ArrayList<DataAccessor>> addUnique(HashMap<String, ArrayList<DataAccessor>> namdDateMessage, DataAccessor data){
		
		String name = data.getName();
		if(!namdDateMessage.containsKey(name))
			namdDateMessage.put(name, new ArrayList<DataAccessor>());
		
		for(DataAccessor e: namdDateMessage.get(data.getName())) {
			DataAccessor longerNDM = e;
			DataAccessor shorterNDM = data;
			
			if(e.getMessage().length() < data.getMessage().length()) {
				longerNDM = data;
				shorterNDM = e;
			}
				
			if(longerNDM.equalsTo(shorterNDM))
				return namdDateMessage;			
		}
		namdDateMessage.get(data.getName()).add(data);
		return namdDateMessage; 
	}
}