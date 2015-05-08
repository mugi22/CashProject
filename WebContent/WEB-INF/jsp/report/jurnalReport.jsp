<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
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
<!-- javascript:printDiv('printed') -->
<input type="button" value="Cetak" onclick="cetak()">
<div id="printArea">
	<table width="90%"	style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">
		<tr>
			<td colspan="3" style="font-weight: bold;" align="left">
				Daftar XXXXXXXXX
			</td>
			<td colspan="3" style="font-weight: bold;" align="right">
				PIN000X
			</td>
		</tr>
		<tr>
			<td colspan="3" style="font-weight: bold;" align="left">
				PT.	XXXXXXXX
			</td>
			<td colspan="3" style="font-weight: bold;" align="right">
				Tgl	Cetak : 28/04/2015
			</td>
		</tr>
		<tr>
			<td colspan="6" style="font-weight: bold;" align="center">
				Daftar XXXXXXXXX
			</td>
		</tr>
		<tr>
			<td colspan="6" style="font-weight: bold;" align="center">
				KANWIL XXXXXXXX
			</td>

		</tr>
		<tr>
			<td colspan="6" style="font-weight: bold;" align="center">
				Per	Tanggal : 27/04/2015
			</td>
		</tr>
	</table>
	<br>
	<br>


<table id="myData" align="center" width="90%" cellpadding="0" cellspacing="0" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">
	<thead>            
    	<tr>
    	
			        <th style="font-weight:bold" align="center" class="cfg_header" >BranchCode</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >Keterangan</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >JurnalId</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >Authorized</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >RefId</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >Reverse</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >RespCode</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >TglPosting</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >KodeTransaksi</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >AuthorizedBy</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >AuthorizedDate</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >TglTransaksi</th> 
                    <th style="font-weight:bold" align="center" class="cfg_header" >MobileBranchCode</th> 
                   
                     
    	</tr>
	</thead>
</table>
</div>
</body>

<script type="text/javascript">

	$("document").ready(function(){
		var uri ='jurnalDataReport.htm?';
		var s = window.location.search.replace("?", "") ;
		$.ajax({
			url:uri+"&"+s,
			success	: function(result){			
				var x = JSON.parse(result);
				var no = 1;
				 $.each(x, function( index, value ) {
	                 var row = $("<tr>"+
	                       			    "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.branchCode + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.keterangan + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.jurnalId + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.authorized + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.refId + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.reverse + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.respCode + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.tglPosting + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.kodeTransaksi + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.authorizedBy + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.authorizedDate + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.tglTransaksi + "</font></div></td>"+
								        "<td align='left'> <div class='cfg_detail'><font class='f_boldhd'>" +  value.mobileBranchCode + "</font></div></td>"+

	                		     "</tr>");
	                 $("#myData").append(row);
	                 no++;
	              });
				var z =  $("<tr> <td colspan='13' align='left' style='background-color:#CCF;'> <div class='cfg_detailx'><font class='f_boldhd'>" +'T O T A L'+ "</font></div></td></tr>");
				$("#myData").append(z);
			}
		});
	});

function cetak(){
	printDiv('printed');
}

function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML = 
      "<html><head><title></title></head><body>" + 
      divElements + "</body>";

    //Print Page
    window.print();

    //Restore orignal HTML
    document.body.innerHTML = oldPage; 
}
</script>
</html>
