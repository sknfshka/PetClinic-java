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
    <link rel="stylesheet" type="text/css" href="../../css/style.css" />
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/add-client" method="POST">
    <label for="name" >Имя : </label>
    <input type="text" name="name" id="name">
    <label for="petName" >Имя питомца : </label>
    <input type="text" name="petName" id="petName">
    <label for="age" >Возраст : </label>
    <input type="text" name="age" id="age">
    <label for="kind">Вид питомца :</label>
    <select name="kind" id="kind">
        <option value="Dog" selected >Dog</option>
        <option value="Cat">Cat</option>
    </select>
    <input type="submit" align="center" value="Добавить"/>
</form>
</body>
</html>
