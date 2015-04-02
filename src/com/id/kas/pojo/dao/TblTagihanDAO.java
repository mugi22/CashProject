package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblTagihan;

//import com.id.kas.pojo.TblTagihan;

public class TblTagihanDAO {
	private Session session;
	
	public TblTagihanDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblTagihan tbltagihan){
		session.save(tbltagihan);
	}
		
	public void delete(TblTagihan tbltagihan){
		session.delete(tbltagihan);
	}
	
	public void update(TblTagihan tbltagihan){
		session.update(tbltagihan);
	}
//====================================================================	
	public TblTagihan getById(String nik,String yemm,String branchcode){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTagihan.class);
                    if (nik.length()>0){criteria.add(Restrictions.eq("nik", nik)); 	}
                    if (yemm.length()>0){criteria.add(Restrictions.eq("yemm", yemm)); 	}
                    if (branchcode.length()>0){criteria.add(Restrictions.eq("branchcode", branchcode)); 	}

		return (TblTagihan)  criteria.uniqueResult();//session.get(TblTagihan.class, id);
	}
	
	public List<TblTagihan> getByIdAndBranch(String nik,String branchcode,String statBayar){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTagihan.class);
                    criteria.add(Restrictions.eq("nik", nik));                     
                    criteria.add(Restrictions.eq("branchcode", branchcode)); 
                    criteria.add(Restrictions.eq("satusBayar", statBayar)); 
		return   criteria.list();
	} 
	
	public TblTagihan getByIdAndBranchAndYemm(String nik,String branchcode,String statBayar,String yemm){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTagihan.class);
                    criteria.add(Restrictions.eq("nik", nik));                     
                    criteria.add(Restrictions.eq("branchcode", branchcode)); 
                    criteria.add(Restrictions.eq("satusBayar", statBayar)); 
                    criteria.add(Restrictions.eq("yemm", yemm)); 
		return   (TblTagihan) criteria.uniqueResult();
	}
	
	public List<TblTagihan> getAll(){
		return (List<TblTagihan>) session.createCriteria(TblTagihan.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblTagihan.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblTagihan> getAll(int start, int rowcount ){
		return (List<TblTagihan>) session.createCriteria(TblTagihan.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Nik,String Yemm,String Branchcode){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTagihan.class);
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
                    if (Yemm.length()>0){criteria.add(Restrictions.eq("yemm", Yemm)); 	}
                    if (Branchcode.length()>0){criteria.add(Restrictions.eq("branchcode", Branchcode)); 	}
		
		return criteria;
	}

	public List<TblTagihan> getBy(String Nik,String Yemm,String Branchcode ,int start, int rowcount ){
		Criteria criteria =getCriteria(Nik,Yemm,Branchcode);
		return (List<TblTagihan>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Nik,String Yemm,String Branchcode, int start, int rowcount  ){
		Criteria criteria =getCriteria(Nik,Yemm,Branchcode);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Nik,String Yemm,String Branchcode ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Nik,Yemm,Branchcode,  start,rowcount);//total jumlah row
		List<TblTagihan> l = getBy(Nik,Yemm,Branchcode, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
