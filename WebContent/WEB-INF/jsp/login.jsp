<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>L O G I N </title>
		<meta charset="utf-8">
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!--webfonts-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet' type='text/css'>
		<!--//webfonts-->
</head>
<body>
	 <!-----start-main---->
	 <div class="main">
		<div class="login-form">
			<h1>Member Login</h1>
					<div class="head">
						<img src="image/user.png" alt=""/>
					</div>
				<form action="login.htm" method="post" id="formLogin">
						<input type="text" class="text" name="USERNAME" value="P81035" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'ADMIN';}" >
						<input type="password" name="Password" value="123" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '1234';}">
						<div class="submit">
							<input type="submit" onclick="myFunction()" value="LOGIN" >
					</div>	
					<p><a href="#">Forgot Password ?</a></p>
				</form>
			</div>
			<!--//End-login-form-->
			 <!-----start-copyright---->
   					<div class="copy-right">
						<p>Template by <a href="http://w3layouts.com">w3layouts</a></p> 
					</div>
				<!-----//end-copyright---->
		</div>
			 <!-----//end-main---->
		 		
</body>
</html>

<script>

function myFunction(){
//	alert("submit");
	$('#formLogin').submit();
}


</script>