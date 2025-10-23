<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestion Alquiler</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>

    <body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="card w-96 bg-base-100 shadow-xl">
        <div class="card-body">
            <h2 class="card-title justify-center">Iniciar Sesión</h2>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <div class="alert alert-error mt-2">
                <span><%= errorMessage %></span>
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/Login" method="post" class="space-y-4 mt-4">
                <div class="form-control">
                    <label class="label">
                        <span class="label-text">Usuario</span>
                    </label>
                    <input type="text" name="username" placeholder="Ingrese su usuario" class="input input-bordered w-full" required>
                </div>

                <div class="form-control">
                    <label class="label">
                        <span class="label-text">Contraseña</span>
                    </label>
                    <input type="password" name="password" placeholder="Ingrese su contraseña" class="input input-bordered w-full" required>
                </div>

                <button type="submit" class="btn btn-primary w-full mt-2">Iniciar Sesión</button>
            </form>
            <form action="${pageContext.request.contextPath}/Login" method="post">
                <div class="mt-4 text-center">
                    <a href="#" class="link link-primary">Aun no estás registrado?</a>
                </div>
            </form>
        </div>
    </div>
    </body>
</html>
