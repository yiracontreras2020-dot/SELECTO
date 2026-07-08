<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Lista de Candidatos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Candidatos Registrados</h2>
            <a href="index.jsp" class="btn btn-outline-primary">Nuevo Registro</a>
        </div>

        <div class="table-responsive">
            <table class="table table-hover table-striped shadow-sm bg-white">
                <thead class="table-dark">
                    <tr>
                        <th>#</th>
                        <th>Nombre</th>
                        <th>Correo Electrónico</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<String[]> candidatos = (List<String[]>) request.getAttribute("candidatos");
                        if (candidatos != null) {
                            int cont = 1;
                            for (String[] c : candidatos) {
                    %>
                    <tr>
                        <td><%= cont++ %></td>
                        <td><strong><%= c[0] %></strong></td>
                        <td><%= c[1] %></td>
                        <td>
                            <td>
                                <a href="editar?id=<%= c[0] %>" class="btn btn-sm btn-warning">Editar</a>

                                <a href="${pageContext.request.contextPath}/EliminarServlet?id=<%= c[0] %>"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('¿Estás segura de eliminar este candidato?')">
                                   Borrar
                                </a>

                                </a>
                            </td>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>