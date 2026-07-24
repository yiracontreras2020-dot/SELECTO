<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Panel de Candidatos - Selecto</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

<header class="navbar">

    <h2>SELECTO</h2>

    <nav>
        <a href="index.jsp">Inicio</a>
        <a href="ListarCandidatos">Candidatos</a>
        <a href="#">Vacantes</a>
        <a href="#">Reportes</a>
    </nav>

</header>

<div class="contenedor-panel">

    <h1>Panel de Administración</h1>

    <p>Gestión de candidatos registrados</p>

    <div class="dashboard">

        <div class="card">
            <h3>👥</h3>
            <p>Candidatos</p>
        </div>

        <div class="card">
            <h3>💼</h3>
            <p>Vacantes</p>
        </div>

        <div class="card">
            <h3>🏢</h3>
            <p>Empresas</p>
        </div>

    </div>

    <br>

    <a href="index.jsp" class="btn">➕ Nuevo candidato</a>

    <br><br>

    <table>

        <thead>

        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Acciones</th>
        </tr>

        </thead>

        <tbody>

        <%

            List<String[]> candidatos =
                    (List<String[]>) request.getAttribute("candidatos");

            if (candidatos != null) {

                for (String[] c : candidatos) {

        %>

        <tr>

            <td><%= c[0] %></td>

            <td><%= c[1] %></td>

            <td><%= c[2] %></td>

            <td>

                <a class="editar"
                   href="editar?id=<%= c[0] %>">✏ Editar</a>

                <a class="eliminar"
                   href="EliminarServlet?id=<%= c[0] %>"
                   onclick="return confirm('¿Desea eliminar este candidato?')">
                    🗑 Eliminar
                </a>

            </td>

        </tr>

        <%

                }

            }

        %>

        </tbody>

    </table>

</div>

</body>

</html>