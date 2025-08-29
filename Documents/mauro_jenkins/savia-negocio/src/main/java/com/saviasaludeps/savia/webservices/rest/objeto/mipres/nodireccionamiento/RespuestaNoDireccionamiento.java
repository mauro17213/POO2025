/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.nodireccionamiento;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class RespuestaNoDireccionamiento implements Serializable {

    private List<NoDireccionamiento> noDireccionamientos;

    public List<NoDireccionamiento> getNoDireccionamientos() {
        return noDireccionamientos;
    }

    public void setNoDireccionamientos(List<NoDireccionamiento> noDireccionamientos) {
        this.noDireccionamientos = noDireccionamientos;
    }

}
