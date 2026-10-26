
package com.softpyme.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {

    private static final String URL =
            System.getenv().getOrDefault(
                    "SOFTPYME_DB_URL",
                    "jdbc:mysql://localhost:3306/softpyme"
            );

    private static final String USUARIO =
            System.getenv().getOrDefault(
                    "SOFTPYME_DB_USER",
                    "root"
            );

    private static final String CLAVE =
            System.getenv("SOFTPYME_DB_PASSWORD") == null
                    ? ""
                    : System.getenv("SOFTPYME_DB_PASSWORD");

    private ConexionBD() {
    }

    public static Connection obtenerConexion()
            throws SQLException {

        return DriverManager.getConnection(
                URL, USUARIO, CLAVE
        );
    }
}
