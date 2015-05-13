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

public class TblCobaDAO {
	private Session session;
	
	public TblCobaDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblCoba tblcoba){
		session.save(tblcoba);
	}
		
	public void delete(TblCoba tblcoba){
		session.delete(tblcoba);
	}
	
	public void update(TblCoba tblcoba){
		session.update(tblcoba);
	}
//====================================================================	
	public TblCoba getById(String  nik){
		Criteria criteria =null;
		criteria = session.createCriteria(TblCoba.class);
                    if (nik.length()>0){criteria.add(Restrictions.eq("nik", nik)); 	}

		return (TblCoba)  criteria.uniqueResult();//session.get(TblCoba.class, id);
	}
	
	public List<TblCoba> getAll(){
		return (List<TblCoba>) session.createCriteria(TblCoba.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblCoba.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblCoba> getAll(int start, int rowcount ){
		return (List<TblCoba>) session.createCriteria(TblCoba.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(BigDecimal Tarif,String Nik,String Nama,Date TglLahir){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblCoba.class);
                    if (Tarif.doubleValue()>0){criteria.add(Restrictions.eq("y", Tarif)); 	}
                    if (Nik.length()>0){criteria.add(Restrictions.eq("nik", Nik)); 	}
                    if (Nama.length()>0){criteria.add(Restrictions.eq("nama", Nama)); 	}
                    try {
                    if (TglLahir.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("tglLahir", TglLahir)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }		
		return criteria;
	}

	public List<TblCoba> getBy(BigDecimal Tarif,String Nik,String Nama,Date TglLahir ,int start, int rowcount ){
		Criteria criteria =getCriteria(Tarif,Nik,Nama,TglLahir);
		return (List<TblCoba>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(BigDecimal Tarif,String Nik,String Nama,Date TglLahir, int start, int rowcount  ){
		Criteria criteria =getCriteria(Tarif,Nik,Nama,TglLahir);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(BigDecimal Tarif,String Nik,String Nama,Date TglLahir ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Tarif,Nik,Nama,TglLahir,  start,rowcount);//total jumlah row
		List<TblCoba> l = getBy(Tarif,Nik,Nama,TglLahir, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<TblCoba> getBy(BigDecimal Tarif,String Nik,String Nama,Date TglLahir  ){
		Criteria criteria =getCriteria(Tarif,Nik,Nama,TglLahir);
		return (List<TblCoba>) criteria.list();
	}


}
