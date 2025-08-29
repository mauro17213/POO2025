package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import java.util.List;

/**
 *
 * @author Chass
 */
public interface CntjEstadoProcesoDocumentoRemoto {
    
     /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 03/03/2025
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 03/03/2025
     * @param paramConsulta
     * @return List<CntjEstadoProcesoDocumento>
     * @throws Exception 
     */
    List<CntjEstadoProcesoDocumento> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 03/03/2025
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjEstadoProcesoDocumento objeto) throws Exception;

    /**
     * Consultar registro por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 03/03/2025
     * @throws Exception 
     */
    CntjEstadoProcesoDocumento consultar(int id) throws Exception;
    
    /**
     * eliminar registro
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 03/03/2025
     * @throws Exception 
     */
    CntjEstadoProcesoDocumento eliminar(int id) throws Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 03/03/2025
     * @throws Exception 
     */
    List<CntjEstadoProcesoDocumento> listaDocumentoEstado(int idestado) throws java.lang.Exception;
    
     /**
     * Consultar registros
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 03/03/2025
     * @throws Exception 
     */
    List<CntjEstadoProcesoDocumento> listaDocumentoEstadoGenerados(int idestado) throws java.lang.Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 03/03/2025
     * @throws Exception 
     */
    List<CntjEstadoProcesoDocumento> listaDocumentoEstadoAdjuntos(int idestado) throws java.lang.Exception;
    
     /**
     * Consultar registros
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 03/03/2025
     * @throws Exception 
     */
    List<CntjEstadoProcesoDocumento> listaDocumentoEstadoDigitalizado(int idestado) throws java.lang.Exception;
    
}
