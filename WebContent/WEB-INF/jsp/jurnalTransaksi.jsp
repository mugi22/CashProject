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
            <table align="center">  	                       
                    <tr><td><label>IdJurnalTransaksi</label> : <input name="IdJurnalTransaksi" type="text" id="IdJurnalTransaksi" size="30" maxlength="30"></td></tr>
                    <tr><td><label>Norek</label> : <input name="Norek" type="text" id="Norek" size="30" maxlength="30"></td></tr>
                    <tr><td><label>JurnalId</label> : <input name="JurnalId" type="text" id="JurnalId" size="30" maxlength="30"></td></tr>
                    <tr><td><label>TglPosting</label> : <input name="TglPosting" type="text" id="TglPosting" size="30" maxlength="30"></td></tr>
                    <tr><td><label>TglTransaksi</label> : <input name="TglTransaksi" type="text" id="TglTransaksi" size="30" maxlength="30"></td></tr>

				 <tr>
	            	<td colspan="1">
	                <div id="btn">     
	                    <input type="button" name="btnKirim" id="btnCari" value="Cari" onclick="retrieve()">     
	                    <input type="reset" name="btnUlangi" id="btnReset" value="Reset" onclick="doClear()" >    
	                    <input type="reset" name="btnCetak" id="btnCetak" value="Cetak" onclick="doCetak()" >  
	                </div>
            	</td>
	            	
	            </tr>  
            </table>  
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  


<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="JURNALTRANSAKSI" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"  data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="idJurnalTransaksi" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.idJurnalTransaksi,0,'.',','); }"align="right">IdJurnalTransaksi</th> 
                    <th field="keterangan" width="100"sortable="true">Keterangan</th> 
                    <th field="noCoa" width="100"sortable="true">NoCoa</th> 
                    <th field="norek" width="100"sortable="true">Norek</th> 
                    <th field="jurnalId" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.jurnalId,0,'.',','); }"align="right">JurnalId</th> 
                    <th field="tglPosting" width="100"sortable="true">TglPosting</th> 
                    <th field="amountD" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.amountD,0,'.',','); }"align="right">AmountD</th> 
                    <th field="amountK" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.amountK,0,'.',','); }"align="right">AmountK</th> 
                    <th field="waktu" width="100"sortable="true">Waktu</th> 
                    <th field="tglTransaksi" width="100"sortable="true">TglTransaksi</th> 
                     
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
		<div class="ftitle">JURNALTRANSAKSI</div>
		<form id="fm" method="post" novalidate>
		<table align="center"> 
                    <tr><td><div class="fitem">	<label>IdJurnalTransaksi</label> :<input name="idJurnalTransaksi"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="idJurnalTransaksi"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>Keterangan</label> :<input name="keterangan"	class="easyui-textbox" id="keterangan"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>NoCoa</label> :<input name="noCoa"	class="easyui-textbox" id="noCoa"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>Norek</label> :<input name="norek"	class="easyui-textbox" id="norek"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>JurnalId</label> :<input name="jurnalId"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="jurnalId"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>TglPosting</label> :<input name="tglPosting"	class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" id="tglPosting"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>AmountD</label> :<input name="amountD"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="amountD"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>AmountK</label> :<input name="amountK"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="amountK"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>Waktu</label> :<input name="waktu"	class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" id="waktu"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>TglTransaksi</label> :<input name="tglTransaksi"	class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" id="tglTransaksi"></td></tr>	</div>
			
		</table>
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
		var jsonurl = 'jurnalTransaksiListAll.htm?'+
'IdJurnalTransaksi='+$('#IdJurnalTransaksi').val()+"&"+'Norek='+$('#Norek').val()+"&"+'JurnalId='+$('#JurnalId').val()+"&"+'TglPosting='+$('#TglPosting').val()+"&"+'TglTransaksi='+$('#TglTransaksi').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== jurnalTransaksitambah*/

	function doAdd() { 
		idRequired(true);
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'jurnalTransaksiAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
	function doEdit() {
		idRequired(false);
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'jurnalTransaksiEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
	function doShow() {
		idRequired(false);
		doEdit();
		onShow();
		$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
	/*
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'jurnalTransaksiEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
		*/
	}
	
	function doDelete() {
		idRequired(false);
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('jurnalTransaksiDelete.htm', {
							                    idJurnalTransaksi : row.idJurnalTransaksi,
                    jurnalId : row.jurnalId,
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
	idRequired(false);
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
	
	
function idRequired(t){
                    $('#idJurnalTransaksi').textbox({   required: t});
                    $('#jurnalId').textbox({   required: t});


	/*$('#idJurnalTransaksi').textbox({   required: t			});
	$('#jurnalId').textbox({   required: t	});
	*/
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
		                    $('#idJurnalTransaksi').textbox('readonly', true);
                    $('#keterangan').textbox('readonly', true);
                    $('#noCoa').textbox('readonly', true);
                    $('#norek').textbox('readonly', true);
                    $('#jurnalId').textbox('readonly', true);
                    $('#tglPosting').textbox('readonly', true);
                    $('#amountD').textbox('readonly', true);
                    $('#amountK').textbox('readonly', true);
                    $('#waktu').textbox('readonly', true);
                    $('#tglTransaksi').textbox('readonly', true);

		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		                    $('#idJurnalTransaksi').textbox('readonly', false);
                    $('#keterangan').textbox('readonly', false);
                    $('#noCoa').textbox('readonly', false);
                    $('#norek').textbox('readonly', false);
                    $('#jurnalId').textbox('readonly', false);
                    $('#tglPosting').textbox('readonly', false);
                    $('#amountD').textbox('readonly', false);
                    $('#amountK').textbox('readonly', false);
                    $('#waktu').textbox('readonly', false);
                    $('#tglTransaksi').textbox('readonly', false);
		
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		                    $('#idJurnalTransaksi').textbox('readonly', true);
                    $('#keterangan').textbox('readonly', false);
                    $('#noCoa').textbox('readonly', false);
                    $('#norek').textbox('readonly', false);
                    $('#jurnalId').textbox('readonly', true);
                    $('#tglPosting').textbox('readonly', false);
                    $('#amountD').textbox('readonly', false);
                    $('#amountK').textbox('readonly', false);
                    $('#waktu').textbox('readonly', false);
                    $('#tglTransaksi').textbox('readonly', false);
	
		$('#btnSave').linkbutton('enable');
	}
/*===============================================REPORT==================================*/
function doCetak(){
		var repUrl = 'jurnalTransaksiReport.htm?'+
					  'IdJurnalTransaksi='+$('#IdJurnalTransaksi').val()+"&"+'Norek='+$('#Norek').val()+"&"+'JurnalId='+$('#JurnalId').val()+"&"+'TglPosting='+$('#TglPosting').val()+"&"+'TglTransaksi='+$('#TglTransaksi').val()+"&"+"userId="+"${userId}";;
		var s = window.location.search.replace("?", "");
		window.open(repUrl+"&"+s,
				"_blank", 
				"toolbar=no, scrollbars=yes, resizable=yes,	directories=no, location=no, \
				 menubar=no, status=no,'");
	}

	
</script>
