package com.saviasaludeps.savia.dominio.contratacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

public class DTOCntContrato implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @SerializedName(value = "id")
    @NotNull
    private Integer id;
    @SerializedName(value = "contrato")
    private String contrato;
    @SerializedName(value = "descripcion")
    private String descripcion;
    @SerializedName(value = "activo")
    private boolean activo;
    @SerializedName(value = "maeEstadoContratoId")
    private int maeEstadoContratoId;
    @SerializedName(value = "maeEstadoContratoCodigo")
    private String maeEstadoContratoCodigo;
    @SerializedName(value = "maeEstadoContratoValor")
    private String maeEstadoContratoValor;
    @SerializedName(value = "fechaInicio")
    private Date fechaInicio;
    @SerializedName(value = "fechaFin")
    private Date fechaFin;
    @SerializedName(value = "valor")
    private BigDecimal valor;
    @SerializedName(value = "valorMes")
    private BigDecimal valorMes;

    // Campos usados de cntPrestador
    @SerializedName(value = "idPrestador")
    private int idPrestador;
    @SerializedName(value = "numAfiliados")
    private Integer numAfiliados;
    @SerializedName(value = "condigoMinSalud")
    private String codigoMinSalud;
    @SerializedName(value = "maeClasePrestadorId")
    private int maeClasePrestadorId;
    @SerializedName(value = "maeClasePrestadorCodigo")
    private String maeClasePrestadorCodigo;
    @SerializedName(value = "maeClasePrestadorValor")
    private String maeClasePrestadorValor;
    @SerializedName(value = "maeTipoDocumentoId")
    private Integer maeTipoDocumentoId;
    @SerializedName(value = "maeTipoDocumentoCodigo")
    private String maeTipoDocumentoCodigo;
    @SerializedName(value = "maeTipoDocumentoValor")
    private String maeTipoDocumentoValor;
    @SerializedName(value = "razonSocial")
    private String razonSocial;
    @SerializedName(value = "categoriaPrestador")
    private int categoriaPrestador;
    @SerializedName(value = "nivelAtencion")
    private int nivelAtencion;

    @SerializedName(value = "contratoSedes")
    private List<DTOCntContratoSede> listaContratoSedes;

    @SerializedName(value = "usuarioCrea")
    private String usuarioCrea;
    @SerializedName(value = "terminalCrea")
    private String terminalCrea;
    @SerializedName(value = "fechaHoraCrea")
    private Date fechaHoraCrea;
    @SerializedName(value = "usuarioModifica")
    private String usuarioModifica;
    @SerializedName(value = "terminalModifica")
    private String terminalModifica;
    @SerializedName(value = "fechaHoraModifica")
    private Date fechaHoraModifica;

    //@SerializedName(value = "contratoDetalles")
    //private List<DTOCntContratoDetalle> listaContratoDetalles;
    public DTOCntContrato() {
    }

    public DTOCntContrato(CntContrato per) {
        this.id = per.getId();
        this.contrato = per.getContrato();
        this.descripcion = per.getDescripcion();
        this.activo = per.isActivo();
        this.maeEstadoContratoId = per.getMaeEstadoContratoId();
        this.maeEstadoContratoCodigo = per.getMaeEstadoContratoCodigo();
        this.maeEstadoContratoValor = per.getMaeEstadoContratoValor();
        this.fechaInicio = per.getFechaInicio();
        this.fechaFin = per.getFechaFin();
        this.valor = per.getValor();
        this.valorMes = per.getValorMes();
        this.numAfiliados = per.getNumAfiliados();
        this.idPrestador = per.getCntPrestador().getId();
        this.codigoMinSalud = per.getCntPrestador().getCodigoMinSalud();
        this.maeClasePrestadorId = per.getCntPrestador().getMaeClasePrestador();
        this.maeClasePrestadorCodigo = per.getCntPrestador().getMaeClasePrestadorCodigo();
        this.maeClasePrestadorValor = per.getCntPrestador().getMaeClasePrestadorValor();
        this.maeTipoDocumentoId = per.getCntPrestador().getMaeTipoDocumentoId();
        this.maeTipoDocumentoCodigo = per.getCntPrestador().getMaeTipoDocumentoCodigo();
        this.maeTipoDocumentoValor = per.getCntPrestador().getMaeTipoDocumentoValor();
        this.razonSocial = per.getCntPrestador().getRazonSocial();
        this.categoriaPrestador = per.getCntPrestador().getCategoriaPrestador();
        this.nivelAtencion = per.getCntPrestador().getNivelAtencion();
        this.usuarioCrea = per.getUsuarioCrea();
        this.terminalCrea = per.getTerminalCrea();
        this.fechaHoraCrea = per.getFechaHoraCrea();
        this.usuarioModifica = per.getUsuarioModifica();
        this.terminalModifica = per.getTerminalModifica();
        this.fechaHoraModifica = per.getFechaHoraModifica();

        DTOCntContratoSede cntContratoSede;
        listaContratoSedes = new ArrayList();
        for (CntContratoSede contratoSedes : per.getListaContratoSedes()) {
            cntContratoSede = new DTOCntContratoSede();
            cntContratoSede.setId(contratoSedes.getId());
            cntContratoSede.setMaeModalidadContratoId(contratoSedes.getMaeModalidadContratoId());
            cntContratoSede.setMaeModalidadContratoCodigo(contratoSedes.getMaeModalidadContratoCodigo());
            cntContratoSede.setMaeModalidadContratoValor(contratoSedes.getMaeModalidadContratoValor());
            cntContratoSede.setValorUpcAfiliado(contratoSedes.getValorUpcAfiliado());
            cntContratoSede.setAplicaSubsidiado(contratoSedes.getAplicaSubsidiado());
            cntContratoSede.setAplicaContribuitivo(contratoSedes.getAplicaContribuitivo());
            cntContratoSede.setAplicaGlosaExtemporanea(contratoSedes.getAplicaGlosaExtemporanea());
            cntContratoSede.setAplicaAuditoria(contratoSedes.getAplicaAuditoria());
            cntContratoSede.setAplicaPortabilidad(contratoSedes.getAplicaPortabilidad());
            cntContratoSede.setAplicaRecaudoCopagosIps(contratoSedes.getAplicaRecaudoCopagosIps());
            cntContratoSede.setIdPrestadorSede(contratoSedes.getCntPrestadorSede().getId());
            cntContratoSede.setNombreSede(contratoSedes.getCntPrestadorSede().getNombreSede());
            cntContratoSede.setUsuarioCrea(contratoSedes.getUsuarioCrea());
            cntContratoSede.setTerminalCrea(contratoSedes.getTerminalCrea());
            cntContratoSede.setFechaHoraCrea(contratoSedes.getFechaHoraCrea());
            cntContratoSede.setUsuarioModifica(contratoSedes.getUsuarioModifica());
            cntContratoSede.setTerminalModifica(contratoSedes.getTerminalModifica());
            cntContratoSede.setFechaHoraModifica(contratoSedes.getFechaHoraModifica());
            listaContratoSedes.add(cntContratoSede);
        }
//        DTOCntContratoDetalle cntContratoDetalle;
//        for (CntContratoDetalle contratoDetalles : per.getListaContratoDetalles()) {
//            cntContratoDetalle = new DTOCntContratoDetalle();
//            cntContratoDetalle.setId(contratoDetalles.getId());
//            cntContratoDetalle.setActivo(contratoDetalles.getActivo());
//            cntContratoDetalle.setTipoTecnologia(contratoDetalles.getTipoTecnologia());
//            cntContratoDetalle.setMaTecnologiaId(contratoDetalles.getMaTecnologiaId());
//            cntContratoDetalle.setMaTecnologiaCodigo(contratoDetalles.getMaTecnologiaCodigo());
//            cntContratoDetalle.setMaTecnologiaValor(contratoDetalles.getMaTecnologiaValor());
//            cntContratoDetalle.setMaServicioHabilitacionId(contratoDetalles.getMaServicioHabilitacionId());
//            cntContratoDetalle.setMaServicioHabilitacionCodigo(contratoDetalles.getMaServicioHabilitacionCodigo());
//            cntContratoDetalle.setMaServicioHabilitacionCodigo(contratoDetalles.getMaServicioHabilitacionCodigo());
//            cntContratoDetalle.setMaServicioHabilitacionValor(contratoDetalles.getMaServicioHabilitacionValor());
//            cntContratoDetalle.setTipoManualTarifario(contratoDetalles.getTipoManualTarifario());
//            cntContratoDetalle.setMaManualTarifarioId(contratoDetalles.getMaManualTarifarioId());
//            cntContratoDetalle.setMaManualTarifarioCodigo(contratoDetalles.getMaManualTarifarioCodigo());
//            cntContratoDetalle.setMaManualTarifarioValor(contratoDetalles.getMaManualTarifarioValor());
//            cntContratoDetalle.setMaManualTarifarioAgno(contratoDetalles.getMaManualTarifarioAgno());
//            cntContratoDetalle.setValorManual(contratoDetalles.getValorManual());
//            cntContratoDetalle.setValorContratado(contratoDetalles.getValorContratado());
//            cntContratoDetalle.setPorcentajeVariacion(contratoDetalles.getPorcentajeVariacion());
//            cntContratoDetalle.setComplejidad(contratoDetalles.getComplejidad());
//            cntContratoDetalle.setObservacionIncluye(contratoDetalles.getObservacionIncluye());
//            cntContratoDetalle.setObservacionExcluye(contratoDetalles.getObservacionExcluye());
//            cntContratoDetalle.setInterdependencia(contratoDetalles.getInterdependencia());
//            cntContratoDetalle.setMaeAmbitoId(contratoDetalles.getMaeAmbitoId());
//            cntContratoDetalle.setMaeAmbitoCodigo(contratoDetalles.getMaeAmbitoCodigo());
//            cntContratoDetalle.setMaeAmbitoValor(contratoDetalles.getMaeAmbitoValor());
//            cntContratoDetalle.setFechaHoraInicio(contratoDetalles.getFechaHoraInicio());
//            cntContratoDetalle.setFechaHoraFin(contratoDetalles.getFechaHoraFin());
//            cntContratoDetalle.setAutomatico(contratoDetalles.isAutomatico());
//            cntContratoDetalle.setValorMaximoRegulado(contratoDetalles.getValorMaximoRegulado());
//            this.listaContratoDetalles.add(cntContratoDetalle);
//        }
    }

    public CntContrato convertir() {
        CntContrato cntContrato = new CntContrato();
        CntPrestador cntPrestador = new CntPrestador();
        CntPrestadorSede objCntPrestadorSede;

        cntContrato.setId(this.id);
        cntContrato.setContrato(this.contrato);
        cntContrato.setDescripcion(this.descripcion);
        cntContrato.setActivo(this.activo);
        cntContrato.setMaeEstadoContratoId(this.maeEstadoContratoId);
        cntContrato.setMaeEstadoContratoCodigo(this.maeEstadoContratoCodigo);
        cntContrato.setMaeEstadoContratoValor(this.maeEstadoContratoValor);
        cntContrato.setFechaInicio(this.fechaInicio);
        cntContrato.setFechaFin(this.fechaFin);
        cntContrato.setValor(this.valor);
        cntContrato.setValorMes(this.valorMes);
        cntContrato.setNumAfiliados(this.numAfiliados);

        //Campos prestador
        cntPrestador.setId(this.idPrestador);
        cntPrestador.setCodigoMinSalud(this.codigoMinSalud);
        cntPrestador.setMaeClasePrestador(this.maeClasePrestadorId);
        cntPrestador.setMaeClasePrestadorCodigo(this.maeClasePrestadorCodigo);
        cntPrestador.setMaeClasePrestadorValor(this.maeClasePrestadorValor);
        cntPrestador.setMaeTipoDocumentoId(this.maeTipoDocumentoId);
        cntPrestador.setMaeTipoDocumentoCodigo(this.maeTipoDocumentoCodigo);
        cntPrestador.setMaeTipoDocumentoValor(this.maeTipoDocumentoValor);
        cntPrestador.setRazonSocial(this.razonSocial);
        cntPrestador.setCategoriaPrestador(this.categoriaPrestador);
        cntPrestador.setNivelAtencion(this.nivelAtencion);
        cntContrato.setCntPrestador(cntPrestador);
        cntContrato.setUsuarioCrea(this.usuarioCrea);
        cntContrato.setTerminalCrea(this.terminalCrea);
        cntContrato.setFechaHoraCrea(this.fechaHoraCrea);
        cntContrato.setUsuarioModifica(this.usuarioModifica);
        cntContrato.setTerminalModifica(this.terminalModifica);
        cntContrato.setFechaHoraModifica(this.fechaHoraModifica);
        CntContratoSede cntContratoSede;
        List<CntContratoSede> listaContratoSede = new ArrayList();

        for (DTOCntContratoSede contratoSedes : this.listaContratoSedes) {
            cntContratoSede = new CntContratoSede();
            cntContratoSede.setId(contratoSedes.getId());
            cntContratoSede.setMaeModalidadContratoId(contratoSedes.getMaeModalidadContratoId());
            cntContratoSede.setMaeModalidadContratoCodigo(contratoSedes.getMaeModalidadContratoCodigo());
            cntContratoSede.setMaeModalidadContratoValor(contratoSedes.getMaeModalidadContratoValor());
            cntContratoSede.setValorUpcAfiliado(contratoSedes.getValorUpcAfiliado());
            cntContratoSede.setAplicaSubsidiado(contratoSedes.getAplicaSubsidiado());
            cntContratoSede.setAplicaContribuitivo(contratoSedes.getAplicaContribuitivo());
            cntContratoSede.setAplicaGlosaExtemporanea(contratoSedes.getAplicaGlosaExtemporanea());
            cntContratoSede.setAplicaAuditoria(contratoSedes.getAplicaAuditoria());
            cntContratoSede.setAplicaPortabilidad(contratoSedes.getAplicaPortabilidad());
            cntContratoSede.setAplicaRecaudoCopagosIps(contratoSedes.getAplicaRecaudoCopagosIps());
            objCntPrestadorSede = new CntPrestadorSede();
            objCntPrestadorSede.setId(contratoSedes.getIdPrestadorSede());
            objCntPrestadorSede.setNombreSede(contratoSedes.getNombreSede());
            cntContratoSede.setCntPrestadorSede(objCntPrestadorSede);
            cntContratoSede.setUsuarioCrea(contratoSedes.getUsuarioCrea());
            cntContratoSede.setTerminalCrea(contratoSedes.getTerminalCrea());
            cntContratoSede.setFechaHoraCrea(contratoSedes.getFechaHoraCrea());
            cntContratoSede.setUsuarioModifica(contratoSedes.getUsuarioModifica());
            cntContratoSede.setTerminalModifica(contratoSedes.getTerminalModifica());
            cntContratoSede.setFechaHoraModifica(contratoSedes.getFechaHoraModifica());
            listaContratoSede.add(cntContratoSede);
        }
        cntContrato.setListaContratoSedes(listaContratoSede);

//        CntContratoDetalle cntContratoDetalle;
//        List<CntContratoDetalle> listaContratoDetalle = new ArrayList();
//        for (DTOCntContratoDetalle contratoDetalles : this.listaContratoDetalles) {
//            cntContratoDetalle = new CntContratoDetalle();
//            cntContratoDetalle.setId(contratoDetalles.getId());
//            cntContratoDetalle.setActivo(contratoDetalles.isActivo());
//            cntContratoDetalle.setTipoTecnologia(contratoDetalles.getTipoTecnologia());
//            cntContratoDetalle.setMaTecnologiaId(contratoDetalles.getMaTecnologiaId());
//            cntContratoDetalle.setMaTecnologiaCodigo(contratoDetalles.getMaTecnologiaCodigo());
//            cntContratoDetalle.setMaTecnologiaValor(contratoDetalles.getMaTecnologiaValor());
//            cntContratoDetalle.setMaServicioHabilitacionId(contratoDetalles.getMaServicioHabilitacionId());
//            cntContratoDetalle.setMaServicioHabilitacionCodigo(contratoDetalles.getMaServicioHabilitacionCodigo());
//            cntContratoDetalle.setMaServicioHabilitacionCodigo(contratoDetalles.getMaServicioHabilitacionCodigo());
//            cntContratoDetalle.setMaServicioHabilitacionValor(contratoDetalles.getMaServicioHabilitacionValor());
//            cntContratoDetalle.setTipoManualTarifario(contratoDetalles.getTipoManualTarifario());
//            cntContratoDetalle.setMaManualTarifarioId(contratoDetalles.getMaManualTarifarioId());
//            cntContratoDetalle.setMaManualTarifarioCodigo(contratoDetalles.getMaManualTarifarioCodigo());
//            cntContratoDetalle.setMaManualTarifarioValor(contratoDetalles.getMaManualTarifarioValor());
//            cntContratoDetalle.setMaManualTarifarioAgno(contratoDetalles.getMaManualTarifarioAgno());
//            cntContratoDetalle.setValorManual(contratoDetalles.getValorManual());
//            cntContratoDetalle.setValorContratado(contratoDetalles.getValorContratado());
//            cntContratoDetalle.setPorcentajeVariacion(contratoDetalles.getPorcentajeVariacion());
//            cntContratoDetalle.setComplejidad(contratoDetalles.getComplejidad());
//            cntContratoDetalle.setObservacionIncluye(contratoDetalles.getObservacionIncluye());
//            cntContratoDetalle.setObservacionExcluye(contratoDetalles.getObservacionExcluye());
//            cntContratoDetalle.setInterdependencia(contratoDetalles.isInterdependencia());
//            cntContratoDetalle.setMaeAmbitoId(contratoDetalles.getMaeAmbitoId());
//            cntContratoDetalle.setMaeAmbitoCodigo(contratoDetalles.getMaeAmbitoCodigo());
//            cntContratoDetalle.setMaeAmbitoValor(contratoDetalles.getMaeAmbitoValor());
//            cntContratoDetalle.setFechaHoraInicio(contratoDetalles.getFechaHoraInicio());
//            cntContratoDetalle.setFechaHoraFin(contratoDetalles.getFechaHoraFin());
//            cntContratoDetalle.setAutomatico(contratoDetalles.isAutomatico());
//            cntContratoDetalle.setValorMaximoRegulado(contratoDetalles.getValorMaximoRegulado());
//            cntContratoDetalle.setUsuarioCrea(contratoDetalles.getUsuarioCrea());
//            cntContratoDetalle.setTerminalCrea(contratoDetalles.getTerminalCrea());
//            cntContratoDetalle.setFechaHoraCrea(contratoDetalles.getFechaHoraCrea());
//            cntContratoDetalle.setUsuarioModifica(contratoDetalles.getUsuarioModifica());
//            cntContratoDetalle.setTerminalModifica(contratoDetalles.getTerminalModifica());
//            cntContratoDetalle.setFechaHoraModifica(contratoDetalles.getFechaHoraModifica());
//            listaContratoDetalle.add(cntContratoDetalle);
//
//        }
        return cntContrato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return activo ? "Si" : "No";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMaeEstadoContratoId() {
        return maeEstadoContratoId;
    }

    public void setMaeEstadoContratoId(int maeEstadoContratoId) {
        this.maeEstadoContratoId = maeEstadoContratoId;
    }

    public String getMaeEstadoContratoCodigo() {
        return maeEstadoContratoCodigo;
    }

    public void setMaeEstadoContratoCodigo(String maeEstadoContratoCodigo) {
        this.maeEstadoContratoCodigo = maeEstadoContratoCodigo;
    }

    public String getMaeEstadoContratoValor() {
        return maeEstadoContratoValor;
    }

    public void setMaeEstadoContratoValor(String maeEstadoContratoValor) {
        this.maeEstadoContratoValor = maeEstadoContratoValor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorMes() {
        return valorMes;
    }

    public void setValorMes(BigDecimal valorMes) {
        this.valorMes = valorMes;
    }

    public Integer getNumAfiliados() {
        return numAfiliados;
    }

    public void setNumAfiliados(Integer numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    public String getCodigoMinSalud() {
        return codigoMinSalud;
    }

    public void setCodigoMinSalud(String codigoMinSalud) {
        this.codigoMinSalud = codigoMinSalud;
    }

    public int getMaeClasePrestadorId() {
        return maeClasePrestadorId;
    }

    public void setMaeClasePrestadorId(int maeClasePrestadorId) {
        this.maeClasePrestadorId = maeClasePrestadorId;
    }

    public String getMaeClasePrestadorCodigo() {
        return maeClasePrestadorCodigo;
    }

    public void setMaeClasePrestadorCodigo(String maeClasePrestadorCodigo) {
        this.maeClasePrestadorCodigo = maeClasePrestadorCodigo;
    }

    public String getMaeClasePrestadorValor() {
        return maeClasePrestadorValor;
    }

    public void setMaeClasePrestadorValor(String maeClasePrestadorValor) {
        this.maeClasePrestadorValor = maeClasePrestadorValor;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCategoriaPrestador() {
        return categoriaPrestador;
    }

    public void setCategoriaPrestador(int categoriaPrestador) {
        this.categoriaPrestador = categoriaPrestador;
    }

    public int getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(int nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    public List<DTOCntContratoSede> getListaContratoSedes() {
        return listaContratoSedes;
    }

    public void setListaContratoSedes(List<DTOCntContratoSede> listaContratoSedes) {
        this.listaContratoSedes = listaContratoSedes;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }
    
    

//    public List<DTOCntContratoDetalle> getListaContratoDetalles() {
//        return listaContratoDetalles;
//    }
//
//    public void setListaContratoDetalles(List<DTOCntContratoDetalle> listaContratoDetalles) {
//        this.listaContratoDetalles = listaContratoDetalles;
//    }
    @Override
    public String toString() {
        return "DTOCntContrato{" + "id=" + id + ", contrato=" + contrato + ", descripcion=" + descripcion + ", activo=" + activo + ", maeEstadoContratoId=" + maeEstadoContratoId + ", maeEstadoContratoCodigo=" + maeEstadoContratoCodigo + ", maeEstadoContratoValor=" + maeEstadoContratoValor + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valor=" + valor + ", valorMes=" + valorMes + ", numAfiliados=" + numAfiliados + ", codigoMinSalud=" + codigoMinSalud + ", maeClasePrestadorId=" + maeClasePrestadorId + ", maeClasePrestadorCodigo=" + maeClasePrestadorCodigo + ", maeClasePrestadorValor=" + maeClasePrestadorValor + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", razonSocial=" + razonSocial + ", categoriaPrestador=" + categoriaPrestador + ", nivelAtencion=" + nivelAtencion + ", listaContratoSedes=" + listaContratoSedes + '}';
    }

    public String toStringJson() {
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        //2022-04-26 (rpalacic)Clonar clase y ajustar datos
        DTOCntContrato clonContrato;
        try {
            clonContrato = (DTOCntContrato) this.clone();
        } catch (CloneNotSupportedException ex) {
            clonContrato = this;
        }
        return gson.toJson(clonContrato);
    }

}
