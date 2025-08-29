
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.web.especial.bean.CargaMasivaGestionBean;

/**
 *
 * @author idbohorquez
 */
public interface CargaMasivaGestionServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CargaMasivaGestionBean bean);
    
    /**
     * Método para cargar inicial del Bean
     * @param bean
     */
    void cargaInicial(CargaMasivaGestionBean bean);
    
}
