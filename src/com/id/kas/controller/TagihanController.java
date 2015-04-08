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
import com.id.kas.pojo.TblTagihan;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblTagihanDAO;
//import com.id.kas.pojo.TblTagihan;//harap tambahain coyyy
import com.id.kas.util.AbstractListScreen;


@Controller
public class TagihanController  extends AbstractListScreen{
	@RequestMapping(value="/tagihan.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/tagihan.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "tagihan";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/tagihanListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String tagihanListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String Nik=reg.getParameter("Nik");
String Yemm=reg.getParameter("Yemm");
String Branchcode=reg.getParameter("Branchcode");		 
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
			TblTagihanDAO dao = new TblTagihanDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblTagihan> l = new ArrayList<TblTagihan>();
				h = dao.getByPerPage(Nik,Yemm,Branchcode,loffset, row);
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
 @RequestMapping(value="/tagihanAdd.htm", method=RequestMethod.POST)
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
               TblTagihanDAO dao = new TblTagihanDAO(sess);
               TblTagihan tbl = new TblTagihan();
                    tbl.setNik(reg.getParameter("nik"));
                    tbl.setGrade(reg.getParameter("grade"));
                    tbl.setJumlah(new BigDecimal(reg.getParameter("jumlah")));
                    tbl.setYemm(reg.getParameter("yemm"));
                    tbl.setBranchcode(reg.getParameter("branchcode"));
                    tbl.setTglBayar(formatter.parse(reg.getParameter("tglBayar")));
                    tbl.setSatusBayar(reg.getParameter("satusBayar"));
                    tbl.setKasir(reg.getParameter("kasir"));
                             
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
	 @RequestMapping(value="/tagihanEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String tagihanEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String Nik=reg.getParameter("nik");
String Yemm=reg.getParameter("yemm");
String Branchcode=reg.getParameter("branchcode");
		 
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
               TblTagihanDAO dao = new TblTagihanDAO(sess);
               TblTagihan tbl = dao.getById(Nik,Yemm,Branchcode);
                String tblOld = gson.toJson(tbl);
                    tbl.setNik(reg.getParameter("nik"));
                    tbl.setGrade(reg.getParameter("grade"));
                    tbl.setJumlah(new BigDecimal(reg.getParameter("jumlah")));
                    tbl.setYemm(reg.getParameter("yemm"));
                    tbl.setBranchcode(reg.getParameter("branchcode"));
                    tbl.setTglBayar(formatter.parse(reg.getParameter("tglBayar")));
                    tbl.setSatusBayar(reg.getParameter("satusBayar"));
                    tbl.setKasir(reg.getParameter("kasir"));
               
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
	 @RequestMapping(value="/tagihanDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String tagihanDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String Nik=reg.getParameter("nik");
String Yemm=reg.getParameter("yemm");
String Branchcode=reg.getParameter("branchcode");

System.out.println("nik "+Nik+" yemm : "+Yemm+" Branchcode :"+Branchcode);
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
               TblTagihanDAO dao = new TblTagihanDAO(sess);
               TblTagihan tbl = dao.getById(Nik,Yemm,Branchcode);
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
