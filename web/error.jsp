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
String pageTitle = "Heartbreak Error";
%>
<jsp:include page="views/header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>


<h1 class="center-shadow">Error</h1>
<h3 class="center-shadow">You broke this site (like your heart)</h3>

<br><br>
<p>Error: ${error}</p>


<c:import url="views/footer.jsp" />

