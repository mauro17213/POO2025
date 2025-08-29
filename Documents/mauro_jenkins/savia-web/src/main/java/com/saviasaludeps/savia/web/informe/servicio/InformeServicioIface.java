package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.web.informe.bean.InformeBean;

public interface InformeServicioIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(InformeBean bean);

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void cargaInicial(InformeBean bean);

}
