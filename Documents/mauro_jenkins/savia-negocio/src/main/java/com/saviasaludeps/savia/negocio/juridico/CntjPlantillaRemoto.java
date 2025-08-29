package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjPlantillaRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 16/07/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 16/07/2024
     * @param paramConsulta
     * @return List<CntjPlantilla>
     * @throws Exception 
     */
    List<CntjPlantilla> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Guardar nuevo proceso
     * @author idbohorquez
     * @return int
     * @creacion 16/07/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjPlantilla objeto) throws Exception;
    
    /**
     * Consultar plantilla por id
     * @author idbohorquez
     * @param idplantilla
     * @return int
     * @creacion 17/07/2024
     * @throws Exception 
     */
    CntjPlantilla consultar(int idplantilla) throws Exception;
    
    /**
     * Actualizar plantilla
     * @author idbohorquez
     * @param objeto
     * @creacion 17/07/2024
     * @throws Exception 
     */
    void actualizar(CntjPlantilla objeto) throws Exception;
    
    /**
     * Eliminar plantilla
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 08/07/2025
     * @throws Exception 
     */
    CntjPlantilla eliminar(int id) throws Exception;
    
    /**
     * Consulta todas las plantillas
     * @author idbohorquez
     * @return 
     * @creacion 23/07/2024
     * @throws Exception 
     */
    List<CntjPlantilla> lista() throws Exception;
    
    /**
     * Consulta plantillas segun estado documento
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 05/03/2025
     * @throws Exception 
     */
    List<CntjPlantilla> listaDocumentoEstadoGenerados(int idestado) throws java.lang.Exception;
    
    /**
     * Consulta plantillas segun estado documento
     * @author idbohorquez
     * @param idestado
     * @return 
     * @creacion 12/08/2025
     * @throws Exception 
     */
    List<CntjPlantilla> listaDocumentoEstadoGeneradosMixtos(int idestado) throws java.lang.Exception;
    
    /**
     * Consulta plantillas segun proceso
     * @author idbohorquez
     * @param idproceso
     * @return 
     * @creacion 07/03/2025
     * @throws Exception 
     */
    List<CntjPlantilla> listaPlantillasProceso(int idproceso) throws java.lang.Exception ;
    
    /**
     * Consulta cantidad de plantillas segun documento
     * @author idbohorquez
     * @param iddocumento
     * @return 
     * @creacion 10/03/2025
     * @throws Exception 
     */
    Integer plantillasDocumento(int iddocumento) throws java.lang.Exception;
    
    /**
     * Consulta cantidad de documentos generados 
     * @author idbohorquez
     * @param idplantilla
     * @return 
     * @creacion 10/03/2025
     * @throws Exception 
     */
    Integer documentosGenerados(int idplantilla) throws java.lang.Exception;
    
    /**
     * inactivar plantillas del documento
     * @author idbohorquez
     * @param objeto 
     * @creacion 10/03/2025
     * @throws Exception 
     */
    void inactivarPlantillasDocumento(CntjPlantilla objeto) throws java.lang.Exception;
    
    /**
     * Consultar id version anterior documento
     * @author idbohorquez
     * @param iddocumento 
     * @return  
     * @creacion 10/03/2025
     * @throws Exception 
     */
    Integer plantillaIdVersionAnterior(int iddocumento) throws java.lang.Exception;
    
}
