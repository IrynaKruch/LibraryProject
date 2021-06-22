<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="locale" />

<html lang="${locale}">
<head>
<title>Error Page</title>
</head>
    <body>
    <div class="container">
    <div class="row">
        <div class="error-template">
            <h1><fmt:message key="header.error"/></h1>
            <div class="error-details">
                ${errMessage}
                <br>
            </div>
            <div class="error-actions">
                  <c:choose>
					<c:when test="${sessionUser==null}">
						<a href="<c:url value="/login.jsp"/>"> <fmt:message key="login.login"/></a>
					</c:when>
					<c:otherwise>
					    <a href="<c:url value="/catalogue"/>"><fmt:message key="header.mainPage"/></a>
					</c:otherwise>
				  </c:choose>
            </div>
        </div>
    </div>
</div>
    
       