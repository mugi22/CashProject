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

import com.id.kas.pojo.TblKaryawan;

public class TblKaryawanDAO {
	private Session session;
	
	public TblKaryawanDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblKaryawan tblkaryawan){
		session.save(tblkaryawan);
	}
		
	public void delete(TblKaryawan tblkaryawan){
		session.delete(tblkaryawan);
	}
	
	public void update(TblKaryawan tblkaryawan){
		session.update(tblkaryawan);
	}
//====================================================================	
	public TblKaryawan getById(String  nik){
		Criteria criteria =null;
		criteria = session.createCriteria(TblKaryawan.class);
                    if (nik.length()>0){criteria.add(Restrictions.eq("nik", nik)); 	}

		return (TblKaryawan)  criteria.uniqueResult();//session.get(TblKaryawan.class, id);
	}
	
	public List<TblKaryawan> getAll(){
		return (List<TblKaryawan>) session.createCriteria(TblKaryawan.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblKaryawan.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblKaryawan> getAll(int start, int rowcount ){
		return (List<TblKaryawan>) session.createCriteria(TblKaryawan.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Nik,String Nama,String UnitKerja){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblKaryawan.class);
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
                    if (Nama.length()>0){criteria.add(Restrictions.eq("nama", Nama)); 	}
                    if (UnitKerja.length()>0){criteria.add(Restrictions.eq("unitKerja", UnitKerja)); 	}
		
		return criteria;
	}

	public List<TblKaryawan> getBy(String Nik,String Nama,String UnitKerja ,int start, int rowcount ){
		Criteria criteria =getCriteria(Nik,Nama,UnitKerja);
		return (List<TblKaryawan>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Nik,String Nama,String UnitKerja, int start, int rowcount  ){
		Criteria criteria =getCriteria(Nik,Nama,UnitKerja);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Nik,String Nama,String UnitKerja ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Nik,Nama,UnitKerja,  start,rowcount);//total jumlah row
		List<TblKaryawan> l = getBy(Nik,Nama,UnitKerja, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<TblKaryawan> getBy(String Nik,String Nama,String UnitKerja  ){
		Criteria criteria =getCriteria(Nik,Nama,UnitKerja);
		return (List<TblKaryawan>) criteria.list();
	}


}
