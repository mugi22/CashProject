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

import com.id.kas.pojo.TblJurnal;

//import com.id.kas.pojo.dacen.TblJurnal;

public class TblJurnalDAO {
	private Session session;
	
	public TblJurnalDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblJurnal tbljurnal){
		session.save(tbljurnal);
	}
		
	public void delete(TblJurnal tbljurnal){
		session.delete(tbljurnal);
	}
	
	public void update(TblJurnal tbljurnal){
		session.update(tbljurnal);
	}
//====================================================================	
	public TblJurnal getById(long  jurnalId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblJurnal.class);
                    if (jurnalId>0){criteria.add(Restrictions.eq("jurnalId", jurnalId)); 	}

		return (TblJurnal)  criteria.uniqueResult();//session.get(TblJurnal.class, id);
	}
	
	public List<TblJurnal> getAll(){
		return (List<TblJurnal>) session.createCriteria(TblJurnal.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblJurnal.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblJurnal> getAll(int start, int rowcount ){
		return (List<TblJurnal>) session.createCriteria(TblJurnal.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String KodeTransaksi,Date TglTransaksi,long JurnalId,String BranchCode){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblJurnal.class);
                    if (KodeTransaksi.length()>0){criteria.add(Restrictions.eq("kodeTransaksi", KodeTransaksi)); 	}
                    try {
                    if (TglTransaksi.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("tglTransaksi", TglTransaksi)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }                    if (JurnalId>0){criteria.add(Restrictions.eq("jurnalId", JurnalId)); 	}
                    if (BranchCode.length()>0){criteria.add(Restrictions.eq("branchCode", BranchCode)); 	}
		
		return criteria;
	}

	public List<TblJurnal> getBy(String KodeTransaksi,Date TglTransaksi,long JurnalId,String BranchCode ,int start, int rowcount ){
		Criteria criteria =getCriteria(KodeTransaksi,TglTransaksi,JurnalId,BranchCode);
		return (List<TblJurnal>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String KodeTransaksi,Date TglTransaksi,long JurnalId,String BranchCode, int start, int rowcount  ){
		Criteria criteria =getCriteria(KodeTransaksi,TglTransaksi,JurnalId,BranchCode);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String KodeTransaksi,Date TglTransaksi,long JurnalId,String BranchCode ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(KodeTransaksi,TglTransaksi,JurnalId,BranchCode,  start,rowcount);//total jumlah row
		List<TblJurnal> l = getBy(KodeTransaksi,TglTransaksi,JurnalId,BranchCode, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<TblJurnal> getBy(String KodeTransaksi,Date TglTransaksi,long JurnalId,String BranchCode  ){
		Criteria criteria =getCriteria(KodeTransaksi,TglTransaksi,JurnalId,BranchCode);
		return (List<TblJurnal>) criteria.list();
	}


}
