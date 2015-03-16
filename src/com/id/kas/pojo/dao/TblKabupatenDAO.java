package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblKabupaten;

public class TblKabupatenDAO {
	private Session session;
	
	public TblKabupatenDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblKabupaten tblkabupaten){
		session.save(tblkabupaten);
	}
		
	public void delete(TblKabupaten tblkabupaten){
		session.delete(tblkabupaten);
	}
	
	public void update(TblKabupaten tblkabupaten){
		session.update(tblkabupaten);
	}
	
	public TblKabupaten getById(String	kodeProvinsi, String kodeKabupaten){
		Criteria criteria =null;
		criteria = session.createCriteria(TblKabupaten.class);
		criteria.add(Restrictions.eq("kodeProvinsi", kodeProvinsi));
		criteria.add(Restrictions.eq("kodeKabupaten", kodeKabupaten));
		return (TblKabupaten)  criteria.uniqueResult();//session.get(TblKabupaten.class, id);
	}
	
	public List<TblKabupaten> getAll(){
		return (List<TblKabupaten>) session.createCriteria(TblKabupaten.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblKabupaten.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblKabupaten> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblKabupaten>) session.createCriteria(TblKabupaten.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

//SESUAIKAN DENGAN KRITERIA
	public Criteria getCriteria(String kriteria1, String kriteria2){
		Criteria criteria =null;
		criteria = session.createCriteria(TblKabupaten.class);
		if (kriteria1.length()>0){
			criteria.add(Restrictions.eq("kodeProvinsi", kriteria1));
		}
		if (kriteria2.length()>0){
			criteria.add(Restrictions.eq("kodeKabupaten", kriteria2));
		}
		return criteria;
	}

	public List<TblKabupaten> getBy(String kriteria1, String kriteria2,int start, int rowcount ){
		System.out.println("kriteria1 "+kriteria1+" kriteria2 "+kriteria2);
		Criteria criteria =getCriteria(kriteria1, kriteria2);
		return (List<TblKabupaten>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String kriteria1, String kriteria2,int start, int rowcount  ){
		Criteria criteria =getCriteria(kriteria1, kriteria2);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String kriteria1, String kriteria2,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(kriteria1, kriteria2, start,rowcount);//total jumlah row
		List<TblKabupaten> l = getBy(kriteria1, kriteria2, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}
	
}
