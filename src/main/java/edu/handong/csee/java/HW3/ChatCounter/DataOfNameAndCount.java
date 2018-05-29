package edu.handong.csee.java.HW3.ChatCounter;

/**
 * This class gets and sets name and count and print out of them
 * 
 * @author KKB
 *
 */
public class DataOfNameAndCount implements Comparable<DataOfNameAndCount> {
	
	private String name;
	private int count;
	
	/**
	 * set name and count using constructor
	 * @param name
	 * @param count
	 */
	public DataOfNameAndCount(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	/**
	 * getter of the name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter of the name
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter of the count
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * setter of the count
	 * @return
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * print out name + count
	 * @return
	 */
	public void printNameAndCount() {
		System.out.println(name + ", " + count);
	}

	/**
	 * to sort the list by count, implements Class Comparable and use compareTo method.
	 */
	public int compareTo(DataOfNameAndCount other) {
		if(this.count < other.getCount())
			return 1;
		else if(this.count > other.getCount())
			return -1;

		return 0;

	}
	
}
