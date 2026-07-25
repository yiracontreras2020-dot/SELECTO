package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/EditarVacanteServlet")
public class EditarVacanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "SELECT * FROM vacantes WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("titulo", rs.getString("titulo"));
                request.setAttribute("descripcion", rs.getString("descripcion"));
                request.setAttribute("salario", rs.getBigDecimal("salario"));
                request.setAttribute("empresa_id", rs.getInt("empresa_id"));
                request.setAttribute("fecha_publicacion", rs.getDate("fecha_publicacion"));
                request.setAttribute("estado", rs.getString("estado"));

                request.getRequestDispatcher("editarvacante.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
