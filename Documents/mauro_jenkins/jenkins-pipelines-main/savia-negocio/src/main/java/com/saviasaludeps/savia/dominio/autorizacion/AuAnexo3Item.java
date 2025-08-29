/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo3Item extends Auditoria {

    public final static int TIPO_TECNOLOGIA_CUP = 1;
    public final static int TIPO_TECNOLOGIA_CUM = 2;
    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    public final static int TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO = 5;

    //Estados
    public static final int ESTADO_RECHAZO = 0;
    public static final int ESTADO_PENDIENTE_AUDITORIA = 1;
    public static final int ESTADO_APROBADO_AUDITORIA = 2;
    public static final int ESTADO_APROBADO_AUTOMATICO = 3;
    public static final int ESTADO_RECHAZO_AUDITORIA = 4;
    public static final int ESTADO_ANULADO = 5;
    public static final int ESTADO_DEVUELTO_AUDITORIA = 6;
    public final static int ESTADO_SIN_COTIZACION = 7;
    public final static int ESTADO_CON_COTIZACION = 8;
    public static final int ESTADO_ANULADO_AUTORIZACION = 9;
    public final static int ESTADO_RECHAZO_COTIZACION = 10;
    public final static int ESTADO_DIRECCIONADO_AUTOMATICO = 11;
    public final static int ESTADO_DIRECCIONADO = 12;
    public static final int ESTADO_PREAUTORIZADO = 13;
    public static final int ESTADO_AUTORIZADA_PREAUTORIZACION = 14;
    public static final int ESTADO_ANULADO_PREAUTORIZACION = 15;
    public static final int ESTADO_PENDIENTE_SEGUIMIENTO = 16;
    public static final int ESTADO_CON_SEGUIMIENTO = 17;
    public static final int ESTADO_CANCELADO_SEGUIMIENTO = 18;
    //2023-09-27 jyperez nuevo estado item
    public static final int ESTADO_CON_PAGO_ANTICIPADO = 19;
    public static final int ESTADO_NEGADO_AUDITORIA = 20;
    public static final int ESTADO_NO_PRESTADO = 21;
    
    public static final String ESTADO_RECHAZO_STR = "Rechazado";
    public static final String ESTADO_PENDIENTE_AUDITORIA_STR = "Pendiente Auditoría";
    public static final String ESTADO_APROBADO_AUDITORIA_STR = "Aprobado Auditoría";
    public static final String ESTADO_APROBADO_AUTOMATICO_STR = "Aprobado Automático";
    public static final String ESTADO_RECHAZO_AUDITORIA_STR = "Rechazado Auditoría";
    public static final String ESTADO_ANULADO_STR = "Anulado";
    public static final String ESTADO_DEVUELTO_AUDITORIA_STR = "Devuelto por Auditoría";
    public final static String ESTADO_SIN_COTIZACION_STR = "Pendiente de Cotización";
    public final static String ESTADO_CON_COTIZACION_STR = "Con cotización";
    public static final String ESTADO_ANULADO_AUTORIZACION_STR = "Anulado en Autorizacion";
    public final static String ESTADO_RECHAZO_COTIZACION_STR = "Rechazo de Cotización";
    public final static String ESTADO_DIRECCIONADO_AUTOMATICO_STR = "Direccionado automático";
    public final static String ESTADO_DIRECCIONADO_STR = "Direccionado";
    public static final String ESTADO_PREAUTORIZADO_STR = "Preautorizado";
    public static final String ESTADO_AUTORIZADA_PREAUTORIZACION_STR = "Autorizada Preautorización";
    public static final String ESTADO_ANULADO_PREAUTORIZACION_STR = "Anulado en Preautorización";
    public static final String ESTADO_PENDIENTE_SEGUIMIENTO_STR = "Pendiente Seguimiento";
    public static final String ESTADO_CON_SEGUIMIENTO_STR = "Con Seguimiento";
    public static final String ESTADO_CANCELADO_SEGUIMIENTO_STR = "Cancelado Seguimiento";
    //2023-09-27 jyperez nuevo estado item
    public static final String ESTADO_CON_PAGO_ANTICIPADO_STR = "Con Pago Anticipado";
    
    public static final String ESTADO_NEGADO_AUDITORIA_STR = "Negado Auditoría";
    
    public static final String ESTADO_NO_PRESTADO_STR = "No Prestado";

    private Integer id;
    private int estado;
    private int tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maMedicamentoId;
    private String maMedicamentoCodigo;
    private String maMedicamentoValor;
    private int cantidadSolicitada;
    private Integer maServicioSolicitadoId;
    private String maServicioSolicitadoCodigo;
    private String maServicioSolicitadoValor;
    private Integer maeEstadoMotivoItemId;
    private String maeEstadoMotivoItemCodigo;
    private String maeEstadoMotivoItemValor;
    private String estadoJustificacion;
    private Integer maeCausaExternaId;
    private String maeCausaExternaCodigo;
    private String maeCausaExternaValor;
    private Integer maeFinalidadId;
    private String maeFinalidadCodigo;
    private String maeFinalidadValor;
    private Integer maeTipoCatastroficoId;
    private String maeTipoCatastroficoCodigo;
    private String maeTipoCatastroficoValor;
    private String frecuencia;
    private Integer maeViaAdministracionId;
    private String maeViaAdministracionCodigo;
    private String maeViaAdministracionValor;
    private Integer maeAmbitoId;
    private String maeAmbitoCodigo;
    private String maeAmbitoValor;
    private Integer nivel;
    private Integer maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private boolean prequirurgico;
    private Date fechaFormula;
    private BigDecimal dosis;
    private BigDecimal posologia;
    private boolean posfechado;
    private Date fechaPosfechado;
    private boolean posfechadoPrincipal;
    private boolean efectosAdversos;
    private AuAnexo3 auAnexo3Id;
    private AuGrupo auGrupoId;
    private Usuario gnUsuarioId;
    private List<AuAnexo4Item> auAnexo4ItemsList;
    private List<AuRechazoItem> auRechazoItemsList;
    private List<AuAnexo3Historico> auAnexo3HistoricosList;
    private List<AuItemBitacora> auItemBitacorasList;
    private List<AuCotizacionItem> auCotizacionItemsList;

    //Variables auxiliares
    private MaTecnologia tecnologia;
    private MaMedicamento medicamento;
    private MaInsumo insumo;
    private MaPaquete paquete;
    private List<AuSolicitudAdjunto> listaAdjuntosCotizacion;

    private int duracion;
    private int numeroEntregas;
    private List<AuRango> listaRangos;
    private String indicaciones;
    private String tipoFrecuencia;
    private String tipoPago;

    private String auditor;
    private String capitaPGP;
    private String contratosCapita;
    private String programasPGP;
    
    //variables auxiliares
    private BigDecimal valorTecnologia;

    public AuAnexo3Item(int id) {
        this.id = id;
    }

    public AuAnexo3Item() {

    }

    public AuAnexo3Item(int id, int estado, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor) {
        this.id = id;
        this.estado = estado;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public Integer getMaeEstadoMotivoItemId() {
        return maeEstadoMotivoItemId;
    }

    public void setMaeEstadoMotivoItemId(Integer maeEstadoMotivoItemId) {
        this.maeEstadoMotivoItemId = maeEstadoMotivoItemId;
    }

    public String getMaeEstadoMotivoItemCodigo() {
        return maeEstadoMotivoItemCodigo;
    }

    public void setMaeEstadoMotivoItemCodigo(String maeEstadoMotivoItemCodigo) {
        this.maeEstadoMotivoItemCodigo = maeEstadoMotivoItemCodigo;
    }

    public String getMaeEstadoMotivoItemValor() {
        return maeEstadoMotivoItemValor;
    }

    public void setMaeEstadoMotivoItemValor(String maeEstadoMotivoItemValor) {
        this.maeEstadoMotivoItemValor = maeEstadoMotivoItemValor;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
    }

    public Integer getMaeFinalidadId() {
        return maeFinalidadId;
    }

    public void setMaeFinalidadId(Integer maeFinalidadId) {
        this.maeFinalidadId = maeFinalidadId;
    }

    public String getMaeFinalidadCodigo() {
        return maeFinalidadCodigo;
    }

    public void setMaeFinalidadCodigo(String maeFinalidadCodigo) {
        this.maeFinalidadCodigo = maeFinalidadCodigo;
    }

    public String getMaeFinalidadValor() {
        return maeFinalidadValor;
    }

    public void setMaeFinalidadValor(String maeFinalidadValor) {
        this.maeFinalidadValor = maeFinalidadValor;
    }

    public Integer getMaeTipoCatastroficoId() {
        return maeTipoCatastroficoId;
    }

    public void setMaeTipoCatastroficoId(Integer maeTipoCatastroficoId) {
        this.maeTipoCatastroficoId = maeTipoCatastroficoId;
    }

    public String getMaeTipoCatastroficoCodigo() {
        return maeTipoCatastroficoCodigo;
    }

    public void setMaeTipoCatastroficoCodigo(String maeTipoCatastroficoCodigo) {
        this.maeTipoCatastroficoCodigo = maeTipoCatastroficoCodigo;
    }

    public String getMaeTipoCatastroficoValor() {
        return maeTipoCatastroficoValor;
    }

    public void setMaeTipoCatastroficoValor(String maeTipoCatastroficoValor) {
        this.maeTipoCatastroficoValor = maeTipoCatastroficoValor;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getMaeViaAdministracionId() {
        return maeViaAdministracionId;
    }

    public void setMaeViaAdministracionId(Integer maeViaAdministracionId) {
        this.maeViaAdministracionId = maeViaAdministracionId;
    }

    public String getMaeViaAdministracionCodigo() {
        return maeViaAdministracionCodigo;
    }

    public void setMaeViaAdministracionCodigo(String maeViaAdministracionCodigo) {
        this.maeViaAdministracionCodigo = maeViaAdministracionCodigo;
    }

    public String getMaeViaAdministracionValor() {
        return maeViaAdministracionValor;
    }

    public void setMaeViaAdministracionValor(String maeViaAdministracionValor) {
        this.maeViaAdministracionValor = maeViaAdministracionValor;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public boolean getPrequirurgico() {
        return prequirurgico;
    }

    public void setPrequirurgico(boolean prequirurgico) {
        this.prequirurgico = prequirurgico;
    }

    public List<AuAnexo4Item> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Item> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    public List<AuRechazoItem> getAuRechazoItemsList() {
        return auRechazoItemsList;
    }

    public void setAuRechazoItemsList(List<AuRechazoItem> auRechazoItemsList) {
        this.auRechazoItemsList = auRechazoItemsList;
    }

    public AuAnexo3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexo3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    public MaTecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(MaTecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public MaMedicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MaMedicamento medicamento) {
        this.medicamento = medicamento;
    }

    public MaInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(MaInsumo insumo) {
        this.insumo = insumo;
    }

    public MaPaquete getPaquete() {
        return paquete;
    }

    public void setPaquete(MaPaquete paquete) {
        this.paquete = paquete;
    }

    public boolean isPosfechado() {
        return posfechado;
    }

    public void setPosfechado(boolean posfechado) {
        this.posfechado = posfechado;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(int numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public List<AuRango> getListaRangos() {
        return listaRangos;
    }

    public void setListaRangos(List<AuRango> listaRangos) {
        this.listaRangos = listaRangos;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(String tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public List<AuSolicitudAdjunto> getListaAdjuntosCotizacion() {
        return listaAdjuntosCotizacion;
    }

    public void setListaAdjuntosCotizacion(List<AuSolicitudAdjunto> listaAdjuntosCotizacion) {
        this.listaAdjuntosCotizacion = listaAdjuntosCotizacion;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public String getTipoTecnologiaStr() {
        String str;
        switch (this.tipoTecnologia) {
            case TIPO_TECNOLOGIA_CUP:
                str = "Procedimiento";
                break;
            case TIPO_TECNOLOGIA_CUM:
                str = "Medicamento";
                break;
            case TIPO_TECNOLOGIA_INSUMO:
                str = "Insumo";
                break;
            case TIPO_TECNOLOGIA_PAQUETE:
                str = "Paquete";
                break;
            case TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO:
                str = "Agrupador Medicamento";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public String getEstadoStr() {
        String str;
        switch (this.estado) {
            case ESTADO_RECHAZO:
                str = ESTADO_RECHAZO_STR;
                break;
            case ESTADO_PENDIENTE_AUDITORIA:
                str = ESTADO_PENDIENTE_AUDITORIA_STR;
                break;
            case ESTADO_APROBADO_AUDITORIA:
                str = ESTADO_APROBADO_AUDITORIA_STR;
                break;
            case ESTADO_APROBADO_AUTOMATICO:
                str = ESTADO_APROBADO_AUTOMATICO_STR;
                break;
            case ESTADO_RECHAZO_AUDITORIA:
                str = ESTADO_RECHAZO_AUDITORIA_STR;
                break;
            case ESTADO_ANULADO:
                str = ESTADO_ANULADO_STR;
                break;
            case ESTADO_DEVUELTO_AUDITORIA:
                str = ESTADO_DEVUELTO_AUDITORIA_STR;
                break;
            case ESTADO_SIN_COTIZACION:
                str = ESTADO_SIN_COTIZACION_STR;
                break;
            case ESTADO_CON_COTIZACION:
                str = ESTADO_CON_COTIZACION_STR;
                break;
            case ESTADO_ANULADO_AUTORIZACION:
                str = ESTADO_ANULADO_AUTORIZACION_STR;
                break;
            case ESTADO_RECHAZO_COTIZACION:
                str = ESTADO_RECHAZO_COTIZACION_STR;
                break;
            case ESTADO_DIRECCIONADO_AUTOMATICO:
                str = ESTADO_DIRECCIONADO_AUTOMATICO_STR;
                break;
            case ESTADO_DIRECCIONADO:
                str = ESTADO_DIRECCIONADO_STR;
                break;
            case ESTADO_PREAUTORIZADO:
                str = ESTADO_PREAUTORIZADO_STR;
                break;
            case ESTADO_AUTORIZADA_PREAUTORIZACION:
                str = ESTADO_AUTORIZADA_PREAUTORIZACION_STR;
                break;
            case ESTADO_ANULADO_PREAUTORIZACION:
                str = ESTADO_ANULADO_PREAUTORIZACION_STR;
                break;
            case ESTADO_PENDIENTE_SEGUIMIENTO:
                str = ESTADO_PENDIENTE_SEGUIMIENTO_STR;
                break;
            case ESTADO_CON_SEGUIMIENTO:
                str = ESTADO_CON_SEGUIMIENTO_STR;
                break;
            case ESTADO_CANCELADO_SEGUIMIENTO:
                str = ESTADO_CANCELADO_SEGUIMIENTO_STR;
                break;
            case ESTADO_CON_PAGO_ANTICIPADO:
                str = ESTADO_CON_PAGO_ANTICIPADO_STR;
                break;
            case ESTADO_NEGADO_AUDITORIA:
                str = ESTADO_NEGADO_AUDITORIA_STR;
                break;
            case ESTADO_NO_PRESTADO:
                str = ESTADO_NO_PRESTADO_STR;
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public Integer getEstadoInt(String estados) {
        Integer str;
        switch (estados) {
            case ESTADO_RECHAZO_STR:
                str = ESTADO_RECHAZO;
                break;
            case ESTADO_PENDIENTE_AUDITORIA_STR:
                str = ESTADO_PENDIENTE_AUDITORIA;
                break;
            case ESTADO_APROBADO_AUDITORIA_STR:
                str = ESTADO_APROBADO_AUDITORIA;
                break;
            case ESTADO_APROBADO_AUTOMATICO_STR:
                str = ESTADO_APROBADO_AUTOMATICO;
                break;
            case ESTADO_RECHAZO_AUDITORIA_STR:
                str = ESTADO_RECHAZO_AUDITORIA;
                break;
            case ESTADO_ANULADO_STR:
                str = ESTADO_ANULADO;
                break;
            case ESTADO_DEVUELTO_AUDITORIA_STR:
                str = ESTADO_DEVUELTO_AUDITORIA;
                break;
            case ESTADO_SIN_COTIZACION_STR:
                str = ESTADO_SIN_COTIZACION;
                break;
            case ESTADO_CON_COTIZACION_STR:
                str = ESTADO_CON_COTIZACION;
                break;
            case ESTADO_ANULADO_AUTORIZACION_STR:
                str = ESTADO_ANULADO_AUTORIZACION;
                break;
            case ESTADO_RECHAZO_COTIZACION_STR:
                str = ESTADO_RECHAZO_COTIZACION;
                break;
            case ESTADO_DIRECCIONADO_AUTOMATICO_STR:
                str = ESTADO_DIRECCIONADO_AUTOMATICO;
                break;
            case ESTADO_DIRECCIONADO_STR:
                str = ESTADO_DIRECCIONADO;
                break;
            case ESTADO_PREAUTORIZADO_STR:
                str = ESTADO_PREAUTORIZADO;
                break;
            case ESTADO_AUTORIZADA_PREAUTORIZACION_STR:
                str = ESTADO_AUTORIZADA_PREAUTORIZACION;
                break;
            case ESTADO_ANULADO_PREAUTORIZACION_STR:
                str = ESTADO_ANULADO_PREAUTORIZACION;
                break;
            case ESTADO_PENDIENTE_SEGUIMIENTO_STR:
                str = ESTADO_PENDIENTE_SEGUIMIENTO;
                break;
            case ESTADO_CON_SEGUIMIENTO_STR:
                str = ESTADO_CON_SEGUIMIENTO;
                break;
            case ESTADO_CANCELADO_SEGUIMIENTO_STR:
                str = ESTADO_CANCELADO_SEGUIMIENTO;
                break;
            case ESTADO_NEGADO_AUDITORIA_STR:
                str = ESTADO_NEGADO_AUDITORIA;
                break;
            default:
                str = null;
                break;
        }
        return str;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public AuGrupo getAuGrupoId() {
        return auGrupoId;
    }

    public void setAuGrupoId(AuGrupo auGrupoId) {
        this.auGrupoId = auGrupoId;
    }

    public List<AuAnexo3Historico> getAuAnexo3HistoricosList() {
        return auAnexo3HistoricosList;
    }

    public Date getFechaFormula() {
        return fechaFormula;
    }

    public void setFechaFormula(Date fechaFormula) {
        this.fechaFormula = fechaFormula;
    }

    public BigDecimal getDosis() {
        return dosis;
    }

    public void setDosis(BigDecimal dosis) {
        this.dosis = dosis;
    }

    public boolean getEfectosAdversos() {
        return efectosAdversos;
    }

    public void setEfectosAdversos(boolean efectosAdversos) {
        this.efectosAdversos = efectosAdversos;
    }

    public List<AuItemBitacora> getAuItemBitacorasList() {
        return auItemBitacorasList;
    }

    public void setAuItemBitacorasList(List<AuItemBitacora> auItemBitacorasList) {
        this.auItemBitacorasList = auItemBitacorasList;
    }

    public Date getFechaPosfechado() {
        return fechaPosfechado;
    }

    public void setFechaPosfechado(Date fechaPosfechado) {
        this.fechaPosfechado = fechaPosfechado;
    }

    public BigDecimal getPosologia() {
        return posologia;
    }

    public void setPosologia(BigDecimal posologia) {
        this.posologia = posologia;
    }

    public boolean getPosfechadoPrincipal() {
        return posfechadoPrincipal;
    }

    public void setPosfechadoPrincipal(boolean posfechadoPrincipal) {
        this.posfechadoPrincipal = posfechadoPrincipal;
    }

    public void setAuAnexo3HistoricosList(List<AuAnexo3Historico> auAnexo3HistoricosList) {
        this.auAnexo3HistoricosList = auAnexo3HistoricosList;
    }

    public List<AuCotizacionItem> getAuCotizacionItemsList() {
        return auCotizacionItemsList;
    }

    public void setAuCotizacionItemsList(List<AuCotizacionItem> auCotizacionItemsList) {
        this.auCotizacionItemsList = auCotizacionItemsList;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getCapitaPGP() {
        return capitaPGP;
    }

    public void setCapitaPGP(String capitaPGP) {
        this.capitaPGP = capitaPGP;
    }

    public String getContratosCapita() {
        return contratosCapita;
    }

    public void setContratosCapita(String contratosCapita) {
        this.contratosCapita = contratosCapita;
    }

    public String getProgramasPGP() {
        return programasPGP;
    }

    public void setProgramasPGP(String programasPGP) {
        this.programasPGP = programasPGP;
    }

    @Override
    public String toString() {
        return "AuAnexo3Item{" + "id=" + id + ", estado=" + estado + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaValor=" + maTecnologiaValor + ", cantidadSolicitada=" + cantidadSolicitada + ", maServicioSolicitadoId=" + maServicioSolicitadoId + ", maServicioSolicitadoCodigo=" + maServicioSolicitadoCodigo + ", maServicioSolicitadoValor=" + maServicioSolicitadoValor + ", maeCausaExternaId=" + maeCausaExternaId + ", maeCausaExternaCodigo=" + maeCausaExternaCodigo + ", maeCausaExternaValor=" + maeCausaExternaValor + ", maeFinalidadId=" + maeFinalidadId + ", maeFinalidadCodigo=" + maeFinalidadCodigo + ", maeFinalidadValor=" + maeFinalidadValor + ", maeTipoCatastroficoId=" + maeTipoCatastroficoId + ", maeTipoCatastroficoCodigo=" + maeTipoCatastroficoCodigo + ", maeTipoCatastroficoValor=" + maeTipoCatastroficoValor + ", frecuencia=" + frecuencia + ", maeViaAdministracionId=" + maeViaAdministracionId + ", maeViaAdministracionCodigo=" + maeViaAdministracionCodigo + ", maeViaAdministracionValor=" + maeViaAdministracionValor + ", maeAmbitoId=" + maeAmbitoId + ", maeAmbitoCodigo=" + maeAmbitoCodigo + ", maeAmbitoValor=" + maeAmbitoValor + ", nivel=" + nivel + ", maDiagnosticoId=" + maDiagnosticoId + ", maDiagnosticoCodigo=" + maDiagnosticoCodigo + ", maDiagnosticoValor=" + maDiagnosticoValor + ", prequirurgico=" + prequirurgico + ", indicaciones=" + indicaciones + ", tipoFrecuencia=" + tipoFrecuencia + '}';
    }

    public void calculaRango() {
        if (isPosfechado()) {
            if (getListaRangos() == null) {
                setListaRangos(new ArrayList());
            }
            int dur = getDuracion();
            int entregas = getNumeroEntregas();
            int cantidad = getCantidadSolicitada();
            if (dur > 0 && entregas > 0 && cantidad > 0) {
                int cantidadTotal = 0;
                int contador = 1;
                int posicion = 0;
                int cantidadEntrega = cantidad / entregas;
                int numRango = dur / entregas;
                Date fecha = new Date();
                Calendar calendar = Calendar.getInstance();
                while (contador <= entregas) {
                    AuRango rango = new AuRango();
                    rango.setNumero(contador);
                    rango.setRango(numRango);
                    rango.setPosicion(posicion);
                    if (contador == 1) {
                        rango.setFechaLiberacion(fecha);
                    } else {
                        calendar.setTime(fecha);
                        calendar.add(Calendar.DAY_OF_WEEK, numRango);
                        fecha = calendar.getTime();
                        rango.setFechaLiberacion(fecha);
                    }
                    rango.setCantidad(cantidadEntrega);
                    getListaRangos().add(rango);
                    contador++;
                    posicion++;
                    if (contador > entregas) {
                        rango.setCantidad(cantidad - cantidadTotal);
                    } else {
                        cantidadTotal += cantidadEntrega;
                    }
                }
            }
        }
    }
    
    public void recalcularRango(Date fechaCambio) {
        if (isPosfechado()) {
            if (getListaRangos() == null) {
                setListaRangos(new ArrayList());
            }
            int dur = getDuracion();
            int entregas = getNumeroEntregas();
            int cantidad = getCantidadSolicitada();
            if (dur > 0 && entregas > 0 && cantidad > 0) {
                int cantidadTotal = 0;
                int contador = 1;
                int posicion = 0;
                int cantidadEntrega = cantidad / entregas;
                int numRango = dur / entregas;
                Date fechaRecalculo = fechaCambio;
                Calendar calendar = Calendar.getInstance();
                while (contador <= entregas) {
                    AuRango rango = new AuRango();
                    rango.setNumero(contador);
                    rango.setRango(numRango);
                    rango.setPosicion(posicion);
                    if (contador == 1) {
                        rango.setFechaLiberacion(fechaRecalculo);
                    } else {
                        calendar.setTime(fechaRecalculo);
                        calendar.add(Calendar.DAY_OF_WEEK, numRango);
                        fechaRecalculo = calendar.getTime();
                        rango.setFechaLiberacion(fechaRecalculo);
                    }
                    rango.setCantidad(cantidadEntrega);
                    getListaRangos().add(rango);
                    contador++;
                    posicion++;
                    if (contador > entregas) {
                        rango.setCantidad(cantidad - cantidadTotal);
                    } else {
                        cantidadTotal += cantidadEntrega;
                    }
                }
            }
        }
    }
    
    public void recalcularRangoGestionar(Date fechaCambio) {
        if (getListaRangos() == null) {
            setListaRangos(new ArrayList());
        }
        int dur = getDuracion();
        int entregas = getNumeroEntregas();
        int cantidad = getCantidadSolicitada();
        if (dur > 0 && entregas > 0 && cantidad > 0) {
            int cantidadTotal = 0;
            int contador = 1;
            int posicion = 0;
            int cantidadEntrega = cantidad / entregas;
            int numRango = dur / entregas;
            Date fechaRecalculo = fechaCambio;
            Calendar calendar = Calendar.getInstance();
            while (contador <= entregas) {
                AuRango rango = new AuRango();
                rango.setNumero(contador);
                rango.setRango(numRango);
                rango.setPosicion(posicion);
                if (contador == 1) {
                    rango.setFechaLiberacion(fechaRecalculo);
                } else {
                    calendar.setTime(fechaRecalculo);
                    calendar.add(Calendar.DAY_OF_WEEK, numRango);
                    fechaRecalculo = calendar.getTime();
                    rango.setFechaLiberacion(fechaRecalculo);
                }
                rango.setCantidad(cantidadEntrega);
                getListaRangos().add(rango);
                contador++;
                posicion++;
                if (contador > entregas) {
                    rango.setCantidad(cantidad - cantidadTotal);
                } else {
                    cantidadTotal += cantidadEntrega;
                }
            }
        }
    }
    
    public void calculaRangoGestionar() {
      
        if (getListaRangos() == null) {
            setListaRangos(new ArrayList());
        }
        int dur = getDuracion();
        int entregas = getNumeroEntregas();
        int cantidad = getCantidadSolicitada();
        if (dur > 0 && entregas > 0 && cantidad > 0) {
            int cantidadTotal = 0;
            int contador = 1;
            int posicion = 0;
            int cantidadEntrega = cantidad / entregas;
            int numRango = dur / entregas;
            Date fecha = new Date();
            Calendar calendar = Calendar.getInstance();
            while (contador <= entregas) {
                AuRango rango = new AuRango();
                rango.setNumero(contador);
                rango.setRango(numRango);
                rango.setPosicion(posicion);
                if (contador == 1) {
                    rango.setFechaLiberacion(fecha);
                } else {
                    calendar.setTime(fecha);
                    calendar.add(Calendar.DAY_OF_WEEK, numRango);
                    fecha = calendar.getTime();
                    rango.setFechaLiberacion(fecha);
                }
                rango.setCantidad(cantidadEntrega);
                getListaRangos().add(rango);
                contador++;
                posicion++;
                if (contador > entregas) {
                    rango.setCantidad(cantidad - cantidadTotal);
                } else {
                    cantidadTotal += cantidadEntrega;
                }
            }
        }
    }
    
    public void calculaRangoCargaMasiva() {
        if (isPosfechado()) {
            if (getListaRangos() == null) {
                setListaRangos(new ArrayList());
            }
            int dur = getDuracion();
            int entregas = getNumeroEntregas();
            int cantidad = getCantidadSolicitada();
            if (dur > 0 && entregas > 0 && cantidad > 0) {
                int cantidadTotal = 0;
                int contador = 1;
                int cantidadEntrega = cantidad / entregas;
                int numRango = dur / entregas;
                Date fecha = new Date();
                Calendar calendar = Calendar.getInstance();
                while (contador <= entregas) {
                    AuRango rango = new AuRango();
                    rango.setNumero(contador);
                    rango.setRango(numRango);
                    if(entregas == 1){
                        calendar.setTime(fecha);
                        calendar.add(Calendar.DAY_OF_WEEK, dur);
                        fecha = calendar.getTime();
                        rango.setFechaLiberacion(fecha);
                    }else if (contador == 1) {
                        rango.setFechaLiberacion(fecha);
                    } else {
                        calendar.setTime(fecha);
                        calendar.add(Calendar.DAY_OF_WEEK, numRango);
                        fecha = calendar.getTime();
                        rango.setFechaLiberacion(fecha);
                    }
                    rango.setCantidad(cantidadEntrega);
                    getListaRangos().add(rango);
                    contador++;
                    if (contador > entregas) {
                        rango.setCantidad(cantidad - cantidadTotal);
                    } else {
                        cantidadTotal += cantidadEntrega;
                    }
                }
            }
        }
    }
    
    /**
     * @return the valorTecnologia
     */
    public BigDecimal getValorTecnologia() {
        return valorTecnologia;
    }

    /**
     * @param valorTecnologia the valorTecnologia to set
     */
    public void setValorTecnologia(BigDecimal valorTecnologia) {
        this.valorTecnologia = valorTecnologia;
    }
}
