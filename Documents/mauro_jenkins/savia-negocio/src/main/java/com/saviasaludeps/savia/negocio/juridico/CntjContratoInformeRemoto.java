package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjContratoInforme;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public interface CntjContratoInformeRemoto {
    
    /**
     * Inserta nuevo registro
     * @param objeto
     * @return
     * @throws Exception 
     */
    int insertar(CntjContratoInforme objeto) throws Exception;
    
    /**
     * Actualiza un registro
     * @param objeto
     * @throws Exception 
     */
    void actualizar(CntjContratoInforme objeto) throws Exception;
    
    /**
     * Elimina un registro
     * @param id
     * @return
     * @throws Exception 
     */
    CntjContratoInforme eliminar(int id) throws Exception;

    /**
     * Consultar campo por id
     * @author Stiven Giraldo
     * @param id
     * @return int
     * @creacion 21/11/2024
     * @throws Exception 
     */
    CntjContratoInforme consultar(int id) throws Exception;
    
    /**
     * Consulta todos los informes de un contrato
     * @param idContrato
     * @return
     * @throws Exception 
     */
    List<CntjContratoInforme> consultarTodosPorIdContrato(int idContrato) throws Exception;
}
