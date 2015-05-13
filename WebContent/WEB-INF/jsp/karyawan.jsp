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
                    <tr>
<td><label style="width: 150px;"><%=properties.getProperty("karyawan.Nama")%></label> : <input name="Nama" type="text" id="Nama" size="30" maxlength="30"></td>
</tr>
                    <tr>
<td><label style="width: 150px;"><%=properties.getProperty("karyawan.UnitKerja")%></label> : <input name="UnitKerja" type="text" id="UnitKerja" size="30" maxlength="30"></td>
</tr>
                    <tr>
<td><label style="width: 150px;"><%=properties.getProperty("karyawan.Nik")%></label> : <input name="Nik" type="text" id="Nik" size="30" maxlength="30"></td>
</tr>

				 <tr>
	            	<td colspan="1">
	                <div id="btn">     
	                    <%@ include file="searchButton.jsp" %>
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
        <table id="dg" title="KARYAWAN" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"  data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="nama" width="100"sortable="true"><%=properties.getProperty("karyawan.Nama")%></th> 
                    <th field="unitKerja" width="100"sortable="true"><%=properties.getProperty("karyawan.UnitKerja")%></th> 
                    <th field="nik" width="100"sortable="true"><%=properties.getProperty("karyawan.Nik")%></th> 
                     
                </tr>
            </thead>
        </table>  
          <div id="toolbar">
        <table width="100%">
        	<tr>
        	<td align="left">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()" id="btnAdd" ><%= add %></a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()" id="btnEdit"><%= edit %></a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete()" id="btnDelete"><%= delete %></a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doShow()" id="btnShow"><%= show %></a>
        	</td>
        	<td align="right">
        	<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-authorize" plain="true" onclick="" id="btnAdd" ><%=otorize %></a>
        	 -->
        	</td>
        	</tr>
        </table>
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
    
          
<!-- ************************** FORM ******************************************** -->
	<div id="dlg" class="easyui-dialog"	style="width: 750px;  padding: 10px 20px" closed="true"	buttons="#dlg-buttons" data-options="modal:true">
		<div class="ftitle">KARYAWAN</div>
		<form id="fm" method="post" novalidate>
		<table align="center"> 
                    <tr><td><div class="fitem">	<label><%=properties.getProperty("karyawan.Nama")%></label> :<input name="nama"	class="easyui-textbox" id="nama"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label><%=properties.getProperty("karyawan.UnitKerja")%></label> :<input name="unitKerja"	class="easyui-textbox" id="unitKerja"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label><%=properties.getProperty("karyawan.Nik")%></label> :<input name="nik"	class="easyui-textbox" id="nik"></td></tr>	</div>
			
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
		var jsonurl = 'karyawanListAll.htm?'+
'Nama='+$('#Nama').val()+"&"+'UnitKerja='+$('#UnitKerja').val()+"&"+'Nik='+$('#Nik').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== karyawantambah*/

	function doAdd() { 
		idRequired(true);
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'karyawanAdd.htm?'+"userId="+"${userId}";
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
			url = 'karyawanEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
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
			url = 'karyawanEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
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
							$.post('karyawanDelete.htm', {
							                    nama : row.nama,
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
                    $('#nama').textbox({   required: t});


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
		                    $('#nama').textbox('readonly', true);
                    $('#unitKerja').textbox('readonly', true);
                    $('#nik').textbox('readonly', true);

		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		                    $('#nama').textbox('readonly', false);
                    $('#unitKerja').textbox('readonly', false);
                    $('#nik').textbox('readonly', false);
		
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		                    $('#nama').textbox('readonly', true);
                    $('#unitKerja').textbox('readonly', false);
                    $('#nik').textbox('readonly', false);
	
		$('#btnSave').linkbutton('enable');
	}
/*===============================================REPORT==================================*/
function doCetak(){
		var repUrl = 'karyawanReport.htm?'+
					  'Nama='+$('#Nama').val()+"&"+'UnitKerja='+$('#UnitKerja').val()+"&"+'Nik='+$('#Nik').val()+"&"+"userId="+"${userId}";;
		var s = window.location.search.replace("?", "");
		window.open(repUrl+"&"+s,
				"_blank", 
				"toolbar=no, scrollbars=yes, resizable=yes,	directories=no, location=no, \
				 menubar=no, status=no,'");
	}

	
</script>
