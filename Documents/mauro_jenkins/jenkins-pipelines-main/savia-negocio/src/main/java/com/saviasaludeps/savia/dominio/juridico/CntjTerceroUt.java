
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author idbohorquez
 */
public class CntjTerceroUt extends Auditoria{
    
    private Integer id;
    private Integer maeTipoDocumentoId;    
    private String maeTipoDocumentoCodigo;    
    private String maeTipoDocumentoValor;    
    private String numeroDocumento;    
    private String razonSocial;    
    private boolean borrado;    
    private Integer naturalezaJuridica;
    private CntjTercero cntjTercero;
    private BigDecimal porcentajeParticipacion;

    public CntjTerceroUt() {
    }

    public CntjTerceroUt(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public void setNaturalezaJuridica(Integer naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
    }

    public CntjTercero getCntjTercero() {
        return cntjTercero;
    }

    public void setCntjTercero(CntjTercero cntjTercero) {
        this.cntjTercero = cntjTercero;
    }

    public BigDecimal getPorcentajeParticipacion() {
        return porcentajeParticipacion;
    }

    public void setPorcentajeParticipacion(BigDecimal porcentajeParticipacion) {
        this.porcentajeParticipacion = porcentajeParticipacion;
    }

    @Override
    public String toString() {
        return "CntjTerceroUt{" + "id=" + id + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", razonSocial=" + razonSocial + ", borrado=" + borrado + ", naturalezaJuridica=" + naturalezaJuridica + ", cntjTercero=" + cntjTercero + ", porcentajeParticipacion=" + porcentajeParticipacion + '}';
    }

    
}
