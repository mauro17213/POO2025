/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import javax.xml.crypto.Data;

/**
 *
 * @author jramirer
 */
public class TuPersona extends Auditoria {

    private Integer id;
    private Integer asegAfiliadoId;
    private Integer maeEstadoAfiliadoId;
    private String maeEstadoAfiliadoCodigo;
    private String maeEstadoAfiliadoValor;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;

    private Date fechaNacimento;
    private Integer maeGeneroId;
    private String maeGeneroCodigo;
    private String maeGeneroValor;
    private Boolean agentreOficioso;
    private String correoElectronico;
    private String direccion;
    private Ubicacion ubicacionAfiliadoId;
    private String contratoAfiliacion;
    private Boolean esAfiliado;
    public TuPersona(Integer id, Integer maeEstadoAfiliadoId, String maeEstadoAfiliadoCodigo, String maeEstadoAfiliadoValor, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String segundoApellido0, Date fechaNacimiento, int maeGeneroId, String maeGeneroCodigo, String maeGeneroValor, String agenteOficioso, String correoElectronico, String direccionResidencia, int ubicacionAfiliacionId) {

        this.id = id;
        this.maeEstadoAfiliadoId = maeEstadoAfiliadoId;
        this.maeEstadoAfiliadoCodigo = maeEstadoAfiliadoCodigo;
        this.maeEstadoAfiliadoValor = maeEstadoAfiliadoValor;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.fechaNacimento = fechaNacimento;
        this.maeGeneroId = maeGeneroId;
        this.maeGeneroCodigo = maeGeneroCodigo;
        this.maeGeneroValor = maeGeneroValor;
        this.agentreOficioso = agentreOficioso;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.ubicacionAfiliadoId = ubicacionAfiliadoId;

    }

    public TuPersona() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public TuPersona(Integer id) {
      this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(Integer asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }
    
    public Integer getMaeEstadoAfiliadoId() {
        return maeEstadoAfiliadoId;
    }

    public void setMaeEstadoAfiliadoId(Integer maeEstadoAfiliadoId) {
        this.maeEstadoAfiliadoId = maeEstadoAfiliadoId;
    }

    public String getMaeEstadoAfiliadoCodigo() {
        return maeEstadoAfiliadoCodigo;
    }

    public void setMaeEstadoAfiliadoCodigo(String maeEstadoAfiliadoCodigo) {
        this.maeEstadoAfiliadoCodigo = maeEstadoAfiliadoCodigo;
    }

    public String getMaeEstadoAfiliadoValor() {
        return maeEstadoAfiliadoValor;
    }

    public void setMaeEstadoAfiliadoValor(String maeEstadoAfiliadoValor) {
        this.maeEstadoAfiliadoValor = maeEstadoAfiliadoValor;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimento() {
        return fechaNacimento;
    }

    public void setFechaNacimento(Date fechaNacimento) {
        this.fechaNacimento = fechaNacimento;
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

    public Boolean getAgentreOficioso() {
        return agentreOficioso;
    }
    
    public Boolean isAgentreOficioso() {
        return agentreOficioso;
    }
    
    public void setAgentreOficioso(Boolean agentreOficioso) {
        this.agentreOficioso = agentreOficioso;
    }
    
    public String getAgentreOficiosoStr() {
        String entregaSucesivas = "N/A";
        if (getAgentreOficioso() != null) {
            entregaSucesivas = (getAgentreOficioso()) ? "SI" : "NO";
        }
        return entregaSucesivas;
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ubicacion getUbicacionAfiliadoId() {
        return ubicacionAfiliadoId;
    }

    public void setUbicacionAfiliadoId(Ubicacion ubicacionAfiliadoId) {
        this.ubicacionAfiliadoId = ubicacionAfiliadoId;
    }

    public String getContratoAfiliacion() {
        return contratoAfiliacion;
    }

    public void setContratoAfiliacion(String contratoAfiliacion) {
        this.contratoAfiliacion = contratoAfiliacion;
    }

    public Boolean getEsAfiliado() {
        return esAfiliado;
    }

    public void setEsAfiliado(Boolean esAfiliado) {
        this.esAfiliado = esAfiliado;
    }
    
    public String getNombreCompleto() {
       String str = "";
       str += (nombres == null) ? "" : nombres;
       str += (apellidos == null) ? "" : " " + apellidos;
       return str;
    }
    
    /*public String getApellidoCompleto() {
       String str = "";
       str += (primerApellido == null) ? "" : primerApellido;
       str += (segundoApellido == null) ? "" : " " + segundoApellido;
       return str;
    }*/
    
    public String getDatosAuditoria() {
        String datos = "";
        if (getNombres() != null) {
            datos += " " + getNombres();
        }
        if (getApellidos() != null) {
            datos += " " + getApellidos();
        }
        if (getMaeTipoDocumentoCodigo() != null) {
            datos += " " + getMaeTipoDocumentoCodigo();
        }
        if (getNumeroDocumento() != null) {
            datos += " " + getNumeroDocumento() + " ";
        }
        return datos;
    }
    
    public boolean exitePersona() {
        return this.getId() != null && this.getId() > 0;
    }
    
    @Override
    public String toString() {
        return "TuPersona{" + "id=" + id + ", maeEstadoAfiliadoId=" + maeEstadoAfiliadoId + ", maeEstadoAfiliadoCodigo=" + maeEstadoAfiliadoCodigo + ", maeEstadoAfiliadoValor=" + maeEstadoAfiliadoValor + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", nombres=" + nombres + ", apellidos=" + apellidos  + ", fechaNacimento=" + fechaNacimento + ", maeGeneroId=" + maeGeneroId + ", maeGeneroCodigo=" + maeGeneroCodigo + ", maeGeneroValor=" + maeGeneroValor + ", agentreOficioso=" + agentreOficioso + ", correoElectronico=" + correoElectronico + ", direccion=" + direccion + ", ubicacionAfiliadoId=" + ubicacionAfiliadoId + ", contratoAfiliacion=" + contratoAfiliacion + '}';
    }

}
