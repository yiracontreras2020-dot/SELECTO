<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

                <li class="nav-item">
                    <a class="nav-link text-warning" href="login.jsp">🚪 Salir</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link text-danger" href="logout">
                        Cerrar sesión
                    </a>
                </li>

            </ul>

        </div>

    </div>

</nav>