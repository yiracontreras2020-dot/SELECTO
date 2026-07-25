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

@WebServlet("/ListarVacantesDisponibles")
public class ListarVacantesDisponiblesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar()) {

            String sql = """
                    SELECT v.id,
                           v.titulo,
                           v.descripcion,
                           v.salario,
                           e.nombre_empresa
                    FROM vacantes v
                    INNER JOIN empresas e
                    ON v.empresa_id = e.id
                    WHERE v.estado='Activa'
                    ORDER BY v.fecha_publicacion DESC
                    """;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                lista.add(new String[]{
                        rs.getString("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("salario"),
                        rs.getString("nombre_empresa")
                });

            }

            request.setAttribute("vacantes", lista);

            request.getRequestDispatcher("vacantesDisponibles.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}
