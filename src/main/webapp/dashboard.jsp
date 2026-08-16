<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Verificar que exista una sesión
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Verificar que el usuario sea ADMIN
    String rol = (String) session.getAttribute("rol");

    if (!"ADMIN".equalsIgnoreCase(rol)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Panel de Administración - SELECTO</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- CSS de SELECTO -->
    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<%@ include file="menu.jsp" %>


<!-- ============================= -->
<!-- PANEL DE ADMINISTRACIÓN -->
<!-- ============================= -->

<div class="container mt-5 mb-5">

    <h1 class="text-center mb-2">
        Panel de Administración
    </h1>

    <p class="text-center text-muted mb-5">
        Gestión del sistema SELECTO
    </p>


    <!-- ============================= -->
    <!-- PRIMERA FILA -->
    <!-- ============================= -->

    <div class="row">


        <!-- CANDIDATOS -->

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 45px;">
                        👥
                    </div>

                    <h4 class="mt-3">
                        Candidatos
                    </h4>

                    <p>
                        Gestionar candidatos registrados.
                    </p>

                    <a href="ListarCandidatos"
                       class="btn btn-primary">

                        Ver candidatos

                    </a>

                </div>

            </div>

        </div>


        <!-- EMPRESAS -->

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 45px;">
                        🏢
                    </div>

                    <h4 class="mt-3">
                        Empresas
                    </h4>

                    <p>
                        Gestionar empresas registradas.
                    </p>

                    <a href="ListarEmpresas"
                       class="btn btn-success">

                        Ver empresas

                    </a>

                </div>

            </div>

        </div>


        <!-- VACANTES -->

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 45px;">
                        💼
                    </div>

                    <h4 class="mt-3">
                        Vacantes
                    </h4>

                    <p>
                        Administrar las vacantes.
                    </p>

                    <a href="ListarVacantes"
                       class="btn btn-warning">

                        Ver vacantes

                    </a>

                </div>

            </div>

        </div>


        <!-- POSTULACIONES -->

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 45px;">
                        📄
                    </div>

                    <h4 class="mt-3">
                        Postulaciones
                    </h4>

                    <p>
                        Consultar las postulaciones.
                    </p>

                    <a href="ListarPostulaciones"
                       class="btn btn-danger">

                        Ver postulaciones

                    </a>

                </div>

            </div>

        </div>

    </div>


    <!-- ============================= -->
    <!-- SEGUNDA FILA -->
    <!-- ============================= -->

    <div class="row justify-content-center mt-3">


        <!-- VACANTES DISPONIBLES -->

        <div class="col-md-4 mb-4">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <div style="font-size: 45px;">
                        🔎
                    </div>

                    <h4 class="mt-3">
                        Vacantes disponibles
                    </h4>

                    <p>
                        Consultar las vacantes disponibles.
                    </p>

                    <a href="ListarVacantesDisponibles"
                       class="btn btn-info">

                        Ver disponibles

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>


<!-- Bootstrap JS -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js">
</script>


<!-- Footer -->

<%@ include file="footer.jsp" %>

</body>

</html>