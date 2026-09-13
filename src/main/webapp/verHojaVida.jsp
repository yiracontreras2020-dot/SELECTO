<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Hoja de Vida - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5 mb-5">

    <div class="card shadow">

        <div class="card-body p-4">

            <h1 class="text-center mb-4">
                📄 Hoja de Vida
            </h1>

            <hr>

            <h3>Información personal</h3>

            <p>
                <strong>Nombre:</strong>
                <%= request.getAttribute("nombre") %>
            </p>

            <p>
                <strong>Correo:</strong>
                <%= request.getAttribute("correo") %>
            </p>

            <p>
                <strong>Teléfono:</strong>
                <%= request.getAttribute("telefono") %>
            </p>

            <hr>

            <h3>Perfil profesional</h3>

            <p>
                <%= request.getAttribute("perfil_profesional") %>
            </p>

            <hr>

            <h3>Formación académica</h3>

            <p>
                <%= request.getAttribute("formacion_academica") %>
            </p>

            <hr>

            <h3>Experiencia laboral</h3>

            <p>
                <%= request.getAttribute("experiencia_laboral") %>
            </p>

            <hr>

            <h3>Habilidades</h3>

            <p>
                <%= request.getAttribute("habilidades") %>
            </p>

            <hr>

            <div class="text-center mt-4">

                <a href="hojaVida.jsp"
                   class="btn btn-primary">
                    ✏️ Editar hoja de vida
                </a>

                <button onclick="window.print()"
                        class="btn btn-success">
                    🖨️ Imprimir / Guardar PDF
                </button>

            </div>

        </div>

    </div>

</div>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>