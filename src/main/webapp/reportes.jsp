<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <title>Reportes - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5 mb-5">

    <h1 class="text-center mb-4">
        📊 Reportes de SELECTO
    </h1>

    <p class="text-center text-muted mb-5">
        Resumen general de la información registrada en el sistema.
    </p>


    <div class="row g-4">

        <!-- CANDIDATOS -->
        <div class="col-md-6 col-lg-3">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <h5 class="card-title">
                        👥 Candidatos
                    </h5>

                    <h2 class="mt-3">
                        <%= request.getAttribute("totalCandidatos") %>
                    </h2>

                    <p class="text-muted">
                        Candidatos registrados
                    </p>

                </div>

            </div>

        </div>


        <!-- EMPRESAS -->
        <div class="col-md-6 col-lg-3">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <h5 class="card-title">
                        🏢 Empresas
                    </h5>

                    <h2 class="mt-3">
                        <%= request.getAttribute("totalEmpresas") %>
                    </h2>

                    <p class="text-muted">
                        Empresas registradas
                    </p>

                </div>

            </div>

        </div>


        <!-- VACANTES -->
        <div class="col-md-6 col-lg-3">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <h5 class="card-title">
                        💼 Vacantes
                    </h5>

                    <h2 class="mt-3">
                        <%= request.getAttribute("totalVacantes") %>
                    </h2>

                    <p class="text-muted">
                        Vacantes publicadas
                    </p>

                </div>

            </div>

        </div>


        <!-- POSTULACIONES -->
        <div class="col-md-6 col-lg-3">

            <div class="card shadow text-center h-100">

                <div class="card-body">

                    <h5 class="card-title">
                        📄 Postulaciones
                    </h5>

                    <h2 class="mt-3">
                        <%= request.getAttribute("totalPostulaciones") %>
                    </h2>

                    <p class="text-muted">
                        Postulaciones realizadas
                    </p>

                </div>

            </div>

        </div>

    </div>


    <div class="text-center mt-5">

        <a href="dashboard.jsp"
           class="btn btn-secondary">
            ← Volver al panel
        </a>

    </div>

</div>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>