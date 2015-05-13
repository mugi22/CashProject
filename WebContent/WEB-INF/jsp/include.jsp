<%@page import="java.io.*" %>
<%@page import="java.util.*" %>

<!-- membaca file properties -->
<%
	Properties properties = new Properties();
	properties.load(getServletContext().getResourceAsStream("/WEB-INF/myapp.properties"));
	%>
<!-- 	String add=properties.getProperty("button.add");
	String edit=properties.getProperty("button.edit");
	String delete=properties.getProperty("button.delete");
	String otorize=properties.getProperty("button.otorize");
	String show=properties.getProperty("button.show");
 -->    
    

<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <link rel="stylesheet" type="text/css" href="css/themes/color.css">
        <link rel="stylesheet" type="text/css" href="css/searchcss.css">     
        
        <script type="text/javascript" src="css/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="css/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="css/formater.js"></script>
		<script type="text/javascript" src="css/accounting.min.js"></script>
		<script type="text/javascript" src="css/myalert.js"></script>
		<script type="text/javascript" src="css/my.js"></script>