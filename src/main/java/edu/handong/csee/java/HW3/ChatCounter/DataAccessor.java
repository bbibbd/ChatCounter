package edu.handong.csee.java.HW3.ChatCounter;

public class DataAccessor {
	
	private String name;
	private String date;
	private String message;

	protected DataAccessor(String name, String date, String message) {
		this.name = name;
		this.date = date;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean equalsTo(DataAccessor dataAccessor) {
		if((dataAccessor.getDate().equals(this.date) && dataAccessor.getName().equals(this.name)) && this.message.startsWith(dataAccessor.getMessage()))
			return true;
		return false;
	}
	



}