package com.id.kas.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

public class TempCreate {

	/**
	 * @param args
	 */
	private static String p="'";
	private static char d='"';
	private static String k=",";
	private static String j1="                    ";
	public static void main(String[] args) {
		TempCreate tem = new TempCreate();
		
	}

	//JSP
	public String readFile(String s,List<String> lForm,List<String> lCol,List<String> lList,String jspName,List<String> lSearch,List<String>  lIds,List<String> lTipes){
		
//		for(String v:lIds){
//			System.out.println(" v : "+v);
//		}
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
							int i = 0;
							for(String t :lSearch){
								if(t.equals("Y")){
                                 sbField.append(j1+"<tr><td><label>"+Util.firstUpper(lCol.get(i))+"</label> : "+"<input name="+'"'+
                                                Util.firstUpper(lCol.get(i))+'"'+" type="+'"'+"text"+'"'+" id="+'"'+lCol.get(i)+'"'+" size="+'"'+"30"+'"'+
                                                " maxlength="+'"'+"30"+'"'+"></td></tr>\n");
								}
							i++;
							}
							String z = line.replaceAll("XXXsearch", Util.firstLowerr(sbField.toString()));
							line =z;
						}
                        //PAMEETER Search   param=' + $('#idSearch').val();//+'&param2='++ $('#idSearch2').val();	//'seqName='$('#SeqName').val()&+'SeqNum='$('#SeqNum').val();
						if (sCurrentLine.contains("XXXparamSearch")){
							StringBuffer sbCari = new StringBuffer();  
							String x = "";
							int i = 0;
							for(String t :lSearch){
								if(t.equals("Y")){
								sbCari.append("'"+Util.firstUpper(lCol.get(i))+"="+"'"+"+"+"\\$"+"("+"'"+"#"+Util.firstUpper(lCol.get(i))+"'"+").val()"+"+"+'"'+"&"+'"'+"+");
								}
								i++;
                            }
							x = (sbCari.toString()).substring(0,sbCari.toString().length()-5);
							String z = line.replaceAll("XXXparamSearch", x+'+'+'"'+"&"+'"'+"+"+'"'+"userId="+'"'+'+'+'"'+"\\$"+"{userId}"+'"'+';');
							line =z;
						}
						
						//LIST
						if (sCurrentLine.contains("XXXfield")){
							StringBuffer sbField = new StringBuffer();
							int i =0;
							for(String t :lList){
								if(t.equals("Y")){
									if(lTipes.get(i).equals("long")||lTipes.get(i).equals("BigDecimal")){
										sbField.append(j1+"<th field="+'"'+Util.firstLowerr(lCol.get(i))+'"'+" width="+'"'+"100"+'"'+ "sortable="+'"'+"true"+'"'+
												"data-options="+'"'+"formatter:function(value, row){return accounting.formatNumber(row."+Util.firstLowerr(lCol.get(i))+",0,'.',','); }"+'"'+
												"align="+'"'+"right"+'"'+">"+lCol.get(i)+"</th> "+"\n");
									}else{
									sbField.append(j1+"<th field="+'"'+Util.firstLowerr(lCol.get(i))+'"'+" width="+'"'+"100"+'"'+ "sortable="+'"'+"true"+'"'+">"+lCol.get(i)+"</th> "+"\n");
									}
								}
								i++;
								}
							
							String z = line.replaceAll("XXXfield", Util.firstLowerr(sbField.toString()));
							line =z;
						}
						
