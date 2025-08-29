package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import java.util.List;

/**
 *
 * @author Chass
 */
public interface CntjEstadoEjecucionRemoto {
    
     /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 03/02/2025
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 03/02/2025
     * @param paramConsulta
     * @return List<CntjEstadoEjecucion>
     * @throws Exception 
     */
    List<CntjEstadoEjecucion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 03/02/2025
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjEstadoEjecucion objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 03/02/2025
     * @throws Exception 
     */
    CntjEstadoEjecucion consultar(int id) throws Exception;
    
    /**
     * Eliminar registro
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 03/02/2025
     * @throws Exception 
     */
    CntjEstadoEjecucion eliminar(int id) throws Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param idExpediente
     * @return 
     * @creacion 03/02/2025
     * @throws Exception 
     */
    List<CntjEstadoEjecucion> listaEjecucionExpediente(int idExpediente) throws java.lang.Exception;
    
}
