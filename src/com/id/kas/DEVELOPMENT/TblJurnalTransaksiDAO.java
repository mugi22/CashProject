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

import com.id.kas.pojo.TblJurnalTransaksi;

public class TblJurnalTransaksiDAO {
	private Session session;
	
	public TblJurnalTransaksiDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblJurnalTransaksi tbljurnaltransaksi){
		session.save(tbljurnaltransaksi);
	}
		
	public void delete(TblJurnalTransaksi tbljurnaltransaksi){
		session.delete(tbljurnaltransaksi);
	}
	
	public void update(TblJurnalTransaksi tbljurnaltransaksi){
		session.update(tbljurnaltransaksi);
	}
//====================================================================	
	public TblJurnalTransaksi getById(long  idJurnalTransaksi,long  jurnalId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblJurnalTransaksi.class);
                    if (idJurnalTransaksi>0){criteria.add(Restrictions.eq("idJurnalTransaksi", idJurnalTransaksi)); 	}
                    if (jurnalId>0){criteria.add(Restrictions.eq("jurnalId", jurnalId)); 	}

		return (TblJurnalTransaksi)  criteria.uniqueResult();//session.get(TblJurnalTransaksi.class, id);
	}
	
	public List<TblJurnalTransaksi> getAll(){
		return (List<TblJurnalTransaksi>) session.createCriteria(TblJurnalTransaksi.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblJurnalTransaksi.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblJurnalTransaksi> getAll(int start, int rowcount ){
		return (List<TblJurnalTransaksi>) session.createCriteria(TblJurnalTransaksi.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(long IdJurnalTransaksi,String Norek,long JurnalId,Date TglPosting,Date TglTransaksi){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblJurnalTransaksi.class);
                    if (IdJurnalTransaksi>0){criteria.add(Restrictions.eq("idJurnalTransaksi", IdJurnalTransaksi)); 	}
                    if (Norek.length()>0){criteria.add(Restrictions.eq("norek", Norek)); 	}
                    if (JurnalId>0){criteria.add(Restrictions.eq("jurnalId", JurnalId)); 	}
                    try {
                    if (TglPosting.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("tglPosting", TglPosting)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }                    try {
                    if (TglTransaksi.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("tglTransaksi", TglTransaksi)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }		
		return criteria;
	}

	public List<TblJurnalTransaksi> getBy(long IdJurnalTransaksi,String Norek,long JurnalId,Date TglPosting,Date TglTransaksi ,int start, int rowcount ){
		Criteria criteria =getCriteria(IdJurnalTransaksi,Norek,JurnalId,TglPosting,TglTransaksi);
		return (List<TblJurnalTransaksi>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(long IdJurnalTransaksi,String Norek,long JurnalId,Date TglPosting,Date TglTransaksi, int start, int rowcount  ){
		Criteria criteria =getCriteria(IdJurnalTransaksi,Norek,JurnalId,TglPosting,TglTransaksi);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(long IdJurnalTransaksi,String Norek,long JurnalId,Date TglPosting,Date TglTransaksi ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(IdJurnalTransaksi,Norek,JurnalId,TglPosting,TglTransaksi,  start,rowcount);//total jumlah row
		List<TblJurnalTransaksi> l = getBy(IdJurnalTransaksi,Norek,JurnalId,TglPosting,TglTransaksi, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<TblJurnalTransaksi> getBy(long IdJurnalTransaksi,String Norek,long JurnalId,Date TglPosting,Date TglTransaksi  ){
		Criteria criteria =getCriteria(IdJurnalTransaksi,Norek,JurnalId,TglPosting,TglTransaksi);
		return (List<TblJurnalTransaksi>) criteria.list();
	}


}
