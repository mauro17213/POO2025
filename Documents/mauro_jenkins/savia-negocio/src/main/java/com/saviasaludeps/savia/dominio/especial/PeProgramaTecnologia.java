/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class PeProgramaTecnologia  extends Auditoria {
    
    private Integer id;
    private PePrograma peProgramasId;
    private short tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer accion;    
    private boolean marcaAutomatico; 
    private boolean direcciona;
    private boolean borrado;
    
    private transient Integer sincronizado;
    private transient String programaCargaMasiva;
    private String errorCarga;
    private String registroArchivo;
    
    public PeProgramaTecnologia() {
    }

    public PeProgramaTecnologia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PePrograma getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PePrograma peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public short getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(short tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
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

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
    }    

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getProgramaCargaMasiva() {
        return programaCargaMasiva;
    }

    public void setProgramaCargaMasiva(String programaCargaMasiva) {
        this.programaCargaMasiva = programaCargaMasiva;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }    

    public String getRegistroArchivo() {
        return registroArchivo;
    }

    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    public boolean getMarcaAutomatico() {
        return marcaAutomatico;
    }

    public void setMarcaAutomatico(boolean marcaAutomatico) {
        this.marcaAutomatico = marcaAutomatico;
    }

    public boolean getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(boolean direcciona) {
        this.direcciona = direcciona;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    
}
