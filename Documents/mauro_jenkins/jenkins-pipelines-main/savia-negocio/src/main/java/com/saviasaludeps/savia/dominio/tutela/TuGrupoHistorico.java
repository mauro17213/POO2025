/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jperezn
*/
public class TuGrupoHistorico extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String toString;
    private TuGrupo tuGrupo;

    public TuGrupoHistorico() {
    }

    public TuGrupoHistorico(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public TuGrupo getTuGrupo() {
        if(tuGrupo==null){
            tuGrupo = new TuGrupo();
        }
        return tuGrupo;
    }

    public void setTuGrupo(TuGrupo tuGrupo) {
        this.tuGrupo = tuGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuGrupoHistorico)) {
            return false;
        }
        TuGrupoHistorico other = (TuGrupoHistorico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TuGrupoHistorico{" + "id=" + id + ", toString=" + toString + ", tuGrupo=" + getTuGrupo().toString()+ '}';
    }

   
    
}
