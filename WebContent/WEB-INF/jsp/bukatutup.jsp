<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ include file="include.jsp" %>

<title>Insert title here</title>
</head>
<body>
<h1>Status  : ${unitStatus}</h1>

<form action="" method="post">
<label>Unit Status</label> :<input name="branchCode"	class="easyui-textbox"  id="branchCode">	</div>
<input type="submit" value="Submit" id="submit">
</form>
</body>
</html>


<script>
$("document").ready(function() {
	$('#submit').prop("disabled", ${ubah}); 
	$('#branchCode').prop("disabled", ${ubah}); 
	addComboStatusUnit();
});

//satatus Unit kerja
function addComboStatusUnit() {
		$('#branchCode').combobox({
			url : 'comboLookup.htm?param=' + branchCode+'&param2=STATUS-BRANCH',
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		branchCode = '';
	}

</script>