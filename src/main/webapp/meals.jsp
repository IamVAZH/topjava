<%--
  Created by IntelliJ IDEA.
  User: valerijzarkov
  Date: 18.02.2022
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border: solid black;
            border-collapse: collapse;
            table-layout: fixed;
        }
        td {
            border: solid black;
            text-align: left;
        }
        tr {
            border: solid black;
            text-align: center;
        }
        .exceeded {
            color: darkred;
        }
        .nexceeded {
            color: darkgreen;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h4><a href="meals?action=create">Add meal</a></h4>
<table>
    <thead>
    <td>Date</td>
    <td>Description</td>
    <td>Calories</td>
    <td></td>
    <td></td>
    </thead>
    <c:forEach var="meal" items="${meals}">
        <tr class= "${meal.excess ? "exceeded" : "nexceeded"}">
            <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="mealDate"/>
                <fmt:formatDate value="${mealDate}" pattern="dd.MM.yyyy HH:mm"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<h2>
</h2>
</body>
</html>

