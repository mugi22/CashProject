package com.id.kas.DEVELOPMENT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.id.kas.pojo.TblPriviledge;

public class TblPriviledgeDAO {
	private Session session;
	
	public TblPriviledgeDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblPriviledge tblpriviledge){
		session.save(tblpriviledge);
	}
		
	public void delete(TblPriviledge tblpriviledge){
		session.delete(tblpriviledge);
	}
	
	public void update(TblPriviledge tblpriviledge){
		session.update(tblpriviledge);
	}
//====================================================================	
	public TblPriviledge getById(BigDecimal groupId,BigDecimal menuId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblPriviledge.class);
                    if (groupId.doubleValue()>0){criteria.add(Restrictions.eq("groupId", groupId)); 	}
                    if (menuId.doubleValue()>0){criteria.add(Restrictions.eq("menuId", menuId)); 	}

		return (TblPriviledge)  criteria.uniqueResult();//session.get(TblPriviledge.class, id);
	}
	
	public List<TblPriviledge> getAll(){
		return (List<TblPriviledge>) session.createCriteria(TblPriviledge.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblPriviledge.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblPriviledge> getAll(int start, int rowcount ){
		return (List<TblPriviledge>) session.createCriteria(TblPriviledge.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(BigDecimal groupId,BigDecimal  menuId,String isAdd,String isDelete,String isUpdate,String isView){
		Criteria criteria =null;
		criteria = session.createCriteria(TblPriviledge.class);
                    if (groupId.doubleValue()>0){criteria.add(Restrictions.eq("groupId", groupId)); 	}
                    if (menuId.doubleValue()>0){criteria.add(Restrictions.eq("menuId", menuId)); 	}
                    if (isAdd.length()>0){criteria.add(Restrictions.eq("isAdd", isAdd)); 	}
                    if (isDelete.length()>0){criteria.add(Restrictions.eq("isDelete", isDelete)); 	}
                    if (isUpdate.length()>0){criteria.add(Restrictions.eq("isUpdate", isUpdate)); 	}
                    if (isView.length()>0){criteria.add(Restrictions.eq("isView", isView)); 	}
		
		return criteria;
	}

	public List<TblPriviledge> getBy(BigDecimal groupId,BigDecimal menuId,String isAdd,String isDelete,String isUpdate,String isView ,int start, int rowcount ){
		Criteria criteria =getCriteria(groupId,menuId,isAdd,isDelete,isUpdate,isView);
		return (List<TblPriviledge>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(BigDecimal groupId,BigDecimal menuId,String isAdd,String isDelete,String isUpdate,String isView, int start, int rowcount  ){
		Criteria criteria =getCriteria(groupId,menuId,isAdd,isDelete,isUpdate,isView);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(BigDecimal groupId,BigDecimal menuId,String isAdd,String isDelete,String isUpdate,String isView ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(groupId,menuId,isAdd,isDelete,isUpdate,isView,  start,rowcount);//total jumlah row
		List<TblPriviledge> l = getBy(groupId,menuId,isAdd,isDelete,isUpdate,isView, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}


	

}
