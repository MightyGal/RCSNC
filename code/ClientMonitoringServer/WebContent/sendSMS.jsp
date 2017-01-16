<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send SMS</title>
</head>
<body>
<form action="MessageManageServlet" method="get">

<table>

<tr><td>Phone Number</td><td><input type="text" name="mobile"></td>
<tr><td>SMS Body</td><td><input type="text" name="sms"></td>
<tr><td><input type="submit" value="Send"> </td></tr>

</table>
</form>

</body>
</html>