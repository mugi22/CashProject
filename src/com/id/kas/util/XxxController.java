package com.id.kas.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class XxxController {
	@RequestMapping(value="/xxx.htm",method=RequestMethod.POST)
	public String doPost(HttpServletRequest reg){
		String jspName = reg.getParameter("jspName");
		String conName = reg.getParameter("cont");
		String mapName = reg.getParameter("maping");
		String daoName = reg.getParameter("dao");
		System.out.println();
		
		String par[] = reg.getParameterValues("tes");
		String list[] = reg.getParameterValues("list");
        String search[] = reg.getParameterValues("search");
        String ids[] = reg.getParameterValues("ids");    
        
		List<String> lForm =  new ArrayList<String>();
		List<String> lList = new ArrayList<String>();
        List<String> lSearch = new ArrayList<String>();
        List<String> lIds = new ArrayList<String>();
        
        
		System.out.println("Form "+par.length);
		int i = 0;
		for(String s : par){
			i++;
//			System.out.println(i+"  : "+s);
			lForm.add(s);
		}
		
		System.out.println("List "+list.length);
		i = 0;
		for(String s : list){
			i++;
//			System.out.println(i+"  : "+s);
			lList.add(s);
		}
		
                System.out.println("search "+list.length);
		i = 0;
		for(String s : search){
			i++;
//			System.out.println(i+"  : "+s);
			lSearch.add(s);
		}
             
		i = 0;
		for(String s : ids){
			i++;
//			System.out.println(i+"  : "+s);
			lIds.add(s);
		}
                
		TempCreate tem = new TempCreate();
//		V I E W H:\Workspace\CashProject\WebContent\WEB-INF\jsp
		String sTemplate="H:\\Workspace\\CashProject\\template\\"; //TEMPLATE FoLDER
		String sFileDAO="H:\\Workspace\\CashProject\\src\\com\\id\\kas\\DEVELOPMENT\\"+daoName+"DAO.java"; //DAO
		String sFileController="H:\\Workspace\\CashProject\\src\\com\\id\\kas\\DEVELOPMENT\\"+conName+".java";//CONTROLER
		String sFileCreate="H:\\Workspace\\CashProject\\WebContent\\WEB-INF\\jsp\\"+jspName+".jsp"; //JSP
		String sFile;
		sFile=sTemplate+"jspTemplate.txt";//baca template
		List<String> l = new ArrayList<String>();
//		System.out.println(tem.readFile(sFile,lForm,lList));
		String strFile = (tem.readFile(sFile,lForm,lList,jspName,lSearch,lIds));
//		String sFileCreate="H:\\Workspace\\CashProject\\WebContent\\WEB-INF\\jsp"+jspName+".jsp";
		tem.createFile(sFileCreate, strFile);
		
		
//		COTROLLER	
		String sFileConTemp=sTemplate+"controllerTemplate.txt";
//		String sFileController="H:\\zzz\\CashProject\\src\\com\\id\\kas\\DEVELOPMENT\\"+conName+".java";
		String strControl = tem.readFileControllerTemp(sFileConTemp,mapName,jspName,conName,daoName,lForm,lSearch,lIds);
		tem.createFile(sFileController, strControl);
		      
//		D A O  mula
//		COTROLLER	
		String sFileDAOTemp=sTemplate+"daoTemplate.txt";
//		String sFileDAO="H:\\zzz\\CashProject\\src\\com\\id\\kas\\DEVELOPMENT\\"+daoName+"DAO.java";
		String strDAO = tem.readFileDAOTemp(sFileDAOTemp, daoName,lSearch,lIds);
		tem.createFile(sFileDAO, strDAO);
			
		System.out.println("POST  disini.......");
//		readFile();
		return "xxxScreen";
	}

	@RequestMapping(value="/xxx.htm",method=RequestMethod.GET)
	public String doGet(HttpServletRequest reg){
		System.out.println("GET   disini.......");
		return "xxxScreen";
	}
	
	
	@RequestMapping(value="/testAjax.htm",method=RequestMethod.GET)
	public @ResponseBody String testAjax(HttpServletRequest reg){
		System.out.println("Test Ajax................"+reg.getParameter("param"));
		Reflect ref = new Reflect();
		ref.setClassName(reg.getParameter("param"));
//		System.out.println();
		List<String> l = ref.getProperty();//ref.getClassField();//ref.getPropertyAndTypeByMehode();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		String col ="colx";
		for(String s : l){
			sb.append("{"+'"'+col+'"'+':'+""+'"'+s+'"'+"}"+",");
		}
		String x = (sb.toString()).substring(0,sb.toString().length()-1);
		String z = x+"]";
//		sb.append("}]");
		
		
		Gson gson = new Gson();
//		String x =  gson.toJson(l);
		System.out.println(z);
		return z.toString();
	}
	
	
	


}
