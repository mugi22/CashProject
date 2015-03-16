package com.id.kas.pojo.dao;

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
	
	public TblSeq getById(String	id){
		Criteria criteria =null;
		criteria = session.createCriteria(TblSeq.class);
		criteria.add(Restrictions.eq("id", id));
		return (TblSeq)  criteria.uniqueResult();//session.get(TblSeq.class, id);
	}
	
	public List<TblSeq> getAll(){
		return (List<TblSeq>) session.createCriteria(TblSeq.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblSeq.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblSeq> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblSeq>) session.createCriteria(TblSeq.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String kriteria1/*, String kriteria2*/){
		Criteria criteria =null;
		criteria = session.createCriteria(TblSeq.class);
		if (kriteria1.length()>0){
			criteria.add(Restrictions.eq("kriteria1", kriteria1)); //Ganti Properti kriteria1
		}
//		if (kriteria2.length()>0){
//			criteria.add(Restrictions.like("kriteria2", "%"+kriteria2+"%"));
//		}
		return criteria;
	}

	public List<TblSeq> getBy(String kriteria1/*, String kriteria2*/,int start, int rowcount ){
//		System.out.println("kriteria1 "+kriteria1+" kriteria2 "+kriteria2);
		Criteria criteria =getCriteria(kriteria1/*, kriteria2*/);
		return (List<TblSeq>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String kriteria1,/* String kriteria2,*/int start, int rowcount  ){
		Criteria criteria =getCriteria(kriteria1/*, kriteria1*/);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String kriteria1/*, String kriteria2*/,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(kriteria1,/* kriteria2,*/ start,rowcount);//total jumlah row
		List<TblSeq> l = getBy(kriteria1, /*kriteria2,*/ start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
