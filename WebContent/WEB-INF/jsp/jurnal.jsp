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
                    <label>Jurnal Id</label> : <input name="JurnalId" type="text" id="JurnalId" size="30" maxlength="30"><br>
                    <label>Branch Code</label> : <input name="BranchCode" type="text" id="BranchCode" size="30" maxlength="30"><br>
                    <label>Tgl Transaksi</label> : <input  name="TglTransaksi" type="text" id="TglTransaksi" size="30" maxlength="30" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"><br>
                    
                   
                    
                    
                    
                    <label>Kode Transaksi</label> : <input name="KodeTransaksi" type="text" id="KodeTransaksi" size="30" maxlength="30"><br>

                <div id="btn">     
                    <%@ include file="searchButton.jsp" %>  
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  


<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="JURNAL" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="jurnalId" width="100"sortable="true">JurnalId</th> 
                	<th field="branchCode" width="100"sortable="true">BranchCode</th>
                    <th field="kodeTransaksi" width="100"sortable="true">KodeTransaksi</th> 
                    <th field="tglPosting" width="100"sortable="true">TglPosting</th>
                    <th field="tglTransaksi" width="100"sortable="true">TglTransaksi</th> 
                    <th field="mobileBranchCode" width="100"sortable="true">MobileBranchCode</th> 
                    <th field="keterangan" width="100"sortable="true">Keterangan</th> 
                    <th field="authorized" width="100"sortable="true">Authorized</th> 
                    <th field="refId" width="100"sortable="true">RefId</th> 
                    <th field="reverse" width="100"sortable="true">Reverse</th> 
                    <th field="respCode" width="100"sortable="true">RespCode</th>                      
                    <th field="authorizedBy" width="100"sortable="true">AuthorizedBy</th> 
                    <th field="authorizedDate" width="100"sortable="true">AuthorizedDate</th> 
                     
                     
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
		<div class="ftitle">JURNAL</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Jurnal Id</label> :<input name="jurnalId"	class="easyui-numberbox" required="true" id="jurnalId">	</div>
                    <div class="fitem">	<label>Branch Code</label> :<input name="branchCode"	class="easyui-textbox" required="true" id="branchCode">	</div>
                    <div class="fitem">	<label>Kode Transaksi</label> :<input name="kodeTransaksi"	class="easyui-textbox" required="true" id="kodeTransaksi">	</div>
                    <div class="fitem">	<label>Tgl Posting</label> :<input name="tglPosting"	 id="tglPosting" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser">	</div>
                    <div class="fitem">	<label>Tgl Transaksi</label> :<input name="tglTransaksi" required="true" id="tglTransaksi" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser">	</div>
                    <div class="fitem">	<label>Keterangan</label> :<input name="keterangan"	class="easyui-textbox"  id="keterangan">	</div>
                    <div class="fitem">	<label>Authorized By</label> :<input name="authorizedBy"	class="easyui-textbox"  id="authorizedBy">	</div>
                    <div class="fitem">	<label>Authorized Date</label> :<input name="authorizedDate"	class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"  id="authorizedDate">	</div>
                    <div class="fitem">	<label>MobileBranch Code</label> :<input name="mobileBranchCode"	class="easyui-textbox"  id="mobileBranchCode">	</div>
                    <div class="fitem">	<label>Authorized</label> :<input name="authorized"	class="easyui-textbox" " id="authorized">	</div>
                    <div class="fitem">	<label>RefId</label> :<input name="refId"	class="easyui-textbox" id="refId">	</div>
                    <div class="fitem">	<label>Reverse</label> :<input name="reverse"	class="easyui-textbox" id="reverse">	</div>
                    <div class="fitem">	<label>RespCode</label> :<input name="respCode"	class="easyui-textbox"  id="respCode">	</div>
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
		var jsonurl = 'jurnalListAll.htm?'+
'KodeTransaksi='+$('#KodeTransaksi').val()+"&"+'TglTransaksi='+$('#TglTransaksi').val()+"&"+'JurnalId='+$('#JurnalId').val()+"&"+'BranchCode='+$('#BranchCode').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== jurnaltambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'jurnalAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'jurnalEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
	function doShow() {
		doEdit();
		onShow();
	/*
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'jurnalEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
		*/
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('jurnalDelete.htm', {
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
		            $('#kodeTransaksi').textbox('readonly', true);
                    $('#authorizedBy').textbox('readonly', true);
                    $('#authorizedDate').textbox('readonly', true);
                    $('#tglTransaksi').textbox('readonly', true);
                    $('#mobileBranchCode').textbox('readonly', true);
                    $('#keterangan').textbox('readonly', true);
                    $('#jurnalId').textbox('readonly', true);
                    $('#authorized').textbox('readonly', true);
                    $('#refId').textbox('readonly', true);
                    $('#reverse').textbox('readonly', true);
                    $('#respCode').textbox('readonly', true);
                    $('#tglPosting').textbox('readonly', true);
                    $('#branchCode').textbox('readonly', true);
					$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		            $('#kodeTransaksi').textbox('readonly', false);
                    $('#authorizedBy').textbox('readonly', false);
                    $('#authorizedDate').textbox('readonly', false);
                    $('#tglTransaksi').textbox('readonly', false);
                    $('#mobileBranchCode').textbox('readonly', false);
                    $('#keterangan').textbox('readonly', false);
                    $('#jurnalId').textbox('readonly', false);
                    $('#authorized').textbox('readonly', false);
                    $('#refId').textbox('readonly', false);
                    $('#reverse').textbox('readonly', false);
                    $('#respCode').textbox('readonly', false);
                    $('#tglPosting').textbox('readonly', false);
                    $('#branchCode').textbox('readonly', false);		
					$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		            $('#kodeTransaksi').textbox('readonly', false);
                    $('#authorizedBy').textbox('readonly', false);
                    $('#authorizedDate').textbox('readonly', false);
                    $('#tglTransaksi').textbox('readonly', false);
                    $('#mobileBranchCode').textbox('readonly', false);
                    $('#keterangan').textbox('readonly', false);
                    $('#jurnalId').textbox('readonly', true);
                    $('#authorized').textbox('readonly', false);
                    $('#refId').textbox('readonly', false);
                    $('#reverse').textbox('readonly', false);
                    $('#respCode').textbox('readonly', false);
                    $('#tglPosting').textbox('readonly', false);
                    $('#branchCode').textbox('readonly', false);	
					$('#btnSave').linkbutton('enable');
	}
/*===============================================REPORT==================================*/
function doCetak(){
		var repUrl = 'jurnalReport.htm?'+
					  'KodeTransaksi='+$('#KodeTransaksi').val()+"&"+'TglTransaksi='+$('#TglTransaksi').val()+"&"+'JurnalId='+$('#JurnalId').val()+"&"+'BranchCode='+$('#BranchCode').val()+"&"+"userId="+"${userId}";;
		var s = window.location.search.replace("?", "");
		window.open(repUrl+"&"+s,
				"_blank", 
				"toolbar=no, scrollbars=yes, resizable=yes,	directories=no, location=no, \
				 menubar=no, status=no,'");
	}

	
</script>
