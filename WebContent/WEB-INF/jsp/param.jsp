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
                    <label>Value</label> : <input name="Value" type="text" id="Value" size="30" maxlength="30"><br>
                    <label>Key</label> : <input name="Key" type="text" id="Key" size="30" maxlength="30"><br>

                <div id="btn">     
                    <%@ include file="searchButton.jsp" %>  
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  

<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="PARAM" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="value" width="100"sortable="true">Value</th> 
                    <th field="key" width="100"sortable="true">Key</th> 
                    <th field="description" width="100"sortable="true">Description</th> 
                    <th field="createBy" width="100"sortable="true">CreateBy</th> 
                    <th field="createDate" width="100"sortable="true">CreateDate</th> 
                    <th field="updateBy" width="100"sortable="true">UpdateBy</th> 
                    <th field="updateDate" width="100"sortable="true">UpdateDate</th> 
                    <th field="versi" width="100"sortable="true">Versi</th> 
                     
                </tr>
            </thead>
        </table>        
                  
        <div id="toolbar">
        <%@ include file="toolbar.jsp" %>          
        	<td align="right">
        	<!-- 
        		 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-authorize" plain="true" onclick="" id="btnAdd" ><%=otorize %></a>
        	-->
        	</td>
        	</tr>
        </table>
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
    
          
<!-- ************************** FORM ******************************************** -->
	<div id="dlg" class="easyui-dialog"	style="width: 750px;  padding: 10px 20px" closed="true"	buttons="#dlg-buttons" data-options="modal:true">
		<div class="ftitle">PARAM</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Value</label> :<input name="value"	class="easyui-textbox" required="false" id="value">	</div>
                    <div class="fitem">	<label>Key</label> :<input name="key"	class="easyui-textbox" required="false" id="key">	</div>
                    <div class="fitem">	<label>Description</label> :<input name="description"	class="easyui-textbox" required="false" id="description">	</div>
			
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
		var jsonurl = 'paramListAll.htm?'+'Value='+$('#Value').val()+"&"+'Key='+$('#Key').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== paramtambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'paramAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
/* ---- paramedit*/
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'paramEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
/*-- paramtampil*/
	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'paramEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('paramDelete.htm', {
								Key : row.key,
								userId:"${userId}"//SESUAIKAN Id=>huruf depan BEsar row.Id==>huruf depan kecil
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
			textField : 'text'
		});
		branchcode = '';
	}
	
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
$('#value').textbox('readonly', true);
$('#key').textbox('readonly', true);
$('#description').textbox('readonly', true);

		//form button
		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		//list button
		//$('#userId').textbox('readonly', false);		
$('#value').textbox('readonly', false);
$('#key').textbox('readonly', false);
$('#description').textbox('readonly', false);
		
		//form button
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		//list button
		//$('#userId').textbox('readonly', true);	
$('#value').textbox('readonly', false);
$('#key').textbox('readonly', true);
$('#description').textbox('readonly', false);
	
		//form button
		$('#btnSave').linkbutton('enable');
	}

	
</script>
