/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GjProcesoHistorico extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Short estado;
    private Date fechaContestacion;
    private Short estadoProceso;
    private Integer maeInstanciaId;
    private String maeInstanciaCodigo;
    private String maeInstanciaValor;
    private Boolean llamamientoGarantia;
    private Integer maeMedicaCautelarId;
    private String maeMedicaCautelarCodigo;
    private String maeMedicaCautelarValor;
    private BigDecimal montoMedida;
    private Short probabilidad;
    private Short riesgoClasificacion;
    private Short claseProvision;
    private Date fechaTerminacion;
    private Short sentidoSentencia;
    private BigDecimal valorSentenciaEjecutoria;
    private Short estadoCumplimientoCondena;
    private Integer maeActuacionTerminacionId;
    private String maeActuacionTerminacionCodigo;
    private String maeActuacionTerminacionValor;
    private BigDecimal valorAcuerdoTransaccion;
    private Date fechaActuacion;
    private byte[] actuacion;
    private String actuacionStr;

    private String tipoEstado;
    private String procesoEstado;

    private String probabilidadTipo;
    private String riegoClasificacionTipo;
    private String claseProvisionTipo;
    private String sentidoSentenciaTipo;
    private String estadoCumplimientoCondenaTipó;

    private List<GjProcesoAdjunto> gjProcesoAdjuntosList;
    private GjProceso gjProcesosId;

    public GjProcesoHistorico() {
    }

    public GjProcesoHistorico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Date getFechaContestacion() {
        return fechaContestacion;
    }

    public void setFechaContestacion(Date fechaContestacion) {
        this.fechaContestacion = fechaContestacion;
    }

    public Short getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Short estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Integer getMaeInstanciaId() {
        return maeInstanciaId;
    }

    public void setMaeInstanciaId(Integer maeInstanciaId) {
        this.maeInstanciaId = maeInstanciaId;
    }

    public String getMaeInstanciaCodigo() {
        return maeInstanciaCodigo;
    }

    public void setMaeInstanciaCodigo(String maeInstanciaCodigo) {
        this.maeInstanciaCodigo = maeInstanciaCodigo;
    }

    public String getMaeInstanciaValor() {
        return maeInstanciaValor;
    }

    public void setMaeInstanciaValor(String maeInstanciaValor) {
        this.maeInstanciaValor = maeInstanciaValor;
    }

    public Boolean getLlamamientoGarantia() {
        return llamamientoGarantia;
    }

    public void setLlamamientoGarantia(Boolean llamamientoGarantia) {
        this.llamamientoGarantia = llamamientoGarantia;
    }

    public Integer getMaeMedicaCautelarId() {
        return maeMedicaCautelarId;
    }

    public void setMaeMedicaCautelarId(Integer maeMedicaCautelarId) {
        this.maeMedicaCautelarId = maeMedicaCautelarId;
    }

    public String getMaeMedicaCautelarCodigo() {
        return maeMedicaCautelarCodigo;
    }

    public void setMaeMedicaCautelarCodigo(String maeMedicaCautelarCodigo) {
        this.maeMedicaCautelarCodigo = maeMedicaCautelarCodigo;
    }

    public String getMaeMedicaCautelarValor() {
        return maeMedicaCautelarValor;
    }

    public void setMaeMedicaCautelarValor(String maeMedicaCautelarValor) {
        this.maeMedicaCautelarValor = maeMedicaCautelarValor;
    }

    public BigDecimal getMontoMedida() {
        return montoMedida;
    }

    public void setMontoMedida(BigDecimal montoMedida) {
        this.montoMedida = montoMedida;
    }

   
    public Short getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Short probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Short getRiesgoClasificacion() {
        return riesgoClasificacion;
    }

    public void setRiesgoClasificacion(Short riesgoClasificacion) {
        this.riesgoClasificacion = riesgoClasificacion;
    }

    public Short getClaseProvision() {
        return claseProvision;
    }

    public void setClaseProvision(Short claseProvision) {
        this.claseProvision = claseProvision;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Short getSentidoSentencia() {
        return sentidoSentencia;
    }

    public void setSentidoSentencia(Short sentidoSentencia) {
        this.sentidoSentencia = sentidoSentencia;
    }

    public BigDecimal getValorSentenciaEjecutoria() {
        return valorSentenciaEjecutoria;
    }

    public void setValorSentenciaEjecutoria(BigDecimal valorSentenciaEjecutoria) {
        this.valorSentenciaEjecutoria = valorSentenciaEjecutoria;
    }
   
    public Short getEstadoCumplimientoCondena() {
        return estadoCumplimientoCondena;
    }

    public void setEstadoCumplimientoCondena(Short estadoCumplimientoCondena) {
        this.estadoCumplimientoCondena = estadoCumplimientoCondena;
    }

    public Integer getMaeActuacionTerminacionId() {
        return maeActuacionTerminacionId;
    }

    public void setMaeActuacionTerminacionId(Integer maeActuacionTerminacionId) {
        this.maeActuacionTerminacionId = maeActuacionTerminacionId;
    }

    public String getMaeActuacionTerminacionCodigo() {
        return maeActuacionTerminacionCodigo;
    }

    public void setMaeActuacionTerminacionCodigo(String maeActuacionTerminacionCodigo) {
        this.maeActuacionTerminacionCodigo = maeActuacionTerminacionCodigo;
    }

    public String getMaeActuacionTerminacionValor() {
        return maeActuacionTerminacionValor;
    }

    public void setMaeActuacionTerminacionValor(String maeActuacionTerminacionValor) {
        this.maeActuacionTerminacionValor = maeActuacionTerminacionValor;
    }

    public BigDecimal getValorAcuerdoTransaccion() {
        return valorAcuerdoTransaccion;
    }

    public void setValorAcuerdoTransaccion(BigDecimal valorAcuerdoTransaccion) {
        this.valorAcuerdoTransaccion = valorAcuerdoTransaccion;
    }
    
    public Date getFechaActuacion() {
        return fechaActuacion;
    }

    public void setFechaActuacion(Date fechaActuacion) {
        this.fechaActuacion = fechaActuacion;
    }

    public byte[] getActuacion() {
        return actuacion;
    }

    public void setActuacion(byte[] actuacion) {
        this.actuacion = actuacion;
    }

    public String getActuacionStr() {
    if (getActuacion() == null) {
        return "";
    }
    actuacionStr = (new String(getActuacion()));
    return actuacionStr;
}


    public void setActuacionStr(String actuacionStr) {
        this.actuacionStr = actuacionStr;
    }

    public List<GjProcesoAdjunto> getGjProcesoAdjuntosList() {
        return gjProcesoAdjuntosList;
    }

    public void setGjProcesoAdjuntosList(List<GjProcesoAdjunto> gjProcesoAdjuntosList) {
        this.gjProcesoAdjuntosList = gjProcesoAdjuntosList;
    }

    public GjProceso getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProceso gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    public String getProcesoEstado() {

        if (getEstadoProceso() > 0) {
            switch (this.getEstadoProceso()) {
                case 1:
                    procesoEstado = "Admisión De La Demanda";
                    break;
                case 2:
                    procesoEstado = "Audiencia Preparatoria";
                    break;
                case 3:
                    procesoEstado = "Excepciones Previas";
                    break;
                case 4:
                    procesoEstado = "Audiencia De Conciliación";
                    break;
                case 5:
                    procesoEstado = "Etapa Probatoria";
                    break;
                case 6:
                    procesoEstado = "Alegatos";
                    break;
                case 7:
                    procesoEstado = "Sentancia";
                    break;
                case 8:
                    procesoEstado = "Impugnación";
                    break;
                case 9:
                    procesoEstado = "Reposición";
                    break;
                case 10:
                    procesoEstado = "Apelación";
                    break;
                case 11:
                    procesoEstado = "Queja";
                    break;
                case 12:
                    procesoEstado = "Casación";
                    break;
                case 13:
                    procesoEstado = "Anulación";
                    break;
                case 14:
                    procesoEstado = "Revisión";
                    break;
                case 15:
                    procesoEstado = "Archivo";
                    break;
            }
        } else {
            procesoEstado = "";
        }
        return procesoEstado;
    }

    public void setProcesoEstado(String ProcesoEstado) {
        this.procesoEstado = ProcesoEstado;
    }

    public String getTipoEstado() {
        if (getEstado() != null) {
            switch (this.getEstado()) {
                case 0:
                    tipoEstado = "Radicado Sin Admisión";
                    break;
                case 1:
                    tipoEstado = "Activo";

                    break;
                case 2:
                    tipoEstado = "Inadmitido";
                    break;
                case 3:
                    tipoEstado = "Rechazado";
                    break;
                case 4:
                    tipoEstado = "Terminado";
                    break;
                case 5:
                    tipoEstado = "Anulado";
                    break;
            }
        } else{
            tipoEstado = " ";
        }
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public String getProbabilidadTipo() {
        if (getProbabilidad() != null) {
            switch (this.getProbabilidad()) {
                case 1:
                    probabilidadTipo = "Alto";
                    break;
                case 2:
                    probabilidadTipo = "Medio Alto";
                    break;
                case 3:
                    probabilidadTipo = "Medio Bajo";
                    break;
                case 4:
                    probabilidadTipo = "Bajo";
                    break;

            }
        } else {
            probabilidadTipo = "";
        }
        return probabilidadTipo;
    }

    public void setProbabilidadTipo(String probabilidadTipo) {
        this.probabilidadTipo = probabilidadTipo;
    }

    public String getRiegoClasificacionTipo() {
        if (getRiesgoClasificacion() != null) {
            switch (this.getRiesgoClasificacion()) {

                case 1:
                    riegoClasificacionTipo = "Alto";

                    break;
                case 2:
                    riegoClasificacionTipo = "Medio";
                    break;
                case 3:
                    riegoClasificacionTipo = "Bajo";
                    break;
                case 4:
                    riegoClasificacionTipo = "Remoto";
                    break;
                case 5:
                    riegoClasificacionTipo = "Por Definir";
                    break;
            }
        } else {
            riegoClasificacionTipo = "";
        }
        return riegoClasificacionTipo;
    }

    public void setRiegoClasificacionTipo(String riegoClasificacionTipo) {
        this.riegoClasificacionTipo = riegoClasificacionTipo;
    }

    public String getClaseProvisionTipo() {
        if (getClaseProvision() != null) {
            switch (this.getClaseProvision()) {

                case 1:
                    claseProvisionTipo = "Provision contable";
                    break;
                case 2:
                    claseProvisionTipo = " Cuentas de orden";
                    break;
                case 3:
                    claseProvisionTipo = "Sin provision";
                    break;
                case 4:
                    claseProvisionTipo = "Por definir";
                    break;

            }
        } else {
            claseProvisionTipo = "";
        }
        return claseProvisionTipo;
    }

    public void setClaseProvisionTipo(String claseProvisionTipo) {
        this.claseProvisionTipo = claseProvisionTipo;
    }

    public String getSentidoSentenciaTipo() {
        if (getSentidoSentencia() != null) {
            switch (this.getSentidoSentencia()) {
                case 0:
                    sentidoSentenciaTipo = "Desfavorable";
                    break;
                case 1:
                    sentidoSentenciaTipo = "Favorable";
                    break;
            }
        } else {
            sentidoSentenciaTipo = "";
        }
        return sentidoSentenciaTipo;
    }

    public void setSentidoSentenciaTipo(String sentidoSentenciaTipo) {
        this.sentidoSentenciaTipo = sentidoSentenciaTipo;
    }

    public String getEstadoCumplimientoCondenaTipó() {
        if (getEstadoCumplimientoCondena() != null) {
            switch (this.getEstadoCumplimientoCondena()) {

                case 0:
                    estadoCumplimientoCondenaTipó = "N/A";
                    break;
                case 1:
                    estadoCumplimientoCondenaTipó = "Cumplido";
                    break;
                case 2:
                    estadoCumplimientoCondenaTipó = "Pendiente";
                    break;

            }
        } else {
            estadoCumplimientoCondenaTipó = "";
        }
        return estadoCumplimientoCondenaTipó;
    }

    public void setEstadoCumplimientoCondenaTipó(String estadoCumplimientoCondenaTipó) {
        this.estadoCumplimientoCondenaTipó = estadoCumplimientoCondenaTipó;
    }

    @Override
    public String toString() {
        return "GjProcesoHistorico{" + "id=" + id + ", estado=" + estado + ", fechaContestacion=" + fechaContestacion + ", estadoProceso=" + estadoProceso + ", maeInstanciaId=" + maeInstanciaId + ", maeInstanciaCodigo=" + maeInstanciaCodigo + ", maeInstanciaValor=" + maeInstanciaValor + ", llamamientoGarantia=" + llamamientoGarantia + ", maeMedicaCautelarId=" + maeMedicaCautelarId + ", maeMedicaCautelarCodigo=" + maeMedicaCautelarCodigo + ", maeMedicaCautelarValor=" + maeMedicaCautelarValor + ", montoMedida=" + montoMedida + ", probabilidad=" + probabilidad + ", riesgoClasificacion=" + riesgoClasificacion + ", claseProvision=" + claseProvision + ", fechaTerminacion=" + fechaTerminacion + ", sentidoSentencia=" + sentidoSentencia + ", valorSentenciaEjecutoria=" + valorSentenciaEjecutoria + ", estadoCumplimientoCondena=" + estadoCumplimientoCondena + ", maeActuacionTerminacionId=" + maeActuacionTerminacionId + ", maeActuacionTerminacionCodigo=" + maeActuacionTerminacionCodigo + ", maeActuacionTerminacionValor=" + maeActuacionTerminacionValor + ", valorAcuerdoTransaccion=" + valorAcuerdoTransaccion + ", fechaActuacion=" + fechaActuacion + ", actuacion=" + actuacion + ", gjProcesoAdjuntosList=" + gjProcesoAdjuntosList + ", gjProcesosId=" + gjProcesosId + '}';
    }

}
