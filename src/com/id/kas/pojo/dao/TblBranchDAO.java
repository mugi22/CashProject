package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblBranch;

public class TblBranchDAO {
	private Session session;
	
	public TblBranchDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblBranch tblbranch){
		session.save(tblbranch);
	}
		
	public void delete(TblBranch tblbranch){
		session.delete(tblbranch);
	}
	
	public void update(TblBranch tblbranch){
		session.update(tblbranch);
	}
//====================================================================	
	public TblBranch getById(String branchCode){
		Criteria criteria =null;
		criteria = session.createCriteria(TblBranch.class);
                    if (branchCode.length()>0){criteria.add(Restrictions.eq("branchCode", branchCode)); 	}

		return (TblBranch)  criteria.uniqueResult();//session.get(TblBranch.class, id);
	}
	
	public List<TblBranch> getAll(){
		return (List<TblBranch>) session.createCriteria(TblBranch.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblBranch.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblBranch> getAll(int start, int rowcount ){
		return (List<TblBranch>) session.createCriteria(TblBranch.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Name,String Status){
		Criteria criteria =null;
		criteria = session.createCriteria(TblBranch.class);
                    if (Name.length()>0){criteria.add(Restrictions.eq("name", Name)); 	}
                    if (Status.length()>0){criteria.add(Restrictions.eq("status", Status)); 	}
		
		return criteria;
	}

	public  List<TblBranch> getByParent(String KodeParent){
		Criteria criteria =null;
		criteria = session.createCriteria(TblBranch.class);
                    if (KodeParent.length()>0){criteria.add(Restrictions.eq("parentId", KodeParent)); 	}
//                    if (Status.length()>0){criteria.add(Restrictions.eq("status", Status)); 	}
		
		return criteria.list();
	}
	
	
	
	
	public List<TblBranch> getBy(String Name,String Status ,int start, int rowcount ){
		Criteria criteria =getCriteria(Name,Status);
		return (List<TblBranch>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Name,String Status, int start, int rowcount  ){
		Criteria criteria =getCriteria(Name,Status);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Name,String Status ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Name,Status,  start,rowcount);//total jumlah row
		List<TblBranch> l = getBy(Name,Status, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
