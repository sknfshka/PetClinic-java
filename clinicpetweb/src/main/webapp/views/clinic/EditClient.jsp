<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.08.2017
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактирование клиента ${client.id}</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/edit-client" method="POST">
    <p>Редактирование клиента "${client.id}"</p>
    <label>Имя : </label>
    <input type="text" name="name" value="${client.name}">
    <label>Имя питомца : </label>
    <input type="text" name="petName" value="${client.getPetName()}">
    <input type="hidden" name="id" value="${client.id}">
    <input type="submit" align="center" value="Применить"/>
</form>
</body>
</html>