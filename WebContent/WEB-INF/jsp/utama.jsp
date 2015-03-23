<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-image: url('css/bg_top.jpg')">
<h1>Main Page</h1>
<a href="userList.htm">User </a>
</body>
</html-->
<html>
<head>
<meta charset="UTF-8">
<title>UANG KAS</title>
<link rel="stylesheet" type="text/css"
	href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../demo.css">
<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/jquery.easyui.min.js"></script>

              
</head>
<body>
	
	<div style="margin: 20px 0;"></div> 
	<div id="cc" class="easyui-layout" style="width: 100%; height: 650px;">
		<div data-options="region:'north'" style="height: 110px; background-color:aqua;">
			<h2>Cash Project</h2>
			<table width="100%">
			<tr>
				<td align="left" width="10%"><a href="logout.htm">Logout</a></td>
				<td align="right">
					<img alt="" src="image/java.jpg"  height="42" width="60">
					<img alt="" src="image/eclipse.png"  height="42" width="100">
					<img alt="" src= "image/hibernate.png" height="42" width="70">
					<img alt="" src="image/mysql.png"  height="42" width="60">
					<img alt="" src= "image/easyui.jpg" height="42" width="60">
					<img alt="" src= "image/jquery.png" height="42" width="50">
					<img alt="" src="image/json.png"  height="42" width="60">
					<img alt="" src= "image/javascript.jpg" height="42" width="60">
					<img alt="" src="image/ajax.jpg"  height="42" width="60">
				</td>
			</tr>
			</table>
			
		</div>
			<!-- div data-options="region:'south',split:true" style="height: 50px;"></div-->
		<div data-options="region:'west',split:true" title="Menu" style="width: 200px;">		
	        ${menu}
			<input type="button" value="test" onclick="test()">
		</div>
		<div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
			<iframe src="utamaMain.htm" style="width: 100%; height: 100%" id="iframe" frameborder="0"></iframe>			
		</div>
	</div>

</body>
</html>

<script type="text/javascript">


$('#tt').tree({
	onClick: function(node){
		//alert(node.id);  // alert node text property when clicked
		$('#iframe').attr('src',node.id);
		$('#cc').layout('panel', 'center').panel('setTitle', node.text);
	}
});

function test(){
	alert("test");
	$('#cc').layout('panel', 'center').panel('setTitle', 'abc');
}

</script>
