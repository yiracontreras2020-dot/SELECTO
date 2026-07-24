<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Registrar Empresa - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

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

</body>
</html>