package com.saviasaludeps.savia.negocio.financiera;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.financiera.FinCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface FinCargaRemoto {

       /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<FinCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    FinCarga consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(FinCarga obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(FinCarga obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    FinCarga eliminar(int id) throws Exception;
   
    /**
     * actualiza estado carga
     * @param obj
     * @throws Exception 
     */
    void actualizarEstado(FinCarga obj) throws Exception;
    
    /**
     * actualiza los fallidos , exitosos y fecha fin de carga
     * @param obj
     * @throws Exception 
     */
    void actualizarNumeroEjecucion(FinCarga obj) throws Exception;
   
    /**
     * Permite actualizar tabla carga 
     * @param obj
     * @throws Exception 
     */
    void actualizarRespuestaExiste(FinCarga obj) throws Exception;
    
    
    /**
     * permite consultar los prestadores de la carga
     * @param documento
     * @return
     * @throws Exception 
     */
     CntPrestador consultarPrestadorCarga(String documento) throws Exception;
     
     /**
      * actualizar fecha fin para eventos de error
      * @param obj
      * @throws Exception 
      */
     void actualizarFechaFin(FinCarga obj) throws Exception;
}
