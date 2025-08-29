package com.saviasaludeps.savia.web.recobro.servicio;

import com.saviasaludeps.savia.web.recobro.bean.RecobroGrupoBean;

/**
 *
 * @author Stiven Giraldo
 */
public interface RecobroGrupoServicioIface {
    
    /**
     * Método de acciones central
     * @author Stiven Giraldo
     * @creacion 17/03/2025
     * @param bean 
     */
    void Accion(RecobroGrupoBean bean);  
    /**
     * Método para realizar carga inicial de información
     * @author Stiven Giraldo
     * @creacion 17/03/2025
     * @param bean 
     */
    void cargaInicial(RecobroGrupoBean bean);
    
    /**
     * Metodo para listar los usuarios para el lazy
     * @param bean 
     */
    void listarUsuarios(RecobroGrupoBean bean);
    
}
