package com.id.kas.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.id.kas.pojo.AuditTrail;
@Controller
public class PojoGenerator {
	String sFile="";//"H:\\Workspace\\CashProject\\src\\com\\id\\kas\\DEVELOPMENT\\"+daoName+".java"; //DAO
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@RequestMapping(value="/pojogen.htm",method=RequestMethod.GET)
	public String doGet(HttpServletRequest reg){
		return "pojogen";
	}
	

	@RequestMapping(value="/pojogen.htm",method=RequestMethod.POST)
	public String doPost(HttpServletRequest reg){
		sFile="H:\\Workspace\\CashProject\\src\\com\\id\\kas\\DEVELOPMENT\\"+reg.getParameter("pojoName")+".java"; //DAO
		
		
		
		String col[] = reg.getParameterValues("col");
		String types[] = reg.getParameterValues("types");
		String length[] = reg.getParameterValues("length");
		String uniq[] = reg.getParameterValues("uniq");
		String nulable[] = reg.getParameterValues("nulable");
		String ids[] = reg.getParameterValues("ids");
		System.out.println(col.length);
		for(int i=0 ;i< col.length;){
			System.out.println("col  " +col[i]+" types "+types[i]);
			i++;
		}
			create(reg);	
		return "pojogen";
	}
	
	
	public void create(HttpServletRequest reg){
		String col[] = reg.getParameterValues("col");
		String types[] = reg.getParameterValues("types");
		String length[] = reg.getParameterValues("length");
		String uniq[] = reg.getParameterValues("uniq");
		String nulable[] = reg.getParameterValues("nulable");
		String ids[] = reg.getParameterValues("ids");
		
		
		
		String namaPojo= reg.getParameter("pojoName");
		StringBuffer sb = new StringBuffer();
		String pakage="package com.id.kas.DEVELOPMENT;\n";
		String imports="import java.math.BigDecimal;\n"+
						"import java.util.Date;\n"+
						"import javax.persistence.Column;\n"+
						"import javax.persistence.Entity;\n"+
						"import javax.persistence.Id;\n"+
						"import javax.persistence.Table;\n"+
						"import javax.persistence.Temporal;\n"+
						"import javax.persistence.TemporalType;\n"+
						"import com.id.kas.pojo.AuditTrail;";
		
		String className="@Entity\n"+
						"@Table(name = "+'"'+reg.getParameter("tblName")+'"'+", schema = "+'"'+"TESTDB"+'"'+")\n"+
						"public class "+namaPojo+" extends AuditTrail implements java.io.Serializable {\n";
		
		StringBuffer sProperti = new StringBuffer();
		for(int i=0 ;i< col.length;i++){
			sProperti.append("private "+types[i]+" "+Util.firstLowerr(col[i])+";\n");
			
		}
		
		String sContructor="public "+namaPojo+"(){\n}";
		
		sb.append(pakage);
		sb.append(imports);
		sb.append(className);
		sb.append(sProperti.toString());
		sb.append(sContructor);
		/*
		 * @Id
	@Column(name = "GROUP_ID", unique = true, nullable = false, scale = 0)
	public BigDecimal getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}
		 */
		StringBuffer sbGet = new StringBuffer();
		for(int i=0 ;i< col.length;i++){
//cek id 
			String stemporal="";
			if(types[i].equals("Date")){
				stemporal ="@Temporal(TemporalType.DATE)\n";
			}
				String sid ="\n";
				if(ids[i].equals("Yes")){
					sid ="@Id\n";
				}
				String sUniq ="false,";
				if(uniq[i].equals("Yes")){
					sUniq="true,";			
				}
				sbGet.append(sid);
				sbGet.append(stemporal);
				sbGet.append("@Column(name = "+'"'+col[i].toUpperCase()+'"'+','+"unique ="+sUniq);
				String snull="nullable = false";
				if(nulable[i].equals("Yes")){
					snull="nullable = true";					
				}				
				sbGet.append(snull);
				
				sbGet.append(",length ="+length[i]+" ");
				
				String sScale = "";
				if(types[i].equals("Date")){
					sScale=")\n";
				}else{
					sScale=", scale = 0)\n";
				}
				sbGet.append(sScale);
				sbGet.append("public "+types[i]+" get"+Util.firstUpper(col[i])+"(){\n");
				sbGet.append("return this."+Util.firstLowerr(col[i])+";\n}");
				
				sbGet.append("public void set"+Util.firstUpper(col[i])+"("+types[i]+" "+Util.firstLowerr(col[i])+"){\n");
				sbGet.append("this."+Util.firstLowerr(col[i])+"="+Util.firstLowerr(col[i])+";\n}");
			
			
				
			
		}
		
		sbGet.append("\n}");
		sb.append(sbGet.toString());
		System.out.println(sb.toString());
		createFile(sFile, sb.toString());
	}
	
	
	
	
	public void createFile(String fileName,String fileText) {
		try {
			File file = new File(fileName);
			System.out.println(file.getAbsolutePath());
			if(file.createNewFile()){
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(fileText);
				output.close();
		        System.out.println("Success..........................................!");
			}
		         else{
		         System.out.println("Error, file already exists.");
		    }
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
