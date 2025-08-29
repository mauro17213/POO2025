package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;

public interface AusPersonaServicioIface {
    
    void Accion(AusPersonaBean bean);
    
    /**
     * Cargar lista de Zonas
     * @param bean
     * @throws Exception 
     */
    void cargaInial(AusPersonaBean bean);
    
    void buscarAfiliado(AusPersonaBean bean);
    
    void consultarPersona(AusPersonaBean bean);
    
}
