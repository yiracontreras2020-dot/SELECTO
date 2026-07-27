<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="UTF-8">

<title>Dashboard - SELECTO</title>

<link rel="stylesheet" href="css/styles.css">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5">

    <h2 class="text-center mb-4">
        Bienvenido a SELECTO
    </h2>

    <div class="row">

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center">

                <div class="card-body">

                    <h1>👥</h1>

                    <h4>Candidatos</h4>

                    <a href="ListarCandidatos"
                       class="btn btn-primary">
                        Ver
                    </a>

                </div>

            </div>

        </div>

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center">

                <div class="card-body">

                    <h1>🏢</h1>

                    <h4>Empresas</h4>

                    <a href="ListarEmpresas"
                       class="btn btn-success">
                        Ver
                    </a>

                </div>

            </div>

        </div>

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center">

                <div class="card-body">

                    <h1>💼</h1>

                    <h4>Vacantes</h4>

                    <a href="ListarVacantes"
                       class="btn btn-warning">
                        Ver
                    </a>

                </div>

            </div>

        </div>

        <div class="col-md-3 mb-4">

            <div class="card shadow text-center">

                <div class="card-body">

                    <h1>📄</h1>

                    <h4>Postulaciones</h4>

                    <a href="ListarPostulaciones"
                       class="btn btn-danger">
                        Ver
                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

<header class="navbar">

<h2>SELECTO</h2>

<nav>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<a href="dashboard.jsp">Inicio</a>

<a href="ListarCandidatos">Candidatos</a>

<a href="ListarEmpresas">Empresas</a>

<a href="ListarVacantes">Vacantes</a>

<a href="ListarVacantesDisponibles">Vacantes Disponibles</a>

<a href="ListarPostulaciones">Postulaciones</a>

<a href="PanelEmpresa">Panel Empresa</a>

<a href="login.jsp">Cerrar sesión</a>

</nav>

</header>

<div class="contenedor">

<h1>Bienvenido a SELECTO</h1>

<p>Seleccione una opción del menú para administrar el sistema.</p>

<div class="cards">

    <a href="ListarCandidatos" class="text-decoration-none text-dark">
        <div class="card">
            <h2>👥</h2>
            <p>Candidatos</p>
        </div>
    </a>

    <a href="ListarEmpresas" class="text-decoration-none text-dark">
        <div class="card">
            <h2>🏢</h2>
            <p>Empresas</p>
        </div>
    </a>

    <a href="ListarVacantesServlet" class="text-decoration-none text-dark">
        <div class="card">
            <h2>💼</h2>
            <p>Vacantes</p>
        </div>
    </a>

    <a href="postulaciones.jsp" class="text-decoration-none text-dark">
        <div class="card">
            <h2>📄</h2>
            <p>Postulaciones</p>
        </div>
    </a>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>

</html>