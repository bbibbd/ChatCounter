package edu.handong.csee.java.HW3.ChatCounter;

import java.io.File;
import java.util.*;

public class FileLoader {
	private ArrayList<String> messages;
	
	public void readDirectory(String path) {
		ArrayList<File> filenames = getFileNames(path);
		getFileNames(path);
	}

	private ArrayList<File> getFileNames(String path) {
		ArrayList<File> fileNames = new ArrayList<File>();
		File myPath = new File(path);
		for(File fileName:myPath.listFiles()) {
			myPath.listFiles();
		}
		return fileNames;
	}
}

