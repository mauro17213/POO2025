/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MpPrescripcionRemoto {
    
     /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<MpPrescripcion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Consulta de cantidad de registros en una lista  por afiliado
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros por afiliado
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<MpPrescripcion> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar por id 
     * @param id
     * @return
     * @throws Exception 
     */
    MpPrescripcion consultar(int id) throws Exception;
    
    /**
     * Consultar por id prescripcion
     * @param idPrescripcion
     * @return
     * @throws Exception 
     */
    MpPrescripcionRecobrante consultarRecobrante(int idPrescripcion) throws Exception;
}
