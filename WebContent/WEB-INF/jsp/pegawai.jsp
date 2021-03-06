<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jsp" %>
<title>User</title>
</head>
<body>

<!-- ******************************FORM PENCARIAN******************************* -->   
        <div id="div2">
            <form name="FREG" id="formCari" method="post" action="#"  >                 
                    <label>Nik</label> : <input name="Nik" type="text" id="Nik" class="easyui-textbox"  size="30" maxlength="30"><br>
                    <label>Nama</label> : <input name="Nama" type="text" id="Nama" class="easyui-textbox" size="30" maxlength="30"><br>

                <div id="btn">     
                    <%@ include file="searchButton.jsp" %>     
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  

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
        <%@ include file="toolbar.jsp" %>          
        	<td align="right">
        	<!-- 
        		 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-authorize" plain="true" onclick="" id="btnAdd" ><%=properties.getProperty("button.otorize")  %></a>
        	-->
        	</td>
        	</tr>
        </table>
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
    
          
<!-- ************************** FORM ******************************************** -->
	<div id="dlg" class="easyui-dialog"	style="width: 750px;  padding: 10px 20px" closed="true"	buttons="#dlg-buttons" data-options="modal:true">
		<div class="ftitle">PEGAWAI</div>
		<form id="fm" method="post" novalidate>
		            <div class="fitem">	<label>Nik</label> :<input name="nik"	class="easyui-textbox" required="true" id="nik">	</div>                    
                    <div class="fitem">	<label>Nama</label> :<input name="nama"	class="easyui-textbox" required="true" id="nama">	</div>
					<div class="fitem">	<label>Cif</label> :<input name="cif"	class="easyui-textbox" id="cif">	</div>
                    <div class="fitem">	<label>BranchCode</label> :<input name="branchCode"	class="easyui-textbox"  id="branchCode">	</div>
                    <div class="fitem">	<label>StatusPegawai</label> :<input name="statusPegawai"	class="easyui-textbox"  id="statusPegawai">	</div>
                    <div class="fitem">	<label>StatusAktif</label> :<input name="statusAktif"	class="easyui-textbox" id="statusAktif">	</div>
                    <div class="fitem">	<label>TglLahir</label> :<input name="tglLahir"	class="easyui-datebox" required="true" id="tglLahir" data-options="formatter:myformatter,parser:myparser">	</div>
                    <div class="fitem">	<label>Grade</label> :<input name="grade"	class="easyui-textbox"  id="grade">	</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="doSave()" style="width: 90px" id="btnSave">Save</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" 	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"style="width: 90px" id="btnCancel">Cancel</a>
	</div>
	<!-- ************************************************END FORM******************* -->

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
		var jsonurl = 'pegawaiListAll.htm?'+
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

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'pegawaiAdd.htm?'+"userId="+"${userId}";
		//keyEnter($('#nik'));
		//upperCase($('#nik'));
		//upperCase($('#nama'));
		onAdd();
	}
/* ---- pegawaiedit*/
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'pegawaiEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
/*-- pegawaitampil*/
	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'pegawaiEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('pegawaiDelete.htm', {
							                    nik : row.nik,userId:"${userId}"
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.errorMsg + "Delete Gagal "
									});
								}
							}, 'json');
						}
					});
		}
	}

	function doSave() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var resultx = eval('(' + result + ')');
				if (resultx === 'fail' || result === null) {
					alertError("Simpan Gagal");					
				} else {
					alertAll('Simpan Sukses');
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
					$('#dg').datagrid({
						url : jsonurl
					});
				}
			}
		});
	}
	
	
	/* ================TAMBAHAN=================*/
	//untuk mengisi combobox kode cabang keseluruhan dengan default value sesuai parameter
	function addComboBranch() {
		$('#branchCode').combobox({
			url : 'comboAllBranch.htm?param=' + branchcode,
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		branchcode = '';
	}
	
	
	//satatus pegawai
	function addComboStatusPegawai() {
		$('#statusPegawai').combobox({
			url : 'comboLookup.htm?param=' + statusPegawai+'&param2=STATUS-PEG',
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		statusPegawai = '';
	}
	
	//satatus pegawai
	function addComboStatusAktif() {
		$('#statusAktif').combobox({
			url : 'comboLookup.htm?param=' + statusAktif+'&param2=STATUS-AKTIF',
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		statusAktif = '';
	}
	
	//grade
	function addComboGrade() {
		$('#grade').combobox({
			url : 'comboLookup.htm?param=' + grade+'&param2=GRADE',
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		grade = '';
	}
	
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
