<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Registrar Vacante - SELECTO</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<%@ include file="menu.jsp" %>


<div class="container py-5">

    <div class="row justify-content-center">

        <div class="col-md-8 col-lg-7">

            <div class="card shadow border-0">

                <div class="card-header bg-primary text-white text-center">

                    <h2 class="mb-0">
                        💼 Registrar Nueva Vacante
                    </h2>

                </div>


                <div class="card-body p-4">

                    <form action="RegistrarVacanteServlet" method="post">


                        <!--
                            El ID de la empresa se obtiene
                            automáticamente de la sesión.
                        -->

                        <input type="hidden"
                               name="empresa_id"
                               value="<%= session.getAttribute("empresa_id") %>">


                        <!-- TÍTULO -->

                        <div class="mb-3">

                            <label class="form-label">
                                Título de la vacante
                            </label>

                            <input type="text"
                                   name="titulo"
                                   class="form-control"
                                   placeholder="Ej: Desarrollador Java"
                                   required>

                        </div>


                        <!-- DESCRIPCIÓN -->

                        <div class="mb-3">

                            <label class="form-label">
                                Descripción
                            </label>

                            <textarea
                                    name="descripcion"
                                    class="form-control"
                                    rows="4"
                                    placeholder="Describe las funciones y requisitos del cargo"
                                    required></textarea>

                        </div>


                        <!-- SALARIO -->

                        <div class="mb-3">

                            <label class="form-label">
                                Salario
                            </label>

                            <input type="number"
                                   name="salario"
                                   class="form-control"
                                   placeholder="Ej: 2500000"
                                   step="0.01"
                                   required>

                        </div>


                        <!-- FECHA -->

                        <div class="mb-3">

                            <label class="form-label">
                                Fecha de publicación
                            </label>

                            <input type="date"
                                   name="fecha_publicacion"
                                   class="form-control"
                                   required>

                        </div>


                        <!-- ESTADO -->

                        <input type="hidden"
                               name="estado"
                               value="ACTIVA">


                        <!-- BOTONES -->

                        <div class="d-flex justify-content-between mt-4">

                            <a href="PanelEmpresa"
                               class="btn btn-secondary">

                                ← Volver

                            </a>


                            <button type="submit"
                                    class="btn btn-success">

                                ✅ Registrar Vacante

                            </button>

                        </div>


                    </form>

                </div>

            </div>

        </div>

    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js">
</script>


<%@ include file="footer.jsp" %>

</body>

</html>