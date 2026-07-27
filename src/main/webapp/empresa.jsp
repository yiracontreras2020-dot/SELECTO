<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Registrar Empresa - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<body>

<%@ include file="menu.jsp" %>

<div class="contenedor">

    <div class="login">

        <h1>SELECTO</h1>

        <p>Registro de Empresas</p>

        <form action="RegistrarEmpresa" method="post">

            <input
                    type="text"
                    name="nombre_empresa"
                    placeholder="Nombre de la empresa"
                    required>

            <input
                    type="text"
                    name="nit"
                    placeholder="NIT"
                    required>

            <input
                    type="text"
                    name="contacto"
                    placeholder="Nombre del contacto">

            <input
                    type="email"
                    name="correo_empresa"
                    placeholder="Correo de la empresa"
                    required>

            <button type="submit">
                Registrar Empresa
            </button>

        </form>

        <br>

        <a href="ListarEmpresas">
            ← Volver al listado
        </a>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>
</html>