/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.facturacionelectronica;

import com.saviasaludeps.savia.dominio.facturacionelectronica.FeDocumento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jorge perez
 */
public interface FeDocumentoRemoto {
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    FeDocumento consultar(int id) throws Exception;
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
    List<FeDocumento> consultarLista(ParamConsulta paramConsulta) throws Exception;
   
    /**
     * consulta si existe la factura por prestador
     * @param paramConsulta: paramConsulta.getParametroConsulta1()  : prestador nit, 
     * paramConsulta.getParametroConsulta2() : numero factura
     * @return
     * @throws java.lang.Exception 
     */
    FeDocumento consultarFacturaPorPrestador(ParamConsulta paramConsulta) throws java.lang.Exception ;
    
 
}
