package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class RcoConciliacionGestion extends Auditoria {
    
    //ACUERDOS
    public static final int NO_ACUERDO_RECOBRO = 0;
    public static final int SI_ACUERDO_RECOBRO = 1;
    public static final int NO_CORRESPONDE_RECOBRO = 3;
    
    private Integer id;
    private Integer acuerdoRecobro;
    private String observacion;
    private RcoConciliacion rcoConciliacionesId;
    private RcoFacturaDetalle RcoFacturaDetallesId;

    public RcoConciliacionGestion() {
    }

    public RcoConciliacionGestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcuerdoRecobro() {
        return acuerdoRecobro;
    }

    public void setAcuerdoRecobro(Integer acuerdoRecobro) {
        this.acuerdoRecobro = acuerdoRecobro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public RcoConciliacion getRcoConciliacionesId() {
        return rcoConciliacionesId;
    }

    public void setRcoConciliacionesId(RcoConciliacion rcoConciliacionesId) {
        this.rcoConciliacionesId = rcoConciliacionesId;
    }

    public RcoFacturaDetalle getRcoFacturaDetallesId() {
        return RcoFacturaDetallesId;
    }

    public void setRcoFacturaDetallesId(RcoFacturaDetalle RcoFacturaDetallesId) {
        this.RcoFacturaDetallesId = RcoFacturaDetallesId;
    }
    //Metodos
    public String getAcuerdoRecobroStr(){
        String acuerdoRecobroStr = "";
        if(acuerdoRecobro != null){
            switch (acuerdoRecobro) {
                case NO_ACUERDO_RECOBRO:
                    acuerdoRecobroStr = "No acuerdo recobro";
                    break;
                case SI_ACUERDO_RECOBRO:
                    acuerdoRecobroStr = "Si acuerdo recobro";
                    break;
                case NO_CORRESPONDE_RECOBRO:
                    acuerdoRecobroStr = "No corresponde recobro";
                    break;
              
            }
        }
        return acuerdoRecobroStr;
    }
    
    /*public String getEstadoProcesoStr(){
        String estadoProcesoStr = "No conciliado";
        if(estadoProceso){
            estadoProcesoStr = "Conciliado"; 
        }
       return estadoProcesoStr;
    }*/

    @Override
    public String toString() {
        return "RcoConciliacionGestion{" + "id=" + id + ", acuerdoRecobro=" + acuerdoRecobro + ", observacion=" + observacion + ", rcoConciliacionesId=" + rcoConciliacionesId + ", RcoFacturaDetallesId=" + RcoFacturaDetallesId + '}';
    }
}
