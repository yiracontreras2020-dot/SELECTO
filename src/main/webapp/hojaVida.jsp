<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Mi Hoja de Vida - SELECTO</title>

    <link rel="stylesheet" href="css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="container mt-5 mb-5">

    <div class="card shadow">

        <div class="card-body">

            <h1 class="text-center mb-4">
                📄 Mi Hoja de Vida
            </h1>

            <p class="text-center text-muted">
                Completa tu información profesional para que las empresas
                puedan conocer mejor tu perfil.
            </p>

            <form action="GuardarHojaVidaServlet" method="post">

                <!-- TELÉFONO -->

                <div class="mb-3">

                    <label class="form-label">
                        Teléfono
                    </label>

                    <input
                            type="text"
                            name="telefono"
                            class="form-control"
                            placeholder="Ejemplo: 3001234567"
                            required>

                </div>


                <!-- PERFIL PROFESIONAL -->

                <div class="mb-3">

                    <label class="form-label">
                        Perfil profesional
                    </label>

                    <textarea
                            name="perfil_profesional"
                            class="form-control"
                            rows="4"
                            placeholder="Describe brevemente tu perfil profesional..."
                            required></textarea>

                </div>


                <!-- FORMACIÓN -->

                <div class="mb-3">

                    <label class="form-label">
                        Formación académica
                    </label>

                    <textarea
                            name="formacion_academica"
                            class="form-control"
                            rows="4"
                            placeholder="Indica tus estudios, títulos o formación académica..."
                            required></textarea>

                </div>


                <!-- EXPERIENCIA -->

                <div class="mb-3">

                    <label class="form-label">
                        Experiencia laboral
                    </label>

                    <textarea
                            name="experiencia_laboral"
                            class="form-control"
                            rows="5"
                            placeholder="Describe tu experiencia laboral..."
                            required></textarea>

                </div>


                <!-- HABILIDADES -->

                <div class="mb-3">

                    <label class="form-label">
                        Habilidades
                    </label>

                    <textarea
                            name="habilidades"
                            class="form-control"
                            rows="4"
                            placeholder="Ejemplo: Java, MySQL, HTML, CSS, trabajo en equipo..."
                            required></textarea>

                </div>


                <!-- BOTÓN -->

                <div class="text-center mt-4">

                    <button
                            type="submit"
                            class="btn btn-primary">

                        Guardar hoja de vida

                    </button>

                    <a
                            href="ListarVacantes"
                            class="btn btn-secondary">

                        Cancelar

                    </a>

                </div>

            </form>

        </div>

    </div>

</div>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>