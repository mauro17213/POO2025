/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class TuTutelaEstadoRepresentante extends Auditoria{
   
    private Integer id;
    private TuTutelaEstado tuTutelaEstadosId;
    private TuRepresentante tuRepresentantesId;
    private String tuTutelaEstadoRepresentantescol;
   
    public TuTutelaEstadoRepresentante(){
        
    }
    
    public TuTutelaEstadoRepresentante(Integer id){
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TuTutelaEstado getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstado tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    public TuRepresentante getTuRepresentantesId() {
        return tuRepresentantesId;
    }

    public void setTuRepresentantesId(TuRepresentante tuRepresentantesId) {
        this.tuRepresentantesId = tuRepresentantesId;
    }

    public String getTuTutelaEstadoRepresentantescol() {
        return tuTutelaEstadoRepresentantescol;
    }

    public void setTuTutelaEstadoRepresentantescol(String tuTutelaEstadoRepresentantescol) {
        this.tuTutelaEstadoRepresentantescol = tuTutelaEstadoRepresentantescol;
    }

    @Override
    public String toString() {
        return "TuTutelaEstadoRepresentante{" + "id=" + id + ", tuTutelaEstadosId=" + tuTutelaEstadosId + ", tuRepresentantesId=" + tuRepresentantesId + ", tuTutelaEstadoRepresentantescol=" + tuTutelaEstadoRepresentantescol + '}';
    }
    
}
