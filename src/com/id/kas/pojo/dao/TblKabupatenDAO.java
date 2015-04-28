/*
*Create by CodeGenerator
*daoTemplate
*/

package com.id.kas.pojo.dao;
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
//====================================================================	
	public TblKabupaten getById(String  kodeProvinsi,String  kodeKabupaten){
		Criteria criteria =null;
		criteria = session.createCriteria(TblKabupaten.class);
                    if (kodeProvinsi.length()>0){criteria.add(Restrictions.eq("kodeProvinsi", kodeProvinsi)); 	}
                    if (kodeKabupaten.length()>0){criteria.add(Restrictions.eq("kodeKabupaten", kodeKabupaten)); 	}

		return (TblKabupaten)  criteria.uniqueResult();//session.get(TblKabupaten.class, id);
	}
	
	public List<TblKabupaten> getAll(){
		return (List<TblKabupaten>) session.createCriteria(TblKabupaten.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblKabupaten.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblKabupaten> getAll(int start, int rowcount ){
		return (List<TblKabupaten>) session.createCriteria(TblKabupaten.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String KodeProvinsi,String KodeKabupaten,String NamaKabupaten){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		System.out.println("kode pro vinsi "+ KodeProvinsi);
		criteria = session.createCriteria(TblKabupaten.class);
                    if (KodeProvinsi.length()>0){criteria.add(Restrictions.eq("kodeProvinsi", KodeProvinsi)); 	}
                    if (KodeKabupaten.length()>0){criteria.add(Restrictions.eq("kodeKabupaten", KodeKabupaten)); 	}
                    if (NamaKabupaten.length()>0){criteria.add(Restrictions.eq("namaKabupaten", NamaKabupaten)); 	}
		
		return criteria;
	}

	public List<TblKabupaten> getBy(String KodeProvinsi,String KodeKabupaten,String NamaKabupaten ,int start, int rowcount ){
		Criteria criteria =getCriteria(KodeProvinsi,KodeKabupaten,NamaKabupaten);
		return (List<TblKabupaten>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String KodeProvinsi,String KodeKabupaten,String NamaKabupaten, int start, int rowcount  ){
		Criteria criteria =getCriteria(KodeProvinsi,KodeKabupaten,NamaKabupaten);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String KodeProvinsi,String KodeKabupaten,String NamaKabupaten ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(KodeProvinsi,KodeKabupaten,NamaKabupaten,  start,rowcount);//total jumlah row
		List<TblKabupaten> l = getBy(KodeProvinsi,KodeKabupaten,NamaKabupaten, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
