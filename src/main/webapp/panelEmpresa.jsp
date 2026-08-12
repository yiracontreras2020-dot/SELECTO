<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Panel de Empresa - SELECTO</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- Estilos propios -->
    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<!-- MENÚ PRINCIPAL -->
<%@ include file="menu.jsp" %>


<!-- CONTENIDO PRINCIPAL -->
<div class="container py-5">

    <!-- TÍTULO -->
    <div class="text-center mb-5">

        <h1 class="fw-bold">
            🏢 Panel de Empresa
        </h1>

        <p class="text-muted">
            Administra tus vacantes y consulta las postulaciones recibidas.
        </p>

    </div>


    <!-- ESTADÍSTICAS -->
    <div class="row g-4 mb-5">

        <!-- VACANTES -->
        <div class="col-md-6">

            <div class="card shadow-sm border-0 h-100">

                <div class="card-body text-center p-4">

                    <div class="fs-1 mb-2">
                        💼
                    </div>

                    <h2 class="display-5 text-primary fw-bold">

                        <%= request.getAttribute("totalVacantes") != null
                                ? request.getAttribute("totalVacantes")
                                : 0 %>

                    </h2>

                    <h5 class="card-title">
                        Vacantes publicadas
                    </h5>

                    <p class="text-muted">
                        Vacantes registradas por tu empresa.
                    </p>

                    <a href="ListarVacantes"
                       class="btn btn-primary">

                        Administrar Vacantes

                    </a>

                </div>

            </div>

        </div>


        <!-- POSTULACIONES -->
        <div class="col-md-6">

            <div class="card shadow-sm border-0 h-100">

                <div class="card-body text-center p-4">

                    <div class="fs-1 mb-2">
                        📄
                    </div>

                    <h2 class="display-5 text-success fw-bold">

                        <%= request.getAttribute("totalPostulaciones") != null
                                ? request.getAttribute("totalPostulaciones")
                                : 0 %>

                    </h2>

                    <h5 class="card-title">
                        Postulaciones recibidas
                    </h5>

                    <p class="text-muted">
                        Candidatos que se han postulado a tus vacantes.
                    </p>

                    <a href="ListarPostulaciones"
                       class="btn btn-success">

                        Ver Postulaciones

                    </a>

                </div>

            </div>

        </div>

    </div>

    <hr>

    <h2 class="mt-4">Mis Vacantes</h2>

    <%
        List<String[]> vacantesEmpresa =
                (List<String[]>) request.getAttribute("vacantesEmpresa");
    %>

    <% if (vacantesEmpresa != null && !vacantesEmpresa.isEmpty()) { %>

        <div class="table-responsive">

            <table class="table table-striped table-hover">

                <thead class="table-primary">

                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Salario</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>

                </thead>

                <tbody>

                <% for (String[] vacante : vacantesEmpresa) { %>

                    <tr>

                        <td><%= vacante[0] %></td>

                        <td>
                            <strong><%= vacante[1] %></strong>
                        </td>

                        <td>
                            <%= vacante[2] %>
                        </td>

                        <td>
                            $<%= vacante[3] %>
                        </td>

                        <td>
                            <%= vacante[4] %>
                        </td>

                        <td>

                            <% if ("ACTIVA".equalsIgnoreCase(vacante[5])) { %>

                                <span class="badge bg-success">
                                    Activa
                                </span>

                            <% } else { %>

                                <span class="badge bg-secondary">
                                    <%= vacante[5] %>
                                </span>

                            <% } %>

                        </td>

                        <td>

                            <a href="EditarVacanteServlet?id=<%= vacante[0] %>"
                               class="btn btn-warning btn-sm">
                                ✏️ Editar
                            </a>

                            <a href="EliminarVacanteServlet?id=<%= vacante[0] %>"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('¿Deseas eliminar esta vacante?');">
                                🗑️ Eliminar
                            </a>

                        </td>

                    </tr>

                <% } %>

                </tbody>

            </table>

        </div>

    <% } else { %>

        <div class="alert alert-info">
            📢 Esta empresa todavía no tiene vacantes registradas.
        </div>

    <% } %>


    <!-- NUEVA VACANTE -->
    <div class="card shadow-sm border-0">

        <div class="card-header bg-primary text-white py-3">

            <h3 class="mb-0">
                ➕ Registrar nueva vacante
            </h3>

        </div>


        <div class="card-body p-4">

            <form action="RegistrarVacanteServlet"
                  method="post">

                <div class="row g-4">

                    <!-- TÍTULO -->
                    <div class="col-md-6">

                        <label for="titulo"
                               class="form-label fw-semibold">

                            Título de la vacante

                        </label>

                        <input
                                type="text"
                                id="titulo"
                                name="titulo"
                                class="form-control"
                                placeholder="Ejemplo: Desarrollador Java"
                                required>

                    </div>


                    <!-- SALARIO -->
                    <div class="col-md-6">

                        <label for="salario"
                               class="form-label fw-semibold">

                            Salario

                        </label>

                        <input
                                type="number"
                                id="salario"
                                name="salario"
                                class="form-control"
                                placeholder="Ejemplo: 2500000"
                                min="0"
                                step="0.01"
                                required>

                    </div>


                    <!-- DESCRIPCIÓN -->
                    <div class="col-12">

                        <label for="descripcion"
                               class="form-label fw-semibold">

                            Descripción

                        </label>

                        <textarea
                                id="descripcion"
                                name="descripcion"
                                class="form-control"
                                rows="5"
                                placeholder="Describe las funciones, requisitos y condiciones de la vacante."
                                required></textarea>

                    </div>


                    <!-- FECHA -->
                    <div class="col-md-6">

                        <label for="fecha_publicacion"
                               class="form-label fw-semibold">

                            Fecha de publicación

                        </label>

                        <input
                                type="date"
                                id="fecha_publicacion"
                                name="fecha_publicacion"
                                class="form-control"
                                required>

                    </div>


                    <!-- ESTADO -->
                    <div class="col-md-6">

                        <label class="form-label fw-semibold">

                            Estado

                        </label>

                        <input
                                type="text"
                                class="form-control"
                                value="ACTIVA"
                                disabled>

                        <input
                                type="hidden"
                                name="estado"
                                value="ACTIVA">

                    </div>


                    <!-- EMPRESA -->
                    <input
                            type="hidden"
                            name="empresa_id"
                            value="<%= session.getAttribute("empresa_id") %>">


                    <!-- BOTÓN -->
                    <div class="col-12 text-center pt-3">

                        <button
                                type="submit"
                                class="btn btn-success btn-lg px-5">

                            ➕ Registrar Vacante

                        </button>

                    </div>

                </div>

            </form>

        </div>

    </div>

</div>


<!-- FOOTER -->
<%@ include file="footer.jsp" %>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>