<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="heartbreak.*" %>

<%-- changing title source: https://coderanch.com/t/280993/java/Dynamic-HTML-page-titles-jsp --%>
<%-- this isn't el style... I couldn't figure out how to change pagetile dynamically with el. --%>
<%
String pageTitle = "Heartbreak User Profile";
%>
<jsp:include page="views/header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>


<%-- idea for below code "User currentUser..." based on https://stackoverflow.com/questions/50025627/retrieving-the-attribute-value-from-the-request-getsession 
    answer by lucumt 4-18-2018 --%>
<% 
    User currentUser = UserModel.getUser((String)request.getSession().getAttribute("username")); 
    Integer user_id = currentUser.getId();
%>

<div class="center">
    <h1 class="center-shadow">Hello ${username}</h1>
    <h3 class="center-shadow">Did you find love?😂</h3>
    <br>
    <c:if test="${(filename != null)}">
        <img src="GetImage?username=${username}" width="140" height="200"/>
    </c:if>

    <%-- forms based on lecture week 12 and week 13 CIS 2454 lectures, Prof. Charnesky --%>
    <h3>Change your profile picture!</h3>
    <form action="Upload" method="post" enctype="multipart/form-data">
        <div id="data">       
            <input type="file" accept="image/*" name="file"><br>
            <input type="submit" value="Upload">
        </div>
    </form>
    
    <br><br>
    
    <h3>View your tweets</h3>
    <p class="background-login"><a href="Profile?username=${username}">Click here</a></p>

    <br><br>

    <h3>Update user</h3>
    <p class="userProfile">You will be logged out after changing these</p>
    <form action="Heartbreak" method="post">     
        <label>User Name</label>
        <input type="text" name="username"/><br>
        <label>Password</label>
        <input type="password" name="password"/><br>
        <input type="hidden" name="id" value="<%= user_id %>" />
        <input type="hidden" name="action" value="updateUser"/>
        <input type="submit" value="Update User"/>
    </form>

    <br><br>

    <h3>Delete user</h3>
    <p class="userProfile">I hope you found the love of your life!<br>🤍This is final🤍 </p>
    <form action="Heartbreak" method="post">
        <input type="hidden" name="id" value="<%= user_id %>" />
        <input type="hidden" name="action" value="deleteUser"/>
        <input type="submit" value="Delete User"/>
    </form>
</div>
        
<c:import url="views/footer.jsp" />
