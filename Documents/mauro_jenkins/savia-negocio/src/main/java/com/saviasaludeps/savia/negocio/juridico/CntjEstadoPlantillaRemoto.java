package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoPlantilla;
import java.util.List;

/**
 *
 * @author Chass
 */
public interface CntjEstadoPlantillaRemoto {
    
     /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 10/02/2025
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 10/02/2025
     * @param paramConsulta
     * @return List<CntjEstadoPlantilla>
     * @throws Exception 
     */
    List<CntjEstadoPlantilla> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 10/02/2025
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjEstadoPlantilla objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 10/02/2025
     * @throws Exception 
     */
    CntjEstadoPlantilla consultar(int id) throws Exception;
    
    /**
     * eliminar registro
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 10/02/2025
     * @throws Exception 
     */
    CntjEstadoPlantilla eliminar(int id) throws Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 10/02/2025
     * @throws Exception 
     */
    List<CntjEstadoPlantilla> listaEstadoPlantillas(int idestado) throws java.lang.Exception;
    
}
