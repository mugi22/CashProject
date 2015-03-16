package com.id.kas.pojo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

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
	
	public TblParam getById(String	id){
		return (TblParam) session.get(TblParam.class, id);
	}
	
	public List<TblParam> getAll(){
		return (List<TblParam>) session.createCriteria(TblParam.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblParam.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblParam> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblParam>) session.createCriteria(TblParam.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

	
}
