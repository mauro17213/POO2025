package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.web.informe.bean.InformeGeneradoBean;

public interface InformeGeneradoServicioIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(InformeGeneradoBean bean);

    /**
     * Método de carga inicial
     *
     * @param bean
     */
    void cargaInicial(InformeGeneradoBean bean);

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void guardarDescargado(InformeGeneradoBean bean);

}
