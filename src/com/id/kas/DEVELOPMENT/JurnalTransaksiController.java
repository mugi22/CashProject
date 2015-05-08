/*
*Create by CodeGenerator
*controllerTemplate
*/

package com.id.kas.DEVELOPMENT;
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
import com.id.kas.pojo.TblJurnalTransaksi;//harap Sesuaikan
import com.id.kas.DEVELOPMENT.TblJurnalTransaksiDAO;

import com.id.kas.util.AbstractListScreen;


@Controller
public class JurnalTransaksiController  extends AbstractListScreen{
	@RequestMapping(value="/jurnalTransaksi.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/jurnalTransaksi.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "jurnalTransaksi";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/jurnalTransaksiListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String jurnalTransaksiListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
                    String IdJurnalTransaksi ="0";
                    if(reg.getParameter("IdJurnalTransaksi").length()>0){
                    IdJurnalTransaksi = (reg.getParameter("IdJurnalTransaksi"));
                    }
                    String Norek=reg.getParameter("Norek");
                    String JurnalId ="0";
                    if(reg.getParameter("JurnalId").length()>0){
                    JurnalId = (reg.getParameter("JurnalId"));
                    }
                    String TglPosting ="00-00-0000";
                    if(reg.getParameter("TglPosting").length()>0){
                    TglPosting = (reg.getParameter("TglPosting"));
                    }
                    String TglTransaksi ="00-00-0000";
                    if(reg.getParameter("TglTransaksi").length()>0){
                    TglTransaksi = (reg.getParameter("TglTransaksi"));
                    }		 
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
			TblJurnalTransaksiDAO dao = new TblJurnalTransaksiDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblJurnalTransaksi> l = new ArrayList<TblJurnalTransaksi>();
				h = dao.getByPerPage(Long.parseLong(IdJurnalTransaksi),Norek,Long.parseLong(JurnalId),formatter.parse(TglPosting),formatter.parse(TglTransaksi),loffset, row);
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
 @RequestMapping(value="/jurnalTransaksiAdd.htm", method=RequestMethod.POST)
     public @ResponseBody String userAdd(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		String userId = reg.getParameter("userId");
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
               TblJurnalTransaksiDAO dao = new TblJurnalTransaksiDAO(sess);
               TblJurnalTransaksi tbl = new TblJurnalTransaksi();
                    tbl.setIdJurnalTransaksi(Long.parseLong((reg.getParameter("idJurnalTransaksi"))));
                    tbl.setKeterangan(reg.getParameter("keterangan"));
                    tbl.setNoCoa(reg.getParameter("noCoa"));
                    tbl.setNorek(reg.getParameter("norek"));
                    tbl.setJurnalId(Long.parseLong((reg.getParameter("jurnalId"))));
                    tbl.setTglPosting(formatter.parse(reg.getParameter("tglPosting")));
                    tbl.setAmountD(new BigDecimal((reg.getParameter("amountD"))));
                    tbl.setAmountK(new BigDecimal((reg.getParameter("amountK"))));
                    tbl.setWaktu(formatter.parse(reg.getParameter("waktu")));
                    tbl.setTglTransaksi(formatter.parse(reg.getParameter("tglTransaksi")));
                             
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
	 @RequestMapping(value="/jurnalTransaksiEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String jurnalTransaksiEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
                    String IdJurnalTransaksi="0";
                    if(reg.getParameter("idJurnalTransaksi").length()>0){
                    IdJurnalTransaksi = (reg.getParameter("idJurnalTransaksi"));
                    }
                    String JurnalId="0";
                    if(reg.getParameter("jurnalId").length()>0){
                    JurnalId = (reg.getParameter("jurnalId"));
                    }
		 
		String userId = reg.getParameter("userId");
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
               TblJurnalTransaksiDAO dao = new TblJurnalTransaksiDAO(sess);
               TblJurnalTransaksi tbl = dao.getById(Long.parseLong(IdJurnalTransaksi),Long.parseLong(JurnalId));
                String tblOld = gson.toJson(tbl);
                    tbl.setIdJurnalTransaksi(Long.parseLong((reg.getParameter("idJurnalTransaksi"))));
                    tbl.setKeterangan(reg.getParameter("keterangan"));
                    tbl.setNoCoa(reg.getParameter("noCoa"));
                    tbl.setNorek(reg.getParameter("norek"));
                    tbl.setJurnalId(Long.parseLong((reg.getParameter("jurnalId"))));
                    tbl.setTglPosting(formatter.parse(reg.getParameter("tglPosting")));
                    tbl.setAmountD(new BigDecimal((reg.getParameter("amountD"))));
                    tbl.setAmountK(new BigDecimal((reg.getParameter("amountK"))));
                    tbl.setWaktu(formatter.parse(reg.getParameter("waktu")));
                    tbl.setTglTransaksi(formatter.parse(reg.getParameter("tglTransaksi")));
               
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
	 @RequestMapping(value="/jurnalTransaksiDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String jurnalTransaksiDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
                    String IdJurnalTransaksi="0";
                    if(reg.getParameter("idJurnalTransaksi").length()>0){
                    IdJurnalTransaksi = (reg.getParameter("idJurnalTransaksi"));
                    }
                    String JurnalId="0";
                    if(reg.getParameter("jurnalId").length()>0){
                    JurnalId = (reg.getParameter("jurnalId"));
                    }
	
//		 String sId = reg.getParameter("param"); //param sesuaikan dengan yg di jsp
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
               TblJurnalTransaksiDAO dao = new TblJurnalTransaksiDAO(sess);
               TblJurnalTransaksi tbl = dao.getById(Long.parseLong(IdJurnalTransaksi),Long.parseLong(JurnalId));
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

//===============================REPORT====================================================

	@RequestMapping(value="/jurnalTransaksiReport.htm",method=RequestMethod.GET)
	 public String doGetjurnalTransaksiReport(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg, HttpServletResponse res){ 
	 	super.doGet(model, session, reg,res);
	 	return "/report/jurnalTransaksiReport";
	}
	
	
		 @RequestMapping(value="/jurnalTransaksiDataReport.htm", method=RequestMethod.GET)
     public @ResponseBody String jurnalTransaksiDataReport(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
                    String IdJurnalTransaksi ="0";
                    if(reg.getParameter("IdJurnalTransaksi").length()>0){
                    IdJurnalTransaksi = (reg.getParameter("IdJurnalTransaksi"));
                    }
                    String Norek=reg.getParameter("Norek");
                    String JurnalId ="0";
                    if(reg.getParameter("JurnalId").length()>0){
                    JurnalId = (reg.getParameter("JurnalId"));
                    }
                    String TglPosting ="00-00-0000";
                    if(reg.getParameter("TglPosting").length()>0){
                    TglPosting = (reg.getParameter("TglPosting"));
                    }
                    String TglTransaksi ="00-00-0000";
                    if(reg.getParameter("TglTransaksi").length()>0){
                    TglTransaksi = (reg.getParameter("TglTransaksi"));
                    }		 
         String userId = reg.getParameter("userId");
         String ses = (String) session.getAttribute("session"+userId);
         TblUser user = (TblUser) session.getAttribute("user"+userId);
  
         model.put("session", ses);
         if(!cekValidSession(session,userId)){
        	 return "[]";
         }
         String result="";
          Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         Session sess = null;
         try {
        	long rowCount=0;
			sess = HibernateUtil.getSessionFactory().openSession();
			TblJurnalTransaksiDAO dao = new TblJurnalTransaksiDAO(sess);
			List<TblJurnalTransaksi> l = new ArrayList<TblJurnalTransaksi>();
				l = dao.getBy(Long.parseLong(IdJurnalTransaksi),Norek,Long.parseLong(JurnalId),formatter.parse(TglPosting),formatter.parse(TglTransaksi));
			sess.close();
            result = gson.toJson(l);
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
	
	
	
	
	
}
