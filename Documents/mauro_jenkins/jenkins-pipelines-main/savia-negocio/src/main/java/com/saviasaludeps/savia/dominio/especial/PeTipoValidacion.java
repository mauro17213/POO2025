/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

/**
 *
 * @author jdlopez
 * Enumeracion la cual tiene todos los tipos de operacion de una validacion
 * de una variable especifica. (Operaciones basicas)
 */
public enum PeTipoValidacion {
    MAYOR_QUE(0,  "Mayor o igual"), //limite inferior
    MENOR_QUE(1,  "Menor o igual"), //limite superior
    IGUAL(2, "Igual"),//Igualdad en caso de que solo tenga validaciones tipo 2, en caso de que tenga mas validaciones se comporta como COMODIN.
    ANTERIORIDAD(3, "Anterioridad"),//uso solo en variables tipo fecha (fechas antes o iguales a partir del ultimo dia del mes actual (MAXIMO) Y la cantidad de meses asignados en el valor (MINIMO))
    ACTUALIDAD(4, "Actualidad"),//uso solo en variables tipo fecha (fechas iguales o anteriores a la fecha del momento (no aplica poner ningun valor))
    CORRELACION(5, "Correlacion");//relacion reciproca de una variable con otra
    
    private final Integer id;
    private final String nombre;

    private PeTipoValidacion(Integer id, String nombre) {
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
