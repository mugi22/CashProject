package com.id.kas.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempCreate {

	/**
	 * @param args
	 */
	private static String p="'";
	private static char d='"';
	private static String k=",";
	private static String j1="                    ";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String sFile;
//		sFile="C:\\KasProject\\KasTIProject\\template\\jspTemplate.txt";//baca template
		TempCreate tem = new TempCreate();
//		List<String> l = new ArrayList<String>();
//		l.add("Name");
//		l.add("alamat");
//		System.out.println(tem.readFile(sFile,l,l));
//		String strFile = (tem.readFile(sFile,l,l));
//		String sFileCreate="C:\\KasProject\\KasTIProject\\WebContent\\WEB-INF\\jsp\\test.jsp";
//		tem.createFile(sFileCreate, strFile);
//		C:\\Users\\Mugiarto\\Documents\\NetBeansProjects\\KasProject\\template
//		COTROLLER C:\MgRepos\Project\KasProject\src\com\id\kas\DEVELOPMENT
//		String sFileCreate="C:\\Users\\Mugiarto\\Documents\\NetBeansProjects\\KasProject\\template\\controllerTemplate.txt";
//		String sMapping ="/banch.htm";
//		String sJsp="branch";
//		String sClass="branch2Controller";
		//String strControl = tem.readFileControllerTemp(sFileCreate,sMapping,sJsp,sClass,"dao");
		
	}
