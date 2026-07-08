package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recoger datos del formulario (index.jsp)
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 2. Intentar guardar en la base de datos
        try (Connection con = ConexionBD.conectar()) {
            if (con != null) {
                String sql = "INSERT INTO candidatos (nombre, correo, password) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, correo);
                ps.setString(3, password); // Recuerda que luego aprenderemos a encriptarla

                int filasInsercion = ps.executeUpdate();

                if (filasInsercion > 0) {
                    out.println("<html><body>");
                    out.println("<h2 style='color: green;'>¡Registro guardado en la base de datos!</h2>");
                    out.println("<p>Bienvenido/a, " + nombre + ". Ya estás en el sistema.</p>");
                    out.println("<br><a href='ListarCandidatos'>Ver Lista de candidatos</a>");
                    out.println("</body></html>");
                }
            } else {
                out.println("<h2>Error: No se pudo conectar a la base de datos.</h2>");
            }
        } catch (SQLException e) {
            out.println("<h2>Error al registrar: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}