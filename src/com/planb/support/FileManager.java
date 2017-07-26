package com.planb.support;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	public static ArrayList<File> files = new ArrayList<>();
	
	public static void exploreFile(String source) {
		File dir = new File(source); // directory path
		File[] fileList = dir.listFiles(); // directory's files
		
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				try {
					files.add(fileList[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (fileList[i].isDirectory()) {
				try {
					exploreFile(fileList[i].getCanonicalPath().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
