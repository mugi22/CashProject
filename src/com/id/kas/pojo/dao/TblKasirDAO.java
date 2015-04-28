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

import com.id.kas.pojo.TblKasir;

public class TblKasirDAO {
	private Session session;
	
	public TblKasirDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblKasir tblkasir){
		session.save(tblkasir);
	}
		
	public void delete(TblKasir tblkasir){
		session.delete(tblkasir);
	}
	
	public void update(TblKasir tblkasir){
		session.update(tblkasir);
	}
//====================================================================	
	public TblKasir getById(String  userId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblKasir.class);
                    if (userId.length()>0){criteria.add(Restrictions.eq("userId", userId)); 	}

		return (TblKasir)  criteria.uniqueResult();//session.get(TblKasir.class, id);
	}
	
	public List<TblKasir> getAll(){
		return (List<TblKasir>) session.createCriteria(TblKasir.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblKasir.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblKasir> getAll(int start, int rowcount ){
		return (List<TblKasir>) session.createCriteria(TblKasir.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Status,String BranchCode,String UserId,String Norek){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblKasir.class);
                    if (Status.length()>0){criteria.add(Restrictions.eq("status", Status)); 	}
                    if (BranchCode.length()>0){criteria.add(Restrictions.eq("branchCode", BranchCode)); 	}
                    if (UserId.length()>0){criteria.add(Restrictions.eq("userId", UserId)); 	}
                    if (Norek.length()>0){criteria.add(Restrictions.eq("norek", Norek)); 	}
		
		return criteria;
	}

	public List<TblKasir> getBy(String Status,String BranchCode,String UserId,String Norek ,int start, int rowcount ){
		Criteria criteria =getCriteria(Status,BranchCode,UserId,Norek);
		return (List<TblKasir>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Status,String BranchCode,String UserId,String Norek, int start, int rowcount  ){
		Criteria criteria =getCriteria(Status,BranchCode,UserId,Norek);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Status,String BranchCode,String UserId,String Norek ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Status,BranchCode,UserId,Norek,  start,rowcount);//total jumlah row
		List<TblKasir> l = getBy(Status,BranchCode,UserId,Norek, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
