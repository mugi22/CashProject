<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>ADMIN MODE  : ${AdminMode}</h1>

<form action="" method="post">
<label>Admin Mode</label>
<select name="mode">
<option>OPEN</option>
<option>CLOSE</option>
</select>
<input type="submit" value="Submit">
</form>
</body>
</html>