/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuCargaFijo;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rpalacic
 */
@Remote
public interface TuCargaFijoRemoto {
    
    /**
     * consulta lista de carga fijos
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * consulta lista de carga fijos
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<TuCargaFijo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta  carga fijos por id
     * @param id
     * @return (Adjunto) objeto consultado
     * @throws java.lang.Exception 
     */
    TuCargaFijo consultar(int id)throws Exception;

   /**
    * permite consultar hash de configuraciones 
    * @return 
    */
    HashMap<Integer, String> consultarHash();
    

        
}
