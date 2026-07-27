<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Selecto - Sistema de Selección de Personal</title>

    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

<header class="navbar">

    <div class="logo">
        <h2>SELECTO</h2>
    </div>

    <nav>
        <a href="#">Inicio</a>
        <a href="#">Nosotros</a>
        <a href="#">Servicios</a>
        <a href="#">Contacto</a>
    </nav>

</header>

<section class="principal">

    <div class="informacion">

        <h1>Bienvenido a <span>SELECTO</span></h1>

        <p>
            SELECTO es una plataforma desarrollada para facilitar el proceso de
            selección de personal entre empresas y candidatos.
        </p>

        <h2>Nuestra misión</h2>

        <p>
            Ayudar a las empresas a encontrar el mejor talento y permitir que
            los candidatos administren su información de forma rápida,
            organizada y segura.
        </p>

        <h2>¿Qué puedes hacer?</h2>

        <ul>
            <li>✅ Registrar candidatos.</li>
            <li>✅ Consultar información.</li>
            <li>✅ Actualizar datos.</li>
            <li>✅ Eliminar registros.</li>
            <li>✅ Gestionar procesos de selección.</li>
        </ul>

    </div>

    <div class="formulario">

        <div class="card">

            <h2>Registro de Candidatos</h2>

            <form action="registro" method="post">

                <input
                        type="text"
                        name="nombre"
                        placeholder="Nombre completo"
                        required>

                <input
                        type="email"
                        name="correo"
                        placeholder="Correo electrónico"
                        required>

                <input
                        type="password"
                        name="password"
                        placeholder="Contraseña"
                        required>

                <button type="submit">
                    Registrar
                </button>

            </form>

            <br><br>

            <a href="login.jsp" class="btn-login">
                Iniciar sesión como administrador
            </a>

        </div>

    </div>

</section>

<footer>

    <p>© 2026 SELECTO | Sistema de Selección de Personal</p>

</footer>

<script src="js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>
</html>