package org.example;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/dashboard",
        "/dashboard.jsp",

        // Candidatos
        "/ListarCandidatos",
        "/RegistrarServlet",
        "/EditarServlet",
        "/EliminarServlet",

        // Empresas
        "/ListarEmpresas",
        "/RegistrarEmpresaServlet",
        "/EditarEmpresaServlet",
        "/EliminarEmpresaServlet",
        "/PanelEmpresa",

        // Vacantes
        "/ListarVacantes",
        "/RegistrarVacanteServlet",
        "/EditarVacanteServlet",
        "/EliminarVacanteServlet",
        "/ListarVacantesDisponibles",

        // Postulaciones
        "/PostularServlet",
        "/ListarPostulaciones",

        // Cerrar sesión
        "/logout"
})
public class AuthFilter extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        System.out.println(">>> AuthFilter ejecutándose...");

        HttpSession session = request.getSession(false);

        boolean autenticado = session != null &&
                session.getAttribute("usuario") != null;

        if (autenticado) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
