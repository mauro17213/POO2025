/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;


/**
 *
 * @author Stiven Giraldo
 */
public class AusCargaMasivaCaso extends Auditoria {
    
    private AusCaso caso;
    private AusPersona afiliado;
    private AusPersona peticionario;
    private AusCasoServicio servicio;
    private AusSeguimiento seguimiento;
    private AusCargaMasiva ausCargaMasiva;
    private AusAdjunto adjunto;
     
    public AusCargaMasivaCaso() {
        this.caso = new AusCaso();
        this.afiliado = new AusPersona();
        this.peticionario = new AusPersona();
        this.servicio = new AusCasoServicio();
        this.seguimiento = new AusSeguimiento();
    }

    public AusCaso getCaso() {
        return caso;
    }

    public void setCaso(AusCaso caso) {
        this.caso = caso;
    }

    public AusPersona getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(AusPersona afiliado) {
        this.afiliado = afiliado;
    }

    public AusPersona getPeticionario() {
        return peticionario;
    }

    public void setPeticionario(AusPersona peticionario) {
        this.peticionario = peticionario;
    }

    public AusCasoServicio getServicio() {
        return servicio;
    }

    public void setServicio(AusCasoServicio servicio) {
        this.servicio = servicio;
    }

    public AusSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(AusSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public AusCargaMasiva getAusCargaMasiva() {
        if( ausCargaMasiva == null ){
            ausCargaMasiva = new AusCargaMasiva();
        }
        return ausCargaMasiva;
    }

    public void setAusCargaMasiva(AusCargaMasiva ausCargaMasiva) {
        this.ausCargaMasiva = ausCargaMasiva;
    }

    public AusAdjunto getAdjunto() {
        if(adjunto==null){
          adjunto = new AusAdjunto();
        }
        return adjunto;
    }

    public void setAdjunto(AusAdjunto adjunto) {
        this.adjunto = adjunto;
    }

    @Override
    public String toString() {
        return "AusCargaMasivaCaso{" + "ausCargaMasiva=" + ausCargaMasiva.toString() + '}';
    }
  
    
}
