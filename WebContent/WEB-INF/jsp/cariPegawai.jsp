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

		<script type="text/javascript" src="css/myalert.js"></script>

<title>User</title>
</head>
<body>

<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="PEGAWAI" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                	<th field="nik" width="100"sortable="true">Nik</th>
                    <th field="nama" width="100"sortable="true">Nama</th>
                    <th field="cif" width="100"sortable="true">Cif</th> 
                    <th field="branchCode" width="100"sortable="true">BranchCode</th> 
                    <th field="statusPegawai" width="100"sortable="true">StatusPegawai</th> 
                    <th field="statusAktif" width="100"sortable="true">StatusAktif</th> 
                    <th field="tglLahir" width="100"sortable="true">TglLahir</th> 
                    <th field="grade" width="100"sortable="true">Grade</th> 
                     
                </tr>
            </thead>
        </table>        
        <div id="toolbar">
        <label>Nik</label> :<input name="Nik"	type="hidden" class="easyui-textbox" id="userId" value="${user}">	
            	<label>Nik</label> :<input name="Nik"	class="easyui-textbox" id="Nik">	                 
 			 	<label>Nama</label> :<input name="Nama"	class="easyui-textbox"  id="Nama">	
				  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="retrieve()" id="btnShow">Cari</a>
          
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
    
       
</body>
</html>



<script>
var url;
var branchcode;
var statusPegawai;
var statusAktif;
var grade;
	$("document").ready(function() {
		$("#btnAdd").linkbutton('${btnAdd}');
		$("#btnEdit").linkbutton('${btnEdit}');
		$("#btnDelete").linkbutton('${btnDelete}');
		$("#btnShow").linkbutton('${btnShow}');	
		branchcode = ''; //combobox tidak ada default
		addComboBranch();
		addComboStatusPegawai();
		addComboStatusAktif();
		addComboGrade();
		upperCase($('#nama'));
		upperCase($('#nik'));
		upperCase($('#Nik'));
		upperCase($('#Nama'));
		
		
	});
	
	
	
	function test() {
		alert("testtttt..... click");
	}

/* function untuk list data      param=' + $('#idSearch').val();//+'&param2='++ $('#idSearch2').val();*/
	function retrieve() {		
		var jsonurl = 'cariPegawaiListAll.htm?'+
'Nik='+$('#Nik').val()+"&"+'Nama='+$('#Nama').val()+"&"+"userId="+"${userId}";
		$('#dg').datagrid({
			url : jsonurl,
			onLoadSuccess : function(data) {
				if (data.total == 0) {
					alert("Data Tidak Ditemukan..................");
				}
			}
		});
	}

	function doClear() {
		$('#formCari').form('clear');
		var jsonurl = "listClear.htm";
		$('#dg').datagrid({
			url : jsonurl,
			onLoadSuccess : function(data) {
				if (data.total > 0) {
					alert("Clear Gagal..................");
				}
			}
		});
	}

	/* END function untuk list data*/
	
	/* ============FORM FUNCTION ========== pegawaitambah*/


	
	//
	if (e.keyCode == 13){	// when press ENTER key, accept the inputed value.
		t.textbox('setValue', $(this).val());
	}
	
	/*Untuk membuat menjadi huruf besar semua */
	function upperCase(t) {
		t.textbox('textbox').bind('keyup', function(e) {
			$(this).val($(this).val().toUpperCase());
		});
	}

	/*Untuk membuat menjadi huruf besar semua */
	function keyEnter(t) {
		t.textbox('textbox').bind('keyup', function(e) {
			if (e.keyCode == 13){	// when press ENTER key, accept the inputed value.
				t.textbox('setValue', $(this).val());
			alert($(this).val());
			}
		});
	}
	
	
	/*inputan readonly atau tidak saat onShow  XXXenableField */
	function onShow() {
                    $('#branchCode').textbox('readonly', true);
                    $('#statusPegawai').textbox('readonly', true);
                    $('#statusAktif').textbox('readonly', true);
                    $('#nik').textbox('readonly', true);
                    $('#cif').textbox('readonly', true);
                    $('#nama').textbox('readonly', true);
                    $('#tglLahir').textbox('readonly', true);
                    $('#grade').textbox('readonly', true);

		//form button
		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
                    $('#branchCode').textbox('readonly', false);
                    $('#statusPegawai').textbox('readonly', false);
                    $('#statusAktif').textbox('readonly', false);
                    $('#nik').textbox('readonly', false);
                    $('#cif').textbox('readonly', false);
                    $('#nama').textbox('readonly', false);
                    $('#tglLahir').textbox('readonly', false);
                    $('#grade').textbox('readonly', false);
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
                   $('#branchCode').textbox('readonly', false);
                    $('#statusPegawai').textbox('readonly', false);
                    $('#statusAktif').textbox('readonly', false);
                    $('#nik').textbox('readonly', true);
                    $('#cif').textbox('readonly', false);
                    $('#nama').textbox('readonly', false);
                    $('#tglLahir').textbox('readonly', false);
                    $('#grade').textbox('readonly', false);
		$('#btnSave').linkbutton('enable');
	}

	
</script>
