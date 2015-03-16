<%-- 
    Document   : TestMenu
    Created on : Jan 15, 2015, 9:23:59 AM
    Author     : Mugiarto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <script type="text/javascript" src="css/simpletreemenu.js">

/***********************************************
* Simple Tree Menu- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

        </script>

<link rel="stylesheet" type="text/css" href="css/simpletree.css" />
        
  
    </head>
    <body>
        <a href="javascript:ddtreemenu.flatten('treemenu1', 'expand')">Expand All</a> | <a href="javascript:ddtreemenu.flatten('treemenu1', 'contact')">Contact All</a>

       
        ${menu}
        
        <script type="text/javascript">

//ddtreemenu.createTree(treeid, enablepersist, opt_persist_in_days (default is 1))

ddtreemenu.createTree("treemenu1", true)
//ddtreemenu.createTree("treemenu2", false)

</script>
    </body>
</html>
