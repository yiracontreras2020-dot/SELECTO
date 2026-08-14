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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try (Connection con = ConexionBD.conectar()) {

            String sql =
                    "SELECT id, usuario, password, rol, empresa_id, candidato_id " +
                            "FROM usuarios " +
                            "WHERE usuario = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String rol = rs.getString("rol");

                HttpSession session = request.getSession();

                session.setAttribute("usuario", rs.getString("usuario"));
                session.setAttribute("rol", rol);

                // =========================
                // EMPRESA
                // =========================

                if ("EMPRESA".equalsIgnoreCase(rol)) {

                    int empresaId = rs.getInt("empresa_id");

                    session.setAttribute("empresa_id", empresaId);

                    response.sendRedirect("panelEmpresa.jsp");

                    // =========================
                    // CANDIDATO
                    // =========================

                } else if ("CANDIDATO".equalsIgnoreCase(rol)) {

                    int candidatoId = rs.getInt("candidato_id");

                    session.setAttribute("candidato_id", candidatoId);

                    response.sendRedirect("ListarVacantes");

                    // =========================
                    // ADMINISTRADOR
                    // =========================

                } else if ("ADMIN".equalsIgnoreCase(rol)) {

                    response.sendRedirect("dashboard.jsp");

                } else {

                    response.sendRedirect("login.jsp");
                }

            } else {

                response.setContentType("text/html;charset=UTF-8");

                response.getWriter().println("""
                    <html>
                    <head>
                        <meta charset="UTF-8">
                        <title>Error de inicio de sesión</title>
                    </head>

                    <body>

                        <h2>Usuario o contraseña incorrectos</h2>

                        <a href="login.jsp">
                            Volver al inicio de sesión
                        </a>

                    </body>
                    </html>
                    """);
            }

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}