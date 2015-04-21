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

   <link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <link rel="stylesheet" type="text/css" href="css/color.css">
        <link rel="stylesheet" type="text/css" href="css/searchcss.css">     
        
        <script type="text/javascript" src="css/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="css/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="css/formater.js"></script>
		<script type="text/javascript" src="css/accounting.min.js"></script>
		<script type="text/javascript" src="css/myalert.js"></script>
		 <script type="text/javascript" src="css/my.js"></script>

<title>User</title>
</head>
<body>

<!-- ******************************FORM PENCARIAN******************************* -->   
        <div id="div2">
            <form name="FREG" id="formCari" method="post" action="#"  >                 
                    <label>Status</label> : <input name="Status" type="text" id="Status" size="30" maxlength="30"><br>
                    <label>BranchCode</label> : <input name="BranchCode" type="text" id="BranchCode" size="30" maxlength="30"><br>
                    <label>UserId</label> : <input name="UserId" type="text" id="UserId" size="30" maxlength="30"><br>
                    <label>Norek</label> : <input name="Norek" type="text" id="Norek" size="30" maxlength="30"><br>

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
        <table id="dg" title="KASIR" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="status" width="100"sortable="true">Status</th> 
                    <th field="limitAmount" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">LimitAmount</th> 
                    <th field="branchMapping" width="100"sortable="true">BranchMapping</th> 
                    <th field="ccy" width="100"sortable="true">Ccy</th> 
                    <th field="amount" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">Amount</th> 
                    <th field="branchCode" width="100"sortable="true">BranchCode</th> 
                    <th field="userId" width="100"sortable="true">UserId</th> 
                    <th field="norek" width="100"sortable="true">Norek</th> 
                     
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
		<div class="ftitle">KASIR</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Status</label> :<input name="status"	class="easyui-textbox" required="false" id="status">	</div>
                    <div class="fitem">	<label>LimitAmount</label> :<input name="limitAmount"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" required="false" id="limitAmount">	</div>
                    <div class="fitem">	<label>BranchMapping</label> :<input name="branchMapping"	class="easyui-textbox" required="false" id="branchMapping">	</div>
                    <div class="fitem">	<label>Ccy</label> :<input name="ccy"	class="easyui-textbox" required="false" id="ccy">	</div>
                    <div class="fitem">	<label>Amount</label> :<input name="amount"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" required="false" id="amount">	</div>
                    <div class="fitem">	<label>BranchCode</label> :<input name="branchCode"	class="easyui-textbox" required="false" id="branchCode">	</div>
                    <div class="fitem">	<label>UserId</label> :<input name="userId"	class="easyui-textbox" required="false" id="userId">	</div>
                    <div class="fitem">	<label>Norek</label> :<input name="norek"	class="easyui-textbox" required="false" id="norek">	</div>
			
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
		var jsonurl = 'kasirListAll.htm?'+
'Status='+$('#Status').val()+"&"+'BranchCode='+$('#BranchCode').val()+"&"+'UserId='+$('#UserId').val()+"&"+'Norek='+$('#Norek').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== kasirtambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'kasirAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'kasirEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
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
			url = 'kasirEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('kasirDelete.htm', {
							                    userId : row.userId,
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
		                    $('#status').textbox('readonly', true);
                    $('#limitAmount').textbox('readonly', true);
                    $('#branchMapping').textbox('readonly', true);
                    $('#ccy').textbox('readonly', true);
                    $('#amount').textbox('readonly', true);
                    $('#branchCode').textbox('readonly', true);
                    $('#userId').textbox('readonly', true);
                    $('#norek').textbox('readonly', true);

		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		                    $('#status').textbox('readonly', false);
                    $('#limitAmount').textbox('readonly', false);
                    $('#branchMapping').textbox('readonly', false);
                    $('#ccy').textbox('readonly', false);
                    $('#amount').textbox('readonly', false);
                    $('#branchCode').textbox('readonly', false);
                    $('#userId').textbox('readonly', false);
                    $('#norek').textbox('readonly', false);
		
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		                    $('#status').textbox('readonly', true);
                    $('#limitAmount').textbox('readonly', true);
                    $('#branchMapping').textbox('readonly', true);
                    $('#ccy').textbox('readonly', true);
                    $('#amount').textbox('readonly', true);
                    $('#branchCode').textbox('readonly', true);
                    $('#userId').textbox('readonly', true);
                    $('#norek').textbox('readonly', true);
	
		$('#btnSave').linkbutton('enable');
	}

	
</script>
