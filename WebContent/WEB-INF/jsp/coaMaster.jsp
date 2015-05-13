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
                    <label>Groups</label> : <input name="Groups" type="text" id="Groups" size="30" maxlength="30"><br>
                    <label>NoCoa</label> : <input name="NoCoa" type="text" id="NoCoa" size="30" maxlength="30"><br>
                    <label>ParentCoa</label> : <input name="ParentCoa" type="text" id="ParentCoa" size="30" maxlength="30"><br>
                    <label>Lvl</label> : <input name="Lvl" type="text" id="Lvl" size="30" maxlength="30"><br>

                <div id="btn">     
                   <%@ include file="searchButton.jsp" %>   
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  


<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="COAMASTER" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="description" width="100"sortable="true">Description</th> 
                    <th field="groups" width="100"sortable="true">Groups</th> 
                    <th field="noCoa" width="100"sortable="true">NoCoa</th> 
                    <th field="parentCoa" width="100"sortable="true">ParentCoa</th> 
                    <th field="lvl" width="100"sortable="true">Lvl</th> 
                     
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
		<div class="ftitle">COAMASTER</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Description</label> :<input name="description"	class="easyui-textbox" required="false" id="description">	</div>
                    <div class="fitem">	<label>Groups</label> :<input name="groups"	class="easyui-textbox" required="false" id="groups">	</div>
                    <div class="fitem">	<label>NoCoa</label> :<input name="noCoa"	class="easyui-textbox" required="false" id="noCoa">	</div>
                    <div class="fitem">	<label>ParentCoa</label> :<input name="parentCoa"	class="easyui-textbox" required="false" id="parentCoa">	</div>
			
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
		var jsonurl = 'coaMasterListAll.htm?'+
'Description='+$('#Description').val()+"&"+'Groups='+$('#Groups').val()+"&"+'NoCoa='+$('#NoCoa').val()+"&"+'ParentCoa='+$('#ParentCoa').val()+"&"+'Lvl='+$('#Lvl').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== coamastertambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'coaMasterAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'coaMasterEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
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
			url = 'coaMasterEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('coaMasterDelete.htm', {
							                    noCoa : row.noCoa,
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
                    $('#groups').textbox('readonly', true);
                    $('#noCoa').textbox('readonly', true);
                    $('#parentCoa').textbox('readonly', true);
                    $('#lvl').textbox('readonly', true);

		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		                    $('#description').textbox('readonly', false);
                    $('#groups').textbox('readonly', false);
                    $('#noCoa').textbox('readonly', false);
                    $('#parentCoa').textbox('readonly', false);
                    $('#lvl').textbox('readonly', false);
		
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		                    $('#description').textbox('readonly', true);
                    $('#groups').textbox('readonly', true);
                    $('#noCoa').textbox('readonly', true);
                    $('#parentCoa').textbox('readonly', true);
                    $('#lvl').textbox('readonly', true);
	
		$('#btnSave').linkbutton('enable');
	}

	
</script>
