<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

<div class="contenedor">

    <div class="login">

        <h1>SELECTO</h1>

        <p>Panel de Administración</p>

        <form action="login" method="post">

            <input
                    type="text"
                    name="usuario"
                    placeholder="Usuario"
                    required>

            <input
                    type="password"
                    name="password"
                    placeholder="Contraseña"
                    required>

            <button type="submit">
                Iniciar sesión
            </button>

        </form>

        <br>

        <a href="index.jsp">
            ← Volver al registro
        </a>

    </div>

</div>

</body>
</html>