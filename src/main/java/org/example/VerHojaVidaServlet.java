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

        System.out.println(">>> VerHojaVidaServlet EJECUTADO");

        HttpSession session = request.getSession(false);

        if (session == null) {
            System.out.println(">>> No existe sesión");
            response.sendRedirect("login.jsp");
            return;
        }

        Object candidatoObj = session.getAttribute("candidato_id");

        System.out.println(">>> candidato_id: " + candidatoObj);

        if (candidatoObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int candidatoId = (Integer) candidatoObj;

        try (Connection con = ConexionBD.conectar()) {

            String sql =
                    "SELECT id, nombre, correo, telefono, " +
                            "perfil_profesional, formacion_academica, " +
                            "experiencia_laboral, habilidades " +
                            "FROM candidatos " +
                            "WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, candidatoId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(">>> Candidato encontrado: "
                        + rs.getString("nombre"));

                request.setAttribute("nombre",
                        rs.getString("nombre"));

                request.setAttribute("correo",
                        rs.getString("correo"));

                request.setAttribute("telefono",
                        rs.getString("telefono"));

                request.setAttribute("perfil_profesional",
                        rs.getString("perfil_profesional"));

                request.setAttribute("formacion_academica",
                        rs.getString("formacion_academica"));

                request.setAttribute("experiencia_laboral",
                        rs.getString("experiencia_laboral"));

                request.setAttribute("habilidades",
                        rs.getString("habilidades"));

                request.getRequestDispatcher(
                        "verHojaVida.jsp"
                ).forward(request, response);

            } else {

                System.out.println(">>> No se encontró el candidato");

                response.sendRedirect("hojaVida.jsp");
            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}