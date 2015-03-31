package com.id.kas.util;

import org.springframework.stereotype.Service;


public class MyVariable{
private static  String sAppStatus;

public static String getsAppStatus() {
	return sAppStatus;
}

public static void setsAppStatus(String sAppStatus) {
	MyVariable.sAppStatus = sAppStatus;
}

  


}