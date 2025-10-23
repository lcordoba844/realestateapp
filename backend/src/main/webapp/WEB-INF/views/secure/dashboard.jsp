<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    User currentUser = (User) session.getAttribute("current_user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestion Alquiler</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body>
        <h1>Bienvenido <%= currentUser.getUsername() %> :D </h1>
        <h2>Tu usuario es de tipo <%= currentUser.getRole()%></h2>
    </body>
</html>