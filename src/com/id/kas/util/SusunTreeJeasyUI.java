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
public class SusunTreeJeasyUI {

    private String jrk = "";
    private Set<BigDecimal> l;
    private String sKey;
    private RandomString rs;
    private JCrypto crypto;
    public void susunTree(Session session, BigInteger parentId, String jarak,Set<BigDecimal> menuIdSet) {
        /*menu by user */
       
        
        String j =jarak; 
	jarak = j + "   ";
        Criteria criteria = session.createCriteria(TblMenu.class);
        criteria.add(Restrictions.eq("parentId", new BigDecimal(parentId)));
        criteria.add(Restrictions.in("menuId", l));
        criteria.addOrder(Order.asc("noUrut"));
        List<TblMenu> list = criteria.list();
        for (TblMenu tblmenu : list) {   
            //juka punya child
        	PriviledgeCek pv = new PriviledgeCek();
        	pv.cekPriveldge(user, (tblmenu.getMenuId()+""));//, isAdd, isEdit, isDelete, isView);
        	String Enc =crypto.encrypt(tblmenu.getMenuId()+"");
        	//String x ="isAdd="+crypto.encrypt(pv.getIsAdd())+"isEdit="+crypto.encrypt(pv.getIsEdit())+"isDelete="+crypto.encrypt(pv.getIsDelete())+"isView="+crypto.encrypt(pv.getIsView());
        	String x ="&isAdd="+(pv.getIsAdd())+"&isEdit="+(pv.getIsEdit())+"&isDelete="+(pv.getIsDelete())+"&isView="+(pv.getIsView());

        	String priv = crypto.encrypt(x);
            String dec ="";// crypto.decrypt(priv);
           

            if(tblmenu.getHaveChild() =='0'){
            	//tambahkan parameter menu_id//            	prililedge
            	sb.append(jarak+"<li"+" id='"+tblmenu.getMenuPage()+"?param="+priv+"&"+"param2="+this.sKey+"'"+">");
                x=null;
                sb.append(tblmenu.getMenuName());
                SusunTreeJeasyUI susunTree = new SusunTreeJeasyUI();
                susunTree(session,BigInteger.valueOf(tblmenu.getMenuId().longValue()),jarak,l);
            }else{
                sb.append(jarak+"<li "+" id='"+"utamaMain.htm"+"?param="+priv+"&"+"param2="+this.sKey+"'"+"><span>");
                sb.append(tblmenu.getMenuName()+"</span>");
                sb.append("\r\n"+jarak+"<ul"+" id='"+"utamaMain.htm"+"?param="+priv+"&"+"param2="+this.sKey+"'"+">\r\n");
                SusunTreeJeasyUI susunTree = new SusunTreeJeasyUI();
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
	        /*Menu berdasarkan Priviledge */
	        Criteria c = session.createCriteria(TblPriviledge.class);
	        c.add(Restrictions.in("id.groupId", l));
	        c.setProjection(Projections.projectionList().add(Projections.property("id.menuId")));
	        Set<BigDecimal> lMenuId = new HashSet();
	        List<BigDecimal> k = c.list();
	              for(BigDecimal x :k){
	                  lMenuId.add(x);//new BigDecimal(x));
	        }
	        return lMenuId;
	    }else{
	    	return null;
	    }

    }
    
    
    private String user;
    
    public String susunMenuByUser(String userId,String skey){
    	System.out.println("Skey 11111   "+sKey);
    	this.user = userId;
    	this.sKey = skey;
    	rs = new RandomString();
    	sKey = rs.randomString();
    	System.out.println("Skey    "+sKey);
    	crypto = new JCrypto(sKey);
    	String menu ="";// " onclick="+'"'+"window.open("+"'"+"utamaMain.htm"+"'"+","+"'"+"MAIN"+"'"+")"+'"';
        sb = new StringBuffer();//id="treemenu1" class="treeview"
//        sb.append("<ul"+" id="+'"'+"treemenu1"+'"'+menu+" class="+'"'+"treeview"+'"'+" >Menu"+"\n\r"); //STANDAR
        sb.append("<ul"+" class='easyui-tree' id='tt'"+" >"+"\n"); //pake punya jeasy
        String jrk="";
        Session ses;
        ses = HibernateUtil.getSessionFactory().openSession();
        SusunTreeJeasyUI susunTree = new SusunTreeJeasyUI();
        l = listMenuByUser(ses, userId);
        if(l==null){
        		System.out.println("------   tidak punya group dan priviledge");
        		return "";
        }else{
        
//        susunTree.menuByUser(ses,"P81035");
        susunTree(ses, BigInteger.valueOf(0), jrk, l);
        ses.close();
        sb.append("</ul>"+"\n\r");
        String x = sb.toString();
        return x;
        }
    }
private static StringBuffer sb ;
    public static void main(String[] args) {
        SusunTreeJeasyUI susunTree = new SusunTreeJeasyUI();
        susunTree.susunMenuByUser("ADMIN","1234567890123456");
        System.out.println(sb.toString());
    }
}
