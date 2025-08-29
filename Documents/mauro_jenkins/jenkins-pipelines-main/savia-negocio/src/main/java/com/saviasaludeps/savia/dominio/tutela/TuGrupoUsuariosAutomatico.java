/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jorge perez
 */

public class TuGrupoUsuariosAutomatico extends Auditoria {
    

    private TuGrupoUsuario tuGrupoUsuarioAbogado;
    private TuGrupoUsuario tuGrupoUsuarioMedico;
    private TuGrupoUsuario tuGrupoUsuarioGestor;
    private TuGrupoUsuario tuGrupoUsuarioDigitaror;
    private TuGrupoUsuario tuGrupoUsuarioAsistenteLegal;
    private TuGrupoUsuario tuGrupoUsuarioEnfermero;
    
    private TuGrupo tuGrupo;
    private int tipo;
    
    private String mensajeOperacion;
    
    private boolean estadoOperacion;
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public TuGrupoUsuariosAutomatico() {
    }

    public void setTuGrupo(TuGrupo tuGrupo) {
        this.tuGrupo = tuGrupo;
    }

    public TuGrupo getTuGrupo() {
        if (tuGrupo == null) {
            tuGrupo = new TuGrupo();
        }
        return tuGrupo;
    }

    public TuGrupoUsuario getTuGrupoUsuarioAbogado() {
        if (tuGrupoUsuarioAbogado == null) {
            tuGrupoUsuarioAbogado = new TuGrupoUsuario();
        }
        return tuGrupoUsuarioAbogado;
    }

    public void setTuGrupoUsuarioAbogado(TuGrupoUsuario tuGrupoUsuarioAbogado) {
        this.tuGrupoUsuarioAbogado = tuGrupoUsuarioAbogado;
    }

    public TuGrupoUsuario getTuGrupoUsuarioMedico() {
        if (tuGrupoUsuarioMedico == null) {
            tuGrupoUsuarioMedico = new TuGrupoUsuario();
        }
        return tuGrupoUsuarioMedico;
    }

    public void setTuGrupoUsuarioMedico(TuGrupoUsuario tuGrupoUsuarioMedico) {
        this.tuGrupoUsuarioMedico = tuGrupoUsuarioMedico;
    }

    public TuGrupoUsuario getTuGrupoUsuarioGestor() {
        if (tuGrupoUsuarioGestor == null) {
            tuGrupoUsuarioGestor = new TuGrupoUsuario();
        }
        return tuGrupoUsuarioGestor;
    }

    public void setTuGrupoUsuarioGestor(TuGrupoUsuario tuGrupoUsuarioGestor) {
        this.tuGrupoUsuarioGestor = tuGrupoUsuarioGestor;
    }

    public TuGrupoUsuario getTuGrupoUsuarioDigitaror() {
        if (tuGrupoUsuarioDigitaror == null) {
            tuGrupoUsuarioDigitaror = new TuGrupoUsuario();
        }
        return tuGrupoUsuarioDigitaror;
    }

    public void setTuGrupoUsuarioDigitaror(TuGrupoUsuario tuGrupoUsuarioDigitaror) {
        this.tuGrupoUsuarioDigitaror = tuGrupoUsuarioDigitaror;
    }

    public TuGrupoUsuario getTuGrupoUsuarioAsistenteLegal() {
        if (tuGrupoUsuarioAsistenteLegal == null) {
            tuGrupoUsuarioAsistenteLegal = new TuGrupoUsuario();
        }
        return tuGrupoUsuarioAsistenteLegal;
    }

    public void setTuGrupoUsuarioAsistenteLegal(TuGrupoUsuario tuGrupoUsuarioAsistenteLegal) {
        this.tuGrupoUsuarioAsistenteLegal = tuGrupoUsuarioAsistenteLegal;
    }

    public TuGrupoUsuario getTuGrupoUsuarioEnfermero() {
         if (tuGrupoUsuarioEnfermero == null) {
            tuGrupoUsuarioEnfermero = new TuGrupoUsuario();
        }
        return tuGrupoUsuarioEnfermero;
    }

    public void setTuGrupoUsuarioEnfermero(TuGrupoUsuario tuGrupoUsuarioEnfermero) {
        this.tuGrupoUsuarioEnfermero = tuGrupoUsuarioEnfermero;
    }

    public String getMensajeOperacion() {
        return mensajeOperacion;
    }

    public void setMensajeOperacion(String mensajeOperacion) {
        this.mensajeOperacion = mensajeOperacion;
    }

    public boolean isEstadoOperacion() {
        return estadoOperacion;
    }
    
    public boolean getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(boolean estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    @Override
    public String toString() {
        return "TuGrupoUsuariosAutomatico{" + "tuGrupoUsuarioAbogado=" + getTuGrupoUsuarioAbogado().getId() + ", tuGrupoUsuarioMedico=" + getTuGrupoUsuarioMedico().getId() + ", tuGrupoUsuarioGestor=" + getTuGrupoUsuarioGestor().getId() +  ", tuGrupo=" + getTuGrupo().getId() + ", tipo=" + tipo + ", mensajeOperacion=" + mensajeOperacion + ", estadoOperacion=" + estadoOperacion + '}';
    }
 
}
