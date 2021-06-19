<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:import url="head.jsp"/>
<c:import url="header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" />

<body>
 <div class="container">
  <h2><fmt:message key="person.registration"/>:</h2>
  <form action="<c:url value="/updateUser"/>">
  			<p style="color: blue">${message}</p>
			<c:remove var="message" scope="session" />
  
    <div class="form-group">
      <label for="fullname"><fmt:message key="registration.fullname" /> : ${sessionUser.fullname} </label>
      <input type="text" class="form-control" name="fullname" placeholder="<fmt:message key="person.change" /> <fmt:message key="registration.fullname" />" >
    </div>
    <div class="form-group">
      <label for="email"><fmt:message key="registration.email" />: ${sessionUser.email} </label>
      <input type="email" class="form-control" name="email" placeholder="<fmt:message key="person.change" /> <fmt:message key="registration.email" />" >
    </div>
    <div class="form-group">
      <label for="email"><fmt:message key="login.login" />: ${sessionUser.login} </label>
      <input type="text" class="form-control" name="login" placeholder="<fmt:message key="person.change" /> <fmt:message key="login.login" />" >
    </div>
    <div class="form-group">
      <label for="email"><fmt:message key="login.password" />: ******** </label>
      <input type="password" class="form-control" name="password" placeholder="<fmt:message key="person.change" /> <fmt:message key="login.password" />" >
    </div>
    
    <button type="submit" class="btn btn-primary">Update</button>
    <span style="color: red"> 
		<c:if test="${errMessage!=null}">${errMessage}</c:if>
	</span>
		<c:remove var="errMessage" scope="session" />
  </form>
</div>
</body>
</html>