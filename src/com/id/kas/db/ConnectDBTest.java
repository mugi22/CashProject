package com.id.kas.db;

import org.hibernate.Session;

public class ConnectDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session =null;
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("connect sukses....");
		session.close();
		System.out.println("Session Selesai");
		} catch (Exception e) {
			System.out.println("connect Gagal....");
			e.printStackTrace();
		}
	}

}
