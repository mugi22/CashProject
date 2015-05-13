<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

	.numberbox .textbox-text{
	  text-align: right;
	  color: black;
	}
</style>

<%@ include file="include.jsp" %>

<title>User</title>
</head>
<body>

<!-- ******************************FORM PENCARIAN******************************* -->   
        <div id="div2">
            <form name="FREG" id="formCari" method="post" action="#"  >                 
                    <label>Grade</label> : <input name="Grade" type="text" id="Grade" size="30" maxlength="30"><br>
                    <label>IdTarif</label> : <input name="IdTarif" type="text" id="IdTarif" size="30" maxlength="30"><br>

                <div id="btn">     
                    <%@ include file="searchButton.jsp" %>    
                </div>
            </form> 
            <div id="result"></div>
        </div>
        <hr>
<!-- ******************************END  FORM PENCARIAN******************************* -->  

<!-- **********************TABLE RESULT************************************** -->
        <table id="dg" title="TARIF" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="grade" width="100"sortable="true">Grade</th> 
                    <th field="idTarif" width="100"sortable="true">IdTarif</th> 
                    <th field="startDate" width="100"sortable="true">StartDate</th> 
                    <th field="tarif" width="100"sortable="true"   data-options="
					      formatter:function(value, row){					      
					      return accounting.formatNumber(row.tarif,0,'.',',');
					}" align="right">Tarif</th> 
					                     
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
		<div class="ftitle">TARIF</div>
		<form id="fm" method="post" novalidate>
                    <div class="fitem">	<label>Grade</label> :<input name="grade"	class="easyui-textbox" required="true" id="grade">	</div>
                    <div class="fitem">	<label>IdTarif</label> :<input name="idTarif"	class="easyui-textbox" required="true" id="idTarif">	</div>
                    <div class="fitem">	<label>StartDate</label> :<input name="startDate"	class="easyui-datebox" required="true" id="startDate" data-options="formatter:myformatter,parser:myparser">	</div>
                    <div class="fitem">	<label>Tarif</label> :<input name="tarif"   class="easyui-numberbox"  data-options="min:0,precision:0,groupSeparator:','" required="true" id="tarif">	</div>
			
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
		addComboGrade();
	});

	function test() {
		alert("testtttt..... click");
	}

/* function untuk list data      param=' + $('#idSearch').val();//+'&param2='++ $('#idSearch2').val();*/
	function retrieve() {		
		var jsonurl = 'tarifListAll.htm?'+
'Grade='+$('#Grade').val()+"&"+'IdTarif='+$('#IdTarif').val()+"&"+"userId="+"${userId}";
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
	
	/* ============FORM FUNCTION ========== tariftambah*/

	function doAdd() { 
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah');
		$('#fm').form('clear');
		url = 'tarifAdd.htm?'+"userId="+"${userId}";
		onAdd();
	}
/* ---- tarifedit*/
	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'tarifEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten; //SESUAIKAN
			onEdit();
		}
	}
/*-- tariftampil*/
	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');		
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Tampil');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'tarifEdit.htm?'+"userId="+"${userId}";//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			onShow();
		}
	}
	
	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('tarifDelete.htm', {
							      	grade : row.grade,
				                    idTarif : row.idTarif,
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
                  $('#grade').textbox('readonly', true);
                    $('#idTarif').textbox('readonly', true);
                    $('#startDate').textbox('readonly', true);
                    $('#tarif').textbox('readonly', true);
		$('#btnSave').linkbutton('disable');
	}
	
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
                    $('#grade').textbox('readonly', false);
                    $('#idTarif').textbox('readonly', false);
                    $('#startDate').textbox('readonly', false);
                    $('#tarif').textbox('readonly', false);
		$('#btnSave').linkbutton('enable');
	}
	
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
                    $('#grade').textbox('readonly', true);
                    $('#idTarif').textbox('readonly', true);
                    $('#startDate').textbox('readonly', false);
                    $('#tarif').textbox('readonly', false);
		$('#btnSave').linkbutton('enable');
	}

	
	//grade
	function addComboGrade() {
		$('#grade').combobox({
			url : 'comboLookup.htm?param=' + grade+'&param2=GRADE',
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto'
		});
		grade = '';
	}
	
</script>
