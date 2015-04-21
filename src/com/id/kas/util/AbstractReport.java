package com.id.kas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblUserDAO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class AbstractReport {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	
	protected String  sJasper;
	protected JasperPrint jasperPrint = null;
	/**
	 * retrurn file jasper ex:  return sJasper = "H:\\Workspace\\CashProject\\WebContent\\test01.jasper";
	 */
	protected abstract String getJasper() ;
	
	
	public List listData(){//untu data nya
		Session session = null;
		List<TblUser> l = null ;
		try{
			session = (Session) HibernateUtil.getSessionFactory().openSession();
			TblUserDAO dao = new TblUserDAO(session);
			 l = dao.getAll();
			 session.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
	
	public Map mapParameter(){
		Map parameters = new HashMap();
		parameters.put("wa", "parameter nya");
		return parameters;
	}
	
	public JasperPrint print(){
		System.out.println("Create Report.......");
		getJasper();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(/*getDataBeanList()*/listData());
		try {
			jasperPrint = JasperFillManager.fillReport(sJasper, mapParameter(), beanColDataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint,pdfFile());
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Create Report.......Done");
		return jasperPrint;
	}
	public String pdfFile(){
		return "h:/test_jasper.pdf";
	}
	
	

}
