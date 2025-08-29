package com.saviasaludeps.savia.dominio.mapa;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

public class MapaPrestador extends Auditoria {

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
    private boolean facturacionElectronica;
    private boolean unionTemporal;
    private Integer grupoRipsMinisterio;
    private List<MapaPrestadorSede> listaPrestadorSedes;

    public MapaPrestador() {
    }

    public MapaPrestador(Integer id) {
        this.id = id;
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

    public List<MapaPrestadorSede> getListaPrestadorSedes() {
        return listaPrestadorSedes;
    }

    public void setListaPrestadorSedes(List<MapaPrestadorSede> listaPrestadorSedes) {
        this.listaPrestadorSedes = listaPrestadorSedes;
    }
    
    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
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

    public String getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public void setNaturalezaJuridica(String naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
    }

    public int getMaeClasePrestador() {
        return maeClasePrestador;
    }

    public void setMaeClasePrestador(int maeClasePrestador) {
        this.maeClasePrestador = maeClasePrestador;
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

    public int getMaeTipoDocumentoRepId() {
        return maeTipoDocumentoRepId;
    }

    public void setMaeTipoDocumentoRepId(int maeTipoDocumentoRepId) {
        this.maeTipoDocumentoRepId = maeTipoDocumentoRepId;
    }

    public String getNumeroDocumentoRep() {
        return numeroDocumentoRep;
    }

    public void setNumeroDocumentoRep(String numeroDocumentoRep) {
        this.numeroDocumentoRep = numeroDocumentoRep;
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

    public String getMaeTipoDocumentoRepCodigo() {
        return maeTipoDocumentoRepCodigo;
    }

    public void setMaeTipoDocumentoRepCodigo(String maeTipoDocumentoRepCodigo) {
        this.maeTipoDocumentoRepCodigo = maeTipoDocumentoRepCodigo;
    }

    public String getMaeTipoDocumentoRepValor() {
        return maeTipoDocumentoRepValor;
    }

    public void setMaeTipoDocumentoRepValor(String maeTipoDocumentoRepValor) {
        this.maeTipoDocumentoRepValor = maeTipoDocumentoRepValor;
    }

    public boolean isActivo() {
        return activo;
    }

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

    public boolean getFacturacionElectronica() {
        return facturacionElectronica;
    }

    public void setFacturacionElectronica(boolean facturacionElectronica) {
        this.facturacionElectronica = facturacionElectronica;
    }

    public boolean getUnionTemporal() {
        return unionTemporal;
    }

    public void setUnionTemporal(boolean unionTemporal) {
        this.unionTemporal = unionTemporal;
    }

    public Integer getGrupoRipsMinisterio() {
        return grupoRipsMinisterio;
    }

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

    public void setGrupoRipsMinisterio(Integer grupoRipsMinisterio) {
        this.grupoRipsMinisterio = grupoRipsMinisterio;
    }
}
