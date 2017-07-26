package com.planb.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileManager {
	public static File[] files;
	
	public static void exploreFile(File dir) {
		File[] fileList = dir.listFiles(); // directory's files
		
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				try {
					files[i] = fileList[i];
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (fileList[i].isDirectory()) {
				exploreFile(fileList[i]);
			}
		}
	}
	
	public static boolean compressZip(File[] files) {
		int count = 0;
		for(File file: files) {
			if(file == null) {
				break;
			}
			count++;
		}
		
		System.out.println(count);
		if(count < 2) {
			return false;
		}
		
		byte[] buf = new byte[8192];
		
		try {
			String outFilePath = "temp.zip";
			if(!new File(outFilePath).exists()) {
				new File(outFilePath).createNewFile();
			}
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilePath));
			
			for(File file: files) {
				if(file == null) {
					continue;
				}
				
				FileInputStream in = new FileInputStream(file.getPath());
				out.putNextEntry(new ZipEntry(file.getPath()));
				
				int len;
				while((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				
				out.closeEntry();
				in.close();
			}
			
			out.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
