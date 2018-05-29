package edu.handong.csee.java.HW3.ChatCounter;

/**
 * This class allows to access the data of name, date and message
 * @author KKB
 *
 */
public class DataAccessor {
	
	private String name;
	private String date;
	private String message;

	protected DataAccessor(String name, String date, String message) {
		this.name = name;
		this.date = date;
		this.message = message;
	}
	
	/**
	 * getter of name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter of name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter of date
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * setter of name
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * getter of message
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * setter of message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * compare data and if these are same, return true
	 * @param dataAccessor
	 * @return
	 */
	public boolean equalsTo(DataAccessor dataAccessor) {
		if((dataAccessor.getDate().equals(this.date) && dataAccessor.getName().equals(this.name)) && this.message.startsWith(dataAccessor.getMessage()))
			return true;
		return false;
	}
	



}