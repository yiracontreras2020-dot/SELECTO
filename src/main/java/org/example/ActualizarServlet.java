package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/ActualizarServlet")
public class ActualizarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "UPDATE candidatos SET nombre = ?, correo = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setInt(3, id);

            ps.executeUpdate();

            response.sendRedirect("ListarCandidatos");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}