/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.generico;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ripalacios
 */
public class Auditoria implements Serializable {

    protected String usuarioCrea;
    protected String terminalCrea;
    protected Date fechaHoraCrea;

    protected String usuarioModifica;
    protected String terminalModifica;
    protected Date fechaHoraModifica;

    private List<String> messages = new ArrayList();
    private List<String> errors = new ArrayList();

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

    public List<String> getMenssages() {
        return messages;
    }

    public void addMenssge(String mensaje) {
        this.messages.add(mensaje);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public boolean isError() {
        return (!this.errors.isEmpty());
    }

    //Verificar si aplica salto de linea pendiente******
    public String getSaltoLinea(String palabra) {
        String strCss = " ";
        if (palabra != null) {
            if (!(palabra.contains(" "))) {
                strCss = "word-break:break-all";
            }
        }
        return strCss;
    }

    /**
     * Genera el texto de auditoría de un registro
     *
     * @return Texto de auditoría
     */
    public String getAuditoria() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String auditoria = "";
        if (fechaHoraCrea != null) {
            auditoria += "<b>CREACIÓN:</b> Fecha Hora: " + sdf.format(fechaHoraCrea) + " | Usuario: " + usuarioCrea;
            }
        if (fechaHoraModifica != null) {
//            auditoria += "&lt;br /&gt;";
            auditoria += "<br>";
            auditoria += "<b>ÚLTIMA EDICIÓN:</b> Fecha Hora: " + sdf.format(fechaHoraModifica) + " | Usuario: " + usuarioModifica;
        }
        return auditoria;
    }
    
    public String getAuditoriaStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String auditoria = "";
        if (fechaHoraCrea != null) {
            auditoria += "CREACIÓN: Fecha Hora: " + sdf.format(fechaHoraCrea) + " \n Usuario: " + usuarioCrea;
            }
        if (fechaHoraModifica != null) {
            auditoria += "\n";
            auditoria += "ÚLTIMA EDICIÓN: Fecha Hora: " + sdf.format(fechaHoraModifica) + " \n Usuario: " + usuarioModifica;
        }
        return auditoria;
    }
    
    public String getAuditoriaStrHTML() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String auditoria = "";
        if (fechaHoraCrea != null) {
            auditoria += sdf.format(fechaHoraCrea) + "<br>" + usuarioCrea;
            }
        if (fechaHoraModifica != null) {
            auditoria += "&&";
            auditoria += sdf.format(fechaHoraModifica) + "<br>" + usuarioModifica;
        }
        return auditoria;
    }

}
