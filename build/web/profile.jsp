<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- changing title source: https://coderanch.com/t/280993/java/Dynamic-HTML-page-titles-jsp --%>
<%
String pageTitle = "Heartbreak Profile";
%>
<jsp:include page="views/header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>


<h1 class="center-shadow">${param.username}'s Profile</h1>
<h3 class="center-shadow">Commiserate about love?</h3>


<div class="profile-wrapper">
    <div><img src="GetImage?username=${param.username}" width="120" height="180"/></div>
    <div class="profile-username">${param.username}</div>
    <div class="profile-un-follow"> 
        <div class="background-login">
        <a href="Follow?follow=follow&followedUser=${param.username}">follow</a>&nbsp;
        <a href="Follow?follow=unfollow&followedUser=${param.username}">unfollow</a></div>
    </div>
</div>

<c:import url="tweets.jsp" /> 

<c:import url="views/footer.jsp" />

