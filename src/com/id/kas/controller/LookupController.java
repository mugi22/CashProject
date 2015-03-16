package com.id.kas.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.google.gson.GsonBuilder;
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblLookup;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblLookupDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class LookupController  extends AbstractListScreen{
	@RequestMapping(value="/lookup.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg){ 
		return super.doGet(model, session, reg);
	}
	
	
	 @RequestMapping(value="/lookup.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session) {
		 super.doPost(model, session);
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
         String ses = (String) session.getAttribute("session");
         TblUser user = (TblUser) session.getAttribute("user");
         model.put("session", ses);
         if(!cekValidSession(session)){
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
				h = dao.getByPerPage(reg.getParameter("param"), reg.getParameter("param2"),loffset, row);

			sess.close();
            result = gson.toJson(h);
            System.out.println(result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}         
         return result;
     }

// *********************ADD***********************
 @RequestMapping(value="/lookupAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 TblUser user = getUser(session);		 
		 if(!cekValidSession(session)){
        	 return "fail";
         }
         Session ses = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               ses = HibernateUtil.getSessionFactory().openSession();
               TblLookupDAO dao = new TblLookupDAO(ses);
               TblLookup tbl = new TblLookup();
                    tbl.setNoUrut(new BigDecimal(reg.getParameter("noUrut")));
                    tbl.setLookupName(reg.getParameter("lookupName"));
                    tbl.setLookupValue(reg.getParameter("lookupValue"));
                    tbl.setLookupLabel(reg.getParameter("lookupLabel"));
                             
               tbl.setCreateBy(user.getUserId());
               tbl.setCreateDate(new Date());
               
               ses.beginTransaction();
               dao.insert(tbl);
               ses.getTransaction().commit();
               ses.close();
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
		 String sId = reg.getParameter("param"); //ID dari table
		 
		 TblUser user = getUser(session);
		 if(!cekValidSession(session)){
        	 return "fail";
         }
         
         Session ses = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               ses = HibernateUtil.getSessionFactory().openSession();
               TblLookupDAO dao = new TblLookupDAO(ses);
               TblLookup tbl = dao.getById(reg.getParameter("param"),reg.getParameter("param2"));
                    tbl.setNoUrut(new BigDecimal(reg.getParameter("noUrut")));
                    tbl.setLookupName(reg.getParameter("lookupName"));
                    tbl.setLookupValue(reg.getParameter("lookupValue"));
                    tbl.setLookupLabel(reg.getParameter("lookupLabel"));
               
               tbl.setUpdateBy(user.getUserId());
               tbl.setUpdateDate(new Date());
               
               ses.beginTransaction();
               dao.update(tbl);
               ses.getTransaction().commit();
               ses.close();
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

		 String sId = reg.getParameter("param");
		 TblUser user = getUser(session);
		 
		 if(!cekValidSession(session)){
        	 return "fail";
         }
         Session ses = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         try {
               ses = HibernateUtil.getSessionFactory().openSession();
               TblLookupDAO dao = new TblLookupDAO(ses);
               System.out.println(reg.getParameter("lookupName")+"====="+reg.getParameter("lookupValue"));
               TblLookup tbl = dao.getById(reg.getParameter("lookupName"),reg.getParameter("lookupValue"));
               ses.beginTransaction();
               dao.delete(tbl);
               ses.getTransaction().commit();
               ses.close();
               h.put("success", true);
               x=gson.toJson(h);
         }catch(Exception e){
        	 x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }


	
}
