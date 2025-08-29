package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class CntjTercero extends Auditoria{
    
    private Integer id;
    private Integer tipoTercero;
    private Integer naturalezaJuridica;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String razonSocial;
    private Integer maeRepresentanteTipoDocumentoId;
    private String maeRepresentanteTipoDocumentoCodigo;
    private String maeRepresentanteTipoDocumentoValor;
    private String representanteNumeroDocumento;
    private String nombreRepresentanteLegal;
    private String codigoHabilitacion;
    private String direccion;
    private String correoElectronico;
    private String telefonoTercero;
    private Integer maeCargoId;
    private String maeCargoCodigo;
    private String maeCargoValor;
    private Integer maeAreaId;
    private String maeAreaCodigo;
    private String maeAreaValor;
    private boolean unionTemporal;
    private List<CntjTerceroUnionTemporal> cntjTerceroUnionTemporalList;
    private List<CntjTerceroContacto> cntjTerceroContactoList;
    private List<CntjContrato> cntjContratoList;
    private CntPrestador cntPrestadorId;
    private Ubicacion gnUbicacionId;
    private Usuario gnUsuarioId;
    private List<CntjContratoSupervisor> cntjContratoSupervisorList;
    private List<CntjContratoSeguimiento> cntjContratoSeguimientoList;

    public CntjTercero() {
        this.gnUbicacionId = new Ubicacion();
    }

    public CntjTercero(Integer id) {
        this.id = id;
        this.gnUbicacionId = new Ubicacion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoTercero() {
        return tipoTercero;
    }

    public void setTipoTercero(Integer tipoTercero) {
        this.tipoTercero = tipoTercero;
    }

    public Integer getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public void setNaturalezaJuridica(Integer naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
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

    public Integer getMaeRepresentanteTipoDocumentoId() {
        return maeRepresentanteTipoDocumentoId;
    }

    public void setMaeRepresentanteTipoDocumentoId(Integer maeRepresentanteTipoDocumentoId) {
        this.maeRepresentanteTipoDocumentoId = maeRepresentanteTipoDocumentoId;
    }

    public String getMaeRepresentanteTipoDocumentoCodigo() {
        return maeRepresentanteTipoDocumentoCodigo;
    }

    public void setMaeRepresentanteTipoDocumentoCodigo(String maeRepresentanteTipoDocumentoCodigo) {
        this.maeRepresentanteTipoDocumentoCodigo = maeRepresentanteTipoDocumentoCodigo;
    }

    public String getMaeRepresentanteTipoDocumentoValor() {
        return maeRepresentanteTipoDocumentoValor;
    }

    public void setMaeRepresentanteTipoDocumentoValor(String maeRepresentanteTipoDocumentoValor) {
        this.maeRepresentanteTipoDocumentoValor = maeRepresentanteTipoDocumentoValor;
    }

    public String getRepresentanteNumeroDocumento() {
        return representanteNumeroDocumento;
    }

    public void setRepresentanteNumeroDocumento(String representanteNumeroDocumento) {
        this.representanteNumeroDocumento = representanteNumeroDocumento;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoTercero() {
        return telefonoTercero;
    }

    public void setTelefonoTercero(String telefonoTercero) {
        this.telefonoTercero = telefonoTercero;
    }

    public Integer getMaeCargoId() {
        return maeCargoId;
    }

    public void setMaeCargoId(Integer maeCargoId) {
        this.maeCargoId = maeCargoId;
    }

    public String getMaeCargoCodigo() {
        return maeCargoCodigo;
    }

    public void setMaeCargoCodigo(String maeCargoCodigo) {
        this.maeCargoCodigo = maeCargoCodigo;
    }

    public String getMaeCargoValor() {
        return maeCargoValor;
    }

    public void setMaeCargoValor(String maeCargoValor) {
        this.maeCargoValor = maeCargoValor;
    }

    public Integer getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(Integer maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public boolean isUnionTemporal() {
        return unionTemporal;
    }

    public void setUnionTemporal(boolean unionTemporal) {
        this.unionTemporal = unionTemporal;
    }

    public List<CntjTerceroUnionTemporal> getCntjTerceroUnionTemporalList() {
        return cntjTerceroUnionTemporalList;
    }

    public void setCntjTerceroUnionTemporalList(List<CntjTerceroUnionTemporal> cntjTerceroUnionTemporalList) {
        this.cntjTerceroUnionTemporalList = cntjTerceroUnionTemporalList;
    }

    public List<CntjTerceroContacto> getCntjTerceroContactoList() {
        return cntjTerceroContactoList;
    }

    public void setCntjTerceroContactoList(List<CntjTerceroContacto> cntjTerceroContactoList) {
        this.cntjTerceroContactoList = cntjTerceroContactoList;
    }

    public List<CntjContrato> getCntjContratoList() {
        return cntjContratoList;
    }

    public void setCntjContratoList(List<CntjContrato> cntjContratoList) {
        this.cntjContratoList = cntjContratoList;
    }

    public CntPrestador getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(CntPrestador cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public Ubicacion getGnUbicacionId() {
        return gnUbicacionId;
    }

    public void setGnUbicacionId(Ubicacion gnUbicacionId) {
        this.gnUbicacionId = gnUbicacionId;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    public List<CntjContratoSupervisor> getCntjContratoSupervisorList() {
        return cntjContratoSupervisorList;
    }

    public void setCntjContratoSupervisorList(List<CntjContratoSupervisor> cntjContratoSupervisorList) {
        this.cntjContratoSupervisorList = cntjContratoSupervisorList;
    }

    public List<CntjContratoSeguimiento> getCntjContratoSeguimientoList() {
        return cntjContratoSeguimientoList;
    }

    public void setCntjContratoSeguimientoList(List<CntjContratoSeguimiento> cntjContratoSeguimientoList) {
        this.cntjContratoSeguimientoList = cntjContratoSeguimientoList;
    }
    
    //Metodos auxiliares
    public String getUnionTemporalstr() {
        return unionTemporal?"Si":"No";
    }


    @Override
    public String toString() {
        return "CntjTercero{" + "id=" + id + ", tipoTercero=" + tipoTercero + ", naturalezaJuridica=" + naturalezaJuridica + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", razonSocial=" + razonSocial + ", maeRepresentanteTipoDocumentoId=" + maeRepresentanteTipoDocumentoId + ", maeRepresentanteTipoDocumentoCodigo=" + maeRepresentanteTipoDocumentoCodigo + ", maeRepresentanteTipoDocumentoValor=" + maeRepresentanteTipoDocumentoValor + ", representanteNumeroDocumento=" + representanteNumeroDocumento + ", nombreRepresentanteLegal=" + nombreRepresentanteLegal + ", codigoHabilitacion=" + codigoHabilitacion + ", direccion=" + direccion + ", correoElectronico=" + correoElectronico + ", telefonoTercero=" + telefonoTercero + ", maeCargoId=" + maeCargoId + ", maeCargoCodigo=" + maeCargoCodigo + ", maeCargoValor=" + maeCargoValor + ", maeAreaId=" + maeAreaId + ", maeAreaCodigo=" + maeAreaCodigo + ", maeAreaValor=" + maeAreaValor + ", unionTemporal=" + unionTemporal + '}';
    }    

}
