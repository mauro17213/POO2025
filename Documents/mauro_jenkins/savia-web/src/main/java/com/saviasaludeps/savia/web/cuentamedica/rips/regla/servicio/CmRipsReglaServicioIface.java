package com.saviasaludeps.savia.web.cuentamedica.rips.regla.servicio;

import com.saviasaludeps.savia.web.cuentamedica.rips.regla.bean.CmRipsReglaBean;

public interface CmRipsReglaServicioIface {
    
    void Accion(CmRipsReglaBean bean);
    
    void cargasInicial(CmRipsReglaBean bean);
    
    void listar(CmRipsReglaBean bean);
    
    void guardar(CmRipsReglaBean bean);
    
    void ver(CmRipsReglaBean bean);
    
    void editar(CmRipsReglaBean bean);
    
}
