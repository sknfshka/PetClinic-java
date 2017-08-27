<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.08.2017
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/user/create" method="POST">
        <label>Login : </label>
        <input type="text" name="login">
        <label>Email : </label>
        <input type="text" name="email">
        <input type="submit" align="center" value="Submit"/>
    </form>
</body>
</html>
