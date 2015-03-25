package com.id.kas.pojo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblPegawai;

public class TblPegawaiDAO {
	private Session session;
	
	public TblPegawaiDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblPegawai tblpegawai){
		session.save(tblpegawai);
	}
		
	public void delete(TblPegawai tblpegawai){
		session.delete(tblpegawai);
	}
	
	public void update(TblPegawai tblpegawai){
		session.update(tblpegawai);
	}
//====================================================================	
	public TblPegawai getById(String nik){
		Criteria criteria =null;
		criteria = session.createCriteria(TblPegawai.class);
                    if (nik.length()>0){criteria.add(Restrictions.eq("nik", nik)); 	}

		return (TblPegawai)  criteria.uniqueResult();//session.get(TblPegawai.class, id);
	}
	
	public List<TblPegawai> getAll(){
		return (List<TblPegawai>) session.createCriteria(TblPegawai.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblPegawai.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblPegawai> getAll(int start, int rowcount ){
		return (List<TblPegawai>) session.createCriteria(TblPegawai.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Nik,String Nama){
		Criteria criteria =null;
		criteria = session.createCriteria(TblPegawai.class);
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
                    if (Nama.length()>0){criteria.add(Restrictions.eq("nama", Nama)); 	}
		
		return criteria;
	}

	public List<TblPegawai> getBy(String Nik,String Nama ,int start, int rowcount ){
		Criteria criteria =getCriteria(Nik,Nama);
		return (List<TblPegawai>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Nik,String Nama, int start, int rowcount  ){
		Criteria criteria =getCriteria(Nik,Nama);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Nik,String Nama ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Nik,Nama,  start,rowcount);//total jumlah row
		List<TblPegawai> l = getBy(Nik,Nama, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
