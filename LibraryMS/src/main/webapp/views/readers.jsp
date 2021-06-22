<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>

<c:import url="/views/head.jsp" />
<c:import url="/views/header.jsp" />

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<html lang = "${locale}">

<div class="container">


    <form name="searchForm" method="POST" action="/searchReaders">


        <div class="col-lg-11">
            <div class="input-group">
                <input type="text" name="search" class="form-control" value="${searchText}">

                <div class="input-group-btn">
                    <select name="selected" class="form-control" style="width: 145px;">
                        <option><fmt:message key="registration.fullname"/></option>
                        <option><fmt:message key="registration.email"/></option>
                    </select>

                    <button id="sendButton" class="btn btn-default" type="submit"  style="width:95px;">
                    <fmt:message key="homepage.search"/></button>
                </div>
            </div>
        </div>

<hr/>
    </form>

    <div class="col-md-12">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="registration.fullname"/></th>
                <th><fmt:message key="registration.email"/></th>
                <th><fmt:message key="debt"/></th>
                <th><fmt:message key="role"/></th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="r" items="${readers}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td><a href="<c:url value="/readerforAdmin?id=${r.id} "/>"> ${r.fullname} </a>
                </td>
                <td>${r.email}</td>
                <td>
                	<c:choose>
						<c:when test="${readerDebt[key=r.id]!=null}">
						${readerDebt[key=r.id]}.00$
							</c:when>
							<c:otherwise>
							-
                            </c:otherwise>
						</c:choose>
                </td>
                <td>${r.role}
                <c:if test="${sessionUser.role == 'ADMIN'}">
                <form class="form-inline" name="form"  method="POST" action="setRole">
					<input type="hidden" name="userId" value="${r.id}" />
						<p>
						<select name="role">
							<option value="ADMIN"><fmt:message key="readers.admin" /></option>
							<option value="LIBRARIAN"><fmt:message key="readers.librarian" /></option>
							<option value="READER"><fmt:message key="readers.reader" /></option>
							<option value="BLOCKED"><fmt:message key="readers.block" /></option>
						</select>
						</p>
					<input  role="button" type="submit"
						value="<fmt:message key="readers.changeRole" />"></input>
				</form>
				</c:if>
				</td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
    </div>
</div>
</body>
</html>
