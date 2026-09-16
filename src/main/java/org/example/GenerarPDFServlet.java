package org.example;

import com.lowagie.text .Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/GenerarPDFServlet")
public class GenerarPDFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Verificar sesión
        if (session == null ||
                session.getAttribute("candidato_id") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int candidatoId =
                (Integer) session.getAttribute("candidato_id");

        // Configurar respuesta como PDF
        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=hoja_de_vida.pdf"
        );

        Document documento = new Document();

        try {

            PdfWriter.getInstance(
                    documento,
                    response.getOutputStream()
            );

            documento.open();

            Font titulo = new Font(
                    Font.HELVETICA,
                    20,
                    Font.BOLD
            );

            Font subtitulo = new Font(
                    Font.HELVETICA,
                    14,
                    Font.BOLD
            );

            Font texto = new Font(
                    Font.HELVETICA,
                    11,
                    Font.NORMAL
            );

            documento.add(
                    new Paragraph(
                            "HOJA DE VIDA",
                            titulo
                    )
            );

            documento.add(
                    new Paragraph(
                            "SELECTO",
                            subtitulo
                    )
            );

            documento.add(
                    new Paragraph(" ")
            );

            try (Connection con = ConexionBD.conectar()) {

                String sql =
                        "SELECT nombre, correo, telefono, " +
                                "perfil_profesional, formacion_academica, " +
                                "experiencia_laboral, habilidades " +
                                "FROM candidatos " +
                                "WHERE id = ?";

                try (PreparedStatement ps =
                             con.prepareStatement(sql)) {

                    ps.setInt(1, candidatoId);

                    try (ResultSet rs =
                                 ps.executeQuery()) {

                        if (rs.next()) {

                            documento.add(
                                    new Paragraph(
                                            "INFORMACIÓN PERSONAL",
                                            subtitulo
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            "Nombre: " +
                                                    rs.getString("nombre"),
                                            texto
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            "Correo: " +
                                                    rs.getString("correo"),
                                            texto
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            "Teléfono: " +
                                                    rs.getString("telefono"),
                                            texto
                                    )
                            );

                            documento.add(
                                    new Paragraph(" ")
                            );

                            documento.add(
                                    new Paragraph(
                                            "PERFIL PROFESIONAL",
                                            subtitulo
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            rs.getString(
                                                    "perfil_profesional"
                                            ),
                                            texto
                                    )
                            );

                            documento.add(
                                    new Paragraph(" ")
                            );

                            documento.add(
                                    new Paragraph(
                                            "FORMACIÓN ACADÉMICA",
                                            subtitulo
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            rs.getString(
                                                    "formacion_academica"
                                            ),
                                            texto
                                    )
                            );

                            documento.add(
                                    new Paragraph(" ")
                            );

                            documento.add(
                                    new Paragraph(
                                            "EXPERIENCIA LABORAL",
                                            subtitulo
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            rs.getString(
                                                    "experiencia_laboral"
                                            ),
                                            texto
                                    )
                            );

                            documento.add(
                                    new Paragraph(" ")
                            );

                            documento.add(
                                    new Paragraph(
                                            "HABILIDADES",
                                            subtitulo
                                    )
                            );

                            documento.add(
                                    new Paragraph(
                                            rs.getString(
                                                    "habilidades"
                                            ),
                                            texto
                                    )
                            );

                        } else {

                            documento.add(
                                    new Paragraph(
                                            "No se encontró información del candidato.",
                                            texto
                                    )
                            );
                        }
                    }
                }

            }

            documento.close();

        } catch (DocumentException | SQLException e) {

            e.printStackTrace();

            throw new ServletException(
                    "Error al generar la hoja de vida en PDF.",
                    e
            );
        }
    }
}