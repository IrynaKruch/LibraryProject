<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Page</title>
</head>
<body>
<center><h2>Admin's Home</h2></center>
 
Welcome ${sessionUser.login}
 
<div style="text-align: right"><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
<b><a href="${pageContext.request.contextPath}/homepage.jsp">Home</a></b>

</body>
</html>