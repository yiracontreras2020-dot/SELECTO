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

@WebServlet("/ListarPostulaciones")
public class ListarPostulacionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> postulaciones = new ArrayList<>();

        HttpSession session = request.getSession(false);

        // Verificar que exista una sesión
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        if (rol == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection con = ConexionBD.conectar()) {

            String sql;

            PreparedStatement ps;

            // =========================
            // CANDIDATO
            // =========================

            if ("CANDIDATO".equalsIgnoreCase(rol)) {

                Integer candidatoId =
                        (Integer) session.getAttribute("candidato_id");

                if (candidatoId == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                sql =
                        "SELECT p.id, c.nombre, c.correo, v.titulo " +
                                "FROM postulaciones p " +
                                "INNER JOIN candidatos c ON p.candidato_id = c.id " +
                                "INNER JOIN vacantes v ON p.vacante_id = v.id " +
                                "WHERE p.candidato_id = ?";

                ps = con.prepareStatement(sql);

                ps.setInt(1, candidatoId);

                // =========================
                // EMPRESA
                // =========================

            } else if ("EMPRESA".equalsIgnoreCase(rol)) {

                Integer empresaId =
                        (Integer) session.getAttribute("empresa_id");

                if (empresaId == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                sql =
                        "SELECT p.id, c.nombre, c.correo, v.titulo " +
                                "FROM postulaciones p " +
                                "INNER JOIN candidatos c ON p.candidato_id = c.id " +
                                "INNER JOIN vacantes v ON p.vacante_id = v.id " +
                                "WHERE v.empresa_id = ?";

                ps = con.prepareStatement(sql);

                ps.setInt(1, empresaId);

                // =========================
                // ADMINISTRADOR
                // =========================

            } else if ("ADMIN".equalsIgnoreCase(rol)) {

                sql =
                        "SELECT p.id, c.nombre, c.correo, v.titulo " +
                                "FROM postulaciones p " +
                                "INNER JOIN candidatos c ON p.candidato_id = c.id " +
                                "INNER JOIN vacantes v ON p.vacante_id = v.id";

                ps = con.prepareStatement(sql);

            } else {

                response.sendRedirect("login.jsp");
                return;
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                postulaciones.add(new String[]{
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("titulo")
                });
            }

            request.setAttribute("postulaciones", postulaciones);

            request.getRequestDispatcher("postulaciones.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}