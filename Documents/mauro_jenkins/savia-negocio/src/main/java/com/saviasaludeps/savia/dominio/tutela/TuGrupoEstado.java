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
 * @author jperez
 */

public class TuGrupoEstado extends Auditoria {

    private static final long serialVersionUID = 1L;
    
    public final static boolean REPARTO_MANUAL = false; 
    public final static boolean REPARTO_AUTOMATICO = true; 

    private Integer id;
    private int maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private TuGrupo tuGrupo;
    private boolean reparto;
    private List<Integer> selectedEstados;
     
    public TuGrupoEstado() {
    }

    public TuGrupoEstado(Integer id) {
        this.id = id;
    }

    public TuGrupoEstado(Integer id, int maeEstadoId, String maeEstadoCodigo, String maeEstadoValor, boolean reparto ) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.maeEstadoCodigo = maeEstadoCodigo;
        this.maeEstadoValor = maeEstadoValor;
        this.reparto = reparto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
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

    public boolean isReparto() {
        return reparto;
    }

    public void setReparto(boolean reparto) {
        this.reparto = reparto;
    }
    
    public String getRepartoStr() {
        return TuGrupoEstado.getRepartoStr(isReparto());
    }

     public static String getRepartoStr(boolean tipo) {
        if (tipo) {
            return "Automatico";
        }else{
            return "Manual";
        }
    }

    public List<Integer> getSelectedEstados() {
        if(selectedEstados == null){
            selectedEstados = new ArrayList();
        }
        return selectedEstados;
    }

    public void setSelectedEstados(List<Integer> selectedEstados) {
        this.selectedEstados = selectedEstados;
    }   

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuGrupoEstado)) {
            return false;
        }
        TuGrupoEstado other = (TuGrupoEstado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "TuGrupoEstado{" + "id=" + id + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor +", reparto :"+ getRepartoStr()+'}';
//    }

    @Override
    public String toString() {
        return "TuGrupoEstado{" + "id=" + id + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + '}';
    }

   
}
