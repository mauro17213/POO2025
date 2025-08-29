
package com.saviasaludeps.savia.web.reserva.servicio;

import com.saviasaludeps.savia.web.reserva.bean.ReservaTecnicaBean;

/**
 *
 * @author idbohorquez
 */
public interface ReservaTecnicaServicioIface {
    
    /**
     * Método de acciones central
     * @author idbohorquez
     * @creacion 26/07/2023
     * @param bean 
     */
    void Accion(ReservaTecnicaBean bean);
    
    
    /**
     * Método para realizar carga inicial de información
     * @author idbohorquez
     * @creacion 26/07/2023
     * @param bean 
     */
    void cargasInicial(ReservaTecnicaBean bean);
    
}