//JSP
	public String readFile(String s,List<String> lForm,List<String> lList,String jspName,List<String> lSearch,List<String>  lIds){
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;	
				try {
					String sCurrentLine;
					br = new BufferedReader(new FileReader(s));
					while ((sCurrentLine = br.readLine()) != null) {
						//System.out.println(sCurrentLine);
						String line = sCurrentLine;
						if (sCurrentLine.contains("XXXjudul")){
							String z = line.replaceAll("XXXjudul", jspName.toUpperCase());
							line =z;
						}
                        //PENCARIAN       XXXparamSearch                                        
						if (sCurrentLine.contains("XXXsearch")){
							StringBuffer sbField = new StringBuffer();                                                       
							for(String t :lSearch){
                                 sbField.append(j1+"<label>"+Util.firstUpper(t)+"</label> : "+"<input name="+'"'+Util.firstUpper(t)+'"'+" type="+'"'+"text"+'"'+" id="+'"'+t+'"'+" size="+'"'+"30"+'"'+" maxlength="+'"'+"30"+'"'+"><br>\n");
                            }
							String z = line.replaceAll("XXXsearch", Util.firstLowerr(sbField.toString()));
							line =z;
						}
                        //PAMEETER Search   param=' + $('#idSearch').val();//+'&param2='++ $('#idSearch2').val();	//'seqName='$('#SeqName').val()&+'SeqNum='$('#SeqNum').val();
						if (sCurrentLine.contains("XXXparamSearch")){
							StringBuffer sbCari = new StringBuffer();  
							String x = "";
							for(String t :lSearch){
								sbCari.append("'"+Util.firstUpper(t)+"="+"'"+"+"+"\\$"+"("+"'"+"#"+Util.firstUpper(t)+"'"+").val()"+"+"+'"'+"&"+'"'+"+");
                            }
							x = (sbCari.toString()).substring(0,sbCari.toString().length()-5);
							String z = line.replaceAll("XXXparamSearch", x+";");
							line =z;
						}
						
						//LIST
						if (sCurrentLine.contains("XXXfield")){
							StringBuffer sbField = new StringBuffer();
							for(String t :lList){
								sbField.append(j1+"<th field="+'"'+Util.firstLowerr(t)+'"'+" width="+'"'+"100"+'"'+ "sortable="+'"'+"true"+'"'+">"+t+"</th> "+"\n");
							}
							String z = line.replaceAll("XXXfield", Util.firstLowerr(sbField.toString()));
							line =z;
						}
						
						//FORM
						if (sCurrentLine.contains("XXXlist")){
							StringBuffer sbField = new StringBuffer();
							for(String t :lForm){
								if(t.equals("CreateDate")||t.equals("CreateBy")||t.equals("UpdateDate")||t.equals("UpdateBy")||t.equals("Versi")||t.equals("")){									
								}else{
									sbField.append(j1+"<div class="+d+"fitem"+d+">	<label>"+t+"</label> :<input name="+d+Util.firstLowerr(t)+d+"	class="+d+"easyui-textbox"+d+" required="+d+"false"+d+" id="+d+Util.firstLowerr(t)+d+">	</div>\n");
								}
							}
							String z = line.replaceAll("XXXlist", sbField.toString());
							line =z;
						}	
						
						//ENABLE/DISABLE FIELD FORM XXXenableField
						//XXXenableOnShowField                  $('#userId').textbox('readonly', false);		
						if (sCurrentLine.contains("XXXenableOnShowField")){
							StringBuffer sbField = new StringBuffer();
							for(String t :lForm){
								if(t.equals("CreateDate")||t.equals("CreateBy")||t.equals("UpdateDate")||t.equals("UpdateBy")||t.equals("Versi")||t.equals("")){									
								}else{
									sbField.append(j1+"\\$('#"+Util.firstLowerr(t)+"').textbox('readonly', true);"+"\n");
								}								
							}
							String z = line.replaceAll("XXXenableOnShowField", sbField.toString());
							line =z;
						}
						
						//XXXenableOnAddField                  $('#userId').textbox('readonly', false);		
						if (sCurrentLine.contains("XXXenableOnAddField")){
							StringBuffer sbField = new StringBuffer();
							for(String t :lForm){
								if(t.equals("CreateDate")||t.equals("CreateBy")||t.equals("UpdateDate")||t.equals("UpdateBy")||t.equals("Versi")||t.equals("")){									
								}else{
									sbField.append(j1+"\\$('#"+Util.firstLowerr(t)+"').textbox('readonly', false);"+"\n");
								}								
							}
							String z = line.replaceAll("XXXenableOnAddField", sbField.toString());
							line =z;
						}
						
						//XXXenableOnEditField
						if (sCurrentLine.contains("XXXenableOnEditField")){
							StringBuffer sbField = new StringBuffer();
							for(String t :lForm){
								if(t.equals("CreateDate")||t.equals("CreateBy")||t.equals("UpdateDate")||t.equals("UpdateBy")||t.equals("Versi")||t.equals("")){									
								}else{
									String z =j1+"\\$('#"+Util.firstLowerr(t)+"').textbox('readonly', false);"+"\n";
									for(String k :lIds){
										System.out.println("t  : "+" k ");
										if(t.equals(k)){
											z =j1+"\\$('#"+Util.firstLowerr(t)+"').textbox('readonly', true);"+"\n";
										}
									}
									sbField.append(z);
								}								
							}
							String z = line.replaceAll("XXXenableOnEditField", sbField.toString());
							line =z;
						}
						
						
						//XXXrowDelete Id : row.Id
						if (sCurrentLine.contains("XXXrowDelete")){
							StringBuffer sbField = new StringBuffer();
							String x="";
							for(String k :lIds){
								sbField.append(j1+Util.firstLowerr(k)+" : row."+Util.firstLowerr(k)+",\n");
							}
							x = (sbField.toString()).substring(0,sbField.toString().length()-1);
							String z = line.replaceAll("XXXrowDelete", x);
							line =z;
						}
						//=======================================
						
						
						if (sCurrentLine.contains("XXXZ")){//XXXZ nama file jsp 
							String z = line.replaceAll("XXXZ", jspName.toLowerCase());
							line =z;
						}	
						sb.append(line+"\n");						
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				return sb.toString();
			}
	
	
	
//	*************************CONTROLLER*************************************
	public String readFileControllerTemp(String s,String sMapping, String sJsp,String sClassName, String daoName,List<String> lForm, List<String> lSearch, List<String> lIds ){
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;	
				try {
					String sCurrentLine;
					br = new BufferedReader(new FileReader(s));
					while ((sCurrentLine = br.readLine()) != null) {
						//System.out.println(sCurrentLine);XXXclass
						String line = sCurrentLine;
						if (sCurrentLine.contains("XXXclass")){
							String z = line.replaceAll("XXXclass",sClassName);
							line =z;							
						}
						
						if (sCurrentLine.contains("XXXmap")){
							String z = line.replaceAll("XXXmap",sMapping);
							line =z;							
						}
						
						if (sCurrentLine.contains("XXXview")){
							String z = line.replaceAll("XXXview",sJsp);
							line =z;							
						}
						
						if (sCurrentLine.contains("XXtbl")){
							String z = line.replaceAll("XXtbl",daoName);
							line =z;							
						}
						
						
						if (sCurrentLine.contains("XXFormFild")){
							StringBuffer sbField = new StringBuffer();
							for(String t :lForm){
								if(t.equals("CreateDate")||t.equals("CreateBy")||t.equals("UpdateDate")||t.equals("UpdateBy")||t.equals("Versi")||t.equals("")){									
								}else{
									sbField.append(j1+"tbl.set"+Util.firstUpper(t)+"(reg.getParameter("+d+Util.firstLowerr(t)+d+"));\n");
								}
							}
							String z = line.replaceAll("XXFormFild", sbField.toString());
							line =z;
						}	
						
						//parameter pencarian  String kriteria1 =reg.getParameter("param");
						if (sCurrentLine.contains("XXXStringParam")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							for(String t :lSearch){								
								sbx.append( "String "+Util.firstUpper(t)+"=reg.getParameter("+'"'+Util.firstUpper(t)+'"'+");\n");								
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXStringParam",x);
							line =z;							
						}
						
						
						//Pemaggilan criteria dari controller  h = dao.getByPerPage(kriteria1/*, kriteria2*/,loffset, row);  XXXCritParam
						if (sCurrentLine.contains("XXXCritParam")){ //tanpa String
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							for(String t :lSearch){								
								sbx.append(Util.firstUpper(t)+",");								
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXCritParam",x);
							line =z;							
						}
						

//						GetByID ambil param yang dikirim XXXParamId
						if (sCurrentLine.contains("XXXByIdParam")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
//							String x = "";
							for(String t :lIds){								
								sbx.append( "String "+Util.firstUpper(t)+"=reg.getParameter("+'"'+Util.firstLowerr(t)+'"'+");\n");								
							}
//							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXByIdParam",sbx.toString());
							line =z;							
						}
					
						if (sCurrentLine.contains("XXXParamId")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							for(String t :lIds){								
								sbx.append( Util.firstUpper(t)+",");								
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXParamId",x);
							line =z;							
						}
						
						sb.append(line+"\n");
					}
					System.out.println(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				return sb.toString();
			}
	
	
//***************** DAO****************************	
	public String readFileDAOTemp(String s,String tbl,List<String> lSearch, List<String> lIds){
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;	
				try {
					String sCurrentLine;
					br = new BufferedReader(new FileReader(s));
					while ((sCurrentLine = br.readLine()) != null) {
						//System.out.println(sCurrentLine);XXXclass
						String line = sCurrentLine;
						if (sCurrentLine.contains("XXXpojo")){
							String z = line.replaceAll("XXXpojo",tbl);
							line =z;							
						}
						
						if (sCurrentLine.contains("XXXKecil")){
							String z = line.replaceAll("XXXKecil",tbl.toLowerCase());
							line =z;
							
						}
						
//						+++++++++++++++++++++++ public Criteria getCriteria(String XXXgetCriParam){
						
						if (sCurrentLine.contains("XXXgetCriParam")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							for(String t :lSearch){								
								sbx.append("String "+Util.firstUpper(t)+",");								
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXgetCriParam",x);
							line =z;							
						}
						//public Criteria getCriteria
						if (sCurrentLine.contains("XXXgetCriteria")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							for(String t :lSearch){								
								sbx.append(j1+"if ("+Util.firstUpper(t)+".length()>0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(t)+'"'+", "+Util.firstUpper(t)+")); 	}\n");
							}
							String z = line.replaceAll("XXXgetCriteria",sbx.toString());
							line =z;
							
						}
						
						
						
						
						
						
						if (sCurrentLine.contains("XXX2getCriParam")){ //tanpa String
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							for(String t :lSearch){								
								sbx.append(Util.firstUpper(t)+",");								
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXX2getCriParam",x);
							line =z;							
						}
						
						
					

				
//						criteria BY ID
						if (sCurrentLine.contains("XXXByIdParam")){ //tanpa String
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							for(String t :lIds){								
								sbx.append("String "+Util.firstLowerr(t)+",");								
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXByIdParam",x);
							line =z;							
						}
											
						//untuk criteria if (kriteria1.length()>0){	criteria.add(Restrictions.eq("kriteria1", kriteria1)); 	}
						if (sCurrentLine.contains("XXXgetIdCriteria")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							for(String t :lIds){								
								sbx.append(j1+"if ("+Util.firstLowerr(t)+".length()>0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(t)+'"'+", "+Util.firstLowerr(t)+")); 	}\n");
							}
							String z = line.replaceAll("XXXgetIdCriteria",sbx.toString());
							line =z;							
						}
						
						
//					+++++++++++++++++++++++++++++
						sb.append(line+"\n");
					}
					System.out.println(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				return sb.toString();
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
	
//	
}
