package com.id.kas.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblLookup;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblLookupDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class LookupController  extends AbstractListScreen{
	@RequestMapping(value="/lookup.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session,  HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/lookup.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg, res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "lookup";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/lookupListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String lookupListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		String LookupValue=reg.getParameter("LookupValue");
		String LookupName=reg.getParameter("LookupName");	
		
		 String userId = reg.getParameter("userId");
         String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("userId"+userId);
         
       
         
         model.put("session", ses);
         if(!cekValidSession(session,userId)){
        	 return "[]";
         }
         String result="";
         int row = Integer.parseInt(reg.getParameter("rows"));
         int loffset = (Integer.parseInt(reg.getParameter("page"))-1)*row;
         Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
         
         Session sess = null;
         try {
        	long rowCount=0;
			sess = HibernateUtil.getSessionFactory().openSession();
			TblLookupDAO dao = new TblLookupDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblLookup> l = new ArrayList<TblLookup>();
				h = dao.getByPerPage(LookupValue,LookupName,loffset, row);
			sess.close();
            result = gson.toJson(h);
            System.out.println(result);
            
            /**  BILA ADA PERUBAHAN DATA JSON
            String x = changeJson(h, sess);
            sess.close();
        	result ="{"+'"'+"total"+'"'+":"+h.get("total")+","+'"'+"rows"+'"'+":["+x+']'+'}';
            */
            
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}         
         return result;
     }

// *********************ADD***********************
 @RequestMapping(value="/lookupAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
	 String userId = reg.getParameter("userId");
     String ses = (String) session.getAttribute("session"+userId);
     TblUser user = (TblUser) session.getAttribute("user"+userId);
//		 TblUser user = getUser(session,userId);	
		 
		 if(!cekValidSession(session,userId)){
        	 return "fail";
         }
         Session sess = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               sess = HibernateUtil.getSessionFactory().openSession();
               TblLookupDAO dao = new TblLookupDAO(sess);
               TblLookup tbl = new TblLookup();
                    tbl.setLookupLabel(reg.getParameter("lookupLabel"));
                    tbl.setLookupValue(reg.getParameter("lookupValue"));
                    tbl.setNoUrut(new BigDecimal(reg.getParameter("noUrut")));
                    tbl.setLookupName(reg.getParameter("lookupName"));
                             
               tbl.setCreateBy(user.getUserId());
               tbl.setCreateDate(new Date());
               
               sess.beginTransaction();
               dao.insert(tbl);
               sess.getTransaction().commit();
               simpanLog(user.getUserId(),gson.toJson(tbl));
               sess.close();
               x=gson.toJson("SUKSES");
         }catch(Exception e){
             x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }

//**************************************EDIT*************************************
//	 EDIT	 
	 @RequestMapping(value="/lookupEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String lookupEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String LookupValue=reg.getParameter("lookupValue");
String LookupName=reg.getParameter("lookupName");

String userId = reg.getParameter("userId");
String ses = (String) session.getAttribute("session"+userId);
TblUser user = (TblUser) session.getAttribute("user"+userId);
//		 TblUser user = getUser(session);
		 if(!cekValidSession(session,userId)){
        	 return "fail";
         }
         
         Session sess = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               sess = HibernateUtil.getSessionFactory().openSession();
               TblLookupDAO dao = new TblLookupDAO(sess);
               TblLookup tbl = dao.getById(LookupValue,LookupName);
                String tblOld = gson.toJson(tbl);
                    tbl.setLookupLabel(reg.getParameter("lookupLabel"));
                    tbl.setLookupValue(reg.getParameter("lookupValue"));
                    tbl.setNoUrut(new BigDecimal(reg.getParameter("noUrut")));
                    tbl.setLookupName(reg.getParameter("lookupName"));
               
               tbl.setUpdateBy(user.getUserId());
               tbl.setUpdateDate(new Date());
               
               sess.beginTransaction();
               dao.update(tbl);
               sess.getTransaction().commit();
                simpanLog(user.getUserId(),"MODIFY  : "+gson.toJson(tbl)+" OLD "+tblOld);
               sess.close();
               x=gson.toJson("UPDATE SUKSES");
         }catch(Exception e){
             x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }
	 
//	***********************************DELETE**************************************** 
	 @RequestMapping(value="/lookupDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String lookupDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String LookupValue=reg.getParameter("lookupValue");
String LookupName=reg.getParameter("lookupName");
String userId = reg.getParameter("userId");
String ses = (String) session.getAttribute("session"+userId);
TblUser user = (TblUser) session.getAttribute("user"+userId);
//		 String sId = reg.getParameter("param"); //param sesuaikan dengan yg di jsp
//		 TblUser user = getUser(session);
		 
		 if(!cekValidSession(session,userId)){
        	 return "fail";
         }
         Session sess = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         try {
               sess = HibernateUtil.getSessionFactory().openSession();
               TblLookupDAO dao = new TblLookupDAO(sess);
               TblLookup tbl = dao.getById(LookupValue,LookupName);
               String tblDel = gson.toJson(tbl);
               sess.beginTransaction();
               dao.delete(tbl);
               sess.getTransaction().commit();
               simpanLog(user.getUserId(),"DELETE  : "+tblDel);
               sess.close();
               h.put("success", true);
               x=gson.toJson(h);
         }catch(Exception e){
        	 x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }

//----------BILA ADA PERUBAHAN DATA JSON, RUBAH DI SINI------------------------------------------
//	public String changeJson(Map<String,Object> result, Session sess){//nemabhakan field nama group dan nama menu
//		List<TblPriviledge> listPri = (List<TblPriviledge>) result.get("rows");
////		List<TblPriviledge> priv = (List<TblPriviledge>) h.get("rows");
//		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
//		StringBuffer sb = new StringBuffer();
//		for(TblPriviledge pr : listPri){
//			String s = gson.toJson(pr);			
//			TblGroupDAO groupDAO = new TblGroupDAO(sess);
//			TblGroup group = groupDAO.getById(pr.getGroupId());
//			TblMenuDAO menuDAO = new TblMenuDAO(sess);
//			TblMenu menu =  menuDAO.getById(pr.getMenuId());
//			String a = s.replace("}", ","+'"'+"groupName"+'"'+":"+'"'+group.getGroupName()+'"'+","+'"'+"menuName"+'"'+":"+'"'+menu.getMenuName()+'"'+"},");
//			sb.append(a);
//		}
//		String x="";
//		if(sb.toString().length()>0){
//			x= (sb.toString()).substring(0,sb.toString().length()-1);
//		}	else{
//			x="";
//		}
//		return x;
//	}

	
}
