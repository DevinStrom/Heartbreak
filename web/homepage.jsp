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
String pageTitle = "Heartbreak Homepage";
%>
<jsp:include page="views/header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>



<h1 class="center-shadow">Homepage</h1>
<h3 class="center-shadow">Musing hearts broken</h3>

<%-- source for text area: https://www.w3schools.com/tags/tag_textarea.asp --%>
<div class="center">
    <br><br>
        <h3>Still single? Tweet about it</h3>
        <form action="AddTweet" method="post">
            <textarea name="tweetText" rows="3" cols="50"></textarea>
            <br>
            <input type="hidden" name="action" value="addtweet"/>
            <input type="submit" value="Tweet">
        </form>
</div>

   
<c:import url="tweets.jsp" />

<c:import url="views/footer.jsp" />