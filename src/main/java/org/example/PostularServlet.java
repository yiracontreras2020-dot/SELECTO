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
import java.time.LocalDate;

@WebServlet("/PostularServlet")
public class PostularServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Verificar que exista una sesión
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener candidato de la sesión
        Object candidatoObj = session.getAttribute("candidato_id");

        // Verificar que sea un candidato
        if (candidatoObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int candidatoId = (Integer) candidatoObj;

        String vacanteId = request.getParameter("vacante_id");

        if (vacanteId == null || vacanteId.isEmpty()) {
            response.sendRedirect("ListarVacantes");
            return;
        }

        try (Connection con = ConexionBD.conectar()) {

            // Evitar que el mismo candidato se postule dos veces
            String verificar =
                    "SELECT id FROM postulaciones " +
                            "WHERE candidato_id = ? AND vacante_id = ?";

            PreparedStatement psVerificar =
                    con.prepareStatement(verificar);

            psVerificar.setInt(1, candidatoId);
            psVerificar.setInt(2, Integer.parseInt(vacanteId));

            var rs = psVerificar.executeQuery();

            if (rs.next()) {

                response.sendRedirect("ListarVacantes?mensaje=yaPostulado");
                return;
            }

            // Registrar la postulación
            String sql =
                    "INSERT INTO postulaciones " +
                            "(candidato_id, vacante_id, fecha_postulacion) " +
                            "VALUES (?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, candidatoId);
            ps.setInt(2, Integer.parseInt(vacanteId));
            ps.setDate(
                    3,
                    java.sql.Date.valueOf(LocalDate.now())
            );

            ps.executeUpdate();

            response.sendRedirect(
                    "ListarVacantes?mensaje=postulacionExitosa"
            );

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}