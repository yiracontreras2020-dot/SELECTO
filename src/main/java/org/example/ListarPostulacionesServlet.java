package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

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

        HttpSession session = request.getSession(false);

        // Verificar que haya sesión iniciada
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String usuario = (String) session.getAttribute("usuario");
        String rol = (String) session.getAttribute("rol");

        List<String[]> postulaciones = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String sql;

            /*
             * ADMIN:
             * Puede ver todas las postulaciones.
             */
            if ("ADMIN".equals(rol)) {

                sql = """
                        SELECT p.id, c.nombre, c.correo, v.titulo
                        FROM postulaciones p
                        INNER JOIN candidatos c ON p.candidato_id = c.id
                        INNER JOIN vacantes v ON p.vacante_id = v.id
                        """;

                /*
                 * CANDIDATO:
                 * Solo puede ver sus propias postulaciones.
                 *
                 * Se compara el usuario de la sesión
                 * con el correo del candidato.
                 */
            } else if ("CANDIDATO".equals(rol)) {

                sql = """
                        SELECT p.id, c.nombre, c.correo, v.titulo
                        FROM postulaciones p
                        INNER JOIN candidatos c ON p.candidato_id = c.id
                        INNER JOIN vacantes v ON p.vacante_id = v.id
                        WHERE c.correo = ?
                        """;

                /*
                 * EMPRESA:
                 * Por ahora no le mostramos las postulaciones desde
                 * este servlet.
                 */
            } else {

                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "No tienes permisos para consultar las postulaciones."
                );
                return;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            // Solo el candidato necesita parámetro
            if ("CANDIDATO".equals(rol)) {
                ps.setString(1, usuario);
            }

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