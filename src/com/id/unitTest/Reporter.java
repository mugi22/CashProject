package com.id.unitTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;



import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblUserDAO;
import com.id.kas.util.AbstractReport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Reporter extends AbstractReport {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Reporter rep = new Reporter();
		rep.print();	
	}
	
	
	
	
	@Override
	public String pdfFile() {
		// TODO Auto-generated method stub
		return "h:/test.pdf";
	}
	
	@Override
	public List listData() {
		// TODO Auto-generated method stub
		return null;//super.listData();
	}
	
	
	
	
	
	
	
	

	@Override
	protected String getJasper() {
		// TODO Auto-generated method stub
		return sJasper = "H:\\Workspace\\CashProject\\WebContent\\test01.jasper";
	}
}