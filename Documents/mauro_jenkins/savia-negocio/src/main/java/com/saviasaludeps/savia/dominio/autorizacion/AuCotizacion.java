package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AuCotizacion extends Auditoria {
    
    public static final int FUENTE_ORIGEN_ANEXOS_3 = 1;
    public static final int FUENTE_ORIGEN_MIPRES = 2;
    
    private Integer id;
    private boolean activo;
    private int tipoTecnologia;
    private int tipoTecnologiaMipres;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maMedicamentoId;
    private String maMedicamentoCodigo;
    private String maMedicamentoValor;
    private int tipoTarifa;
    private Integer maTarifarioId;
    private String maTarifarioCodigo;
    private String maTarifarioValor;
    private BigDecimal porcentajeNegociacion;
    private BigDecimal valorTecnologia;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    private String observacion;
    //2023-09-27 jyperez nuevos campos
    private boolean pagoAnticipado;
    private List<AuCotizacionAdjunto> auCotizacionAdjuntosList;
    private List<AuCotizacionItem> auCotizacionItemsList;
    private List<AuSolicitudAdjunto> auSolicitudAdjuntosList;
    private CntPrestadorSede cntPrestadorSede;
    //-062024-17 pvacca nuevos campos 
    private MpPrescripcion mpPrescripcionId;
    private AsegAfiliado asegAfiliadosId;
    private AuAnexo3 auAnexo3Id;
    private Integer fuenteOrigen;
    private String mpNumeroPrescripcion;
    //2024-09-06 pvacca nuevos campos
    private AntAnticipo antAnticiposId;
    private AntAnticipoItem antAnticipoItemsId;
    private boolean habilitarCamposAnticipos;

    public final static int TIPO_TECNOLOGIA = 1;
    public final static int TIPO_MEDICAMENTO = 2;
    public final static int TIPO_INSUMO = 3;
    public final static int TIPO_PAQUETE = 4;

    public final static int TARIFA_SOAT = 1;
    public final static int TARIFA_ISS2000 = 2;
    public final static int TARIFA_ISS2001 = 3;
    public final static int TARIFA_PROPIA = 4;

    public final static int TIPO_TARIFA_PROPIA = 0;
    public final static int TIPO_TARIFA_SOAT = 1;
    public final static int TIPO_TARIFA_ISS2000 = 2;
    public final static int TIPO_TARIFA_ISS2001 = 3;

    public final static int CIEN = 100;

    public AuCotizacion() {
    }

    public AuCotizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getTipoTecnologiaMipres() {
        return tipoTecnologiaMipres;
    }

    public void setTipoTecnologiaMipres(int tipoTecnologiaMipres) {
        this.tipoTecnologiaMipres = tipoTecnologiaMipres;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public BigDecimal getPorcentajeNegociacion() {
        return porcentajeNegociacion;
    }

    public void setPorcentajeNegociacion(BigDecimal porcentajeNegociacion) {
        this.porcentajeNegociacion = porcentajeNegociacion;
    }

    public BigDecimal getValorTecnologia() {
        return valorTecnologia;
    }

    public void setValorTecnologia(BigDecimal valorTecnologia) {
        this.valorTecnologia = valorTecnologia;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public MpPrescripcion getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripcion mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexo3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexo3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String getMpNumeroPrescripcion() {
        return mpNumeroPrescripcion;
    }

    public AntAnticipo getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipo antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public AntAnticipoItem getAntAnticipoItemsId() {
        return antAnticipoItemsId;
    }

    public void setAntAnticipoItemsId(AntAnticipoItem antAnticipoItemsId) {
        this.antAnticipoItemsId = antAnticipoItemsId;
    }

    public boolean isHabilitarCamposAnticipos() {
        return habilitarCamposAnticipos;
    }

    public void setHabilitarCamposAnticipos(boolean habilitarCamposAnticipos) {
        this.habilitarCamposAnticipos = habilitarCamposAnticipos;
    }

    public void setMpNumeroPrescripcion(String mpNumeroPrescripcion) {
        this.mpNumeroPrescripcion = mpNumeroPrescripcion;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTarifarioCodigo() {
        return maTarifarioCodigo;
    }

    public void setMaTarifarioCodigo(String maTarifarioCodigo) {
        this.maTarifarioCodigo = maTarifarioCodigo;
    }

    public Integer getMaTarifarioId() {
        return maTarifarioId;
    }

    public void setMaTarifarioId(Integer maTarifarioId) {
        this.maTarifarioId = maTarifarioId;
    }

    public String getMaTarifarioValor() {
        return maTarifarioValor;
    }

    public void setMaTarifarioValor(String maTarifarioValor) {
        this.maTarifarioValor = maTarifarioValor;
    }

    public int getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(int tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjunto> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public List<AuCotizacionItem> getAuCotizacionItemsList() {
        return auCotizacionItemsList;
    }

    public void setAuCotizacionItemsList(List<AuCotizacionItem> auCotizacionItemsList) {
        this.auCotizacionItemsList = auCotizacionItemsList;
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

    @Override
    public String toString() {
        return "AuCotizacion{" + "id=" + id + ", activo=" + activo + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", tipoTarifa=" + tipoTarifa + ", maTarifarioId=" + maTarifarioId + ", maTarifarioCodigo=" + maTarifarioCodigo + ", maTarifarioValor=" + maTarifarioValor + ", porcentajeNegociacion=" + porcentajeNegociacion + ", valorTecnologia=" + valorTecnologia + ", fechaInicioVigencia=" + fechaInicioVigencia + ", fechaFinVigencia=" + fechaFinVigencia + ", observacion=" + observacion + ", auSolicitudAdjuntosList=" + auSolicitudAdjuntosList + ", auCotizacionItemsList=" + auCotizacionItemsList + ", cntPrestadorSede=" + cntPrestadorSede + '}';
    }

    /**
     * @return the pagoAnticipado
     */
    public boolean isPagoAnticipado() {
        return pagoAnticipado;
    }

    /**
     * @param pagoAnticipado the pagoAnticipado to set
     */
    public void setPagoAnticipado(boolean pagoAnticipado) {
        this.pagoAnticipado = pagoAnticipado;
    }
    
    public String getFuenteOerigenStr(){
        String strFuenteOrigen = "";
        if(fuenteOrigen != null){
            switch(fuenteOrigen){
                case FUENTE_ORIGEN_ANEXOS_3:
                    strFuenteOrigen = "Anexos 3";
                    break;
                case FUENTE_ORIGEN_MIPRES:
                    strFuenteOrigen = "Mipres";
                    break;
            }
        }
        return strFuenteOrigen;
    }
}
