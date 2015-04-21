package com.id.kas.pojo.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblKabupaten;
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

	
	/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String userId,String branchCode){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		System.out.println("branchCode "+ branchCode);
		criteria = session.createCriteria(TblUser.class);
                    if (userId.length()>0){criteria.add(Restrictions.eq("userId", userId)); 	}
                    if (branchCode.length()>0){criteria.add(Restrictions.eq("branchCode", branchCode)); 	}          
		return criteria;
	}
	
	public List<TblUser> getBy(String userId,String branchCode,int start, int rowcount ){
		Criteria criteria =getCriteria(userId,branchCode);
		return (List<TblUser>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String userId,String branchCode, int start, int rowcount  ){
		Criteria criteria =getCriteria(userId,branchCode);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	
	public Map<String,Object> getByPerPage(String userId,String branchCode ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(userId,branchCode,  start,rowcount);//total jumlah row
		List<TblUser> l = getBy(userId,branchCode , start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}
}
