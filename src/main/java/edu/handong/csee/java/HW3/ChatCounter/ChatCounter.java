package edu.handong.csee.java.HW3.ChatCounter;

public class ChatCounter {
	
	private String name;
	private int count;
	
	public ChatCounter(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void print() {
		System.out.println(name + ", " + count);
	}

	public int compareTo(ChatCounter other) {
		if(this.count < other.getCount())
			return 1;
		else if(this.count > other.getCount())
			return -1;

		return 0;

	}
	
}
