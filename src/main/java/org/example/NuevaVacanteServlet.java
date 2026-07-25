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

@WebServlet("/NuevaVacanteServlet")
public class NuevaVacanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> empresas = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String sql = "SELECT id, nombre_empresa FROM empresas ORDER BY nombre_empresa";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                empresas.add(new String[]{
                        rs.getString("id"),
                        rs.getString("nombre_empresa")
                });
            }

            request.setAttribute("empresas", empresas);

            request.getRequestDispatcher("vacante.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
