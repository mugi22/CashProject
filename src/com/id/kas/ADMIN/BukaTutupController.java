package com.id.kas.ADMIN;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblBranch;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.pojo.dao.TblUserGroupDAO;
import com.id.kas.util.AbstractListScreen;
import com.id.kas.util.MyVariable;
@Controller
public class BukaTutupController extends AbstractListScreen {
final static Logger logger = Logger.getLogger(BukaTutupController.class);
	
	@RequestMapping(value="/bukatutup.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){ 
		reg.setAttribute("param", "ud5QqNn792ilaMP05ijYm8/uVEYl3fND5MVdd9WTr35xDpucRJe3bcM3K8S2kbi0WbUfbvyFpQZer47TYkhJ2g==");
		TblUser user = (TblUser) session.getAttribute("user");
		String valid =  (String) session.getAttribute("valid");
		String ret="";
	    if(session.getAttribute("valid") == null){
	    	//System.out.println(user.getUserId()+"  "+user.getName()+"  "+"Anda Tidak Punya Hak........");
	    	return "redirect:/logout.htm";
	    }
		Session sess =null;
		sess = HibernateUtil.getSessionFactory().openSession();
		TblBranchDAO dao =new TblBranchDAO(sess);
		TblBranch tblBranch = dao.getById(user.getBranchCode());
		System.out.println("ccc  : "+tblBranch.getBranchCode());
		if(tblBranch.getStatus().equals("O")){
			model.put("unitStatus","OPEN");
		}else{
			model.put("unitStatus","CLOSE");
		}
		
		//cek user mempunyai group 3 (ADMINBRANCH)
		TblUserGroupDAO tblUserGroupDAO = new TblUserGroupDAO(sess);
		TblUserGroup tblUserGroup = tblUserGroupDAO.getById(new BigDecimal("3"), user.getUserId());
		if(tblUserGroup==null){
//			System.out.println("TIDAK ADA.....");
			model.put("ubah", true);
		}else{
//			System.out.println("ADAAAAAAAAAAAAAAAaaa.....");
			model.put("ubah", false);
		}
		
		
		sess.close();
	 	return /*getView();*/super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/bukatutup.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
		 String mode = reg.getParameter("branchCode");
		TblUser user = (TblUser) session.getAttribute("user");
		Session sess =null;
		try{
		sess = HibernateUtil.getSessionFactory().openSession();
		TblBranchDAO dao =new TblBranchDAO(sess);
		TblBranch tblBranch = dao.getById(user.getBranchCode());
		tblBranch.setUpdateBy(user.getUserId());
		tblBranch.setUpdateDate(new Date());
		tblBranch.setStatus(reg.getParameter("branchCode"));
		sess.beginTransaction();
			dao.update(tblBranch);
			sess.getTransaction().commit();           
		sess.close();
		if(tblBranch.getStatus().equals("O")){
			model.put("unitStatus","OPEN");
		}else{
			model.put("unitStatus","CLOSE");
		}
		
		}catch (Exception e){
			e.printStackTrace();
		}
		 
		 
		super.doPost(model, session, reg,res);
		session.invalidate();
		return getView();		 
	 }
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "bukatutup";
	}
}
