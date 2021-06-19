<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
    <body>
        <h2>
           Error Page<br/>
            <i>${errMessage}</i>
        </h2>


       <br>
Return to <a href="${pageContext.request.contextPath}/homepage.jsp">homepage</a>
<div style="text-align: right"><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
<a href="${pageContext.request.contextPath}/">login</a>
    </body>
</html>