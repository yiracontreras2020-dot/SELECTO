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

@WebServlet("/ReporteVacantesServlet")
public class ReporteVacantesServlet extends HttpServlet {

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

        List<String[]> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String estado = request.getParameter("estado");

            if (estado != null) {
                estado = estado.trim();
            }

            String sql =
                    "SELECT v.id, v.titulo, v.descripcion, " +
                            "v.salario, e.nombre_empresa, " +
                            "v.fecha_publicacion, v.estado, " +
                            "COUNT(p.id) AS total_postulantes " +
                            "FROM vacantes v " +
                            "INNER JOIN empresas e ON v.empresa_id = e.id " +
                            "LEFT JOIN postulaciones p ON p.vacante_id = v.id ";

            if (estado != null && !estado.isEmpty()) {

                sql += "WHERE v.estado = ? ";
            }

            sql +=
                    "GROUP BY v.id, v.titulo, v.descripcion, v.salario, " +
                            "e.nombre_empresa, v.fecha_publicacion, v.estado " +
                            "ORDER BY v.fecha_publicacion DESC";


            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                if (estado != null && !estado.isEmpty()) {
                    ps.setString(1, estado);
                }

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        lista.add(new String[]{
                                rs.getString("id"),
                                rs.getString("titulo"),
                                rs.getString("descripcion"),
                                rs.getString("salario"),
                                rs.getString("nombre_empresa"),
                                rs.getString("fecha_publicacion"),
                                rs.getString("estado"),
                                rs.getString("total_postulantes")
                        });
                    }
                }
            }

            request.setAttribute("reporteVacantes", lista);

            request.getRequestDispatcher(
                    "reporteVacantes.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServletException(e);
        }
    }
}