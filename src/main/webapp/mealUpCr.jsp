<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meal</title>
</head>
<body>
<h2>Edit meal</h2>
<form method="post">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="hidden" name="id" value="${meal.id}">
    <label>DateTime:&nbsp;&nbsp;&nbsp;</label>
    <input type="datetime-local" name="dateTime" value="${meal.dateTime}">
    <br>
    <label>Description:&nbsp;</label>
    <input type="text" name="description" value="${meal.description}">
    <br>
    <label>Calories:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
    <input type="text" name="calories" value="${meal.calories}">
    <br>
    <input type="submit" value="Save">
    <input type="button" value="Cancel" onclick="window.history.back()">
</form>
</body>
</html>