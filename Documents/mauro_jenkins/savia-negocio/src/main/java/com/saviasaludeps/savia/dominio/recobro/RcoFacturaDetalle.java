package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class RcoFacturaDetalle extends Auditoria {

    //MODALIDAD
    public static final int PGP = 0;
    public static final int CAPITA = 1;
    public static final int LONGITUD_CAMPO_CORTO = 20;
    //APLICA RECOBRO
    public static final int NO_AUDITADO = 0;
    public static final int SI = 1;
    public static final int NO = 2;

    //CAUSAL RECOBRO
    public static final int TOTAL_FACTURADO = 0;
    public static final int PORCENTAJE_FACTURA = 1;

    private Integer id;
    private Integer modalidad;
    private Integer maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private String observacionDetalle;
    private String numeroContrato;
    private boolean aplicaRecobro;
    private boolean causalRecobro;
    private int cmDetalleEstado;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String nombreCompleto;
    private int consecutivoItem;
    private int maServicioHabilitadoId;
    private String maServicioCodigo;
    private String maServicioValor;
    private int cantidad;
    private int valorFacturado;
    private String observacion;
    private int tipoServicio;
    private Date fechaHoraPrestacion;
    private String conceptoContable;
    private String maDiagnostico;
    private Integer maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private BigDecimal valorTotalRecobro;
    private BigDecimal valorRestanteRecobro;
    private List<RcoConciliacionGestion> rcoConciliacionGestionesList;
    private CntPrestadorSede cntPrestadoresSedesId;
    private Usuario gnUsuarioResponsableId;
    private CmDetalle cmDetalleId;
    private PePrograma peProgramaId;
    private RcoFactura rcoFacturasId;
    private RcoGrupo rcoGruposId;
    private boolean estadoConciliacion;
    private RcoConciliacion rcoConciliacionId;

    public RcoFacturaDetalle() {
    }

    public RcoFacturaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public String getObservacionDetalle() {
        return observacionDetalle;
    }

    public void setObservacionDetalle(String observacionDetalle) {
        this.observacionDetalle = observacionDetalle;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

        public boolean isAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public boolean isCausalRecobro() {
        return causalRecobro;
    }

    public void setCausalRecobro(boolean causalRecobro) {
        this.causalRecobro = causalRecobro;
    }

    public int getCmDetalleEstado() {
        return cmDetalleEstado;
    }

    public void setCmDetalleEstado(int cmDetalleEstado) {
        this.cmDetalleEstado = cmDetalleEstado;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getConsecutivoItem() {
        return consecutivoItem;
    }

    public void setConsecutivoItem(int consecutivoItem) {
        this.consecutivoItem = consecutivoItem;
    }

    public int getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(int maServicioHabilitadoId) {
        this.maServicioHabilitadoId = maServicioHabilitadoId;
    }

    public String getMaServicioCodigo() {
        return maServicioCodigo;
    }

    public void setMaServicioCodigo(String maServicioCodigo) {
        this.maServicioCodigo = maServicioCodigo;
    }

    public String getMaServicioValor() {
        return maServicioValor;
    }

    public void setMaServicioValor(String maServicioValor) {
        this.maServicioValor = maServicioValor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(int valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Date getFechaHoraPrestacion() {
        return fechaHoraPrestacion;
    }

    public void setFechaHoraPrestacion(Date fechaHoraPrestacion) {
        this.fechaHoraPrestacion = fechaHoraPrestacion;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public String getMaDiagnostico() {
        return maDiagnostico;
    }

    public void setMaDiagnostico(String maDiagnostico) {
        this.maDiagnostico = maDiagnostico;
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

    public BigDecimal getValorTotalRecobro() {
        return valorTotalRecobro;
    }

    public void setValorTotalRecobro(BigDecimal valorTotalRecobro) {
        this.valorTotalRecobro = valorTotalRecobro;
    }

    public BigDecimal getValorRestanteRecobro() {
        return valorRestanteRecobro;
    }

    public void setValorRestanteRecobro(BigDecimal valorRestanteRecobro) {
        this.valorRestanteRecobro = valorRestanteRecobro;
    }

    public List<RcoConciliacionGestion> getRcoConciliacionGestionesList() {
        return rcoConciliacionGestionesList;
    }

    public void setRcoConciliacionGestionesList(List<RcoConciliacionGestion> rcoConciliacionGestionesList) {
        this.rcoConciliacionGestionesList = rcoConciliacionGestionesList;
    }

    public CntPrestadorSede getCntPrestadoresSedesId() {
        return cntPrestadoresSedesId;
    }

    public void setCntPrestadoresSedesId(CntPrestadorSede cntPrestadoresSedesId) {
        this.cntPrestadoresSedesId = cntPrestadoresSedesId;
    }

    public Usuario getGnUsuarioResponsableId() {
        return gnUsuarioResponsableId;
    }

    public void setGnUsuarioResponsableId(Usuario gnUsuarioResponsableId) {
        this.gnUsuarioResponsableId = gnUsuarioResponsableId;
    }

    public CmDetalle getCmDetalleId() {
        return cmDetalleId;
    }

    public void setCmDetalleId(CmDetalle cmDetalleId) {
        this.cmDetalleId = cmDetalleId;
    }

    public PePrograma getPeProgramaId() {
        return peProgramaId;
    }

    public void setPeProgramaId(PePrograma peProgramaId) {
        this.peProgramaId = peProgramaId;
    }

    public RcoFactura getRcoFacturasId() {
        return rcoFacturasId;
    }

    public void setRcoFacturasId(RcoFactura rcoFacturasId) {
        this.rcoFacturasId = rcoFacturasId;
    }

    public RcoGrupo getRcoGruposId() {
        return rcoGruposId;
    }

    public void setRcoGruposId(RcoGrupo rcoGruposId) {
        this.rcoGruposId = rcoGruposId;
    }

    public boolean isEstadoConciliacion() {
        return estadoConciliacion;
    }

    public void setEstadoConciliacion(boolean estadoConciliacion) {
        this.estadoConciliacion = estadoConciliacion;
    }

    public RcoConciliacion getRcoConciliacionId() {
        return rcoConciliacionId;
    }

    public void setRcoConciliacionId(RcoConciliacion rcoConciliacionId) {
        this.rcoConciliacionId = rcoConciliacionId;
    }
    
// Metodos auxiliares
    public String getObservacionNombreCorto() {
        String observacionNombreCorto = "";
        if (getObservacion() != null) {
            observacionNombreCorto = getObservacion();
            if (getObservacion().length() >= LONGITUD_CAMPO_CORTO) {
                return observacionNombreCorto.substring(0, LONGITUD_CAMPO_CORTO) + "..";
            } else {
                return observacionNombreCorto;
            }
        }
        return observacionNombreCorto;
    }
    public String getModalidadStr(){
        String modalidadStr = "PGP";
        if (getModalidad() == 1) {
            modalidadStr = "CAPITA";
        }
        return modalidadStr;
    }
    
    public String getAplicaRecobroStr(){
        String aplicaRecobroStr = "No";
        if(aplicaRecobro){
           aplicaRecobroStr = "Si";
        }
        return aplicaRecobroStr;
    }
    
    public String getAplicaRecobroColor(){
        String aplicaRecobroColor = "red";
        if(aplicaRecobro){
           aplicaRecobroColor = "green";
        }
        return aplicaRecobroColor;
    }
    
    public String getCausalRecobroStr(){
        String causalRecobroStr = "Porcentaje Facturado";
        if(causalRecobro){
           causalRecobroStr = "Total Facturado";
        }
        return causalRecobroStr;
    }
    public String getEstadoConciliacionStr(){
        String causalRecobroStr = "No Conciliado";
        if(estadoConciliacion){
           causalRecobroStr = "Conciliado";
        }
        return causalRecobroStr;
    }
        public Boolean getEstadoFacturaAuditada(){
        Boolean estadoGestionar = true;
        if ("auditado".equals(maeEstadoValor)) {
            estadoGestionar = false;
        }
        return estadoGestionar;
    }
        public boolean getModalidadBln(){
        boolean modalidadBln = false;
            if (modalidad==1) {
                modalidadBln = true;
            }
        return modalidadBln;
        }
    
    @Override
    public String toString() {
        return "RcoFacturaDetalle{" + "id=" + id + ", modalidad=" + modalidad + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", observacionDetalle=" + observacionDetalle + ", numeroContrato=" + numeroContrato + ", aplicaRecobro=" + aplicaRecobro + ", causalRecobro=" + causalRecobro + ", cmDetalleEstado=" + cmDetalleEstado + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", nombreCompleto=" + nombreCompleto + ", consecutivoItem=" + consecutivoItem + ", maServicioHabilitadoId=" + maServicioHabilitadoId + ", maServicioCodigo=" + maServicioCodigo + ", maServicioValor=" + maServicioValor + ", cantidad=" + cantidad + ", valorFacturado=" + valorFacturado + ", observacion=" + observacion + ", tipoServicio=" + tipoServicio + ", fechaHoraPrestacion=" + fechaHoraPrestacion + ", conceptoContable=" + conceptoContable + ", maDiagnosticoId=" + maDiagnosticoId + ", maDiagnosticoCodigo=" + maDiagnosticoCodigo + ", maDiagnosticoValor=" + maDiagnosticoValor + '}';
    }

}
