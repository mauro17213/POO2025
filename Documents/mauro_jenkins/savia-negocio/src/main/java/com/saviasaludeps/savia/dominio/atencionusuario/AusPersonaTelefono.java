/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class AusPersonaTelefono extends Auditoria {

     public static final int ACCION_INSERTAR = 0;
    public static final int ACCION_BORRAR = 1;
    public static final int ACCION_NINGUNO= 2;
    
    private Integer id;
    private AusPersona ausPersona;
    private String numero;
    private String observacion;
    private int pos;
    private int accion = ACCION_INSERTAR;
    
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AusPersona getAusPersona() {
        return ausPersona;
    }

    public void setAusPersona(AusPersona ausPersona) {
        this.ausPersona = ausPersona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "AusPersonaTelefono{" + "id=" + id + ", ausPersona=" + ausPersona + ", numero=" + numero + ", observacion=" + observacion + ", pos=" + pos + ", accion=" + accion + '}';
    }
}
