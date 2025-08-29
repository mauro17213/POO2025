/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CntProfesional extends Auditoria {
    
    private Integer id;
    private int maeTipoCodumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String registroMedico;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private List<CntProfesionalPrestador> listaCntProfesionalPrestador;
    private List<RefAnexo9> listaRefAnexo9;
    
    //Identificador para la consola de Referencia y contraReferencia
    private boolean guardar;

    public CntProfesional() {
    }

    public CntProfesional(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoCodumentoId() {
        return maeTipoCodumentoId;
    }

    public void setMaeTipoCodumentoId(int maeTipoCodumentoId) {
        this.maeTipoCodumentoId = maeTipoCodumentoId;
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

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
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
    
    public String getNombreCompleto() {
        String str = "";
        str += (primerNombre == null) ? "" : primerNombre;
        str += (segundoNombre == null) ? "" : " " + segundoNombre;
        str += (primerApellido == null) ? "" : " " + primerApellido;
        str += (segundoApellido == null) ? "" : " " + segundoApellido;
        return str;
    }

    public List<CntProfesionalPrestador> getListaCntProfesionalPrestador() {
        return listaCntProfesionalPrestador;
    }

    public void setListaCntProfesionalPrestador(List<CntProfesionalPrestador> listaCntProfesionalPrestador) {
        this.listaCntProfesionalPrestador = listaCntProfesionalPrestador;
    }

    public List<RefAnexo9> getListaRefAnexo9() {
        return listaRefAnexo9;
    }

    public void setListaRefAnexo9(List<RefAnexo9> listaRefAnexo9) {
        this.listaRefAnexo9 = listaRefAnexo9;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    @Override
    public String toString() {
        return "CntProfesional{" + "id=" + id + ", maeTipoCodumentoId=" + maeTipoCodumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", registroMedico=" + registroMedico + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + '}';
    }
    
}
