package cyberhub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexiones {

    public static final String BASE = "jdbc:hsqldb:hsql://localhost";
    private static final String USUARIO = "SA";
    private static final String CONTRASEÑA = "SA";

    public static Connection con;

    static {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(BASE, USUARIO, CONTRASEÑA);
            if (con != null) {
                System.out.println("Conexión creada exitosamente");
            } else {
                System.out.println("Error al crear la conexión");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static Connection crear() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(BASE, USUARIO, CONTRASEÑA);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return con;
    }

    public static void cerrar() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada exitosamente");
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
