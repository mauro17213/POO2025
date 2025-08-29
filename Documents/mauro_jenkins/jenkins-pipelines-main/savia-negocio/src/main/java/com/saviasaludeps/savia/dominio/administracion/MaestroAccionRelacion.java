/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class MaestroAccionRelacion extends Auditoria {
    
    private Integer id;
    private MaestroAccion maestroAccionId;
    private Maestro maestroId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MaestroAccion getMaestroAccionId() {
        return maestroAccionId;
    }

    public void setMaestroAccionId(MaestroAccion maestroAccionId) {
        this.maestroAccionId = maestroAccionId;
    }

    public Maestro getMaestroId() {
        return maestroId;
    }

    public void setMaestroId(Maestro maestroId) {
        this.maestroId = maestroId;
    }
    
}
