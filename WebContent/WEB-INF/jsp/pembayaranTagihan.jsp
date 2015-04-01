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
						<td style="width: 100px">NiK Pegawai:</td>
						<td style="width: 400px"><input class="easyui-textbox" type="text" name="nik" width="100px"	id="nik" data-options="required:true" ></input>
						<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="cari()">Cari</a> </td>
					</tr>
					<tr>
						<td style="width: 100px">Name Pegawai:</td>
						<td><input class="easyui-textbox" type="text" name="userName" style="width:300px" 	id="nama" data-options="required:true" ></input></td>
					</tr>
					
					<tr>
						<td>Tagihan:</td>
						<td><input class="easyui-textbox" type="text" name="tagihan" id='tagihan'></input></td>
					</tr>
					
				</table>
				<input type="submit" value="Submit">
				<input type="reset" value="Resset">
			</form>
			<!-- div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="submitForm()">Submit</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div-->
		</div>
	</div>
	<div id="win"></div>
	<script>
	
	
	
	$(document).ready(function(){
		$('#nama').textbox('readonly',true);
	});
	
	//	function submitForm() {
	//		$('#ff').form('submit');
	//	}
		
		
	    $('#ff').form({
	        success:function(data){
	        $.messager.alert('Info', data, 'info');
	        clearForm();
	        }
	    });
		
		
		
		function clearForm() {
			//alert("clear");
			$('#ff').form('clear');
			addComboTagihan();
		}
		function cari() {
			//alert("cari()..... click");
			//$('#tagihan').combobox('reload',"dataTagihan.htm?nik="+$('#nik').val());
			 $.ajax({url: "dataTagihan.htm?nik="+$('#nik').val(), success: function(result){
				 if(result=='{}'){alert('Data Tidak Ditemukan');}
				 var x = JSON.parse(result);		           
		            $("#nama").textbox('setValue',x.nama);
		            addComboTagihan();
		            //$('#tagihan').combobox('reload');
		        }});
			
		}
		
	
		
		function addComboTagihan() {
			$('#tagihan').combobox({
				url : 'comboTagihan.htm?param=' + $('#nik').val(),
				valueField : 'id',
				textField : 'text',
				panelHeight:'auto'
			});
			//tagihan = '';
		}
		
		
		
		
	</script>
</body>
</html>