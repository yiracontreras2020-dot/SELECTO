<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>
</html>