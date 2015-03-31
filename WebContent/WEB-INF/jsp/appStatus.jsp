<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <link rel="stylesheet" type="text/css" href="css/color.css">
        <link rel="stylesheet" type="text/css" href="css/searchcss.css">     
        
        <script type="text/javascript" src="css/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="css/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="css/formater.js"></script>

		<script type="text/javascript" src="css/myalert.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- ************************** FORM ******************************************** -->
	<div class="ftitle">APP STATUS</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	
                    <label>Status</label> :<input name="status"	class="easyui-textbox" id="status">	</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="doSave()" style="width: 90px" id="btnSave">Save</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" 	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"style="width: 90px" id="btnCancel">Cancel</a>
	</div>
	<!-- ************************************************END FORM******************* -->
<div id='result'></div>
		
</body>
</html>

<script>
$("document").ready(function(){
	addComboAppStatus();
});


function addComboAppStatus() {
	$('#status').combobox({
		url : 'comboLookup.htm?param=' + status+'&param2=APP-STAT',
		valueField : 'id',
		textField : 'text',
		panelHeight:'auto'
	});
	status = '';
}

//do save
function doSave() {
	$('#fm').form('submit');
	}



</script>