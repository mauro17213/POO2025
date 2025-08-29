package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjContratoIndicadorRemoto {
    
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
     * @return List<CntjContratoIndicador>
     * @throws Exception 
     */
    List<CntjContratoIndicador> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 21/11/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjContratoIndicador objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoIndicador consultar(int id) throws Exception;
    
    /**
     * Eliminar registros
     * @author idbohorquez
     * @param idcontrato
     * @creacion 21/11/2024
     * @throws Exception 
     */
    void eliminarPorcontrato(int idcontrato) throws Exception;
    
    /**
     * consultar registros
     * @author idbohorquez
     * @param idcontrato
     * @return 
     * @creacion 21/11/2024
     * @throws Exception 
     */
    List<CntjContratoIndicador> indicadoresContrato(int idcontrato) throws Exception;
    
    /**
     * consultar registros
     * @author idbohorquez
     * @param id
     * @return 
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoIndicador eliminar(int id) throws Exception;
            
}
