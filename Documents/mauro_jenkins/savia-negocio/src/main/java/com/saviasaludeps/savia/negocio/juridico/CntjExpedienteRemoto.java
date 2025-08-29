package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjExpedienteRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 11/10/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 11/10/2024
     * @param paramConsulta
     * @return List<CntjExpediente>
     * @throws Exception 
     */
    List<CntjExpediente> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 11/10/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadTareas(ParamConsulta paramConsulta) throws java.lang.Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 11/10/2024
     * @param paramConsulta
     * @return List<CntjExpediente>
     * @throws Exception 
     */
    public List<CntjExpediente> consultarListaTareas(ParamConsulta paramConsulta) throws java.lang.Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 11/10/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjExpediente objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param idcampo
     * @return int
     * @creacion 11/10/2024
     * @throws Exception 
     */
    CntjExpediente consultar(int idcampo) throws Exception;
    
    /**
     * Actualizar proceso
     * @author idbohorquez
     * @param objeto
     * @creacion 11/10/2024
     * @throws Exception 
     */
    void actualizar(CntjExpediente objeto) throws Exception;
    
    /**
     * Eliminar registros
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 11/10/2024
     * @throws Exception 
     */
    CntjExpediente eliminar(int id) throws java.lang.Exception;
    
     /**
     * Consulta lista de registros
     * @author idbohorquez
     * @return 
     * @creacion 11/10/2024
     * @throws Exception 
     */
    List<CntjExpediente> listaExpedienteEstadoComite() throws java.lang.Exception;
    
     /**
     * Consulta ultimo numero expediente
     * @author idbohorquez
     * @param anio
     * @return 
     * @creacion 21/04/2025
     * @throws Exception 
     */
    Integer ultimoNumeroExpediente(Integer anio) throws java.lang.Exception;
    
}
