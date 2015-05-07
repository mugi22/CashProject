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

import com.id.kas.pojo.TblSeq;

//import com.id.kas.pojo.dacen.TblSeq;

public class TblSeqDAO {
	private Session session;
	
	public TblSeqDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblSeq tblseq){
		session.save(tblseq);
	}
		
	public void delete(TblSeq tblseq){
		session.delete(tblseq);
	}
	
	public void update(TblSeq tblseq){
		session.update(tblseq);
	}
//====================================================================	
	public TblSeq getById(String  seqName){
		Criteria criteria =null;
		criteria = session.createCriteria(TblSeq.class);
                    if (seqName.length()>0){criteria.add(Restrictions.eq("seqName", seqName)); 	}

		return (TblSeq)  criteria.uniqueResult();//session.get(TblSeq.class, id);
	}
	
	public List<TblSeq> getAll(){
		return (List<TblSeq>) session.createCriteria(TblSeq.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblSeq.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblSeq> getAll(int start, int rowcount ){
		return (List<TblSeq>) session.createCriteria(TblSeq.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(Date LastLogIn,String Keterangan,String SeqName,long SeqNum,BigDecimal Tarif){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblSeq.class);
                    try {
                    if (LastLogIn.after(formatter.parse("00-00-0000"))){criteria.add(Restrictions.eq("lastLogIn", LastLogIn)); 	}
                    } catch (ParseException e) {
                    e.printStackTrace();
                    }                    if (Keterangan.length()>0){criteria.add(Restrictions.eq("keterangan", Keterangan)); 	}
                    if (SeqName.length()>0){criteria.add(Restrictions.eq("seqName", SeqName)); 	}
                    if (SeqNum>0){criteria.add(Restrictions.eq("seqNum", SeqNum)); 	}
                    if (Tarif.doubleValue()>0){criteria.add(Restrictions.eq("y", Tarif)); 	}
		
		return criteria;
	}

	public List<TblSeq> getBy(Date LastLogIn,String Keterangan,String SeqName,long SeqNum,BigDecimal Tarif ,int start, int rowcount ){
		Criteria criteria =getCriteria(LastLogIn,Keterangan,SeqName,SeqNum,Tarif);
		return (List<TblSeq>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(Date LastLogIn,String Keterangan,String SeqName,long SeqNum,BigDecimal Tarif, int start, int rowcount  ){
		Criteria criteria =getCriteria(LastLogIn,Keterangan,SeqName,SeqNum,Tarif);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(Date LastLogIn,String Keterangan,String SeqName,long SeqNum,BigDecimal Tarif ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(LastLogIn,Keterangan,SeqName,SeqNum,Tarif,  start,rowcount);//total jumlah row
		List<TblSeq> l = getBy(LastLogIn,Keterangan,SeqName,SeqNum,Tarif, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}

//==============================REPORT====================================
/** Retrieve by kriteria tanpa batasan row */
	public List<TblSeq> getBy(Date LastLogIn,String Keterangan,String SeqName,long SeqNum,BigDecimal Tarif  ){
		Criteria criteria =getCriteria(LastLogIn,Keterangan,SeqName,SeqNum,Tarif);
		return (List<TblSeq>) criteria.list();
	}


}
