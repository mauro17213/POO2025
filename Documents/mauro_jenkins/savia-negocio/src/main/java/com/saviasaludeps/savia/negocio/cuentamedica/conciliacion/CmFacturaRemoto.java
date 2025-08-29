/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rpalacic
 */
public interface CmFacturaRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CmFactura> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmFactura consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmFactura obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmFactura obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmFactura eliminar(int id) throws Exception;

    /**
     * Metodo que permite actualizar el estado de asignacion de quien esta
     * utilizando la plantilla
     *
     * @param paramConsulta: getParametroConsulta1(): usuario gestiona para asignar, 
     * getParametroConsulta2(): usuario gestiona buscar, getparamem3(): facturas a buscar,
     * @throws Exception
     */
    public void actualizarEstadoGestion(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que trae las facturas que estan bloquedas , de un grupo dado.
     *
     * @param paramConsulta param 1 id de facturas, param 2 usuario actual
     * @return
     * @throws Exception
     */
    public String consultarFacturasBloquedas(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite consultar las Ips de una factura dado un nit o
     * descripcion
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    public Map<String, String> consultarIps(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite obtener facturas
     *
     * @param paramConsulta por id radicado
     * @return
     * @throws Exception
     */
    public List<CmFactura> consultarPorRadicado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite obtener facturas por radicado
     *
     * @param cntPrestadores idPrestador
     * @param numeroFacturado numeroFacturado
     * @return
     * @throws Exception
     */
    List<CmFactura> consultarPorPrestadorYFactura(int idPrestador, String numeroFacturado) throws Exception;

    /**
     * Metodo que permite obtener las facturas en forma de mapa
     *
     * @param paramConsulta por id radicado
     * @return
     * @throws java.lang.Exception
     */
    public Map<String, CmFactura> hashCosultarPorRadicado(ParamConsulta paramConsulta) throws java.lang.Exception;

    /**
     * metodo que permite realizar historial de proceso a una factura
     *
     * @param paramConsulta
     * @throws Exception
     */
    public void actualizarHistorialProcesoFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite obtener facturas
     *
     * @param idCarga por id carga
     * @param estado por estado de factura
     * @return
     * @throws Exception
     */
    public List<CmFactura> consultarPorCargaYEstado(int idCarga, int estado) throws Exception;

    /**
     * Metodo que permite cambiar el estado o tipo de auditoria de una factura.
     *
     * @param paramConsulta (getParametroConsulta1() : idfactura {obligatorio},
     * paramConsulta.getParametroConsulta2() : tipo auditoria{opcional},
     * paramConsulta.getParametroConsulta3() : estado factura{opcional})
     * @throws Exception
     */
    public void actualizarEstadoAuditoria(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite actualizar el usuario gestiona de una factua.
     *
     * @param paramConsulta : paramConsulta.getParametroConsulta2() : usuario
     * gestiona, paramConsulta.getParametroConsulta1() : id factua
     * @throws Exception
     */
    public void actualizarUsuarioGestiona(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite actualizar el usuario y el tipo que gestiona de una
     * factua.
     *
     * @param paramConsulta
     * @throws Exception
     */
    void actualizarUsuarioGestionaAsignado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Permite actualizar la fecha auditoria.
     * @param idsCmFactura
     * @param fechaAuditoria
     * @throws Exception 
     */
    void actualizarFechaAuditoriaCmFactura(String idsCmFactura, Date fechaAuditoria) throws Exception ;

    /**
     * Metodo que permite consultar facturas por atributos
     *
     * @param paramConsulta : getParametroConsulta1 : numerofacturado,
     * getParametroConsulta2 : nit , getparam3(): facturas no anuladas estado(12)
     * @return List
     * @throws Exception
     */
    List<CmFactura> consultarPorAtributos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite verificar si una factura es multiusuario
     *
     * @param paramConsulta paramConsulta.getParametroConsulta1(): idFactura,
     * obligatorio.
     * @return
     * @throws Exception
     */
    boolean verificarFacturaMonousurio(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite validar si existe una factura para la misma carga de ID repetida
     *
     * @param numeroFacturado numero de Factura
     * @param idCarga id de la carga RIPS
     * @return
     * @throws Exception
     */
    int consultarPorNumeroFacturadoYCarga(String numeroFacturado, int idCarga) throws Exception;

    /**
     * permite obtener facturas por numero de facturas, separados por coma(,)
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmFactura> consultarPorNumerosFacturados(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener las facturas en forma de mapa
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Map<String, CmFactura> hashCosultarPorNumeroFacturado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite actualizar usuarios gestiona de manera masiva
     * @param paramConsulta: paramConsulta.getParametroConsulta1() : idfacturas, 
     * paramConsulta.getParametroConsulta2() : idusuario asignar, si es cero se asigna usuario null o libera usuario
     * @throws Exception 
     */
    void actualizarUsuarioGestionFacturasMasiva(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite modificar los estados de un grupo de facturas
     * @param listFacturas
     * @param estado: nuevo estado
     * @throws Exception 
     */
    void cambiarEstadoFacturas(List<CmFactura> listFacturas, int estado) throws Exception;
    
    /**
     * Permite realizar la actualizacion de campos usuario para diferentes momentos
     * @param paramConsulta : paramConsulta.getParametroConsulta1() : estadoFactura{9-ESTADO_FACTURA_AUDITADA_EXITOSA,
     * 2-ESTADO_FACTURA_GLOSADA, 4-ESTADO_FACTURA_CONCILIADA, 5-ESTADO_FACTURA_DEVUELTA}, 
     * paramConsulta.getParametroConsulta2() :id factura, paramConsulta.getParametroConsulta3(): usuario,
     * paramConsulta.getParametroConsulta4(): terminal, paramConsulta.getParametroConsulta5(): fecha hora.
     * @throws java.lang.Exception 
     */
    void actualizarUsuarioSegunMomento(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite consultar todos las facturas pertenecienes a una carga.
     * @param idCarga
     * @return
     * @throws Exception 
     */
    List<CmFactura> consultarPorIdCargaRip(int idCarga) throws Exception;

    /**
     * Permite actualizar los campos 
     * @param paramConsulta getParametroConsulta1(); idFacturas, getParametroConsulta2(): marcado( 0 inactivo, 1 activo),
     * getParametroConsulta3() fecha marcado (date, null) 
     * @throws Exception 
     */
    void actualizarMarcadoGlosaIPS(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite modificar la fecha de devolucion de una factura.
     * @param idsCmFactura
     * @param fechaDevolucion
     * @throws Exception 
     */
    void actualizarFechaDevolucionCmFactura(String idsCmFactura, Date fechaDevolucion) throws Exception;
    /**
     * permite consultar las carga que pertenecen a sistma sistema carga facturacion electronica
     * @param idCarga
     * @return
     * @throws java.lang.Exception 
     */
    List<CmFactura> consultarPorIdFeCargaRip(int idCarga) throws java.lang.Exception ;

}
