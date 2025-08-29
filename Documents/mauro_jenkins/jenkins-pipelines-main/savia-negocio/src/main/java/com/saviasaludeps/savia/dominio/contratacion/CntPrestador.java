package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

public class CntPrestador extends Auditoria {
    
    public static final int ID_PRESTADOR_SIN_ESPECIFICAR = 8;

    private Integer id;
    private String codigoMinSalud;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String digitoVerificacion;
    private String razonSocial;
    private String naturalezaJuridica;
    private String prefijo;
    private int maeClasePrestador;
    private String maeClasePrestadorCodigo;
    private String maeClasePrestadorValor;
    private int categoriaPrestador;
    private int nivelAtencion;
    private int maeTipoDocumentoRepId;
    private String maeTipoDocumentoRepCodigo;
    private String maeTipoDocumentoRepValor;
    private String numeroDocumentoRep;
    private String nombreRepresentanteLegal;
    private boolean activo;
    //2021-06-30 jyperez INC 0854 nuevo campo
    private boolean facturacionElectronica;
    //2023-07-04 jyperez nuevo campo unión temporal;
    private boolean unionTemporal;
    //2024-10-03 jyperez nuevos campos
    private Integer grupoRipsMinisterio;
    private List<CntPrestadorSede> listaPrestadorSedes;

    public CntPrestador() {
    }

    public CntPrestador(Integer id) {
        this.id = id;
    }

    public CntPrestador(Integer id, String razonSocial) {
        this.id = id;
        this.razonSocial = razonSocial;
    }

    public CntPrestador(Integer id, String razonSocial, String nombreRepresentanteLegal) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoMinSalud() {
        return codigoMinSalud;
    }

    public void setCodigoMinSalud(String codigoMinSalud) {
        this.codigoMinSalud = codigoMinSalud;
    }
    
    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public List<CntPrestadorSede> getListaPrestadorSedes() {
        return listaPrestadorSedes;
    }

    public void setListaPrestadorSedes(List<CntPrestadorSede> listaPrestadorSedes) {
        this.listaPrestadorSedes = listaPrestadorSedes;
    }

    @Override
    public String toString() {
        return "CntPrestador{" + "id=" + id + ", codigoMinSalud=" + codigoMinSalud + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", digitoVerificacion=" + digitoVerificacion + ", razonSocial=" + razonSocial + ", naturalezaJuridica=" + naturalezaJuridica + ", prefijo=" + prefijo + ", maeClasePrestador=" + maeClasePrestador + ", maeClasePrestadorCodigo=" + maeClasePrestadorCodigo + ", maeClasePrestadorValor=" + maeClasePrestadorValor + ", categoriaPrestador=" + categoriaPrestador + ", nivelAtencion=" + nivelAtencion + ", maeTipoDocumentoRepId=" + maeTipoDocumentoRepId + ", maeTipoDocumentoRepCodigo=" + maeTipoDocumentoRepCodigo + ", maeTipoDocumentoRepValor=" + maeTipoDocumentoRepValor + ", numeroDocumentoRep=" + numeroDocumentoRep + ", nombreRepresentanteLegal=" + nombreRepresentanteLegal + ", facturacionElectronica=" + facturacionElectronica + '}';
    }

    /**
     * @return the maeTipoDocumentoId
     */
    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    /**
     * @param maeTipoDocumentoId the maeTipoDocumentoId to set
     */
    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the naturalezaJuridica
     */
    public String getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    /**
     * @param naturalezaJuridica the naturalezaJuridica to set
     */
    public void setNaturalezaJuridica(String naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
    }

    /**
     * @return the maeClasePrestador
     */
    public int getMaeClasePrestador() {
        return maeClasePrestador;
    }

    /**
     * @param maeClasePrestador the maeClasePrestador to set
     */
    public void setMaeClasePrestador(int maeClasePrestador) {
        this.maeClasePrestador = maeClasePrestador;
    }

    /**
     * @return the categoriaPrestador
     */
    public int getCategoriaPrestador() {
        return categoriaPrestador;
    }

    /**
     * @param categoriaPrestador the categoriaPrestador to set
     */
    public void setCategoriaPrestador(int categoriaPrestador) {
        this.categoriaPrestador = categoriaPrestador;
    }

    /**
     * @return the nivelAtencion
     */
    public int getNivelAtencion() {
        return nivelAtencion;
    }

