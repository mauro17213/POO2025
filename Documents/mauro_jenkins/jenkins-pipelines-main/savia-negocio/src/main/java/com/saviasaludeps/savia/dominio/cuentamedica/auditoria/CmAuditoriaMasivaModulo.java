/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class CmAuditoriaMasivaModulo extends Auditoria implements Cloneable {
    
    public final static int TIPO_OPERACION_CIERRE_AUDITORIA_MASIVA = 1;
    public final static int TIPO_OPERACION_DEVOLUCION_AUDITORIA_MASIVA = 2;
    public final static int TIPO_OPERACION_BORRAR_GLOSADO_DEVOLUCION = 3;
    
    private int tipoAuditoria;
    private int tipoOperacionMasiva;
    private int numeroRadicado; 
    private int idCmAuditoriaMasiva;
    private int idCmAuditoriaDevoluciones;
    private int idPrestador;
    private String nombreOperacionMasiva;
    private String observacion;
    private String tipoContrato;
    private String nit;
    private String ips;
    private String codigoRegimen;
    private String regimen;
    private List<Integer> idsFacturas;
    private List<Integer> listaAdjuntosEliminar;
    private List<CmAuditoriaConceptoContable> registrosConceptoContable;
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico;
    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa;
    private List<CmAuditoriaCapitaDescuento> registrosAuditoriaCapitaDescuento;
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private Map<Integer, List<Integer>>listaFacturasPorAdjunto;
    private boolean contratoCapitaPGP;
    private boolean procesamientoPorDetalles;
    private List<Integer> motivosEspecificosSeleccionados = new ArrayList();
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle;
    private boolean version;
    private boolean aplicaRecobro;
    
    public int getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(int tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }

    public Map<Integer, List<Integer>> getListaFacturasPorAdjunto() {
        if (listaFacturasPorAdjunto == null) {
            listaFacturasPorAdjunto = new HashMap<>();
        }
        return listaFacturasPorAdjunto;
    }

    public void setListaFacturasPorAdjunto(Map<Integer, List<Integer>> listaFacturasPorAdjunto) {
        this.listaFacturasPorAdjunto = listaFacturasPorAdjunto;
    }

    public List<Integer> getListaAdjuntosEliminar() {
        if(listaAdjuntosEliminar == null){
            listaAdjuntosEliminar = new ArrayList<>();
        }
        return listaAdjuntosEliminar;
    }

    public void setListaAdjuntosEliminar(List<Integer> listaAdjuntosEliminar) {
        this.listaAdjuntosEliminar = listaAdjuntosEliminar;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getTipoOperacionMasiva() {
        return tipoOperacionMasiva;
    }

    public void setTipoOperacionMasiva(int tipoOperacionMasiva) {
        this.tipoOperacionMasiva = tipoOperacionMasiva;
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getIdOperacionRadicado() {
        return getTipoOperacionMasiva() + "-" + getNumeroRadicado();
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getCodigoRegimen() {
        return codigoRegimen;
    }

    public void setCodigoRegimen(String codigoRegimen) {
        this.codigoRegimen = codigoRegimen;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public List<Integer> getIdsFacturas() {
        if (idsFacturas == null) {
            idsFacturas = new ArrayList<>();
        }
        return idsFacturas;
    }

    public void setIdsFacturas(List<Integer> idsFacturas) {
        this.idsFacturas = idsFacturas;
    }

    public boolean isContratoCapitaPGP() {
        return contratoCapitaPGP;
    }

    public void setContratoCapitaPGP(boolean contratoCapitaPGP) {
        this.contratoCapitaPGP = contratoCapitaPGP;
    }

    public int getIdCmAuditoriaMasiva() {
        return idCmAuditoriaMasiva;
    }

    public void setIdCmAuditoriaMasiva(int idCmAuditoriaMasiva) {
        this.idCmAuditoriaMasiva = idCmAuditoriaMasiva;
    }

    public int getIdCmAuditoriaDevoluciones() {
        return idCmAuditoriaDevoluciones;
    }

    public void setIdCmAuditoriaDevoluciones(int idCmAuditoriaDevoluciones) {
        this.idCmAuditoriaDevoluciones = idCmAuditoriaDevoluciones;
    }
    
    public List<CmAuditoriaConceptoContable> getRegistrosConceptoContable() {
        if (registrosConceptoContable == null) {
            registrosConceptoContable = new ArrayList<>();
        }
        return registrosConceptoContable;
    }

    public void setRegistrosConceptoContable(List<CmAuditoriaConceptoContable> registrosConceptoContable) {
        this.registrosConceptoContable = registrosConceptoContable;
    }
    
    public List<CmAuditoriaDiagnostico> getRegistrosAuditoriaDiagnostico() {
        if (registrosAuditoriaDiagnostico == null) {
            registrosAuditoriaDiagnostico = new ArrayList<>();
        }
        return registrosAuditoriaDiagnostico;
    }

    public void setRegistrosAuditoriaDiagnostico(List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico) {
        this.registrosAuditoriaDiagnostico = registrosAuditoriaDiagnostico;
    }
    
    public List<CmAuditoriaMotivoGlosa> getRegistrosAuditoriaMotivoGlosa() {
        if (registrosAuditoriaMotivoGlosa == null) {
            registrosAuditoriaMotivoGlosa = new ArrayList<>();
        }
        return registrosAuditoriaMotivoGlosa;
    }

    public void setRegistrosAuditoriaMotivoGlosa(List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa) {
        this.registrosAuditoriaMotivoGlosa = registrosAuditoriaMotivoGlosa;
    }
    
    public List<CmAuditoriaCapitaDescuento> getRegistrosAuditoriaCapitaDescuento() {
    if (registrosAuditoriaCapitaDescuento == null) {
            registrosAuditoriaCapitaDescuento = new ArrayList<>();
        }
        return registrosAuditoriaCapitaDescuento;
    }

    public void setRegistrosAuditoriaCapitaDescuento(List<CmAuditoriaCapitaDescuento> registrosAuditoriaCapitaDescuento) {
        this.registrosAuditoriaCapitaDescuento = registrosAuditoriaCapitaDescuento;
    }

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
    }

    public boolean isProcesamientoPorDetalles() {
        return procesamientoPorDetalles;
    }

    public void setProcesamientoPorDetalles(boolean procesamientoPorDetalles) {
        this.procesamientoPorDetalles = procesamientoPorDetalles;
    }

    public String getNombreOperacionMasiva() {
        return nombreOperacionMasiva;
    }

    public void setNombreOperacionMasiva(String nombreOperacionMasiva) {
        this.nombreOperacionMasiva = nombreOperacionMasiva;
    }
    
    public List<Integer> getMotivosEspecificosSeleccionados() {
        return motivosEspecificosSeleccionados;
    }

    public void setMotivosEspecificosSeleccionados(List<Integer> motivosEspecificosSeleccionados) {
        this.motivosEspecificosSeleccionados = motivosEspecificosSeleccionados;
    }
    
    public List<CmAuditoriaAdjunto> getRegistrosAuditoriaAdjutoCmDetalle() {
        if (registrosAuditoriaAdjutoCmDetalle == null) {
            registrosAuditoriaAdjutoCmDetalle = new ArrayList<>();
        }
        return registrosAuditoriaAdjutoCmDetalle;
    }

    public void setRegistrosAuditoriaAdjutoCmDetalle(List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle) {
        this.registrosAuditoriaAdjutoCmDetalle = registrosAuditoriaAdjutoCmDetalle;
    }

    public boolean isVersion() {
        return version;
    }
    
    public boolean getVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public boolean isAplicaRecobro() {
        return aplicaRecobro;
    }
    
    public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }
    
    public static String getTipoOperacionStr(int tipoOperacion) {
        String str;
        switch (tipoOperacion) {
            case CmAuditoriaMasivaModulo.TIPO_OPERACION_CIERRE_AUDITORIA_MASIVA:
                str = "Cierre Auditoria Masiva";
                break;
            case CmAuditoriaMasivaModulo.TIPO_OPERACION_DEVOLUCION_AUDITORIA_MASIVA:
                str = "Devolucion Auditoria Masiva";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    
    @Override
    public Object clone()throws CloneNotSupportedException{  
        CmAuditoriaMasivaModulo clone;
           try {
              clone = (CmAuditoriaMasivaModulo)super.clone(); 
           }
           catch (CloneNotSupportedException e) {
               throw new Error("Error al clonar CmAuditoriaMasiva");
           }
	 return clone;
    }
    

    @Override
    public String toString() {
        return "CmAuditoriaMasiva{" + "tipoAuditoria=" + tipoAuditoria + ", nombre operación masiva: "+ nombreOperacionMasiva+ ",observacion="+observacion+ ",numeroRadicado="+numeroRadicado+'}';
    }
      
}
