
package com.softpyme.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroPeso {

    private int id;
    private String usuario;
    private BigDecimal peso;
    private LocalDate fecha;

    public RegistroPeso(
            String usuario,
            BigDecimal peso,
            LocalDate fecha) {

        this.usuario = usuario;
        this.peso = peso;
        this.fecha = fecha;
    }

    public RegistroPeso(
            int id,
            String usuario,
            BigDecimal peso,
            LocalDate fecha) {

        this.id = id;
        this.usuario = usuario;
        this.peso = peso;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Usuario: " + usuario
                + " | Peso: " + peso + " kg"
                + " | Fecha: " + fecha;
    }
}
