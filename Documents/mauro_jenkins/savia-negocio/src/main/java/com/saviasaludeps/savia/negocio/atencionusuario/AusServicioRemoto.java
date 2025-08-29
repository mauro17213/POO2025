/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;


import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author pavacca
 */
@Remote
public interface AusServicioRemoto {
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
    List<AusCasoServicio> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un Adjunto por ID
     * @param id
     * @return (Adjunto) objeto consultado
     * @throws java.lang.Exception 
     */
    AusCasoServicio consultar(int id)throws Exception;
    
    /**
     * Método para crear un Adjunto
     * @param obj (Servicio)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusCasoServicio obj)throws Exception;
    
    /**
     * Método para actualizar la información de  Servicio
     * @param obj (Servicio) Objeto actualizado
     * @throws java.lang.Exception
     */
    void actualizar(AusCasoServicio obj)throws Exception;
    
    /**
     * Método para eliminar un Servicio
     * @param id
     * @return (Servicio) objeto eliminado
     * @throws java.lang.Exception
     */
    AusCasoServicio eliminar(int id)throws Exception;
    
    /**
     * Método para actualizar la información de  Servicio
     * @param obj (Servicio) Objeto actualizado
     * @throws java.lang.Exception
     */
    void actualizarBorrarServicio(AusCasoServicio obj)throws Exception;
}
