
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="locale" />

<html lang="${locale}">

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand"> <fmt:message key="header.library" />
			</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<c:if
					test="${pageContext.request.requestURL!='http://localhost:8080/LibraryMS/homepage.jsp'}">
					<li><a href="${pageContext.request.contextPath}/catalogue"> 
					<fmt:message key="header.mainPage" />
					</a></li>
				</c:if>
                <c:if
					test="${pageContext.request.requestURL!='http://localhost:8080/LibraryMS/views/personInfo.jsp' && sessionUser!=null}">
					<li><a href="<c:url value="/views/personInfo.jsp"/>"> 
					<fmt:message key="header.personInfo" />
					</a></li>
				</c:if>
   					<li><a href="<c:url value="/readerInfo"/>"> 
					<fmt:message key="header.myBooks" />
					</a></li>
				<c:if test="${sessionUser.role=='LIBRARIAN'}">
					<li><a href="<c:url value="/readers"/>"> 
					<fmt:message key="header.readers" />
					</a></li> 
					<li><a href="<c:url value="/showOrders"/>"> 
					<fmt:message key="header.showorders" />
					</a></li>
				</c:if>
				<c:if test="${sessionUser.role=='ADMIN'}">
					<li><a href="<c:url value="/readers"/>"> 
					<fmt:message key="header.readers" />
					</a></li> 
					<li><a href="<c:url value="/showOrders"/>"> 
					<fmt:message key="header.showorders" />
					</a></li>
					<li><a href="<c:url value="/views/addBook.jsp"/>"> 
					<fmt:message key="header.addBook" />
					</a></li>
				</c:if>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li>
				<a href="?sessionLocale=ua" >
				 <fmt:message key="header.ua" /> </a>
				 </li>
				<li>
				<a href="?sessionLocale=en"> 
				<fmt:message key="header.en" /></a>
				</li>
				<li></li>
				<li><a href="${pageContext.request.contextPath}/logout"> <fmt:message
							key="header.logout" />
				</a></li>
			</ul>

		</div>
	</div>
</nav>
<div class="container p-1">
<h3><fmt:message key="header.hello" />, <c:out value="${sessionUser.fullname}" />!</h3>
</div>
