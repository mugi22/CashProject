package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
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
//====================================================================	
	public TblLookup getById(String lookupValue,String lookupName){
		Criteria criteria =null;
		criteria = session.createCriteria(TblLookup.class);
                    if (lookupValue.length()>0){criteria.add(Restrictions.eq("lookupValue", lookupValue)); 	}
                    if (lookupName.length()>0){criteria.add(Restrictions.eq("lookupName", lookupName)); 	}

		return (TblLookup)  criteria.uniqueResult();//session.get(TblLookup.class, id);
	}
	
	public List<TblLookup> getAll(){
		return (List<TblLookup>) session.createCriteria(TblLookup.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblLookup.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblLookup> getAll(int start, int rowcount ){
		return (List<TblLookup>) session.createCriteria(TblLookup.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String LookupValue,String LookupName){
		Criteria criteria =null;
		criteria = session.createCriteria(TblLookup.class);
                    if (LookupValue.length()>0){criteria.add(Restrictions.eq("lookupValue", LookupValue)); 	}
                    if (LookupName.length()>0){criteria.add(Restrictions.eq("lookupName", LookupName)); 	}
		
		return criteria;
	}
	public Criteria getCriteria(String LookupName){
		Criteria criteria =null;
		criteria = session.createCriteria(TblLookup.class);
//                    if (LookupValue.length()>0){criteria.add(Restrictions.eq("lookupValue", LookupValue)); 	}
                    if (LookupName.length()>0){criteria.add(Restrictions.eq("lookupName", LookupName)); 	}
		
		return criteria;
	}
	//ambil semua
	public List<TblLookup> getBy(String LookupName ){
		Criteria criteria =getCriteria(LookupName);
		return (List<TblLookup>) criteria.list();
	}
	
	public List<TblLookup> getBy(String LookupValue,String LookupName ,int start, int rowcount ){
		Criteria criteria =getCriteria(LookupValue,LookupName);
		return (List<TblLookup>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String LookupValue,String LookupName, int start, int rowcount  ){
		Criteria criteria =getCriteria(LookupValue,LookupName);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String LookupValue,String LookupName ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(LookupValue,LookupName,  start,rowcount);//total jumlah row
		List<TblLookup> l = getBy(LookupValue,LookupName, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
