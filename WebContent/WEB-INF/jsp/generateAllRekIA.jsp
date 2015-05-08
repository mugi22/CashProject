<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="include.jsp" %>
<title>GenerateAllRekIA</title>
</head>
<body>

<form action="" method="post" id="ff">
<label>Unit Kerja</label> :
<input name="branchCode"	class="easyui-textbox"  id="branchCode" >	
<input type="button" value="Cek Unit" onclick="CekBranch()">

<br>
<input type="submit" value="Generate" id="submit">
<!-- 
 -->
</form>
${message}
</body>
</html>


<script>
$("document").ready(function() {	
	$('#submit').prop("disabled", true);  
	$("#branchCode").textbox("readonly", false); 
	keyEnter($("#branchCode"));
	//addComboStatusUnit();
});



function CekBranch(){
	$.ajax({
		url:'getBranchByID.htm?branchCode='+$('#branchCode').val(),
		success : function(result){			
			if(result==''||result=="{}"){
				alert($('#branchCode').val()+" Tidak Ditemukn");				
			}else{
				$('#submit').prop("disabled", false); 
			}
		}		
	});	
}

$('#ff').submit(function() {	 
	  $.messager.progress();
	  return true;
	});

function clearForm(){  
	$('#ff').form('clear');  
	$('#submit').linkbutton("disable"); 
}

function keyEnter(t) {
	t.textbox('textbox').bind('keyup', function(e) {
		if(e.keyCode==13){
			CekBranch();
		}
	});
}

</script>