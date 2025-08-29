package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.web.tutela.bean.TuGrupoBean;

public interface TuGrupoServicioIface {
    
    /**
     * Permite realiar acciones de modulo
     * @param bean 
     */
    void Accion(TuGrupoBean bean);
    
    /**
     * Cargar lista de Juzgados
     * @param bean 
     */
    void cargaInial(TuGrupoBean bean);
    
    
}
