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
</body>
</html>