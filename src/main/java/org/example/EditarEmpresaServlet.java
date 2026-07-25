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

@WebServlet("/EditarEmpresaServlet")
public class EditarEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "SELECT * FROM empresas WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("nombre_empresa", rs.getString("nombre_empresa"));
                request.setAttribute("nit", rs.getString("nit"));
                request.setAttribute("contacto", rs.getString("contacto"));
                request.setAttribute("correo_empresa", rs.getString("correo_empresa"));

                request.getRequestDispatcher("editarEmpresa.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
