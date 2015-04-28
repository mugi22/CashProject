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

import com.id.kas.pojo.TblRekeningIaMaster;

public class TblRekeningIaMasterDAO {
	private Session session;
	
	public TblRekeningIaMasterDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblRekeningIaMaster tblrekeningiamaster){
		session.save(tblrekeningiamaster);
	}
		
	public void delete(TblRekeningIaMaster tblrekeningiamaster){
		session.delete(tblrekeningiamaster);
	}
	
	public void update(TblRekeningIaMaster tblrekeningiamaster){
		session.update(tblrekeningiamaster);
	}
//====================================================================	
	public TblRekeningIaMaster getById(String  noCoa){
		Criteria criteria =null;
		criteria = session.createCriteria(TblRekeningIaMaster.class);
                    if (noCoa.length()>0){criteria.add(Restrictions.eq("noCoa", noCoa)); 	}

		return (TblRekeningIaMaster)  criteria.uniqueResult();//session.get(TblRekeningIaMaster.class, id);
	}
	
	public List<TblRekeningIaMaster> getAll(){
		return (List<TblRekeningIaMaster>) session.createCriteria(TblRekeningIaMaster.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblRekeningIaMaster.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblRekeningIaMaster> getAll(int start, int rowcount ){
		return (List<TblRekeningIaMaster>) session.createCriteria(TblRekeningIaMaster.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Description,String NoCoa,String NoRek,String SaldoNormal){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblRekeningIaMaster.class);
                    if (Description.length()>0){criteria.add(Restrictions.eq("description", Description)); 	}
                    if (NoCoa.length()>0){criteria.add(Restrictions.eq("noCoa", NoCoa)); 	}
                    if (NoRek.length()>0){criteria.add(Restrictions.eq("noRek", NoRek)); 	}
                    if (SaldoNormal.length()>0){criteria.add(Restrictions.eq("saldoNormal", SaldoNormal)); 	}
		
		return criteria;
	}

	public List<TblRekeningIaMaster> getBy(String Description,String NoCoa,String NoRek,String SaldoNormal ,int start, int rowcount ){
		Criteria criteria =getCriteria(Description,NoCoa,NoRek,SaldoNormal);
		return (List<TblRekeningIaMaster>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Description,String NoCoa,String NoRek,String SaldoNormal, int start, int rowcount  ){
		Criteria criteria =getCriteria(Description,NoCoa,NoRek,SaldoNormal);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Description,String NoCoa,String NoRek,String SaldoNormal ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Description,NoCoa,NoRek,SaldoNormal,  start,rowcount);//total jumlah row
		List<TblRekeningIaMaster> l = getBy(Description,NoCoa,NoRek,SaldoNormal, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
