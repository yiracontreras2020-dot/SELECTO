<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Selecto</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

<div class="contenedor">

    <div class="login">

        <h1>SELECTO</h1>

        <p>Sistema de selección de personal</p>

        <form action="registro" method="post">

            <input type="text"
                   name="nombre"
                   placeholder="Nombre completo"
                   required>

            <input type="email"
                   name="correo"
                   placeholder="Correo electrónico"
                   required>
            <input type="password"
                   name="password"
                   placeholder="Contraseña"
                   required>

            <button type="submit">
                Registrar
            </button>

        </form>

    </div>

</div>

<script src="js/script.js"></script>

</body>
</html>