package com.id.kas.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.id.kas.util.JCrypto;
import com.id.kas.util.SusunTree;
import com.id.kas.util.SusunTreeJeasyUI;

@Controller
public class UtamaController {

	@RequestMapping(value="/utama.htm",method = RequestMethod.GET)
    public String viewHalamanUtama(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		
		String encUid = reg.getParameter("paramA");//userId yang terencript
		String sKey = reg.getParameter("paramB");//ramdom string
		JCrypto crip = new JCrypto(sKey);
		String decUid = crip.decrypt(encUid); //decrip unyuk user
		
		 TblUser user = (TblUser) session.getAttribute("user"+decUid);//ambil object TblUser dari session
		 model.put("paramx", "paramA="+encUid+"&paramB="+sKey+"&userId="+user.getUserId());
		 String ses = (String) session.getAttribute("valid"+decUid);
		 if(user==null){
			 return "redirect:/login.htm"; 
		 }
       try {          
           if(ses.equals("valid")){
        	   //cek unit status
        	   Session sesdb=null;
        	   sesdb = HibernateUtil.getSessionFactory().openSession();
        	   TblBranchDAO branchDAO = new TblBranchDAO(sesdb);
        	   TblBranch branch = branchDAO.getById(user.getBranchCode());
        	   sesdb.close();
        	   //cek apakah user punya hak untuk buka/tutup cabang ????????????????
        	   if(branch.getStatus().equals("C")){
        		   return "redirect:/bukatutup.htm?paramA="+encUid+"&paramB="+sKey; 
        	   }
//        	   System.out.println("++++++++++++++++++++ 2 :"+(String) session.getAttribute("key"+decUid));
        	   //cek unit status akhir======
        	   SusunTreeJeasyUI st = new SusunTreeJeasyUI(); 
               String menu = st.susunMenuByUser(user.getUserId(),reg.getParameter("paramB"));//engga dari session tapi dari reg.parameter//(String) session.getAttribute("key"+decUid));
               model.put("menu", menu);
               model.put("key",reg.getParameter("paramB") );//session.getAttribute("key"+decUid));
               model.put("user", user);
               return "utama";
           }else{
              return "redirect:/login.htm"; 
           }
       } catch (Exception e) {
           return "redirect:/login.htm";
       }
    }
	
	
	
	
//	 @RequestMapping(value="/utamaTop.htm")
//     public String viewHalamanUtamaTop(Map<String, Object> model,HttpSession session) {
//         String ses = (String) session.getAttribute("session");
//         TblUser user = (TblUser) session.getAttribute("user");
//         model.put("session", ses);
//         return "utamaTop";
//     }
     
	
	
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
     public String doGetUtamaMain(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
    	 String userId = reg.getParameter("userId");
    	 String ses = (String) session.getAttribute("session"+userId);
    	 TblUser user = (TblUser) session.getAttribute("user"+userId);
    	 ///model diput untuk link pegawai
//         model.put("session", ses);
    	 model.put("user", userId);
         return "utamaMain";
     }
	
	
	
	
}
