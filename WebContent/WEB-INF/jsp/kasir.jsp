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
             		<label>UserId</label> : <input name="UserId" type="text" id="UserId" size="30" maxlength="30"><br>
              		<label>BranchCode</label> : <input name="BranchCode" type="text" id="BranchCode" size="30" maxlength="30"><br> 
              		<label>Norek</label> : <input name="Norek" type="text" id="Norek" size="30" maxlength="30"><br>         
                    <label>Status</label> : <input name="Status" type="text" id="Status" size="30" maxlength="30"><br>
                <div id="btn">     
                    <%@ include file="searchButton.jsp" %>    
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
                	<th field="userId" width="100"sortable="true">User Id</th> 
                	<th field="userName" width="100"sortable="true">Name</th>
                	<th field="branchCode" width="100"sortable="true">BranchCode</th>
                	<th field="branchName" width="100"sortable="true">Branch Name</th>
                	<th field="norek" width="100"sortable="true">Norek</th>
                   	<th field="status" width="100"sortable="true">Status</th> 
                    <th field="LimitAmount" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">LimitAmount</th> 
                    <th field="branchMapping" width="100"sortable="true">BranchMapping</th> 
                    <th field="ccy" width="100"sortable="true">Ccy</th> 
                    <th field="amount" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">Amount</th> 
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
		<div class="ftitle">KASIR</div>
		<form id="fm" method="post" novalidate>
					 <div class="fitem">	
					 	<label>User Id</label> :<input name="userId"	style="width: 80px"  class="easyui-textbox" required="true" id="userId">	
					 	<input type="button" value="Check" style="width: 50px" onclick="check();">
					 							<input name="userName"	class="easyui-textbox" id="userName">	
					 </div>
					 <div class="fitem"><label>Branch Code</label> :<input name="branchCode"	style="width: 60px"class="easyui-textbox" required="true" id="branchCode">	
					 							<input name="branchName"	class="easyui-textbox"  id="branchName" style="width: 200px" >	
					 </div>
					 <div class="fitem">	
					 		<label>No Rekening</label> :<input name="norek"	class="easyui-textbox" required="false" id="norek">	
					 		<input type="button" value="Check Rekening" style="width: 100px" onclick="checkRekening();">
					 		<input name="namaNorek"	class="easyui-textbox"  id="namaNorek">	
					 </div>
                    <div class="fitem">	<label>Status</label> :<input name="status"	class="easyui-textbox"  id="status">	</div>
                    <div class="fitem">	<label>Limit Amount</label> :<input name="limitAmount"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="limitAmount">	</div>
                    <div class="fitem">	<label>Branch Mapping</label> :<input name="branchMapping"	class="easyui-textbox"  id="branchMapping">	</div>
                    <div class="fitem">	<label>CCY</label> :<input name="ccy"	class="easyui-textbox"  id="ccy">	</div>
                    <div class="fitem">	<label>Amount</label> :<input name="amount"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','"  id="amount">	</div>
                    
                    
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


function check(){
	var param = window.location.search.replace("?", "");
	var nik = $('#userId').val();
	$.ajax({
		url:'cekUserBaranch.htm?nik='+nik+'&'+param,
		 success	: function(result){		
			if(result==''||result=='{}'){
				alert("User Tidak Ditemukan");
			}else{
				var x = JSON.parse(result);
				 $('#userName').textbox('setValue',x.name);
				 $('#branchCode').textbox('setValue',x.branchCode);
				 $('#norek').textbox('setValue',x.branchCode);
				 $('#branchName').textbox('setValue',x.branchName);
				 
				 $('#status').textbox('readonly', true);
                 $('#limitAmount').textbox('readonly', true);
                 $('#branchMapping').textbox('readonly', true);
                 $('#ccy').textbox('readonly', true);
                 $('#amount').textbox('readonly', true);
                 $('#branchCode').textbox('readonly', true);
                 $('#userId').textbox('readonly', true);
                 $('#norek').textbox('readonly', false);
			}
			 
			
		 }
	});
}

//"namaNorek"

	function checkRekening() {
		var param = window.location.search.replace("?", "");
		var norek = $('#norek').val();
		$.ajax({
			url : 'cekRekening.htm?norek=' + norek + '&' + param,
			success : function(result) {
				if (result == '' || result == "{}") {
					alert("rek Tidak Ditemukan");
				} else {
					var x = JSON.parse(result);
					$('#namaNorek').textbox('setValue', x.description);
					$('#status').textbox('readonly', false);
					$('#limitAmount').textbox('readonly', false);
					$('#branchMapping').textbox('readonly', false);
					$('#ccy').textbox('readonly', false);
					$('#amount').textbox('readonly', false);
				}
			}
		});
	}

	function test(t) {
		t.textbox('textbox').bind('blur', function(e) {
			/*$(this).val($(this).val().toUpperCase());*/
			//alert("oooooooooooooooooo ............");
		});
	}
	$("document").ready(function() {
		$("#btnAdd").linkbutton('${btnAdd}');
		$("#btnEdit").linkbutton('${btnEdit}');
		$("#btnDelete").linkbutton('${btnDelete}');
		$("#btnShow").linkbutton('${btnShow}');
		$('#userName').textbox('readonly', true);
		$('#branchName').textbox('readonly', true);
	});

	
	function retrieve() {
		var jsonurl = 'kasirListAll.htm?' + 'Status=' + $('#Status').val()
				+ "&" + 'BranchCode=' + $('#BranchCode').val() + "&"
				+ 'UserId=' + $('#UserId').val() + "&" + 'Norek='
				+ $('#Norek').val() + "&" + "userId=" + "${userId}";
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
		url = 'kasirAdd.htm?' + "UID=" + "${userId}";
		onAdd();
	}
	
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'kasirEdit.htm?' + "UID=" + "${userId}";
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
			url = 'kasirEdit.htm?' + "userId=" + "${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
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
								UID : "${userId}"
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
		$('#namaNorek').textbox('readonly', true);
		$('#btnSave').linkbutton('disable');
	}

	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		$('#status').textbox('readonly', true);
		$('#limitAmount').textbox('readonly', true);
		$('#branchMapping').textbox('readonly', true);
		$('#ccy').textbox('readonly', true);
		$('#amount').textbox('readonly', true);
		$('#branchCode').textbox('readonly', true);
		$('#userId').textbox('readonly', false);
		$('#norek').textbox('readonly', true);
		$('#btnSave').linkbutton('enable');
	}

	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		$('#status').textbox('readonly', false);
		$('#limitAmount').textbox('readonly', false);
		$('#branchMapping').textbox('readonly', false);
		$('#ccy').textbox('readonly', false);
		$('#amount').textbox('readonly', false);
		$('#branchCode').textbox('readonly', true);
		$('#userId').textbox('readonly', true);
		$('#norek').textbox('readonly', false);
		$('#btnSave').linkbutton('enable');
	}
</script>
