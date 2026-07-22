package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/editar")
public class EditarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection con = ConexionBD.conectar()) {

            String sql = "SELECT * FROM candidatos WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("nombre", rs.getString("nombre"));
                request.setAttribute("correo", rs.getString("correo"));
            }

            request.getRequestDispatcher("editar.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