						//FORM
						if (sCurrentLine.contains("XXXlist")){
							StringBuffer sbField = new StringBuffer();
							String trtd="<tr><td>";
							String trtdx="</td></tr>";
							int i =0;
							for(String t :lForm){
								if(t.equals("Y")){
									if(lCol.get(i).equals("CreateDate")||lCol.get(i).equals("CreateBy")||lCol.get(i).equals("UpdateDate")||lCol.get(i).equals("UpdateBy")||lCol.get(i).equals("Versi")||lCol.get(i).equals("")){									
									}else if(lTipes.get(i).equals("String")){
										sbField.append(j1+trtd+"<div class="+d+"fitem"+d+">	<label>"+lCol.get(i)+"</label> :<input name="+d+Util.firstLowerr(lCol.get(i))+d+"	class="+d+"easyui-textbox"+d+" id="+d+Util.firstLowerr(lCol.get(i))+d+">"+trtdx+"	</div>\n");
									}else if(lTipes.get(i).equals("long")||lTipes.get(i).equals("BigDecimal")){
										sbField.append(j1+trtd+"<div class="+d+"fitem"+d+">	<label>"+lCol.get(i)+"</label> :<input name="+d+Util.firstLowerr(lCol.get(i))+d+"	class="+d+"easyui-numberbox"+d+
												" "+"data-options="+'"'+"min:0,precision:0,groupSeparator:','"+'"'
												+" id="+d+Util.firstLowerr(lCol.get(i))+d+">"+trtdx+"	</div>\n");
									}else if(lTipes.get(i).equals("Date")||lTipes.get(i).equals("date")){
										sbField.append(j1+trtd+"<div class="+d+"fitem"+d+">	<label>"+lCol.get(i)+"</label> :<input name="+d+Util.firstLowerr(lCol.get(i))+d+"	class="+d+"easyui-datebox"+d+
												" "+"data-options="+'"'+"formatter:myformatter,parser:myparser"+'"'
												+" id="+d+Util.firstLowerr(lCol.get(i))+d+">"+trtdx+"	</div>\n");
									}
								}
								i++;
							}
							String z = line.replaceAll("XXXlist", sbField.toString());
							line =z;
						}	
						
						//ENABLE/DISABLE FIELD FORM XXXenableField
						if (sCurrentLine.contains("XXXenableOnShowField")){
							StringBuffer sbField = new StringBuffer();
							int i=0;
							for(String t :lForm){
								if(t.equals("Y")){
									if(lCol.get(i).equals("CreateDate")||lCol.get(i).equals("CreateBy")||lCol.get(i).equals("UpdateDate")||lCol.get(i).equals("UpdateBy")||lCol.get(i).equals("Versi")||lCol.get(i).equals("")){									
									}else{
										sbField.append(j1+"\\$('#"+Util.firstLowerr(lCol.get(i))+"').textbox('readonly', true);"+"\n");
									}	
								}
								i++;
							}
							String z = line.replaceAll("XXXenableOnShowField", sbField.toString());
							line =z;
						}
						
						//XXXenableOnAddField                  $('#userId').textbox('readonly', false);		
						if (sCurrentLine.contains("XXXenableOnAddField")){
							StringBuffer sbField = new StringBuffer();
							int i=0;
							for(String t :lForm){
								if(t.equals("Y")){
									if(lCol.get(i).equals("CreateDate")||lCol.get(i).equals("CreateBy")||lCol.get(i).equals("UpdateDate")||lCol.get(i).equals("UpdateBy")||lCol.get(i).equals("Versi")||lCol.get(i).equals("")){									
									}else{
										sbField.append(j1+"\\$('#"+Util.firstLowerr(lCol.get(i))+"').textbox('readonly', false);"+"\n");
									}
								}
								i++;
							}
							String z = line.replaceAll("XXXenableOnAddField", sbField.toString());
							line =z;
						}
						
