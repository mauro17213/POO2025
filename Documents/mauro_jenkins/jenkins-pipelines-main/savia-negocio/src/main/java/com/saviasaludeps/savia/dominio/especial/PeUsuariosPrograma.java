/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeUsuariosPrograma  extends Auditoria implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private int tipo;
    private boolean activo;
    private Date fechaInicio;
    private Date fechaFin;    
    private PePrograma peProgramasId;
    private Usuario usuariosId;
    private Integer maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    
    public static final Integer TIPO_COORDINADOR = 1;
    public static final Integer TIPO_ANALISTA = 2;
    
    public static final Integer InActivo = 0;
    public static final Integer Activo = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }
    
    public String getTipoString() {
        String mensaje = "";
        switch (tipo) {
            case 1:
                mensaje = "Coordinador";
                break;
            case 2:
                mensaje = "Analista";
                break;
            case 3:
                mensaje = "Auxiliar";
                break;
            case 4:
                mensaje = "Practicante";
                break;
        }
        return mensaje;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public String getActivoString() {
        return activo ? "Si" : "No";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public PePrograma getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PePrograma peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public Usuario getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuario usuariosId) {
        this.usuariosId = usuariosId;
    }

    public Integer getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(Integer maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }
    
}
