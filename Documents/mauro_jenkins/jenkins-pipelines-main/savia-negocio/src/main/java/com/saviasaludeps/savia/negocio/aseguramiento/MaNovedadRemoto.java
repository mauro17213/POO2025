
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegMaNovedad;


public interface MaNovedadRemoto {
    
    
    /**
     * Consulta un día en particular
     * @param codigo
     * @return
     * @throws Exception 
     */
    public AsegMaNovedad consultarNovedad(String codigo) throws Exception;
}
