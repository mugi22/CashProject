/*
*Create by CodeGenerator
*controllerTemplate
*/

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
import com.id.kas.pojo.TblBranch;
import com.id.kas.pojo.TblRekeningIA;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblKasir;//harap Sesuaikan
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.pojo.dao.TblKasirDAO;
import com.id.kas.pojo.dao.TblRekeningIADAO;
import com.id.kas.pojo.dao.TblUserDAO;


import com.id.kas.util.AbstractListScreen;


@Controller
public class KasirController  extends AbstractListScreen{
	@RequestMapping(value="/kasir.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/kasir.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "kasir";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/kasirListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String kasirListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
                    String Status=reg.getParameter("Status");
                    String BranchCode=reg.getParameter("BranchCode");
                    String UserId=reg.getParameter("UserId");
                    String Norek=reg.getParameter("Norek");		 
         String userId = reg.getParameter("userId");
         String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+userId);
  
         model.put("session", ses);
         if(!cekValidSession(session,userId)){
        	 return "[]";
         }
         String result="";
         int row = Integer.parseInt(reg.getParameter("rows"));
         int loffset = (Integer.parseInt(reg.getParameter("page"))-1)*row;
         Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         Session sess = null;
         try {
        	long rowCount=0;
			sess = HibernateUtil.getSessionFactory().openSession();
			TblKasirDAO dao = new TblKasirDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblKasir> l = new ArrayList<TblKasir>();
				h = dao.getByPerPage(Status,BranchCode,UserId,Norek,loffset, row);
			
            result = gson.toJson(h);
            
            
            /**  BILA ADA PERUBAHAN DATA JSON*/
            String x = changeJson(h, sess);
            //sess.close();
        	result ="{"+'"'+"total"+'"'+":"+h.get("total")+","+'"'+"rows"+'"'+":["+x+']'+'}';
            
        	System.out.println(result);
        	sess.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}         
         return result;
     }

// *********************ADD***********************
 @RequestMapping(value="/kasirAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		String userId = reg.getParameter("UID");
         //String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+userId);
         
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
               TblKasirDAO dao = new TblKasirDAO(sess);
               TblKasir tbl = new TblKasir();
                    tbl.setStatus(reg.getParameter("status"));
                    tbl.setLimitAmount(new BigDecimal((reg.getParameter("limitAmount"))));
                    tbl.setBranchMapping(reg.getParameter("branchMapping"));
                    tbl.setCcy(reg.getParameter("ccy"));
                    tbl.setAmount(new BigDecimal((reg.getParameter("amount"))));
                    tbl.setBranchCode(reg.getParameter("branchCode"));
                    tbl.setUserId(reg.getParameter("userId"));
                    tbl.setNorek(reg.getParameter("norek"));
                             
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
	 @RequestMapping(value="/kasirEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String kasirEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
        System.out.println("Do Edit.......");
		 String UserId=reg.getParameter("userId");
		 
		 String UID = reg.getParameter("UID");
         //String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+UID);
         if(!cekValidSession(session,UID)){        	 
         	return "fail";
         }
         
         Session sess = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               sess = HibernateUtil.getSessionFactory().openSession();
               TblKasirDAO dao = new TblKasirDAO(sess);
               TblKasir tbl = dao.getById(UserId);
                String tblOld = gson.toJson(tbl);
                    tbl.setStatus(reg.getParameter("status"));
                    tbl.setLimitAmount(new BigDecimal((reg.getParameter("limitAmount"))));
                    tbl.setBranchMapping(reg.getParameter("branchMapping"));
                    tbl.setCcy(reg.getParameter("ccy"));
                    tbl.setAmount(new BigDecimal((reg.getParameter("amount"))));
                    tbl.setBranchCode(reg.getParameter("branchCode"));
                    tbl.setUserId(reg.getParameter("userId"));
                    tbl.setNorek(reg.getParameter("norek"));
               
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
	 @RequestMapping(value="/kasirDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String kasirDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
                    String UID=reg.getParameter("UID");	
		 String userId = reg.getParameter("userId");
         //String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+UID);
         //model.put("session", ses);
          if(!cekValidSession(session,UID)){
                	 return "fail";
         }
         Session sess = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         try {
               sess = HibernateUtil.getSessionFactory().openSession();
               TblKasirDAO dao = new TblKasirDAO(sess);
               TblKasir tbl = dao.getById(userId);
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
	public String changeJson(Map<String,Object> result, Session sess){//nemabhakan field nama group dan nama menu
		List<TblKasir> listPri = (List<TblKasir>) result.get("rows");
//		List<TblPriviledge> priv = (List<TblPriviledge>) h.get("rows");
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
		StringBuffer sb = new StringBuffer();
		for(TblKasir pr : listPri){
			String s = gson.toJson(pr);			
			TblUserDAO groupDAO = new TblUserDAO(sess);
			TblUser user = groupDAO.getById(pr.getUserId());
			//"userName":"MUGI"
			String userName = ","+'"'+"userName"+'"'+":"+'"'+user.getName()+'"';
			//"branchName":"KPPP"
			TblBranchDAO branchDAO = new TblBranchDAO(sess);
			TblBranch tblBranch = branchDAO.getById(user.getBranchCode());
			String branchName = ","+'"'+"branchName"+'"'+":"+'"'+tblBranch.getName()+'"';
			
			
			//namaRekening
			TblRekeningIADAO iadao = new TblRekeningIADAO(sess);
			TblRekeningIA rekeningIA = iadao.getById(pr.getNorek());
			String namaNorek = ","+'"'+"namaNorek"+'"'+":"+'"'+rekeningIA.getDescription()+'"';
			
			
			
			
			
			
			String a = s.replace("}", userName+branchName+namaNorek+"},");
			sb.append(a);
		}
		String x="";
		if(sb.toString().length()>0){
			x= (sb.toString()).substring(0,sb.toString().length()-1);
		}	else{
			x="";
		}
		return x;
	}

	
}
