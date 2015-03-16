package com.id.kas.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblPriviledge;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;

public class PriviledgeCek {
	private String isAdd = "disable"; 
	private String isEdit = "disable"; 
	private String isDelete = "disable";
	private String isView = "disable";
	

	
	
	public String getIsAdd() {
		return isAdd;
	}



	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}



	public String getIsEdit() {
		return isEdit;
	}



	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}



	public String getIsDelete() {
		return isDelete;
	}



	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}



	public String getIsView() {
		return isView;
	}



	public void setIsView(String isView) {
		this.isView = isView;
	}



	//=============== cek priveledge============
	/**
	 * pengecekan priviledge
	 * @param userId
	 * @param menuId
	 * @param isAdd
	 * @param isEdit
	 * @param isDelete
	 */
	public void cekPriveldge(String userId, String menuId){//,String isAdd, String isEdit, String isDelete, String isView){
		Session sess = null;
		try {
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria critGroup = sess.createCriteria(TblUserGroup.class);
			critGroup.add(Restrictions.eq("id.userId", userId));
			critGroup.setProjection(Projections.projectionList().add(
					Projections.property("id.groupId")));// "id.groupId"
			List<BigDecimal> l = critGroup.list();
			// for(BigDecimal x :l){System.out.println(x);}

			Criteria c = sess.createCriteria(TblPriviledge.class);
			c.add(Restrictions.in("id.groupId", l));
			c.add(Restrictions.eq("id.menuId", new BigDecimal(menuId)));
			c.add(Restrictions.eq("isAdd", '1'));
			List k = c.list();
//			System.out.println("user " + userId + " menu id " + menuId);
			if (k.size() > 0) {
				this.isAdd = "enable";
			}

			Criteria d = sess.createCriteria(TblPriviledge.class);
			d.add(Restrictions.in("id.groupId", l));
			d.add(Restrictions.eq("id.menuId", new BigDecimal(menuId)));
			d.add(Restrictions.eq("isUpdate", '1'));
			List k2 = d.list();
			if (k2.size() > 0) {
				this.isEdit = "enable";
			}
			//
			Criteria e = sess.createCriteria(TblPriviledge.class);
			e.add(Restrictions.in("id.groupId", l));
			e.add(Restrictions.eq("id.menuId", new BigDecimal(menuId)));
			e.add(Restrictions.eq("isDelete", '1'));
			List k3 = e.list();
			if (k3.size() > 0) {
				this.isDelete = "enable";
			}
			
			Criteria f = sess.createCriteria(TblPriviledge.class);
			f.add(Restrictions.in("id.groupId", l));
			f.add(Restrictions.eq("id.menuId", new BigDecimal(menuId)));
			f.add(Restrictions.eq("isView", '1'));
			List k4 = f.list();
			if (k4.size() > 0) {
				this.isView = "enable";
			}
			
			sess.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (sess != null) {
				sess.close();
			}
		}
		 
	}
	
	
	
	//=========================
	
//	 public String doGet(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
//		 String param =reg.getParameter("param");
//         String ses = (String) session.getAttribute("session");
//         TblUser user = (TblUser) session.getAttribute("user");
//         
//         cekPriveldge(user.getUserId(), param,isAdd,  isEdit, isDelete,isView);
//         model.put("session", ses);
//        
//         model.put("btnAdd",isAdd);// "disable");
//         model.put("btnEdit",isEdit );//"disable");
//         model.put("btnDelete",isDelete);//"disable");   
//         model.put("btnShow",isView);//"disable");
//         return null;//getView();
//     }
	
}
