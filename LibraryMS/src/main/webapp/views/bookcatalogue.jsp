<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sorted books</title>
<style type="text/css">
  #div0{display:table-cell;width:50%;}
  #div1{float:left;text-align:center;display:table-cell;background-color:#9C8DEB}
  #div2{float:left;text-align:center;display:table-cell;background-color:#41EC88}
  #div3{width:361px; overflow:auto;background-color:yellow;}
</style>
</head>
<body>
    <div id="div0">
        
        <div id="div2">
            <table border="1" cellpadding="4" cellspacing="0">
                <tr>
                 <th><a href="${pageContext.request.contextPath}/sortBooks?page=1&sortingType=ASC&sortingColumn=name">
                     Book name
                    </a></th>
                    <th><a href="${pageContext.request.contextPath}/sortBooks?page=1&sortingType=ASC&sortingColumn=author">
                     Book author
                    </a></th>
                </tr>
         
                <c:forEach var="book" items="${catalogue}">
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.author}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
 
  <div> 
   <c:if test="${numberOfPages > 1}">
       <div style="float: left;">
              <!--For displaying previous link except for the 1st page -->
                <c:if test="${currentPage != 1}">
                    <td><a href="${pageContext.request.contextPath}/sortBooks?page=${currentPage - 1}&sortingType=${activeSortingType}&sortingColumn=${activeSortingColumn}">Previous</a></td>
                </c:if>
            </div>
      
       <div id="div3" style="float: left;"> 
                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <table border="1" cellpadding="3" cellspacing="0">
                    <tr>
                        <c:forEach begin='1' end="${numberOfPages}" var='i'>
                            <c:choose>
                                <c:when test="${currentPage == i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${pageContext.request.contextPath}/sortBooks?page=${i}&sortingType=${activeSortingType}&sortingColumn=${activeSortingColumn}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>
            </div>
         
         <div style="float: left;">
                <%--For displaying Next link except for the last page --%>
                <c:if test="${currentPage lt numberOfPages}">
                    <td><a href="${pageContext.request.contextPath}/sortBooks?page=${currentPage + 1}&sortingType=${activeSortingType}&sortingColumn=${activeSortingColumn}">Next</a></td>
                </c:if>
            </div>
        </c:if>
    </div>
</body>
</html>

<%-- <html>
<head>
<meta charset="UTF-8">
<title>BookCatalogue</title>
</head>
<body>
	<h2>Catalogue of Books</h2>

	<p style="color: blue">${message}</p>
	<c:remove var="message" scope="session" />

	<form action="Servlet" method="post">
		<input type="hidden" name="command" value="searchBook" />
		<div class="form-group">
			<label for="searchType">Search by: 
			<!-- <input type="hidden"
				class="form-control add" required> --></label> 
				<select name="searchType">
				<option value="author">Author</option>
				<option value="bookname">Name</option>
				<option value="yearOfEd">year</option>
			</select> <input type="submit" value="Search">
		</div>
		<div class="form-group">
			<label for="query">Search term <input type="text"
				class="form-control add" name="searchText" placeholder="search" /></label>
		</div>
	</form>

	<form name="form" action="Servlet" method="post">
		<input type="hidden" name="command" value="order" />
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>author</th>
					<th>edition</th>
					<th>year of edition</th>
					<th>select</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${catalogue}">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.author}</td>
						<td>${item.edition}</td>
						<td>${item.yearOfEd}</td>
						<td><input type="checkbox" name="itemId" value="${item.id}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <select name="subscribe">
			<option value="false">Reading Room</option>
			<option value="true">Subscribe for a month</option>
		</select> <input type="submit" value="Order"></input>

	</form>
</body>
</html> --%>