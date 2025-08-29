/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteNegacionServicio;
import com.saviasaludeps.savia.web.autorizacion.bean.AuSolicitudBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuSolicitudServicioIface {

    void Accion(AuSolicitudBean bean);

    void cargaInicial(AuSolicitudBean bean);

    void listarAfiliado(AuSolicitudBean bean);

    void listarIps(AuSolicitudBean bean);

    void completarAfiliado(AuSolicitudBean bean);

    void buscarProfesional(AuSolicitudBean bean);

    void borrarAdjunto(int id, AuSolicitudBean bean);

    void listarContratoDetalle(AuSolicitudBean bean);

    List<ReporteAnexo3> generarReporteAnexo3(int id, AuSolicitudBean bean);

    List<ReporteAnexo4> generarReporteAnexo4(int id, AuSolicitudBean bean);

    List<ReporteNegacionServicio> generarReporteRechazo(int id, AuSolicitudBean bean);

    void listarServiocioHabilitacionTecnologia(AuSolicitudBean bean);

    void borrarTecnologia(int id, AuSolicitudBean bean);

    void borrarDiagnostico(int id, AuSolicitudBean bean);

    void borrarTutela(int id, AuSolicitudBean bean);

    String validarTecnologia(String tipoTecnologia, String codigoTecnologia, String tipoDocumento, String numeroDocumento);

    String validarDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento);

    void buscarCotizacion(AuSolicitudBean bean);

    AuRechazo buscarRechazo(int idItem);

    void cambiarEstadoItemACotizacion(AuSolicitudBean bean);

    void cambiarEstadoItemADireccionado(AuSolicitudBean bean);

    void cambiarEstadoItemASeguimiento(AuSolicitudBean bean);

    String validarCantidadTecnologia(String tipoTecnologia, int cantidad, String codigoTecnologia);

    DiaHabil validarFechaHabil(Date fecha);

    void listarCotizacion(AuSolicitudBean bean);

    boolean validarIpsContrato(AuSolicitudBean bean);

    void verificarEstadoItem(AuSolicitudBean bean);

    void consultarPosfechados(AuSolicitudBean bean);
    
    List<AuAnexo3Item> consultarListaPosfechados(AuSolicitudBean bean);

    void verBitacoras(AuSolicitudBean bean);

    AuAnexo4Item verAutorizacionAnterior(AuSolicitudBean bean, int idItem, int tipoItem);
    
    List<AuAnexo4Item> verAutorizaciones(AuSolicitudBean bean, int idItem, int tipoItem);

    boolean validarTecnologiaPosfechada(int idAfiliado, int idTecnologia, AuSolicitudBean bean);

    void verBitacorasAnexo3(AuSolicitudBean bean);

    void guardarBitacora(AuSolicitudBean bean);

    void verRescatesAnexo3(AuSolicitudBean bean);

    void verRescate(AuSolicitudBean bean);

    boolean validarEstadoAfiliado(int maeEstadoAfiliacion, AuSolicitudBean bean);

    AuAnexo3Item consultarTecnologiaId(int id);

    void consultarAdjuntosCotizacion(AuSolicitudBean bean);

    void actualizarProcesoActual(AuSolicitudBean bean);
    
    void validarSugeridoParaBorrar(AuSolicitudBean bean);
    
    /**
     * valida si aplica item direccionado para RIAS
     *
     * @param bean
     * @return true si aplica
     */
    boolean validarItemDireccionado(AuSolicitudBean bean);

    /**
     * valida si ya existe seguimiento.
     *
     * @param bean
     */
    void validarItemSeguimiento(AuSolicitudBean bean);

    /**
     * Permite guardar en db las tutelas
     *
     * @param bean
     * @throws Exception
     */
    void guardarTutelas(AuSolicitudBean bean) throws Exception;

    /**
     * validar tecnoliga por grupos aprueba de usuario y ambito
     *
     * @param bean
     */
    void validarTecnologiaGrupoAprueba(AuSolicitudBean bean);

    /**
     * Consultar tutelas asociadas al afiliado
     *
     * @param bean
     * @throws Exception
     */
    void buscarTutelasAfiliado(AuSolicitudBean bean) throws Exception;
    
    /**
     * Consultar sugeridos asociadas al afiliado
     *
     * @param bean
     */
    void consultarGestionRiegosSugerido(AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param bean
     * */
    void listarGestionRiesgo(AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param bean
     * @return 
     * */
    AuCotizacion consultarCotizacionAnticipo(AuSolicitudBean bean);
    
    /**
     * Consultar cotizacion por anexo 3
     *
     * @param bean
     * @return 
     * */
    AuCotizacion consultarCotizacionAnticipoByAnexo3(AuSolicitudBean bean);
    
    /**
     * Consultar cotizacion por anexo 3
     *
     * @param idAnexo3Item
     * @param bean
     * @return 
     * */
    AuCotizacion consultarCotizacionAnticipoByAnexo3(int idAnexo3Item, AuSolicitudBean bean);
    
     /**
     * Consultar cotizacion por anexo 3
     *
     * @param idAnexo3Item
     * @param bean
     * @return 
     * */
    AuAnexo3Item consultarAnxo3Item(int idAnexo3Item, AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param idAnexo3
     * @param bean
     * @return 
     * */
    AuAnexo3 consultarAnexo3(int idAnexo3, AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param idAfiliado
     * @param idTecnologia
     * @param bean
     * @return 
     * */
    AntAnticipo consultarAnticipoBYafiliado(int idAfiliado, int idTecnologia, AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param idAfiliado
     * @param idTecnologia
     * @param bean
     * @return 
     * */
    List<AntAnticipo> consultarAnticipoBYafiliadoList(int idAfiliado, int idTecnologia, AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param idTecnologia
     * @param bean
     * @return 
     * */
    AntAnticipo consultarAnticipoBYTecnologia(int idTecnologia, AuSolicitudBean bean);
    
    /**
     * Consultar programas asociadas al afiliado
     *
     * @param idTecnologia
     * @param bean
     * @return 
     * */
    List<AntAnticipo> consultarAnticipoBYTecnologiaList(int idTecnologia, AuSolicitudBean bean);
    
    /**
     * Consultar maestro origen , tipo servicio atenciom y ubicacion del paciente
     *
     * @param bean
     * */
    void completarMaestro(AuSolicitudBean bean);
    
    /**
     * Consultar maestro origen , tipo servicio atenciom y ubicacion del paciente
     *
     * @param bean
     * */
    void completarMaestroVersion2335(AuSolicitudBean bean);
}
