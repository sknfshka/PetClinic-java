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
    <link rel="stylesheet" type="text/css" href="../../css/style.css" />
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/views/clinic/AddClient.jsp">Добавить клиента</a>
    <table border="1">
        <tr>
            <td>Имя</td>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr valign="top">
                <td>${client.name}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">Редактировать</a>
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/client/delete?id=${client.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
