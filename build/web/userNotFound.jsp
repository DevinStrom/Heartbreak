<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>


<%-- changing title source: https://coderanch.com/t/280993/java/Dynamic-HTML-page-titles-jsp --%>
<%
String pageTitle = "Heartbreak No User Found";
%>
<jsp:include page="views/header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="padding-top:30%;">
    <h1 class="center-shadow">${noUser} not found</h1>
    <br>
    <h3 class="center">This person probably found love and deleted their account. <br>Please find other heartbroken friends 🖤</h3>
    <br>
    <h3 class="center"><a href="Homepage">Homepage</a></h3>  
</div>

<c:import url="views/footer.jsp" />
