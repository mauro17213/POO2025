/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Oscar Quiroz
 */
public interface AuAnexo3CargaSucesoRemoto {
    
    /**
     * Obtener cantidad de registros
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Obtener lista de registros
     * @param paramConsulta
     * @return
     */
    public List<AuAnexo3CargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception;
}
