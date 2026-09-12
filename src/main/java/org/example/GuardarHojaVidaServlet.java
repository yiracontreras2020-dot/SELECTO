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

@WebServlet("/GuardarHojaVidaServlet")
public class GuardarHojaVidaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Obtener la sesión actual
        HttpSession session = request.getSession(false);

        // Verificar que el candidato haya iniciado sesión
        if (session == null ||
                session.getAttribute("candidato_id") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener el ID del candidato desde la sesión
        int candidatoId =
                (Integer) session.getAttribute("candidato_id");

        // Obtener los datos del formulario
        String telefono =
                request.getParameter("telefono");

        String perfilProfesional =
                request.getParameter("perfil_profesional");

        String formacionAcademica =
                request.getParameter("formacion_academica");

        String experienciaLaboral =
                request.getParameter("experiencia_laboral");

        String habilidades =
                request.getParameter("habilidades");

        try (Connection con = ConexionBD.conectar()) {

            String sql =
                    "UPDATE candidatos SET " +
                            "telefono = ?, " +
                            "perfil_profesional = ?, " +
                            "formacion_academica = ?, " +
                            "experiencia_laboral = ?, " +
                            "habilidades = ? " +
                            "WHERE id = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, telefono);
            ps.setString(2, perfilProfesional);
            ps.setString(3, formacionAcademica);
            ps.setString(4, experienciaLaboral);
            ps.setString(5, habilidades);
            ps.setInt(6, candidatoId);

            ps.executeUpdate();

            // Volver a la hoja de vida
            response.sendRedirect(
                    "hojaVida.jsp?guardado=ok"
            );

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}