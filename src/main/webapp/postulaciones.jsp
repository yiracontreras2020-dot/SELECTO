<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Postulaciones - SELECTO</title>
    <link rel="stylesheet" href="css/styles.css">
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<body>

<%@ include file="menu.jsp" %>

<div class="contenedor">

    <div class="login">

        <h1>Postulaciones</h1>

        <table border="1" width="100%">
            <tr>
                <th>ID</th>
                <th>Candidato</th>
                <th>Correo</th>
                <th>Vacante</th>
            </tr>

            <%
                List<String[]> postulaciones =
                        (List<String[]>) request.getAttribute("postulaciones");

                if(postulaciones != null){

                    for(String[] p : postulaciones){
            %>

            <tr>
                <td><%= p[0] %></td>
                <td><%= p[1] %></td>
                <td><%= p[2] %></td>
                <td><%= p[3] %></td>
            </tr>

            <%
                    }
                }
            %>

        </table>

        <br>

        <a href="ListarVacantes">Volver a Vacantes</a>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>
</html>