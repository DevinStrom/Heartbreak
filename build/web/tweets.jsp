<%--
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="heartbreak.*" %>


            
<c:forEach var="tweet" items="${tweets}"> 
    <div class="wrapper">
        <%-- source statement <%= request.getSession().getAttribute("username") %> from https://stackoverflow.com/questions/10993429/print-session-attributes-in-jsp
            answer by Kazekage Gaara, 6-12-12 --%>
        <div class="center-like-photo"><a href="Likes?tweet_id=${tweet.id}&username=<%= request.getSession().getAttribute("username") %>&like=like">like</a><br>${tweet.getTweet_Likes()}<br>
            <a href="Likes?tweet_id=${tweet.id}&username=<%= request.getSession().getAttribute("username") %>&like=dislike">dislike</a><br>${tweet.getTweet_Dislikes()}
        </div> 
        <div class="center-like-photo">
            <img src="GetImage?username=${tweet.username}" width="60" height="100"/>
        </div>
        <%-- sending name as get param idea: https://docs.oracle.com/cd/A97329_03/web.902/a95882/basics.htm#1014110 --%>
        <div class="center-like-photo"> <div class="background-login"><a href="Profile?username=${tweet.username}">${tweet.username}</a></div></div>
        <div class="left-text">${tweet.text}</div> 
    </div>
</c:forEach>
