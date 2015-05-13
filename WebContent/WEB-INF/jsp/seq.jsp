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
            <form name="FREG" id="formCari" method="post" action="#" style="width: 80%;" >   
            <table align="center" >  	                       
                    <tr><td><label style="width: 150px;"><%=properties.getProperty("lastLogIn")%></label> : <input name="LastLogIn" type="text" id="LastLogIn" size="30" maxlength="30"></td></tr>
                    <tr><td><label style="width: 150px;"><%=properties.getProperty("keterangan")%></label> : <input name="Keterangan" type="text" id="Keterangan" size="30" maxlength="30"></td></tr>
                    <tr><td><label style="width: 150px;"><%=properties.getProperty("seqName")%></label> : <input name="SeqName" type="text" id="SeqName" size="30" maxlength="30"></td></tr>
                    <tr><td><label style="width: 150px;"><%=properties.getProperty("seqNum")%></label> : <input name="SeqNum" type="text" id="SeqNum" size="30" maxlength="30"></td></tr>
                    <tr><td><label style="width: 150px;"><%=properties.getProperty("tarif")%></label> : <input name="Tarif" type="text" id="Tarif" size="30" maxlength="30"></td></tr>

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
        <table id="dg" title="SEQ" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"  data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="lastLogIn" width="100"sortable="true">LastLogIn</th> 
                    <th field="keterangan" width="100"sortable="true">Keterangan</th> 
                    <th field="seqName" width="100"sortable="true">SeqName</th> 
                    <th field="seqNum" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.seqNum,0,'.',','); }"align="right">SeqNum</th> 
                    <th field="tarif" width="100"sortable="true"data-options="formatter:function(value, row){return accounting.formatNumber(row.tarif,0,'.',','); }"align="right">Tarif</th> 
                     
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
		<div class="ftitle">SEQ</div>
		<form id="fm" method="post" novalidate>
		<table align="center"> 
                    <tr><td><div class="fitem">	<label>LastLogIn</label> :<input name="lastLogIn"	class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" id="lastLogIn"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>Keterangan</label> :<input name="keterangan"	class="easyui-textbox" id="keterangan"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>SeqName</label> :<input name="seqName"	class="easyui-textbox" id="seqName"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>SeqNum</label> :<input name="seqNum"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="seqNum"></td></tr>	</div>
                    <tr><td><div class="fitem">	<label>Tarif</label> :<input name="tarif"	class="easyui-numberbox" data-options="min:0,precision:0,groupSeparator:','" id="tarif"></td></tr>	</div>
			
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
		var jsonurl = 'seqListAll.htm?'+
'LastLogIn='+$('#LastLogIn').val()+"&"+'Keterangan='+$('#Keterangan').val()+"&"+'SeqName='+$('#SeqName').val()+"&"+'SeqNum='+$('#SeqNum').val()+"&"+'Tarif='+$('#Tarif').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== seqtambah*/

	function doAdd() { 
		idRequired(true);
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'seqAdd.htm?'+"userId="+"${userId}";
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
			url = 'seqEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
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
			url = 'seqEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
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
							$.post('seqDelete.htm', {
							                    seqName : row.seqName,
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
                    $('#seqName').textbox({   required: t});


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
		                    $('#lastLogIn').textbox('readonly', true);
                    $('#keterangan').textbox('readonly', true);
                    $('#seqName').textbox('readonly', true);
                    $('#seqNum').textbox('readonly', true);
                    $('#tarif').textbox('readonly', true);

		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		                    $('#lastLogIn').textbox('readonly', false);
                    $('#keterangan').textbox('readonly', false);
                    $('#seqName').textbox('readonly', false);
                    $('#seqNum').textbox('readonly', false);
                    $('#tarif').textbox('readonly', false);
		
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		                    $('#lastLogIn').textbox('readonly', false);
                    $('#keterangan').textbox('readonly', false);
                    $('#seqName').textbox('readonly', true);
                    $('#seqNum').textbox('readonly', false);
                    $('#tarif').textbox('readonly', false);
	
		$('#btnSave').linkbutton('enable');
	}
/*===============================================REPORT==================================*/
function doCetak(){
		var repUrl = 'seqReport.htm?'+
					  'LastLogIn='+$('#LastLogIn').val()+"&"+'Keterangan='+$('#Keterangan').val()+"&"+'SeqName='+$('#SeqName').val()+"&"+'SeqNum='+$('#SeqNum').val()+"&"+'Tarif='+$('#Tarif').val()+"&"+"userId="+"${userId}";;
		var s = window.location.search.replace("?", "");
		window.open(repUrl+"&"+s,
				"_blank", 
				"toolbar=no, scrollbars=yes, resizable=yes,	directories=no, location=no, \
				 menubar=no, status=no,'");
	}

	
</script>
