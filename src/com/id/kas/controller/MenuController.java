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

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblMenu;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblMenuDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class MenuController  extends AbstractListScreen{
	@RequestMapping(value="/menu.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session,  HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/menu.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res) {
		 super.doPost(model, session,reg,res);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "menu";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/menuListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String menuListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String menuId="0";
			if(reg.getParameter("MenuId").length()>0){
				 menuId = reg.getParameter("MenuId");
			}
			String MenuName=reg.getParameter("MenuName");
			
//String ParentId=reg.getParameter("ParentId");
String ParentId="0";
if(reg.getParameter("ParentId").length()>0){
	ParentId = reg.getParameter("ParentId");
}



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
         
         
         try {
        	 Session sess = null;
        	long rowCount=0;
			sess = HibernateUtil.getSessionFactory().openSession();
			TblMenuDAO dao = new TblMenuDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblMenu> l = new ArrayList<TblMenu>();
				h = dao.getByPerPage(new BigDecimal(menuId),MenuName,new BigDecimal(ParentId),loffset, row);
				sess.setCacheMode(CacheMode.IGNORE);
			sess.close();
            result = gson.toJson(h);
            System.out.println(result);
            
            /**  BILA ADA PERUBAHAN DATA JSON
            String x = changeJson(h, sess);
            sess.close();
        	result ="{"+'"'+"total"+'"'+":"+h.get("total")+","+'"'+"rows"+'"'+":["+x+']'+'}';
            */
          
            
            System.out.println("session "+sess);
//            HibernateUtil.shutdown();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}         
         return result;
     }

// *********************ADD***********************
 @RequestMapping(value="/menuAdd.htm", method=RequestMethod.POST)
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
               TblMenuDAO dao = new TblMenuDAO(ses);
               TblMenu tbl = new TblMenu();
                    tbl.setParams(reg.getParameter("params"));
                    tbl.setMenuId(new BigDecimal(reg.getParameter("menuId")));
                    tbl.setHaveChild(reg.getParameter("haveChild").charAt(0));
                    tbl.setMenuName(reg.getParameter("menuName"));
                    tbl.setNoUrut(new BigDecimal(reg.getParameter("noUrut")));
                    tbl.setParentId(new BigDecimal(reg.getParameter("parentId")));
                    tbl.setMenuPage(reg.getParameter("menuPage"));
                    tbl.setScreenClass(reg.getParameter("screenClass"));
                    tbl.setIsUsingGroovy(reg.getParameter("isUsingGroovy"));
                             
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
	 @RequestMapping(value="/menuEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String menuEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String menuId="0";
			if(reg.getParameter("menuId").length()>0){
				 menuId = reg.getParameter("menuId");
			}		 
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
               TblMenuDAO dao = new TblMenuDAO(ses);
               TblMenu tbl = dao.getById(new BigDecimal(menuId));
                    tbl.setParams(reg.getParameter("params"));
                    tbl.setMenuId(new BigDecimal(reg.getParameter("menuId")));
                    tbl.setHaveChild(reg.getParameter("haveChild").charAt(0));
                    tbl.setMenuName(reg.getParameter("menuName"));
                    tbl.setNoUrut(new BigDecimal(reg.getParameter("noUrut")));
                    tbl.setParentId(new BigDecimal(reg.getParameter("parentId")));
                    tbl.setMenuPage(reg.getParameter("menuPage"));
                    tbl.setScreenClass(reg.getParameter("screenClass"));
                    tbl.setIsUsingGroovy(reg.getParameter("isUsingGroovy"));
               
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
	 @RequestMapping(value="/menuDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String menuDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 String menuId="0";
			if(reg.getParameter("MenuId").length()>0){
				 menuId = reg.getParameter("MenuId");
			}	
		 String sId = reg.getParameter("param"); //param sesuaikan dengan yg di jsp
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
               TblMenuDAO dao = new TblMenuDAO(ses);
               TblMenu tbl = dao.getById(new BigDecimal(menuId));
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
