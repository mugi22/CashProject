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


<title>User</title>
</head>
<body>

<!-- ******************************FORM PENCARIAN******************************* -->   
        <div id="div2">
            <form name="FREG" id="formCari" method="post" action="#"  >    
                <label>Kode Provinsi</label> : 
                <input name="idSearch" type="text" id="idSearch" size="30" maxlength="30"><br>
                
                 <label>Kode Kabupaten</label> : 
                <input name="idSearch2" type="text" id="idSearch2" size="30" maxlength="30"><br>

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
        <table id="dg" title="KABUPATEN" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="createBy" width="100"sortable="true">CreateBy</th> 
                    <th field="updateBy" width="100"sortable="true">UpdateBy</th> 
                    <th field="versi" width="100"sortable="true">Versi</th> 
                    <th field="kodeProvinsi" width="100"sortable="true">KodeProvinsi</th> 
                    <th field="kodeKabupaten" width="100"sortable="true">KodeKabupaten</th> 
                    <th field="namaKabupaten" width="100"sortable="true">NamaKabupaten</th> 
                    <th field="createDate" width="100"sortable="true">CreateDate</th> 
                    <th field="updateDate" width="100"sortable="true">UpdateDate</th> 
                     
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
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>KodeProvinsi</label> :<input name="kodeProvinsi"	class="easyui-textbox" required="false" id="kodeProvinsi">	</div>
                    <div class="fitem">	<label>KodeKabupaten</label> :<input name="kodeKabupaten"	class="easyui-textbox" required="false" id="kodeKabupaten">	</div>
                    <div class="fitem">	<label>NamaKabupaten</label> :<input name="namaKabupaten"	class="easyui-textbox" required="false" id="namaKabupaten">	</div>
			
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

/* function untuk list data*/
	function retrieve() {		
		var jsonurl = 'kabupatenListAll.htm?param=' + $('#idSearch').val()+'&param2='+$('#idSearch2').val();
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
	
	/* ============FORM FUNCTION ==========*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah kabupatentambah');
		$('#fm').form('clear');
		url = 'kabupatenAdd.htm';
		/*var t = $('#namax');
		upperCase($('#namax'));
		upperCase($('#userId'));
		branchcode = ''; //combobox tidak ada default
		addComboBranch();*/
		onAdd();
	}

	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit kabupatenedit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'kabupatenEdit.htm?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			/*upperCase($('#namax'));
			branchcode = row.branchCode;
			addComboBranch();*/
			onEdit();
		}
	}

	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil kabupatentampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'kabupatenEdit.htm?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			/*upperCase($('#namax'));
			branchcode = row.branchCode;
			addComboBranch();*/
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('kabupatenDelete.htm', {
								kodeProvinsi : row.kodeProvinsi,
								kodeKabupaten : row.kodeKabupaten
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
					alert("Simpan Gagal");
					$.messager.show({
						title : 'Error',
						msg : "Simpan Gagal"
					});
				} else {
					alert("Simpan Sukses");
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
					//var kd = $('#kode').val();
					//$('#kodeProvinsi').val(kd);
					//                        var jsonurl =  'provinsiAjax.htm?param='+$('#kodeProvinsi').val();
					$('#dg').datagrid({
						url : jsonurl
					});
				}
			}
		});
	}
	
	
	/* ================TAMBAHAN=================*/
	var kodeProvinsi;
	//untuk mengisi combobox kode cabang keseluruhan dengan default value sesuai parameter
	
	$("document").ready(function(){
		$('#idSearch').combobox({
			url : 'comboProvinsi.htm?param=' + branchcode,
			valueField : 'id',
			textField : 'text'
		});
	});
	
	
	/*Untuk membuat menjadi huruf besar semua */
	function upperCase(t) {
		t.textbox('textbox').bind('keyup', function(e) {
			$(this).val($(this).val().toUpperCase());
		});
	}
	
	/*inputan readonly atau tidak saat onShow */
	function onShow() {
		//list button
		//$('#userId').textbox('readonly', true);
		//form button
		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		//list button
		//$('#userId').textbox('readonly', false);
		
		//form button
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		//list button
		//$('#userId').textbox('readonly', true);
		
		//form button
		$('#btnSave').linkbutton('enable');
	}

	
</script>
