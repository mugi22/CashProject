package com.id.kas.ADMIN;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.id.kas.login.LoginController;
import com.id.kas.pojo.TblUser;
import com.id.kas.util.AbstractListScreen;
import com.id.kas.util.AppProp;
@Controller
public class AppStatusController extends AbstractListScreen{
	final static Logger logger = Logger.getLogger(AppStatusController.class);
	@RequestMapping(value="/appstatus.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){
//		System.out.println("appstatus GET......");
	 	return getView();//super.doGet(model, session, reg,res);
	}
	
	
//	 @RequestMapping(value="/appstatus.htm", method=RequestMethod.POST)
//	 public @ResponseBody String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
//		MyVariable.setsAppStatus("CLOSE");
//		// super.doPost(model, session);
//		return "aaaaaaa";		 
//	 }


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "ststusapp";
	}
}
