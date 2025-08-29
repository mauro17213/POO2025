/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Modulo;

/**
 *
 * @author Fabian Coronel
 */
public class PermisosMap {
    
    private String privilegios;
    private Modulo mod;

    public PermisosMap(String privilegios, Modulo mod) {
        this.privilegios = privilegios;
        this.mod = mod;
    }
    
    public PermisosMap() {
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    public Modulo getMod() {
        return mod;
    }

    public void setMod(Modulo mod) {
        this.mod = mod;
    }    
    
}
