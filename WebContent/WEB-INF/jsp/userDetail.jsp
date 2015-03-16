<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Detaile</title>
</head>

<body>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
closed="true" buttons="#dlg-buttons">
<div class="ftitle">User Information</div>
<form id="fm" method="post" novalidate>
<div class="fitem">
<label>First Name:</label>
<input name="firstname" class="easyui-textbox" required="true">
</div>
<div class="fitem">
<label>Last Name:</label>
<input name="lastname" class="easyui-textbox" required="true">
</div>
<div class="fitem">
<label>Phone:</label>
<input name="phone" class="easyui-textbox">
</div>
<div class="fitem">
<label>Email:</label>
<input name="email" class="easyui-textbox" validType="email">
</div>
</form>
</div>

 <div id="dlg-buttons">
<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>



</body>
</html>