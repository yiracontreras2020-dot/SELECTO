package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/ActualizarEmpresaServlet")
public class ActualizarEmpresaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre_empresa");
        String nit = request.getParameter("nit");
        String contacto = request.getParameter("contacto");
        String correo = request.getParameter("correo_empresa");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "UPDATE empresas SET nombre_empresa=?, nit=?, contacto=?, correo_empresa=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, nit);
            ps.setString(3, contacto);
            ps.setString(4, correo);
            ps.setInt(5, Integer.parseInt(id));

            int filas = ps.executeUpdate();

            if (filas > 0) {
                response.sendRedirect("ListarEmpresas");
            } else {
                response.getWriter().println("<h2>No fue posible actualizar la empresa.</h2>");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}