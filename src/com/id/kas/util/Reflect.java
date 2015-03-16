package com.id.kas.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reflect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reflect ref = new Reflect();
		String className = "com.id.kas.pojo.TblBranch";
//		ref.getClassMethode(className);
		
		ref.setClassName("com.id.kas.pojo.TblBranch");
		ref.getClassField();
		ref.getPropertyAndTypeByMehode();
	}

	private String className;
		
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
/*
 * mengambil property dan typenya dengan menggunakan methode get
 */
	public List<String> getPropertyAndTypeByMehode(){
		List<String> listMethode =  new ArrayList<String>();
		try {
			Class c = Class.forName(className);
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++){
				
				if(m[i].getName().toString().startsWith("get")){
					String sType = ((m[i].getReturnType().toString()).replace("class", ""));
					
					String[] xType = sType.split("\\.");
					int q = xType.length - 1;
//					System.out.println(q);
//					System.out.println(sType+" "+xType[q]);
					listMethode.add(m[i].getName().toString().replace("get", "")+"|"+xType[q]);
//					System.out.println((m[i].getName().toString().replace("get", ""))+"|"+xType[q]);
				}
			}
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	
		return listMethode;
	}
	
	public List<String> getProperty(){
		List<String> listMethode =  new ArrayList<String>();
		try {
			Class c = Class.forName(className);
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++){
				
				if(m[i].getName().toString().startsWith("get")){
					String sType = ((m[i].getReturnType().toString()).replace("class", ""));
					
					String[] xType = sType.split("\\.");
					int q = xType.length - 1;
//					System.out.println(q);
//					System.out.println(sType+" "+xType[q]);
					listMethode.add(m[i].getName().toString().replace("get", ""));//+"|"+xType[q]);
//					System.out.println((m[i].getName().toString().replace("get", ""))+"|"+xType[q]);
				}
			}
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	
		return listMethode;
	}
	
	
	
	
	
	private Set<String> setImport; 
	/*
	 * mengmabil property dari peroperty yang di declare
	 */
	public List<String> getClassField(){
		List<String> listProperty = new ArrayList<String>();
		List<String> listImport = new ArrayList<String>();
		setImport = new HashSet<String>();
		try {
			Class c = Class.forName(className);
			Field fieldlist[] = c.getDeclaredFields();
			 for (Field aFieldlist : fieldlist) {
				 String classType = aFieldlist.getType().toString().replace("class", "").trim();
				 String nameClass = aFieldlist.getName().toString(); listImport.add(aFieldlist.getName().toString()+":"+classType);
				 listProperty.add(nameClass+";"+classType);
				 if (classType.contains("int")||classType.contains("long")||classType.contains("dobel")||classType.contains("String")
						 ||classType.contains("char")){
					 
				 }else{
					 setImport.add(classType+";");
				 }
				 
			 }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		for(String s:listProperty){
//			System.out.println(s);
//		}
		return listProperty;
	}
	
	public Set<String> getImportClass(){
		return setImport;
	}
	/*
	 * mengurai nama class dari pakage+class
	 */
	public String getClazzName(){
		String clazz[] = className.split("\\.");
		int i = clazz.length;i--;
		return clazz[i].toString();
	}
}
