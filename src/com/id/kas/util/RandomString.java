package com.id.kas.util;

import java.util.Random;

public class RandomString {
	
	int length=16;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}










	public String randomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		System.out.println(output);
		return output ;
	}
	
		

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomString rd =  new RandomString();
		rd.setLength(4);
		rd.randomString();

	}

	
	
	
	
}
