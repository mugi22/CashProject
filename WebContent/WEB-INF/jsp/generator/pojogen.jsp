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
<h1>POJO GEN</h1>
<form action="" method="post">
Table Name : <input type="text" style="width: 300px" id="tblName" name="tblName" value="Tbl_Test"> <input type="button" value="Add" id="btnTest" onclick="addRow()">
<br>
Pojo Name : <input type="text" style="width: 300px" id="pojoName" name="pojoName" value="TblTest"> 


		<table border="1" id="ddReferences">
            <thead>
                <tr>
                    <th>Column</th>
                    <th>Type</th>
                    <th>Lenght</th>
                    <th>Uniq</th>
                    <th>Nulable</th>
                    <th>ID<th>
                </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
        <input type="submit" value="Create">
</form>
 
</body>
</html>


<script>

function  create(){
	alert("Create");
}




function addRow(){
	//alert("addd");
	
	 var eachrow = "<tr>"
        +"<td><input type='text' value='' name='col'> </td>"
        
        +"<td><select name ='types'><option value='String'>String</option>"
		        +"<option value='long'>long</option>"
		        +"<option value='BigDecimal'>BigDecimal</option>"
		        +"<option value='Date'>Date</option>"
        +"</select> </td>"
        +"<td><input name='length' type='' value='' name='col'> </td>"
        +"<td><select name= 'uniq'><option value='No'>No</option><option value='Yes'>Yes</option></select>  </td>"
        +"<td><select name= 'nulable'><option value='Yes'>Yes</option><option value='No'>No</option></select>  </td>"
        +"<td><select name= 'ids'><option value='No'>No</option><option value='Yes'>Yes</option></select>  </td>"
               
        
        + "</tr>";
		 $('#tbody').append(eachrow);
	
	
	
}

</script>