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
	<h2>Basic Form</h2>
	
	<div style="margin: 20px 0;"></div>
	<div style="width: 100%">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5" align="center">

					<tr>
						<td style="width: 200px">Keterangan
						<td>
						<td><input class="easyui-textbox" type="text"
							name="Keterangan" id="Keterangan" data-options="required:false"
							value=""></td>
					</tr>

					<tr>
						<td style="width: 100px">SeqName
						<td>
						<td><input class="easyui-textbox" type="text" name="SeqName"
							id="SeqName" data-options="required:false" value=""></td>
					</tr>

					<tr>
						<td style="width: 200px">SeqNum
						<td>
						<td><input class="easyui-textbox" type="text" name="SeqNum"
							id="SeqNum" data-options="required:false" value=""></td>
					</tr>

					<tr>
						<td style="width: 200px">Tarif
						<td>
						<td><input class="easyui-textbox" type="text" name="Tarif"
							id="Tarif" data-options="required:false" value=""></td>
					</tr>

					<tr>
						<td style="width: 200px">LastLogIn
						<td>
						<td><input class="easyui-textbox" type="text"
							name="LastLogIn" id="LastLogIn" data-options="required:true"
							value=""></td>
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
