package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.web.juridico.bean.TareaBean;

/**
 *
 * @author idbohorquez
 */
public interface TareaServicioIface {
    
    public void Accion(TareaBean bean);
    
    void cargaInicial(TareaBean bean);
    
}
