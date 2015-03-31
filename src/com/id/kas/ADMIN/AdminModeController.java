package com.id.kas.ADMIN;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.id.kas.login.LoginController;
import com.id.kas.pojo.TblUser;
import com.id.kas.util.AbstractListScreen;
import com.id.kas.util.MyVariable;
@Controller
public class AdminModeController extends AbstractListScreen {
	final static Logger logger = Logger.getLogger(AdminModeController.class);
	
	@RequestMapping(value="/adminmode.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){ 
		model.put("AdminMode",MyVariable.getsAppStatus());
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/adminmode.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
//		 System.out.println("Do Post................."+reg.getParameter("mode"));
		 MyVariable.setsAppStatus(reg.getParameter("mode"));
		 TblUser user = (TblUser) session.getAttribute("user");
		 simpanLog(user.getUserId(), " SET ADMIN MODE "+reg.getParameter("mode"));
		 model.put("AdminMode",MyVariable.getsAppStatus());
		 super.doPost(model, session, reg,res);
		return getView();		 
	 }
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "adminmode";
	}

}
