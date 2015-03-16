package com.id.kas.controller;

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
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblUserDAO;
@Controller
public class UserDetailController {

	
	 @RequestMapping(value="/userDetail.htm", method=RequestMethod.GET)
     public String doGet(Map<String, Object> model,HttpSession session) {
         String ses = (String) session.getAttribute("session");
         TblUser user = (TblUser) session.getAttribute("user");
         model.put("session", ses);
         return "userDetail";
     }
	
	
	 @RequestMapping(value="/userDetail.htm", method=RequestMethod.POST)
     public String doPost(Map<String, Object> model,HttpSession session) {
         String ses = (String) session.getAttribute("session");
         TblUser user = (TblUser) session.getAttribute("user");
         model.put("session", ses);
         return "userDetail";
     }
	 
//=========================
	
}
