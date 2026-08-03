<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Panel de Empresa - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<body>

<%@ include file="menu.jsp" %>

<header class="navbar">

    <h2>Nueva Vacante</h2>

    <form action="RegistrarVacanteServlet" method="post">

        <div class="mb-3">
            <label>Título</label>
            <input type="text" name="titulo" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Descripción</label>
            <textarea name="descripcion" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label>Salario</label>
            <input type="number" name="salario" class="form-control" required>
        </div>

        <input type="hidden"
               name="empresa_id"
               value="<%= session.getAttribute("empresa_id") %>">

        <div class="mb-3">
            <label>Fecha</label>
            <input type="date"
                   name="fecha_publicacion"
                   class="form-control"
                   required>
        </div>

        <input type="hidden" name="estado" value="ACTIVA">

        <button class="btn btn-success">
            Registrar Vacante
        </button>

    </form>

    <h2>SELECTO</h2>

    <nav>

        <a href="ListarVacantes">Vacantes</a>

        <a href="ListarPostulaciones">Postulaciones</a>

        <a href="login.jsp">Cerrar sesión</a>

    </nav>

</header>

<div class="contenedor">

    <h1>Panel de Empresa</h1>

    <div class="cards">

        <div class="card">

            <h2><%= request.getAttribute("totalVacantes") %></h2>

            <p>Vacantes publicadas</p>

        </div>

        <div class="card">

            <h2><%= request.getAttribute("totalPostulaciones") %></h2>

            <p>Postulaciones recibidas</p>

        </div>

    </div>

    <br>

    <a class="nuevo" href="ListarVacantes">
        Administrar Vacantes
    </a>

    <br><br>

    <a class="nuevo" href="ListarPostulaciones">
        Ver Postulaciones
    </a>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>

</html>