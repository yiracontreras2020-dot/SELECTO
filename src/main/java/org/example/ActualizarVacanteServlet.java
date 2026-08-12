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

@WebServlet("/ActualizarVacanteServlet")
public class ActualizarVacanteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String salario = request.getParameter("salario");
        String fecha = request.getParameter("fecha_publicacion");
        String estado = request.getParameter("estado");

        if (id == null || id.isEmpty()) {
            response.sendRedirect("ListarVacantes");
            return;
        }

        try (Connection con = ConexionBD.conectar()) {

            PreparedStatement ps;

            // ADMIN puede actualizar cualquier vacante
            if ("ADMIN".equals(rol)) {

                String sql = "UPDATE vacantes SET " +
                        "titulo=?, descripcion=?, salario=?, " +
                        "fecha_publicacion=?, estado=? " +
                        "WHERE id=?";

                ps = con.prepareStatement(sql);

                ps.setString(1, titulo);
                ps.setString(2, descripcion);
                ps.setBigDecimal(3, new java.math.BigDecimal(salario));
                ps.setDate(4, java.sql.Date.valueOf(fecha));
                ps.setString(5, estado);
                ps.setInt(6, Integer.parseInt(id));

            }

            // EMPRESA solo puede actualizar sus propias vacantes
            else if ("EMPRESA".equals(rol)) {

                Integer empresaId =
                        (Integer) session.getAttribute("empresa_id");

                if (empresaId == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                String sql = "UPDATE vacantes SET " +
                        "titulo=?, descripcion=?, salario=?, " +
                        "fecha_publicacion=?, estado=? " +
                        "WHERE id=? AND empresa_id=?";

                ps = con.prepareStatement(sql);

                ps.setString(1, titulo);
                ps.setString(2, descripcion);
                ps.setBigDecimal(3, new java.math.BigDecimal(salario));
                ps.setDate(4, java.sql.Date.valueOf(fecha));
                ps.setString(5, estado);
                ps.setInt(6, Integer.parseInt(id));
                ps.setInt(7, empresaId);

            }

            // Otros roles no pueden actualizar vacantes
            else {
                response.sendRedirect("vacantesDisponibles.jsp");
                return;
            }

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0) {
                response.sendRedirect("ListarVacantes");
            } else {
                response.sendRedirect("ListarVacantes");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}