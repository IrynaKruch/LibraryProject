<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:import url="head.jsp"/>
<c:import url="header.jsp"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>




<div class="col-md-10">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="registration.fullname"/></th>
            <th><fmt:message key="homepage.bookname"/></th>
            <th><fmt:message key="homepage.author"/></th>
            <th><fmt:message key="reader.daysLeft" /></th>
            <th>
 			 <div class="dropdown">
 			   <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
 			   <fmt:message key="homepage.kindOfOrder" />
    			<span class="caret"></span></button>
    			<ul class="dropdown-menu">
      			<li><a href="<c:url value="/searchOrders?type=true"/>">
      			<fmt:message key="homepage.subscribe" /></a></li>
      			<li><a href="<c:url value="/searchOrders?type=false"/>">
      			<fmt:message key="homepage.readingRoom" /></a></li>
      			<li><a href="<c:url value="/searchOrders"/>">
      			<fmt:message key="homepage.allorders" /></a></li>
    			</ul>
  			</div>
            </th>
            <th><fmt:message key="librarian.giveBook"/></th>
            <th><fmt:message key="librarian.return"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${orders}" var="o" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td><a href="<c:url value="/readerforAdmin?id=${o.user.id} "/>"> ${o.user.fullname} </a></td>
                <td> ${o.book.name}</td>
                <td> ${o.book.author}</td>
                <td>
				${daysLeft[key=o.id]}
                </td>
                <td>
				    <c:choose>
                      <c:when test="${o.subscribe==false}">
						<fmt:message key="homepage.readingRoom" />
                            </c:when>
                            <c:otherwise>
						<fmt:message key="homepage.subscribe" />
                            </c:otherwise>
                    </c:choose>
				</td>
                <td>
                    <form action="processOrder" method="POST">
                        <input type="hidden" name="id" value="${o.id}"/>
                        <div>
                            <input class="form-inline" type="number" name="days" required="required" placeholder="days"
                                   onchange="handleChange(this);">
                        </div>
                        <button type="submit" class="btn btn-success"><fmt:message key="librarian.giveBook"/></button>
                    </form>
                </td>
                <td>
                    <form action="returnBook" method="POST">
                        <input type="hidden" name="orderId" value="${o.id}"/>
                      <button type="submit" class="btn btn-danger"><fmt:message key="librarian.return"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
</div>
<script>
    function handleChange(input) {
      if (input.value > 50) input.value = 50;
    }
</script>
</body>

</html>
