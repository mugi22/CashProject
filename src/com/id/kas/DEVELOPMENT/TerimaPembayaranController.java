package com.id.kas.DEVELOPMENT;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblBranch;
import com.id.kas.pojo.TblPegawai;
import com.id.kas.pojo.TblTagihan;
import com.id.kas.pojo.TblTarif;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.pojo.dao.TblPegawaiDAO;
import com.id.kas.pojo.dao.TblTagihanDAO;
import com.id.kas.pojo.dao.TblTarifDAO;
import com.id.kas.pojo.dao.TblUserGroupDAO;
import com.id.kas.util.AbstractListScreen;
import com.id.kas.util.AppProp;
@Controller
public class TerimaPembayaranController extends AbstractListScreen {
final static Logger logger = Logger.getLogger(TerimaPembayaranController.class);
	
	@RequestMapping(value="/pembayaranTagihan.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){ 
		TblUser user = (TblUser) session.getAttribute("user");
		String valid =  (String) session.getAttribute("valid");

	    if(session.getAttribute("valid") == null){
	    	return "redirect:/logout.htm";
	    }
		Session sess =null;
		sess = HibernateUtil.getSessionFactory().openSession();
		TblBranchDAO dao =new TblBranchDAO(sess);
		TblBranch tblBranch = dao.getById(user.getBranchCode());
		
		model.put("branchCode", user.getBranchCode());
		sess.close();
	 	return /*getView();*/super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/pembayaranTagihan.htm", method=RequestMethod.POST)
	 public @ResponseBody String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
		//String mode = reg.getParameter("branchCode");
		 System.out.println("pembayaranTagihan........POST");
		TblUser user = (TblUser) session.getAttribute("user");
		String nik = reg.getParameter("nik");
		String tagihan = reg.getParameter("tagihan");
		Session sess =null;
		System.out.println("nik   "+nik+" tagihan :"+tagihan);
		model.put("message", "Selesai............... ");
		String msg="";
		try{
			sess= HibernateUtil.getSessionFactory().openSession();
			TblTagihanDAO tagihanDAO = new TblTagihanDAO(sess);
			TblTagihan tblTagihan = tagihanDAO.getById(nik, tagihan, user.getBranchCode());
			if(tblTagihan==null) {
				sess.close();
				msg= "FAIL";
			}else{
				tblTagihan.setUpdateBy(user.getUserId());
				tblTagihan.setUpdateDate(new Date());
				tblTagihan.setTglBayar(new Date());
				tblTagihan.setKasir(user.getUserId());
				tblTagihan.setSatusBayar("Y");
				
				sess.beginTransaction();
				sess.evict(tblTagihan);
				sess.update(tblTagihan);
				sess.getTransaction().commit();
				sess.close();
				msg="SUCCESSS";
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		super.doPost(model, session, reg,res);
		System.out.println(msg);
		return /*getView();//*/msg;		 
	 }
	 
	 
	 
	@RequestMapping(value="/dataTagihan.htm",method=RequestMethod.GET)
	public @ResponseBody String doGetDataTagihan(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){ 
		System.out.println("dataTagihan.htm .GET");
		TblUser user = (TblUser) session.getAttribute("user");
		//cari data pegawai
		Map x = new HashMap<String, String>();
		String sNik = reg.getParameter("nik");
		Session sess = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			TblPegawaiDAO pegawaiDAO = new TblPegawaiDAO(sess);
			TblPegawai pegawai = pegawaiDAO.getById(sNik);
			System.out.println("Snik "+sNik);
			if(pegawai!=null) {
				//cek punya tagihan atau tidak
				System.out.println("Snik "+sNik+"  user.getBranchCode() :"+ user.getBranchCode());
				TblTagihanDAO tagihanDAO = new TblTagihanDAO(sess);
				List<TblTagihan> tagihans = tagihanDAO.getByIdAndBranch(pegawai.getNik(), user.getBranchCode(), "N");
				if(tagihans.size()>0){
					x.put("nama", pegawai.getNama());
				}
			}
			sess.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("==========data tagihan action========== ");
		Gson gson = new Gson();
		System.out.println(gson.toJson(x));
		return gson.toJson(x);
		}
		
	 
	 
	 
	//combo tagihan 
		 @RequestMapping(value="/comboTagihan.htm", method=RequestMethod.POST)
		    public @ResponseBody String comboTagihan(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
			 
			 TblUser user = (TblUser) session.getAttribute("user");
				 String param =reg.getParameter("param");
				 System.out.println("comboTagihan.................param "+param);
				 Session sess = null;
				 String x="";String z ="";
				 try {
					sess = HibernateUtil.getSessionFactory().openSession();
					TblTagihanDAO dao = new TblTagihanDAO(sess);
					List<TblTagihan> l = dao.getByIdAndBranch(param, user.getBranchCode(),"N");
					StringBuffer sb = new StringBuffer();
							sb.append("[");
					for(TblTagihan tbl : l){
						String selected="";
						String item = "{"+'"'+"id"+'"'+":"+'"'+tbl.getYemm()+'"'+","+'"'+"text"+'"'+":"+'"'+tbl.getYemm()+" - "+tbl.getJumlah()+'"'+selected+"},";	
						sb.append(item);
					}
					x = (sb.toString()).substring(0,sb.toString().length()-1);
					 z = x+"]";
					 if(z.equals("]")) z="{}";
					 sess.close();
					 System.out.println(z);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return z;
			 }
	 
	 
	 
	 
	 
	@RequestMapping(value = "/getTagihan.htm", method = RequestMethod.GET)
	public @ResponseBody
	String getTagihan(Map<String, Object> model, HttpSession session,HttpServletRequest reg) {
		 TblUser user = (TblUser) session.getAttribute("user");
		 String param =reg.getParameter("param");//nik
		 String param2 =reg.getParameter("param2");//comboselect yemm
		 Map mTagihan = new HashMap<String, BigDecimal>();
		 Session sess = null;
		 try {
			sess = HibernateUtil.getSessionFactory().openSession();
			TblTagihanDAO tagihanDAO = new TblTagihanDAO(sess);
			TblTagihan tagihan = (TblTagihan) tagihanDAO.getByIdAndBranchAndYemm(param, user.getBranchCode(), "N", param2);
			sess.close();
			mTagihan.put("jml", tagihan.getJumlah());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		 
		 
		 Gson gson = new  Gson();
		 String ret = gson.toJson(mTagihan);
		return ret;
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "pembayaranTagihan";
	}
}
