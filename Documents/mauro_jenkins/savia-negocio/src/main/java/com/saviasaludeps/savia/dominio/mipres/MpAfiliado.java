package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

public class MpAfiliado extends Auditoria {

    private Integer id;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private Integer maeGeneroId;
    private String maeGeneroCodigo;
    private String maeGeneroValor;
    private Integer maeEstadoAfiliacionId;
    private String maeEstadoAfiliacionCodigo;
    private String maeEstadoAfiliacionValor;
    private List<MpPrescripcion> mpPrescripcionesList;
    private AsegAfiliado asegAfiliadosId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getMaeGeneroId() {
        return maeGeneroId;
    }

    public void setMaeGeneroId(Integer maeGeneroId) {
        this.maeGeneroId = maeGeneroId;
    }

    public String getMaeGeneroCodigo() {
        return maeGeneroCodigo;
    }

    public void setMaeGeneroCodigo(String maeGeneroCodigo) {
        this.maeGeneroCodigo = maeGeneroCodigo;
    }

    public String getMaeGeneroValor() {
        return maeGeneroValor;
    }

    public void setMaeGeneroValor(String maeGeneroValor) {
        this.maeGeneroValor = maeGeneroValor;
    }

    public Integer getMaeEstadoAfiliacionId() {
        return maeEstadoAfiliacionId;
    }

    public void setMaeEstadoAfiliacionId(Integer maeEstadoAfiliacionId) {
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
    }

    public String getMaeEstadoAfiliacionCodigo() {
        return maeEstadoAfiliacionCodigo;
    }

    public void setMaeEstadoAfiliacionCodigo(String maeEstadoAfiliacionCodigo) {
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
    }

    public String getMaeEstadoAfiliacionValor() {
        return maeEstadoAfiliacionValor;
    }

    public void setMaeEstadoAfiliacionValor(String maeEstadoAfiliacionValor) {
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
    }

    public List<MpPrescripcion> getMpPrescripcionesList() {
        return mpPrescripcionesList;
    }

    public void setMpPrescripcionesList(List<MpPrescripcion> mpPrescripcionesList) {
        this.mpPrescripcionesList = mpPrescripcionesList;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MpAfiliado{id=").append(id);
        sb.append(", maeTipoDocumentoId=").append(maeTipoDocumentoId);
        sb.append(", maeTipoDocumentoCodigo=").append(maeTipoDocumentoCodigo);
        sb.append(", maeTipoDocumentoValor=").append(maeTipoDocumentoValor);
        sb.append(", numeroDocumento=").append(numeroDocumento);
        sb.append(", primerNombre=").append(primerNombre);
        sb.append(", segundoNombre=").append(segundoNombre);
        sb.append(", primerApellido=").append(primerApellido);
        sb.append(", segundoApellido=").append(segundoApellido);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", maeGeneroId=").append(maeGeneroId);
        sb.append(", maeGeneroCodigo=").append(maeGeneroCodigo);
        sb.append(", maeGeneroValor=").append(maeGeneroValor);
        sb.append(", maeEstadoAfiliacionId=").append(maeEstadoAfiliacionId);
        sb.append(", maeEstadoAfiliacionCodigo=").append(maeEstadoAfiliacionCodigo);
        sb.append(", maeEstadoAfiliacionValor=").append(maeEstadoAfiliacionValor);
        sb.append(", usuarioCrea=").append(usuarioCrea);
        sb.append(", terminalCrea=").append(terminalCrea);
        sb.append(", fechaHoraCrea=").append(fechaHoraCrea);
        sb.append(", mpPrescripcionesList=").append(mpPrescripcionesList);
        sb.append(", asegAfiliadosId=").append(asegAfiliadosId);
        sb.append('}');
        return sb.toString();
    }
    
}
