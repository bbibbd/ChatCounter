package edu.handong.csee.java.HW3.ChatCounter;

import java.io.File;
import java.util.ArrayList;

/**
 * this class load files using thread pool
 */
public class ThreadLoader extends Thread{
	private File file;
	private ArrayList<DataAccessor> pool;

	
	public void run() {
		if(file.getName().endsWith(".csv")) {
			FileLoader loader = new FileLoader();
			pool = loader.loadCSVFiles(file);
		}
		if(file.getName().endsWith(".txt")) {
			FileLoader loader = new FileLoader();
			pool = loader.loadTXTFiles(file);
		}
	}
	
	/**
	 * this is constructor sets file and data pool
	 * @param file
	 */
	public ThreadLoader(File file) {
		this.file = file;
		this.pool = new ArrayList<DataAccessor>();
	}
	
	/**
	 * This method is getter of dataPool
	 * @return
	 */
	public ArrayList<DataAccessor> getDataPool() {
		return pool;
	}
	
}