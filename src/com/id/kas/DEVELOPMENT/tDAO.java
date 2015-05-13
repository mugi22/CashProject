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

import com.id.kas.pojo.t;

public class tDAO {
	private Session session;
	
	public tDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(t t){
		session.save(t);
	}
		
	public void delete(t t){
		session.delete(t);
	}
	
	public void update(t t){
		session.update(t);
	}
//====================================================================	
	public t getById(String  nama){
		Criteria criteria =null;
		criteria = session.createCriteria(t.class);
                    if (nama.length()>0){criteria.add(Restrictions.eq("nama", nama)); 	}

		return (t)  criteria.uniqueResult();//session.get(t.class, id);
	}
	
	public List<t> getAll(){
		return (List<t>) session.createCriteria(t.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(t.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<t> getAll(int start, int rowcount ){
		return (List<t>) session.createCriteria(t.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Nama,String UnitKerja,String Nik){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(t.class);
                    if (Nama.length()>0){criteria.add(Restrictions.eq("nama", Nama)); 	}
                    if (UnitKerja.length()>0){criteria.add(Restrictions.eq("unitKerja", UnitKerja)); 	}
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
		
		return criteria;
	}

	public List<t> getBy(String Nama,String UnitKerja,String Nik ,int start, int rowcount ){
		Criteria criteria =getCriteria(Nama,UnitKerja,Nik);
		return (List<t>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Nama,String UnitKerja,String Nik, int start, int rowcount  ){
		Criteria criteria =getCriteria(Nama,UnitKerja,Nik);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Nama,String UnitKerja,String Nik ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Nama,UnitKerja,Nik,  start,rowcount);//total jumlah row
		List<t> l = getBy(Nama,UnitKerja,Nik, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<t> getBy(String Nama,String UnitKerja,String Nik  ){
		Criteria criteria =getCriteria(Nama,UnitKerja,Nik);
		return (List<t>) criteria.list();
	}


}
