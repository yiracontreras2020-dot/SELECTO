package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/ActualizarVacanteServlet")
public class ActualizarVacanteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String salario = request.getParameter("salario");
        String empresaId = request.getParameter("empresa_id");
        String fecha = request.getParameter("fecha_publicacion");
        String estado = request.getParameter("estado");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "UPDATE vacantes SET titulo=?, descripcion=?, salario=?, empresa_id=?, fecha_publicacion=?, estado=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setBigDecimal(3, new java.math.BigDecimal(salario));
            ps.setInt(4, Integer.parseInt(empresaId));
            ps.setDate(5, java.sql.Date.valueOf(fecha));
            ps.setString(6, estado);
            ps.setInt(7, Integer.parseInt(id));

            ps.executeUpdate();

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
