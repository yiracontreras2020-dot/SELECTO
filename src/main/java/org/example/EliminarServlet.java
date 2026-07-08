package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;        // <--- Esto quita el rojo de Connection
import java.sql.PreparedStatement;  // <--- Esto quita el rojo de PreparedStatement// <--- Esto quita el rojo de la línea 22

@WebServlet("/EliminarServlet")
public class EliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Capturamos el id que viene de la tabla
        String idCandidato = request.getParameter("id");
        // Agregamos "org.example." antes del nombre de la clase
        try (Connection con = org.example.ConexionBD.conectar()) {
            // Usamos el nombre exacto de tu tabla: candidatos
            String sql = "DELETE FROM candidatos WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCandidato);

            ps.executeUpdate();

            // Te devuelve a la lista automáticamente para ver que ya no está
            response.sendRedirect("ListarCandidatos");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
