package com.id.kas.DEVELOPMENT;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.id.kas.util.AbstractListScreen;
import com.id.kas.util.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;
@Controller
public class TestUploadFileController extends AbstractListScreen {

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return "uploadFile";
	}

	@RequestMapping(value="/uploadFile.htm",method=RequestMethod.GET)
	 public String doGet(java.util.Map<String,Object> model, HttpSession session, HttpServletRequest reg, HttpServletResponse res){ 
	 	return super.doGet(model, session, reg,res);
	}
	
	
	 @RequestMapping(value="/uploadFile.htm", method=RequestMethod.POST)
	 public @ResponseBody String doPost(Map<String, Object> model,HttpSession session, HttpServletRequest reg, HttpServletResponse res, @RequestParam("file") MultipartFile file,@RequestParam("name") String name) {
		
		 if (!file.isEmpty()) {
			 System.out.println("=====================   "+file.getName()+" ** "+file.getOriginalFilename());
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(Constants.SysParam.DownloadFolder+name)));
	               // System.out.println("FILEEEE "+);
	                stream.write(bytes);
	                stream.close();
	                return "You successfully uploaded " + name + "!";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
	 }
	
	
}
