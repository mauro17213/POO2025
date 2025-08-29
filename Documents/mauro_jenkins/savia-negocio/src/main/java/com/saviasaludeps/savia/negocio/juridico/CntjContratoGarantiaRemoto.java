package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface CntjContratoGarantiaRemoto {
    
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
     * @return List<CntjContratoGarantia>
     * @throws Exception 
     */
    List<CntjContratoGarantia> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Guardar nuevo registro
     * @author idbohorquez
     * @return int
     * @creacion 21/11/2024
     * @param objeto
     * @throws Exception 
     */
    int insertar(CntjContratoGarantia objeto) throws Exception;

    /**
     * Consultar campo por id
     * @author idbohorquez
     * @param id
     * @return int
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoGarantia consultar(int id) throws Exception;
    
    /**
     * Eliminar registro 
     * @author idbohorquez
     * @param idcontrato 
     * @creacion 24/10/2024
     * @throws Exception 
     */
    void eliminarPorcontrato(int idcontrato) throws Exception;
    
    /**
     * Listar registros
     * @author idbohorquez
     * @param idcontrato 
     * @return  
     * @creacion 24/10/2024
     * @throws Exception 
     */
    List<CntjContratoGarantia> garantiasContrato(int idcontrato) throws Exception;
    
    /**
     * Listar registros
     * @author idbohorquez
     * @param id 
     * @return  
     * @creacion 24/10/2024
     * @throws Exception 
     */
    CntjContratoGarantia eliminar(int id) throws Exception;
    
}
