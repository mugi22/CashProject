package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.DEVELOPMENT.TblTest;

public class TblTestDAO {
	private Session session;
	
	public TblTestDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblTest tbltest){
		session.save(tbltest);
	}
		
	public void delete(TblTest tbltest){
		session.delete(tbltest);
	}
	
	public void update(TblTest tbltest){
		session.update(tbltest);
	}
//====================================================================	
	public TblTest getById(String nik){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTest.class);
                    if (nik.length()>0){criteria.add(Restrictions.eq("nik", nik)); 	}

		return (TblTest)  criteria.uniqueResult();//session.get(TblTest.class, id);
	}
	
	public List<TblTest> getAll(){
		return (List<TblTest>) session.createCriteria(TblTest.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblTest.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblTest> getAll(int start, int rowcount ){
		return (List<TblTest>) session.createCriteria(TblTest.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Nik,String Nama){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTest.class);
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
                    if (Nama.length()>0){criteria.add(Restrictions.eq("nama", Nama)); 	}
		
		return criteria;
	}

	public List<TblTest> getBy(String Nik,String Nama ,int start, int rowcount ){
		Criteria criteria =getCriteria(Nik,Nama);
		return (List<TblTest>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Nik,String Nama, int start, int rowcount  ){
		Criteria criteria =getCriteria(Nik,Nama);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Nik,String Nama ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Nik,Nama,  start,rowcount);//total jumlah row
		List<TblTest> l = getBy(Nik,Nama, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
