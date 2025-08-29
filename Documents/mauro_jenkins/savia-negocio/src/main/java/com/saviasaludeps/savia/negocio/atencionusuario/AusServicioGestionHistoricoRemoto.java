/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestionHistorico;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rpalacic
 */
@Remote
public interface AusServicioGestionHistoricoRemoto {
    
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
    List<AusServicioGestionHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un Servicio por ID
     * @param id
     * @return (ServicioGestion) objeto consultado
     * @throws java.lang.Exception 
     */
    AusServicioGestionHistorico consultar(int id)throws Exception;
    
    /**
     * Método para crear un Servicio
     * @param obj (ServicioGestion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusServicioGestionHistorico obj)throws Exception;
    
    /**
     * Método para actualizar la información de  Servicio
     * @param obj (ServicioGestion) Objeto actualizado
     * @throws java.lang.Exception
     */
    void actualizar(AusServicioGestionHistorico obj)throws Exception;
    
    /**
     * Método para eliminar un Servicio
     * @param id
     * @return (ServicioGestion) objeto eliminado
     * @throws java.lang.Exception
     */
    AusServicioGestionHistorico eliminar(int id)throws Exception;  
}
