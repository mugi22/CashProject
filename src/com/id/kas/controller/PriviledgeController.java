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
import com.id.kas.pojo.TblPriviledge;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblPriviledgeDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class PriviledgeController  extends AbstractListScreen{
	@RequestMapping(value="/priviledge.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session,  HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/priviledge.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "priviledge";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/priviledgeListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String priviledgeListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String GroupId="0";//reg.getParameter("GroupId");
if(reg.getParameter("GroupId").length()>0){
	GroupId = reg.getParameter("GroupId");
}	

String MenuId="0";//reg.getParameter("MenuId");		
if(reg.getParameter("MenuId").length()>0){
	MenuId = reg.getParameter("MenuId");
}	
String userId = reg.getParameter("userId");
//String ses = (String) session.getAttribute("session"+userId);
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
			TblPriviledgeDAO dao = new TblPriviledgeDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblPriviledge> l = new ArrayList<TblPriviledge>();
				h = dao.getByPerPage(new BigDecimal(GroupId),new BigDecimal(MenuId),loffset, row);
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
 @RequestMapping(value="/priviledgeAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
	 String userId = reg.getParameter("userId");
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
               TblPriviledgeDAO dao = new TblPriviledgeDAO(sess);
               TblPriviledge tbl = new TblPriviledge();
                    tbl.setGroupId(new BigDecimal(reg.getParameter("groupId")));
                    tbl.setMenuId(new BigDecimal(reg.getParameter("menuId")));
                    tbl.setIsAdd(reg.getParameter("isAdd").charAt(0));
                    tbl.setIsDelete(reg.getParameter("isDelete").charAt(0));
                    tbl.setIsUpdate(reg.getParameter("isUpdate").charAt(0));
                    tbl.setIsView(reg.getParameter("isView").charAt(0));
                             
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
	 @RequestMapping(value="/priviledgeEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String priviledgeEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String GroupId="0";//reg.getParameter("GroupId");
		 if(reg.getParameter("groupId").length()>0){
		 	GroupId = reg.getParameter("groupId");
		 }	

		 String MenuId="0";//reg.getParameter("MenuId");		
		 if(reg.getParameter("menuId").length()>0){
			 MenuId = reg.getParameter("menuId");
		 }	
		 
		 String userId = reg.getParameter("userId");
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
               TblPriviledgeDAO dao = new TblPriviledgeDAO(sess);
               TblPriviledge tbl = dao.getById(new BigDecimal(GroupId),new BigDecimal(MenuId));
                String tblOld = gson.toJson(tbl);
                tbl.setGroupId(new BigDecimal(reg.getParameter("groupId")));
                tbl.setMenuId(new BigDecimal(reg.getParameter("menuId")));
                tbl.setIsAdd(reg.getParameter("isAdd").charAt(0));
                tbl.setIsDelete(reg.getParameter("isDelete").charAt(0));
                tbl.setIsUpdate(reg.getParameter("isUpdate").charAt(0));
                tbl.setIsView(reg.getParameter("isView").charAt(0));
               
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
	 @RequestMapping(value="/priviledgeDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String priviledgeDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String GroupId="0";//reg.getParameter("GroupId");
		 if(reg.getParameter("GroupId").length()>0){
		 	GroupId = reg.getParameter("GroupId");
		 }	

		 String MenuId="0";//reg.getParameter("MenuId");		
		 if(reg.getParameter("MenuId").length()>0){
		 	GroupId = reg.getParameter("MenuId");
		 }	
		 
		 String sId = reg.getParameter("param"); //param sesuaikan dengan yg di jsp
		 String userId = reg.getParameter("userId");
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
               TblPriviledgeDAO dao = new TblPriviledgeDAO(sess);
               TblPriviledge tbl = dao.getById(new BigDecimal(GroupId),new BigDecimal(MenuId));
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
