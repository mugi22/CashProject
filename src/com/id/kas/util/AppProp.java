package com.id.kas.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Service;


public class AppProp{
private static  String sAppStatus;
private static Map mSession = new HashMap<String, String>();
private static boolean singleLogin = true; //single longgin

public static boolean isSingleLogin() {
	return singleLogin;
}

public static void setSingleLogin(boolean singleLogin) {
	AppProp.singleLogin = singleLogin;
}

public static Map getmSession() {
	return mSession;
}

public static void setmSession(Map mSession) {
	AppProp.mSession = mSession;
}

public static void setmSession(String key, String val) {
	mSession.put(key, val);
//	AppProp.mSession = mSession;
}

public static String getmSessionVal(String key) {
	String val = (String) mSession.get(key); 
	return val;
}





public static String getsAppStatus() {
	return sAppStatus;
}

public static void setsAppStatus(String sAppStatus) {
	AppProp.sAppStatus = sAppStatus;
}

  


}