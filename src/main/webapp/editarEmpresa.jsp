<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Editar Empresa - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<div class="contenedor">

    <div class="login">

        <h1>Editar Empresa</h1>

        <form action="ActualizarEmpresaServlet" method="post">

            <input
                    type="hidden"
                    name="id"
                    value="<%= request.getAttribute("id") %>">

            <input
                    type="text"
                    name="nombre_empresa"
                    value="<%= request.getAttribute("nombre_empresa") %>"
                    required>

            <input
                    type="text"
                    name="nit"
                    value="<%= request.getAttribute("nit") %>"
                    required>

            <input
                    type="text"
                    name="contacto"
                    value="<%= request.getAttribute("contacto") %>">

            <input
                    type="email"
                    name="correo_empresa"
                    value="<%= request.getAttribute("correo_empresa") %>"
                    required>

            <button type="submit">
                Guardar Cambios
            </button>

        </form>

        <br>

        <a href="ListarEmpresas">
            ← Volver al listado
        </a>

    </div>

</div>

</body>

</html>