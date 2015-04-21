package com.id.unitTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.dao.TblUserDAO;

public class TblUserDAOTest {

	Session session;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("1");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		dao = new TblUserDAO(session);
	}

	@After
	public void tearDown() throws Exception {
	}

	private TblUserDAO dao;
	@Test
	public final void testTblUserDAO() {		
//		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInsert() {		
		TblUser pojo = new TblUser();
		pojo.setBranchCode("99999");
		pojo.setCreateBy("mugi");
		pojo.setCreateDate(new Date());
		pojo.setUserId("PMugi");
		pojo.setName("User Mugi Test");
		pojo.setEndTime(new Date());
		pojo.setPassword("123");
		pojo.setStartTime(new Date());
		session.beginTransaction();
		session.save(pojo);
		session.getTransaction().commit();
//		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetById() {
		TblUser tblUser = (TblUser)dao.getById("PMugi");
		System.out.println(tblUser.getName()+" "+tblUser.getUserId());
//		fail("Not yet implemented"); // TODO
	}
	
	
	@Test
	public final void testUpdate(){
		TblUser tblUser = (TblUser)dao.getById("PMugi");
		tblUser.setName("UPDATED YAAAAA");
		session.beginTransaction();
		session.update(tblUser);
		session.getTransaction().commit();
		testGetById();
	}
	
	@Test
	public final void testDelete(){
		TblUser tblUser = (TblUser)dao.getById("PMugi");
		session.beginTransaction();
		session.delete(tblUser);
		session.getTransaction().commit();
	}

	@Test
	public final void testGetAll() {
		List<TblUser> l = dao.getAll();
		for(TblUser tbl :l){
			System.out.println(tbl.getUserId()+"  "+tbl.getName());
		}
	}
	
	@Test
	public final void testGetRowcount() {
		List<TblUser> l = dao.getAll(10,10);
		for(TblUser tbl :l){
			System.out.println(tbl.getUserId()+"  "+tbl.getName());
		}
	}
	
	
	
	
}
