package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class RcoFactura extends Auditoria {

    //TIPO RECOBRO
    public static final int PGP = 0;
    public static final int CAPITA = 1;

    private Integer id;
    private boolean aplicaRecobro;
    private Integer tipoRecobro;
    private Integer maeTipoContratoId;
    private String maeTipoContratoCodigo;
    private String maeTipoContratoValor;
    private String numeroContrato;
    private String nit;
    private String ips;
    private int numeroRadicado;
    private String numeroFacturado;
    private BigDecimal valorFactura;
    private BigDecimal valorPagadoFactura;
    private Integer estadoFactura;
    private Date fechaHoraPrestacion;
    private Date fechaHoraRadicacion;
    private int maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private Date cmFechaHoraCrea;
    private BigDecimal valorInicialGlosa;
    private Date fechaHoraModifica;
    private String usuarioAuditoria;
    private String terminalAuditoria;
    private Date fechaHoraAuditoria;
    private List<RcoFacturaDetalle> rcoFacturaDetallesList;
    private CmFactura cmFacturaId;
    private CntPrestador cntPrestadoreId;
    private int maeEstadoRecobroId;
    private String maeEstadoRecobroCodigo;
    private String maeEstadoRecobroValor;
    private PePrograma peProgramaId;

    public RcoFactura() {
    }

    public RcoFactura(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public Integer getTipoRecobro() {
        return tipoRecobro;
    }

    public void setTipoRecobro(Integer tipoRecobro) {
        this.tipoRecobro = tipoRecobro;
    }

    public Integer getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(Integer maeTipoContratoId) {
        this.maeTipoContratoId = maeTipoContratoId;
    }

    public String getMaeTipoContratoCodigo() {
        return maeTipoContratoCodigo;
    }

    public void setMaeTipoContratoCodigo(String maeTipoContratoCodigo) {
        this.maeTipoContratoCodigo = maeTipoContratoCodigo;
    }

    public String getMaeTipoContratoValor() {
        return maeTipoContratoValor;
    }

    public void setMaeTipoContratoValor(String maeTipoContratoValor) {
        this.maeTipoContratoValor = maeTipoContratoValor;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public BigDecimal getValorPagadoFactura() {
        return valorPagadoFactura;
    }

    public void setValorPagadoFactura(BigDecimal valorPagadoFactura) {
        this.valorPagadoFactura = valorPagadoFactura;
    }

    public Integer getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(Integer estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Date getFechaHoraPrestacion() {
        return fechaHoraPrestacion;
    }

    public void setFechaHoraPrestacion(Date fechaHoraPrestacion) {
        this.fechaHoraPrestacion = fechaHoraPrestacion;
    }

    public Date getFechaHoraRadicacion() {
        return fechaHoraRadicacion;
    }

    public void setFechaHoraRadicacion(Date fechaHoraRadicacion) {
        this.fechaHoraRadicacion = fechaHoraRadicacion;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public Date getCmFechaHoraCrea() {
        return cmFechaHoraCrea;
    }

    public void setCmFechaHoraCrea(Date cmFechaHoraCrea) {
        this.cmFechaHoraCrea = cmFechaHoraCrea;
    }

    public BigDecimal getValorInicialGlosa() {
        return valorInicialGlosa;
    }

    public void setValorInicialGlosa(BigDecimal valorInicialGlosa) {
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getUsuarioAuditoria() {
        return usuarioAuditoria;
    }

    public void setUsuarioAuditoria(String usuarioAuditoria) {
        this.usuarioAuditoria = usuarioAuditoria;
    }

    public String getTerminalAuditoria() {
        return terminalAuditoria;
    }

    public void setTerminalAuditoria(String terminalAuditoria) {
        this.terminalAuditoria = terminalAuditoria;
    }

    public Date getFechaHoraAuditoria() {
        return fechaHoraAuditoria;
    }

    public void setFechaHoraAuditoria(Date fechaHoraAuditoria) {
        this.fechaHoraAuditoria = fechaHoraAuditoria;
    }

    public List<RcoFacturaDetalle> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalle> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
    }

    public CmFactura getCmFacturaId() {
        return cmFacturaId;
    }

    public void setCmFacturaId(CmFactura cmFacturaId) {
        this.cmFacturaId = cmFacturaId;
    }

    public CntPrestador getCntPrestadoreId() {
        return cntPrestadoreId;
    }

    public void setCntPrestadoreId(CntPrestador cntPrestadoreId) {
        this.cntPrestadoreId = cntPrestadoreId;
    }

    public int getMaeEstadoRecobroId() {
        return maeEstadoRecobroId;
    }

    public void setMaeEstadoRecobroId(int maeEstadoRecobroId) {
        this.maeEstadoRecobroId = maeEstadoRecobroId;
    }

    public String getMaeEstadoRecobroCodigo() {
        return maeEstadoRecobroCodigo;
    }

    public void setMaeEstadoRecobroCodigo(String maeEstadoRecobroCodigo) {
        this.maeEstadoRecobroCodigo = maeEstadoRecobroCodigo;
    }

    public String getMaeEstadoRecobroValor() {
        return maeEstadoRecobroValor;
    }

    public void setMaeEstadoRecobroValor(String maeEstadoRecobroValor) {
        this.maeEstadoRecobroValor = maeEstadoRecobroValor;
    }

    public PePrograma getPeProgramaId() {
        return peProgramaId;
    }

    public void setPeProgramaId(PePrograma peProgramaId) {
        this.peProgramaId = peProgramaId;
    }
    

    //Metodo auxiliares
    public String getAplicaRecobroStr() {
        if (aplicaRecobro) {
            return "Si";
        } else {
            return "No";
        }
    }
//
    @Override
    public String toString() {
        return "RcoFactura{" + "id=" + id + ", aplicaRecobro=" + aplicaRecobro + ", tipoRecobro=" + tipoRecobro + ", maeTipoContratoId=" + maeTipoContratoId + ", maeTipoContratoCodigo=" + maeTipoContratoCodigo + ", maeTipoContratoValor=" + maeTipoContratoValor + ", numeroContrato=" + numeroContrato + ", nit=" + nit + ", ips=" + ips + ", numeroRadicado=" + numeroRadicado + ", numeroFacturado=" + numeroFacturado + ", valorFactura=" + valorFactura + ", valorPagadoFactura=" + valorPagadoFactura + ", estadoFactura=" + estadoFactura + ", fechaHoraPrestacion=" + fechaHoraPrestacion + ", fechaHoraRadicacion=" + fechaHoraRadicacion + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", cmFechaHoraCrea=" + cmFechaHoraCrea + ", valorInicialGlosa=" + valorInicialGlosa + ", fechaHoraModifica=" + fechaHoraModifica + ", usuarioAuditoria=" + usuarioAuditoria + ", terminalAuditoria=" + terminalAuditoria + ", fechaHoraAuditoria=" + fechaHoraAuditoria + '}';
    }

}
