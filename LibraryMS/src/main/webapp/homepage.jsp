<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>

<c:import url="/views/head.jsp" />
<c:import url="/views/header.jsp" />

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="locale" />

<html lang = "${locale}">
<div class="navbar navbar-inverse navbar-fixed-left">
	<h4 class="navbar-brand"> <a href="<c:url value="/catalogue"/>"><fmt:message key="homepage.catalogue" /></a></h4>
	<br/>	<br/>
	<ul class="nav navbar-nav navbar-fixed-left">
			<li><a><fmt:message key="homepage.sortBy" />:</a></li>
            <li><a href="<c:url value="/sortBooks?page=1&sortingType=${sortingType}&sortingColumn=name"/>">
            <fmt:message key="homepage.bookname" /></a></li>
            <li><a href="<c:url value="/sortBooks?page=1&sortingType=${sortingType}&sortingColumn=author"/>">
            <fmt:message key="homepage.author" /></a></li>
            <li><a href="<c:url value="/sortBooks?page=1&sortingType=${sortingType}&sortingColumn=yearOfEd"/>">
            <fmt:message key="homepage.yearOfEd" /></a></li>
    </ul>
</div>


<div class="container">
	<form name="searchForm" method="POST" action="searchBook">

		<div class="col-lg-11">
			<div class="input-group">
				<input type="text" name="searchText" value="${searchText}" class="form-control" 
				placeholder="<fmt:message key="homepage.searchBy"/>">
				
				<div class="input-group-btn">
 						<select name="searchType" class="form-control" style="width: 155px;">
						<option value="bookname">
							<fmt:message key="homepage.bookname"/>
						</option>
						<option value="author">
							<fmt:message key="homepage.author"/>
						</option>
						<option value="yearOfEd">
							<fmt:message key="homepage.yearOfEd" />
						</option>
					</select>

					<button id="sendButton" class="btn btn-default" type="submit">
						<fmt:message key="homepage.search" />
					</button>
				</div>
			</div>
		</div>
		<br/>
	</form>

<div class="container-lg">
<br/>
	<p style="color: blue">${message}</p>
	<c:remove var="message" scope="session" />
</div>
	
	<div class="row">	
		<c:forEach var="b" items="${catalogue}">
			<div class="col-sm-4 col-md-4 col-lg-4">
				<div class="thumbnail">
					<h4>
						<c:choose>
							<c:when test="${sessionUser.role=='ADMIN'}">
                                                            ${b.name}
                       
<%--                                 <a href="/Controller?command=bookLenders&id=${b.id}"> ${b.name}</a>
 --%>
							</c:when>
							<c:otherwise>
                                ${b.name}
                            </c:otherwise>
						</c:choose>
					</h4>
					<p>
						<fmt:message key="homepage.author" />
						: ${b.author}
					</p>
					<p>
						<fmt:message key="homepage.edition" />
						:${b.edition}
					</p>
					<p>
						<fmt:message key="homepage.yearOfEd" />
						:${b.yearOfEd}
					</p>

					<c:choose>
						<c:when test="${b.quantity>0}">
							<form name="form"  method="POST" action="order">
								<input type="hidden" name="itemId" value="${b.id}" />
								<p>
									<select name="subscribe">
										<option value="false"><fmt:message key="homepage.readingRoom" /></option>
										<option value="true"><fmt:message key="homepage.subscribe" /></option>
									</select>
								</p>
								<input class="btn btn-default" role="button" type="submit"
									value="<fmt:message key="homepage.order" />»"></input>
							</form>
						</c:when>
						<c:otherwise>
							<p>
								<fmt:message key="homepage.notAvailable" />
							</p>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${sessionUser.role=='ADMIN'}">
							<p>
								<fmt:message key="homepage.quantity" />:
							<form name="setAmount" method="POST" action="setBookAmount">
								<input type="hidden" name="bookId" value="${b.id}" /> 
								<input class="form-control" type="number" name="amount"
									required="required" value="${b.quantity}" placeholder="<fmt:message key="homepage.bookAmount" />">
								<button type="submit" class="btn btn-default" 							
								onclick="return confirm('Check all the documents are in order.')">
									<fmt:message key="homepage.setAmount" />
								</button>
							</form>
						</c:when>
					</c:choose>
					
					<br>
					<c:if test="${sessionUser.role=='ADMIN'}">
					        <c:url var="deleteBook" value="deleteBook">
       						     <c:param name="bookId" value="${b.id}"/>
  						      </c:url>
					
						<a href="${deleteBook}"
							role="button" class="btn btn-danger"
							onclick="return confirm('Are you sure you want to delete?')">
							<fmt:message key="homepage.delete" />
						</a>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
<div> 

<ul class="pagination">
   <c:if test="${numberOfPages > 1}">
      <c:if test="${currentPage != 1}">
           <li><a href="${pageContext.request.contextPath}/sortBooks?page=${currentPage - 1}&sortingColumn=${activeSortingColumn}">Previous</a></li>
      </c:if>
    	<c:forEach begin='1' end="${numberOfPages}" var='i'>
           <li><a href="${pageContext.request.contextPath}/sortBooks?page=${i}&sortingColumn=${activeSortingColumn}">${i}</a></li>
        </c:forEach>
      <c:if test="${currentPage < numberOfPages}">
           <li><a href="${pageContext.request.contextPath}/sortBooks?page=${currentPage + 1}&sortingColumn=${activeSortingColumn}">Next</a></li>
      </c:if>
   </c:if>
</ul>
</div>


<hr />
</div>

<script>
	function handleChange(input) {
		if (input.value < 0)
			input.value = 0;
		if (input.value > 100)
			input.value = 100;
	}

</script>

</body>
</html>







