
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiempo;

/**
 *
 * @author idbohorquez
 */
public interface GatTiempoRemoto {
    
    public int insertar(GatTiempo obj) throws Exception;
    
    public GatTiempo consultar(int id) throws Exception;
    
    public Integer cantidadReposos(String fecha, int idUsuario) throws Exception;
    
    public void actualizarTiempo(GatTiempo obj) throws Exception;
    
    public GatTiempo consultarActivo(int idUsuario) throws Exception;
    
    /**
     * Consulta el total de taquillas en reposo
     * @param idUsuarios
     * @return
     * @throws Exception 
     */
    int consultarTaquillasEnReposo(String idUsuarios) throws Exception;
    
}
