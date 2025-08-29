package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmRipsCargaBean;

public interface CmRipsCargaServicioIface {

    void Accion(CmRipsCargaBean bean);

    void listar(CmRipsCargaBean bean);

    void cargar(CmRipsCargaBean bean);

    void ver(CmRipsCargaBean bean);

    void verListaErrores(CmRipsCargaBean bean);

    void gestionar(CmRipsCargaBean bean);

    void enviarAuditoria(CmRipsCargaBean bean);

    void rechazar(CmRipsCargaBean bean);

    void listarPrestadoresYSedes(CmRipsCargaBean bean);

    /**
     * Método para cargar inicial del Bean
     *
     * @param bean
     */
    void cargaInicial(CmRipsCargaBean bean);

}
