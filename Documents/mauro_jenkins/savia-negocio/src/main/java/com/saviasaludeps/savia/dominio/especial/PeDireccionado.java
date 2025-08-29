/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class PeDireccionado extends Auditoria implements Serializable{
    
    private Integer id;
    private int maeAfiliadoTipoDocumentoId;
    private String maeAfiliadoTipoDocumentoCodigo;
    private String maeAfiliadoTipoDocumentoValor;
    private String afiliadoNumeroDocumento;
    private String afiliadoPrimerNombre;
    private String afiliadoSegundoNombre;
    private String afiliadoPrimerApellido;
    private String afiliadoSegundoApellido;
    private int estado;
    private String observacion;
    private boolean tieneTutela;
    private AsegAfiliado asegAfiliadosId;
    private AuAnexo3 auAnexos3Id;
    private CntPrestadorSede cntPrestadorSedesId;
    private CntPrestador cntPrestadoresId;
    private PePrograma peProgramasId;
    private Date fechaHoraEnGestion;
    private Date fechaHoraGestiona;
    private Empresa gnEmpresasId;
    private List<PeDireccionadoItem> listDireccionadoItems;    
    private List<PeDireccionadoGestion> listaGestion;
    private List<ReporteDireccionado> reporteDireccionados;

    public PeDireccionado() {
        listaGestion = new ArrayList<>();
        listDireccionadoItems = new ArrayList<>();
        reporteDireccionados = new ArrayList<>();
    }

    public PeDireccionado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeAfiliadoTipoDocumentoId() {
        return maeAfiliadoTipoDocumentoId;
    }

    public void setMaeAfiliadoTipoDocumentoId(int maeAfiliadoTipoDocumentoId) {
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
    }

    public String getMaeAfiliadoTipoDocumentoCodigo() {
        return maeAfiliadoTipoDocumentoCodigo;
    }

    public void setMaeAfiliadoTipoDocumentoCodigo(String maeAfiliadoTipoDocumentoCodigo) {
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
    }

    public String getMaeAfiliadoTipoDocumentoValor() {
        return maeAfiliadoTipoDocumentoValor;
    }

    public void setMaeAfiliadoTipoDocumentoValor(String maeAfiliadoTipoDocumentoValor) {
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getTieneTutela() {
        return tieneTutela;
    }

    public void setTieneTutela(boolean tieneTutela) {
        this.tieneTutela = tieneTutela;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexo3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexo3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public CntPrestadorSede getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSede cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestador getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestador cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public PePrograma getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PePrograma peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public List<PeDireccionadoGestion> getListaGestion() {
        return listaGestion;
    }

    public void setListaGestion(List<PeDireccionadoGestion> listaGestion) {
        this.listaGestion = listaGestion;
    }

    public Date getFechaHoraEnGestion() {
        return fechaHoraEnGestion;
    }

    public void setFechaHoraEnGestion(Date fechaHoraEnGestion) {
        this.fechaHoraEnGestion = fechaHoraEnGestion;
    }

    public Date getFechaHoraGestiona() {
        return fechaHoraGestiona;
    }

    public void setFechaHoraGestiona(Date fechaHoraGestiona) {
        this.fechaHoraGestiona = fechaHoraGestiona;
    }

    public Empresa getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Empresa gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public List<PeDireccionadoItem> getListDireccionadoItems() {
        return listDireccionadoItems;
    }

    public void setListDireccionadoItems(List<PeDireccionadoItem> listDireccionadoItems) {
        this.listDireccionadoItems = listDireccionadoItems;
    }

    public List<ReporteDireccionado> getReporteDireccionados() {
        return reporteDireccionados;
    }

    public void setReporteDireccionados(List<ReporteDireccionado> reporteDireccionados) {
        this.reporteDireccionados = reporteDireccionados;
    }
    
    public String getDatosAuditoria() {
        String datos = "";
        if (getAfiliadoPrimerNombre()!= null) {
            datos += " " + getAfiliadoPrimerNombre();
        }
        if (getAfiliadoSegundoNombre() != null) {
            datos += " " + getAfiliadoSegundoNombre();
        }
        if (getAfiliadoPrimerApellido() != null) {
            datos += " " + getAfiliadoPrimerApellido();
        }
        if (getAfiliadoSegundoApellido() != null) {
            datos += " " + getAfiliadoSegundoApellido();
        }
        if (getMaeAfiliadoTipoDocumentoCodigo() != null) {
            datos += " " + getMaeAfiliadoTipoDocumentoCodigo();
        }
        if (getAfiliadoNumeroDocumento() != null) {
            datos += " " + getAfiliadoNumeroDocumento() + " ";
        }
        return datos;
    }

    @Override
    public String toString() {
        return "PeDireccionado{" + "id=" + id + ", maeAfiliadoTipoDocumentoId=" + maeAfiliadoTipoDocumentoId + ", maeAfiliadoTipoDocumentoCodigo=" + maeAfiliadoTipoDocumentoCodigo + ", maeAfiliadoTipoDocumentoValor=" + maeAfiliadoTipoDocumentoValor + ", afiliadoNumeroDocumento=" + afiliadoNumeroDocumento + ", afiliadoPrimerNombre=" + afiliadoPrimerNombre + ", afiliadoSegundoNombre=" + afiliadoSegundoNombre + ", afiliadoPrimerApellido=" + afiliadoPrimerApellido + ", afiliadoSegundoApellido=" + afiliadoSegundoApellido + ", estado=" + estado + ", observacion=" + observacion + ", tieneTutela=" + tieneTutela + ", asegAfiliadosId=" + asegAfiliadosId + ", auAnexos3Id=" + auAnexos3Id + ", cntPrestadorSedesId=" + cntPrestadorSedesId + ", cntPrestadoresId=" + cntPrestadoresId + ", peProgramasId=" + peProgramasId + ", fechaHoraEnGestion=" + fechaHoraEnGestion + ", fechaHoraGestiona=" + fechaHoraGestiona + ", gnEmpresasId=" + gnEmpresasId + '}';
    }
    
    public String getBooleanToStr(Boolean valor) {
        if (valor == null) {
            valor = false;
        }
        return (valor) ? "SI" : "NO";
    }
    
    
}
