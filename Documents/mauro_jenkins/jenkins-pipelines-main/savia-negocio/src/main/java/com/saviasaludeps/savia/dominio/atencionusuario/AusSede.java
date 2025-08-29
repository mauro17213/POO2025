/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusSede extends Auditoria{
    private Integer id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String usuarioCrea;
    private String terminalCrea;
    private Date fechaHoraCrea;
    private String usuarioModifica;
    private String terminalModifica;
    private Date fechaHoraModifica;
    private Empresa empresasId;
    private Ubicacion ubicacionesId;
    private List<AusCaso> ausCasosList;

    public AusSede() {
    }

    public AusSede(Integer id) {
        this.id = id;
    }

    public AusSede(Integer id, String nombre, String descripcion, Ubicacion ubicacionesId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacionesId = ubicacionesId;
    }
    
    public AusSede(Integer id, String nombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public Empresa getEmpresasId() {
        return empresasId;
    }

    public void setEmpresasId(Empresa empresasId) {
        this.empresasId = empresasId;
    }

    public Ubicacion getUbicacionesId() {
        return ubicacionesId;
    }

    public void setUbicacionesId(Ubicacion ubicacionesId) {
        this.ubicacionesId = ubicacionesId;
    }

    public List<AusCaso> getAusCasosList() {
        return ausCasosList;
    }

    public void setAusCasosList(List<AusCaso> ausCasosList) {
        this.ausCasosList = ausCasosList;
    }
    
    
}
