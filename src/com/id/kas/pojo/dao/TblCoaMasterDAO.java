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

import com.id.kas.pojo.TblCoaMaster;

public class TblCoaMasterDAO {
	private Session session;
	
	public TblCoaMasterDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblCoaMaster tblcoamaster){
		session.save(tblcoamaster);
	}
		
	public void delete(TblCoaMaster tblcoamaster){
		session.delete(tblcoamaster);
	}
	
	public void update(TblCoaMaster tblcoamaster){
		session.update(tblcoamaster);
	}
//====================================================================	
	public TblCoaMaster getById(String  noCoa){
		Criteria criteria =null;
		criteria = session.createCriteria(TblCoaMaster.class);
                    if (noCoa.length()>0){criteria.add(Restrictions.eq("noCoa", noCoa)); 	}

		return (TblCoaMaster)  criteria.uniqueResult();//session.get(TblCoaMaster.class, id);
	}
	
	public List<TblCoaMaster> getAll(){
		return (List<TblCoaMaster>) session.createCriteria(TblCoaMaster.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblCoaMaster.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblCoaMaster> getAll(int start, int rowcount ){
		return (List<TblCoaMaster>) session.createCriteria(TblCoaMaster.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(String Description,String Groups,String NoCoa,String ParentCoa,int Lvl){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Criteria criteria =null;
		criteria = session.createCriteria(TblCoaMaster.class);
                    if (Description.length()>0){criteria.add(Restrictions.eq("description", Description)); 	}
                    if (Groups.length()>0){criteria.add(Restrictions.eq("groups", Groups)); 	}
                    if (NoCoa.length()>0){criteria.add(Restrictions.eq("noCoa", NoCoa)); 	}
                    if (ParentCoa.length()>0){criteria.add(Restrictions.eq("parentCoa", ParentCoa)); 	}
                    if (Lvl>0){criteria.add(Restrictions.eq("lvl", Lvl)); 	}
		
		return criteria;
	}

	public List<TblCoaMaster> getBy(String Description,String Groups,String NoCoa,String ParentCoa,int Lvl ,int start, int rowcount ){
		Criteria criteria =getCriteria(Description,Groups,NoCoa,ParentCoa,Lvl);
		return (List<TblCoaMaster>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(String Description,String Groups,String NoCoa,String ParentCoa,int Lvl, int start, int rowcount  ){
		Criteria criteria =getCriteria(Description,Groups,NoCoa,ParentCoa,Lvl);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(String Description,String Groups,String NoCoa,String ParentCoa,int Lvl ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(Description,Groups,NoCoa,ParentCoa,Lvl,  start,rowcount);//total jumlah row
		List<TblCoaMaster> l = getBy(Description,Groups,NoCoa,ParentCoa,Lvl, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
