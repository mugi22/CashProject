package com.id.kas.pojo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.id.kas.pojo.TblUser;

public class TblUserDAO {
	private Session session;
	
	public TblUserDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblUser tblUser){
		session.save(tblUser);
	}
		
	public void delete(TblUser tblUser){
		session.delete(tblUser);
	}
	
	public void update(TblUser tblUser){
		session.update(tblUser);
	}
	
	public TblUser getById(String	user){
		return (TblUser) session.get(TblUser.class, user);
	}
	
	public List<TblUser> getAll(){
		return (List<TblUser>) session.createCriteria(TblUser.class).list();
	}
	
	/*
	 * Criteria crit = session.createCriteria(Person.class);
crit.add( Restrictions.isNotNull("birthDate"));
crit.add( Restrictions.eq("isStudent", true));
criteria.setProjection(Projections.rowCount());
Integer count = criteria.uniqueResult();
	 */
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblUser.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblUser> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblUser>) session.createCriteria(TblUser.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

	
}
