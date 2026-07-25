<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Registrar Vacante - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<div class="contenedor">

    <div class="login">

        <h1>Registrar Vacante</h1>

        <form action="RegistrarVacanteServlet" method="post">

            <%@ page import="java.util.List" %>

            <select name="empresa_id" required>

                <option value="">Seleccione una empresa</option>

                <%
                    List<String[]> empresas =
                            (List<String[]>) request.getAttribute("empresas");

                    if (empresas != null) {

                        for (String[] empresa : empresas) {
                %>

                <option value="<%= empresa[0] %>">
                    <%= empresa[1] %>
                </option>

                <%
                        }
                    }
                %>

            </select>

            <textarea
                    name="descripcion"
                    placeholder="Descripción"
                    required></textarea>

            <input
                    type="number"
                    step="0.01"
                    name="salario"
                    placeholder="Salario">

            <input
                    type="number"
                    name="empresa_id"
                    placeholder="ID de la empresa"
                    required>

            <input
                    type="date"
                    name="fecha_publicacion">

            <select name="estado">

                <option value="Activa">Activa</option>

                <option value="Cerrada">Cerrada</option>

            </select>

            <button type="submit">
                Registrar Vacante
            </button>

        </form>

        <br>

        <a href="NuevaVacanteServlet">
            Registrar Vacante
        </a>

    </div>

</div>

</body>

</html>