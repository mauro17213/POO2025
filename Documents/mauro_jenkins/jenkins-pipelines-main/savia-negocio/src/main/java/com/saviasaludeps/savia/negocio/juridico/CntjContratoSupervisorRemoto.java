package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjContratoSupervisorRemoto {
    
    /**
     * Consulta de cantidad de registros 
     * @author idbohorquez
     * @creacion 21/11/2024
     * @param paramConsulta
     * @return int
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 21/11/2024
     * @param paramConsulta
     * @return List<CntjContratoSupervisor>
     * @throws Exception 
     */
    List<CntjContratoSupervisor> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 21/11/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjContratoSupervisor objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoSupervisor consultar(int id) throws Exception;
    
    /**
     * Eliminar registros
     * @author idbohorquez
     * @param idcontrato
     * @creacion 21/11/2024
     * @throws Exception 
     */
    void eliminarPorcontrato(int idcontrato) throws Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param idcontrato
     * @return 
     * @creacion 21/11/2024
     * @throws Exception 
     */
    List<CntjContratoSupervisor> supervisoresContrato(int idcontrato) throws Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoSupervisor eliminar(int id) throws Exception ;
    
}
