<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="css/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="css/jquery.easyui.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<h1>Code Generator</h1><br>
name : <input type="text" style="width: 300px" id="z" value="com.id.kas.pojo.TblSeq"> <input type="button" value="Ambil" id="btnTest" 
onclick="testAjax()">
<hr>
<form action="" method="post">
JSP Name : <input type="text" style="width: 300px" id="jspName" name="jspName" value="seq" >
Mapping : <input type="text" style="width: 300px" id="maping" name="maping" value="seq"  ><br>
Controller : <input type="text" style="width: 300px" id="cont" name="cont" value="SeqController"  >
DAO : <input type="text" style="width: 300px" id="dao" name="dao" value="TblSeq" ><br><br>

JSP : <select name="jsp">	<option>Y</option>	<option>N</option>	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
DAO : <select name="dao2">	<option>Y</option>	<option>N</option>	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Controller : <select name="controller">	<option>Y</option>	<option>N</option>	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Report : <select name="report">	<option>N</option> <option>Y</option>		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
Form JSP : <select name="formx">	<option>N</option>	<option>Y</option>	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Controller Form : <select name="controllerFormx"><option>N</option>	<option>Y</option>		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br><br>


<table border="1" id="ddReferences">
            <thead>
                <tr>
                    <th>Column Name</th>
                    <th>Form</th>
                    <th>List</th>
                    <th>Search</th>
                    <th>ID</th>
                    <th>Tipe</th>
                </tr>
            </thead>
            <tbody id="tbody">
                
            </tbody>
        </table>
        <input type="submit" value="Submit"> 
        <input type="reset" value="Reset">
</form>
 <!-- input type="button" value="test" onclick="test()"-->

</body>
</html>

<script>

function test(){
	alert("testttttt.................");
	
}


function testAjax(){
//	alert($('#z').val());
	$.ajax({
		url :'testAjax.htm?param='+$('#z').val(),
		datatype:'json',
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			
			//alert(data);
			var f ;
			var i ;
			$.each(obj, function (index, data) {
			     i="dddd";
			     
			     var eachrow = "<tr>"
	                 + "<td>"+"<input type="+'"'+"text"+'"'+" name="+'"'+"col"+'"'+"value="+'"'+data.colx+'"'+" />" +  "</td>"	 
	                <!-- +"<td>"  +"<input type="+'"'+"checkbox"+'"'+" name="+'"'+"tes"+'"'+"value="+'"'+data.colx+'"'+" />"+ "</td>"	-->
	                +"<td><select name="+'"'+"tes"+'"'+">"
	                +"<option value="+'"'+"Y"+'"'+">Y</option>"
	                +"<option value="+'"'+"N"+'"'+">N</option>"
	            	+"</select></td>"
	                <!-- +"<td>"  +"<input type="+'"'+"checkbox"+'"'+" name="+'"'+"list"+'"'+"value="+'"'+data.colx+'"'+" />"+ "</td>"
	                --> 
	                +"<td><select name="+'"'+"list"+'"'+">"
	                +"<option value="+'"'+"Y"+'"'+">Y</option>"
	                +"<option value="+'"'+"N"+'"'+">N</option>"
	            	+"</select></td>"
	                
	            	<!--+"<td>"  +"<input type="+'"'+"checkbox"+'"'+" name="+'"'+"search"+'"'+"value="+'"'+data.colx+'"'+" />"+ "</td>"
	                -->
	            	+"<td><select name="+'"'+"search"+'"'+">"
	            	+"<option value="+'"'+"N"+'"'+">N</option>"
	                +"<option value="+'"'+"Y"+'"'+">Y</option>"	                
	            	+"</select></td>"
	                
	                <!--+"<td>"  +"<input type="+'"'+"checkbox"+'"'+" name="+'"'+"ids"+'"'+"value="+'"'+data.colx+'"'+" />"+ "</td>"
	                -->
	                +"<td><select name="+'"'+"ids"+'"'+">"
	                +"<option value="+'"'+"N"+'"'+">N</option>"
	                +"<option value="+'"'+"Y"+'"'+">Y</option>"
	                
	            	+"</select></td>"
	                
	                +"<td>"  +"<input type="+'"'+"text"+'"'+" name="+'"'+"tipe"+'"'+"value="+'"'+data.tipe+'"'+" />"+ "</td>"
	                
	                
	                + "</tr>";
	    			 $('#tbody').append(eachrow);
			});
		}
		
	});
}
















</script>