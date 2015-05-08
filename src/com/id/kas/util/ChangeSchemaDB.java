package com.id.kas.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeSchemaDB {
	static String sFolder = "H:/Workspace/HnfProject/src/com/id/kas/pojo/";
	final static File folder = new File(sFolder);
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	           // listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getAbsolutePath());
	            BufferedReader br = null;
	            StringBuffer sb = new StringBuffer();
	            try {
	            	String sCurrentLine;
					br = new BufferedReader(new FileReader(fileEntry.getAbsolutePath()));
					while ((sCurrentLine = br.readLine()) != null) {
						String line = sCurrentLine;
						if (sCurrentLine.contains("TESTDB")){
							String z = line.replaceAll("TESTDB", "dacen");
							line =z;							
						}
						sb.append(line+"\n");	
					}
					createFile(sFolder+"new/"+fileEntry.getName(), sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
	        }
	    }
	}
	
	
	
	public void createFile(String fileName,String fileText) {
		try {
			File file = new File(fileName);
			if(file.createNewFile()){
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(fileText);
				output.close();
		        System.out.println("Success..........................................!"+fileName);
			}
		         else{
		         System.out.println("Error ,"+fileName+" file already exists.");
		    }
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		ChangeSchemaDB schemaDB = new ChangeSchemaDB();
		schemaDB.listFilesForFolder(folder);
	}
	

}
