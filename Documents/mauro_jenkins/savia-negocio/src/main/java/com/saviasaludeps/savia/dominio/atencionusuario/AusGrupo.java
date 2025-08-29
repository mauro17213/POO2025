/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jose perez
 */
public class AusGrupo extends Auditoria{
    
    public static final int TIPO_GESTION = 1;
    public static final int TIPO_CIERRE = 2;
    
    public static final int ID_GRUPO_GESTION_MEDICAMENTOS = 1;
    public static final int ID_GRUPO_GESTION_DIA_DIA = 2;
    public static final int ID_GRUPO_CIERRE_DIA_DIA = 3;
    public static final int ID_GRUPO_CIERRE_MEDICAMENTOS = 4;
    
    private Integer id;
    private String nombre;
    private Integer tipo;
    private String descripcion;
    private List<AusGrupoUsuario> listaGrupoUsuarios;
     
    public AusGrupo(){
        
    }

    public AusGrupo(Integer id) {
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

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }
    
    /**
     * @return the tipo
     */
    public String getTipoStr() {
        String mensaje = "";
        switch (this.tipo) {
            case TIPO_GESTION:
                mensaje = "Gestión";
            break;
            case TIPO_CIERRE:
                mensaje = "Cierre";
            break;
        }
        return mensaje;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the listaGrupoUsuarios
     */
    public List<AusGrupoUsuario> getListaGrupoUsuarios() {
        return listaGrupoUsuarios;
    }

    /**
     * @param listaGrupoUsuarios the listaGrupoUsuarios to set
     */
    public void setListaGrupoUsuarios(List<AusGrupoUsuario> listaGrupoUsuarios) {
        this.listaGrupoUsuarios = listaGrupoUsuarios;
    }

    @Override
    public String toString() {
        return "AusGrupo{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", descripcion=" + descripcion + ", listaGrupoUsuarios=" + listaGrupoUsuarios + '}';
    }
    
}
