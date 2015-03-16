package com.id.kas.pojo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.id.kas.pojo.TblBranch;
import com.id.kas.pojo.TblUser;

public class TblBranchDAO {
private Session session;
	
	
	
	public TblBranchDAO(Session session) {	
	this.session = session;
}

	public List<TblBranch> getAll(){
		return (List<TblBranch>) session.createCriteria(TblBranch.class).list();
	}
	
	public void insert(TblBranch tbl){
		session.save(tbl);
	}
		
	public void delete(TblBranch tbl){
		session.delete(tbl);
	}
	
	public void update(TblBranch tbl){
		session.update(tbl);
	}
	
	public TblBranch getById(String	id){
		return (TblBranch) session.get(TblBranch.class, id);
	}
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblBranch.class).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public List<TblBranch> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblBranch>) session.createCriteria(TblBranch.class).setFirstResult(start).setMaxResults(rowcount).list();
	}
}
