package com.saviasaludeps.savia.web.judicial.servicio;

import com.saviasaludeps.savia.web.judicial.bean.GjProcesoBean;

public interface GjProcesoServicioIface {

    /**
     * Cargar lista
     *
     * @param bean
     */
    void cargasInicial(GjProcesoBean bean);

    void Accion(GjProcesoBean bean);

    void listarJuzgados(GjProcesoBean bean);

    void listarClaseDes(GjProcesoBean bean);

    void consultarUbicaciones(GjProcesoBean bean);

    void listarTerceroAfiliado(GjProcesoBean bean);

    void completarTerceroAfiliado(GjProcesoBean bean);

    void listarAbogadoAfiliado(GjProcesoBean bean);

    void completarAbogadoAfiliado(GjProcesoBean bean);

    void consultarRadicado(GjProcesoBean bean);

}
