<%-- 
    Document   : logout
    Created on : Dec 9, 2017, 4:11:17 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
     session.setAttribute("login", null);
     session.setAttribute("username", null);
    if( session.getAttribute("login") == null)
    {   
%>
     <script>
          alert("You have logged out" );
          window.location = 'index.jsp';
     </script> 
<%
       }
%>

    <c:redirect url="index.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>loging out .. !</h1>
    </body>
</html>
