
package com.softpyme.app;

import com.softpyme.dao.RegistroPesoDAO;
import com.softpyme.modelo.RegistroPeso;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner ENTRADA =
            new Scanner(System.in);

    private static final RegistroPesoDAO DAO =
            new RegistroPesoDAO();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");

            try {
                switch (opcion) {
                    case 1 -> insertar();
                    case 2 -> consultarTodos();
                    case 3 -> consultarPorId();
                    case 4 -> actualizar();
                    case 5 -> eliminar();
                    case 0 -> System.out.println(
                            "Programa finalizado.");
                    default -> System.out.println(
                            "Opción no válida.");
                }
            } catch (SQLException error) {
                System.err.println(
                        "Error de base de datos: "
                                + error.getMessage());
            } catch (IllegalArgumentException error) {
                System.err.println(
                        "Dato inválido: "
                                + error.getMessage());
            }

            System.out.println();

        } while (opcion != 0);

        ENTRADA.close();
    }

    private static void mostrarMenu() {
        System.out.println("================================");
        System.out.println("       SOFTPYME - MI PESO");
        System.out.println("================================");
        System.out.println("1. Registrar peso");
        System.out.println("2. Consultar todos los registros");
        System.out.println("3. Consultar por ID");
        System.out.println("4. Actualizar registro");
        System.out.println("5. Eliminar registro");
        System.out.println("0. Salir");
    }

    private static void insertar() throws SQLException {
        String usuario = leerTexto("Nombre del usuario: ");
        BigDecimal peso = leerPeso();
        LocalDate fecha = leerFecha();

        RegistroPeso registro =
                new RegistroPeso(usuario, peso, fecha);

        if (DAO.insertar(registro)) {
            System.out.println(
                    "Registro insertado correctamente.");
        }
    }

    private static void consultarTodos()
            throws SQLException {

        List<RegistroPeso> registros = DAO.consultarTodos();

        if (registros.isEmpty()) {
            System.out.println(
                    "No hay registros disponibles.");
            return;
        }

        registros.forEach(System.out::println);
    }

    private static void consultarPorId()
            throws SQLException {

        int id = leerEntero("ID del registro: ");
        validarId(id);

        RegistroPeso registro = DAO.consultarPorId(id);

        if (registro == null) {
            System.out.println(
                    "No se encontró el registro.");
        } else {
            System.out.println(registro);
        }
    }

    private static void actualizar()
            throws SQLException {

        int id = leerEntero("ID que deseas actualizar: ");
        validarId(id);

        RegistroPeso existente = DAO.consultarPorId(id);

        if (existente == null) {
            System.out.println(
                    "No se encontró el registro.");
            return;
        }

        String usuario = leerTexto("Nuevo nombre: ");
        BigDecimal peso = leerPeso();
        LocalDate fecha = leerFecha();

        RegistroPeso actualizado =
                new RegistroPeso(id, usuario, peso, fecha);

        if (DAO.actualizar(actualizado)) {
            System.out.println(
                    "Registro actualizado correctamente.");
        } else {
            System.out.println(
                    "No fue posible actualizar el registro.");
        }
    }

    private static void eliminar()
            throws SQLException {

        int id = leerEntero("ID que deseas eliminar: ");
        validarId(id);

        RegistroPeso registro = DAO.consultarPorId(id);

        if (registro == null) {
            System.out.println(
                    "No se encontró el registro.");
            return;
        }

        System.out.println(registro);
        System.out.print(
                "¿Confirmas la eliminación? (S/N): ");

        String confirmacion =
                ENTRADA.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            if (DAO.eliminar(id)) {
                System.out.println(
                        "Registro eliminado correctamente.");
            }
        } else {
            System.out.println(
                    "Operación cancelada.");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String valor = ENTRADA.nextLine().trim();

        if (valor.isBlank() || valor.length() > 100) {
            throw new IllegalArgumentException(
                    "El texto debe tener entre 1 y 100 caracteres.");
        }

        return valor;
    }

    private static BigDecimal leerPeso() {
        System.out.print("Peso en kilogramos: ");

        try {
            BigDecimal peso = new BigDecimal(
                    ENTRADA.nextLine().trim());

            if (peso.compareTo(BigDecimal.ONE) < 0
                    || peso.compareTo(
                            new BigDecimal("500")) > 0) {
                throw new IllegalArgumentException(
                        "El peso debe estar entre 1 y 500 kg.");
            }

            return peso;
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(
                    "Escribe un peso numérico válido.");
        }
    }

    private static LocalDate leerFecha() {
        System.out.print("Fecha (AAAA-MM-DD): ");

        try {
            return LocalDate.parse(
                    ENTRADA.nextLine().trim());
        } catch (DateTimeParseException error) {
            throw new IllegalArgumentException(
                    "Usa el formato AAAA-MM-DD.");
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);

        try {
            return Integer.parseInt(
                    ENTRADA.nextLine().trim());
        } catch (NumberFormatException error) {
            return -1;
        }
    }

    private static void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser un entero positivo.");
        }
    }
}
