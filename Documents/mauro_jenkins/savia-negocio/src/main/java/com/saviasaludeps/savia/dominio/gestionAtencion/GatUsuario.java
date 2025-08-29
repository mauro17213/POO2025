/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;
/**
 *
 * @author acuartas
 */
public class GatUsuario extends Auditoria {

    private Integer id;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String email;
    private List<GatAtencion> gatAtencionList;
    private AsegAfiliado asegAfiliado;
    private List<GatTiquete> gatTiqueteList;
    
    //Variable Auxiliar
    private boolean afiliado;

    public GatUsuario() {
    }

    public GatUsuario(Integer id) {
        this.id = id;
    }
    
    public GatUsuario(Integer id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

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

    public List<GatAtencion> getGatAtencionList() {
        return gatAtencionList;
    }

    public void setGatAtencionList(List<GatAtencion> gatAtencionList) {
        this.gatAtencionList = gatAtencionList;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliadoId) {
        this.asegAfiliado = asegAfiliado;
    }

    public List<GatTiquete> getGatTiqueteList() {
        return gatTiqueteList;
    }

    public void setGatTiqueteList(List<GatTiquete> gatTiqueteList) {
        this.gatTiqueteList = gatTiqueteList;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //Metodos auxiliares
    public String getNombreCompleto() {
        String nombreCompleto = getNombres();
        if (getApellidos() != null) {
            nombreCompleto += " "+getApellidos();
        }
        return nombreCompleto;
    }

    public boolean isAfiliado() {
        if (getAsegAfiliado() != null) {
            this.afiliado = true;
        }
        return afiliado;
    }

    public void setAfiliado(boolean afiliado) {
        this.afiliado = afiliado;
    }   
    
    
    @Override
    public String toString() {
        return "GatUsuario{" + "id=" + id + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", gatAtencionList=" + gatAtencionList + ", asegAfiliado=" + asegAfiliado + '}';
    }
}
