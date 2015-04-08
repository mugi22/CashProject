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

<style type="text/css">
	.numberbox .textbox-text{
	  text-align: right;
	  color: red;
	}
</style>

<title>Penerimaan Pembayaran Uang Kas</title>
</head>
<body>
	<h2>Pembayaran</h2>
	<p></p>
	<div style="margin: 20px 0;"></div>
	<div style="width: 100%">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post" >
				<table cellpadding="5" align="center">
					<tr>
						<td style="width: 100px">NiK Pegawai:</td>
						<td style="width: 400px"><input class="easyui-textbox" type="text" name="nik" width="100px"	id="nik" onblur="test()"
						autocomplete="off" data-options="required:true" ></input>
						<a href="javascript:void(0)" class="easyui-linkbutton"	onclick="cari()">Cari</a> </td>
					</tr>
					<tr>
						<td style="width: 100px">Name Pegawai:</td>
						<td><input class="easyui-textbox" type="text" name="userName" style="width:300px" 	id="nama" autocomplete="off" data-options="required:true" ></input></td>
					</tr>
					
					<tr>
						<td>Tagihan:</td>
						<td><input class="easyui-textbox" type="text" name="tagihan" id='tagihan'></input></td>
					</tr>
					<tr>
						<td>Jumlah Tunggakan:</td>
						<td><input class="easyui-numberbox" type="text" name="tunggakan" id='tunggakan'
						data-options="required:true,min:0,precision:0,groupSeparator:','," autocomplete="off"></input></td>
					</tr>
					<tr>
						<td>Diterima:</td>
						<td><input class="easyui-numberbox" type="text" name="terima" id='terima' 
						data-options="required:true,min:0,precision:0,groupSeparator:','," autocomplete="off" onblur="test()"></input></td>
					</tr>
					
					<tr>
						<td>Kembali:</td>
						<td><input class="easyui-numberbox" type="text" name="kembali" id='kembali'
						data-options="required:true,precision:0,groupSeparator:','" autocomplete="off"></input></td>
					</tr>
				</table>
				<!-- 
				<input type="submit" value="Submit" id='submit'>
				<input type="reset" value="Resset" id='reset'>
				 -->
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" id='submit'	onclick="submitForm()">Submit</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" id='reset' onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<div id="win"></div>
	<script>
	
	
	
	
	function test(){
		alert("test");
	}
	
	$(document).ready(function(){
		$('#nama').textbox('readonly',true);
		$("#terima").textbox('readonly',true);
		$("#kembali").textbox('readonly',true);
		$("#tunggakan").numberbox('readonly',true);
		clearForm();
		upperCase($('#nik'));	
		//upperBlur($('#terima'));
		cekBayar($('#terima'));
	});
	
    function submitForm(){
    	//validasi
    	$('#kembali').numberbox('setValue',$('#terima').val()-$('#tunggakan').val());    	
		$('#ff').form('submit', {			
			success : function(data) {	
				//alert(data);
				if (data=="SUCCESSS") {
					alert("SUCCESSS");
				}else{
					alert("Gagal........");
				}
				clearForm();
			}
		});
    	
    }


		function clearForm() {
			$('#ff').form('clear');
			addComboTagihan();
			$("#terima").textbox('readonly', true);
			$("#kembali").textbox('readonly', true);
			$("#tagihan").combobox('readonly', true);
			$("#submit").linkbutton('disable');
			$("#reset").linkbutton('disable');
		}
		function cari() {
			$.ajax({
				url : "dataTagihan.htm?nik=" + $('#nik').val(),
				success : function(result) {
					if (result == '{}') {
						alert('Data Tunggakan Tidak Ditemukan');
						clearForm();
					} else {
						$("#submit").linkbutton('disable');
						$("#reset").linkbutton('disable');
						$("#terima").textbox('readonly', false);
						//$("#kembali").textbox('readonly', false);
						var x = JSON.parse(result);
						$("#nama").textbox('setValue', x.nama);
						addComboTagihan();
					}
					
				}
			});

		}

		function addComboTagihan() {
			$('#tagihan').combobox({
				url : 'comboTagihan.htm?param=' + $('#nik').val(),
				valueField : 'id',
				textField : 'text',
				panelHeight : 'auto',
				onSelect: function(){
					getTagihan();
				}
			});
		}

		
		function getTagihan(x){			
			$.ajax({
				url:'getTagihan.htm?param=' + $('#nik').val()+'&param2='+$('#tagihan').combobox('getValue'),
				success : function(data) {	
					var x = JSON.parse(data);					
					$('#tunggakan').numberbox('setValue', x.jml);
					$("#submit").linkbutton('disable');
					$("#reset").linkbutton('disable');
					$('#terima').numberbox('setValue',0);
					$('#kembali').numberbox('setValue',0);
				}		
			});
		}
		/*Untuk membuat menjadi huruf besar semua */
		function upperCase(t) {
			t.textbox('textbox').bind('keyup', function(e) {
				$(this).val($(this).val().toUpperCase());
			});
		}
		
		
		function upperBlur(t) {
			t.textbox('textbox').bind('blur', function(e) {
				//alert("g");
				//$("#nama").textbox('setValue', x.nama);
				$('#kembali').numberbox('setValue',$(this).val());
			});
		}
		
		/*Untuk membuat menjadi huruf besar semua */
		function cekBayar(t) {
			t.textbox('textbox').bind('keyup', function(e) {
				//alert("alert");
				$('#kembali').numberbox('setValue',$(this).val()-$('#tunggakan').val());
				if($('#kembali').val()>=0){
					$("#submit").linkbutton('enable');
					$("#reset").linkbutton('enable');
				}else{
					$("#submit").linkbutton('disable');
					$("#reset").linkbutton('disable');
				}
			});
		}
	</script>
</body>
</html>