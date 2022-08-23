<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- changing title source: https://coderanch.com/t/280993/java/Dynamic-HTML-page-titles-jsp --%>
<%-- this isn't el style... I couldn't figure out how to change pagetile dynamically with el. --%>
<%
String pageTitle = "Heartbreak Login";
%>
<jsp:include page="views/header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

    <h1 class="center-shadow">Heartbreak</h1>
    <h3 class="center-shadow">Login/Register</h3>
    <div class="center">
        <br><br>
        <h3>Login</h3>
        <form action="Login" method="post">
            <label>User Name</label>
            <input type="text" name="username"/><br>
            <label>Password</label>
            <input type="password" name="password"/><br>
            <input type="hidden" name="action" value="login"/>
            <input type="submit" value="Login"/>
        </form>
        <br><br>
        <h3>Register Account</h3>
        <form action="Login" method="post">
            <label>User Name</label>
            <input type="text" name="username"/><br>
            <label>Password</label>
            <input type="password" name="password"/><br>
            
            <input type="hidden" name="action" value="register"/>

            <input type="submit" value="Register"/>
        </form>
    </div>
        <c:import url="views/footer.jsp" />
