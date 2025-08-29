package com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio;

import com.saviasaludeps.savia.web.cuentamedica.auditoria.bean.CmGrupoBean;

public interface CmGrupoServicioIface {
    
    void Accion(CmGrupoBean bean);
    
    void cargasInicial(CmGrupoBean bean);
    
    void listar(CmGrupoBean bean);
    
    void guardar(CmGrupoBean bean);
    
    void ver(CmGrupoBean bean);
    
    void editar(CmGrupoBean bean);
    
}
