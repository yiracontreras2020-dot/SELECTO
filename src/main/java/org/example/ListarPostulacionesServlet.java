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

        // Verificar sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        try (Connection con = ConexionBD.conectar()) {

            String sql;
            PreparedStatement ps;

            // ADMIN puede ver todas las postulaciones
            if ("ADMIN".equals(rol)) {

                sql =
                        "SELECT p.id, c.nombre, c.correo, v.titulo " +
                                "FROM postulaciones p " +
                                "INNER JOIN candidatos c ON p.candidato_id = c.id " +
                                "INNER JOIN vacantes v ON p.vacante_id = v.id";

                ps = con.prepareStatement(sql);
            }

            // CANDIDATO solo puede ver sus propias postulaciones
            else if ("CANDIDATO".equals(rol)) {

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

            }

            // Otros roles no pueden acceder a este listado
            else {
                response.sendRedirect("dashboard.jsp");
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