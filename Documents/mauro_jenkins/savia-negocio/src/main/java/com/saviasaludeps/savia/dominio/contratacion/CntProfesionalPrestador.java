/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CntProfesionalPrestador extends Auditoria{
    
    private Integer id;
    private boolean activo;
    private int maEspecialidadId;
    private String maEspecialidadCodigo;
    private String maEspecialidadValor;
    private CntPrestador cntPrestador;
    private CntProfesional cntProfesionalesId;
    private boolean actualizar;

    public CntProfesionalPrestador() {
    }

    public CntProfesionalPrestador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMaEspecialidadId() {
        return maEspecialidadId;
    }

    public void setMaEspecialidadId(int maEspecialidadId) {
        this.maEspecialidadId = maEspecialidadId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
    }

    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    public CntProfesional getCntProfesionalesId() {
        return cntProfesionalesId;
    }

    public void setCntProfesionalesId(CntProfesional cntProfesionalesId) {
        this.cntProfesionalesId = cntProfesionalesId;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
    @Override
    public String toString() {
        return "CntProfesionalPrestador{" + "id=" + id + ", activo=" + activo + ", maEspecialidadId=" + maEspecialidadId + 
                ", maEspecialidadCodigo=" + maEspecialidadCodigo + ", maEspecialidadValor=" + maEspecialidadValor + ", cntPrestador=" + cntPrestador + 
                ", cntProfesionalesId=" + cntProfesionalesId + '}';
    }
    
}
