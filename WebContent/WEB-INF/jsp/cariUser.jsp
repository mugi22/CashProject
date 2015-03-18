<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Cari User</h1>
       <div id="parameter">
            <form name="FREG" id="formCari" method="post" action="#"  >    
                <label>User Id</label> : 
                <input name="userId" type="text" id="userIdSearch" size="30" maxlength="30"><br>
                <div id="btn">     
                    <input type="button" name="btnKirim" id="btnCari2" value="Cari" onclick="retrieve2()">     
                    <input type="reset" name="btnUlangi" id="btnReset2" value="Reset" onclick="doClear()" >     
                </div>
            </form> 

        </div>
        <hr>
        
<!-- **********************TABLE RESULT************************************** -->
        <table id="dg2" title="Daftar User" class="easyui-datagrid" style="width:100%;"
               toolbar="#toolbar2" pagination="true"
               data-options="total:2000,pageSize:10"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="userId" width="100" sortable="true">User ID</th> 
                    <th field="name" width="250" sortable="true">Nama</th>
                    <th field="branchCode" width="100" sortable="true">Kode Unit</th> 
                    <!-- th field="createBy" width="100" sortable="true">Di Buat</th> 
                    <th field="createDate" width="100" sortable="true">Tgl Buat</th>
                    <th field="updateBy" width="100" sortable="true">Di Ubah</th> 
                    <th field="updateDate" width="100" sortable="true">Tgl Ubah</th>
                    <th field="endTime" width="100" sortable="true">endTime</th-->
                </tr>
            </thead>
        </table>        
        <div id="toolbar2">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doAmbil()" id="btnGet">Ambil</a>
        </div>
 <!-- ************************** END LIST/TABLE ******************************************** -->       
        
<script type="text/javascript">
function retrieve2() {
	//alert("hhhhhhhhh");
	var jsonurl = 'userListAll.htm?param=' + $('#userIdSearch').val();
	$('#dg2').datagrid({
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
	$('#dg2').datagrid({
		url : jsonurl,
		onLoadSuccess : function(data) {
			if (data.total > 0) {
				alert("Clear Gagal..................");
			}
		}
	});
}

</script>
        
        
        
</body>
</html>


<script>



</script>