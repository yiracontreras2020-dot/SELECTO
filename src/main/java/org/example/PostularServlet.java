package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

@WebServlet("/PostularServlet")
public class PostularServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String candidatoId = request.getParameter("candidato_id");
        String vacanteId = request.getParameter("vacante_id");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "INSERT INTO postulaciones (candidato_id, vacante_id, fecha_postulacion) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(candidatoId));
            ps.setInt(2, Integer.parseInt(vacanteId));
            ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
