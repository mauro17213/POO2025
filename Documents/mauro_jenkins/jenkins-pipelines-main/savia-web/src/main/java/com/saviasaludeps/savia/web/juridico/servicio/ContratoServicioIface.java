package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.web.juridico.bean.ContratoBean;

/**
 *
 * @author idbohorquez
 */
public interface ContratoServicioIface {
    
    void Accion(ContratoBean bean); 
    
    void cargaInicial(ContratoBean bean);
    
}
