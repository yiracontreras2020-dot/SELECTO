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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ReportePostulacionesServlet")
public class ReportePostulacionesServlet extends HttpServlet {

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

        List<String[]> reporte = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String vacanteId = request.getParameter("vacante_id");
            String candidatoId = request.getParameter("candidato_id");

            String sql =
                    "SELECT p.id, " +
                            "c.nombre, " +
                            "c.correo, " +
                            "v.titulo, " +
                            "e.nombre_empresa " +
                            "FROM postulaciones p " +
                            "INNER JOIN candidatos c ON p.candidato_id = c.id " +
                            "INNER JOIN vacantes v ON p.vacante_id = v.id " +
                            "INNER JOIN empresas e ON v.empresa_id = e.id ";

            boolean hayFiltro = false;

            if (vacanteId != null && !vacanteId.isEmpty()) {
                sql += "WHERE v.id = ? ";
                hayFiltro = true;
            }

            if (candidatoId != null && !candidatoId.isEmpty()) {

                if (hayFiltro) {
                    sql += "AND p.candidato_id = ? ";
                } else {
                    sql += "WHERE p.candidato_id = ? ";
                }

                hayFiltro = true;
            }

            sql += "ORDER BY p.id DESC";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                int parametro = 1;

                if (vacanteId != null && !vacanteId.isEmpty()) {
                    ps.setInt(parametro++, Integer.parseInt(vacanteId));
                }

                if (candidatoId != null && !candidatoId.isEmpty()) {
                    ps.setInt(parametro++, Integer.parseInt(candidatoId));
                }

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        reporte.add(new String[]{
                                rs.getString("id"),
                                rs.getString("nombre"),
                                rs.getString("correo"),
                                rs.getString("titulo"),
                                rs.getString("nombre_empresa")
                        });
                    }
                }
            }

            request.setAttribute(
                    "reportePostulaciones",
                    reporte
            );

            List<String[]> candidatosFiltro = new ArrayList<>();

            String sqlCandidatos =
                    "SELECT id, nombre FROM candidatos ORDER BY nombre";

            try (PreparedStatement psCandidatos =
                         con.prepareStatement(sqlCandidatos);
                 ResultSet rsCandidatos =
                         psCandidatos.executeQuery()) {

                while (rsCandidatos.next()) {

                    candidatosFiltro.add(new String[]{
                            rsCandidatos.getString("id"),
                            rsCandidatos.getString("nombre")
                    });
                }
            }

            request.setAttribute(
                    "candidatosFiltro",
                    candidatosFiltro
            );

            request.getRequestDispatcher(
                    "reportePostulaciones.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServletException(e);
        }
    }
}
