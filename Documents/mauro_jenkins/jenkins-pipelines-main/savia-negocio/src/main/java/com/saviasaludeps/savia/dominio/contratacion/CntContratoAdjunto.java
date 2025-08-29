/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jyperez
 */
public class CntContratoAdjunto extends Auditoria {
    
    private Integer id;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    private CntContrato cntContrato;
    
    public CntContratoAdjunto() {
    }

    public CntContratoAdjunto(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the maeTipoArchivoId
     */
    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    /**
     * @param maeTipoArchivoId the maeTipoArchivoId to set
     */
    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    /**
     * @return the maeTipoArchivoCodigo
     */
    public String getMaeTipoArchivoCodigo() {
        return maeTipoArchivoCodigo;
    }

    /**
     * @param maeTipoArchivoCodigo the maeTipoArchivoCodigo to set
     */
    public void setMaeTipoArchivoCodigo(String maeTipoArchivoCodigo) {
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
    }

    /**
     * @return the maeTipoArchivoValor
     */
    public String getMaeTipoArchivoValor() {
        return maeTipoArchivoValor;
    }

    /**
     * @param maeTipoArchivoValor the maeTipoArchivoValor to set
     */
    public void setMaeTipoArchivoValor(String maeTipoArchivoValor) {
        this.maeTipoArchivoValor = maeTipoArchivoValor;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the existe
     */
    public boolean isExiste() {
        return existe;
    }

    /**
     * @param existe the existe to set
     */
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    /**
     * @return the cntContrato
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContrato to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }
    
}
