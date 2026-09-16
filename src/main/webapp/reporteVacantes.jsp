<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <title>Reporte de Vacantes - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5 mb-5">

    <h1 class="text-center mb-4">
        💼 Reporte de Vacantes
    </h1>

    <p class="text-center text-muted mb-4">
        Listado detallado de las vacantes registradas en SELECTO.
    </p>

    <form action="ReporteVacantesServlet" method="get"
          class="row justify-content-center mb-4">

        <div class="col-md-4">

            <label for="estado" class="form-label">
                Filtrar por estado
            </label>

            <select name="estado" id="estado"
                    class="form-select">

                <option value="">Todas las vacantes</option>

                <option value="ACTIVA">Activas</option>

                <option value="INACTIVA">Inactivas</option>

            </select>

        </div>

        <div class="col-md-2 d-flex align-items-end">

            <button type="submit"
                    class="btn btn-primary w-100">
                🔎 Filtrar
            </button>

        </div>

    </form>

    <div class="table-responsive">

        <table class="table table-bordered table-hover shadow">

            <thead class="table-primary">

            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Descripción</th>
                <th>Salario</th>
                <th>Empresa</th>
                <th>Fecha de publicación</th>
                <th>Estado</th>
                <th>Postulantes</th>
            </tr>

            </thead>

            <tbody>

            <%
                java.util.List<String[]> reporte =
                        (java.util.List<String[]>) request.getAttribute("reporteVacantes");

                if (reporte != null && !reporte.isEmpty()) {

                    for (String[] vacante : reporte) {
            %>

            <tr>

                <td><%= vacante[0] %></td>

                <td><%= vacante[1] %></td>

                <td><%= vacante[2] %></td>

                <td><%= vacante[3] %></td>

                <td><%= vacante[4] %></td>

                <td><%= vacante[5] %></td>

                <td><%= vacante[6] %></td>

                <td><%= vacante[7] %></td>

            </tr>

            <%
                    }

                } else {
            %>

            <tr>

                <td colspan="8"
                    class="text-center text-muted">

                    No hay vacantes registradas.

                </td>

            </tr>

            <%
                }
            %>

            </tbody>

        </table>

    </div>


    <div class="text-center mt-4">

        <a href="ReportesServlet"
           class="btn btn-secondary">

            ← Volver a Reportes

        </a>

    </div>

</div>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>