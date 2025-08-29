package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.mipres.MpAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.nodireccionamiento.NoDireccionamiento;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.JuntaProfesional;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.Prescripcion;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion.PrescripcionRecobrante;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.reporteentrega.ReporteEntrega;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.suministro.Suministro;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yjimeneh
 */
public interface PrescripcionWsRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param prescripciones
     * @param listaAfiliados
     * @param listaProfesionales
     * @param hashTipoDocumentos
     * @param hashTipoDocumentoEmpresas
     * @param hashTipoDocumentoProfesionales
     * @return
     * @throws Exception
     */
    MpConsumoFallo procesarPrescripciones(Prescripcion prescripciones, Map<String, AsegAfiliado> listaAfiliados, Map<String, CntProfesional> listaProfesionales, HashMap<String, Maestro> hashTipoDocumentos, HashMap<String, Maestro> hashTipoDocumentoEmpresas, HashMap<String, Maestro> hashTipoDocumentoProfesionales) throws Exception;

    /**
     * Agregar datos Junta medica a tecnologias de prescripcion
     *
     * @param junta
     * @param listaMedicamentos
     * @param listaTecnologias
     * @param listaInsumos
     * @return
     * @throws Exception
     */
    MpConsumoFallo procesarJunta(JuntaProfesional junta,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param prescripciones
     * @param listaAfiliados
     * @param listaProfesionales
     * @param hashTipoDocumentos
     * @param hashTipoDocumentoEmpresas
     * @param hashTipoDocumentoProfesionales
     * @param prescripcionExiste
     * @return
     * @throws Exception
     */
    MpConsumoFallo procesarPrescripcionesRecobrante(
            PrescripcionRecobrante prescripciones,
            Map<String, AsegAfiliado> listaAfiliados,
            Map<String, CntProfesional> listaProfesionales,
            HashMap<String, Maestro> hashTipoDocumentos,
            HashMap<String, Maestro> hashTipoDocumentoEmpresas,
            HashMap<String, Maestro> hashTipoDocumentoProfesionales,
            boolean prescripcionExiste) throws Exception;

    /**
     * insertar MpAfiliado
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpAfiliados(MpAfiliado obj) throws Exception;

    /**
     * consulta MpPrescripcion por numero Prescripcion
     *
     * @param noPrescripcion
     * @return
     * @throws Exception
     */
    MpPrescripcion consultarMpPrescripcion(String noPrescripcion) throws Exception;

    /**
     * consulta MpPrescripcion por idMpPrescripcion
     *
     * @param idPrescripcion
     * @return
     * @throws Exception
     */
    MpPrescripcionRecobrante consultarMpPrescripcionRec(String idPrescripcion) throws Exception;

    /**
     * consulta lista MpPrescripcionTecnologia por idMpPrescripcion
     *
     * @param idPrescripcion
     * @return
     * @throws Exception
     */
    List<MpPrescripcionTecnologia> consultarListaMpPrescripcionTecnologia(String idPrescripcion) throws Exception;

    /**
     * consulta lista MpPrescripcionMedicamento por idMpPrescripcion
     *
     * @param idPrescripcion
     * @return
     * @throws Exception
     */
    List<MpPrescripcionMedicamento> consultarListaMpPrescripcionMedicamento(String idPrescripcion) throws Exception;

    /**
     * consulta lista MpPrescripcionInsumo por idMpPrescripcion
     *
     * @param idPrescripcion
     * @return
     * @throws Exception
     */
    List<MpPrescripcionInsumo> consultarListaMpPrescripcionInsumo(String idPrescripcion) throws Exception;

    /**
     * insertar MpAfiliado
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcion(MpPrescripcion obj) throws Exception;

    /**
     * insertar MpPrescripcionRecobrante
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcionRecobrante(MpPrescripcionRecobrante obj) throws Exception;

    /**
     * insertar MpPrescripcionTecnologia
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcionTecnologia(MpPrescripcionTecnologia obj) throws Exception;

    /**
     * insertar MpPrescripcionTecnologia
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcionMedicamento(MpPrescripcionMedicamento obj) throws Exception;

    /**
     * insertar MpPrescripcionTecnologia
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcionInsumo(MpPrescripcionInsumo obj) throws Exception;

    /**
     * insertar insertarMpPrescripcionAnulada
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcionAnulada(MpAnuladaPrescripcion obj) throws Exception;

    /**
     * insertar MpPrescripcionHistorico
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarMpPrescripcionHistorico(MpPrescripcionHistorico obj) throws Exception;

    /**
     * Consulta que obtiene la ultima fecha en la que se realizó el consumo de
     * un servicio especifico
     *
     * @param servicio
     * @return
     * @throws Exception
     */
    String obtenerUltimaFechaEjecucionServicio(String servicio) throws Exception;

    /**
     * Consultar ultimo consumo exitoso
     *
     * @param servicio
     * @return
     * @throws Exception
     */
    MpConsumo consultarUltimoConsumo(String servicio) throws Exception;

    /**
     * insertar registro en mp_consumo
     *
     * @param periodo
     * @param servicio
     * @return
     * @throws Exception
     */
    Integer insertarConsumo(Date periodo, String servicio) throws Exception;

    /**
     * insertar registro en mp_consumo
     *
     * @param mpConsumo
     * @return
     * @throws Exception
     */
    Integer insertarMpConsumo(MpConsumo mpConsumo) throws Exception;

    /**
     * actualizar registro en mp_consumo
     *
     * @param estado
     * @param observacion
     * @param registros
     * @param idConsumo
     * @param exitosos
     * @throws Exception
     */
    void actualizarConsumo(int estado, String observacion, int registros, Integer idConsumo, int exitosos) throws Exception;

    /**
     * actualizar registro en mp_consumo
     *
     * @param mpConsumo
     * @throws Exception
     */
    void actualizarMpConsumo(MpConsumo mpConsumo) throws Exception;

    /**
     * Consulta los ultimos 3 consumos
     *
     * @param servicio
     * @param idConsumo
     * @return
     * @throws Exception
     */
    public Integer consultarCantidadFallidos(String servicio, Integer idConsumo) throws Exception;

    /**
     * Consulta registros de mp_consumos por servicio con diferencia entre
     * exitosos y registros
     *
     * @param servicio
     * @return
     * @throws Exception
     */
    public List<MpConsumo> consultarConsumosRegistrosVsExitosos(String servicio) throws Exception;

    /**
     * Consulta el registro del dia mp_consumos por servicio
     *
     * @param servicio
     * @param fecha
     * @return
     * @throws Exception
     */
    public MpConsumo consultarUltimoConsumo(String servicio, String fecha) throws Exception;

    /**
     * Consulta el registro del dia mp_consumos por servicio
     *
     * @param id
     * @return
     * @throws Exception
     */
    public MpConsumo consultarIdConsumo(Integer id) throws Exception;

    /**
     * Obtiene una lista de afiliados
     *
     * @param listaPrescripciones
     * @param hashMaestros
     * @return
     * @throws Exception
     */
    Map<String, AsegAfiliado> consultarListaAfiliados(List<Prescripcion> listaPrescripciones, HashMap<String, Maestro> hashMaestros) throws Exception;

    /**
     * Obtiene una lista de afiliados
     *
     * @param listaPrescripciones
     * @param hashMaestros
     * @return
     * @throws Exception
     */
    Map<String, AsegAfiliado> consultarListaAfiliadosRec(List<PrescripcionRecobrante> listaPrescripciones, HashMap<String, Maestro> hashMaestros) throws Exception;

    /**
     *
     * @param lista
     * @param hashMaestrosTipoDocumento
     * @return
     * @throws Exception
     */
    Map<String, CntPrestador> consultarListaPrestadores(List<Direccionamiento> lista, HashMap<String, Maestro> hashMaestrosTipoDocumento) throws Exception;

    /**
     *
     * @param listaPrescripciones
     * @param hashMaestrosTipoDocumentoProfesionales
     * @return
     * @throws Exception
     */
    Map<String, CntProfesional> consultarListaProfesionales(List<Prescripcion> listaPrescripciones, HashMap<String, Maestro> hashMaestrosTipoDocumentoProfesionales) throws Exception;

    /**
     *
     * @param listaPrescripciones
     * @param hashMaestrosTipoDocumentoProfesionales
     * @return
     * @throws Exception
     */
    Map<String, CntProfesional> consultarListaProfesionalesRec(List<PrescripcionRecobrante> listaPrescripciones, HashMap<String, Maestro> hashMaestrosTipoDocumentoProfesionales) throws Exception;

    /**
     * Consulta una lista de prescripciones por numeros de prescripcion
     *
     * @param listaPrescripciones
     * @return
     * @throws Exception
     */
    List<Prescripcion> consultarListaPrescripciones(List<Prescripcion> listaPrescripciones) throws Exception;

    /**
     * Consulta una lista de prescripciones por periodo y regimen
     *
     * @param periodo
     * @param regimen
     * @return
     * @throws Exception
     */
    List<MpPrescripcion> consultarListaPrescripcionesPorAnular(String periodo, String regimen) throws Exception;

    List<MpPrescripcion> consultarListaPrescripcionesPorAnularEstado(String regimen, int desde, int hasta) throws Exception;
    
    List<MpPrescripcion> consultarListaPrescripcionesPorAnularEstadoFecha(String regimen, int desde, int hasta,Date fecha) throws Exception;

    List<MpPrescripcion> consultarListaPrescripcionesPorAnularEstadoSolicitud(String regimen, int desde, int hasta,Date fecha) throws Exception;

    List<MpAnuladaPrescripcion> consultarListaAnuladas(String regimen, int desde, int hasta) throws Exception;

    MpAnuladaPrescripcion validarExisteSolicitud(Integer id, String prescripcion) throws Exception;

    /**
     * Consulta una lista de prescripciones por una fecha minima de creacion
     *
     * @param fecha
     * @param regimen
     * @return
     * @throws Exception
     */
    List<MpPrescripcion> consultarListaPrescripcionesPorFechaMinima(Date fecha, String regimen) throws Exception;

    /**
     *
     * @param respuesta
     * @param idPrescripcion
     * @param estado
     * @throws Exception
     */
    void actualizaPrescripcionAnulada(String respuesta, Integer idPrescripcion, Integer estado) throws Exception;
    
    void actualizaPrescripcionSolicitud(Integer idSolicitud, Integer estado) throws Exception;

    /**
     * Consulta una lista de prescripciones por numero de prescripcion
     *
     * @param noPrescripcion
     * @return
     * @throws Exception
     */
    List<MpPrescripcion> consultarPrescripcion(String noPrescripcion) throws java.lang.Exception;

    /**
     * Consulta una lista de prescripciones por numeros de prescripcion
     *
     * @param listaPrescripciones
     * @return
     * @throws Exception
     */
    List<Prescripcion> consultarListaPrescripcionesRec(List<PrescripcionRecobrante> listaPrescripciones) throws Exception;

    /**
     * Consulta una lista de tecnologias para un consecutivo y prescripcion
     *
     * @param listaDireccionamientos
     * @return
     * @throws Exception
     */
    Map<String, MpPrescripcionTecnologia> consultarListaTecnologias(List<Direccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionTecnologia> consultarListaTecnologias2(List<Direccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionMedicamento> consultarListaMedicamentos(List<Direccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionMedicamento> consultarListaMedicamentos2(List<Direccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionInsumo> consultarListaInsumos(List<Direccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionInsumo> consultarListaInsumos2(List<Direccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionTecnologia> consultarListaTecnologiasNd(List<NoDireccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionMedicamento> consultarListaMedicamentosNd(List<NoDireccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionInsumo> consultarListaInsumosNd(List<NoDireccionamiento> listaDireccionamientos) throws Exception;

    Map<String, MpPrescripcionTecnologia> consultarListaTecnologiasEnt(List<ReporteEntrega> listaEntregas) throws Exception;

    Map<String, MpPrescripcionMedicamento> consultarListaMedicamentosEnt(List<ReporteEntrega> listaEntregas) throws Exception;

    Map<String, MpPrescripcionInsumo> consultarListaInsumosEnt(List<ReporteEntrega> listaEntregas) throws Exception;

    Map<String, MpPrescripcionTecnologia> consultarListaTecnologiasSum(List<Suministro> listaSuministros) throws Exception;

    Map<String, MpPrescripcionMedicamento> consultarListaMedicamentosSum(List<Suministro> listaSuministros) throws Exception;

    Map<String, MpPrescripcionInsumo> consultarListaInsumosSum(List<Suministro> listaSuministros) throws Exception;

    /**
     *
     * @param listaMedicamentos
     * @return
     * @throws Exception
     */
    Map<String, MpProgramadaEntrega> consultarListaEntregaMedicamentos(List<MpPrescripcionMedicamento> listaMedicamentos) throws Exception;

    Map<String, MpProgramadaEntrega> consultarListaEntregaMedicamentosUnificado(List<Suministro> listaSuministros) throws Exception;

    Map<String, MpProgramadaEntrega> consultarListaEntregaTecnologias(List<MpPrescripcionTecnologia> listaTecnologias) throws Exception;

    Map<String, MpProgramadaEntrega> consultarListaEntregaTecnologiasUnificado(List<Suministro> listaSuministros) throws Exception;

    Map<String, MpProgramadaEntrega> consultarListaEntregaInsumos(List<MpPrescripcionInsumo> listaInsumos) throws Exception;

    Map<String, MpProgramadaEntrega> consultarListaEntregaInsumosUnificado(List<Suministro> listaSuministros) throws Exception;

    List<MpDireccionamiento> consultarDireccionamientosPendientesXEnviar(Integer estado, String regimen) throws Exception;

    List<MpDireccionamiento> consultarAnulamientosPendientesXEnviar(Integer estado, String regimen) throws Exception;

    List<MpEntregaFactura> consultarFacturadosPendientesXEnviar() throws Exception;

    List<MpDireccionamientoEntregado> consultarDireccionamientosPendientesXEnviar(boolean facturaCerrada, String regimen) throws Exception;

    List<MpNoDireccionado> consultarNoDireccionamientosPorEstado(Integer estado, String regimen) throws Exception;

    void actualizaDireccionamiento(String respuesta, Integer id, Integer estado, Integer idTransaccion,
            Integer idDireccionamiento) throws Exception;

    void actualizaDireccionamientoEntregado(String respuesta, Integer id, Integer idTransaccion, Integer idDatoFacturado) throws Exception;

    Map<String, MpDireccionamientoEntregado> consultarListaDatosFacturados(List<String> listaPrescripciones) throws Exception;

    void actualizaNoDireccionamiento(String respuesta, Integer id, Integer estado, Integer idTransaccion,
            Integer idDireccionamiento, String respuestaAnula) throws Exception;

    Map<String, MpDireccionamiento> consultarListaDireccionamientos(List<ReporteEntrega> entregas) throws java.lang.Exception;

    boolean validarEstadoDireccionamiento(int idDireccionamiento) throws Exception;

    boolean validarEstadoSuministro(int id) throws Exception;

    void actualizarSuministroRespuesta(Integer id, Integer estadoMipres, Integer idSuministros) throws Exception;
    
     void actualizarRespuestaSuministro(Integer id, String respuesta) throws Exception;

    void actualizarSuministroRespuestaFallo(Integer id) throws Exception;

    void actualizarSuministroRespuestaAnulacion(Integer id) throws Exception;

    void actualizarEntregaProcesada(Integer id, Integer estado) throws Exception;

    List<MpPrescripcion> consultarPrescripcionesConsultaAnulacion(List<String> listaPrescripcionesAnuladas) throws Exception;

    List<MpNoDireccionado> consultarAnulamientosPendientesXEnviarNoDireccionados(Integer estado, String regimen) throws Exception;
    
    void actualizaItemNoDireccionamiento(int tipoTecnologia, int idItem) throws Exception;
}
