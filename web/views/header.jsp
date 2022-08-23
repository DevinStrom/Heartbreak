<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="heartbreak.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    String sessionUsername = (String) session.getAttribute("username");
    if (sessionUsername == null) {
        sessionUsername = "Please log in";
    }
%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=request.getParameter("pageTitle")%>💔</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="background-login">
    <h3>
        <div class="float-child-left"><a href="Heartbreak">Heartbreak💔</a></div>
        <div class="float-child-right"><a href="UserProfile" style="text-decoration: none;">💕 <%= sessionUsername%> </a></div>
    </h3>
</div>
<div class="background">