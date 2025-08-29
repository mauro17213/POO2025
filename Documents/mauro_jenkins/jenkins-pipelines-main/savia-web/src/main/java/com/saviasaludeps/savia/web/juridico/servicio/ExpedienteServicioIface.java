package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.web.juridico.bean.ExpedienteBean;

/**
 *
 * @author idbohorquez
 */
public interface ExpedienteServicioIface {
    
    void Accion(ExpedienteBean bean);
    
    void cargaInicial(ExpedienteBean bean);
    
}
