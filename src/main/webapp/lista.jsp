<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Candidatos - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<%@ include file="menu.jsp" %>


<div class="contenedor">

    <div class="titulo">

        <h1>Gestión de Candidatos</h1>

        <p>Listado de candidatos registrados en SELECTO</p>

    </div>


    <!-- Estadísticas -->

    <div class="cards">

        <div class="card">

            <h2>
                <%= request.getAttribute("totalCandidatos") %>
            </h2>

            <p>Candidatos registrados</p>

        </div>


        <div class="card">

            <h2>
                <%= request.getAttribute("totalEmpresas") %>
            </h2>

            <p>Empresas registradas</p>

        </div>

    </div>


    <!-- Registrar candidato -->

    <a href="index.jsp" class="nuevo">

        + Registrar nuevo candidato

    </a>


    <br><br>


    <!-- Tabla de candidatos -->

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

            if (candidatos != null && !candidatos.isEmpty()) {

                for (String[] candidato : candidatos) {

        %>

        <tr>

            <td>
                <%= candidato[0] %>
            </td>

            <td>
                <%= candidato[1] %>
            </td>

            <td>
                <%= candidato[2] %>
            </td>

            <td>

                <a class="btn editar"
                   href="editar.jsp?id=<%= candidato[0] %>">

                    Editar

                </a>


                <a class="btn eliminar"
                   href="EliminarServlet?id=<%= candidato[0] %>"
                   onclick="return confirm('¿Deseas eliminar este candidato?');">

                    Eliminar

                </a>

            </td>

        </tr>

        <%

                }

            } else {

        %>

        <tr>

            <td colspan="4" class="text-center">

                No hay candidatos registrados.

            </td>

        </tr>

        <%

            }

        %>

        </tbody>

    </table>

</div>


<%@ include file="footer.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js">
</script>

</body>

</html>