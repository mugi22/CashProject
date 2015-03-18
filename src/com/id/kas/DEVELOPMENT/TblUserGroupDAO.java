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

import com.id.kas.pojo.TblUserGroup;

public class TblUserGroupDAO {
	private Session session;
	
	public TblUserGroupDAO(Session session){
		this.session = session;
	}
	
	
	public void insert(TblUserGroup tblusergroup){
		session.save(tblusergroup);
	}
		
	public void delete(TblUserGroup tblusergroup){
		session.delete(tblusergroup);
	}
	
	public void update(TblUserGroup tblusergroup){
		session.update(tblusergroup);
	}
//====================================================================	
	public TblUserGroup getById(BigDecimal groupId,String userId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblUserGroup.class);
                    if (groupId.doubleValue()>0){criteria.add(Restrictions.eq("groupId", groupId)); 	}
                    if (userId.length()>0){criteria.add(Restrictions.eq("userId", userId)); 	}

		return (TblUserGroup)  criteria.uniqueResult();//session.get(TblUserGroup.class, id);
	}
	
	public List<TblUserGroup> getAll(){
		return (List<TblUserGroup>) session.createCriteria(TblUserGroup.class).list();
	}
	
	
	
	public Long getAllCount(){
		return (Long) session.createCriteria(TblUserGroup.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<TblUserGroup> getAll(int start, int rowcount ){
		return (List<TblUserGroup>) session.createCriteria(TblUserGroup.class).setFirstResult(start).setMaxResults(rowcount).list();
	}

/*//SESUAIKAN DENGAN KRITERIA*/	
	public Criteria getCriteria(BigDecimal GroupId,String UserId){
		Criteria criteria =null;
		criteria = session.createCriteria(TblUserGroup.class);
                    if (GroupId.doubleValue()>0){criteria.add(Restrictions.eq("groupId", GroupId)); 	}
                    if (UserId.length()>0){criteria.add(Restrictions.eq("userId", UserId)); 	}
		
		return criteria;
	}

	public List<TblUserGroup> getBy(BigDecimal GroupId,String UserId ,int start, int rowcount ){
		Criteria criteria =getCriteria(GroupId,UserId);
		return (List<TblUserGroup>) criteria.setFirstResult(start).setMaxResults(rowcount).list();
	}
	
	public Long getByCount(BigDecimal GroupId,String UserId, int start, int rowcount  ){
		Criteria criteria =getCriteria(GroupId,UserId);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public Map<String,Object> getByPerPage(BigDecimal GroupId,String UserId ,int start, int rowcount ){
		Map map = new HashMap<String, Object>();		
		long rowCount =  getByCount(GroupId,UserId,  start,rowcount);//total jumlah row
		List<TblUserGroup> l = getBy(GroupId,UserId, start,rowcount);//data result nya
		map.put("total", rowCount);
		map.put("rows", l);
		return map;
	}




}
