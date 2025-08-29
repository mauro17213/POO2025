package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.web.tutela.bean.TuPersonalBean;

public interface TuPersonalServicioIface {
    
     /**
     * funcion que se encarga de realizar las funcione
     *
     * @param bean
     */
    void Accion(TuPersonalBean bean);

    /**
     * Cargar lista Tutelas
     *
     * @param bean
     */
    void cargaInial(TuPersonalBean bean);

   
}
