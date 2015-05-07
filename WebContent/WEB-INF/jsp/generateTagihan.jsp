<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <link rel="stylesheet" type="text/css" href="css/color.css">
        <link rel="stylesheet" type="text/css" href="css/searchcss.css">  
        <script type="text/javascript" src="css/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="css/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="css/formater.js"></script>



<title>Insert title here</title>
</head>
<body>
Generate 


<form action="" method="post">
<label>Unit Kerja</label> :<input name="branchCode"	class="easyui-textbox"  id="branchCode" value=${branchCode}>	</div><br>
<label>Year Month</label> :<input name="yemm"	class="easyui-textbox"  id="yemm" required="true">yyyymm</div><br>

<input type="submit" value="Generate" id="submit">
</form>
${message}
</body>
</html>


<script>
	$("document").ready(function() {
	$('#submit').prop("disabled", false); 
	$("#branchCode").textbox("readonly", true); 
	//addComboStatusUnit();
});

//satatus Unit kerja
//function addComboStatusUnit() {
//		$('#branchCode').combobox({
//			url : 'comboLookup.htm?param=' + branchCode+'&param2=STATUS-BRANCH',
//			valueField : 'id',
//			textField : 'text',
//			panelHeight:'auto'
//		});
//		branchCode = '';
//	}

</script>