package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.web.atencionusuario.bean.AusGrupoBean;

public interface AusGrupoServicioIface {
    
    /**
     * Permite realiar acciones de modulo
     * @param bean 
     */
    void Accion(AusGrupoBean bean);
    
    /**
     * Cargar lista de Juzgados
     * @param bean 
     */
    void cargaInial(AusGrupoBean bean);
    
}
