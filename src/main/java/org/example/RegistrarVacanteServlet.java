package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/RegistrarVacanteServlet")
public class RegistrarVacanteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String salario = request.getParameter("salario");
        String empresaId = request.getParameter("empresa_id");
        String fecha = request.getParameter("fecha_publicacion");
        String estado = request.getParameter("estado");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "INSERT INTO vacantes (titulo, descripcion, salario, empresa_id, fecha_publicacion, estado) VALUES (?, ?, ?, ?, ?, ?)";

            System.out.println("Título: " + titulo);
            System.out.println("Empresa ID: " + empresaId);
            System.out.println("Fecha: " + fecha);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setBigDecimal(3, new java.math.BigDecimal(salario));
            ps.setInt(4, Integer.parseInt(empresaId));
            ps.setDate(5, java.sql.Date.valueOf(fecha));
            ps.setString(6, estado);

            ps.executeUpdate();

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}