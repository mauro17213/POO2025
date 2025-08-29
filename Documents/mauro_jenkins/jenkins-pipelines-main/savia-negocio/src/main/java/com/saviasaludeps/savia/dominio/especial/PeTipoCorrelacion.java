/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

/**
 *
 * @author jdlopez
 * Enumeracion la cual tiene todos los tipos de operacion de una validacion de correlacion
 * de una variable especifica.
 */
public enum PeTipoCorrelacion {
    MAYOR(0,  "Mayor"), //El valor debe ser mayor a la variable de correlacion
    MENOR(1,  "Menor"), //El valor debe ser menor a la variable de correlacion
    COMODIN_REQUERIDO(2,  "Comodin requerido"), //El valor debe ser un comodin si la correlacion tiene como valor un comodin
    IGUALDAD(3,  "Igualdad correlacionada"), //El valor de igualdad es valido si la correlacion tiene un valor estipulado en la validacion
    COMBINACION(4,  "Combinacion correlacionada"); //La validacion revisa si una combinacion de valores esta presente o no y sera valido de acuerdo al atributo match (true -> la combinacion debe estar presente, false -> la combinacion no puede estar presente)
   
    private final Integer id;
    private final String nombre;

    private PeTipoCorrelacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
}
