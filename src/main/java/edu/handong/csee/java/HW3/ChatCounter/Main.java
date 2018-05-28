package edu.handong.csee.java.HW3.ChatCounter;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Main main = new Main();
		main.run();

	}


	private void run() {

		System.out.println("Start!");

		FileLoader fileLoader = new FileLoader("C:\\git\\HW3\\chat_log");

		fileLoader.loadCSVFiles();
		fileLoader.loadTXTFiles();
		HashMap<String, ArrayList<DataAccessor>> nameAndMessage = fileLoader.getMessages();

		List<ChatCounter> chatCount = new ArrayList<ChatCounter>();

		for(String name : nameAndMessage.keySet()) {

			chatCount.add(new ChatCounter(name, nameAndMessage.get(name).size()));

			//System.out.println(name + " " + NMlist.get(name).size());

			//for(DataAccessor namdDateMessage : nameAndMessage.get(name)) {

				//System.out.println(name+ " " + namdDateMessage.getDate() + " " + namdDateMessage.getMessage());

			//}
		}
		//Collections.sort(chatCount);
		for(ChatCounter chat : chatCount) {
			chat.print();
		}

	}

}