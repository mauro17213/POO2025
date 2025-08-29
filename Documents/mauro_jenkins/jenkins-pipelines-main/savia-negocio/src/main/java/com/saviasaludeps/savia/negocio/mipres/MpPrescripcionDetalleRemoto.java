package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacion;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpDetalleItem;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpHomologacion;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoIndicacionUnirs;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoPrincipioActivo;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionAuditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

/**
 *
 * @author bsgomez
 */
public interface MpPrescripcionDetalleRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaCotizacion(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaContratoDetalle(ParamConsulta paramConsulta, Integer id, Integer tipo) throws Exception;

    boolean consultarAuditoria(int id, int idItem, int tipoTec) throws Exception;

    String consultarCorreoPrestador(String documento) throws Exception;

    String consultarCorreoPrestadorDir(String documento) throws Exception;

    String consultarCorreoAfiliado(int id) throws Exception;

    String consultarContactoAfiliado(int id) throws Exception;

    void insertarAuditoria(MpPrescripcionAuditoria presAu) throws Exception;

    void insetarSolicitudCot(MpCotizacion cotizacion) throws Exception;

    void insertarNoDireccionamiento(MpNoDireccionado noDireccionado) throws Exception;

    void anularDireccionamiento(int id, Integer estado, String usuario, String terminal, Date fecha) throws Exception;

    void EliminarDireccionamiento(int id) throws Exception;

    void cambiarEstadoPrescripcion(Integer idM, Integer idT, Integer idI, int estado) throws Exception;

    void cambiarEstadoPrescripcionInicial(Integer id) throws Exception;

    void insertarAtencion(String usuario, String terminal, boolean atendido, Date fecha, int tabla, int id) throws Exception;

    MpPrescripcionHistorico inicioHistorico(MpPrescripcionHistorico historico) throws Exception;

    MpDireccionamientoEntregado verEntrega(Integer id) throws Exception;

    MpDireccionamientoEntregado verEntregaSuministroD(Integer id) throws Exception;

    Boolean anularSuministro(Integer id) throws Exception;

    void afectaPresupuestoMax(boolean afecta, Integer id) throws Exception;

    MpDireccionamientoEntregado verEntregaD(Integer id) throws Exception;

//    MpPrescripcion consultarPrescripcion(Integer id) throws Exception;
    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<MpPrescripcionDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Crea un nuevo registro
     *
     * @param direc (Direccionamientos)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertarDireccionamiento(MpDireccionamiento direc) throws Exception;

    String consultarCorreoPrestador(BigInteger valor, String habilitacion) throws Exception;

    Integer consultarTutelas(Integer id) throws Exception;

    void actualizarAtencionItem(Integer id, Integer tipo) throws Exception;

    void actualizarItemCot(Integer id, Integer tipo) throws Exception;

    void actualizarFinAtencion(Integer id, Integer tipo) throws Exception;

    void liberarTecnologia(Integer id, Integer tipo) throws Exception;

    List<MpHomologacion> consultarHomologacion() throws Exception;

    List<Integer> consultarIdDireccionamiento(Integer idPrescripcion, Integer idItem, Integer tipo) throws Exception;

    List<MpDireccionamientoEntregado> consultarEntregaSinDireccionamiento(Integer idPrescripcion, Integer idItem, Integer tipo) throws Exception;

    MpDireccionamientoEntregado consultarEntregaSinDireccionamiento(Integer id) throws Exception;

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpPrescripcion consultarPrescripcion(int id) throws Exception;

    MpPrescripcion consultarPrescripcionCot(int id) throws Exception;

    MpPrescripcionMedicamento consultarPrescripcionMCot(int id) throws Exception;

    MpPrescripcionTecnologia consultarPrescripcionTCot(int id) throws Exception;

    MpPrescripcionInsumo consultarPrescripcionICot(int id) throws Exception;

    MpAnuladaPrescripcion consultarMpPrescripcionAnulada(int id) throws Exception;

    List<MpPrescripcion> consultarPorMpPrescripcionPorDocumento(String doc) throws Exception;

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpDetalleItem consultarPrescripcionM(int id) throws Exception;

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpPrescripcion consultarPrescripcionPorId(int id) throws Exception;

    MpPrescripcion consultarPrescripcionS(Integer id) throws Exception;

    MpPrescripcion traerDatosPrescripcion(Integer id) throws Exception;

    MpPrescripcionMedicamento consultarMedicamentoSuministro(Integer id) throws Exception;

    MpPrescripcionTecnologia consultarTecnologiaSuministro(Integer id) throws Exception;

    MpPrescripcionInsumo consultarInsumoSuministro(Integer id) throws Exception;

    MpDireccionamiento consultarDireccionamientoS(Integer id) throws Exception;

    MpPrescripcionAuditoria consultarAudirotiaS(Integer id) throws Exception;

    MpPrescripcionAuditoria consultarAudirotiaSis(Integer id, Integer tipo, Integer idTipo) throws Exception;

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpDetalleItem consultarPrescripcionT(int id) throws Exception;

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpDetalleItem consultarPrescripcionI(int id) throws Exception;

    MpPrescripcionDetalle consultarPrescripcionDetalle(Integer tipoTec, Integer id) throws Exception;

    /**
     * Consultar por id prescripcion
     *
     * @param idPrescripcion
     * @return
     * @throws Exception
     */
    MpPrescripcionRecobrante consultarRecobrante(int idPrescripcion) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista - Buscador
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaPrestador(ParamConsulta paramConsulta) throws Exception;

    MpPrescripcionTecnologia consultarPorTecnologia(int idItem) throws Exception;

    MpPrescripcionInsumo consultarPorInsumo(int idItem) throws Exception;

    MpDireccionamiento direccionarMedicamento(int idItem) throws Exception;

    MpDireccionamiento direccionarTecnologia(int idItem) throws Exception;

    MpDireccionamiento direccionarInsumo(int idItem) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaPrestadorSede(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntPrestadorSede> consultarListaPrestadorSede(ParamConsulta paramConsulta) throws Exception;

    List<MpCotizacionDetalle> consultarListaCotizaciones(ParamConsulta paramConsulta) throws Exception;

    MpCotizacionDetalle consultarParaCotizacion(Integer id) throws Exception;

    MpCotizacionDetalle consultarCotizacionParaDireccionar(Integer prescripcion, Integer item, Integer tipo) throws Exception;

    AuCotizacion consultarAuCotizacion(Integer id) throws Exception;

    AuCotizacion consultarAuCotizacionVigencia(Integer id) throws Exception;

    List<CntContratoDetalle> consultarListaContratoDetalle(ParamConsulta paramConsulta, Integer id, Integer tipo) throws Exception;

    List<MaMedicamento> consultarListaMaMedicamento(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaMaMedicamento(ParamConsulta paramConsulta) throws Exception;

    MpPrescripcionMedicamento consultarPorMedicamento(int idItem) throws Exception;

    MpPrescripcionAuditoria consultarAuditoriaa(Integer id, Integer idItem, int tipo) throws Exception;

    List<MpPrescripcionAuditoria> consultarAuditoriaaLista(Integer id, Integer idItem, int tipo) throws Exception;

    List<MpMedicamentoPrincipioActivo> consultarPrincipioActivo(int item) throws Exception;

    List<MpMedicamentoIndicacionUnirs> consultarUnirs(int item) throws Exception;

    List<MpPrescripcionMedicamento> consultarPorMpPrescripcionMedicamento(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionMedicamento> consultarPorMpPrescripcionMedicamentoH(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionMedicamento> consultarPorMpPrescripcionMedicamentoPlanManejo(Integer mpPrescripcionId) throws Exception;

    List<MpPrescripcionTecnologia> consultarPorMpPrescripcionTecnologia(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionTecnologia> consultarPorMpPrescripcionTecnologiaH(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionTecnologia> consultarPorMpPrescripcionTecnologiaPlanManejo(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionInsumo> consultarPorMpPrescripcionInsumo(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionInsumo> consultarPorMpPrescripcionInsumoH(int mpPrescripcionId) throws Exception;

    List<MpPrescripcionInsumo> consultarPorMpPrescripcionInsumoPlanManejo(int mpPrescripcionId) throws Exception;

    List<MpProgramadaEntrega> consultarListaEntrega(int prescripcion, int item, int tipotecnologia) throws Exception;

    List<MpPrescripcionProgramada> consultarListaProgramada(int prescripcion, int item, int tipotecnologia) throws Exception;

    List<MpPrescripcionHistorico> consultarListaDeHistorico(int prescripcion, int item) throws Exception;

    List<MpDireccionamientoEntregado> consultarListaDeDireccionamientoEntregado(int id) throws Exception;

    List<MpDireccionamientoEntregado> consultarListaDeDireccionamientoEntregadoPresItem(int idPres, int idItem, int tec) throws Exception;

    List<MpDireccionamiento> consultarListaDeDireccionamiento(int prescripcion, int item, int tipoTecnologia) throws Exception;

    List<MpDireccionamiento> consultarListaDeDireccionamientoDireccionado(int prescripcion, int item, int tipoTecnologia) throws Exception;

    List<Integer> consultarIdParaBorrar(int prescripcion, int item, int tipoTecnologia) throws Exception;

    void borradoLogico(List<Integer> id) throws Exception;

    Boolean consultarDireccionamientoCot(int prescripcion, int item, int tipoTecnologia) throws Exception;

    List<MpNoDireccionado> consultarListaDeNoDireccionamiento(int prescripcion, int item, int tipoTecnologia) throws Exception;

    List<MaInsumo> consultarListaMaInsumo(ParamConsulta paramConsulta) throws Exception;

    List<MaInsumoMipres> consultarListaMaInsumoMipres(ParamConsulta paramConsulta) throws Exception;

    List<MaPaqueteMipres> consultarListaMaPaqueteMipres(ParamConsulta paramConsulta) throws Exception;

    List<MaTecnologiaMipres> consultarListaMaTecnologiaMipres(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaMaInsumo(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaMaInsumoMipres(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaMaPaqueteMipres(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaMaTecnologiaMipres(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaTecnologia(ParamConsulta paramConsulta) throws Exception;

    int consultarCantidadListaPrescripcionPrestador(ParamConsulta paramConsulta, String numeroPrestador) throws Exception;

    List<MpPrescripcion> consultarListaPrescripcionPrestador(ParamConsulta paramConsulta, String numeroPrestador) throws Exception;

    List<MaTecnologia> consultarListaMaTecnologia(ParamConsulta paramConsulta) throws Exception;

    List<SelectItem> consultarPrescripciones(String docAfiliado) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaSedePrestador(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CntPrestadorSede> consultarListaSedePrestador(ParamConsulta paramConsulta) throws Exception;

    MpCodigoInsumo consultarPorCodigoInsumo(String cod) throws Exception;

    int consultarCantidadListaPorCodigoInsumo(ParamConsulta paramConsulta) throws Exception;

    List<MpCodigoInsumo> consultarListaPorCodigoInsumo(ParamConsulta paramConsulta) throws Exception;

    MpDireccionamientoEntregado actualizarCiclo(Boolean cierre, Integer id) throws Exception;

    MpDireccionamientoEntregado actualizarAnula(Boolean anula, Integer id) throws Exception;

    MpDireccionamientoEntregado ActualizarCompletaUltima(Boolean val, Integer valor, Integer id) throws Exception;

    void guardarSuministro(MpEntregaSuministro suministro) throws Exception;

    MpEntregaSuministro consultarSuministroDeEntrega(Integer id) throws Exception;

    MpEntregaFactura consultarFactura(Integer id) throws Exception;

    void cierreCiloFactura(Integer id, String nombre, String ip) throws Exception;

    ReporteDireccionamiento consultarDireccionamientoReporte(Integer id) throws Exception;

    String consultarUbicacionReporte(int id) throws Exception;

    boolean tieneAuditorias(int id) throws Exception;

    boolean tieneDireccionamiento(Integer prescripcion, Integer medicamento, Integer tecnologia, Integer insumo) throws Exception;

    boolean tieneNoDireccionamiento(Integer prescripcion, Integer medicamento, Integer tecnologia, Integer insumo) throws Exception;

    List<MpDireccionamiento> consultarDireccionamientosPorAfiliado(int idAfiliado, ParamConsulta paramConsulta) throws Exception;

    int contarConsultarDireccionamientosPorAfiliado(int idAfiliado, ParamConsulta paramConsulta) throws Exception;

    Integer consultarCotizacion(Integer prescripcion, Integer item, Integer tipo) throws Exception;

    Integer consultarCotizacion2(Integer prescripcion, Integer item, Integer tipo) throws Exception;

    void cambiarEstadoCotizacionMp(Integer id, Integer estado) throws Exception;

    void cambiarEstadoCotizacionMpAu(Integer id, Integer estado) throws Exception;

    void asignarCotizacionASolicitud(Integer solicitud, Integer cotizacion) throws Exception;

    void asignarUsuarioGestion(Integer id, String usuario, String ip) throws Exception;

    void asignarUsuarioRechaza(Integer id, String usuario, String ip) throws Exception;

    void cambiarEstadoItem(Integer item, Integer tipo, int estado) throws Exception;

    boolean anularNoDireccionamiento(Integer idPrescripcion, int tipoTecnologia, int idPrescripcionTecnologia, String usuarioAnula, String terminarAnula, int estado) throws Exception;

    String consultarCntUnionTemp(Integer id);

}
