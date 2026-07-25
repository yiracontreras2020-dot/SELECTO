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

@WebServlet("/ListarVacantes")
public class ListarVacantesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String sql = "SELECT v.id, v.titulo, v.descripcion, v.salario, e.nombre_empresa, v.fecha_publicacion, v.estado " +
                    "FROM vacantes v " +
                    "INNER JOIN empresas e ON v.empresa_id = e.id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                lista.add(new String[]{
                        rs.getString("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("salario"),
                        rs.getString("nombre_empresa"),
                        rs.getString("fecha_publicacion"),
                        rs.getString("estado")
                });
            }

            request.setAttribute("vacantes", lista);
            request.getRequestDispatcher("vacantes.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
