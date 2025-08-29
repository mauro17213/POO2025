/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2000Bean;

/**
 *
 * @author AlexanderDiaz
 */
public interface Iss2000ServicioIface {

    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(SelIss2000Bean bean);
    
}