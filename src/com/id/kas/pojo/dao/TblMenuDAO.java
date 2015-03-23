package com.id.kas.pojo.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblMenu;

public class TblMenuDAO {
	private Session session;
	
	public TblMenuDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblMenu tblmenu){
		session.save(tblmenu);
	}
		
	public void delete(TblMenu tblmenu){
		session.delete(tblmenu);
	}
	
	public void update(TblMenu tblmenu){
		session.update(tblmenu);
	}
//====================================================================	
	public TblMenu getById(BigDecimal menuId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblMenu.class);
                    if (menuId.doubleValue()>0){criteria.add(Restrictions.eq("menuId", menuId)); 	}

		return (TblMenu)  criteria.uniqueResult();//session.get(TblMenu.class, id);
	}
	
	public List<TblMenu> getAll(){
		return (List<TblMenu>) session.createCriteria(TblMenu.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblMenu.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblMenu> getAll(int start, int rowcount ){
		return (List<TblMenu>) session.createCriteria(TblMenu.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(BigDecimal menuId,String menuName,BigDecimal parentId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblMenu.class);
                    if (menuId.doubleValue()>0){criteria.add(Restrictions.eq("menuId", menuId)); 	}
                    if (menuName.length()>0){criteria.add(Restrictions.eq("menuName", menuName)); 	}
                    if (parentId.doubleValue()>0){criteria.add(Restrictions.eq("parentId", parentId)); 	}
		
		return criteria;
	}

	public List<TblMenu> getBy(BigDecimal menuId,String menuName,BigDecimal parentId ,int start, int rowcount ){
		Criteria criteria =getCriteria(menuId,menuName,parentId);
		return (List<TblMenu>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(BigDecimal menuId,String menuName,BigDecimal parentId, int start, int rowcount  ){
		Criteria criteria =getCriteria(menuId,menuName,parentId);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(BigDecimal menuId,String menuName,BigDecimal parentId ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(menuId,menuName,parentId,  start,rowcount);//total jumlah row
		List<TblMenu> l = getBy(menuId,menuName,parentId, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
