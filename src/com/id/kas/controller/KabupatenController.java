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
import com.id.kas.pojo.TblKabupaten;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblKabupatenDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class KabupatenController  extends AbstractListScreen{
	@RequestMapping(value="/kabupaten.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg){ 
	 	super.doGet(model, session, reg);
		System.out.println("GET   disini.......");
		return getView();
	}
	
	
	 @RequestMapping(value="/kabupaten.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session) {
		 super.doPost(model, session);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "kabupaten";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/kabupatenListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String kabupatenListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
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
			TblKabupatenDAO dao = new TblKabupatenDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblKabupaten> l = new ArrayList<TblKabupaten>();
//			Sesuaikan dengan pencarian/kriteria
	
				h = dao.getByPerPage(reg.getParameter("param"),reg.getParameter("param2"),loffset, row);

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
 @RequestMapping(value="/kabupatenAdd.htm", method=RequestMethod.POST)
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
               TblKabupatenDAO dao = new TblKabupatenDAO(ses);
               TblKabupaten tbl = new TblKabupaten();
                    tbl.setKodeProvinsi(reg.getParameter("kodeProvinsi"));
                    tbl.setKodeKabupaten(reg.getParameter("kodeKabupaten"));
                    tbl.setNamaKabupaten(reg.getParameter("namaKabupaten"));
                             
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
	 @RequestMapping(value="/kabupatenEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String kabupatenEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String sKodeProvinsi = reg.getParameter("param"); 
		 String sKodeKabupaten = reg.getParameter("param2"); //ID dari table
		 
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
               TblKabupatenDAO dao = new TblKabupatenDAO(ses);
               TblKabupaten tbl = dao.getById(sKodeProvinsi,sKodeKabupaten);
                    tbl.setKodeProvinsi(reg.getParameter("kodeProvinsi"));
                    tbl.setKodeKabupaten(reg.getParameter("kodeKabupaten"));
                    tbl.setNamaKabupaten(reg.getParameter("namaKabupaten"));
               
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
	 @RequestMapping(value="/kabupatenDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String kabupatenDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String sKodeProvinsi = reg.getParameter("kodeProvinsi"); 
		 String sKodeKabupaten = reg.getParameter("kodeKabupaten"); 
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
               TblKabupatenDAO dao = new TblKabupatenDAO(ses);
               TblKabupaten tbl = dao.getById(sKodeProvinsi,sKodeKabupaten);
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
