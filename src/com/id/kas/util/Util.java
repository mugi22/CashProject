package com.id.kas.util;

import org.hibernate.Session;

import com.id.kas.pojo.TblUserGroup;
import com.id.kas.pojo.dao.TblUserGroupDAO;

public  class Util {

	public static String firstUpper(String string){
		String s =Character.toUpperCase(string.charAt(0)) + string.substring(1);
		return s;
	}

	public static String firstLowerr(String string){
		String s =Character.toLowerCase(string.charAt(0)) + string.substring(1);
		return s;
	}
	
	
	public static boolean cekUserAdminMode(String userId, Session sess){
		TblUserGroupDAO userGroupDAO = new TblUserGroupDAO(sess);
		TblUserGroup tblUserGroup = userGroupDAO.getById(AppConstant.AdminModeGroup.AdminModeGroup, userId);
		if (tblUserGroup==null){
			return false;
		}else
		{
			return true;
		}
	}
	
}
