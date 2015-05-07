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
                    <label>Nik</label> : <input name="Nik" type="text" id="Nik" size="30" maxlength="30"><br>
                    <label>Yemm</label> : <input name="Yemm" type="text" id="Yemm" size="30" maxlength="30"><br>
                    <label>Branchcode</label> : <input name="Branchcode" type="text" id="Branchcode" size="30" maxlength="30"><br>

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
        <table id="dg" title="TAGIHAN" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="nik" width="100"sortable="true">Nik</th> 
                    <th field="grade" width="100"sortable="true">Grade</th> 
                    <th field="jumlah" width="100"sortable="true"   data-options="
					      formatter:function(value, row){					      
					      return accounting.formatNumber(row.jumlah,0,'.',',');
					}" align="right">Jumlah</th> 
                    <th field="yemm" width="100"sortable="true">Yemm</th> 
                    <th field="branchcode" width="100"sortable="true">Branchcode</th> 
                    <th field="tglBayar" width="100"sortable="true">TglBayar</th> 
                    <th field="satusBayar" width="100"sortable="true">SatusBayar</th> 
                    <th field="kasir" width="100"sortable="true">Kasir</th> 
                     
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
		<div class="ftitle">TAGIHAN</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Nik</label> :<input name="nik"	class="easyui-textbox" required="false" id="nik">	</div>
                    <div class="fitem">	<label>Grade</label> :<input name="grade"	class="easyui-textbox" required="false" id="grade">	</div>
                    <div class="fitem">	<label>Jumlah</label> :<input name="jumlah"	class="easyui-textbox" required="false" id="jumlah">	</div>
                    <div class="fitem">	<label>Yemm</label> :<input name="yemm"	class="easyui-textbox" required="false" id="yemm">	</div>
                    <div class="fitem">	<label>Branchcode</label> :<input name="branchcode"	class="easyui-textbox" required="false" id="branchcode">	</div>
                    <div class="fitem">	<label>TglBayar</label> :<input name="tglBayar"	class="easyui-textbox" required="false" id="tglBayar">	</div>
                    <div class="fitem">	<label>SatusBayar</label> :<input name="satusBayar"	class="easyui-textbox" required="false" id="satusBayar">	</div>
                    <div class="fitem">	<label>Kasir</label> :<input name="kasir"	class="easyui-textbox" required="false" id="kasir">	</div>
			
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

/* function untuk list data      param=' + $('#idSearch').val();//+'&param2='++ $('#idSearch2').val();*/
	function retrieve() {		
		var jsonurl = 'tagihanListAll.htm?'+
'Nik='+$('#Nik').val()+"&"+'Yemm='+$('#Yemm').val()+"&"+'Branchcode='+$('#Branchcode').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== tagihantambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'tagihanAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
/* ---- tagihanedit*/
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'tagihanEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
/*-- tagihantampil*/
	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'tagihanEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('tagihanDelete.htm', {
							       nik : row.nik,
                    			   yemm : row.yemm,
                    	           branchcode : row.branchcode,
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
	
	/*Untuk membuat menjadi huruf besar semua */
	function upperCase(t) {
		t.textbox('textbox').bind('keyup', function(e) {
			$(this).val($(this).val().toUpperCase());
		});
	}
	
	/*inputan readonly atau tidak saat onShow  XXXenableField */
	function onShow() {
		//list button
		//$('#userId').textbox('readonly', true);
                    $('#nik').textbox('readonly', true);
                    $('#grade').textbox('readonly', true);
                    $('#jumlah').textbox('readonly', true);
                    $('#yemm').textbox('readonly', true);
                    $('#branchcode').textbox('readonly', true);
                    $('#tglBayar').textbox('readonly', true);
                    $('#satusBayar').textbox('readonly', true);
                    $('#kasir').textbox('readonly', true);

		//form button
		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		//list button
		//$('#userId').textbox('readonly', false);		
                    $('#nik').textbox('readonly', false);
                    $('#grade').textbox('readonly', false);
                    $('#jumlah').textbox('readonly', false);
                    $('#yemm').textbox('readonly', false);
                    $('#branchcode').textbox('readonly', false);
                    $('#tglBayar').textbox('readonly', false);
                    $('#satusBayar').textbox('readonly', false);
                    $('#kasir').textbox('readonly', false);
		
		//form button
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		//list button
		//$('#userId').textbox('readonly', true);	
                    $('#nik').textbox('readonly', true);
                    $('#grade').textbox('readonly', false);
                    $('#jumlah').textbox('readonly', false);
                    $('#yemm').textbox('readonly', true);
                    $('#branchcode').textbox('readonly', true);
                    $('#tglBayar').textbox('readonly', false);
                    $('#satusBayar').textbox('readonly', false);
                    $('#kasir').textbox('readonly', false);
	
		//form button
		$('#btnSave').linkbutton('enable');
	}

	
</script>
