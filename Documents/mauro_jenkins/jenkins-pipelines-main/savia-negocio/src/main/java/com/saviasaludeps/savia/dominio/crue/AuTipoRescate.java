package com.saviasaludeps.savia.dominio.crue;

import java.util.Arrays;

/**
 *
 * @author jdlopez
 */
public enum AuTipoRescate {
    GESTION_RIESGO(1, "Gestión del riesgo"),
    GESTION_CAPITA(2, "Gestión capita");

    private final int id;
    private final String nombre;

    private AuTipoRescate(int id, String nombre) {
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
        return Arrays.stream(AuTipoRescate.values())
                .filter(tipo -> tipo.getId() == id)
                .findFirst()
                .map(AuTipoRescate::getNombre)
                .orElse(null);
    }

}
