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

@WebServlet("/ListarVacantes")
public class ListarVacantesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        try (Connection con = ConexionBD.conectar()) {

            String sql;

            PreparedStatement ps;

            // ADMIN puede ver todas las vacantes
            if ("ADMIN".equals(rol)) {

                sql = "SELECT v.id, v.titulo, v.descripcion, v.salario, " +
                        "e.nombre_empresa, v.fecha_publicacion, v.estado " +
                        "FROM vacantes v " +
                        "INNER JOIN empresas e ON v.empresa_id = e.id";

                ps = con.prepareStatement(sql);

            }

            // EMPRESA solo puede ver sus propias vacantes
            else if ("EMPRESA".equals(rol)) {

                Integer empresaId = (Integer) session.getAttribute("empresa_id");

                if (empresaId == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                sql = "SELECT v.id, v.titulo, v.descripcion, v.salario, " +
                        "e.nombre_empresa, v.fecha_publicacion, v.estado " +
                        "FROM vacantes v " +
                        "INNER JOIN empresas e ON v.empresa_id = e.id " +
                        "WHERE v.empresa_id = ?";

                ps = con.prepareStatement(sql);
                ps.setInt(1, empresaId);

            }

            // Otros roles no pueden acceder a este listado
            else {
                response.sendRedirect("vacantesDisponibles.jsp");
                return;
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new String[]{
                        rs.getString("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("salario"),
                        rs.getString("nombre_empresa"),
                        rs.getString("fecha_publicacion"),
                        rs.getString("estado")
                });
            }

            request.setAttribute("vacantes", lista);

            request.getRequestDispatcher("vacantes.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}