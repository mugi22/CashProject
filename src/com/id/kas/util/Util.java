package com.id.kas.util;

public  class Util {

	public static String firstUpper(String string){
		String s =Character.toUpperCase(string.charAt(0)) + string.substring(1);
		return s;
	}

	public static String firstLowerr(String string){
		String s =Character.toLowerCase(string.charAt(0)) + string.substring(1);
		return s;
	}
}
