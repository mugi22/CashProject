package com.id.kas.DEVELOPMENT;

import java.math.BigDecimal;
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
import com.id.kas.pojo.TblGroup;
import com.id.kas.pojo.TblMenu;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblPriviledge;//harap tambahain coyyy
import com.id.kas.pojo.dao.TblGroupDAO;
import com.id.kas.util.AbstractListScreen;


@Controller
public class PriviledgeController  extends AbstractListScreen{
	@RequestMapping(value="/priviledge.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg){ 
	 	return super.doGet(model, session, reg);
	}
	
	
	 @RequestMapping(value="/priviledge.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session) {
		 super.doPost(model, session);
		return getView();		 
	 }
	 
	 @Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "priviledge";
	}
	
//	 ***************************** LIST  **************************************************************
	 @RequestMapping(value="/priviledgeListAll.htm", method=RequestMethod.POST)
     public @ResponseBody String priviledgeListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		String GroupId="0";
		if(reg.getParameter("GroupId").length()>0){
			GroupId=reg.getParameter("GroupId");
		}
		String MenuId="0";
		if(reg.getParameter("MenuId").length()>0){
			MenuId=reg.getParameter("MenuId");
		}
		String IsAdd=reg.getParameter("IsAdd");
		String IsDelete=reg.getParameter("IsDelete");
		String IsUpdate=reg.getParameter("IsUpdate");
		String IsView=reg.getParameter("IsView");		 
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
			TblPriviledgeDAO dao = new TblPriviledgeDAO(sess);
			Map h = new HashMap<String, Object>();
			List<TblPriviledge> l = new ArrayList<TblPriviledge>();
				h = dao.getByPerPage(new BigDecimal(GroupId),new BigDecimal(MenuId),IsAdd,IsDelete,IsUpdate,IsView,loffset, row);
			String x = changeJson(h, sess);
            sess.close();
        	result ="{"+'"'+"total"+'"'+":"+h.get("total")+","+'"'+"rows"+'"'+":["+x+']'+'}';
        	 System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}         
         return result;
     }

// *********************ADD***********************
 @RequestMapping(value="/priviledgeAdd.htm", method=RequestMethod.POST)
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
               TblPriviledgeDAO dao = new TblPriviledgeDAO(ses);
               TblPriviledge tbl = new TblPriviledge();
                    tbl.setGroupId(new BigDecimal(reg.getParameter("groupId")));
                    tbl.setMenuId(new BigDecimal(reg.getParameter("menuId")));
                    tbl.setIsAdd(reg.getParameter("isAdd").charAt(0));
                    tbl.setIsDelete(reg.getParameter("isDelete").charAt(0));
                    tbl.setIsUpdate(reg.getParameter("isUpdate").charAt(0));
                    tbl.setIsView(reg.getParameter("isView").charAt(0));
                             
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
	 @RequestMapping(value="/priviledgeEdit.htm", method=RequestMethod.POST)
     public @ResponseBody String priviledgeEdit(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String groupId=reg.getParameter("groupId");
String menuId=reg.getParameter("menuId");
		 
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
               TblPriviledgeDAO dao = new TblPriviledgeDAO(ses);
               TblPriviledge tbl = dao.getById(new BigDecimal(groupId),new BigDecimal(menuId));
	               tbl.setGroupId(new BigDecimal(reg.getParameter("groupId")));
	               tbl.setMenuId(new BigDecimal(reg.getParameter("menuId")));
	               tbl.setIsAdd(reg.getParameter("isAdd").charAt(0));
	               tbl.setIsDelete(reg.getParameter("isDelete").charAt(0));
	               tbl.setIsUpdate(reg.getParameter("isUpdate").charAt(0));
	               tbl.setIsView(reg.getParameter("isView").charAt(0));
               
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
	 @RequestMapping(value="/priviledgeDelete.htm", method=RequestMethod.POST)
     public @ResponseBody String priviledgeDelete(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
String groupId=reg.getParameter("groupId");
String menuId=reg.getParameter("menuId");
	
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
               TblPriviledgeDAO dao = new TblPriviledgeDAO(ses);
               TblPriviledge tbl = dao.getById(new BigDecimal(groupId),new BigDecimal(menuId));
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


	public String changeJson(Map<String,Object> result, Session sess){
		List<TblPriviledge> listPri = (List<TblPriviledge>) result.get("rows");
//		List<TblPriviledge> priv = (List<TblPriviledge>) h.get("rows");
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
		StringBuffer sb = new StringBuffer();
		for(TblPriviledge pr : listPri){
			String s = gson.toJson(pr);			
			TblGroupDAO groupDAO = new TblGroupDAO(sess);
			TblGroup group = groupDAO.getById(pr.getGroupId());
			TblMenuDAO menuDAO = new TblMenuDAO(sess);
			TblMenu menu =  menuDAO.getById(pr.getMenuId());
			String a = s.replace("}", ","+'"'+"groupName"+'"'+":"+'"'+group.getGroupName()+'"'+","+'"'+"menuName"+'"'+":"+'"'+menu.getMenuName()+'"'+"},");
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
