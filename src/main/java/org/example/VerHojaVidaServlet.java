package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/VerHojaVidaServlet")
public class VerHojaVidaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la sesión actual
        HttpSession session = request.getSession(false);

        // Verificar que el candidato haya iniciado sesión
        if (session == null ||
                session.getAttribute("candidato_id") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener el ID del candidato
        int candidatoId =
                (Integer) session.getAttribute("candidato_id");

        try (Connection con = ConexionBD.conectar()) {

            String sql =
                    "SELECT id, nombre, correo, telefono, " +
                            "perfil_profesional, formacion_academica, " +
                            "experiencia_laboral, habilidades " +
                            "FROM candidatos " +
                            "WHERE id = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, candidatoId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute(
                        "id",
                        rs.getInt("id")
                );

                request.setAttribute(
                        "nombre",
                        rs.getString("nombre")
                );

                request.setAttribute(
                        "correo",
                        rs.getString("correo")
                );

                request.setAttribute(
                        "telefono",
                        rs.getString("telefono")
                );

                request.setAttribute(
                        "perfil_profesional",
                        rs.getString("perfil_profesional")
                );

                request.setAttribute(
                        "formacion_academica",
                        rs.getString("formacion_academica")
                );

                request.setAttribute(
                        "experiencia_laboral",
                        rs.getString("experiencia_laboral")
                );

                request.setAttribute(
                        "habilidades",
                        rs.getString("habilidades")
                );

                request.getRequestDispatcher(
                        "verHojaVida.jsp"
                ).forward(request, response);

            } else {

                response.sendRedirect("hojaVida.jsp");
            }

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}