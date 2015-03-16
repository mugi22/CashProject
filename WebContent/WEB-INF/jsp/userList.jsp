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
                <label>User Id</label> : 
                <input name="userId" type="text" id="userIdSearch" size="30" maxlength="30"><br>

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
        <table id="dg" title="Daftar User" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="userId" width="100" sortable="true">User ID</th> 
                    <th field="name" width="250" sortable="true">Nama</th>
                    <th field="branchCode" width="100" sortable="true">Kode Unit</th> 
                    <th field="createBy" width="100" sortable="true">Di Buat</th> 
                    <th field="createDate" width="100" sortable="true">Tgl Buat</th>
                    <th field="updateBy" width="100" sortable="true">Di Ubah</th> 
                    <th field="updateDate" width="100" sortable="true">Tgl Ubah</th>
                    <th field="endTime" width="100" sortable="true">endTime</th>
                </tr>
            </thead>
        </table>        
        <div id="toolbar">
            <a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()" id="btnAdd" >Tambah</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()" id="btnEdit">Edit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete()" id="btnDelete">Hapus</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doShow()" id="btnShow">Tampil</a>
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
    
          
<!-- ************************** FORM ******************************************** -->
	<div id="dlg" class="easyui-dialog"	style="width: 750px;  padding: 10px 20px" closed="true"	buttons="#dlg-buttons" data-options="modal:true">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>User ID</label> :<input name="userId"	class="easyui-textbox" required="true" id="userId">
				<input type="button" name="btnKirim" id="btnCariUser" value="Cari" onclick="cariUser()"> 
			</div>
			<div class="fitem">
				<label>Nama User</label> :<input name="name" class="easyui-textbox" required="true"  id="namax">				
			</div>
			
			<div class="fitem">
				<label>Password</label> :<input type="password" name="password" class="easyui-textbox" required="true" id="password">
			</div>			
			
			<div class="fitem">
				<label>Kode Cabang</label> :<input id="branchCode" name="branchCode" value="aa" style="width: 300px;" id="branchCode">
			</div>
			
			<div class="fitem">
				<label>Email</label> :<input name="email" class="easyui-textbox" validType="email" id="email">
			</div>
			
			<div class="fitem">
				<label>Start Time</label> :<input class="easyui-datebox" name="startTime" id="startTime" data-options="formatter:myformatter,parser:myparser"/>
			</div>
			<div class="fitem">
				<label>End Time  </label> :<input name="endTime" class="easyui-datebox" id="endTime" data-options="formatter:myformatter,parser:myparser"/>
			</div>
			<div class="fitem">
				<label>Enable</label> : 
				<select class="easyui-combobox" name="enabled" id="enabled" style="width:60px;" data-options="panelHeight:'auto'">
							<option value="Y">Ya</option>
							<option value="N">Tidak</option>
				</select>
			</div>
			
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="doSave()" style="width: 90px" id="btnSave">Save</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" 	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"style="width: 90px" id="btnCancel">Cancel</a>
	</div>
	<!-- ************************************************END FORM******************* -->

<div id="win">	
<div id="cari">
<form id="formCari2" method="post" action="#"  >    
                <label>User Id</label> : 
                <input name="userId" type="text" id="searchUserName" size="30" maxlength="30"><br>

                <div id="btn">     
                    <input type="button" name="btnKirim" id="btnCari" value="Cari" onclick="userSearch()">     
                    <input type="reset" name="btnUlangi" id="btnReset" value="Reset" onclick="doClear()" >     
                </div>
            </form> 
</div>
	<table id="dg2">  </table>
	<div id="toolbar2">
             <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doAmbil()" id="btnAmbil">Tampil</a>
        </div>
</div>
</body>
</html>



