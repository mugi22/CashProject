package com.id.kas.login;

import java.util.Map;
import com.dframework.jpos.security.SecurityUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblUserDAO;
import com.id.kas.util.RandomString;
import com.id.kas.util.log.LogClass;

@Controller
public class LoginController {
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String doGet(){
		logger.info("Login.....................");
		return "login";
	}

	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest req, HttpServletResponse res){
		String userName =  req.getParameter("USERNAME");
		String password = req.getParameter("Password");
		logger.info(userName + "Login.....................");
		Session sess = null;
		int valid = 0;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			TblUserDAO dao = new TblUserDAO(sess);
			TblUser tblUser = dao.getById(userName);
			if(tblUser!=null){
				String enkPwd = SecurityUtil.encrypt(password);
				if(enkPwd.equals(tblUser.getPassword().toString())){
					valid = 1;
					session.setAttribute("valid", "valid");
					session.setAttribute("user", tblUser);
					RandomString rs = new RandomString();
					session.setAttribute("key", rs.randomString());
				}
				
			}else{
//				System.out.println("tidak tidak ada.....");
			}
			sess.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (valid==1){
			logger.info(userName + "Login.....................Success");
	         return "redirect:/utama.htm";
	    }else{
	         return "loginGagal";
	    }
	}

	
	
	
	@RequestMapping(value="/logout.htm",method=RequestMethod.GET)
	public String dologout(HttpSession session){
		session.invalidate();
		return "logout";
	}
	
	
}
