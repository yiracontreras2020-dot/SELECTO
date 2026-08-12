package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/EditarVacanteServlet")
public class EditarVacanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");
        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            response.sendRedirect("ListarVacantes");
            return;
        }

        try (Connection con = ConexionBD.conectar()) {

            String sql;
            PreparedStatement ps;

            // ADMIN puede editar cualquier vacante
            if ("ADMIN".equals(rol)) {

                sql = "SELECT * FROM vacantes WHERE id = ?";

                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(id));

            }

            // EMPRESA solo puede editar sus propias vacantes
            else if ("EMPRESA".equals(rol)) {

                Integer empresaId =
                        (Integer) session.getAttribute("empresa_id");

                if (empresaId == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                sql = "SELECT * FROM vacantes " +
                        "WHERE id = ? AND empresa_id = ?";

                ps = con.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(id));
                ps.setInt(2, empresaId);

            }

            // Cualquier otro rol no puede editar vacantes
            else {
                response.sendRedirect("vacantesDisponibles.jsp");
                return;
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("titulo", rs.getString("titulo"));
                request.setAttribute("descripcion", rs.getString("descripcion"));
                request.setAttribute("salario", rs.getBigDecimal("salario"));
                request.setAttribute("empresa_id", rs.getInt("empresa_id"));
                request.setAttribute("fecha_publicacion",
                        rs.getDate("fecha_publicacion"));
                request.setAttribute("estado", rs.getString("estado"));

                request.getRequestDispatcher("editarvacante.jsp")
                        .forward(request, response);

            } else {

                // La vacante no existe o no pertenece a la empresa
                response.sendRedirect("ListarVacantes");
            }

        } catch (NumberFormatException e) {

            response.sendRedirect("ListarVacantes");

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}