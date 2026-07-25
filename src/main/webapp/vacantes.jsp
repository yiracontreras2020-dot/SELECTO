<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Vacantes - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<header class="navbar">

    <h2>SELECTO</h2>

    <nav>
        <a href="ListarCandidatos">Candidatos</a>
        <a href="ListarEmpresas">Empresas</a>
        <a href="vacante.jsp">Registrar Vacante</a>
        <a href="login.jsp">Salir</a>
    </nav>

</header>

<div class="contenedor">

    <h1>Vacantes Registradas</h1>

    <a href="vacante.jsp" class="nuevo">
        + Registrar nueva vacante
    </a>

    <br><br>

    <table>

        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Descripción</th>
            <th>Salario</th>
            <th>Empresa</th>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>

        <%
            List<String[]> vacantes =
                    (List<String[]>) request.getAttribute("vacantes");

            if(vacantes != null){

                for(String[] v : vacantes){
        %>

        <tr>

            <td><%= v[0] %></td>

            <td><%= v[1] %></td>

            <td><%= v[2] %></td>

            <td><%= v[3] %></td>

            <td><%= v[4] %></td>

            <td><%= v[5] %></td>

            <td><%= v[6] %></td>

        <td>

            <a class="btn editar"
               href="EditarVacanteServlet?id=<%= v[0] %>">
                Editar
            </a>

            <a class="btn eliminar"
               href="EliminarVacanteServlet?id=<%= v[0] %>"
               onclick="return confirm('¿Eliminar esta vacante?');">
                Eliminar
            </a>

            <a class="btn postular"
               href="PostularServlet?candidato_id=1&vacante_id=<%= v[0] %>">
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

</body>

</html>