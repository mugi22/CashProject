package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblProvinsi;

public class TblProvinsiDAO {
	private Session session;
	
	public TblProvinsiDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblProvinsi tblprovinsi){
		session.save(tblprovinsi);
	}
		
	public void delete(TblProvinsi tblprovinsi){
		session.delete(tblprovinsi);
	}
	
	public void update(TblProvinsi tblprovinsi){
		session.update(tblprovinsi);
	}
	
	public TblProvinsi getById(String	id){
		Criteria criteria =null;
		criteria = session.createCriteria(TblProvinsi.class);
		criteria.add(Restrictions.eq("id", id));
		return (TblProvinsi)  criteria.uniqueResult();//session.get(TblProvinsi.class, id);
	}
	
	public List<TblProvinsi> getAll(){
		return (List<TblProvinsi>) session.createCriteria(TblProvinsi.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblProvinsi.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblProvinsi> getAll(int start, int rowcount ){
//		Criteria criteria = session.createCriteria(TblProvinsi.class).setFirstResult(mulai).setMaxResults(jumlah); 
		return (List<TblProvinsi>) session.createCriteria(TblProvinsi.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

//SESUAIKAN DENGAN KRITERIA
public List<TblProvinsi> getBy(String id,int start, int rowcount ){ // BigDecimal groupId =dihapus, key hanya 1 column
		System.out.println(" ID "+id);
		Criteria criteria =null;
		criteria = session.createCriteria(TblProvinsi.class);
		if (id.length()>0){
			criteria.add(Restrictions.eq("kodeProvinsi", id));
		}		
		return (List<TblProvinsi>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String id,int start, int rowcount ){
		System.out.println(" ID "+id);
		Criteria criteria =null;
		criteria = session.createCriteria(TblProvinsi.class);
		if (id.length()>0){
			criteria.add(Restrictions.eq("userId", id));
		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String id,int start, int rowcount){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(id, start,rowcount);
		List<TblProvinsi> l = getBy(id, start,rowcount);
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
