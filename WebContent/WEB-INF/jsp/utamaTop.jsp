<%-- 
    Document   : HalamanUtamaTop
    Created on : Dec 29, 2014, 1:17:33 PM
    Author     : Mugiarto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><title>Untitled Document</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="MSHTML 6.00.2800.1400" name="GENERATOR">
        <style>
            span.dropt {border-bottom: thin dotted; background: #ffeedd;}
            span.dropt:hover {text-decoration: none; background: #ffffff; z-index: 6; }
            span.dropt span {position: absolute; left: -9999px;
                             margin: 20px 0 0 0px; padding: 3px 3px 3px 3px;
                             border-style:solid; border-color:black; border-width:1px; z-index: 6;}
            span.dropt:hover span {left: 2%; background: #ffffff;} 
            span.dropt span {position: absolute; left: -9999px;
                             margin: 4px 0 0 0px; padding: 3px 3px 3px 3px; 
                             border-style:solid; border-color:black; border-width:1px;}
            span.dropt:hover span {margin: 20px 0 0 170px; background: #ffffff; z-index:6;} 
        </style>
        <style>
            .circle{width:130px;height:130px;border-radius:100px;font-size:20px;color:#fff;line-height:100px;text-align:center;background:#FFFFFF;}
            .circle2{width:200px;height:130px;border-radius:100px;font-size:20px;color:#fff;line-height:100px;text-align:center;background:rgb(191,215,48)}
            .circle3{width:260px;height:130px;border-radius:100px;font-size:20px;color:#fff;line-height:100px;text-align:center;background:rgb(0,171,78)}
            .square {height: 130px;	width: 150%;background:rgb(0,77,67)}
            .whitesquare {width: 50%;height: 130px;background:#FFFFFF}
            .letterpress {
                text-shadow: 0px 2px 3px rgb(191,215,48);
                color: #ffffff;
                font: 20px 'VERDANA';
                margin-top:-70px;
            }
            .letterpress2 {
                /* text-shadow: 0px 2px 3px rgb(191,215,48); */
                color: #FF0;
                font: 12px;
                font-weight:bold; 
                margin-top:-70px;
            }
        </style>
        <link rel="icon" type="image/ico" href="http://10.253.252.11/favicon.ico">
    </head>
    <body scroll="NO">
        <table style="margin-top: -32px" width="100%" border="0" cellpadding="0" cellspacing="0">
            <tbody><tr>
                    <td width="21%">	
                        <div class="square">
                            <div class="circle3">
                                <div class="circle2">
                                    <div class="circle">
                                        <div class="whitesquare">
                                            <div style="position: absolute; top: 20px;">
                                                
                                                <img src="image/pgd_logo2.gif" alt="Logo" height="51" width="108">
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td class="square" width="40%"><p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p></p>
                        <div class="letterpress">Information System<br> <h6>${user.name}</h6>
                        </div>
                        <p></p>    
                    </td>
                    <td class="square" width="29%"><p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p></p>
                        <div class="letterpress2" style="font-size:12px; font-family:'Trebuchet MS', Arial, Helvetica, sans-serif">
                            <marquee scrollamount="4" width="500">Informasi :${session}<!-- GANTI YAAAAAAAAAAAAAAAAA -->
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </marquee>		
                        </div>
                        <p></p>  
                        <!--
            <marquee width="500" behavior="scroll" direction="left" scrollamount="4" style="font-size:12px; font-family:'Trebuchet MS', Arial, Helvetica, sans-serif">
        INFORMASI : MIS 2014 - Data untuk outlet syariah sedang di Restore. Terimakasih</marquee>  !-->
                    </td>
                    <td class="square" width="10%"><p>&nbsp;</p>
                        <p></p>
                        <div style="position: absolute;left:94%; top: 60px;">
                            <a href="logout.htm" target="_parent">
                                
                                <img src="image/logout.gif" alt="Logout" border="0"></a>
                        </div>
                        <p></p>
                    </td>
                </tr>
            </tbody>
        </table>

    </body>
</html>