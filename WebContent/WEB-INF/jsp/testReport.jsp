<%@page import="com.id.unitTest.Reporter"%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page  %>


    <%@ page import="java.io.*, java.util.*,
    net.sf.jasperreports.engine.*, 
    net.sf.jasperreports.engine.export.*" %>
    
    
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Test Report</h1>
<%
	
	Reporter rep = new Reporter();
	JasperPrint jasperPrint = rep.print();
	 
	OutputStream outputStream = response.getOutputStream();
	response.setContentType("application/pdf");
	JRExporter jrExporter = null;
	jrExporter = new JRPdfExporter();
	jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
	
	jrExporter.exportReport();
	outputStream.close();
	out.clear();
	out = pageContext.pushBody();
%>




</body>
</html>