<script>
	function cariUser() {
	    $('#win').window({
	        width:600,
	        height:400,
	        modal:true
	        });
	   
	    $('#cari').show();
	    
	    $('#dg2').datagrid({
	        url:'userListAll.htm?param=',
	        title:"Pencarian",	        
	        pagination:"true",	               
	        columns:[[
	        {field:'userId',title:'userId',width:100},
	        {field:'name',title:'Name',width:250},
	        {field:'branchCode',title:'branchCode',width:100,align:'right'}
	        ]],
	        toolbar:"#toolbar2"
	        
	        });
	    
	    
	}

	function userSearch() {
		
		var jsonurl = 'userListAll.htm?param='+$("#searchUserName").val();
		alert("Cari User  "+jsonurl);		
		$('#dg2').datagrid({
			url : jsonurl,
			onLoadSuccess : function(data) {
				if (data.total == 0) {
					alert("Data Tidak Ditemukan..................");
				}
			}
		});
	}

	var url;
	var branchcode;
	$("document").ready(function() {
		$("#btnAdd").linkbutton('${btnAdd}');
		$("#btnEdit").linkbutton('${btnEdit}');
		$("#btnDelete").linkbutton('${btnDelete}');
		$("#btnShow").linkbutton('${btnShow}');
		$('#cari').hide();
		//toolbar2
		$('#toolbar2').hide();
		$('#cc').layout('panel', 'west').panel('tittle', 'abc');
		
		doClear();
	});

	function doAmbil(){
		var row = $('#dg2').datagrid('getSelected');
		//alert(row.userId);
		$('#userId').textbox('setValue',row.userId);
		$('#win').window('close'); 
	}
	
	
	function test() {
		alert("testtttt..... click");
	}

	/* function untuk list data*/
	function retrieve() {
		var jsonurl = 'userListAll.htm?param=' + $('#userIdSearch').val();
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
		$('#dlg').dialog('open').dialog('setTitle', 'Tambah User');
		$('#fm').form('clear');
		url = 'userAdd.htm';
		/*var t = $('#namax');*/
		upperCase($('#namax'));
		upperCase($('#userId'));
		branchcode = ''; //combobox tidak ada default
		addComboBranch();
		onAdd();
	}

	function doEdit() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'userEdit.htm';//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			upperCase($('#namax'));
			branchcode = row.branchCode;
			addComboBranch();
			onEdit();
		}
	}

	function doShow() {
		$('#fm').form('clear');
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('clear');
			$('#fm').form('load', row);
			url = 'userEdit.htm';//?param='+row.kodeProvinsi+'&param2='+row.kodeKabupaten;
			upperCase($('#namax'));
			branchcode = row.branchCode;
			addComboBranch();
			onShow();
		}
	}

	function doDelete() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', 'Anda Ingin Mengapus Data?',
					function(r) {
						if (r) {
							$.post('userDelete.htm', {
								userId : row.userId
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
					var kd = $('#kode').val();
					$('#kodeProvinsi').val(kd);
					//                        var jsonurl =  'provinsiAjax.htm?param='+$('#kodeProvinsi').val();
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
		$('#btnCariUser').hide();
		$('#userId').textbox('readonly', true);
		$('#namax').textbox('readonly', true);
		$('#password').textbox('readonly', true);
		$('#branchCode').textbox('readonly', true);
		$('#email').textbox('readonly', true);
		$('#startTime').textbox('readonly', true);
		$('#endTime').textbox('readonly', true);
		$('#enabled').combobox('readonly', true);
		//form button
		$('#btnSave').linkbutton('disable');
	}
	/*inputan readonly atau tidak saat Add*/
	function onAdd() {
		//list button
		$('#btnCariUser').show();
		$('#userId').textbox('readonly', false);
		$('#namax').textbox('readonly', false);
		$('#password').textbox('readonly', false);
		$('#branchCode').textbox('readonly', false);
		$('#email').textbox('readonly', false);
		$('#startTime').textbox('readonly', false);
		$('#endTime').textbox('readonly', false);
		$('#enabled').combobox('readonly', false);
		//form button
		$('#btnSave').linkbutton('enable');
	}
	/*inputan readonly atau tidak saat Edit */
	function onEdit() {
		//list buttonbtn CariUser
		$('#btnCariUser').hide();
		$('#userId').textbox('readonly', true);
		$('#namax').textbox('readonly', false);
		$('#password').textbox('readonly', false);
		$('#branchCode').textbox('readonly', false);
		$('#email').textbox('readonly', false);
		$('#startTime').textbox('readonly', false);
		$('#endTime').textbox('readonly', false);
		$('#enabled').combobox('readonly', false);
		//form button
		$('#btnSave').linkbutton('enable');
	}

	//getSearch
	//function getSarch(){		
	//	var jsonurl = 'userListAll.htm?rows=10&param=' + $('#userSearch').val();
	//	$('#dg').datagrid({
	//		url : jsonurl,
	//		onLoadSuccess : function(data) {
	//			if (data.total == 0) {
	//				alert("Data Tidak Ditemukan..................");
	//			}
	//		}
	//	});

	//$('#namax').textbox('setValue',$('#namaSearch').val());	
	//$('#dd').dialog({
	//title: 'My Dialog',
	//width:600,
	// height: 300,
	//    closed: true,
	//    cache: false,
	// href: 'get_content.php',
	//    modal: true
	//    });
	//$('#dd').dialog('refresh', 'new_content.php');
	//}
</script>