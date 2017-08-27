<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.08.2017
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавление клиента</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/add-client" method="POST">
    <label>Имя : </label>
    <input type="text" name="name">
    <label>Имя питомца : </label>
    <input type="text" name="petName">
    <input type="submit" align="center" value="Добавить"/>
</form>
</body>
</html>
