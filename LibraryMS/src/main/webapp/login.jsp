<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:import url="/views/head.jsp" />

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="locale" />


<!DOCTYPE html>
<html lang="${locale}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login ${locale}</title>
<!-- <script>
function validate()
{ 
     var login = document.form.login.value; 
     var password = document.form.password.value;
 
     if (login==null || login=="")
     { 
     alert("Login cannot be blank"); 
     return false; 
     }
     else if(password==null || password=="")
     { 
     alert("Password cannot be blank"); 
     return false; 
     } 
}
}
</script> -->
</head>

<body>

	<div class="container">
		<form class="form-signin" name="login" method="POST" action="${pageContext.request.contextPath}/login">
			<p style="color: blue">${message}</p>
			<c:remove var="message" scope="session" />
		
			<input type="hidden" name="command" value="login" />
			<fmt:message key="login.login" />
			:<br /> <input class="form-control" required="required"
				placeholder=<fmt:message key="login.login" /> type="text"
				name="login"> <br />
			<fmt:message key="login.password" />
			:<br /> <input class="form-control" required="required"
				placeholder=<fmt:message key="login.password"/> type="password"
				name="password"> <br /> <a href="${pageContext.request.contextPath}/views/register.jsp"><fmt:message
					key="login.registration" /></a>
			<button class="btn btn-lg btn-primary btn-block" type="submit"
				value="Enter">
				<fmt:message key="login.login" />
			</button>
		</form>
		<a
			href="?sessionLocale=ua">
			<fmt:message key="ukrainian" />
		</a> <a
			href="?sessionLocale=en">
			<fmt:message key="english" />
		</a>

		<p style="color: red">${error}</p>
		<span style="color: red"> 
		<c:if test="${errMessage!=null}">${errMessage}</c:if>
		</span>
			<c:remove var="errMessage" scope="session" />
		
	</div>



	<%-- <form name="form" action="Servlet" method="post"
		onsubmit="return validate()">
		<input type="hidden" name="command" value="login" />
		<table align="center">
			<tr>
				<td><fmt:message key="login.login" /></td>
				<td><input type="text" name="login" /></td>
			</tr>
			<tr>
				<td><fmt:message key="login.password" /></td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td><span style="color: red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login"></input><input
					type="reset" value="Reset"></input></td>
			</tr>
		</table>
	</form>
 --%>




</body>
</html>