package com.id.kas.DEVELOPMENT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblSeq;

public class TblSeqDAO {
	private Session session;
	
	public TblSeqDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblSeq tblseq){
		session.save(tblseq);
	}
		
	public void delete(TblSeq tblseq){
		session.delete(tblseq);
	}
	
	public void update(TblSeq tblseq){
		session.update(tblseq);
	}
//====================================================================	
	public TblSeq getById(String nik){
		Criteria criteria =null;
		criteria = session.createCriteria(TblSeq.class);
                    if (nik.length()>0){criteria.add(Restrictions.eq("nik", nik)); 	}

		return (TblSeq)  criteria.uniqueResult();//session.get(TblSeq.class, id);
	}
	
	public List<TblSeq> getAll(){
		return (List<TblSeq>) session.createCriteria(TblSeq.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblSeq.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblSeq> getAll(int start, int rowcount ){
		return (List<TblSeq>) session.createCriteria(TblSeq.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Nik){
		Criteria criteria =null;
		criteria = session.createCriteria(TblSeq.class);
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
		
		return criteria;
	}

	public List<TblSeq> getBy(String Nik ,int start, int rowcount ){
		Criteria criteria =getCriteria(Nik);
		return (List<TblSeq>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Nik, int start, int rowcount  ){
		Criteria criteria =getCriteria(Nik);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Nik ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Nik,  start,rowcount);//total jumlah row
		List<TblSeq> l = getBy(Nik, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}