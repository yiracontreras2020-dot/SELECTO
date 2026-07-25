package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/EliminarEmpresaServlet")
public class EliminarEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "DELETE FROM empresas WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ps.executeUpdate();

            response.sendRedirect("ListarEmpresas");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
