package com.id.kas.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblBranch;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.util.SusunTree;
import com.id.kas.util.SusunTreeJeasyUI;

@Controller
public class UtamaController {

	@RequestMapping(value="/utama.htm",method = RequestMethod.GET)
    public String viewHalamanUtama(Map<String, Object> model,HttpSession session) {
		 TblUser user = (TblUser) session.getAttribute("user");//"user", tblUser
       try {
           String ses = (String) session.getAttribute("valid");
           if(ses.equals("valid")){
        	                
        	   
        	   //cek unit status
        	   Session sesdb=null;
        	   sesdb = HibernateUtil.getSessionFactory().openSession();
        	   TblBranchDAO branchDAO = new TblBranchDAO(sesdb);
        	   TblBranch branch = branchDAO.getById(user.getBranchCode());
        	   sesdb.close();
        	   //cek apakah user punya hak untuk buka/tutup cabang ????????????????
        	   if(branch.getStatus().equals("C")){
        		   return "redirect:/bukatutup.htm"; 
        	   }
        	   
        	   //cek unit status akhir======
        	   SusunTreeJeasyUI st = new SusunTreeJeasyUI(); 
               String menu = st.susunMenuByUser(user.getUserId(),(String) session.getAttribute("key"));
               
               
               model.put("menu", menu);
               model.put("key", session.getAttribute("key"));
               model.put("user", user);
               return "utama";
           }else{
              return "redirect:/login.htm"; 
           }
       } catch (Exception e) {
           return "redirect:/login.htm";
       }
    }
	
	
	
	
	 @RequestMapping(value="/utamaTop.htm")
     public String viewHalamanUtamaTop(Map<String, Object> model,HttpSession session) {
         String ses = (String) session.getAttribute("session");
         TblUser user = (TblUser) session.getAttribute("user");
         model.put("session", ses);
         return "utamaTop";
     }
     
	
	
	 @RequestMapping(value="/utamaMenu.htm")//,method = RequestMethod.GET)
     public ModelAndView viewHalamanUtamaMenu(HttpSession session) {     
		 
		 SusunTree st = new SusunTree();
         TblUser user = (TblUser) session.getAttribute("user");//"user", tblUser
         String menu = st.susunMenuByUser(user.getUserId());
         ModelAndView model = new ModelAndView("utamaMenu");
         model.addObject("menu", menu);

	return model;
    }
     
     @RequestMapping(value="/utamaMain.htm")
     public String doGetUtamaMain(Map<String, Object> model,HttpSession session) {
         String ses = (String) session.getAttribute("session");
         TblUser user = (TblUser) session.getAttribute("user");
         model.put("session", ses);
         return "utamaMain";
     }
	
	
	
	
}
