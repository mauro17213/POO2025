/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.EmpresaBean;
import com.saviasaludeps.savia.dominio.administracion.Empresa;

/**
 *
 * @author raul-palacios
 */
public interface EmpresaServicioIface {
    
    void Accion(EmpresaBean bean);

    public Empresa objetoConverter(Empresa per);
    
    /**
     * Cargar lista de Regiones
     * @param bean 
     */
    void cargaInicial(EmpresaBean bean);
    
    void listarIps(EmpresaBean bean);
    
}
