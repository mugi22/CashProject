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

import com.id.kas.pojo.TblRekeningIA;

public class TblRekeningIADAO {
	private Session session;
	
	public TblRekeningIADAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblRekeningIA tblrekeningia){
		session.save(tblrekeningia);
	}
		
	public void delete(TblRekeningIA tblrekeningia){
		session.delete(tblrekeningia);
	}
	
	public void update(TblRekeningIA tblrekeningia){
		session.update(tblrekeningia);
	}
//====================================================================	
	public TblRekeningIA getById(String  norek){
		Criteria criteria =null;
		criteria = session.createCriteria(TblRekeningIA.class);
                    if (norek.length()>0){criteria.add(Restrictions.eq("norek", norek)); 	}

		return (TblRekeningIA)  criteria.uniqueResult();//session.get(TblRekeningIA.class, id);
	}
	
	public List<TblRekeningIA> getAll(){
		return (List<TblRekeningIA>) session.createCriteria(TblRekeningIA.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblRekeningIA.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblRekeningIA> getAll(int start, int rowcount ){
		return (List<TblRekeningIA>) session.createCriteria(TblRekeningIA.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Description,String Norek,String BranchCode,String NorekIAMaster){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblRekeningIA.class);
                    if (Description.length()>0){criteria.add(Restrictions.eq("description", Description)); 	}
                    if (Norek.length()>0){criteria.add(Restrictions.eq("norek", Norek)); 	}
                    if (BranchCode.length()>0){criteria.add(Restrictions.eq("branchCode", BranchCode)); 	}
                    if (NorekIAMaster.length()>0){criteria.add(Restrictions.eq("norekIAMaster", NorekIAMaster)); 	}
		
		return criteria;
	}

	public List<TblRekeningIA> getBy(String Description,String Norek,String BranchCode,String NorekIAMaster ,int start, int rowcount ){
		Criteria criteria =getCriteria(Description,Norek,BranchCode,NorekIAMaster);
		return (List<TblRekeningIA>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Description,String Norek,String BranchCode,String NorekIAMaster, int start, int rowcount  ){
		Criteria criteria =getCriteria(Description,Norek,BranchCode,NorekIAMaster);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Description,String Norek,String BranchCode,String NorekIAMaster ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Description,Norek,BranchCode,NorekIAMaster,  start,rowcount);//total jumlah row
		List<TblRekeningIA> l = getBy(Description,Norek,BranchCode,NorekIAMaster, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
