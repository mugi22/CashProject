package com.id.kas.pojo.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblGroup;

public class TblGroupDAO {
	private Session session;
	
	public TblGroupDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblGroup tblgroup){
		session.save(tblgroup);
	}
		
	public void delete(TblGroup tblgroup){
		session.delete(tblgroup);
	}
	
	public void update(TblGroup tblgroup){
		session.update(tblgroup);
	}
//====================================================================	
	public TblGroup getById(BigDecimal groupId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblGroup.class);
                    if (groupId.doubleValue()>0.0){criteria.add(Restrictions.eq("groupId", groupId)); 	}

		return (TblGroup)  criteria.uniqueResult();//session.get(TblGroup.class, id);
	}
	
	public List<TblGroup> getAll(){
		return (List<TblGroup>) session.createCriteria(TblGroup.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblGroup.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblGroup> getAll(int start, int rowcount ){
		return (List<TblGroup>) session.createCriteria(TblGroup.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(BigDecimal GroupId,String Jabatan){
		Criteria criteria =null;
		criteria = session.createCriteria(TblGroup.class);
                    if (GroupId.doubleValue()>0){criteria.add(Restrictions.eq("groupId", GroupId)); 	}
                    if (Jabatan.length()>0){criteria.add(Restrictions.eq("jabatan", Jabatan)); 	}
		
		return criteria;
	}

	public List<TblGroup> getBy(BigDecimal GroupId,String Jabatan ,int start, int rowcount ){
		Criteria criteria =getCriteria(GroupId,Jabatan);
		return (List<TblGroup>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(BigDecimal GroupId,String Jabatan, int start, int rowcount  ){
		Criteria criteria =getCriteria(GroupId,Jabatan);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(BigDecimal GroupId,String Jabatan ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(GroupId,Jabatan,  start,rowcount);//total jumlah row
		List<TblGroup> l = getBy(GroupId,Jabatan, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
