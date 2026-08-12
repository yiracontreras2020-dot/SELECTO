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

@WebServlet("/PanelEmpresa")
public class PanelEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Verificar que exista una sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Verificar que sea una empresa
        String rol = (String) session.getAttribute("rol");

        if (!"EMPRESA".equals(rol)) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        // Obtener el ID de la empresa que inició sesión
        Integer empresaId = (Integer) session.getAttribute("empresa_id");

        if (empresaId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<String[]> vacantes = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            // ==========================================
            // 1. CONTAR VACANTES DE ESTA EMPRESA
            // ==========================================

            String sqlVacantes =
                    "SELECT COUNT(*) FROM vacantes WHERE empresa_id = ?";

            try (PreparedStatement ps = con.prepareStatement(sqlVacantes)) {

                ps.setInt(1, empresaId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        request.setAttribute(
                                "totalVacantes",
                                rs.getInt(1)
                        );
                    }
                }
            }

            // ==========================================
            // 2. CONTAR POSTULACIONES RECIBIDAS
            // ==========================================

            String sqlPostulaciones =
                    "SELECT COUNT(*) " +
                            "FROM postulaciones p " +
                            "INNER JOIN vacantes v ON p.vacante_id = v.id " +
                            "WHERE v.empresa_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlPostulaciones)) {

                ps.setInt(1, empresaId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        request.setAttribute(
                                "totalPostulaciones",
                                rs.getInt(1)
                        );
                    }
                }
            }

            // ==========================================
            // 3. LISTAR SOLO LAS VACANTES DE LA EMPRESA
            // ==========================================

            String sqlLista =
                    "SELECT v.id, v.titulo, v.descripcion, " +
                            "v.salario, v.fecha_publicacion, v.estado " +
                            "FROM vacantes v " +
                            "WHERE v.empresa_id = ? " +
                            "ORDER BY v.id DESC";

            try (PreparedStatement ps =
                         con.prepareStatement(sqlLista)) {

                ps.setInt(1, empresaId);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        vacantes.add(new String[]{
                                rs.getString("id"),
                                rs.getString("titulo"),
                                rs.getString("descripcion"),
                                rs.getString("salario"),
                                rs.getString("fecha_publicacion"),
                                rs.getString("estado")
                        });
                    }
                }
            }

            // Enviar las vacantes al JSP
            request.setAttribute("vacantesEmpresa", vacantes);

            // Mostrar el panel
            request.getRequestDispatcher("panelEmpresa.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}