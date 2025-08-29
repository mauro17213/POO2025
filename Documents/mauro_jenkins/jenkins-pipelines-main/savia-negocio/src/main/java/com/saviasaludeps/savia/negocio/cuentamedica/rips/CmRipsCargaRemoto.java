package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAcConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAdServiciosAgrupado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAfFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAhHospitalizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAmMedicamento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAnRecienNacido;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAtOtrosServicio;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCtControlObj;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsEstructuraError;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsUsUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsApProcedimiento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAuUrgencia;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaDetalleDTO;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmRipsCargaRemoto {

    /**
     * Insertar Carga.
     *
     * @param obj CmRipsCarga
     * @return valor incremental
     * @throws Exception
     */
    int insertar(CmRipsCarga obj) throws Exception;

    /**
     * Actualizar carga solo cambia el estado
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizar(CmRipsCarga obj) throws Exception;

    /**
     * Actualizar carga pbs o cama fija solamente
     *
     * @param obj CmRipsCarga
     * @throws Exception
     */
    void actualizarPbsCamaFija(CmRipsCarga obj) throws Exception;

    /**
     * Eliminar una carga
     *
     * @param id de CmRipsCarga
     * @throws Exception
     */
    void eliminar(int id) throws Exception;

    /**
     * Consultar una carga
     *
     * @param id de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    CmRipsCarga consultar(int id) throws Exception;

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
    List<CmRipsCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar errores de estructura de una carga
     *
     * @param id de CmRipsCarga
     * @return CmRipsCarga
     * @throws Exception
     */
    CmRipsCarga consultarErroresEstructura(int id) throws Exception;

    /**
     * Consultar cantidad de registros para la lista de Cargas
     *
     * @param paramConsulta de CmRipsCarga
     * @return cantidad de registros de sucesos
     * @throws Exception
     */
    int consultarCantidadListaSucesos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar sucesos
     *
     * @param paramConsulta objeto con filtros
     * @return Lista CmRipsSucesos
     * @throws Exception
     */
    List<CmRipsSuceso> consultarListaSucesos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Eliminar un adjunto
     *
     * @param id del adjunto
     * @return Lista CmRipsSucesos
     * @throws Exception
     */
    CmRipsCargaAnexo eliminarAdjunto(int id) throws Exception;

    int validarPendientes(int idEstado) throws Exception;

    /**
     * Insertar anexos de carga.
     *
     * @param obj del anexo
     * @return valor incremental
     * @throws Exception
     */
    int insertarAdjunto(CmRipsCargaAnexo obj) throws Exception;

    /**
     * Insertar errores.
     *
     * @param obj de estrucutra errores
     * @return valor incremental
     * @throws Exception
     */
    int insertarEstructuraErrores(CmRipsEstructuraError obj) throws Exception;

    /**
     * Consultar detalles facturables.
     *
     * @param id de carga
     * @return carga con detalles
     * @throws Exception
     */
    CmRipsCarga consultarConDetalles(int id) throws Exception;

    /**
     * Consultar cantidad detalles de una factura en carga
     *
     * @param paramConsulta de carga paramConsulta1 Id de Carga
     * @return detalles factura DTO
     * @throws Exception
     */
    int consultarCantidadFacturaDetalles(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar adjutnos de una carga por ID
     *
     * @param id cmRipsCargasId de cmRipsCargaAnexo
     * @return detalles list Anexos
     * @throws Exception
     */
    List<CmRipsCargaAnexo> consultarAnexos(int id) throws Exception;

    /**
     * Consultar detalles de una factura en carga
     *
     * @param paramConsulta de carga paramConsulta1 Id de Carga
     * @return detalles factura DTO
     * @throws Exception
     */
    List<CmRipsCargaDetalleDTO> consultarFacturaDetalles(ParamConsulta paramConsulta) throws Exception;

    int insertarCargaAc(CmRipsAcConsulta obj, int tipo) throws Exception;

    int insertarCargaAd(CmRipsAdServiciosAgrupado obj, int tipo) throws Exception;

    int insertarCargaAf(CmRipsAfFactura obj, int tipo) throws Exception;

    int insertarCargaAh(CmRipsAhHospitalizacion obj, int tipo) throws Exception;

    int insertarCargaAm(CmRipsAmMedicamento obj, int tipo) throws Exception;

    int insertarCargaAn(CmRipsAnRecienNacido obj, int tipo) throws Exception;

    int insertarCargaAt(CmRipsAtOtrosServicio obj, int tipo) throws Exception;

    int insertarCargaCt(CmRipsCtControlObj obj, int tipo) throws Exception;

    int insertarCargaUs(CmRipsUsUsuario obj, int tipo) throws Exception;

    int insertarCargaAp(CmRipsApProcedimiento obj, int tipo) throws Exception;

    int insertarCargaAu(CmRipsAuUrgencia obj, int tipo) throws Exception;

    int insertarCargaEstados(CmRipsCargaEstado obj, int tipo) throws Exception;

    List<CmRipsCarga> consultarSedeEnProceso(int[] estados, Integer id) throws Exception;

    /**
     * Permite consultar cmripscarga por parametros : getParam1(): numero cuenta, getparam2(): numero prestador- Nit
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    CmRipsCarga consultarPorParametros(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite actualizar el estado de un rechazo de carga rip
     * @param obj
     * @throws Exception 
     */
    void actualizarEstadoRechazo(CmRipsCarga obj) throws Exception;

    /**
     * Permite borrar datos de la tabla cntRipsCargaCtControl
     * @param idRipCarga
     * @throws Exception 
     */
    void eliminarCmRipsCargaCtControl(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaUsUsuarios(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAcConsultas(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAdServiciosAgrupados(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAfFacturas(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAhHospitalizaciones(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAuUrgencias(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAmMedicamentos(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAnRecienNacidos(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaApProcedimientos(int idRipCarga) throws Exception;

    void eliminarCmRipsCargaAtOtrosServicios(int idRipCarga) throws Exception;
    
    

    void eliminarCmRipsCtControl(int idRipCarga) throws Exception;

    void eliminarCmRipsUsUsuarios(int idRipCarga) throws Exception;

    void eliminarCmRipsAuUrgencias(int idRipCarga) throws Exception;

    void eliminarCmRipsAcConsultas(int idRipCarga) throws Exception;

    void eliminarCmRipsAdServiciosAgrupados(int idRipCarga) throws Exception;

    void eliminarCmRipsAfFacturas(int idRipCarga) throws Exception;

    void eliminarCmRipsAhHospitalizaciones(int idRipCarga) throws Exception;

    void eliminarCmRipsAmMedicamentos(int idRipCarga) throws Exception;

    void eliminarCmRipsAnRecienNacidos(int idRipCarga) throws Exception;

    void eliminarCmRipsApProcedimientos(int idRipCarga) throws Exception;

    void eliminarCmRipsAtOtrosServicios(int idRipCarga) throws Exception;

    /**
     * Permite consultar la cantidad contratos asociados a prestadores id
     * @param paramConsulta: getParamConsulta1(): prestador sede id, getParamConsulta2(): maeModalidadContratoId(Vigente, no vigente),
     * getParamConsulta3(): fecha contrato a buscar entre fechaInicio, fechaFin
     * @return
     * @throws Exception 
     */
    public int consultarCantidadContratos(ParamConsulta paramConsulta) throws Exception;


    /**
     * Permite realizar una consulta de un suceso rips
     * @param paramConsulta: paramConsulta1(): icarga ,paramConsulta2(): numero facturado ,
     * paramConsulta3(): documento,  paramConsulta4(): codigo servicio,  
     * paramConsulta6(): numero autorizacion, paramConsulta7(): nombre regla filtrar
     * @return
     * @throws Exception 
     */
    public CmRipsSuceso consultarSucesoRipsAM(ParamConsulta paramConsulta) throws Exception;
    /**
     * Permite realizar una consulta de un suceso rips
     * @param paramConsulta: paramConsulta1(): icarga ,paramConsulta2(): numero facturado ,
     * paramConsulta3(): documento,  paramConsulta4(): codigo servicio,  p
     * paramConsulta6(): numero autorizacion, paramConsulta7(): nombre regla filtrar
     * @return
     * @throws Exception 
     */
    public CmRipsSuceso consultarSucesoRipsAC(ParamConsulta paramConsulta) throws Exception;
    /**
     * Permite realizar una consulta de un suceso rips
     * @param paramConsulta: paramConsulta1(): icarga ,paramConsulta2(): numero facturado ,
     * paramConsulta3(): documento,  paramConsulta4(): codigo servicio,  
     * paramConsulta6(): numero autorizacion, paramConsulta7(): nombre regla filtrar
     * @return
     * @throws Exception 
     */
    public CmRipsSuceso consultarSucesoRipsAP(ParamConsulta paramConsulta) throws Exception;
    /**
     * Permite realizar una consulta de un suceso rips
     * @param paramConsulta: paramConsulta1(): icarga ,paramConsulta2(): numero facturado ,
     * paramConsulta3(): documento,  paramConsulta4(): codigo servicio,  
     * paramConsulta6(): numero autorizacion, paramConsulta7(): nombre regla filtrar
     * @return
     * @throws Exception 
     */
    public CmRipsSuceso consultarSucesoRipsAT(ParamConsulta paramConsulta) throws Exception;


    
}
