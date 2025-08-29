package com.saviasaludeps.savia.negocio.juridico;

import com.saviasaludeps.savia.dominio.juridico.CntjContratoInformeAdjunto;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public interface CntjContratoInformeAdjuntoRemoto {
    
    /**
     * Inserta nuevo registro
     * @param objeto
     * @return
     * @throws Exception 
     */
    int insertar(CntjContratoInformeAdjunto objeto) throws Exception;
    
    /**
     * Actualiza un registro
     * @param objeto
     * @throws Exception 
     */
    void actualizar(CntjContratoInformeAdjunto objeto) throws Exception;
    
    /**
     * Consulta todos los informes de un contrato
     * @param idInforme
     * @return
     * @throws Exception 
     */
    List<CntjContratoInformeAdjunto> consultarTodosPorIdContrato(int idInforme) throws Exception;
    
}
