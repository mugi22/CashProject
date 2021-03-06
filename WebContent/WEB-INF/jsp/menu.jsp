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
                    <label>MenuId</label> : <input name="MenuId" type="text" id="MenuId" size="30" maxlength="30"><br>
                    <label>MenuName</label> : <input name="MenuName" type="text" id="MenuName" size="30" maxlength="30"><br>
                    <label>ParentId</label> : <input name="ParentId" type="text" id="ParentId" size="30" maxlength="30"><br>

                <div id="btn">     
                    <%@ include file="searchButton.jsp" %>
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  

<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="MENU" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    
                    <th field="menuId" width="100"sortable="true">MenuId</th> 
                    <th field="menuName" width="100"sortable="true">MenuName</th> 
                    <th field="haveChild" width="100"sortable="true">HaveChild</th> 
                    <th field="parentId" width="100"sortable="true">ParentId</th> 
                    <th field="noUrut" width="100"sortable="true">NoUrut</th>                   
                    <th field="menuPage" width="100"sortable="true">MenuPage</th> 
                    <th field="screenClass" width="100"sortable="true">ScreenClass</th> 
                    <th field="isUsingGroovy" width="100"sortable="true">IsUsingGroovy</th> 
                    <th field="params" width="100"sortable="true">Params</th> 
                     
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
		<div class="ftitle">MENU</div>
		<form id="fm" method="post" novalidate>
					<div class="fitem">	<label>MenuId</label> :<input name="menuId"	class="easyui-textbox" required="false" id="menuId">	</div>
                    <div class="fitem">	<label>MenuName</label> :<input name="menuName"	class="easyui-textbox" required="false" id="menuName">	</div>
                    <div class="fitem">	<label>ParentId</label> :<input name="parentId"	class="easyui-textbox" required="false" id="parentId">	</div>
                    <div class="fitem">	<label>MenuPage</label> :<input name="menuPage"	class="easyui-textbox" required="false" id="menuPage">	</div>
                    
                    <div class="fitem">	<label>HaveChild</label> :<input name="haveChild"	class="easyui-textbox" required="false" id="haveChild">	</div>
                    <div class="fitem">	<label>NoUrut</label> :<input name="noUrut"	class="easyui-textbox" required="false" id="noUrut">	</div>
                    <div class="fitem"> <label>ScreenClass</label> :<input name="screenClass"	class="easyui-textbox"  id="screenClass">	</div>
                    <div class="fitem">	<label>IsUsingGroovy</label> :<input name="isUsingGroovy"	class="easyui-textbox"  id="isUsingGroovy">	</div>
	                <div class="fitem">	<label>Params</label> :<input name="params"	class="easyui-textbox"  id="params">	</div>
			
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
		var jsonurl = 'menuListAll.htm?'+
'MenuId='+$('#MenuId').val()+"&"+'MenuName='+$('#MenuName').val()+"&"+'ParentId='+$('#ParentId').val()+"&"+"userId="+"${userId}";
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
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'menuAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}

	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'menuEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
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
			url = 'menuEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('menuDelete.htm', {
								MenuId : row.menuId,
								userId:"${userId}"//SESUAIKAN 
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
		$('#params').textbox('readonly', true);
		$('#menuId').textbox('readonly', true);
		$('#haveChild').textbox('readonly', true);
		$('#menuName').textbox('readonly', true);
		$('#noUrut').textbox('readonly', true);
		$('#parentId').textbox('readonly', true);
		$('#menuPage').textbox('readonly', true);
		$('#screenClass').textbox('readonly', true);
		$('#isUsingGroovy').textbox('readonly', true);

		//form button
		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		//list button
		//$('#userId').textbox('readonly', false);		
$('#params').textbox('readonly', false);
$('#menuId').textbox('readonly', false);
$('#haveChild').textbox('readonly', false);
$('#menuName').textbox('readonly', false);
$('#noUrut').textbox('readonly', false);
$('#parentId').textbox('readonly', false);
$('#menuPage').textbox('readonly', false);
$('#screenClass').textbox('readonly', false);
$('#isUsingGroovy').textbox('readonly', true);
		
		//form button
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		//list button
		//$('#userId').textbox('readonly', true);	
$('#params').textbox('readonly', false);
$('#menuId').textbox('readonly', true);
$('#haveChild').textbox('readonly', false);
$('#menuName').textbox('readonly', false);
$('#noUrut').textbox('readonly', false);
$('#parentId').textbox('readonly', false);
$('#menuPage').textbox('readonly', false);
$('#screenClass').textbox('readonly', false);
$('#isUsingGroovy').textbox('readonly', false);
	
		//form button
		$('#btnSave').linkbutton('enable');
	}

	
</script>
