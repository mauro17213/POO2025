/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.aseguramiento.bean.ValidacionDerechosBean;
import java.util.List;

/**
 *
 * @author José Pérez
 */
public interface ValidacionDerechosServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(ValidacionDerechosBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(ValidacionDerechosBean bean);
    
}
