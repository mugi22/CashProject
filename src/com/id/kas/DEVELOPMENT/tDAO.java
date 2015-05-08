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
	public t getById(long  jurnalId,long  idJurnalTransaksi){
		Criteria criteria =null;
		criteria = session.createCriteria(t.class);
                    if (jurnalId>0){criteria.add(Restrictions.eq("jurnalId", jurnalId)); 	}
                    if (idJurnalTransaksi>0){criteria.add(Restrictions.eq("idJurnalTransaksi", idJurnalTransaksi)); 	}

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
	public Criteria getCriteria(String NoCoa,long JurnalId,Date TglPosting,Date TglTransaksi,long IdJurnalTransaksi){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(t.class);
                    if (NoCoa.length()>0){criteria.add(Restrictions.eq("noCoa", NoCoa)); 	}
                    if (JurnalId>0){criteria.add(Restrictions.eq("jurnalId", JurnalId)); 	}
                    try {
                    if (TglPosting.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("tglPosting", TglPosting)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }                    try {
                    if (TglTransaksi.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("tglTransaksi", TglTransaksi)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }                    if (IdJurnalTransaksi>0){criteria.add(Restrictions.eq("idJurnalTransaksi", IdJurnalTransaksi)); 	}
		
		return criteria;
	}

	public List<t> getBy(String NoCoa,long JurnalId,Date TglPosting,Date TglTransaksi,long IdJurnalTransaksi ,int start, int rowcount ){
		Criteria criteria =getCriteria(NoCoa,JurnalId,TglPosting,TglTransaksi,IdJurnalTransaksi);
		return (List<t>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String NoCoa,long JurnalId,Date TglPosting,Date TglTransaksi,long IdJurnalTransaksi, int start, int rowcount  ){
		Criteria criteria =getCriteria(NoCoa,JurnalId,TglPosting,TglTransaksi,IdJurnalTransaksi);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String NoCoa,long JurnalId,Date TglPosting,Date TglTransaksi,long IdJurnalTransaksi ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(NoCoa,JurnalId,TglPosting,TglTransaksi,IdJurnalTransaksi,  start,rowcount);//total jumlah row
		List<t> l = getBy(NoCoa,JurnalId,TglPosting,TglTransaksi,IdJurnalTransaksi, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<t> getBy(String NoCoa,long JurnalId,Date TglPosting,Date TglTransaksi,long IdJurnalTransaksi  ){
		Criteria criteria =getCriteria(NoCoa,JurnalId,TglPosting,TglTransaksi,IdJurnalTransaksi);
		return (List<t>) criteria.list();
	}


}
