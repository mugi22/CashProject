package com.id.kas.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadPropertiesFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadPropertiesFile rp = new ReadPropertiesFile();
		rp.propertesChek("showter");
	}

	public  void propertesChek(String stringToChek) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;	
		String s = "H:/Workspace/CashProject/WebContent/WEB-INF/myapp.properties";
		try{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(s));
			int check = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String line = sCurrentLine;
				if (sCurrentLine.contains(stringToChek)){
//					String z;// = line.replaceAll("XXXjudul", jspName.toUpperCase());
					System.out.println(line);
					check =1;
				}
				sb.append(line+"\n");
			}
			if(check==0){
				//tambah
				sb.append(stringToChek);
				
				createFile(s, sb.toString());
			}
		}catch (Exception e){
			
		}
	}
	
	
	public void createFile(String fileName,String fileText) {
		try {
			File file = new File(fileName);
			if(file.exists()){
				BufferedWriter output = new BufferedWriter(new FileWriter(file,true));
				output.write(fileText);
				output.close();
		        System.out.println("Success file added..........................................!"+fileName);
			}
		         else{
		         System.out.println("Error ,"+fileName+" file already exists.");
		    }
			
			
		} catch (IOException e) {
			System.out.println("file : "+fileName);
			e.printStackTrace();
		}
	}
	
	
	
	
}
