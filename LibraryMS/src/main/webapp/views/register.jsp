<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>


<body data-gr-c-s-loaded="true">
<div class="container">
    <form class="form-signin" name="registrationForm" method="POST" action="<c:url value="/register"/>">
<!--         <input class="form-control" type="hidden" name="command" value="register"/>
 -->        	<fmt:message key="registration.fullname" />:<br/>
        <input class="form-control" type="text" name="fullname"
               placeholder="<fmt:message key="registration.fullname" />" required="required">
        	<fmt:message key="registration.email" /> :<br/>
        <input class="form-control" type="email" name="email"
               placeholder="<fmt:message key="registration.email" />" required="required">
        	<fmt:message key="login.login" />:<br/>
        <input class="form-control" type="text" name="login"
               placeholder="<fmt:message key="login.login" />" required="required">
        	<fmt:message key="login.password" />:<br/>
        <input class="form-control" type="password" name="password"
               placeholder="<fmt:message key="login.password" />" required="required">
        <button class="btn btn-lg btn-primary btn-block" type="submit">
        	<fmt:message key="login.registration" /></button>
		<span style="color: red"> 
		<c:if test="${errMessage!=null}">${errMessage}</c:if>
		</span>
			<c:remove var="errMessage" scope="session" />
    </form>

    	<a
			href="?sessionLocale=ua">
			<fmt:message key="ukrainian" />
		</a> <a
			href="?sessionLocale=en">
			<fmt:message key="english" />
		</a>

<b><a href="${pageContext.request.contextPath}/"><fmt:message key="login.login" /></a></b>

</div>
</body>
</html>




<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>Register</title>
<script> 
function validate()
{ 
     var fullname = document.form.fullname.value;
     var email = document.form.email.value;
     var login = document.form.login.value; 
     var password = document.form.password.value;
     var conpassword= document.form.conpassword.value;
     
     if (fullname==null || fullname=="")
     { 
     alert("Full Name can't be blank"); 
     return false; 
     }
     else if (email==null || email=="")
     { 
     alert("Email can't be blank"); 
     return false; 
     }
     else if (login==null || login=="")
     { 
     alert("Username can't be blank"); 
     return false; 
     }
     else if(password.length<6)
     { 
     alert("Password must be at least 6 characters long."); 
     return false; 
     } 
     else if (password!=conpassword)
     { 
     alert("Confirm Password should match with the Password"); 
     return false; 
     } 
 } 
</script> 
</head>
<body>
<h2 align="center">Registration Form  </h2>
    <form name="form" action="Servlet" method="post" onsubmit="return validate()">
            <input type="hidden" name="command" value="register"/>
        <table align="center">
         <tr>
         <td>Full Name</td>
         <td><input type="text" name="fullname" /></td>
         </tr>
         <tr>
         <td>Email</td>
         <td><input type="text" name="email" /></td>
         </tr>
         <tr>
         <td>Login</td>
         <td><input type="text" name="login" /></td>
         </tr>
         <tr>
         <td>Password</td>
         <td><input type="password" name="password" /></td>
         </tr>
         <tr>
         <td>Confirm Password</td>
         <td><input type="password" name="conpassword" /></td>
         </tr>
         <tr>
         <td><span style="color:red">
         
         <%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%>
         
         </span></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Register"></input><input
         type="reset" value="Reset"></input></td>
         </tr>
        </table>
    </form>
</body>
</html> --%>