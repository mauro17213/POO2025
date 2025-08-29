/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge perez
 */
public class TuGrupo extends Auditoria{
    
    public static final int TIPO_AUDITOR_INICIAL_ABOGADO = 1;
    public static final int TIPO_AUDITOR_INICIAL_ASISTENTE_LEGAL = 2;
    public static final int TIPO_AUDITOR_INICIAL_MEDICO = 3;
    public static final int TIPO_AUDITOR_INICIAL_ENFERMERO = 4;
    public static final int TIPO_AUDITOR_INICIAL_GESTOR    = 5;
    public static final int TIPO_AUDITOR_INICIAL_DIGITADOR = 6;

    private Integer id;
    private String nombre;
    private String descripcion;
    private int orden;
    private boolean activo;
    private int ultimoUsuarioId;
    private TuGrupoEstado grupoEstado;
    private TuGrupoHistorico grupoHistorico;
    private int tipoAuditorInicial;
    private List<TuGrupoEstado> grupoEstados;
    private List<TuGrupoUsuario> grupoUsuarios;
    private String descriptonGroupEstados;
     
    public TuGrupo(){
        
    }

    public TuGrupo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getUltimoUsuarioId() {
        return ultimoUsuarioId;
    }

    public void setUltimoUsuarioId(int ultimoUsuarioId) {
        this.ultimoUsuarioId = ultimoUsuarioId;
    }

    public TuGrupoEstado getGrupoEstado() {
        if(grupoEstado==null){
            grupoEstado = new TuGrupoEstado();
        }
        return grupoEstado;
    }

    public void setGrupoEstado(TuGrupoEstado grupoEstado) {
        this.grupoEstado = grupoEstado;
    }  

    public TuGrupoHistorico getGrupoHistorico() {
        if(grupoHistorico==null){
            grupoHistorico = new TuGrupoHistorico();
        }
        return grupoHistorico;
    }

    public void setGrupoHistorico(TuGrupoHistorico grupoHistorico) {
        this.grupoHistorico = grupoHistorico;
    } 

    public int getTipoAuditorInicial() {
        return tipoAuditorInicial;
    }

    public void setTipoAuditorInicial(int tipoAuditorInicial) {
        this.tipoAuditorInicial = tipoAuditorInicial;
    }

    public List<TuGrupoEstado> getGrupoEstados() {
        if(grupoEstados==null){
            grupoEstados = new ArrayList<>();
        }
        return grupoEstados;
    }

    public void setGrupoEstados(List<TuGrupoEstado> grupoEstados) {
        this.grupoEstados = grupoEstados;
    }

    public List<TuGrupoUsuario> getGrupoUsuarios() {
        return grupoUsuarios;
    }

    public void setGrupoUsuarios(List<TuGrupoUsuario> grupoUsuarios) {
        this.grupoUsuarios = grupoUsuarios;
    }

    public String getDescriptonGroupEstados() {
        return descriptonGroupEstados;
    }

    public void setDescriptonGroupEstados(String descriptonGroupEstados) {
        this.descriptonGroupEstados = descriptonGroupEstados;
    }

//    @Override
//    public String toString() {
//        return "TuGrupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", orden=" + orden + ", activo=" + activo + ", ultimoUsuarioId=" + ultimoUsuarioId + ", grupoEstado=" + getGrupoEstado().toString() +", tipo auditor principal ="+tipoAuditorInicial+ '}';
//    }

    @Override
    public String toString() {
        return "TuGrupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", orden=" + orden + ", activo=" + activo + ", grupoEstados=" + grupoEstados + ", grupoUsuarios=" + grupoUsuarios + '}';
    }

    
}
