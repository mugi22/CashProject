package com.id.kas.db;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.id.kas.pojo.TblUser;
//import com.id.kas.pojo.TblUserHome;

public class TestConnect {
public static void main(String[] args) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	TblUser pojo = new TblUser();
	pojo.setBranchCode("99999");
	pojo.setCreateBy("mugi");
	pojo.setCreateDate(new Date());
	pojo.setUserId("P99");
	pojo.setName("Badrull");
	pojo.setEndTime(new Date());
	pojo.setPassword("123");
	pojo.setStartTime(new Date());
	
//	TblUserHome dao = new TblUserHome();
	
	session.beginTransaction();
	session.save(pojo);
	session.getTransaction().commit();
	session.close();
	
}
}
