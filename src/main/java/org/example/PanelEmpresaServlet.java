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

@WebServlet("/PanelEmpresa")
public class PanelEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ConexionBD.conectar()) {

            PreparedStatement ps1 =
                    con.prepareStatement("SELECT COUNT(*) FROM vacantes");

            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                request.setAttribute("totalVacantes", rs1.getInt(1));
            }

            PreparedStatement ps2 =
                    con.prepareStatement("SELECT COUNT(*) FROM postulaciones");

            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                request.setAttribute("totalPostulaciones", rs2.getInt(1));
            }

            request.getRequestDispatcher("panelEmpresa.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }
}