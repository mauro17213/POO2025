/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class RespuestaDireccionamiento implements Serializable {

    private List<Direccionamiento> direccionamientos;

    public List<Direccionamiento> getDireccionamientos() {
        return direccionamientos;
    }

    public void setDireccionamientos(List<Direccionamiento> direccionamientos) {
        this.direccionamientos = direccionamientos;
    }

}
