/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.util.Arrays;

/**
 *
 * @author jdlopez
 */
public enum PeOperacion {
    SUMA(0, "+"),
    RESTA(1, "-"),
    MULTIPLICACION(2, "*"),
    DIVISION(3, "/"),
    POTENCIA(4, "^");

    private final int id;
    private final String simbolo;

    private PeOperacion(int id, String simbolo) {
        this.id = id;
        this.simbolo = simbolo;
    }

    public int getId() {
        return id;
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    
    public static String getSimboloById(int id) {
        return Arrays.stream(PeOperacion.values())
                .filter(tipo -> tipo.getId() == id)
                .findFirst()
                .map(PeOperacion::getSimbolo)
                .orElse(null);
    }
    
}
