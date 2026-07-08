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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {
            // Aquí pedimos el ID también
            String sql = "SELECT id, nombre, correo FROM candidatos";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                // Guardamos: id (0), nombre (1), correo (2)
                lista.add(new String[]{
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                });
            }

            request.setAttribute("candidatos", lista);
            request.getRequestDispatcher("lista.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

