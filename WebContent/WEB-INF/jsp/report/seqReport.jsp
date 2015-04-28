<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <link rel="stylesheet" type="text/css" href="css/color.css">
        <link rel="stylesheet" type="text/css" href="css/searchcss.css">     
        <link rel="stylesheet" type="text/css" href="css/configzz.css">  
        
        <script type="text/javascript" src="css/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="css/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="css/formater.js"></script>
		<script type="text/javascript" src="css/accounting.min.js"></script>
		<script type="text/javascript" src="css/myalert.js"></script>
		 <script type="text/javascript" src="css/my.js"></script>



<title>Insert title here</title>
</head>
<body>
	<table width="90%"
		style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">
		<tr>
			<td colspan="3" style="font-weight: bold;" align="left">Daftar
				Pelunasan (KCA)</td>
			<td colspan="3" style="font-weight: bold;" align="right">PIN000X</td>
		</tr>
		<tr>
			<td colspan="3" style="font-weight: bold;" align="left">PT.
				Pegadaian (Persero)</td>
			<td colspan="3" style="font-weight: bold;" align="right">Tgl
				Cetak : 28/04/2015</td>
		</tr>
		<tr>
			<td colspan="6" style="font-weight: bold;" align="center">Daftar
				Pelunasan - Gadai(KCA)</td>
		</tr>
		<tr>
			<td colspan="6" style="font-weight: bold;" align="center">KANWIL
				JAKARTA 1 - CP PETAMBURAN(12293) - CP PETAMBURAN(12293)</td>

		</tr>
		<tr>
			<td colspan="6" style="font-weight: bold;" align="center">Per
				Tanggal : 27/04/2015</td>
		</tr>
	</table>
	<br>
	<br>

	<table id="myData" align="center" width="90%" cellpadding="0" cellspacing="0">
<thead> 
           
    <tr>
                    <th style="font-weight:bold"  align="center" class="cfg_header">LastLogIn</th> 
                    <th style="font-weight:bold"  align="center" class="cfg_header">Keterangan</th> 
                    <th style="font-weight:bold"  align="center" class="cfg_header">SeqName</th> 
                    <th style="font-weight:bold"  align="center" class="cfg_header">SeqNum</th> 
                    <th style="font-weight:bold"  align="center" class="cfg_header">Tarif</th> 
                     
    </tr>
           
</thead>
</table>


<!-- 
<td align='left'><div class='cfg_detail'><font class='f_boldhd'>&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;&nbsp;1229314010049201</font></div></td>

 data-options="
					formatter:function(value, row){				      
					return accounting.formatNumber(row.tarif,0,'.',',');
					}" align="right"
 -->


</body>

<script type="text/javascript">

$("document").ready(function(){
	var uri ='seqDataReport.htm?';
	var s = window.location.search.replace("?", "") ;
	//alert("uri "+uri);
	$.ajax({
		url:uri+"&"+s,
		success	: function(result){			
			var x = JSON.parse(result);
			var no = 1;
			 $.each(x, function( index, value ) {
				 var z = accounting.formatNumber(value.tarif,0,'.',',');
                 var row = ("<tr>"+
                        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.lastLogIn + "</font></div></td>"+
				        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.keterangan + "</font></div></td>"+
				        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.seqName + "</font></div></td>"+
				        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.seqNum + "</font></div></td>"+
				        "<td align='right' > <div class='cfg_detail'><font class='f_boldhd'>" +  z + "</font></div></td>"+	
                		"</tr>");
                 $("#myData").append(row);
                 no++;
              });
			var z =  ("<tr> <td colspan='5' align='left' style='background-color:#CCF;'> <div class='cfg_detailx'><font class='f_boldhd'>" +'T O T A L'+ "</font></div></td></tr>");
			$("#myData").append(z);
		}
	});
});


</script>
</html>
