package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSeguimiento;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public interface CntjContratoSeguimientoRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @author idbohorquez
     * @creacion 21/11/2024
     * @param paramConsulta
     * @return List<CntjContratoSeguimiento>
     * @throws Exception 
     */
    List<CntjContratoSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 21/11/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjContratoSeguimiento objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoSeguimiento consultar(int id) throws Exception;
    
    /**
     * Consultar registros
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoSeguimiento eliminar(int id) throws Exception ;
    
    /**
     * Consulta todos los seguimientos de un contrato
     * @param idContrato
     * @return
     * @throws Exception 
     */
    List<CntjContratoSeguimiento> consultarTodoPorContrato(int idContrato) throws Exception;
    
    /**
     * Actualiza el seguimiento
     * @param objeto
     * @throws Exception 
     */
    void actualizar(CntjContratoSeguimiento objeto) throws Exception;
    
}
