package com.id.kas.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsAnything;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblPriviledge;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;
import com.id.kas.util.log.LogClass;

public abstract class AbstractListScreen {
	private final transient Logger log=Logger.getLogger(getClass());

	protected abstract String getView() ;
	private HttpSession session;
	//=========================
	
	 public String doGet(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
		 String param =reg.getParameter("param");//.replace(" ", "");
		 String param2 =reg.getParameter("param2");
		 if (param==null) {
			 param="ud5QqNn792ilaMP05ijYm8/uVEYl3fND5MVdd9WTr35xDpucRJe3bcM3K8S2kbi0WbUfbvyFpQZer47TYkhJ2g==";
			 param2="xntldrqmbpqwhrry";
		 }
			 if(param.contains(" ")){
				 String z = param.replace(" ", "+");
				 param = z;
			 }
			
			 
			 
			 System.out.println(param);
			 JCrypto crypto = new JCrypto(param2);
			 String sPriv = crypto.decrypt(param);
			 System.out.println("sPriv    : "+sPriv);
			 String sDec[] =sPriv.split("&");//==
			 String sIsAdd ="";		 String sIsEdit ="";		 String sIsDelete ="";		 String sIsView="";				 
			 for(String s: sDec){
				if (s.contains("isAdd=")){
					sIsAdd = /*crypto.decrypt*/(((s.replace("isAdd=", ""))));
				}
				if (s.contains("isEdit=")){
					sIsEdit = /*crypto.decrypt*/(((s.replace("isEdit=", ""))));
				}
				if (s.contains("isDelete=")){
					sIsDelete = /*crypto.decrypt*/(((s.replace("isDelete=", ""))));
				}
				if (s.contains("isView=")){
					sIsView = /*crypto.decrypt*/(((s.replace("isView=", ""))));
				}
				 
			 }
		 
		 
	         String ses = (String) session.getAttribute("session");
	         TblUser user = (TblUser) session.getAttribute("user");
         if(MyVariable.getsAppStatus().equals(AppContant.AdminMode.AdminModeOpen)){
	         if(cekValidSession(session)){
//	        	 System.out.println(sIsAdd+"*==========================");
		         model.put("btnAdd",sIsAdd);// "disable");
		         model.put("btnEdit",sIsEdit);//reg.getParameter("isEdit") );//"disable");
		         model.put("btnDelete",sIsDelete);//reg.getParameter("isDelete"));//"disable");   isShow
		         model.put("btnShow",sIsView);//reg.getParameter("isView"));//"disable");
		         return getView();
	         }else{
	        	 return  "redirect:/logout.htm";
	         }
         }else{
        	 //cek apakah user punya group adminmode
        	 Session sess = null;
        	 String ret = null;
        	 try{
        	 sess = HibernateUtil.getSessionFactory().openSession();
        	 if(Util.cekUserAdminMode(user.getUserId(), sess)){//cek apakah user punya group adminmode
        		sess.close();
	        		 if(cekValidSession(session)){
	    	        	 //System.out.println(sIsAdd+"*==========================");
	    		         model.put("btnAdd",sIsAdd);// "disable");
	    		         model.put("btnEdit",sIsEdit);//reg.getParameter("isEdit") );//"disable");
	    		         model.put("btnDelete",sIsDelete);//reg.getParameter("isDelete"));//"disable");   isShow
	    		         model.put("btnShow",sIsView);//reg.getParameter("isView"));//"disable");
	    		         ret =  getView();
	    	         }else{
	    	        	 ret =  "redirect:/logout.htm";
	    	         }
				}else{
					ret =  "redirect:/appstatus.htm";
				}
        	 
        	 }catch (Exception e){
        		 e.printStackTrace();
        	 }
        	 return ret;
         }
         
         //ppppp
     }
	
	
	public String doPost(Map<String, Object> model, HttpSession session,HttpServletRequest reg, HttpServletResponse res) {
		String ses = (String) session.getAttribute("session");
		TblUser user = (TblUser) session.getAttribute("user");
		model.put("session", ses);
		return getView();
	}
	
	
	public boolean cekValidSession(HttpSession session){		
	    TblUser user = (TblUser) session.getAttribute("user");
	    String valid =  (String) session.getAttribute("valid");
	    if(session.getAttribute("valid") == null){
	    	//System.out.println(user.getUserId()+"  "+user.getName()+"  "+"Anda Tidak Punya Hak........");
	    	return false;
	    }else{
		    if (valid.equals("valid")){
		    	return true;
		    }else{
		    	return false;
		    }
			
		}
	}
	
	public TblUser  getUser(HttpSession session){
		TblUser user = (TblUser) session.getAttribute("user");
		return user;
	}
	
	
	public void simpanLog(String sUserId,String sLog){
		String logger =sUserId+"  "/*+ new Date()*/+" "+sLog; //engga [perlu pake data suda ada dari looger
		log.warn(logger);
		System.out.println(sLog);
	}
	
}


