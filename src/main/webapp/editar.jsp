<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Editar Candidato</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

<body class="bg-light">

<%@ include file="menu.jsp" %>

<div class="container mt-5">

    <h2>Editar candidato</h2>

    <form action="ActualizarServlet" method="post">

        <input type="hidden"
               name="id"
               value="${id}">

        <div class="mb-3">

            <label>Nombre</label>

            <input
                    type="text"
                    class="form-control"
                    name="nombre"
                    value="${nombre}">
        </div>

        <div class="mb-3">

            <label>Correo</label>

            <input
                    type="email"
                    class="form-control"
                    name="correo"
                    value="${correo}">
        </div>

        <button class="btn btn-success">
            Actualizar
        </button>

        <a href="ListarCandidatos"
           class="btn btn-secondary">
            Cancelar
        </a>

    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="footer.jsp" %>

</body>
</html>