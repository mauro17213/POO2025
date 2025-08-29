/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.util.Arrays;

/**
 *
 * @author jdlopez
 */
public enum PeTipoVariable {
    FECHA(0, "Fecha"),
    ENTERO(1, "Entero"),
    DECIMAL(2, "Decimal"),
    TEXTO(3, "Texto"),
    TEXTO_LARGO(4, "Texto largo"),
    CALCULO(5, "Calculo");

    private final int id;
    private final String nombre;

    private PeTipoVariable(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static String getNombreById(int id) {
        return Arrays.stream(PeTipoVariable.values())
                .filter(tipo -> tipo.getId() == id)
                .findFirst()
                .map(PeTipoVariable::getNombre)
                .orElse(null);
    }

}
