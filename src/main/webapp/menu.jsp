<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String rolMenu = (String) session.getAttribute("rol");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">

    <div class="container-fluid">

        <!-- LOGO -->
        <a class="navbar-brand fw-bold" href="dashboard.jsp">
            SELECTO
        </a>

        <!-- BOTÓN PARA CELULAR -->
        <button class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#menuPrincipal">

            <span class="navbar-toggler-icon"></span>

        </button>

        <!-- MENÚ -->
        <div class="collapse navbar-collapse" id="menuPrincipal">

            <ul class="navbar-nav ms-auto">

                <!-- INICIO -->
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.jsp">
                        🏠 Inicio
                    </a>
                </li>


                <!-- ================================= -->
                <!-- OPCIONES DEL ADMINISTRADOR -->
                <!-- ================================= -->

                <% if ("ADMIN".equalsIgnoreCase(rolMenu)) { %>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarCandidatos">
                            👥 Candidatos
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarEmpresas">
                            🏢 Empresas
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantes">
                            💼 Vacantes
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantesDisponibles">
                            🔎 Disponibles
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarPostulaciones">
                            📄 Postulaciones
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="PanelEmpresa">
                            📊 Panel Empresa
                        </a>
                    </li>

                <% } %>


                <!-- ================================= -->
                <!-- OPCIONES DE LA EMPRESA -->
                <!-- ================================= -->

                <% if ("EMPRESA".equalsIgnoreCase(rolMenu)) { %>

                    <li class="nav-item">
                        <a class="nav-link" href="PanelEmpresa">
                            📊 Mi Panel
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantes">
                            💼 Mis Vacantes
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarPostulaciones">
                            📄 Postulaciones
                        </a>
                    </li>

                <% } %>


                <!-- ================================= -->
                <!-- OPCIONES DEL CANDIDATO -->
                <!-- ================================= -->

                <% if ("CANDIDATO".equalsIgnoreCase(rolMenu)) { %>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="ListarVacantesDisponibles">
                            🔎 Vacantes Disponibles
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="ListarPostulaciones">
                            📄 Mis Postulaciones
                        </a>
                    </li>

                <% } %>


                <!-- CERRAR SESIÓN -->

                <li class="nav-item">
                    <a class="nav-link text-warning fw-bold"
                       href="login.jsp">
                        🚪 Cerrar sesión
                    </a>
                </li>

            </ul>

        </div>

    </div>

</nav>