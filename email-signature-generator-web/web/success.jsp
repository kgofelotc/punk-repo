<%-- 
    Document   : success
    Created on : 24 Feb 2017, 5:50:15 PM
    Author     : F4888723
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signature Location</title>
    </head>
     <%
        String signatureLocation = (String)request.getAttribute("signatureLocation");
        %>
    <body>
        <h3>Signature successfully generated @ location <a href="<% out.println(signatureLocation); %>"><% out.println(signatureLocation); %></a></h3>       
    </body>
</html>
