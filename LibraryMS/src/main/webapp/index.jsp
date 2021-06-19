<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="ua" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>PhraseApp - i18n</title>
</head>
<body>
    <h2>
        <fmt:message key="login.login" />
        <fmt:message key="login.password" />
        <fmt:message key="ukrainian" />
        <fmt:message key="english" />
        
    </h2>
</body>
</html>