/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.seleccion.servicio;

import com.saviasaludeps.savia.web.aseguramiento.seleccion.bean.SelAfiliadoBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface SelAfiliadoIface {
    
    void cargaInicial(SelAfiliadoBean bean);
    
    void listar(SelAfiliadoBean bean);
}
