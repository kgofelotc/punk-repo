<%-- 
    Document   : error
    Created on : 24 Feb 2017, 5:49:26 PM
    Author     : F4888723
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signature Error</title>
    </head>
       <%
        String errorMessage = (String)request.getAttribute("errorMessage");
        %>
    <body>
        <h3><% out.println(errorMessage); %></h3>
    </body>
</html>
