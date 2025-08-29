/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuEntregaCargaRemoto {

    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    List<AuEntregaCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;

    AuEntregaCarga consultar(int id) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuEntregaCarga obj) throws Exception;
    
    /**
     * actualiza estado mientras se realiza carga
     * @param entrega
     * @throws Exception 
     */
    void actualizar(AuEntregaCarga entrega) throws Exception;
    
    /**
     * 09-03-2023 pra validar archivos duplicados que aun estan en proceso
     * @param nombre
     * @return
     * @throws Exception 
     */
    List<AuEntregaCarga> consultarArchivoNombre(String nombre) throws Exception;
    
    /**
     * Consulta la siguiente carga disponible
     * @param estado
     * @return
     * @throws Exception 
     */
    AuEntregaCarga consultarSiguienteCarga(int estado) throws Exception;
    
    /**
     * Actualiza el estado de una una carga
     * @param carga
     * @throws Exception 
     */
    void actualizarEstado(AuEntregaCarga obj) throws Exception;
}
