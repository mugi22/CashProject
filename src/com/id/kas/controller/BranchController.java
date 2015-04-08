package com.id.kas.controller;

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
import com.id.kas.pojo.TblBranch;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class BranchController  extends AbstractListScreen{
	@RequestMapping(value="/branch.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){ 
	 	return super.doGet(model, session, reg, res);
	}
	
	
	 @RequestMapping(value="/branch.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
		 super.doPost(model, session,reg, res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "branch";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/branchListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String branchListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		String Name=reg.getParameter("Name");
		String Status=reg.getParameter("Status");		 
		
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
         
         Session sess = null;
         try {
        	long rowCount=0;
			sess = HibernateUtil.getSessionFactory().openSession();
			TblBranchDAO dao = new TblBranchDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblBranch> l = new ArrayList<TblBranch>();
				h = dao.getByPerPage(Name,Status,loffset, row);
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
 @RequestMapping(value="/branchAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
	 

	 String userId = reg.getParameter("userId");
     TblUser user = (TblUser) session.getAttribute("user"+userId);
		 if(!cekValidSession(session,userId)){
        	 return "fail";
         }
         Session ses = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               ses = HibernateUtil.getSessionFactory().openSession();
               TblBranchDAO dao = new TblBranchDAO(ses);
               TblBranch tbl = new TblBranch();
                    tbl.setName(reg.getParameter("name"));
                    tbl.setStatus(reg.getParameter("status"));
                    tbl.setBranchCode(reg.getParameter("branchCode"));
                    tbl.setLvl(reg.getParameter("lvl"));
                    tbl.setTelp(reg.getParameter("telp"));
                             
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
	 @RequestMapping(value="/branchEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String branchEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String BranchCode=reg.getParameter("branchCode");

String userId = reg.getParameter("userId");
TblUser user = (TblUser) session.getAttribute("user"+userId);
	 	if(!cekValidSession(session,userId)){
		
        	 return "fail";
         }
         
         Session ses = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         try {
               ses = HibernateUtil.getSessionFactory().openSession();
               TblBranchDAO dao = new TblBranchDAO(ses);
               TblBranch tbl = dao.getById(BranchCode);
                String tblOld = gson.toJson(tbl);
                    tbl.setName(reg.getParameter("name"));
                    tbl.setStatus(reg.getParameter("status"));
                    tbl.setBranchCode(reg.getParameter("branchCode"));
                    tbl.setLvl(reg.getParameter("lvl"));
                    tbl.setTelp(reg.getParameter("telp"));
               
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
	 @RequestMapping(value="/branchDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String branchDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String BranchCode=reg.getParameter("branchCode");

String userId = reg.getParameter("userId");
TblUser user = (TblUser) session.getAttribute("user"+userId);
	 if(!cekValidSession(session,userId)){
        	 return "fail";
         }
         Session ses = null;
         String x ="";
         Map h = new HashMap<String, Object>();
         Gson gson = new Gson();
         try {
               ses = HibernateUtil.getSessionFactory().openSession();
               TblBranchDAO dao = new TblBranchDAO(ses);
               TblBranch tbl = dao.getById(BranchCode);
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
