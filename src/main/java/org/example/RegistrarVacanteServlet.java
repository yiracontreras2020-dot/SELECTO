package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

@WebServlet("/RegistrarVacanteServlet")
public class RegistrarVacanteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String salario = request.getParameter("salario");
        String fecha = request.getParameter("fecha_publicacion");
        String estado = request.getParameter("estado");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("empresa_id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int empresaId = (Integer) session.getAttribute("empresa_id");

        try (Connection con = ConexionBD.conectar()) {

            String sql = """
                INSERT INTO vacantes
                (titulo, descripcion, salario, empresa_id, fecha_publicacion, estado)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setBigDecimal(3, new BigDecimal(salario));
            ps.setInt(4, empresaId);
            ps.setDate(5, Date.valueOf(fecha));
            ps.setString(6, estado);

            ps.executeUpdate();

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}