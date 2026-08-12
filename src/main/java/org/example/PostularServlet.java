package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

@WebServlet("/PostularServlet")
public class PostularServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("usuario") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        // Solo los candidatos pueden postularse
        if (!"CANDIDATO".equals(rol)) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Solo los candidatos pueden postularse a una vacante."
            );

            return;
        }

        // Obtener usuario de la sesión
        String usuario = (String) session.getAttribute("usuario");

        // Obtener vacante
        String vacanteId = request.getParameter("vacante_id");

        if (vacanteId == null || vacanteId.isEmpty()) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "No se recibió el ID de la vacante."
            );

            return;
        }

        try (Connection con = ConexionBD.conectar()) {

            // ==========================================
            // BUSCAR EL CANDIDATO QUE INICIÓ SESIÓN
            // ==========================================

            String sqlCandidato =
                    "SELECT id FROM candidatos WHERE correo = ?";

            int candidatoId;

            try (PreparedStatement ps =
                         con.prepareStatement(sqlCandidato)) {

                ps.setString(1, usuario);

                try (ResultSet rs = ps.executeQuery()) {

                    if (!rs.next()) {

                        response.sendError(
                                HttpServletResponse.SC_NOT_FOUND,
                                "No se encontró el candidato."
                        );

                        return;
                    }

                    candidatoId = rs.getInt("id");
                }
            }


            // ==========================================
            // EVITAR POSTULARSE DOS VECES
            // ==========================================

            String sqlExiste =
                    "SELECT id FROM postulaciones " +
                            "WHERE candidato_id = ? AND vacante_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlExiste)) {

                ps.setInt(1, candidatoId);
                ps.setInt(2, Integer.parseInt(vacanteId));

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        response.setContentType(
                                "text/html;charset=UTF-8"
                        );

                        response.getWriter().println("""
                                <!DOCTYPE html>
                                <html lang="es">
                                <head>
                                    <meta charset="UTF-8">
                                    <title>SELECTO</title>
                                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
                                          rel="stylesheet">
                                </head>
                                <body>
                                    <div class="container mt-5">
                                        <div class="alert alert-warning text-center">
                                            <h4>⚠️ Ya te postulaste a esta vacante.</h4>
                                            <a href="ListarVacantes"
                                               class="btn btn-primary mt-3">
                                                Volver a vacantes
                                            </a>
                                        </div>
                                    </div>
                                </body>
                                </html>
                                """);

                        return;
                    }
                }
            }


            // ==========================================
            // REGISTRAR POSTULACIÓN
            // ==========================================

            String sql =
                    "INSERT INTO postulaciones " +
                            "(candidato_id, vacante_id, fecha_postulacion) " +
                            "VALUES (?, ?, ?)";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setInt(1, candidatoId);
                ps.setInt(2, Integer.parseInt(vacanteId));
                ps.setDate(
                        3,
                        java.sql.Date.valueOf(LocalDate.now())
                );

                ps.executeUpdate();
            }


            // Volver al listado
            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}