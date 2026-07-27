<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Empresas - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<body>

<%@ include file="menu.jsp" %>

<header class="navbar">

    <h2>SELECTO</h2>

    <nav>
        <a href="ListarCandidatos">Candidatos</a>
        <a href="empresa.jsp">Registrar Empresa</a>
        <a href="login.jsp">Salir</a>
    </nav>

</header>

<div class="contenedor">

    <h1>Empresas Registradas</h1>

    <table>

        <tr>
            <th>ID</th>
            <th>Empresa</th>
            <th>NIT</th>
            <th>Contacto</th>
            <th>Correo</th>
            <th>Acciones</th>
        </tr>

        <%
            List<String[]> empresas =
                    (List<String[]>) request.getAttribute("empresas");

            if(empresas != null){

                for(String[] e : empresas){
        %>

        <tr>

            <td><%= e[0] %></td>

            <td><%= e[1] %></td>

            <td><%= e[2] %></td>

            <td><%= e[3] %></td>

            <td><%= e[4] %></td>

            <td>

                <a class="btn editar"
                   href="EditarEmpresaServlet?id=<%= e[0] %>">
                    Editar
                </a>

                <a class="btn eliminar"
                   href="EliminarEmpresaServlet?id=<%= e[0] %>"
                   onclick="return confirm('¿Eliminar esta empresa?');">
                    Eliminar
                </a>



            </td>

        </tr>

        <%
                }
            }
        %>

    </table>

    <br>

    <a href="empresa.jsp" class="nuevo">
        + Registrar nueva empresa
    </a>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>

</html>