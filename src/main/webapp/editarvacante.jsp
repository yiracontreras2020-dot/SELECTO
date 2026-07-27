<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Editar Vacante - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<body>

<%@ include file="menu.jsp" %>

<div class="contenedor">

    <div class="login">

        <h1>Editar Vacante</h1>

        <form action="ActualizarVacanteServlet" method="post">

            <input
                    type="hidden"
                    name="id"
                    value="<%= request.getAttribute("id") %>">

            <input
                    type="text"
                    name="titulo"
                    value="<%= request.getAttribute("titulo") %>"
                    required>

            <textarea
                    name="descripcion"
                    required><%= request.getAttribute("descripcion") %></textarea>

            <input
                    type="number"
                    step="0.01"
                    name="salario"
                    value="<%= request.getAttribute("salario") %>"
                    required>

            <input
                    type="number"
                    name="empresa_id"
                    value="<%= request.getAttribute("empresa_id") %>"
                    required>

            <input
                    type="date"
                    name="fecha_publicacion"
                    value="<%= request.getAttribute("fecha_publicacion") %>">

            <select name="estado">

                <option value="Activa"
                    <%= "Activa".equals(request.getAttribute("estado")) ? "selected" : "" %>>
                    Activa
                </option>

                <option value="Cerrada"
                    <%= "Cerrada".equals(request.getAttribute("estado")) ? "selected" : "" %>>
                    Cerrada
                </option>

            </select>

            <button type="submit">
                Guardar Cambios
            </button>

        </form>

        <br>

        <a href="ListarVacantes">
            ← Volver al listado
        </a>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>

</html>