package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListarCandidatos")
public class ListarCandidatosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            Statement st = con.createStatement();

            // Listar candidatos
            ResultSet rs = st.executeQuery("SELECT id, nombre, correo FROM candidatos");

            while (rs.next()) {
                lista.add(new String[]{
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                });
            }

            // Contar candidatos
            rs = st.executeQuery("SELECT COUNT(*) AS total FROM candidatos");
            int totalCandidatos = 0;
            if (rs.next()) {
                totalCandidatos = rs.getInt("total");
            }

            // Contar empresas
            rs = st.executeQuery("SELECT COUNT(*) AS total FROM empresas");
            int totalEmpresas = 0;
            if (rs.next()) {
                totalEmpresas = rs.getInt("total");
            }

            request.setAttribute("candidatos", lista);
            request.setAttribute("totalCandidatos", totalCandidatos);
            request.setAttribute("totalEmpresas", totalEmpresas);

            request.getRequestDispatcher("lista.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}