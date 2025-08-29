/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierreSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author ibohorquez
 */
public interface AucCargaCierreSucesoRemoto {
    
    
    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AucCargaCierreSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception;
   
    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucCargaCierreSuceso obj) throws Exception;

    /**
     * Consultar lista de suceso de una carga cierre
     *
     * @param id
     * @return List<AucCargaCierreSuceso>
     * @throws Exception
     */
    List<AucCargaCierreSuceso> consultarListaPorIdCarga(int id) throws Exception;
   
    
}
