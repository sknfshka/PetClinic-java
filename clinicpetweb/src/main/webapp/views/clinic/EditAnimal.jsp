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
    <title>Редактирование питомца ${animal.name}</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css" />
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/edit-animal" method="POST">
    <p>Редактирование питомца "${animal.name}"</p>
    <input type="hidden" name="id" value="${animal.id}">
    <input type="hidden" name="userId" value="${userId}">
    <label for="name">Имя : </label>
    <input type="text" name="name" id="name" value="${animal.name}">
    <label for="age">Возраст : </label>
    <input type="text" name="age" id="age" value="${animal.age}">
    <label for="kind">Вид питомца :</label>
        <select name="kind" id="kind">
            <c:set var = "petKing" scope = "session" value = "${animal.kind.toString()}"/>
            <c:set var = "otherPetKing" scope = "session" value = "${petKing == 'Dog' ? 'Cat' :  'Dog'}"/>
            <option value="${petKing}" selected>"${petKing}"</option>
            <option value="${otherPetKing}">"${otherPetKing}"</option>
        </select>
    <input type="submit" align="center" value="Применить"/>
</form>
</body>
</html>