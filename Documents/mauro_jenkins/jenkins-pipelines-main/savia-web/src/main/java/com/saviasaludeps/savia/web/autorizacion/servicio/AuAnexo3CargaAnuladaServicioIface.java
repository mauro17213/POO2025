/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaAnulada;
import com.saviasaludeps.savia.web.autorizacion.bean.AuAnexo3CargaAnuladaBean;



/**
 *
 * @author raul-palacios
 */
public interface AuAnexo3CargaAnuladaServicioIface {

    void Accion(AuAnexo3CargaAnuladaBean bean);
    
    void cargaInicial(AuAnexo3CargaAnuladaBean bean);
    
    AuAnexo3CargaAnulada consultarCarga(AuAnexo3CargaAnuladaBean bean);
    
}
