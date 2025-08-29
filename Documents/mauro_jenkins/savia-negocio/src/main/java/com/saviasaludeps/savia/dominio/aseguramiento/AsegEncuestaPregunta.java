/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegEncuestaPregunta extends Auditoria {

    private Integer id;
    private String pregunta;
    private String norma;
    private boolean estado;
    private List<AsegTabulacionEncuesta> listaAsegTabulacionEncuestas;

    public AsegEncuestaPregunta() {
    }

    public AsegEncuestaPregunta(Integer id) {
        this.id = id;
    }

    public AsegEncuestaPregunta(Integer id, String pregunta, String norma, boolean estado) {
        this.id = id;
        this.pregunta = pregunta;
        this.norma = norma;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<AsegTabulacionEncuesta> getListaAsegTabulacionEncuestas() {
        return listaAsegTabulacionEncuestas;
    }

    public void setListaAsegTabulacionEncuestas(List<AsegTabulacionEncuesta> listaAsegTabulacionEncuestas) {
        this.listaAsegTabulacionEncuestas = listaAsegTabulacionEncuestas;
    }

    @Override
    public String toString() {
        return "AsegEncuestaPregunta{" + "id=" + id + ", pregunta=" + pregunta + ", norma=" + norma + ", estado=" + estado + '}';
    }
    
}
