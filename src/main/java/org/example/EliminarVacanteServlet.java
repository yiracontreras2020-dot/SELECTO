package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/EliminarVacanteServlet")
public class EliminarVacanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Verificar que exista una sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            response.sendRedirect("ListarVacantes");
            return;
        }

        try (Connection con = ConexionBD.conectar()) {

            String sql;

            PreparedStatement ps;

            // ADMIN puede eliminar cualquier vacante
            if ("ADMIN".equals(rol)) {

                sql = "DELETE FROM vacantes WHERE id = ?";

                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(id));

            }

            // EMPRESA solo puede eliminar sus propias vacantes
            else if ("EMPRESA".equals(rol)) {

                Integer empresaId =
                        (Integer) session.getAttribute("empresa_id");

                if (empresaId == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                sql = "DELETE FROM vacantes " +
                        "WHERE id = ? AND empresa_id = ?";

                ps = con.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(id));
                ps.setInt(2, empresaId);

            }

            // Otros roles no pueden eliminar vacantes
            else {
                response.sendRedirect("vacantesDisponibles.jsp");
                return;
            }

            ps.executeUpdate();

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}