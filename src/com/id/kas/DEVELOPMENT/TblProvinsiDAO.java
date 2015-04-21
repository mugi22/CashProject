/*
*Create by CodeGenerator
*daoTemplate
*/

package com.id.kas.DEVELOPMENT;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
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
//====================================================================	
	public TblProvinsi getById(String  kodeProvinsi){
		Criteria criteria =null;
		criteria = session.createCriteria(TblProvinsi.class);
                    if (kodeProvinsi.length()>0){criteria.add(Restrictions.eq("kodeProvinsi", kodeProvinsi)); 	}

		return (TblProvinsi)  criteria.uniqueResult();//session.get(TblProvinsi.class, id);
	}
	
	public List<TblProvinsi> getAll(){
		return (List<TblProvinsi>) session.createCriteria(TblProvinsi.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblProvinsi.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblProvinsi> getAll(int start, int rowcount ){
		return (List<TblProvinsi>) session.createCriteria(TblProvinsi.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String KodeProvinsi,String NamaProvinsi){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblProvinsi.class);
                    if (KodeProvinsi.length()>0){criteria.add(Restrictions.eq("kodeProvinsi", KodeProvinsi)); 	}
                    if (NamaProvinsi.length()>0){criteria.add(Restrictions.eq("namaProvinsi", NamaProvinsi)); 	}
		
		return criteria;
	}

	public List<TblProvinsi> getBy(String KodeProvinsi,String NamaProvinsi ,int start, int rowcount ){
		Criteria criteria =getCriteria(KodeProvinsi,NamaProvinsi);
		return (List<TblProvinsi>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String KodeProvinsi,String NamaProvinsi, int start, int rowcount  ){
		Criteria criteria =getCriteria(KodeProvinsi,NamaProvinsi);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String KodeProvinsi,String NamaProvinsi ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(KodeProvinsi,NamaProvinsi,  start,rowcount);//total jumlah row
		List<TblProvinsi> l = getBy(KodeProvinsi,NamaProvinsi, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
