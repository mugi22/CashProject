package com.id.kas.util.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblBranch;
import com.id.kas.pojo.TblGroup;
import com.id.kas.pojo.TblLookup;
import com.id.kas.pojo.TblProvinsi;
import com.id.kas.pojo.TblRekeningIA;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.pojo.dao.TblGroupDAO;
import com.id.kas.pojo.dao.TblProvinsiDAO;
import com.id.kas.pojo.dao.TblRekeningIADAO;
import com.id.kas.pojo.dao.TblUserDAO;
//import com.id.kas.pojo.dao.TblProvinsiDAO;
import com.id.kas.pojo.dao.TblLookupDAO;

@Controller
public class UtilityController {

	/**
	 * untuk mengisi combobox branch keseluruhan (tanpa filter)
	 * @param model
	 * @param session
	 * @param reg
	 * @return
	 */
	
	@RequestMapping(value="/comboAllBranch.htm", method=RequestMethod.POST)
    public @ResponseBody String comboAllBranch(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String param =reg.getParameter("param");
		 String selectVal =reg.getParameter("selected");
		 System.out.println("param  "+param+" selected "+selectVal);
		 Session sess = null;
		 String x="";String z ="";
		 try {
			sess = HibernateUtil.getSessionFactory().openSession();
			TblBranchDAO dao = new TblBranchDAO(sess);
			List<TblBranch> l = new ArrayList<TblBranch>();
			TblBranch branch = dao.getById(reg.getParameter("param"));
//			System.out.println(" ==================PARAM :"+reg.getParameter("param"));
			if(param.length()>0){
				l = dao.getByParent(/*branch.getParentId()*/param);
			}
			StringBuffer sb = new StringBuffer();
					sb.append("[");
			for(TblBranch tbl : l){
				String selected="";
				if(param.length()>0){
					if (tbl.getBranchCode().equals(selectVal)){
						selected = ","+'"'+"selected"+'"'+":true";
					}else{
						selected="";
					}
				}
				String item = "{"+'"'+"id"+'"'+":"+'"'+tbl.getBranchCode()+'"'+","+'"'+"text"+'"'+":"+'"'+tbl.getBranchCode()+" - "+tbl.getName()+'"'+selected+"},";	
				sb.append(item);
			}
			x = (sb.toString()).substring(0,sb.toString().length()-1);
			 z = x+"]";
			 sess.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 System.out.println(z);
	 return z;
	 }
	
	
	
	
	@RequestMapping(value="/comboAllKanwil.htm", method=RequestMethod.POST)
    public @ResponseBody String comboAllKanwil(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String param =reg.getParameter("param");
		 Session sess = null;
		 String x="";String z ="";
		 try {
			sess = HibernateUtil.getSessionFactory().openSession();
			TblBranchDAO dao = new TblBranchDAO(sess);
			List<TblBranch> l = new ArrayList<TblBranch>();
				l = dao.getByParent("00002");
			StringBuffer sb = new StringBuffer();
					sb.append("[");
			for(TblBranch tbl : l){
				String selected="";
				if(param.length()>0){
					if (tbl.getBranchCode().equals(reg.getParameter("param"))){
						selected = ","+'"'+"selected"+'"'+":true";
					}else{
						selected="";
					}
				}else{//untuk tambah -> set default combobox nya 0002
					if(tbl.getBranchCode().equals("00002")){
						selected = ","+'"'+"selected"+'"'+":true";
					}
				}
				String item = "{"+'"'+"id"+'"'+":"+'"'+tbl.getBranchCode()+'"'+","+'"'+"text"+'"'+":"+'"'+tbl.getBranchCode()+" - "+tbl.getName()+'"'+selected+"},";	
				sb.append(item);
			}
			x = (sb.toString()).substring(0,sb.toString().length()-1);
			 z = x+"]";
			 sess.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 System.out.println(z);
	 return z;
	 }
	 
	 
//******************provinsi************************
	 @RequestMapping(value="/comboProvinsi.htm", method=RequestMethod.POST)
	    public @ResponseBody String provinsi(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
			 String param =reg.getParameter("param");
			 Session sess = null;
			 String x="";String z ="";
			 try {
				sess = HibernateUtil.getSessionFactory().openSession();
				TblProvinsiDAO dao = new TblProvinsiDAO(sess);
				List<TblProvinsi> l = dao.getAll();
				StringBuffer sb = new StringBuffer();
						sb.append("[");
				for(TblProvinsi tbl : l){
					String selected="";
					if(param.length()>0){
						if (tbl.getKodeProvinsi().equals(reg.getParameter("param"))){
							selected = ","+'"'+"selected"+'"'+":true";
						}else{
							selected="";
						}
					}else{//untuk tambah -> set default combobox nya 0002
						if(tbl.getKodeProvinsi().equals("00")){
							selected = ","+'"'+"selected"+'"'+":true";
						}
					}
					String item = "{"+'"'+"id"+'"'+":"+'"'+tbl.getKodeProvinsi()+'"'+","+'"'+"text"+'"'+":"+'"'+tbl.getKodeProvinsi()+" - "+tbl.getNamaProvinsi()+'"'+selected+"},";	
					sb.append(item);
				}
				x = (sb.toString()).substring(0,sb.toString().length()-1);
				 z = x+"]";
				 sess.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return z;
		 }
		 
	 
	 /**
	  * Meng Clear kan table hasil /result
	  * @param model
	  * @param session
	  * @param reg
	  * @return
	  */
	 //	CLEAR TABLE
	 @RequestMapping(value="/listClear.htm", method=RequestMethod.POST)
     public @ResponseBody String userClear(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String sClear ="{"+'"'+"total"+'"'+":"+"0"+","+'"'+"rows"+'"'+":"+"[]}";
//		 {"total":0,"rows":[]}
		 return sClear;
	 }
	 
	 
	 
	 @RequestMapping(value="/cariUser.htm", method=RequestMethod.GET)
	 public String cariUser(){
		 return "cariUser";
	 }
	 
	 
//status pegawai
	 @RequestMapping(value="/comboLookup.htm", method=RequestMethod.POST)
	    public @ResponseBody String comboStatusPeg(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
			 String param =reg.getParameter("param");
			 String param2 =reg.getParameter("param2");
			 System.out.println("param2 : "+param2);
			 Session sess = null;
			 String x="";String z ="";
			 try {
				sess = HibernateUtil.getSessionFactory().openSession();
				TblLookupDAO dao = new TblLookupDAO(sess);
				List<TblLookup> l = dao.getBy(param2);
				StringBuffer sb = new StringBuffer();
						sb.append("[");
				for(TblLookup tbl : l){
					String selected="";
					if(param.length()>0){
						if (tbl.getLookupName().equals(reg.getParameter("param2"))){
							selected = ","+'"'+"selected"+'"'+":true";
						}else{
							selected="";
						}
					}else{//untuk tambah -> set default combobox nya 0002
						if(tbl.getLookupName().equals("00002")){
							selected = ","+'"'+"selected"+'"'+":true";
						}
					}
					String item = "{"+'"'+"id"+'"'+":"+'"'+tbl.getLookupValue()+'"'+","+'"'+"text"+'"'+":"+'"'+tbl.getLookupValue()+" - "+tbl.getLookupLabel()+'"'+selected+"},";	
					sb.append(item);
				}
				x = (sb.toString()).substring(0,sb.toString().length()-1);
				 z = x+"]";
				 sess.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return z;
		 }
		 

//	 group
	 @RequestMapping(value="/comboGroup.htm", method=RequestMethod.POST)
	  public @ResponseBody String comboGroup(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
			 String param =reg.getParameter("param");
			 Session sess = null;
			 String x="";String z ="";
			 try {
				sess = HibernateUtil.getSessionFactory().openSession();
				TblGroupDAO dao = new TblGroupDAO(sess);
				List<TblGroup> l = dao.getAll();//getBy(param2);
				StringBuffer sb = new StringBuffer();
						sb.append("[");
				for(TblGroup tbl : l){
					String selected="";
					if(param.length()>0){
						if (tbl.getGroupId().equals(reg.getParameter("param"))){
							selected = ","+'"'+"selected"+'"'+":true";
						}else{
							selected="";
						}
					}else{//untuk tambah -> set default combobox nya 0002
						if(tbl.getGroupId().equals("00002")){
							selected = ","+'"'+"selected"+'"'+":true";
						}
					}
					String item = "{"+'"'+"id"+'"'+":"+'"'+tbl.getGroupId()+'"'+","+'"'+"text"+'"'+":"+'"'+tbl.getGroupName()+'"'+selected+"},";	
					sb.append(item);
				}
				x = (sb.toString()).substring(0,sb.toString().length()-1);
				 z = x+"]";
				 sess.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return z;
		 }
		
	 
/*
 * 	 
 */
	 @RequestMapping(value="/sessionExpire.htm", method=RequestMethod.GET)
	  public  String sessionExpire(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 //session.invalidate();
		 return "sessionExpire";
	 }
	 
	 @RequestMapping(value="/getParent.htm", method=RequestMethod.GET)
	 public @ResponseBody String getParent(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String kdUnit = reg.getParameter("kodeUnit");
		 System.out.println("getParent..............................................     "+kdUnit);
		 Session sess = HibernateUtil.getSessionFactory().openSession();
		 TblBranchDAO branchDAO = new TblBranchDAO(sess);
		 TblBranch branch = branchDAO.getById(kdUnit);
		 sess.close();
	 	return branch.getParentId();
	 }
	 
	 
	 
	 @RequestMapping(value="/cekUserBaranch.htm", method=RequestMethod.GET)
	 public @ResponseBody String cekUserBaranch(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String userId = reg.getParameter("nik");
		 String sUID =  reg.getParameter("UID");
		 System.out.println("userId..............................................     "+userId+" sUID "+sUID);
		 String message ="";
		 String a="";
		 try {
			 Session sess = HibernateUtil.getSessionFactory().openSession();
			 TblUserDAO tblUserDAO = new TblUserDAO(sess);
			 TblUser tblUser = tblUserDAO.getById(userId);
			 if(tblUser.getUserId().length()<1){
				 sess.close();
				 return "{}";
			 }
			 //cek tblUser mempunyai unit yang sama dengan user penginput (UID)
			 TblUser tblUserInput = tblUserDAO.getById(sUID);
			 
			 if(tblUser.getBranchCode().equals(tblUserInput.getBranchCode())){
				 System.out.println("ADA........................");
				 Gson gson = new  Gson();
				 message = gson.toJson(tblUser);
				 /** * tambhakan nama branc  */
				 TblBranchDAO branchDAO = new TblBranchDAO(sess);
				 TblBranch branch = branchDAO.getById(tblUser.getBranchCode());
				 a = message.replace("}", ","+'"'+"branchName"+'"'+":"+'"'+branch.getName()+'"'+"}");
		
			 }else{
				 //message="User Tidak Ada Untuk Unit ......."+tblUserInput.getBranchCode();
				 a="{}";
				 System.out.println("TIDAK.......................................");
			 }
			 		sess.close();
			 
	            /**  BILA ADA PERUBAHAN DATA JSON
	             * String a = s.replace("}", ","+'"'+"groupName"+'"'+":"+'"'+group.getGroupName()+'"'+","+'"'+"menuName"+'"'+":"+'"'+menu.getMenuName()+'"'+"},");
//		
	            String x = changeJson(h, sess);
	            sess.close();
	        	result ="{"+'"'+"total"+'"'+":"+h.get("total")+","+'"'+"rows"+'"'+":["+x+']'+'}';
	            */
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(a);
	 	return a;//;branch.getParentId();
	 }
	 
	 @RequestMapping(value="/cekRekening.htm", method=RequestMethod.GET)
	 public @ResponseBody String cekRekening(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String norek = reg.getParameter("norek");
		 String sUID =  reg.getParameter("UID");
		 
		 String rek = null;
		 Session sess = null;
		 try {
			sess = HibernateUtil.getSessionFactory().openSession();
			TblRekeningIADAO rekeningIADAO = new TblRekeningIADAO(sess);
			TblRekeningIA ia = rekeningIADAO.getById(norek);
			if(ia.getNorek().length()>0){
				rek = new Gson().toJson(ia);
			}else{
				rek="{}";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		 System.out.println("norek  "+norek);
		 sess.close();
		 System.out.println("rek "+rek);
		 return rek;
	 }
	 
	 //Ambil Branch berdasarkan ID
	 
	 @RequestMapping(value="/getBranchByID.htm", method=RequestMethod.GET)
	 public @ResponseBody String getBranchByID(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
//		 String sUID =  reg.getParameter("UID");
		 String branchCode = reg.getParameter("branchCode");
		 Session sess = null;
		 String sBranch="";
		 try {
			 sess = HibernateUtil.getSessionFactory().openSession();
			 TblBranchDAO branchDAO = new TblBranchDAO(sess);
			 TblBranch branch = branchDAO.getById(branchCode);
			 if(branch.getName().length()> 0){
				 Gson gson = new Gson();
				 sBranch = gson.toJson(branch);
			 }else{
				 sBranch="{}";
			 }
			sess.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 System.out.println(sBranch);
		return sBranch;
	 }
	 
	 
	 
	 
}
