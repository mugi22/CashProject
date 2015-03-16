package com.id.kas.util;

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
import com.id.kas.pojo.TblProvinsi;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblProvinsiDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class ProvinsiController  extends AbstractListScreen{
	@RequestMapping(value="/provinsi.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg){ 
	 	super.doGet(model, session, reg);
		System.out.println("GET   disini.......");
		return getView();
	}
	
	
	 @RequestMapping(value="/provinsi.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session) {
		 super.doPost(model, session);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "provinsi";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/provinsiListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String provinsiListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
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
			TblProvinsiDAO dao = new TblProvinsiDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblProvinsi> l = new ArrayList<TblProvinsi>();
//			Sesuaikan dengan pencarian/kriteria
/*				BigDecimal bdParam2 = new BigDecimal(0);
				if (param2==null||param2.equals("")){
					bdParam2 = new BigDecimal(0);
				}else{
					bdParam2 = new BigDecimal(reg.getParameter("param2"));
				}	*/			
				h = dao.getByPerPage(reg.getParameter("param"),loffset, row);

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
 @RequestMapping(value="/provinsiAdd.htm", method=RequestMethod.POST)
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
               TblProvinsiDAO dao = new TblProvinsiDAO(ses);
               TblProvinsi tbl = new TblProvinsi();
                    tbl.setKodeProvinsi(reg.getParameter("kodeProvinsi"));
                    tbl.setNamaProvinsi(reg.getParameter("namaProvinsi"));
                             
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
	 @RequestMapping(value="/provinsiEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String provinsiEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
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
               TblProvinsiDAO dao = new TblProvinsiDAO(ses);
               TblProvinsi tbl = dao.getById(sId);
               
                    tbl.setKodeProvinsi(reg.getParameter("kodeProvinsi"));
                    tbl.setNamaProvinsi(reg.getParameter("namaProvinsi"));
               
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
	 @RequestMapping(value="/provinsiDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String provinsiDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {

		 String sId = reg.getParameter("kodeProvinsi");//sesuaikan dengan yg di JSP
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
               TblProvinsiDAO dao = new TblProvinsiDAO(ses);
               TblProvinsi tbl = dao.getById(sId);
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
