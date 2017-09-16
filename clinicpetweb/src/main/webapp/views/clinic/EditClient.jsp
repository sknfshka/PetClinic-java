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
    <title>Редактирование клиента ${client.name}</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css" />
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/client/edit" method="POST">
    <p>Редактирование клиента "${client.name}"</p>
    <input type="hidden" name="id" value="${client.id}">
    <label for="name">Имя : </label>
    <input type="text" name="name" id="name" value="${client.name}" required>
    <input type="submit" align="center" value="Применить"/>

    <table border="1">
        <tr>
            <td>Id</td>
            <td>Имя</td>
            <td>Возраст</td>
            <td>Вид</td>
        </tr>
        <c:forEach items="${animals}" var="animal" varStatus="status">
            <tr valign="top">
                <td>${animal.name}</td>
                <td>${animal.age}</td>
                <td>${animal.kind.toString()}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/animal/edit?id=${animal.id}&clientId=${client.id}">Редактировать</a>
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/animal/delete?id=${animal.id}&clientId=${client.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="${pageContext.servletContext.contextPath}/animal/create?clientId=${client.id}">Добавить питомца</a>
</form>
</body>
</html>