						//XXXenableOnEditField
						if (sCurrentLine.contains("XXXenableOnEditField")){
							StringBuffer sbField = new StringBuffer();
							int i = 0;
							for(String t :lForm){
							if(t.equals("Y")){
								if(lCol.get(i).equals("CreateDate")||lCol.get(i).equals("CreateBy")||lCol.get(i).equals("UpdateDate")||lCol.get(i).equals("UpdateBy")||lCol.get(i).equals("Versi")||lCol.get(i).equals("")){									
								}else{
									String z =j1+"\\$('#"+Util.firstLowerr(lCol.get(i))+"').textbox('readonly', false);"+"\n";
//									for(String k :lIds){
										System.out.println("t   : "+t+" k :"+k+" lIds.get(i) "+lIds.get(i)); 
										if(t.equals(/*k*/lIds.get(i))){											
											z =j1+"\\$('#"+Util.firstLowerr(lCol.get(i))+"').textbox('readonly', true);"+"\n";
										}
//									}
									sbField.append(z);
								}	
								i++;
							}
							}
							String z = line.replaceAll("XXXenableOnEditField", sbField.toString());
							line =z;
						}						
						//XXXrowDelete Id : row.Id
						if (sCurrentLine.contains("XXXrowDelete")){
							StringBuffer sbField = new StringBuffer();
							String x="";
							int i =0;
							for(String t :lIds){
								if(t.equals("Y")){
								sbField.append(j1+Util.firstLowerr(lCol.get(i))+" : row."+Util.firstLowerr(lCol.get(i))+",\n");
								}
								i++;
							}
							x = (sbField.toString()).substring(0,sbField.toString().length()-2);
							String z = line.replaceAll("XXXrowDelete", x);
							line =z;
						}
						if (sCurrentLine.contains("XXXZ")){//XXXZ nama file jsp 
							String z = line.replaceAll("XXXZ", jspName/*.toLowerCase()*/);
							line =z;
						}	
//						sb.append(line+"\n");
						
						
						//XXXidRequired
						if (sCurrentLine.contains("XXXREQ")){
							StringBuffer sbField = new StringBuffer();
							int i = 0;
							for(String t :lForm){
							if(t.equals("Y")){
								if(lCol.get(i).equals("CreateDate")||lCol.get(i).equals("CreateBy")||lCol.get(i).equals("UpdateDate")||lCol.get(i).equals("UpdateBy")||lCol.get(i).equals("Versi")||lCol.get(i).equals("")){									
								}else{
									String x ="";//j1+"\\$('#"+Util.firstLowerr(lCol.get(i))+"').textbox('readonly', false);"+"\n";
//									
										System.out.println("ttttttt   : "+t+" kkkkkk :"+k+" lIds.get(i) "+lIds.get(i)); 
										if(t.equals(lIds.get(i))){											
											x =j1+"\\$('#"+Util.firstLowerr(lCol.get(i))+"').textbox({   required: t});"+"\n";
											sbField.append(x);
											System.out.println("xxxx : "+x);
										}										
								}	
								i++;
							}
							}
							String z = line.replaceAll("XXXREQ", sbField.toString());
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
	

	//REPORT JSP
		public String readFileReportJsp(String s,List<String> lForm,List<String> lCol,List<String> lList,String jspName,List<String> lSearch,List<String>  lIds,List<String> lTipes){
			StringBuffer sb = new StringBuffer();
			BufferedReader br = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(s));
				while ((sCurrentLine = br.readLine()) != null) {
					String line = sCurrentLine;
					//TABLE HEADER
					if (sCurrentLine.contains("XXXDatafield")){
						StringBuffer sbField = new StringBuffer();
						int i =0;
						for(String t :lList){						
							sbField.append(j1+"<th"+" style="+d+"font-weight:bold"+'"'+" align="+d+"center"+d+" class="+d+"cfg_header"+d+" >"+lCol.get(i)+"</th> "+"\n");
							i++;
						}					
						String z = line.replaceAll("XXXDatafield", Util.firstLowerr(sbField.toString()));
						line =z;
					}
					//TABLE DATA
					if (sCurrentLine.contains("XXXjsonfield")){
						StringBuffer sbField = new StringBuffer();
						//"<td>"+ value.kodeKelompok + "</td>"+
						int i =0;
						for(String t :lList){	
							if(lTipes.get(i).equals("long")||lTipes.get(i).equals("int")||lTipes.get(i).equals("BigDecimal")||lTipes.get(i).equals("Long")||lTipes.get(i).equals("Int")){
								sbField.append("        "+d+"<td align='right'> <div class='cfg_detail'><font class='f_boldhd'>" +d+" + "+"accounting.formatNumber(value."+Util.firstLowerr(lCol.get(i))+",0,'.',',')" +" + "+d+"</font></div></td>"+d+"+\n");
							}else{
								sbField.append("        "+d+"<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +d+" + "+" value."+Util.firstLowerr(lCol.get(i))+" + "+d+"</font></div></td>"+d+"+\n");
							}
							i++;
						}					
						String z = line.replaceAll("XXXjsonfield", Util.firstLowerr(sbField.toString()));
						line =z;
					}
					
					
					
					//XXXRepJSP
					if (sCurrentLine.contains("XXXRepJSP")){
						StringBuffer sbField = new StringBuffer();
						String z = line.replaceAll("XXXRepJSP", jspName);
						line =z;
					}
					
					
					sb.append(line+"\n");	
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return sb.toString();
		}	
			
	
	
	
//	*************************CONTROLLER*************************************
	public String readFileControllerTemp(String s,String sMapping, String sJsp,String sClassName, String daoName,List<String> lCol,List<String> lForm, List<String> lSearch, List<String> lIds,List<String> lTipes ){
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;	
				try {
					String sCurrentLine;
					br = new BufferedReader(new FileReader(s));
					while ((sCurrentLine = br.readLine()) != null) {
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
							int i =0;
							for(String t :lForm){
								if(t.equals("Y")){	
									if(lCol.get(i).equals("CreateDate")||lCol.get(i).equals("CreateBy")||lCol.get(i).equals("UpdateDate")||lCol.get(i).equals("UpdateBy")||lCol.get(i).equals("Versi")||t.equals("")){									
									}else
									if (lTipes.get(i).equals("String")){
										sbField.append(j1+"tbl.set"+Util.firstUpper(lCol.get(i))+"(reg.getParameter("+d+Util.firstLowerr(lCol.get(i))+d+"));\n");
									}
									
									if (lTipes.get(i).equals("long")){
										sbField.append(j1+"tbl.set"+Util.firstUpper(lCol.get(i))+"(Long.parseLong((reg.getParameter("+d+Util.firstLowerr(lCol.get(i))+d+"))));\n");
									}
									
									if (lTipes.get(i).equals("BigDecimal")){
										sbField.append(j1+"tbl.set"+Util.firstUpper(lCol.get(i))+"(new BigDecimal((reg.getParameter("+d+Util.firstLowerr(lCol.get(i))+d+"))));\n");
									}
									
									if (lTipes.get(i).equals("Date")||lTipes.get(i).equals("Date")){
										sbField.append(j1+"tbl.set"+Util.firstUpper(lCol.get(i))+"(formatter.parse(reg.getParameter("+d+Util.firstLowerr(lCol.get(i))+d+")));\n");
									}
								}
								i++;
							}
							String z = line.replaceAll("XXFormFild", sbField.toString());
							line =z;
						}	
						
						//parameter pencarian  String kriteria1 =reg.getParameter("param");//perbaiki disinai
						if (sCurrentLine.contains("XXXStringParam")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							int i = 0;
							for(String t :lSearch){		
								if(t.equals("Y")){
									if(lTipes.get(i).equals("String")){
										sbx.append(j1+ "String "+Util.firstUpper(lCol.get(i))+"=reg.getParameter("+'"'+Util.firstUpper(lCol.get(i))+'"'+");\n");	
									}else if(lTipes.get(i).equals("Date")){
										sbx.append(j1+"String "+Util.firstUpper(lCol.get(i))+" ="+'"'+"00-00-0000"+'"'+";\n");
										sbx.append(j1+"if(reg.getParameter("+'"'+Util.firstUpper(lCol.get(i))+'"'+")"+'.'+"length()>0){\n");
										sbx.append(j1+Util.firstUpper(lCol.get(i))+" = "+"(reg.getParameter("+'"'+Util.firstUpper(lCol.get(i))+'"'+"));\n"+j1+"}\n");
									}
									
									else/*(lTipes.get(i).equals("long"))*/{
										sbx.append(j1+"String "+Util.firstUpper(lCol.get(i))+" ="+'"'+"0"+'"'+";\n");
										sbx.append(j1+"if(reg.getParameter("+'"'+Util.firstUpper(lCol.get(i))+'"'+")"+'.'+"length()>0){\n");
										sbx.append(j1+Util.firstUpper(lCol.get(i))+" = "+"(reg.getParameter("+'"'+Util.firstUpper(lCol.get(i))+'"'+"));\n"+j1+"}\n");
									}
								}
								i++;
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
							int i = 0;
							for(String t :lSearch){	
								if(t.equals("Y")){
									if(lTipes.get(i).equals("String")){
										sbx.append(Util.firstUpper(lCol.get(i))+",");	
									}else
									if(lTipes.get(i).equals("long")){
										sbx.append("Long.parseLong("+Util.firstUpper(lCol.get(i))+")"+",");	
									}else
									if(lTipes.get(i).equals("BigDecimal")){
										sbx.append("new BigDecimal("+Util.firstUpper(lCol.get(i))+")"+",");	
									}else
									if(lTipes.get(i).equals("Date")){
										sbx.append("formatter.parse("+Util.firstUpper(lCol.get(i))+"),");	
									}
								}
								i++;
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXCritParam",x);
							line =z;							
						}
						

//						GetByID ambil param yang dikirim XXXParamId
						if (sCurrentLine.contains("XXXByIdParam")){//controller
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
//							String x = "";
							int i = 0;
							for(String t :lIds){
								if(t.equals("Y")){
									if(lTipes.get(i).equals("String")||lTipes.get(i).equals("Date")){
										sbx.append( j1+"String"/*lTipes.get(i)*/+" "+Util.firstUpper(lCol.get(i))+"=reg.getParameter("+'"'+Util.firstLowerr(lCol.get(i))+'"'+");\n");								
									}else{
										sbx.append(j1+"String "+Util.firstUpper(lCol.get(i))+"="+'"'+"0"+'"'+";\n");
										sbx.append(j1+"if(reg.getParameter("+'"'+Util.firstLowerr(lCol.get(i))+'"'+")"+'.'+"length()>0){\n");
										sbx.append(j1+Util.firstUpper(lCol.get(i))+" = "+"(reg.getParameter("+'"'+Util.firstLowerr(lCol.get(i))+'"'+"));\n"+j1+"}\n");
										//Tarif = reg.getParameter("Tarif");	
									}
								}
								i++;
							}
//							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXByIdParam",sbx.toString());
							line =z;							
						}
					
						if (sCurrentLine.contains("XXXParamId")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							int i = 0;
							for(String t :lIds){
								if(t.equals("Y")){
									if(lTipes.get(i).endsWith("String")||lTipes.get(i).equals("Date")){
										sbx.append( Util.firstUpper(lCol.get(i))+",");
									}
									
									if(lTipes.get(i).endsWith("long")){
										sbx.append("Long.parseLong("+ Util.firstUpper(lCol.get(i))+"),");
									}
									
									if(lTipes.get(i).endsWith("BigDecimal")){
										sbx.append("new BigDecimal("+ Util.firstUpper(lCol.get(i))+"),");
									}
									
								}
								i++;
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXParamId",x);
							line =z;							
						}
						
						sb.append(line+"\n");
					}
//					System.out.println(sb.toString());
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
	public String readFileDAOTemp(String s,String tbl,List<String> lCol,List<String> lSearch, List<String> lIds,List<String> lTipes){
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
							int i = 0;
							for(String t :lSearch){	
								if(t.equals("Y")){
									sbx.append(/*"String "*/lTipes.get(i)+" "+Util.firstUpper(lCol.get(i))+",");	
								}
								i++;
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXgetCriParam",x);
							line =z;							
						}
						//public Criteria getCriteria
						if (sCurrentLine.contains("XXXgetCriteria")){
							StringBuffer sbx = null ;
							int i =0;
							sbx= new StringBuffer();
							for(String t :lSearch){	
								if(t.equals("Y")){	
	//								sbx.append(j1+"if ("+Util.firstUpper(t)+".length()>0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(t)+'"'+", "+Util.firstUpper(t)+")); 	}\n");
									if(lTipes.get(i).equals("String")){
										sbx.append(j1+"if ("+Util.firstUpper(lCol.get(i))+".length()>0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(lCol.get(i))+'"'+", "+Util.firstUpper(lCol.get(i))+")); 	}\n");
									}
									if(lTipes.get(i).equals("long")){
										sbx.append(j1+"if ("+Util.firstUpper(lCol.get(i))+">0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(lCol.get(i))+'"'+", "+Util.firstUpper(lCol.get(i))+")); 	}\n");
									}
									
									if(lTipes.get(i).equals("BigDecimal")){
										sbx.append(j1+"if ("+Util.firstUpper(lCol.get(i))+".doubleValue()"+">0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(t)+'"'+", "+Util.firstUpper(lCol.get(i))+")); 	}\n");
									}
									
									if(lTipes.get(i).equals("Date")){
										sbx.append(j1+"try {\n"+j1+"if ("+Util.firstUpper(lCol.get(i))+".after(formatter.parse("+'"'+"00-00-0000"+'"'+"))){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(lCol.get(i))+'"'+", "+Util.firstUpper(lCol.get(i))+")); 	}\n");
									    sbx.append(j1+"} catch (ParseException e) {\n"+j1+"e.printStackTrace();\n"+j1+"}");
									}
								}
								i++;
							}
							String z = line.replaceAll("XXXgetCriteria",sbx.toString());
							line =z;
							
						}
					if (sCurrentLine.contains("XXX2getCriParam")){ //tanpa String
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							int i=0;
							for(String t :lSearch){	
								if(t.equals("Y")){
									sbx.append(Util.firstUpper(lCol.get(i))+",");										
								}
								i++;
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXX2getCriParam",x);
							line =z;							
						}
						
				
//						criteria BY ID
						if (sCurrentLine.contains("XXXByIdParam")){ //tanpa String //DAO
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							String x = "";
							int i =0;
							for(String t :lIds){
								if(t.equals("Y")){
//									System.out.println("t :"+t+" ---- "+lTipes.get(i));
									sbx.append(/*"String "*/lTipes.get(i)+"  "+Util.firstLowerr(lCol.get(i))+",");	
								}
								i++;
							}
							x = (sbx.toString()).substring(0,sbx.toString().length()-1);
							String z = line.replaceAll("XXXByIdParam",x);
							line =z;							
						}
											
						//untuk criteria if (kriteria1.length()>0){	criteria.add(Restrictions.eq("kriteria1", kriteria1)); 	}
						if (sCurrentLine.contains("XXXgetIdCriteria")){
							StringBuffer sbx = null ;
							sbx= new StringBuffer();
							int i = 0;
							for(String t :lIds){
								if(t.equals("Y")){
									if(lTipes.get(i).equals("String")||lTipes.get(i).equals("Date")){
										sbx.append(j1+"if ("+Util.firstLowerr(lCol.get(i))+".length()>0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(lCol.get(i))+'"'+", "+Util.firstLowerr(lCol.get(i))+")); 	}\n");
									}
									if(lTipes.get(i).equals("long")||lTipes.get(i).equals("int")){
										sbx.append(j1+"if ("+Util.firstLowerr(lCol.get(i))+">0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(lCol.get(i))+'"'+", "+Util.firstLowerr(lCol.get(i))+")); 	}\n");
									}
									
									if(lTipes.get(i).equals("BigDecimal")){
										sbx.append(j1+"if ("+Util.firstLowerr(lCol.get(i))+".doubleValue()"+">0){criteria.add(Restrictions.eq("+'"'+Util.firstLowerr(lCol.get(i))+'"'+", "+Util.firstLowerr(lCol.get(i))+")); 	}\n");
									}
									
									
								}
								i++;
							}
							String z = line.replaceAll("XXXgetIdCriteria",sbx.toString());
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
		
	
	public void createFile(String fileName,String fileText) {
		try {
			File file = new File(fileName);
			if(file.createNewFile()){
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(fileText);
				output.close();
		        System.out.println("Success..........................................!"+fileName);
			}
		         else{
		         System.out.println("Error ,"+fileName+" file already exists.");
		    }
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	
}
