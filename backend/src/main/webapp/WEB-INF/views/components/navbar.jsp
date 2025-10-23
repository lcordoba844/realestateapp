
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="model.User"  %>
<%@page import="util.LoginGuardJsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NavBar</title>
    <!-- Bootstrap -->
    <link href="bootstrap-4.4.1.css" rel="stylesheet">

    <%
        User current_user = (User) session.getAttribute("current_user");
        String rutaRedirect = null;
        if (current_user == null) {
            rutaRedirect = "./index.jsp";
        } else if (current_user.getRol() == User.Rol.ADMIN) {
            rutaRedirect = "./inicioAdmin.jsp";
        } else {
            rutaRedirect = "./inicioCliente.jsp";
        }
    %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href=<%=rutaRedirect%>>GestAlq</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href=<%=rutaRedirect%>>Inicio</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="PropiedadServlet?action=listarTodasPropiedades">Mis Propiedades</a>
            </li>
            <li class="nav-item">
            </li>
            <%
                if (current_user != null && current_user.getRol() == User.Rol.ADMIN) {
            %>
            <li class="nav-item active">
                <a class="nav-link" href="ClienteServlet">Clientes</a>
            </li>

            <%
                }
            %>
        </ul>
        <% if (current_user == null) { %>
        <form action="index.jsp" method="post">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="logIn">Iniciar Sesión</button>
        </form>

        <% } else { %>

        <form action="ManageSession" method="post" class="form-inline my-2 my-lg-0">
            <button class="btn btn-outline-danger my-2 my-sm-0" type="submit" name="action" value="logOut">LogOut</button>
        </form>

        <% } %>
    </div>
</nav>
</body>
</html>