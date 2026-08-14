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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        try (Connection con = ConexionBD.conectar()) {

            con.setAutoCommit(false);

            // 1. Registrar candidato
            String sqlCandidato =
                    "INSERT INTO candidatos (nombre, correo, password) VALUES (?, ?, ?)";

            PreparedStatement psCandidato =
                    con.prepareStatement(sqlCandidato, PreparedStatement.RETURN_GENERATED_KEYS);

            psCandidato.setString(1, nombre);
            psCandidato.setString(2, correo);
            psCandidato.setString(3, password);

            psCandidato.executeUpdate();

            ResultSet generatedKeys = psCandidato.getGeneratedKeys();

            int candidatoId;

            if (generatedKeys.next()) {
                candidatoId = generatedKeys.getInt(1);
            } else {
                throw new ServletException("No se pudo obtener el ID del candidato.");
            }

            // 2. Crear usuario para permitir el inicio de sesión
            String sqlUsuario =
                    "INSERT INTO usuarios (usuario, password, rol, candidato_id) " +
                            "VALUES (?, ?, 'CANDIDATO', ?)";

            PreparedStatement psUsuario =
                    con.prepareStatement(sqlUsuario);

            // El correo será el usuario para iniciar sesión
            psUsuario.setString(1, correo);
            psUsuario.setString(2, password);
            psUsuario.setInt(3, candidatoId);

            psUsuario.executeUpdate();

            con.commit();

            response.sendRedirect("login.jsp");

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}