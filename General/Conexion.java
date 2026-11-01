package Polizas.General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Conexión a MariaDB con JDBC (los modelos heredan de esta clase)
public class Conexion {

    // Constantes: datos de conexión
    private static final String URL = "jdbc:mariadb://localhost:3306/polizas";
    private static final String USUARIO = "poliza";
    private static final String PASSWORD = "poliza123";

    // Atributos
    public Connection cnx;        // conexión
    public PreparedStatement ps;  // consulta SQL
    public ResultSet rs;          // resultado del SELECT

    // Método: abre la conexión
    public void conectar() {
        try {
            cnx = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    // Método: cierra la conexión si existe
    public void close() {
        try {
            if (cnx != null) {
                cnx.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error. " + ex.getMessage());
        }
    }
}
