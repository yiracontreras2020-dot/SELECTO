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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                response.sendRedirect("ListarCandidatos");

            } else {

                response.setContentType("text/html;charset=UTF-8");

                response.getWriter().println("""
                        <html>
                        <head>
                            <title>Error</title>
                        </head>
                        <body style='font-family:Arial;text-align:center;margin-top:80px;'>

                        <h2>Usuario o contraseña incorrectos.</h2>

                        <br>

                        <a href='login.jsp'>Volver al inicio de sesión</a>

                        </body>
                        </html>
                        """);
            }

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}
