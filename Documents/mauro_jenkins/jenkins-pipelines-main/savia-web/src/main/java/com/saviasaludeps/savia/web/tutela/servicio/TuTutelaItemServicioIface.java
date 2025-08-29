package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.web.tutela.bean.TuTutelaItemBean;

public interface TuTutelaItemServicioIface {
    
     /**
     * funcion que se encarga de realizar las funcione
     *
     * @param bean
     */
    void Accion(TuTutelaItemBean bean);

    /**
     * Cargar lista Tutelas
     *
     * @param bean
     */
    void cargaInicial(TuTutelaItemBean bean);

   
}
