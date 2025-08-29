package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjExpedienteResponsable;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjExpedienteResponsableRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 04/04/2025
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 04/04/2025
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CntjExpedienteResponsable> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 04/04/2025
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjExpedienteResponsable objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 04/04/2025
     * @throws Exception 
     */
    CntjExpedienteResponsable consultar(int id) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 04/04/2025
     * @throws Exception 
     */
    void actualizar(CntjExpedienteResponsable objeto) throws Exception;
    
     /**
     * elimar un registro
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 04/04/2025
     * @throws Exception 
     */
    CntjExpedienteResponsable eliminar(int id) throws Exception;
    
    
     /**
     * Consultar ultimo registro responsable
     * @author idbohorquez
     * @param idExpediente
     * @param rol
     * @return 
     * @creacion 04/04/2025
     * @throws Exception 
     */
    CntjExpedienteResponsable ultimoResponsable(int idExpediente, int rol) throws java.lang.Exception;
    
}
