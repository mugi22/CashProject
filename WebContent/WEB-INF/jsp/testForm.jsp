<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="stylesheet" type="text/css" href="css/color.css">
<link rel="stylesheet" type="text/css" href="css/searchcss.css">

<script type="text/javascript" src="css/jquery-1.11.2.js"></script>
<script type="text/javascript" src="css/jquery.easyui.min.js"></script>
<script type="text/javascript" src="css/formater.js"></script>

<script type="text/javascript" src="css/myalert.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>Basic Form</h2>
	<p>Fill the form and submit it.</p>
	<div style="margin: 20px 0;"></div>
	<div style="width: 100%">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5" align="center">
					<tr>
						<td style="width: 200px">User Id:</td>
						<td><input class="easyui-textbox" type="text" name="userId"	id="userId" data-options="required:true" value="test"></input></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"	onclick="cari()">Cari</a> </td>
					</tr>
					<tr>
						<td style="width: 200px">User Name:</td>
						<td><input class="easyui-textbox" type="text" name="userName"	data-options="required:true" value="name"></input></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input class="easyui-textbox" type="text" name="email"	data-options="required:true,validType:'email'" value="test@yahoo.com"></input></td>
					</tr>
					<tr>
						<td>Subject:</td>
						<td><input class="easyui-textbox" type="text" name="subject" data-options="required:true" value="Test Subject"></input></td>
					</tr>
					<tr>
						<td>Message:</td>
						<td><input class="easyui-textbox" name="message"data-options="multiline:true" style="height: 60px" value="message test"></input></td>
					</tr>
					<tr>
						<td>Language:</td>
						<td><select class="easyui-combobox" name="language">
								<option	value="ar">Arabic</option>
								<option value="bg">Bulgarian</option>
								<option value="ca">Catalan</option>
								<option value="vi">Vietnamese</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="submitForm()">Submit</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<div id="win"></div>
	<script>
		function submitForm() {
			$('#ff').form('submit');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
		function cari() {
			//alert("testtttt..... click");
			$('#win').window({
		        width:600,
		        height:400,
		        href:'cariUser.htm',
		        modal:true,
		        minimizable:false,
		        maximizable:false,
		        collapsible:false
		        
		        });
		}
		
		function doAmbil(){ //ambil key dari row yang dipilih/klik
			var row = $('#dg2').datagrid('getSelected');
			//alert(row.userId);
			$('#userId').textbox('setValue',row.userId);
			$('#win').window('close'); 
		}
		
	</script>
</body>
</html>