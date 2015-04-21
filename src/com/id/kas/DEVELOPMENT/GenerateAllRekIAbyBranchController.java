/*
 * Geberate rekening IA seluruhnya percabang
 */

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
import com.id.kas.pojo.TblCoaMaster;
import com.id.kas.pojo.TblPegawai;
import com.id.kas.pojo.TblRekeningIA;
import com.id.kas.pojo.TblRekeningIaMaster;
import com.id.kas.pojo.TblTagihan;
import com.id.kas.pojo.TblTarif;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;
import com.id.kas.pojo.dao.TblBranchDAO;
import com.id.kas.pojo.dao.TblPegawaiDAO;
import com.id.kas.pojo.dao.TblTagihanDAO;
import com.id.kas.pojo.dao.TblTarifDAO;
import com.id.kas.pojo.dao.TblUserGroupDAO;
import com.id.kas.util.AbstractFormScreen;
import com.id.kas.util.AbstractListScreen;
import com.id.kas.util.AppProp;
@Controller
public class GenerateAllRekIAbyBranchController extends /*AbstractFormScreen*/AbstractListScreen {
final static Logger logger = Logger.getLogger(GenerateAllRekIAbyBranchController.class);
	
	@RequestMapping(value="/generateAllRekIA.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg,HttpServletResponse res){ 
		String userId = reg.getParameter("userId");
		String ses = (String) session.getAttribute("session"+userId);
		TblUser user = (TblUser) session.getAttribute("user"+userId);
		
	 	return /*getView();*/super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/generateAllRekIA.htm", method=RequestMethod.POST)
	 public String doPost(Map<String, Object> model,HttpSession session,HttpServletRequest reg,HttpServletResponse res) {
		 String userId = reg.getParameter("userId");
			String ses = (String) session.getAttribute("session"+userId);
			TblUser user = (TblUser) session.getAttribute("user"+userId);
			
		String sUnit = reg.getParameter("branchCode");
		System.out.println("sUnit "+sUnit);
//		Session sess =null;
//		int iRec =0;
		generateIA(sUnit);
		super.doPost(model, session, reg,res);

		return getView();		 
	 }
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "generateAllRekIA";
	}
	
	
	public void generateIA(String kdUnit){
		Session sess = null;
		try {
			sess = HibernateUtil.getSessionFactory().openSession();
			TblRekeningIaMasterDAO iaMasterDAO = new TblRekeningIaMasterDAO(sess);
			List<TblRekeningIaMaster> l = iaMasterDAO.getAll();
			
			sess.beginTransaction();
			for(TblRekeningIaMaster tbl :l ){
				System.out.println(tbl.getDescription().toString());
				TblRekeningIA ia = new TblRekeningIA();
				TblRekeningIADAO iadao = new TblRekeningIADAO(sess);
				ia.setBranchCode(kdUnit);
				ia.setNorek(kdUnit+tbl.getNoRek().toString());
				ia.setBranchCode(kdUnit);
				ia.setCreateBy("ADMIN");//nanti ganti
				ia.setDescription(tbl.getDescription());
				ia.setMutasiD(new BigDecimal("0"));
				ia.setMutasiK(new BigDecimal("0"));
				ia.setNoCOA(tbl.getNoCoa().toString());
				ia.setNorekIAMaster(tbl.getNoCoa());
				ia.setSaldoAkhir(new BigDecimal("0"));
				ia.setSaldoAwal(new BigDecimal("0"));
				ia.setSaldoNormal(tbl.getSaldoNormal());
				iadao.insert(ia);
				//sess.save(ia);
				
			}
			sess.getTransaction().commit();
			sess.close();
			System.out.println("Generate IA "+kdUnit+" DONE...");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		}
		
	
	
}
