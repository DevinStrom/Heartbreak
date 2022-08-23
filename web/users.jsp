<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>

<%--
Page from Lecture Week 12 for CIS 2454 Summer 2022 by Prof. Charnesky
Not used in my final project but saved for testing purposes
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Users</h2>
        <table>
            <tr>ID</tr>
            <tr>Username</tr>
            <tr>Password Hash</tr>
            
            <c:forEach var="user" items="${users}"> 
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.password}" /></td>
                </tr>
            </c:forEach>
        </table>   
    </body>
</html>
