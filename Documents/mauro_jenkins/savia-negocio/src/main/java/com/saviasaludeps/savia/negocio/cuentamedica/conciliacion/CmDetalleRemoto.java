/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rpalacic
 */
public interface CmDetalleRemoto {
    
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
    List<CmDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmDetalle consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmDetalle obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmDetalle obj) throws Exception;
    
    /**
     * Permite actualizar el valor pendiente actual de un detalle
     * @param obj
     * @throws Exception 
     */
    void actualizarValorPendienteActual(CmDetalle obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmDetalle eliminar(int id) throws Exception;

    /**
     * Metodo consulta los detalles dada una factura(ParametroConsulta1 factura,ParametroConsulta2 todos sin paginar,
     * ParametroConsulta3 valor pendiente actual mayor que cero )
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmDetalle> consultarListaDetallesPorFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que cuanta las cantidad de detalles dada una factura
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaDetallesPorFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * permite obtener lista detalles con cast corto
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmDetalle> consultarListaDetallesPorFacturaCastCorto(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener el valor total de los detalles pertenecientes a una factura.
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    BigDecimal totalizarValorFacturadoDetallesPorFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener el total de valor glosado de los detalles que pertenecen a una factura
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    BigDecimal totalizarValorGlosadoDetallesPorFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener la cantidad de detalles multifactura
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaDetallesMultiFactura(ParamConsulta paramConsulta) throws Exception;
    /**
     * Permite obtener detalles multifactura: getParametroConsulta1() : idfacturas,getParametroConsulta2()(null detalles paginados, != null todos detalles)
     * getParametroConsulta3() : detalles con valores pendientes mayores a cero, getParametroConsulta4(): (!= null detalles especificos, null omite condicion)
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmDetalle> consultarListaDetallesMultiFactura(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Permite consultar la informacion de valor de los detalles que pertenecesa una factura.
     * @param paramConsulta: getParamConsulta1() : id factura, getParamConsulta2() : null  = paginado, !null = todos los detalles,
     *        getParamConsulta5() : null (valoresDefecto), 1(valores auditoria)
     * @return
     * @throws Exception 
     */
     List<CmDetalle> consultarDetallesPorFacturaSoloValores(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite consultar un detalle que no tenga ni concepto o autorizacion para ser procesado en el cierre
      * @param paramConsulta
      * @return getParametroConsulta1() : idfacturas string eg: {1,2,3}
      * @throws Exception 
      */
     List<CmDetalle> consultarListaDetallesMultiFacturaFaltoInsumosParaAuditar(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite actualizar atributos de glosado como casos aplica
      * @param obj
      * @throws Exception 
      */
     void actualizarExistenciaGlosado(CmDetalle obj) throws Exception;

     /**
      * Permite consultar informacion de detalles con historico respuestaDetalle
      * @param paramConsulta
      * @return
      * @throws Exception 
      */
     List<CmDetalle> consultarListaDetallesPorFacturaHistorico(ParamConsulta paramConsulta) throws Exception;
     
     /**
      * Permite obtener detalles que pertenecen a una lista de facturas. con cast detalle corto.
      * @param idsFacturas
      * @return
      * @throws Exception 
      */
     List<CmDetalle> consultarTodosDetallesMultiFactura(String idsFacturas) throws Exception;

     /**
      * Permite obtener todos los detalles dada una factura
      * @param idFactura
      * @return
      * @throws Exception 
      */
     List<CmDetalle> consultarTodosDetallesPorFactura(int idFactura) throws Exception;

     /**
      * permte consular por tipo y numero de documento
      * @param paramConsulta
      * @return
      * @throws Exception 
      */
     List<CmDetalle> consultarPorDocumento(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite consultar factuas por autorizacion
     * @param paramConsulta
      * @return
      * @throws Exception 
      */
     List<CmDetalle> consultarPorAutorizacion(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite consultar autoriazaciones
     * @param paramConsulta
      * @return
      * @throws Exception 
      */
     int cantidadConsultarPorAutorizacion(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite consultar la cantidad de items
      * @param paramConsulta
      * @return
      * @throws Exception 
      */
     int cantidadConsultarPorDocumento(ParamConsulta paramConsulta) throws Exception;

     /**
      * Permite obtener detalles que poseen autorizaciones de un estado especifico
      * @param idFacturas
     * @param tiposAutorizacion
      * @return
      * @throws Exception 
      */
     List<CmDetalle> consultarPorEstadoAutorizacion(String idFacturas,String tiposAutorizacion) throws Exception;

     /**
      * Permite actualizar el campo de no recaudo pago compartido
      * @param idsCmDetalle: String idCmdetalles : 1, 2,3
      * @param marcacion: true,false
      * @throws Exception 
      */
     void actualizarCopagoNoEfectivo(String idsCmDetalle, boolean marcacion) throws Exception;

     /**
      * Permite actualizar campo recobro
      * @param idsCmDetalle
      * @param hayRecobro
      * @throws Exception 
      */
     void actualizarRecobro(String idsCmDetalle, boolean hayRecobro) throws Exception;
     
     /**
     * Permite obtener informacion tecnologia 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Map<String, String> consultarInfoProcedimientosMiPres(ParamConsulta paramConsulta) throws Exception; 
    
    /**
     * Consulta de informacion insumos mipres
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Map<String, String> consultarInfoInsumosMiPres(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta de cantidad de registros CmDetalle en una lista para Consultar Afiliado 360
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaConsultaAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros CmDetalle para Consultar Afiliado 360
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmDetalle> consultarListaConsultaAfiliado(ParamConsulta paramConsulta) throws Exception;
    
}
