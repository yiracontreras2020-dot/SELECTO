<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String rol = (String) session.getAttribute("rol");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">

    <div class="container-fluid">

        <a class="navbar-brand" href="dashboard.jsp">
            <strong>SELECTO</strong>
        </a>

        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse"
                data-bs-target="#menu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="menu">

            <ul class="navbar-nav ms-auto">

                <% if ("ADMIN".equals(rol)) { %>

                    <li class="nav-item">
                        <a class="nav-link" href="dashboard.jsp">🏠 Inicio</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarCandidatos">👥 Candidatos</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarEmpresas">🏢 Empresas</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantes">💼 Vacantes</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantesDisponibles">📢 Disponibles</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarPostulaciones">📄 Postulaciones</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="PanelEmpresa">📊 Panel</a>
                    </li>

                <% } else if ("EMPRESA".equals(rol)) { %>

                    <li class="nav-item">
                        <a class="nav-link" href="PanelEmpresa">📊 Panel Empresa</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantes">💼 Mis Vacantes</a>
                    </li>

                <% } else if ("CANDIDATO".equals(rol)) { %>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarVacantesDisponibles">📢 Vacantes</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ListarPostulaciones">📄 Mis Postulaciones</a>
                    </li>

                <% } %>

                <li class="nav-item">
                    <a class="nav-link text-danger" href="logout">
                        🚪 Cerrar sesión
                    </a>
                </li>

            </ul>

        </div>

    </div>

</nav>