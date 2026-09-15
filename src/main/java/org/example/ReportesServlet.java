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

@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Verificar sesión
        if (session == null ||
                session.getAttribute("usuario") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        // Verificar que sea ADMIN
        String rol = (String) session.getAttribute("rol");

        if (!"ADMIN".equalsIgnoreCase(rol)) {
            response.sendRedirect("login.jsp");
            return;
        }

        int totalCandidatos = 0;
        int totalEmpresas = 0;
        int totalVacantes = 0;
        int totalPostulaciones = 0;

        try (Connection con = ConexionBD.conectar()) {

            // Total de candidatos
            String sqlCandidatos =
                    "SELECT COUNT(*) FROM candidatos";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlCandidatos);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalCandidatos = rs.getInt(1);
                }
            }

            // Total de empresas
            String sqlEmpresas =
                    "SELECT COUNT(*) FROM empresas";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlEmpresas);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalEmpresas = rs.getInt(1);
                }
            }

            // Total de vacantes
            String sqlVacantes =
                    "SELECT COUNT(*) FROM vacantes";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlVacantes);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalVacantes = rs.getInt(1);
                }
            }

            // Total de postulaciones
            String sqlPostulaciones =
                    "SELECT COUNT(*) FROM postulaciones";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlPostulaciones);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    totalPostulaciones = rs.getInt(1);
                }
            }

            // Enviar datos al JSP
            request.setAttribute(
                    "totalCandidatos",
                    totalCandidatos
            );

            request.setAttribute(
                    "totalEmpresas",
                    totalEmpresas
            );

            request.setAttribute(
                    "totalVacantes",
                    totalVacantes
            );

            request.setAttribute(
                    "totalPostulaciones",
                    totalPostulaciones
            );

            // Mostrar página de reportes
            request.getRequestDispatcher(
                    "reportes.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServletException(e);
        }
    }
}