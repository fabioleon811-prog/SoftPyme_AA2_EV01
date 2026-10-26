
package com.softpyme.dao;

import com.softpyme.conexion.ConexionBD;
import com.softpyme.modelo.RegistroPeso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroPesoDAO {

    // INSERTAR
    public boolean insertar(RegistroPeso registro)
            throws SQLException {

        String sql = """
                INSERT INTO registro_peso
                    (usuario, peso, fecha)
                VALUES (?, ?, ?)
                """;

        try (Connection conexion =
                     ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, registro.getUsuario());
            sentencia.setBigDecimal(2, registro.getPeso());
            sentencia.setDate(
                    3, Date.valueOf(registro.getFecha()));

            return sentencia.executeUpdate() == 1;
        }
    }

    // CONSULTAR TODOS LOS REGISTROS
    public List<RegistroPeso> consultarTodos()
            throws SQLException {

        String sql = """
                SELECT id, usuario, peso, fecha
                FROM registro_peso
                ORDER BY fecha DESC, id DESC
                """;

        List<RegistroPeso> registros = new ArrayList<>();

        try (Connection conexion =
                     ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado =
                     sentencia.executeQuery()) {

            while (resultado.next()) {
                registros.add(new RegistroPeso(
                        resultado.getInt("id"),
                        resultado.getString("usuario"),
                        resultado.getBigDecimal("peso"),
                        resultado.getDate("fecha").toLocalDate()
                ));
            }
        }

        return registros;
    }

    // CONSULTAR POR ID
    public RegistroPeso consultarPorId(int id)
            throws SQLException {

        String sql = """
                SELECT id, usuario, peso, fecha
                FROM registro_peso
                WHERE id = ?
                """;

        try (Connection conexion =
                     ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado =
                         sentencia.executeQuery()) {

                if (resultado.next()) {
                    return new RegistroPeso(
                            resultado.getInt("id"),
                            resultado.getString("usuario"),
                            resultado.getBigDecimal("peso"),
                            resultado.getDate("fecha").toLocalDate()
                    );
                }
            }
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizar(RegistroPeso registro)
            throws SQLException {

        String sql = """
                UPDATE registro_peso
                SET usuario = ?, peso = ?, fecha = ?
                WHERE id = ?
                """;

        try (Connection conexion =
                     ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, registro.getUsuario());
            sentencia.setBigDecimal(2, registro.getPeso());
            sentencia.setDate(
                    3, Date.valueOf(registro.getFecha()));
            sentencia.setInt(4, registro.getId());

            return sentencia.executeUpdate() == 1;
        }
    }

    // ELIMINAR
    public boolean eliminar(int id) throws SQLException {

        String sql =
                "DELETE FROM registro_peso WHERE id = ?";

        try (Connection conexion =
                     ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            return sentencia.executeUpdate() == 1;
        }
    }
}
