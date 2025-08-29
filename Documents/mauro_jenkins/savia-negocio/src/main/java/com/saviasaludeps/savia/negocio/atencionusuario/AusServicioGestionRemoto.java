/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusServicioGestion;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rpalacic
 */
@Remote
public interface AusServicioGestionRemoto {
    
    /**
     * 
     * @param paramConsulta
     * @param idEstadoServicio
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta, int idEstadoServicio) throws Exception;
    
    /**
     * 
     * @param paramConsulta
     * @param idEstadoServicio
     * @return
     * @throws Exception 
     */
    List<AusCasoServicio> consultarLista(ParamConsulta paramConsulta, int idEstadoServicio) throws Exception;
    
    /**
     * 
     * @param paramConsulta
     * @param idEstadoServicioParaBuscar
     * @param idEstadoServicioNoIncluido
     * @param idEstadoEstudio
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaEstadoAsignado(ParamConsulta paramConsulta, int idEstadoServicioParaBuscar, int idEstadoServicioNoIncluido, int idEstadoEstudio) throws Exception;
    
    /**
     * 
     * @param paramConsulta
     * @param idEstadoServicioParaBuscar
     * @param idEstadoServicioNoIncluido
     * @param idEstadoEstudio
     * @return
     * @throws Exception 
     */
    List<AusCasoServicio> consultarListaEstadoAsignado(ParamConsulta paramConsulta, int idEstadoServicioParaBuscar,  int idEstadoServicioNoIncluido,int idEstadoEstudio ) throws Exception;
    
    /**
     * Método para consultar un Servicio por ID
     * @param id
     * @return (ServicioGestion) objeto consultado
     * @throws java.lang.Exception 
     */
    AusServicioGestion consultar(int id)throws Exception;
    
    /**
     * Método para crear un Servicio
     * @param obj (ServicioGestion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusServicioGestion obj)throws Exception;
    
    /**
     * Método para actualizar la información de  Servicio
     * @param obj (ServicioGestion) Objeto actualizado
     * @throws java.lang.Exception
     */
    void actualizar(AusServicioGestion obj)throws Exception;
    
    /**
     * Método para eliminar un Servicio
     * @param id
     * @return (ServicioGestion) objeto eliminado
     * @throws java.lang.Exception
     */
    AusServicioGestion eliminar(int id)throws Exception;  
}
