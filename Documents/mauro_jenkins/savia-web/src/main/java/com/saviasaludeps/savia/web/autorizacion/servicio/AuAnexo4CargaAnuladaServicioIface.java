/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4CargaAnulada;
import com.saviasaludeps.savia.web.autorizacion.bean.AuAnexo4CargaAnuladaBean;



/**
 *
 * @author raul-palacios
 */
public interface AuAnexo4CargaAnuladaServicioIface {

    void Accion(AuAnexo4CargaAnuladaBean bean);
    
    void cargaInicial(AuAnexo4CargaAnuladaBean bean);
    
    AuAnexo4CargaAnulada consultarCarga(AuAnexo4CargaAnuladaBean bean);
    
}
