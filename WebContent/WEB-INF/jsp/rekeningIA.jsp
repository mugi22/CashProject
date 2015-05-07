<%-- 
Create by CodeGenerator
jspTemplate
--%>

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
                    <label>Description</label> : <input name="Description" type="text" id="Description" size="30" maxlength="30"><br>
                    <label>Norek</label> : <input name="Norek" type="text" id="Norek" size="30" maxlength="30"><br>
                    <label>BranchCode</label> : <input name="BranchCode" type="text" id="BranchCode" size="30" maxlength="30"><br>
                    <label>NorekIAMaster</label> : <input name="NorekIAMaster" type="text" id="NorekIAMaster" size="30" maxlength="30"><br>

                <div id="btn">     
                    <input type="button" name="btnKirim" id="btnCari" value="Cari" onclick="retrieve()">     
                    <input type="reset" name="btnUlangi" id="btnReset" value="Reset" onclick="doClear()" >     
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  


<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="REKENINGIA" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="description" width="100"sortable="true">Description</th> 
                    <th field="norek" width="100"sortable="true">Norek</th> 
                    <th field="noCOA" width="100"sortable="true">NoCOA</th> 
                    <th field="tglBuka" width="100"sortable="true">TglBuka</th> 
                    <th field="saldoAwal" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">SaldoAwal</th> 
                    <th field="saldoAkhir" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">SaldoAkhir</th> 
                    <th field="mutasiD" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">MutasiD</th> 
                    <th field="mutasiK" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">MutasiK</th> 
                    <th field="createBy" width="100"sortable="true">CreateBy</th> 
                    <th field="branchCode" width="100"sortable="true">BranchCode</th> 
                    <th field="alternateId" width="100"sortable="true">AlternateId</th> 
                    <th field="norekIAMaster" width="100"sortable="true">NorekIAMaster</th> 
                    <th field="saldoNormal" width="100"sortable="true">SaldoNormal</th> 
                    <th field="lastTrxDate" width="100"sortable="true">LastTrxDate</th> 
                     
                </tr>
            </thead>
        </table>        
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()" id="btnAdd" >Tambah</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()" id="btnEdit">Edit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete()" id="btnDelete">Hapus</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doShow()" id="btnShow">Tampil</a>
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
    
          
<!-- ************************** FORM ******************************************** -->
	<div id="dlg" class="easyui-dialog"	style="width: 750px;  padding: 10px 20px" closed="true"	buttons="#dlg-buttons" data-options="modal:true">
		<div class="ftitle">REKENINGIA</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Description</label> :<input name="description"	class="easyui-textbox" required="false" id="description">	</div>
                    <div class="fitem">	<label>Norek</label> :<input name="norek"	class="easyui-textbox" required="false" id="norek">	</div>
                    <div class="fitem">	<label>NoCOA</label> :<input name="noCOA"	class="easyui-textbox" required="false" id="noCOA">	</div>
                    <div class="fitem">	<label>TglBuka</label> :<input name="tglBuka"	class="easyui-textbox" required="false" id="tglBuka">	</div>
                    <div class="fitem">	<label>SaldoAwal</label> :<input name="saldoAwal"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" required="false" id="saldoAwal">	</div>
                    <div class="fitem">	<label>SaldoAkhir</label> :<input name="saldoAkhir"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" required="false" id="saldoAkhir">	</div>
                    <div class="fitem">	<label>MutasiD</label> :<input name="mutasiD"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" required="false" id="mutasiD">	</div>
                    <div class="fitem">	<label>MutasiK</label> :<input name="mutasiK"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" required="false" id="mutasiK">	</div>
                    <div class="fitem">	<label>BranchCode</label> :<input name="branchCode"	class="easyui-textbox" required="false" id="branchCode">	</div>
                    <div class="fitem">	<label>AlternateId</label> :<input name="alternateId"	class="easyui-textbox" required="false" id="alternateId">	</div>
                    <div class="fitem">	<label>NorekIAMaster</label> :<input name="norekIAMaster"	class="easyui-textbox" required="false" id="norekIAMaster">	</div>
                    <div class="fitem">	<label>SaldoNormal</label> :<input name="saldoNormal"	class="easyui-textbox" required="false" id="saldoNormal">	</div>
                    <div class="fitem">	<label>LastTrxDate</label> :<input name="lastTrxDate"	class="easyui-textbox" required="false" id="lastTrxDate">	</div>
			
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
	$("document").ready(function() {
		$("#btnAdd").linkbutton('${btnAdd}');
		$("#btnEdit").linkbutton('${btnEdit}');
		$("#btnDelete").linkbutton('${btnDelete}');
		$("#btnShow").linkbutton('${btnShow}');		
	});

	function test() {
		alert("testtttt..... click");
	}

	function retrieve() {		
		var jsonurl = 'rekeningIAListAll.htm?'+
'Description='+$('#Description').val()+"&"+'Norek='+$('#Norek').val()+"&"+'BranchCode='+$('#BranchCode').val()+"&"+'NorekIAMaster='+$('#NorekIAMaster').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== rekeningiatambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'rekeningIAAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'rekeningIAEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'rekeningIAEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('rekeningIADelete.htm', {
							                    norek : row.norek,
							userId:"${userId}"
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

	
	/*Untuk membuat menjadi huruf besar semua */
	function upperCase(t) {
		t.textbox('textbox').bind('keyup', function(e) {
			$(this).val($(this).val().toUpperCase());
		});
	}
	
	/*inputan readonly atau tidak saat onShow  XXXenableField */
	function onShow() {
		                    $('#description').textbox('readonly', true);
                    $('#norek').textbox('readonly', true);
                    $('#noCOA').textbox('readonly', true);
                    $('#tglBuka').textbox('readonly', true);
                    $('#saldoAwal').textbox('readonly', true);
                    $('#saldoAkhir').textbox('readonly', true);
                    $('#mutasiD').textbox('readonly', true);
                    $('#mutasiK').textbox('readonly', true);
                    $('#branchCode').textbox('readonly', true);
                    $('#alternateId').textbox('readonly', true);
                    $('#norekIAMaster').textbox('readonly', true);
                    $('#saldoNormal').textbox('readonly', true);
                    $('#lastTrxDate').textbox('readonly', true);

		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		                    $('#description').textbox('readonly', false);
                    $('#norek').textbox('readonly', false);
                    $('#noCOA').textbox('readonly', false);
                    $('#tglBuka').textbox('readonly', false);
                    $('#saldoAwal').textbox('readonly', false);
                    $('#saldoAkhir').textbox('readonly', false);
                    $('#mutasiD').textbox('readonly', false);
                    $('#mutasiK').textbox('readonly', false);
                    $('#branchCode').textbox('readonly', false);
                    $('#alternateId').textbox('readonly', false);
                    $('#norekIAMaster').textbox('readonly', false);
                    $('#saldoNormal').textbox('readonly', false);
                    $('#lastTrxDate').textbox('readonly', false);
		
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		                    $('#description').textbox('readonly', true);
                    $('#norek').textbox('readonly', true);
                    $('#noCOA').textbox('readonly', true);
                    $('#tglBuka').textbox('readonly', true);
                    $('#saldoAwal').textbox('readonly', true);
                    $('#saldoAkhir').textbox('readonly', true);
                    $('#mutasiD').textbox('readonly', true);
                    $('#mutasiK').textbox('readonly', true);
                    $('#branchCode').textbox('readonly', true);
                    $('#alternateId').textbox('readonly', true);
                    $('#norekIAMaster').textbox('readonly', true);
                    $('#saldoNormal').textbox('readonly', true);
                    $('#lastTrxDate').textbox('readonly', true);
	
		$('#btnSave').linkbutton('enable');
	}

	
</script>
