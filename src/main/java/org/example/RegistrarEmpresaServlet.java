package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/RegistrarEmpresa")
public class RegistrarEmpresaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre_empresa");
        String nit = request.getParameter("nit");
        String contacto = request.getParameter("contacto");
        String correo = request.getParameter("correo_empresa");

        try (Connection con = ConexionBD.conectar()) {

            String sql = "INSERT INTO empresas(nombre_empresa, nit, contacto, correo_empresa) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, nit);
            ps.setString(3, contacto);
            ps.setString(4, correo);

            ps.executeUpdate();

            response.sendRedirect("ListarEmpresas");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
