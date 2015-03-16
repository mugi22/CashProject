package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblLookup;

public class TblLookupDAO {
	private Session session;
	
	public TblLookupDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblLookup tbllookup){
		session.save(tbllookup);
	}
		
	public void delete(TblLookup tbllookup){
		session.delete(tbllookup);
	}
	
	public void update(TblLookup tbllookup){
		session.update(tbllookup);
	}
	
	public TblLookup getById(String	lookupName,String lookupValue){
		Criteria criteria =null;
		criteria = session.createCriteria(TblLookup.class);
		criteria.add(Restrictions.eq("lookupName", lookupName));
		criteria.add(Restrictions.eq("lookupValue", lookupValue));
		return  (TblLookup) criteria.uniqueResult();// (TblLookup) session.get(TblLookup.class, id);
	}
	
	public List<TblLookup> getAll(){
		return (List<TblLookup>) session.createCriteria(TblLookup.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblLookup.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblLookup> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblLookup>) session.createCriteria(TblLookup.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

//SESUAIKAN DENGAN KRITERIA
	
public Criteria getCriteria(String lookupName, String lookupValue){
	Criteria criteria =null;
	criteria = session.createCriteria(TblLookup.class);
	if (lookupName.length()>0){
		criteria.add(Restrictions.eq("lookupName", lookupName));
	}
	if (lookupValue.length()>0){
		criteria.add(Restrictions.like("lookupValue", "%"+lookupValue+"%"));
	}
	return criteria;
}
public List<TblLookup> getBy(String lookupName, String lookupValue,int start, int rowcount ){
		System.out.println("lookupName "+lookupName+" lookupValue "+lookupValue);
		Criteria criteria =getCriteria(lookupName, lookupValue);
		return (List<TblLookup>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String lookupName, String lookupValue,int start, int rowcount  ){
		Criteria criteria =getCriteria(lookupName, lookupValue);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String lookupName, String lookupValue,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(lookupName, lookupValue, start,rowcount);
		List<TblLookup> l = getBy(lookupName, lookupValue, start,rowcount);
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}



	
}
