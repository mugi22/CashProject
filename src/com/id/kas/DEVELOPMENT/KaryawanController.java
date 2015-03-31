package com.id.kas.DEVELOPMENT;

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
//import com.id.kas.pojo.TblKaryawan;//harap tambahain coyyy
import com.id.kas.util.AbstractListScreen;


@Controller
public class KaryawanController  extends AbstractListScreen{
	@RequestMapping(value="/karyawan.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session,  HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/karyawan.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "karyawan";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/karyawanListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String karyawanListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String Nik=reg.getParameter("Nik");
String Nama=reg.getParameter("Nama");		 
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
			TblKaryawanDAO dao = new TblKaryawanDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblKaryawan> l = new ArrayList<TblKaryawan>();
				h = dao.getByPerPage(Nik,Nama,loffset, row);
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
 @RequestMapping(value="/karyawanAdd.htm", method=RequestMethod.POST)
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
               TblKaryawanDAO dao = new TblKaryawanDAO(ses);
               TblKaryawan tbl = new TblKaryawan();
                    tbl.setUnitKerja(reg.getParameter("unitKerja"));
                    tbl.setNik(reg.getParameter("nik"));
                    tbl.setNama(reg.getParameter("nama"));
                             
               tbl.setCreateBy(user.getUserId());
               tbl.setCreateDate(new Date());
               
               ses.beginTransaction();
               dao.insert(tbl);
               ses.getTransaction().commit();
               simpanLog(user.getUserId(),gson.toJson(tbl));
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
	 @RequestMapping(value="/karyawanEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String karyawanEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String Nik=reg.getParameter("nik");
		 
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
               TblKaryawanDAO dao = new TblKaryawanDAO(ses);
               TblKaryawan tbl = dao.getById(Nik);
                String tblOld = gson.toJson(tbl);
                    tbl.setUnitKerja(reg.getParameter("unitKerja"));
                    tbl.setNik(reg.getParameter("nik"));
                    tbl.setNama(reg.getParameter("nama"));
               
               tbl.setUpdateBy(user.getUserId());
               tbl.setUpdateDate(new Date());
               
               ses.beginTransaction();
               dao.update(tbl);
               ses.getTransaction().commit();
                simpanLog(user.getUserId(),"MODIFY  : "+gson.toJson(tbl)+" OLD "+tblOld);
               ses.close();
               x=gson.toJson("UPDATE SUKSES");
         }catch(Exception e){
             x=gson.toJson("fail");
             e.printStackTrace();
         }
         return x;
 	 }
	 
//	***********************************DELETE**************************************** 
	 @RequestMapping(value="/karyawanDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String karyawanDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String Nik=reg.getParameter("nik");
	
//		 String sId = reg.getParameter("param"); //param sesuaikan dengan yg di jsp
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
               TblKaryawanDAO dao = new TblKaryawanDAO(ses);
               TblKaryawan tbl = dao.getById(Nik);
               String tblDel = gson.toJson(tbl);
               ses.beginTransaction();
               dao.delete(tbl);
               ses.getTransaction().commit();
               simpanLog(user.getUserId(),"DELETE  : "+tblDel);
               ses.close();
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
