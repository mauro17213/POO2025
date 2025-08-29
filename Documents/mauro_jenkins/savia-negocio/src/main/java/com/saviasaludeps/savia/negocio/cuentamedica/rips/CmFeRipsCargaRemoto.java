package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaContenido;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeRipsFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;
import java.util.Map;

public interface CmFeRipsCargaRemoto {

    /**
     * Insertar Carga.
     *
     * @param obj CmRipsCarga
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmFeRipsCarga obj) throws Exception;

    /**
     * Actualizar carga solo cambia el estado
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizar(CmFeRipsCarga obj) throws Exception;

   
    /**
     * Eliminar una carga
     *
     * @param id de CmRipsCarga
     * @return 
     * @throws Exception
     */
    CmFeRipsCarga eliminar(int id) throws Exception;

    /**
     * Consultar una carga
     *
     * @param id de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    CmFeRipsCarga consultar(int id) throws Exception;

    /**
     * Consultar cantidad de registros para la lista de Cargas
     *
     * @param paramConsulta de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de cargas
     *
     * @param paramConsulta de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    List<CmFeRipsCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Actualiza estado de carga
     * @param obj
     * @throws Exception 
     */
    void actualizarEstado(CmFeRipsCarga obj) throws Exception;
    
    /**
     * Actualizar observacion 
     * @param obj
     * @throws Exception 
     */
    public void actualizarObservacion(CmFeRipsCarga obj) throws Exception ;
        
    /**
     * se realiza cambio de gestion
     * @param obj
     * @throws Exception 
     */
   void actualizarAtributosDevolucion(CmFeRipsCarga obj) throws Exception ;
   
   /**
    * permite actualizar los valores de la devolucion de la carga
    * @param obj
    * @throws Exception 
    */
   void actualizarDescripcionDevolucion(CmFeRipsCarga obj) throws Exception;
   
   /**
    * Permite modificar los requisitos manuales
    * @param obj
    * @throws Exception 
    */
   void actualizarRequisitosManuales(CmFeRipsCarga obj) throws Exception;
   
      /**
     * Permite actualizar atributo soporte fe
     * @param obj
     * @throws Exception 
     */
    void actualizarAtributoSoporteFe(CmFeRipsCarga obj) throws Exception;
   
   
   /**
    * consulta carga factura
    * @param paramConsulta: getParametroConsulta1(): factura numero, 
    *  getParametroConsulta2(): documento prestador, getParametroConsulta3(): id sede,  
    *  getParametroConsulta4(): id regimen, getParametroConsulta5(): capita periodo, 
    *  getParametroConsulta6(): tipo, getParametroConsulta7(): numero nota
    * @return
    * @throws java.lang.Exception 
    */
    List<CmFeRipsCarga> consultarExistenciaCargaFactura(ParamConsulta paramConsulta) throws java.lang.Exception ;
    
    /**
     * Permite modificar la fecha de inicio para que el proceso de reintento por error se ejecute
     * @param obj
     * @throws Exception 
     */
     void actualizarFechaInicio(CmFeRipsCarga obj) throws Exception;
     
     /**
      * permite insertar contenido de lso archivos
      * @param obj
      * @return
      * @throws java.lang.Exception 
      */
     int insertarCmFeCargaContenido(CmFeRipsCargaContenido obj) throws Exception; 
     
    /**
     * Permite insertar un fe rip factura
     * @param factura
     * @return
     * @throws java.lang.Exception 
     */
     int insertarFeRipsCmFacturas(CmFeRipsFactura factura) throws java.lang.Exception;

    /**
     * Permite modificar la informacion de rechazo de una carga
     * @param obj
     * @throws Exception 
     */
     void actualizarRechazo(CmFeRipsCarga obj) throws Exception;

     /**
      * actualiza carga periodo que se acaba de asociar
      * @param idCarga
      * @param idCargaPeriodo
      * @throws Exception 
      */
     void actualizarCargaPeriodo(int idCarga, int idCargaPeriodo) throws Exception;

     /**
      * permite obtenercargas segun nit, factura y estados
     * @param nitFacturaUnicos
      * @param estado
     * @return 
      * @throws Exception 
      */
     Map<String, CmFeRipsCarga> consultarCargasSegunNitFactura(List<String> nitFacturaUnicos, String estado) throws Exception;

     /**
      * permite actualizar el numero de soportes que tiene  una carga
      * @param obj
      * @throws Exception 
      */
     void actualizarNumeroSoportes(CmFeRipsCarga obj) throws Exception;

     /**
      * actualiza el radicador asignado de una carga
      * @param idCargas: concatenacion id de carga ej: 1,2
      * @param idRadicador:  radicador para asignar
      * @throws Exception 
      */
     void actualizarRadicadorAsignado(String idCargas, int idRadicador) throws Exception;

}
