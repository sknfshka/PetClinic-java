<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.08.2017
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Клиника</title>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/views/clinic/AddClient.jsp">Добавить клиента</a>
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Имя</td>
            <td>Имя питомца</td>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr valign="top">
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.getPetName()}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/clinic/edit-client?id=${client.id}">Редактировать</a>
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/clinic/delete-client?id=${client.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
