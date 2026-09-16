<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("usuario") == null ||
            session.getAttribute("candidato_id") == null) {

        response.sendRedirect("login.jsp");
        return;
    }

    String usuario = (String) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Panel del Candidato - SELECTO</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5 mb-5">

    <div class="text-center mb-5">

        <h1>
            Bienvenido a SELECTO
        </h1>

        <p class="text-muted">
            Hola, <strong><%= usuario %></strong>.
            Gestiona tu perfil profesional y encuentra nuevas oportunidades.
        </p>

    </div>

    <div class="row justify-content-center">

        <!-- HOJA DE VIDA -->

        <div class="col-md-4 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 50px;">
                        📄
                    </div>

                    <h4 class="mt-3">
                        Mi Hoja de Vida
                    </h4>

                    <p>
                        Completa y actualiza tu información profesional.
                    </p>

                    <a href="verHojaVida.jsp"
                       class="btn btn-primary">

                        Ver mi hoja de vida

                    </a>

                </div>

            </div>

        </div>


        <!-- VACANTES -->

        <div class="col-md-4 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 50px;">
                        💼
                    </div>

                    <h4 class="mt-3">
                        Vacantes
                    </h4>

                    <p>
                        Consulta las oportunidades laborales disponibles.
                    </p>

                    <a href="ListarVacantes"
                       class="btn btn-success">

                        Ver vacantes

                    </a>

                </div>

            </div>

        </div>


        <!-- POSTULACIONES -->

        <div class="col-md-4 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 50px;">
                        📋
                    </div>

                    <h4 class="mt-3">
                        Mis Postulaciones
                    </h4>

                    <p>
                        Consulta las vacantes a las que te has postulado.
                    </p>

                    <a href="ListarPostulaciones"
                       class="btn btn-warning">

                        Ver postulaciones

                    </a>

                </div>

            </div>

        </div>

    </div>


    <!-- PERFIL -->

    <div class="row justify-content-center mt-3">

        <div class="col-md-4 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 50px;">
                        👤
                    </div>

                    <h4 class="mt-3">
                        Mi Perfil
                    </h4>

                    <p>
                        Consulta y administra tus datos personales.
                    </p>

                    <a href="hojaVida.jsp"
                       class="btn btn-info">

                        Ver perfil

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js">
</script>

<%@ include file="footer.jsp" %>

</body>

</html>