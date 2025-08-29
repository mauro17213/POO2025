package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjProcesoDocumentoRemoto {
    
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
     * @return List<CntjProcesoDocumento>
     * @throws Exception 
     */
    List<CntjProcesoDocumento> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 03/03/2025
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjProcesoDocumento objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param idcampo
     * @return int
     * @creacion 03/03/2025
     * @throws Exception 
     */
    CntjProcesoDocumento consultar(int id) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 03/03/2025
     * @throws Exception 
     */
    void actualizar(CntjProcesoDocumento objeto) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 03/03/2025
     * @return List<CntjProcesoDocumento>
     * @throws Exception 
     */
    List<CntjProcesoDocumento> consultarDocumentos() throws java.lang.Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 03/03/2025
     * @return List<CntjProcesoDocumento>
     * @throws Exception 
     */
    List<CntjProcesoDocumento> consultarDocumentosGenerados() throws java.lang.Exception ;
    
     /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 03/03/2025
     * @return List<CntjProcesoDocumento>
     * @throws Exception 
     */
    List<CntjProcesoDocumento> consultarDocumentosGeneradosMixtos() throws java.lang.Exception ;
          
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @param idproceso
     * @creacion 03/03/2025
     * @return List<CntjProcesoDocumento>
     * @throws Exception 
     */
    List<CntjProcesoDocumento> consultarDocumentosProceso(int idproceso) throws java.lang.Exception;
    
}
