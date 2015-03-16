package com.id.kas.pojo.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblUserGroup;

public class TblUserGroupDAO {
	private Session session;
	
	public TblUserGroupDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblUserGroup tblusergroup){
		session.save(tblusergroup);
	}
		
	public void delete(TblUserGroup tblusergroup){
		session.delete(tblusergroup);
	}
	
	public void update(TblUserGroup tblusergroup){
		session.update(tblusergroup);
	}
	
	public TblUserGroup getById(String	id, BigDecimal groupId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblUserGroup.class);		
			criteria.add(Restrictions.eq("userId", id));
			criteria.add(Restrictions.eq("groupId", groupId));		
		return (TblUserGroup) criteria.uniqueResult();
	}
	
	
	public List<TblUserGroup> getBy(String id, BigDecimal groupId,int start, int rowcount ){
		System.out.println("groupId "+groupId+" ID "+id);
		Criteria criteria =null;
		criteria = session.createCriteria(TblUserGroup.class);
		if (id.length()>0){
			criteria.add(Restrictions.eq("userId", id));
		}
		if (groupId.doubleValue() > 0.0){
			criteria.add(Restrictions.eq("groupId", groupId));
		}
		return (List<TblUserGroup>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String id, BigDecimal groupId,int start, int rowcount ){
		System.out.println("groupId "+groupId+" ID "+id);
		Criteria criteria =null;
		criteria = session.createCriteria(TblUserGroup.class);
		if (id.length()>0){
			criteria.add(Restrictions.eq("userId", id));
		}
		if (groupId.doubleValue() > 0.0){
			criteria.add(Restrictions.eq("groupId", groupId));
		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String id, BigDecimal groupId,int start, int rowcount){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(id, groupId, start,rowcount);
		List<TblUserGroup> l = getBy(id, groupId, start,rowcount);
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}
	
	public List<TblUserGroup> getAll(){
		Criteria criteria =null;
		criteria = session.createCriteria(TblUserGroup.class);
		return (List<TblUserGroup>) criteria.list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblUserGroup.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblUserGroup> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblUserGroup>) session.createCriteria(TblUserGroup.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

	
}
