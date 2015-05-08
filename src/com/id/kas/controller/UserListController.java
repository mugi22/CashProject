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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dframework.jpos.security.SecurityUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblKabupaten;
import com.id.kas.pojo.TblPriviledge;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;
import com.id.kas.pojo.dao.TblKabupatenDAO;
import com.id.kas.pojo.dao.TblUserDAO;
import com.id.kas.util.AbstractListScreen;
@Controller
public class UserListController extends AbstractListScreen {

    
	 @RequestMapping(value="/userList.htm", method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session,  HttpServletRequest reg, HttpServletResponse res) {		 
		 return super.doGet(model, session, reg,res);
		}
	 

	 @RequestMapping(value="/userList.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 
	 
	 
//	 ***************************** AJAX  **************************************************************
	 @RequestMapping(value="/userListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String userListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String userId = reg.getParameter("userID");
		 String unitId = reg.getParameter("unitId");
         TblUser user = (TblUser) session.getAttribute("user"+userId);
         
         //model.put("session", ses);
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
 			TblUserDAO dao = new TblUserDAO(sess);
 			Map h = new HashMap<String, Object>();
 			List<TblUser> l = new ArrayList<TblUser>();
 				h = dao.getByPerPage(reg.getParameter("param"),unitId,loffset, row);
 			sess.close();
             result = gson.toJson(h);
             System.out.println(result);
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
        	 
//        	long rowCount=0;
//			sess = HibernateUtil.getSessionFactory().openSession();
//			TblUserDAO dao = new TblUserDAO(sess);
//			Map h = new HashMap<String, Object>();
//			List<TblUser> l = new ArrayList<TblUser>();
//			if (reg.getParameter("param").equals("")|| reg.getParameter("param") == null) {
//				rowCount = dao.getAllCount();
//				l = dao.getAll(loffset, row);				
//				h.put("total", rowCount);
//				h.put("rows", l);
//			} else {
//				TblUser tblUser = dao.getById(reg.getParameter("param"));
//				if(tblUser!=null){
//					h.put("total", 1);
//					l.add(tblUser);
//					h.put("rows", l);
//				}else{//bila pencarian tidak di temukan
//					h.put("total", 0);
//					List lz = new ArrayList();
//					h.put("rows", lz);
//				}
//			}
//			sess.close();
//            result = gson.toJson(h);
//            System.out.println(result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}         
         return result;
     }
	 
	 
	 
	 
	 @RequestMapping(value="/userAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 System.out.println("DO ADDDD>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 String userId = reg.getParameter("userID");
         //String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+userId);
         System.out.println("userID "+userId+" userId "+reg.getParameter("userId"));
         //model.put("session", ses);
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
               TblUserDAO dao = new TblUserDAO(sess);
               TblUser tbl = new TblUser();
               tbl.setUserId(reg.getParameter("userId").toUpperCase());
               tbl.setName((reg.getParameter("name")).toUpperCase());
               tbl.setBranchCode(reg.getParameter("branchCodeAll"));

               tbl.setPassword(SecurityUtil.encrypt(reg.getParameter("password")));
               tbl.setStartTime(formatter.parse(reg.getParameter("startTime")));
               tbl.setEndTime(formatter.parse(reg.getParameter("endTime")));
               tbl.setEmail(reg.getParameter("email"));
               tbl.setEnabled(reg.getParameter("enabled"));
               
               tbl.setCreateBy(user.getUserId());
               tbl.setCreateDate(new Date());
               
               sess.beginTransaction();
               dao.insert(tbl);
               sess.getTransaction().commit();
               sess.close();
               x=gson.toJson("SUKSES");
         }catch(Exception e){
             x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }
	 
//	 EDIT	 
	 @RequestMapping(value="/userEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String userEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String sId = reg.getParameter("userId");
		 String userId = reg.getParameter("userID");
         //String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+userId);
         
         //model.put("session", ses);
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
               TblUserDAO dao = new TblUserDAO(sess);
               TblUser tbl = dao.getById(sId);
               tbl.setUserId(reg.getParameter("userId"));
               tbl.setName(reg.getParameter("name"));
               tbl.setBranchCode(reg.getParameter("branchCodeAll"));
               tbl.setPassword(SecurityUtil.encrypt(reg.getParameter("password")));
               tbl.setEmail(reg.getParameter("email"));
               tbl.setEndTime(formatter.parse(reg.getParameter("endTime")));
               tbl.setEnabled(reg.getParameter("enabled"));
               tbl.setUpdateBy(user.getUserId());
               tbl.setUpdateDate(new Date());
               
               sess.beginTransaction();
               dao.update(tbl);
               sess.getTransaction().commit();
               sess.close();
               x=gson.toJson("UPDATE SUKSES");
         }catch(Exception e){
             x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }
	 
	 
	 
	 
	 
//	DELETE 
	 @RequestMapping(value="/userDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String userDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {

		 String userId = reg.getParameter("userID");
         //String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+userId);
         
         //model.put("session", ses);
         if(!cekValidSession(session,userId)){
        	 return "fail";
         }
         Session sess = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         try {
               sess = HibernateUtil.getSessionFactory().openSession();
               TblUserDAO dao = new TblUserDAO(sess);
               TblUser tbl = dao.getById(reg.getParameter("userId"));
               sess.beginTransaction();
               dao.delete(tbl);
               sess.getTransaction().commit();
               sess.close();
               h.put("success", true);
               x=gson.toJson(h);
         }catch(Exception e){
        	 x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }


	
	 
	 
	 
	 
	 
	 
	 


@Override
protected String getView() {
	// TODO Auto-generated method stub
	return "userList";
}
	 


	 
	 
}
