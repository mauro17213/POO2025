
package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.web.juridico.bean.ProcesoBean;

/**
 *
 * @author idbohorquez
 */
public interface ProcesoServicioIface {
    
    void Accion(ProcesoBean bean);
    
    void cargasInicial(ProcesoBean bean);
    
}
