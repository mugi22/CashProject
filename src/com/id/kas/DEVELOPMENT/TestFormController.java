package com.id.kas.DEVELOPMENT;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.id.kas.util.AbstractListScreen;

@Controller
public class TestFormController extends AbstractListScreen {
	@RequestMapping(value="/testForm.htm",method=RequestMethod.GET)
	@Override
	public String doGet(Map<String, Object> model, HttpSession session,	HttpServletRequest reg) {
		// TODO Auto-generated method stub
		return super.doGet(model, session, reg);
	}

	@RequestMapping(value="/testForm.htm",method=RequestMethod.POST)
	@Override
	public String doPost(Map<String, Object> model, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("DO POST");
		return super.doPost(model, session);
	}
	
	
	 @RequestMapping(value="/testReport.htm", method=RequestMethod.GET)
     public String paramListAll(Map<String, Object> model,HttpSession session,HttpServletRequest reg) {
		 return "testReport";
	 }
	@Override
	protected String getView() {

		return "testForm";
	}

}
