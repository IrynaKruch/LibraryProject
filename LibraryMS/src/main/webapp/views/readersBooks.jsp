<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:import url="head.jsp"/>
<c:import url="header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" />

            <c:if test="${sessionUser.role=='ADMIN' || sessionUser.role=='LIBRARIAN'}">
                <h4><fmt:message key="reader.booksAdmin"/> ${reader.fullname} :</h4>
                email: ${reader.email}
            </c:if>


  <div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="homepage.bookname"/></th>
            <th><fmt:message key="homepage.author"/></th>
            <th><fmt:message key="reader.lendDate"/></th>
            <th><fmt:message key="reader.returnDate"/></th>
            <c:if test="${user.admin}">
                <th><fmt:message key="return" /></th>
            </c:if>
            <th><fmt:message key="reader.daysLeft" /></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="order" items="${userOrders}" varStatus="loop" >
            <tr>
                <td>${order.id}</td>
                <td> ${order.book.name}</td>
                <td> ${order.book.author} </td> 
                <td>
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${order.lendDate}"/>
                </td>
                <td>
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${order.returnDate}"/>
                </td>
                <c:if test="${user.admin}">
                    <td>
                        <form action="/Controller" method="POST">
                            <input type="hidden" name="command" value="returnBook"/>
                            <input type="hidden" name="id" value="${rb.id}"/>
                            <input type="hidden" name="page" value="readerInfo">

                            <button type="submit" class="btn btn-success"><fmt:message key="return"/></button>
                        </form>
                    </td>
                </c:if>
                <td>
				${daysLeft[key=order.id]}
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
