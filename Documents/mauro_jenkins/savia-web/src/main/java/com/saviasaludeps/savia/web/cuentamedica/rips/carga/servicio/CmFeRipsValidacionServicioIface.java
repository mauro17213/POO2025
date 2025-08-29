package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmFeRipsValidacionBean;

public interface CmFeRipsValidacionServicioIface {

    void Accion(CmFeRipsValidacionBean bean);
    void cargaInicial(CmFeRipsValidacionBean bean);

}
