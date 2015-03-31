package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblTarif;

//import com.id.kas.pojo.TblTarif;

public class TblTarifDAO {
	private Session session;
	
	public TblTarifDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblTarif tbltarif){
		session.save(tbltarif);
	}
		
	public void delete(TblTarif tbltarif){
		session.delete(tbltarif);
	}
	
	public void update(TblTarif tbltarif){
		session.update(tbltarif);
	}
//====================================================================	
	public TblTarif getById(String grade,String idTarif){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTarif.class);
                    if (grade.length()>0){criteria.add(Restrictions.eq("grade", grade)); 	}
                    if (idTarif.length()>0){criteria.add(Restrictions.eq("idTarif", idTarif)); 	}

		return (TblTarif)  criteria.uniqueResult();//session.get(TblTarif.class, id);
	}
	
	public List<TblTarif> getAll(){
		return (List<TblTarif>) session.createCriteria(TblTarif.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblTarif.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblTarif> getAll(int start, int rowcount ){
		return (List<TblTarif>) session.createCriteria(TblTarif.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Grade,String IdTarif){
		Criteria criteria =null;
		criteria = session.createCriteria(TblTarif.class);
                    if (Grade.length()>0){criteria.add(Restrictions.eq("grade", Grade)); 	}
                    if (IdTarif.length()>0){criteria.add(Restrictions.eq("idTarif", IdTarif)); 	}
		
		return criteria;
	}

	public List<TblTarif> getBy(String Grade,String IdTarif ,int start, int rowcount ){
		Criteria criteria =getCriteria(Grade,IdTarif);
		return (List<TblTarif>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Grade,String IdTarif, int start, int rowcount  ){
		Criteria criteria =getCriteria(Grade,IdTarif);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Grade,String IdTarif ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Grade,IdTarif,  start,rowcount);//total jumlah row
		List<TblTarif> l = getBy(Grade,IdTarif, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
