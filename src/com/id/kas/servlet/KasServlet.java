package com.id.kas.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.Session;

import com.id.kas.db.HibernateUtil;
import com.id.kas.util.MyVariable;

/**
 * Servlet implementation class KasServlet
 */
public class KasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
//    	super.init();  
    	try {
    		Session sess = HibernateUtil.getSessionFactory().openSession();
        	System.out.println("Open Connection.............");
        	sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	
    	//CLOSE  OPEN
    	MyVariable.setsAppStatus("OPEN");
    	
    }
}
