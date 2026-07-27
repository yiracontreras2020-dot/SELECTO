<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Vacantes Disponibles - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<body>

<%@ include file="menu.jsp" %>

<header class="navbar">

    <h2>SELECTO</h2>

    <nav>
        <a href="login.jsp">Cerrar sesión</a>
    </nav>

</header>

<div class="contenedor">

    <h1>Vacantes Disponibles</h1>

    <table>

        <tr>
            <th>Título</th>
            <th>Descripción</th>
            <th>Salario</th>
            <th>Empresa</th>
            <th>Acción</th>
        </tr>

        <%
            List<String[]> vacantes =
                    (List<String[]>) request.getAttribute("vacantes");

            if (vacantes != null) {

                for (String[] v : vacantes) {
        %>

        <tr>

            <td><%= v[1] %></td>

            <td><%= v[2] %></td>

            <td>$ <%= v[3] %></td>

            <td><%= v[4] %></td>

            <td>

                <a class="btn"
                   href="PostularServlet?candidato_id=16&vacante_id=<%= v[0] %>">
                    Postularme
                </a>

            </td>

        </tr>

        <%
                }
            }
        %>

    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>

</html>