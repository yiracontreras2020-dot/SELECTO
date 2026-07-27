package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListarPostulaciones")
public class ListarPostulacionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> postulaciones = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String sql =
                    "SELECT p.id, c.nombre, c.correo, v.titulo " +
                            "FROM postulaciones p " +
                            "INNER JOIN candidatos c ON p.candidato_id = c.id " +
                            "INNER JOIN vacantes v ON p.vacante_id = v.id";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                postulaciones.add(new String[]{
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("titulo")
                });

            }

            request.setAttribute("postulaciones", postulaciones);

            request.getRequestDispatcher("postulaciones.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }
}
