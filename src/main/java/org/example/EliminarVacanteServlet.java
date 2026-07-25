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

        String id = request.getParameter("id");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "DELETE FROM vacantes WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ps.executeUpdate();

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