    /**
     * @param nivelAtencion the nivelAtencion to set
     */
    public void setNivelAtencion(int nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    /**
     * @return the maeTipoDocumentoRepId
     */
    public int getMaeTipoDocumentoRepId() {
        return maeTipoDocumentoRepId;
    }

    /**
     * @param maeTipoDocumentoRepId the maeTipoDocumentoRepId to set
     */
    public void setMaeTipoDocumentoRepId(int maeTipoDocumentoRepId) {
        this.maeTipoDocumentoRepId = maeTipoDocumentoRepId;
    }

    /**
     * @return the numeroDocumentoRep
     */
    public String getNumeroDocumentoRep() {
        return numeroDocumentoRep;
    }

    /**
     * @param numeroDocumentoRep the numeroDocumentoRep to set
     */
    public void setNumeroDocumentoRep(String numeroDocumentoRep) {
        this.numeroDocumentoRep = numeroDocumentoRep;
    }

    /**
     * @return the maeTipoDocumentoCodigo
     */
    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    /**
     * @param maeTipoDocumentoCodigo the maeTipoDocumentoCodigo to set
     */
    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    /**
     * @return the maeTipoDocumentoValor
     */
    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    /**
     * @param maeTipoDocumentoValor the maeTipoDocumentoValor to set
     */
    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    /**
     * @return the maeClasePrestadorCodigo
     */
    public String getMaeClasePrestadorCodigo() {
        return maeClasePrestadorCodigo;
    }

    /**
     * @param maeClasePrestadorCodigo the maeClasePrestadorCodigo to set
     */
    public void setMaeClasePrestadorCodigo(String maeClasePrestadorCodigo) {
        this.maeClasePrestadorCodigo = maeClasePrestadorCodigo;
    }

    /**
     * @return the maeClasePrestadorValor
     */
    public String getMaeClasePrestadorValor() {
        return maeClasePrestadorValor;
    }

    /**
     * @param maeClasePrestadorValor the maeClasePrestadorValor to set
     */
    public void setMaeClasePrestadorValor(String maeClasePrestadorValor) {
        this.maeClasePrestadorValor = maeClasePrestadorValor;
    }

    /**
     * @return the maeTipoDocumentoRepCodigo
     */
    public String getMaeTipoDocumentoRepCodigo() {
        return maeTipoDocumentoRepCodigo;
    }

    /**
     * @param maeTipoDocumentoRepCodigo the maeTipoDocumentoRepCodigo to set
     */
    public void setMaeTipoDocumentoRepCodigo(String maeTipoDocumentoRepCodigo) {
        this.maeTipoDocumentoRepCodigo = maeTipoDocumentoRepCodigo;
    }

    /**
     * @return the maeTipoDocumentoRepValor
     */
    public String getMaeTipoDocumentoRepValor() {
        return maeTipoDocumentoRepValor;
    }

    /**
     * @param maeTipoDocumentoRepValor the maeTipoDocumentoRepValor to set
     */
    public void setMaeTipoDocumentoRepValor(String maeTipoDocumentoRepValor) {
        this.maeTipoDocumentoRepValor = maeTipoDocumentoRepValor;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getActivoStr() {
        String mensaje = "NO";
        if (activo) {
            mensaje = "SI";
        }
        return mensaje;
    }
    public String getFacturacionElectronicaStr() {
        String mensaje = "NO";
        if (facturacionElectronica) {
            mensaje = "SI";
        }
        return mensaje;
    }

    /**
     * @return the facturacionElectronica
     */
    public boolean getFacturacionElectronica() {
        return facturacionElectronica;
    }

    /**
     * @param facturacionElectronica the facturacionElectronica to set
     */
    public void setFacturacionElectronica(boolean facturacionElectronica) {
        this.facturacionElectronica = facturacionElectronica;
    }

    /**
     * @return the unionTemporal
     */
    public boolean getUnionTemporal() {
        return unionTemporal;
    }

    /**
     * @param unionTemporal the unionTemporal to set
     */
    public void setUnionTemporal(boolean unionTemporal) {
        this.unionTemporal = unionTemporal;
    }

    /**
     * @return the grupoRipsMinisterio
     */
    public Integer getGrupoRipsMinisterio() {
        return grupoRipsMinisterio;
    }
    
    /**
     * @return the grupoRipsMinisterio
     */
    public String getGrupoRipsMinisterioStr() {
        String msg= "";
        if (grupoRipsMinisterio != null) {
            switch(grupoRipsMinisterio) {
                case 1:
                    msg = "Grupo 1";
                break;
                case 2:
                    msg = "Grupo 2";
                break;
                case 3:
                    msg = "Grupo 3";
                break;
                    
            }
        }
        return msg;
    }

    /**
     * @param grupoRipsMinisterio the grupoRipsMinisterio to set
     */
    public void setGrupoRipsMinisterio(Integer grupoRipsMinisterio) {
        this.grupoRipsMinisterio = grupoRipsMinisterio;
    }
}
