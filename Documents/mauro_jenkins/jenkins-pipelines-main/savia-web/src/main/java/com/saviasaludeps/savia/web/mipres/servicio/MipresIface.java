package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionAuditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.ReportePlanManejo;
import com.saviasaludeps.savia.web.mipres.bean.MipresBean;
import java.util.List;

/**
 *
 * @author bsgomez
 */
public interface MipresIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(MipresBean bean);

    void consultarTutela(MipresBean bean);
    
     void validarCotizacion(MipresBean bean);

    void CargaInicial(MipresBean bean);

    void listarPrestador(MipresBean bean);

    void listarMaMedicamento(MipresBean bean);

    void listarMaInsumo(MipresBean bean);

    void listarMaInsumoMipres(MipresBean bean);

    void listarMaPaqueteMipres(MipresBean bean);

    void listarMaTecnologiaMipres(MipresBean bean);

    void listarMaTecnologia(MipresBean bean);

    void listarPrestadorSede(MipresBean bean);

    void consultarAuditoria(MipresBean bean);

    void consultarCorreoPrestador(MipresBean bean);

    void consultarContratoDetalle(MipresBean bean);

    void consultarContratoDetalleAnulado(MipresBean bean);

    void consultarCorreoAfiliado(MipresBean bean);

    void consultarPrescripcionPorId(MipresBean bean);

    MpPrescripcion consultarPrescripcion(Integer id);

    MpPrescripcionMedicamento consultarMedicamentoSuministro(Integer id);

    MpPrescripcionTecnologia consultarTecnologiaSuministro(Integer id);

    MpPrescripcionInsumo consultarInsumoSuministro(Integer id);

    MpDireccionamiento consultarDireccionamientoS(Integer id);

    MpPrescripcionAuditoria consultarAudirotiaS(Integer id);

    MpPrescripcionAuditoria consultarAudirotiaSis(Integer id, Integer tipo, Integer idTipo);

    void consultarItemsPorId(MipresBean bean);

    void consultarItemsPorIdDir(MipresBean bean);

    void guardarNoDirec(MipresBean bean);

    void sincronizarDireccionamiento(String prescripcion, int id, boolean isSub);

    void guardarDireccionamientoIndividual(MpDireccionamiento objeto, MipresBean bean, int numero);

    void anularDireccionamiento(MipresBean bean, int id, Integer estado);

    void verHistorico(MipresBean bean, int id);

    void actualizarAtencion(Integer id, Integer tipo);

    void actualizarFinAtencion(Integer id, Integer tipo);

    void liberarTecnologia(Integer id, Integer tipo);

    MpPrescripcionDetalle consultarPrescripcionDetalle(Integer tipoTec, Integer id);

    String consultarPrefijo(int id);

    String consultarHorarioDePrescripcion(Integer id);

    MpDireccionamientoEntregado verEntrega(Integer id);

    MpDireccionamientoEntregado verEntregaSumin(Integer id);

    List<MpDireccionamientoEntregado> verEntregaSinDireccionamiento(Integer prescripcion, Integer item, Integer tipo);

    MpDireccionamientoEntregado verEntregaSinDireccionamiento(Integer id);

    MpDireccionamientoEntregado verEntregaD(Integer id);

    List<Integer> verDireccionamientos(Integer idPrescripcion, Integer idItem, Integer tipo);

    MpDireccionamientoEntregado actualizarDireEntrega(Boolean cierre, Integer id);

    MpDireccionamientoEntregado actualizarDireEntregaAnula(Boolean anula, Integer id);

    MpDireccionamientoEntregado ActualizarCompletaUltima(Boolean val, Integer valor, Integer id);

    void guardarSuministro(MpEntregaSuministro suministro);

    Boolean anularSuministro(Integer id);

    MpEntregaSuministro consultarSuministroDeEntrega(Integer id);

    MpEntregaFactura consultarFactura(Integer id);

    void cierreCiloFactura(Integer id,MipresBean bean);

    void afectaPresupuestoMax(boolean afecta, Integer id);

    ReporteDireccionamiento consultarDireccionamientoReporte(Integer id);

    List<ReportePlanManejo> gestionarPlanDeManejo(Integer prescripcion);

    Integer validarCotizacion(Integer prescripcion, Integer item, Integer tipo);

    void registroSolicitudCotizacion(Integer prescripcion, Integer item, Integer tipo, MipresBean bean);

    void anularNoDireccionamiento(MipresBean bean);

    String consultarCntUnionTemp(Integer id);

}
