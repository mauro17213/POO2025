package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmFeRipsCargaBean;

public interface CmFeRipsCargaServicioIface {

    void Accion(CmFeRipsCargaBean bean);

    void listar(CmFeRipsCargaBean bean);

    void cargar(CmFeRipsCargaBean bean);

    void ver(CmFeRipsCargaBean bean);

    void verListaErrores(CmFeRipsCargaBean bean);

    void enviarAuditoria(CmFeRipsCargaBean bean);

    void rechazar(CmFeRipsCargaBean bean);

    void listarPrestadoresYSedes(CmFeRipsCargaBean bean);

    void cargaInicial(CmFeRipsCargaBean bean);
    
    void verGestionar(CmFeRipsCargaBean bean);

}
