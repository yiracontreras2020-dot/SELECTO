<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <title>Reporte de Postulaciones - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5 mb-5">

    <h1 class="text-center mb-4">
        📄 Reporte de Postulaciones
    </h1>

    <form action="ReportePostulacionesServlet" method="get"
          class="row justify-content-center mb-4">

        <div class="col-md-5">
            <label for="vacante_id" class="form-label">
                Filtrar por vacante
            </label>

            <select name="vacante_id"
                    id="vacante_id"
                    class="form-select">

                <option value="">Todas las vacantes</option>

                <%
                    java.util.List<String[]> vacantesFiltro =
                            (java.util.List<String[]>) request.getAttribute("vacantesFiltro");

                    String vacanteSeleccionada =
                            request.getParameter("vacante_id");

                    if (vacantesFiltro != null) {

                        for (String[] vacante : vacantesFiltro) {
                %>

                    <option value="<%= vacante[0] %>"
                        <%= vacante[0].equals(vacanteSeleccionada)
                                ? "selected" : "" %>>
                        <%= vacante[1] %>
                    </option>

                <%
                        }
                    }
                %>

            </select>
        </div>

        <div class="col-md-5">
            <label for="candidato_id" class="form-label">
                Filtrar por candidato
            </label>

            <select name="candidato_id"
                    id="candidato_id"
                    class="form-select">

                <option value="">Todos los candidatos</option>

                <%
                    java.util.List<String[]> candidatosFiltro =
                            (java.util.List<String[]>) request.getAttribute("candidatosFiltro");

                    String candidatoSeleccionado =
                            request.getParameter("candidato_id");

                    if (candidatosFiltro != null) {

                        for (String[] candidato : candidatosFiltro) {
                %>

                    <option value="<%= candidato[0] %>"
                        <%= candidato[0].equals(candidatoSeleccionado)
                                ? "selected" : "" %>>
                        <%= candidato[1] %>
                    </option>

                <%
                        }
                    }
                %>

            </select>
        </div>

        <div class="col-md-2 d-flex align-items-end">
            <button type="submit"
                    class="btn btn-primary w-100">
                🔎 Filtrar
            </button>
        </div>

    </form>

    <p class="text-center text-muted mb-4">
        Registro de candidatos postulados a las vacantes disponibles.
    </p>

    <div class="table-responsive">

        <table class="table table-bordered table-hover shadow">

            <thead class="table-primary">

            <tr>
                <th>ID</th>
                <th>Candidato</th>
                <th>Correo</th>
                <th>Vacante</th>
                <th>Empresa</th>
            </tr>

            </thead>

            <tbody>

            <%
                java.util.List<String[]> reporte =
                        (java.util.List<String[]>) request.getAttribute("reportePostulaciones");

                if (reporte != null && !reporte.isEmpty()) {

                    for (String[] postulacion : reporte) {
            %>

            <tr>

                <td><%= postulacion[0] %></td>

                <td><%= postulacion[1] %></td>

                <td><%= postulacion[2] %></td>

                <td><%= postulacion[3] %></td>

                <td><%= postulacion[4] %></td>

            </tr>

            <%
                    }

                } else {
            %>

            <tr>

                <td colspan="5"
                    class="text-center text-muted">

                    No hay postulaciones registradas.

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