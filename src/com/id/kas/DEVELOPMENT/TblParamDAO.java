package com.id.kas.DEVELOPMENT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblParam;

public class TblParamDAO {
	private Session session;
	
	public TblParamDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblParam tblparam){
		session.save(tblparam);
	}
		
	public void delete(TblParam tblparam){
		session.delete(tblparam);
	}
	
	public void update(TblParam tblparam){
		session.update(tblparam);
	}
//====================================================================	
	public TblParam getById(String key){
		Criteria criteria =null;
		criteria = session.createCriteria(TblParam.class);
                    if (key.length()>0){criteria.add(Restrictions.eq("key", key)); 	}

		return (TblParam)  criteria.uniqueResult();//session.get(TblParam.class, id);
	}
	
	public List<TblParam> getAll(){
		return (List<TblParam>) session.createCriteria(TblParam.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblParam.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblParam> getAll(int start, int rowcount ){
		return (List<TblParam>) session.createCriteria(TblParam.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Value,String Key){
		Criteria criteria =null;
		criteria = session.createCriteria(TblParam.class);
                    if (Value.length()>0){criteria.add(Restrictions.eq("value", Value)); 	}
                    if (Key.length()>0){criteria.add(Restrictions.eq("key", Key)); 	}
		
		return criteria;
	}

	public List<TblParam> getBy(String Value,String Key ,int start, int rowcount ){
		Criteria criteria =getCriteria(Value,Key);
		return (List<TblParam>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Value,String Key, int start, int rowcount  ){
		Criteria criteria =getCriteria(Value,Key);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Value,String Key ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Value,Key,  start,rowcount);//total jumlah row
		List<TblParam> l = getBy(Value,Key, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
