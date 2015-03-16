package com.id.kas.pojo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

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
	
	public TblGroup getById(BigDecimal	id){
		return (TblGroup) session.get(TblGroup.class, id);
	}
	
	public List<TblGroup> getAll(){
		return (List<TblGroup>) session.createCriteria(TblGroup.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblGroup.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblGroup> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblGroup>) session.createCriteria(TblGroup.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

	
}
