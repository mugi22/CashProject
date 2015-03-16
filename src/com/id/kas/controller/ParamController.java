package com.id.kas.controller;

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
import com.id.kas.pojo.TblParam;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblParamDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class ParamController  extends AbstractListScreen{
	@RequestMapping(value="/param.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg){ 
		return super.doGet(model, session, reg);
	}
	
	
	 @RequestMapping(value="/param.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session) {
		 super.doPost(model, session);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "param";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/paramListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String userListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
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
			TblParamDAO dao = new TblParamDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblParam> l = new ArrayList<TblParam>();
			if (reg.getParameter("param").equals("")|| reg.getParameter("param") == null) {
				rowCount = dao.getAllCount();
				l = dao.getAll(loffset, row);				
				h.put("total", rowCount);
				h.put("rows", l);
			} else {
				TblParam tbl = dao.getById(reg.getParameter("param"));
				if(tbl!=null){
					h.put("total", 1);
					l.add(tbl);
					h.put("rows", l);
				}else{//bila pencarian tidak di temukan
					h.put("total", 0);
					List lz = new ArrayList();
					h.put("rows", lz);
				}
			}
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
 @RequestMapping(value="/paramAdd.htm", method=RequestMethod.POST)
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
               TblParamDAO dao = new TblParamDAO(ses);
               TblParam tbl = new TblParam();
                    tbl.setValue(reg.getParameter("value"));
                    tbl.setKey(reg.getParameter("key"));
                    tbl.setDescription(reg.getParameter("description"));
                             
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
	 @RequestMapping(value="/paramEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String paramEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String sId = reg.getParameter("key"); //ID dari table
		 
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
               TblParamDAO dao = new TblParamDAO(ses);
               TblParam tbl = dao.getById(sId);
                    tbl.setValue(reg.getParameter("value"));
                    tbl.setKey(reg.getParameter("key"));
                    tbl.setDescription(reg.getParameter("description"));
               
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
	 @RequestMapping(value="/paramDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String paramDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {

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
               TblParamDAO dao = new TblParamDAO(ses);
               TblParam tbl = dao.getById(sId);
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
