<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<c:import url="head.jsp"/>
<c:import url="header.jsp"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>

<div class="container">
	<p style="color: blue">${message}</p>
	<c:remove var="message" scope="session" />
	<p style="color: red">${errMessage}</p>
	<c:remove var="errMessage" scope="session" />

    <form name="addBookForm" method="POST" action="<c:url value="/addBook"/>">
        <div class="form-group">

            <fmt:message key="homepage.bookname"/>:
            <input class="form-control" type="text" name="bookname"
                   placeholder="<fmt:message key="homepage.bookname"/>" required="required"><br/>
        </div>
        <div class="form-group">
            <fmt:message key="homepage.author"/>:
            <input class="form-control" type="text" name="author"
                   placeholder="<fmt:message key="homepage.author"/>" required="required"><br/>
        </div>
        <div class="form-group">
            <fmt:message key="homepage.editor"/>:
            <input class="form-control" type="text" name="edition"
                   placeholder="<fmt:message key="homepage.editor"/>" required="required"><br/>
        </div>
		<div class="form-group">
            <fmt:message key="homepage.yearOfEd"/>:
            <input class="form-control" type="text" name="yearOfEd"
                   placeholder="<fmt:message key="homepage.yearOfEd"/>" required="required"><br/>
        </div>
        <div class="form-group">

            <fmt:message key="homepage.quantity"/>:
            <input class="form-control" type="number" name="quantity" required="required"
                   placeholder="<fmt:message key="homepage.quantity"/>"
                   onchange="handleChange(this);"> <br/>
        </div>
        
        <button type="submit" class="btn btn-default"><fmt:message key="header.addBook"/></button>
    </form>


</div>
</body>
</html>
