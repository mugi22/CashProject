/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.kas.util;

import com.id.kas.db.HibernateUtil;
import com.id.kas.pojo.TblMenu;
import com.id.kas.pojo.TblPriviledge;
import com.id.kas.pojo.TblUser;
import com.id.kas.pojo.TblUserGroup;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mugiarto
 */
public class SusunTree {

    private String jrk = "";
    private Set<BigDecimal> l;
    public void susunTree(Session session, BigInteger parentId, String jarak,Set<BigDecimal> menuIdSet) {
        /*menu by user */
        
        
        String j =jarak; 
	jarak = j + "   ";
        Criteria criteria = session.createCriteria(TblMenu.class);
        criteria.add(Restrictions.eq("parentId", new BigDecimal(parentId)));
        criteria.add(Restrictions.in("menuId", l));
        criteria.addOrder(Order.asc("noUrut"));
        List<TblMenu> list = criteria.list();
        PriviledgeCek pv = new PriviledgeCek();
        for (TblMenu tblmenu : list) {   
//            System.out.println(jarak + tblmenu.getMenuId()+"  "+tblmenu.getMenuName());
            //juka punya child
            if(tblmenu.getHaveChild() =='0'){
            	//tambahkan parameter menu_id
//            	prililedge
            	pv.cekPriveldge(user, (tblmenu.getMenuId()+""));//, isAdd, isEdit, isDelete, isView);
            	String priv = "&isAdd="+pv.getIsAdd()+"&isEdit="+pv.getIsEdit()+"&isDelete="+pv.getIsDelete()+"&isView="+pv.getIsView();
                sb.append(jarak+"<li "+"onclick="+'"'+"window.open("+"'"+tblmenu.getMenuPage()+"?param="+tblmenu.getMenuId()+priv+"'"+","+"'"+"MAIN"+"'"+")"+'"'+">");
                sb.append(tblmenu.getMenuName());
                SusunTree susunTree = new SusunTree();
                susunTree(session,BigInteger.valueOf(tblmenu.getMenuId().longValue()),jarak,l);
            }else{
                sb.append(jarak+"<li>");
                sb.append(tblmenu.getMenuName());
                sb.append("\r\n"+jarak+"<ul>\r\n");
                SusunTree susunTree = new SusunTree();
                susunTree(session,BigInteger.valueOf(tblmenu.getMenuId().longValue()),jarak,l);           
                sb.append(jarak+"</ul>\r\n");
            }
            sb.append("</li>\r\n");
        }  
    }
    
    
      public Set<BigDecimal> listMenuByUser(Session session, String userId) {
        
        /* Ambil gropu by user */
        Criteria critGroup = session.createCriteria(TblUserGroup.class);
        critGroup.add(Restrictions.eq("id.userId", userId));
        critGroup.setProjection(Projections.projectionList().add(Projections.property("id.groupId")));//"id.groupId"
        List<BigDecimal> l =critGroup.list();
	    if(l.size()>0){    
	//        for(BigDecimal x :l){System.out.println(x);        }
	        /*Menu berdasarkan Priviledge */
	        Criteria c = session.createCriteria(TblPriviledge.class);
	        c.add(Restrictions.in("id.groupId", l));
	        c.setProjection(Projections.projectionList().add(Projections.property("id.menuId")));
	        Set<BigDecimal> lMenuId = new HashSet();
	        List<BigDecimal> k = c.list();
	              for(BigDecimal x :k){
	                  lMenuId.add(x);//new BigDecimal(x));
	//            System.out.println(x.getid().getMenuId());
	//                  System.out.println(k);
	        }
	        return lMenuId;
	    }else{
	    	return null;
	    }

    }
    
    
    private String user;
    
    public String susunMenuByUser(String userId){
    	this.user = userId;
    	String menu = " onclick="+'"'+"window.open("+"'"+"utamaMain.htm"+"'"+","+"'"+"MAIN"+"'"+")"+'"';
        sb = new StringBuffer();//id="treemenu1" class="treeview"
        sb.append("<ul"+" id="+'"'+"treemenu1"+'"'+menu+" class="+'"'+"treeview"+'"'+" >Menu"+"\n\r"); //STANDAR
//        sb.append("<ul"+" id="+'"'+"tt"+'"'+" class="+'"'+"easyui-tree"+'"'+" >Menu"+"\n\r"); //pake punya jeasy
        String jrk="";
        Session ses;
        ses = HibernateUtil.getSessionFactory().openSession();
        SusunTree susunTree = new SusunTree();
        l = listMenuByUser(ses, userId);
        if(l==null){
        		System.out.println("------   tidak punya group dan priviledge");
        		return "";
        }else{
        
//        susunTree.menuByUser(ses,"P81035");
        susunTree(ses, BigInteger.valueOf(0), jrk, l);
        ses.close();
        sb.append("</ul>"+"\n\r");
//        System.out.println("Selesai");
        String x = sb.toString();
//        System.out.println(x);
        return x;}
    }
private static StringBuffer sb ;
    public static void main(String[] args) {
        SusunTree susunTree = new SusunTree();
        susunTree.susunMenuByUser("P1");
//        System.out.println(sb.toString());
    }
}
