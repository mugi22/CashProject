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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.id.kas.util.MyVariable;
@Controller
public class GenerateTagihanController extends AbstractListScreen {
final static Logger logger = Logger.getLogger(GenerateTagihanController.class);
	
	@RequestMapping(value="/generateTagihan.htm",method=RequestMethod.GET)
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
	
	
	 @RequestMapping(value="/generateTagihan.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
		 String mode = reg.getParameter("branchCode");
		TblUser user = (TblUser) session.getAttribute("user");
		String sUnit = reg.getParameter("branchCode");
		String sYemm = reg.getParameter("yemm");
		Session sess =null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Proses Generate Tagihan "+sUnit+" - "+sYemm);
			//ambil tarif masukan ke hashmap
			TblTarifDAO tarifDAO = new TblTarifDAO(sess);
			List<TblTarif> lTarif = tarifDAO.getAll();
			Map mTarif = new HashMap<String,BigDecimal>();
			for(TblTarif tarif : lTarif){
				mTarif.put(tarif.getGrade(), tarif.getTarif());
			}
			
			
			//ambil pegawai yang unitnya sama
			TblPegawaiDAO dao = new TblPegawaiDAO(sess);
			List<TblPegawai> lPegawai = dao.getAllByUnit(sUnit);
			sess.getTransaction().begin();
			TblTagihanDAO tagihanDAO = new TblTagihanDAO(sess);
			for(TblPegawai t:lPegawai){
				BigDecimal bdTarif = (BigDecimal) mTarif.get(t.getGrade());
				TblTagihan tagihan = new TblTagihan();
				tagihan.setBranchcode(t.getBranchCode());
				tagihan.setNik(t.getNik());
				tagihan.setYemm(sYemm);
				tagihan.setGrade(t.getGrade());
				tagihan.setJumlah(bdTarif);
				tagihan.setSatusBayar("N");
				tagihan.setCreateBy(user.getUserId());
				tagihan.setCreateDate(new Date());
				
				//cek apakah sudah ada atau belu
				TblTagihan tagihan2 = tagihanDAO.getById(t.getNik(), sYemm, t.getBranchCode());
				if(tagihan2==null){
					tagihanDAO.insert(tagihan);
				}
			}
			sess.getTransaction().commit();
		
		}catch (Exception e){
			e.printStackTrace();
		}
		model.put("message", "Selesai............... ");
		sess.close();
		super.doPost(model, session, reg,res);

		return getView();		 
	 }
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "generateTagihan";
	}
}
