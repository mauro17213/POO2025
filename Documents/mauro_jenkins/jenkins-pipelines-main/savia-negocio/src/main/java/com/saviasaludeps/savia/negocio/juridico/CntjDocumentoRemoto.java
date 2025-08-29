
package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjDocumentoRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 21/02/2025
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 21/02/2025
     * @param paramConsulta
     * @return List<CntjDocumento>
     * @throws Exception 
     */
    List<CntjDocumento> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Insertar nuevo registro
     * @author idbohorquez
     * @param objeto
     * @return 
     * @creacion 21/02/2025
     * @throws Exception 
     */
    int insertar(CntjDocumento objeto) throws java.lang.Exception;
    
    /**
     * Consultar registro
     * @author idbohorquez
     * @param iddocumento
     * @return 
     * @creacion 28/05/2025
     * @throws Exception 
     */
    CntjDocumento consultar(int iddocumento) throws java.lang.Exception;
    
    /**
     * Consultar lista de datos
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 21/02/2025
     * @throws Exception 
     */
    List<CntjDocumento> documentosExpediente(int id) throws java.lang.Exception;
    
    /**
     * Actualizar documento
     * @author idbohorquez
     * @param objeto 
     * @creacion 25/04/2025
     * @throws Exception 
     */
    void actualizar(CntjDocumento objeto) throws java.lang.Exception;
    
    /**
     * Actualizar documento
     * @author idbohorquez
     * @param objeto 
     * @creacion 05/08/2025
     * @throws Exception 
     */
    void restablecerActualizacion(CntjDocumento objeto) throws java.lang.Exception;
    
    /**
     * elimar un registro
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 21/02/2025
     * @throws Exception 
     */
    CntjDocumento eliminar(int id) throws Exception;
    
    /**
     * elimar registros
     * @author idbohorquez
     * @param idExpediente 
     * @creacion 28/03/2025
     * @throws Exception 
     */
    void eliminarPorExpediente(int idExpediente) throws java.lang.Exception;
    
    /**
     * Consulta documento existente
     * @author idbohorquez
     * @param idexpediente
     * @param documentoNombre 
     * @return  
     * @creacion 25/04/2025
     * @throws Exception 
     */
    Integer existeDocumentoExpediente(int idexpediente, String documentoNombre) throws java.lang.Exception;
    
    /**
     * Consulta lista documento con etapa contratacion
     * @author idbohorquez
     * @param id 
     * @return  
     * @creacion 25/04/2025
     * @throws Exception 
     */
    List<CntjDocumento> documentosExportar(int id) throws java.lang.Exception;
    
    /**
     * Consulta documento por expediente y nombre
     * @author idbohorquez
     * @param idexpediente
     * @param documentoNombre 
     * @return  
     * @creacion 12/08/2025
     * @throws Exception 
     */
    CntjDocumento getDocumentoExpedienteNombre(int idexpediente, String documentoNombre) throws java.lang.Exception;
    
}
