package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Nombre de la base de datos: seleccion (sin tilde)
    private static final String URL = "jdbc:mysql://localhost:3306/seleccion";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a la base de datos seleccion!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el conector de MySQL.");
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar. Verifica que XAMPP esté prendido.");
            e.printStackTrace();
        }
        return conexion;
    }
}
