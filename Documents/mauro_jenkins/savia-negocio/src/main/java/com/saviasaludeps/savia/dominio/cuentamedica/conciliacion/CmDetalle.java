/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author jperezn
 */
public class CmDetalle extends Auditoria {

    private Integer id;
    private Integer asegAfiliadosId;
    private int estado;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String nombreCompletoAfiliado;
    private String codigoMiPress;
    private int maServicioId;
    private String maServicioCodigo;
    private String maServicioValor;
    private BigDecimal valorCopago;
    private BigDecimal valorCopagoItem;
    private BigDecimal valorFacturado;
    private BigDecimal valorPagado;
    private BigDecimal valorPendiente;
    private String observacion;
    private CmFactura cmFacturas;
    private CmGlosa cmGlosas;
    private Integer radicadoGlosa;
    private Integer cantidad;
    private String motivoGlosa;
    private BigDecimal valorAceptadoIps;
    private BigDecimal valorPendienteActual;
    private BigDecimal valorPagadoEPS;
    private BigDecimal porcentajePagadoEPS;
    private BigDecimal valorAceptadoIPS;
    private BigDecimal porcentajeAceptadoIPS;
    private String observacionGlosa;
    private Date fechaPrestacion;
    private List<CmAuditoriaDiagnostico> listaCmAuditoriaDiagnosticos;
    private List<CmAuditoriaCapitaDescuento> listaCmAuditoriaDescuentoCapita;
    private List<CmAuditoriaDevolucion> listaCmAuditoriaDevolucion;
    private List<CmAuditoriaAutorizacion> listacmAuditoriaAutorizacion;
    private List<CmAuditoriaMotivoGlosa> listaCmAuditoriaMotivosGlosa;
    private List<CmAuditoriaAdjunto> listaCmAuditoriaAdjuntos;
    private List<CmAuditoriaConceptoContable> listaCmAuditoriaConceptoContable;
    private List<CmGlosaRespuestaDetalle> listaRespuestaDetalle;
    private ArrayList<Integer> listaConceptosEliminar;
    private ArrayList<Integer> listaAdjuntosEliminar;
    private ArrayList<Integer> listaAutorizacionEliminar;
    private ArrayList<Integer> listaDiagnosticosEliminar;
    private ArrayList<Integer> listaMotivosEliminar;
    private ArrayList<Integer> listaCapitaDescuentoEliminar;
    private int cantidadConceptosContablesAsociados;
    private int cantidadDiagnosticosAsociados;
    private int cantidadCapitaDescuentosAsociados;
    private int cantidadAutorizacionesAsociadas;
    private int cantidadMotivosAsociadas;

    private String observacionRespuestaDetalles;
    
    public static final int ESTADO_DETALLE_REGITRADO        = 0;
    public static final int ESTADO_DETALLE_GLOSADO_PARCIAL  = 1;
    public static final int ESTADO_DETALLE_GLOSADO_TOTAL    = 2;
    public static final int ESTADO_DETALLE_LISTO_PARA_PAGO  = 3;
    
    public static final int TIPO_SERVICIO_MEDICAMENTO = 0;
    public static final int TIPO_SERVICIO_PROCEDIMIENTO = 1;
    public static final int TIPO_SERVICIO_INSUMOS = 2;
    public static final int TIPO_SERVICIO_CONSULTAS = 3;
    
    public static final int TAMANIO_OBSERVACION = 20;
    public static final int TAMANIO_NOMBRE_SERVICIO= 20;
    private Boolean existeObservacionHistorica = false;
    
    private Boolean aplicaAc;
    private Boolean aplicaDc;
    private Boolean aplicaPbs;
    private int tipoServicio;
    private int consecutivoItem;
    private BigDecimal valorUnitario;
    private Boolean copagoNoEfectivo;
    
    private Boolean aplicaGlosa;
    private Boolean aplicaConcepto;
    private Boolean aplicaDx;
    private Boolean aplicaAutorizacion;
    private BigDecimal valorGlosa;
    private String conceptoContable;
    private Short porcentajeConcepto;
    private String numeroAutorizacion;
    private String codigoDx;
    private String nombreDx;
    private boolean regulado;
    private boolean condicionado;
    private Boolean aplicaRecobro;

    public CmDetalle() {
    }

    public CmDetalle(Integer id) {
        this.id = id;
    }
    
    public CmDetalle(Integer id,String documento, String nombreCompletoAfiliado, List<CmAuditoriaConceptoContable> auditoriaConceptoContableList) {
        this.id = id;
        this.documento = documento;
        this.nombreCompletoAfiliado = nombreCompletoAfiliado;
        this.listaCmAuditoriaConceptoContable = auditoriaConceptoContableList;
    }

    public CmDetalle(Integer id, String tipoDocumento, String documento, String nombreCompletoAfiliado, String maServicioCodigo, String maServicioValor, BigDecimal valorTotalDetalle, BigDecimal valorCopago, BigDecimal valorFacturado, BigDecimal valorPagado, BigDecimal valorPendiente, BigDecimal valorAceptadoIps, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, String usuarioModifica, String terminalModifica, Date fechaHoraModifica) {
        this.id = id;
        this.documento = documento;
        this.nombreCompletoAfiliado = nombreCompletoAfiliado;
        this.maServicioCodigo = maServicioCodigo;
        this.maServicioValor= maServicioValor;
        this.valorCopago = valorCopago;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.valorPendiente = valorPendiente;
        this.valorAceptadoIps = valorAceptadoIps;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompletoAfiliado() {
        return nombreCompletoAfiliado;
    }

    public void setNombreCompletoAfiliado(String nombreCompletoAfiliado) {
        this.nombreCompletoAfiliado = nombreCompletoAfiliado;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(BigDecimal valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public String getsetObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
        
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public String getObservacionGlosa() {
        return observacionGlosa;
    }

    public void setObservacionGlosa(String observacionGlosa) {
        this.observacionGlosa = observacionGlosa;
    }

    public Boolean getExisteObservacionHistorica() {
        if(this.getObservacionRespuestaDetalles() != null && 
           this.getObservacionRespuestaDetalles().trim().length() > 1 ){
            existeObservacionHistorica = true;
        }
        return existeObservacionHistorica;
    }

    public void setExisteObservacionHistorica(Boolean existeObservacionHistorica) {
        this.existeObservacionHistorica = existeObservacionHistorica;
    }
    
     public String getNombreServicioCorto() {
        if (maServicioValor != null && maServicioValor.length() >= TAMANIO_NOMBRE_SERVICIO) {
            return maServicioValor.substring(0, TAMANIO_NOMBRE_SERVICIO) + "...   ";
        } else {
            return maServicioValor;
        }
        
    }
   
    public String getObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
        
    }
    
    public String getObservacionGlosaCorto() {
        if (observacionGlosa != null && observacionGlosa.length() >= TAMANIO_OBSERVACION) {
            return observacionGlosa.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacionGlosa;
        }
        
    }

    public String getObservacionDetalleCorto() {
         if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
    }

    public String getObservacionRespuestaDetalles() {
        return observacionRespuestaDetalles;
    }

    public void setObservacionRespuestaDetalles(String observacionRespuestaDetalles) {
        this.observacionRespuestaDetalles = observacionRespuestaDetalles;
    }
    
    public CmFactura getCmFacturas() {
        return cmFacturas;
    }

    public void setCmFacturas(CmFactura cmFacturas) {
        this.cmFacturas = cmFacturas;
    }

    public CmGlosa getCmGlosas() {
        return cmGlosas;
    }

    public void setCmGlosas(CmGlosa cmGlosas) {
        this.cmGlosas = cmGlosas;
    }

    public Integer getRadicadoGlosa() {
        return radicadoGlosa;
    }

    public void setRadicadoGlosa(Integer radicadoGlosa) {
        this.radicadoGlosa = radicadoGlosa;
    }

    public String getMotivoGlosa() {
        return motivoGlosa;
    }

    public void setMotivoGlosa(String motivoGlosa) {
        this.motivoGlosa = motivoGlosa;
    }
    
    public String getMotivoGlosaCorto() {
        if (motivoGlosa != null && motivoGlosa.length() >= TAMANIO_NOMBRE_SERVICIO) {
            return motivoGlosa.substring(0, TAMANIO_NOMBRE_SERVICIO) + "...";
        } else {
            return motivoGlosa;
        }
    }

    public BigDecimal getValorPagadoEPS() {
        return valorPagadoEPS;
    }

    public void setValorPagadoEPS(BigDecimal valorPagadoEPS) {
        this.valorPagadoEPS = valorPagadoEPS;
    }

    public BigDecimal getPorcentajePagadoEPS() {
        return porcentajePagadoEPS;
    }

    public void setPorcentajePagadoEPS(BigDecimal porcentajePagadoEPS) {
        this.porcentajePagadoEPS = porcentajePagadoEPS;
    }

    public BigDecimal getValorAceptadoIPS() {
        return valorAceptadoIPS;
    }

    public void setValorAceptadoIPS(BigDecimal valorAceptadoIPS) {
        this.valorAceptadoIPS = valorAceptadoIPS;
    }

    public BigDecimal getPorcentajeAceptadoIPS() {
        return porcentajeAceptadoIPS;
    }

    public void setPorcentajeAceptadoIPS(BigDecimal porcentajeAceptadoIPS) {
        this.porcentajeAceptadoIPS = porcentajeAceptadoIPS;
    }

    public List<CmAuditoriaDiagnostico> getListaCmAuditoriaDiagnosticos() {
        if(listaCmAuditoriaDiagnosticos == null){
            listaCmAuditoriaDiagnosticos = new ArrayList<>();
        }
        return listaCmAuditoriaDiagnosticos;
    }

    public void setListaCmAuditoriaDiagnosticos(List<CmAuditoriaDiagnostico> listaCmAuditoriaDiagnosticos) {
        this.listaCmAuditoriaDiagnosticos = listaCmAuditoriaDiagnosticos;
    }

    public List<CmAuditoriaCapitaDescuento> getListaCmAuditoriaDescuentoCapita() {
        if(listaCmAuditoriaDescuentoCapita == null){
            listaCmAuditoriaDescuentoCapita = new ArrayList<>();
        }
        return listaCmAuditoriaDescuentoCapita;
    }

    public void setListaCmAuditoriaDescuentoCapita(List<CmAuditoriaCapitaDescuento> listaCmAuditoriaDescuentoCapita) {
        this.listaCmAuditoriaDescuentoCapita = listaCmAuditoriaDescuentoCapita;
    }

    public List<CmAuditoriaDevolucion> getListaCmAuditoriaDevolucion() {
        if(listaCmAuditoriaDevolucion == null){
            listaCmAuditoriaDevolucion = new ArrayList<>();
        }
        return listaCmAuditoriaDevolucion;
    }

    public void setListaCmAuditoriaDevolucion(List<CmAuditoriaDevolucion> listaCmAuditoriaDevolucion) {
        this.listaCmAuditoriaDevolucion = listaCmAuditoriaDevolucion;
    }

    public List<CmAuditoriaAutorizacion> getListacmAuditoriaAutorizacion() {
        if(listacmAuditoriaAutorizacion == null){
            listacmAuditoriaAutorizacion = new ArrayList<>();
        }
        return listacmAuditoriaAutorizacion;
    }

    public void setListacmAuditoriaAutorizacion(List<CmAuditoriaAutorizacion> listacmAuditoriaAutorizacion) {
        this.listacmAuditoriaAutorizacion = listacmAuditoriaAutorizacion;
    }

    public List<CmAuditoriaMotivoGlosa> getListaCmAuditoriaMotivosGlosa() {
        if(listaCmAuditoriaMotivosGlosa == null){
            listaCmAuditoriaMotivosGlosa = new ArrayList<>();
        }
        return listaCmAuditoriaMotivosGlosa;
    }

    public void setListaCmAuditoriaMotivosGlosa(List<CmAuditoriaMotivoGlosa> listaCmAuditoriaMotivosGlosa) {
        this.listaCmAuditoriaMotivosGlosa = listaCmAuditoriaMotivosGlosa;
    }

    public List<CmAuditoriaAdjunto> getListaCmAuditoriaAdjuntos() {
        if(listaCmAuditoriaAdjuntos == null){
            listaCmAuditoriaAdjuntos = new ArrayList<>();
        }
        return listaCmAuditoriaAdjuntos;
    }

    public void setListaCmAuditoriaAdjuntos(List<CmAuditoriaAdjunto> listaCmAuditoriaAdjuntos) {
        this.listaCmAuditoriaAdjuntos = listaCmAuditoriaAdjuntos;
    }

    public List<CmGlosaRespuestaDetalle> getListaRespuestaDetalle() {
        if(listaRespuestaDetalle == null){
            listaRespuestaDetalle = new ArrayList<>();
        }
        return listaRespuestaDetalle;
    }

    public void setListaRespuestaDetalle(List<CmGlosaRespuestaDetalle> listaRespuestaDetalle) {
        this.listaRespuestaDetalle = listaRespuestaDetalle;
    } 

    public List<CmAuditoriaConceptoContable> getListaCmAuditoriaConceptoContable() {
        if(listaCmAuditoriaConceptoContable == null){
            listaCmAuditoriaConceptoContable = new ArrayList<>();
        }
        return listaCmAuditoriaConceptoContable;
    }

    public void setListaCmAuditoriaConceptoContable(List<CmAuditoriaConceptoContable> listaCmAuditoriaConceptoContable) {
        this.listaCmAuditoriaConceptoContable = listaCmAuditoriaConceptoContable;
    }

    public ArrayList<Integer> getListaConceptosEliminar() {
        if(listaConceptosEliminar == null){
            listaConceptosEliminar = new ArrayList<>();
        }
        return listaConceptosEliminar;
    }

    public void setListaConceptosEliminar(ArrayList<Integer> listaConceptosEliminar) {
         if(listaConceptosEliminar == null){
            listaConceptosEliminar = new ArrayList<>();
        }
        this.listaConceptosEliminar = listaConceptosEliminar;
    }

    public ArrayList<Integer> getListaAdjuntosEliminar() {
        if(listaAdjuntosEliminar == null){
            listaAdjuntosEliminar = new ArrayList<>();
        }
        return listaAdjuntosEliminar;
    }

    public void setListaAdjuntosEliminar(ArrayList<Integer> listaAdjuntosEliminar) {
        this.listaAdjuntosEliminar = listaAdjuntosEliminar;
    }

    public ArrayList<Integer> getListaAutorizacionEliminar() {
         if(listaAutorizacionEliminar == null){
            listaAutorizacionEliminar = new ArrayList<>();
        }
        return listaAutorizacionEliminar;
    }

    public void setListaAutorizacionEliminar(ArrayList<Integer> listaAutorizacionEliminar) {
        this.listaAutorizacionEliminar = listaAutorizacionEliminar;
    }

    public ArrayList<Integer> getListaDiagnosticosEliminar() {
        if(listaDiagnosticosEliminar == null){
            listaDiagnosticosEliminar = new ArrayList<>();
        }
        return listaDiagnosticosEliminar;
    }

    public void setListaDiagnosticosEliminar(ArrayList<Integer> listaDiagnosticosEliminar) {
        this.listaDiagnosticosEliminar = listaDiagnosticosEliminar;
    }

    public ArrayList<Integer> getListaMotivosEliminar() {
         if(listaMotivosEliminar == null){
            listaMotivosEliminar = new ArrayList<>();
        }
        return listaMotivosEliminar;
    }

    public void setListaMotivosEliminar(ArrayList<Integer> listaMotivosEliminar) {
        this.listaMotivosEliminar = listaMotivosEliminar;
    }

    public ArrayList<Integer> getListaCapitaDescuentoEliminar() {
        if(listaCapitaDescuentoEliminar == null){
            listaCapitaDescuentoEliminar = new ArrayList<>();
        }
        return listaCapitaDescuentoEliminar;
    }

    public void setListaCapitaDescuentoEliminar(ArrayList<Integer> listaCapitaDescuentoEliminar) {
        this.listaCapitaDescuentoEliminar = listaCapitaDescuentoEliminar;
    }
    
    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public int getMaServicioId() {
        return maServicioId;
    }

    public void setMaServicioId(int maServicioId) {
        this.maServicioId = maServicioId;
    }

    public String getMaServicioCodigo() {
        return maServicioCodigo;
    }

    public void setMaServicioCodigo(String maServicioCodigo) {
        this.maServicioCodigo = maServicioCodigo;
    }

    public String getMaServicioValor() {
        return maServicioValor;
    }

    public void setMaServicioValor(String maServicioValor) {
        this.maServicioValor = maServicioValor;
    }

    public Boolean getAplicaAc() {
        return aplicaAc;
    }
    
    public String getAplicaAcStr() {
        return aplicaAc != null && aplicaAc ? "Si": "";
    }


    public void setAplicaAc(Boolean aplicaAc) {
        this.aplicaAc = aplicaAc;
    }

    public Boolean getAplicaDc() {
        return aplicaDc;
    }
    
     public String getAplicaDcStr() {
        return aplicaDc != null && aplicaDc ? "Si": "";
    }

    public void setAplicaDc(Boolean aplicaDc) {
        this.aplicaDc = aplicaDc;
    }

    public Boolean getAplicaPbs() {
        return aplicaPbs;
    }
    
    public String getAplicaPbsStr() {
        return aplicaPbs != null && aplicaPbs ? "Si": "";
    }

    public void setAplicaPbs(Boolean aplicaPbs) {
        this.aplicaPbs = aplicaPbs;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getConsecutivoItem() {
        return consecutivoItem;
    }

    public void setConsecutivoItem(int consecutivoItem) {
        this.consecutivoItem = consecutivoItem;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadConceptosContablesAsociados() {
        return cantidadConceptosContablesAsociados;
    }

    public void setCantidadConceptosContablesAsociados(int cantidadConceptosContablesAsociados) {
        this.cantidadConceptosContablesAsociados = cantidadConceptosContablesAsociados;
    }

    public int getCantidadCapitaDescuentosAsociados() {
        return cantidadCapitaDescuentosAsociados;
    }

    public void setCantidadCapitaDescuentosAsociados(int cantidadCapitaDescuentosAsociados) {
        this.cantidadCapitaDescuentosAsociados = cantidadCapitaDescuentosAsociados;
    }

    public int getCantidadDiagnosticosAsociados() {
        return cantidadDiagnosticosAsociados;
    }

    public void setCantidadDiagnosticosAsociados(int cantidadDiagnosticosAsociados) {
        this.cantidadDiagnosticosAsociados = cantidadDiagnosticosAsociados;
    }

    public int getCantidadAutorizacionesAsociadas() {
        return cantidadAutorizacionesAsociadas;
    }

    public void setCantidadAutorizacionesAsociadas(int cantidadAutorizacionesAsociadas) {
        this.cantidadAutorizacionesAsociadas = cantidadAutorizacionesAsociadas;
    }

    public int getCantidadMotivosAsociadas() {
        return cantidadMotivosAsociadas;
    }

    public void setCantidadMotivosAsociadas(int cantidadMotivosAsociadas) {
        this.cantidadMotivosAsociadas = cantidadMotivosAsociadas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }
    
    public String getTipoServicioStr() {
        return CmDetalle.getTipoServicioStr(getTipoServicio());
    }

    public Boolean getAplicaGlosa() {
        return aplicaGlosa;
    }

    public void setAplicaGlosa(Boolean aplicaGlosa) {
        this.aplicaGlosa = aplicaGlosa;
    }
    
    public String getAplicaGlosaStr() {
        return aplicaGlosa != null && aplicaGlosa ? "Si": "";
    }

    public Boolean getAplicaConcepto() {
        return aplicaConcepto;
    }

    public void setAplicaConcepto(Boolean aplicaConcepto) {
        this.aplicaConcepto = aplicaConcepto;
    }
    
    public String getAplicaConceptoStr() {
        return aplicaConcepto != null && aplicaConcepto ? "Si": "";
    }

    public Boolean getAplicaDx() {
        return aplicaDx;
    }

    public void setAplicaDx(Boolean aplicaDx) {
        this.aplicaDx = aplicaDx;
    }
    
    public String getAplicaDxStr() {
        return aplicaDx != null && aplicaDx || getCantidadDiagnosticosAsociados() > 0 ? "Si": "";
    }

    public Boolean getAplicaAutorizacion() {
        return aplicaAutorizacion;
    }

    public void setAplicaAutorizacion(Boolean aplicaAutorizacion) {
        this.aplicaAutorizacion = aplicaAutorizacion;
    }
    
    public String getAplicaAutorizacionStr() {
        return aplicaAutorizacion != null && aplicaAutorizacion || getCantidadAutorizacionesAsociadas() > 0 ? "Si": "";
    }

    public BigDecimal getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(BigDecimal valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }
    
     public String getConceptoContableCorto() {
        if (conceptoContable != null && conceptoContable.length() >= TAMANIO_NOMBRE_SERVICIO) {
            return conceptoContable.substring(0, TAMANIO_NOMBRE_SERVICIO) + "...";
        } else {
            return conceptoContable;
        }  
    }

    public Short getPorcentajeConcepto() {
        return porcentajeConcepto;
    }

    public void setPorcentajeConcepto(Short porcentajeConcepto) {
        this.porcentajeConcepto = porcentajeConcepto;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getCodigoDx() {
        return codigoDx;
    }

    public void setCodigoDx(String codigoDx) {
        this.codigoDx = codigoDx;
    }

    public String getNombreDx() {
        return nombreDx;
    }

    public void setNombreDx(String nombreDx) {
        this.nombreDx = nombreDx;
    } 
    
    public String getNombreDxCorto() {
        if (nombreDx != null && nombreDx.length() >= TAMANIO_NOMBRE_SERVICIO) {
            return nombreDx.substring(0, TAMANIO_NOMBRE_SERVICIO) + "...";
        } else {
            return nombreDx;
        }
    }

    public String getCodigoMiPress() {
        return codigoMiPress;
    }

    public void setCodigoMiPress(String codigoMiPress) {
        this.codigoMiPress = codigoMiPress;
    }

    public Integer getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(Integer asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }
    
    public boolean isRegulado() {
        return regulado;
    }

    public void setRegulado(boolean regulado) {
        this.regulado = regulado;
    }
    
    public boolean getRegulado(){
        return regulado;
    }

    public boolean isCondicionado() {
        return condicionado;
    }

    public void setCondicionado(boolean condicionado) {
        this.condicionado = condicionado;
    }
    
    public boolean getCondicionado() {
        return condicionado;
    }

    public Boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(Boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }
    
    public String getAplicaRecobroStr() {
        return aplicaRecobro ? "Si" : "No";
    }
       
    public String getReguladoStr() {
        return regulado? "Si":"No";
    }
    
    public String getCondicionadoStr() {
        return condicionado? "Si":"No";
    }

    public BigDecimal getValorCopagoItem() {
        return valorCopagoItem;
    }

    public void setValorCopagoItem(BigDecimal valorCopagoItem) {
        this.valorCopagoItem = valorCopagoItem;
    }

    public Boolean getCopagoNoEfectivo() {
        return  Optional.ofNullable(copagoNoEfectivo).orElse(false);
    }

    public void setCopagoNoEfectivo(Boolean copagoNoEfectivo) {
        this.copagoNoEfectivo = copagoNoEfectivo;
    }
    
    public String getCopagoNoEfectivoStr() {
        return CmDetalle.getCopagoNoEfectivoStr(getCopagoNoEfectivo());
    }

    public static String getCopagoNoEfectivoStr(boolean copagoNoEfectiro) {
        return copagoNoEfectiro ? "Si" : "No";
    }
    
    public static String getTipoServicioStr(int tipoServicio) {
        String str;
        switch (tipoServicio) {
            case TIPO_SERVICIO_MEDICAMENTO:
                str = "Medicamentos";
                break;
            case TIPO_SERVICIO_PROCEDIMIENTO:
                str = "Procedimiento";
                break;
            case TIPO_SERVICIO_INSUMOS:
                str = "Insumos";
                break;
            case TIPO_SERVICIO_CONSULTAS:
                str = "Consultas";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public String getEstadoStr() {
        return CmDetalle.getEstadoStr(getEstado());
    }

    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_DETALLE_REGITRADO:
                str = "Registrado";
                break;
            case ESTADO_DETALLE_GLOSADO_PARCIAL:
                str = "Glosado parcial";
                break;
            case ESTADO_DETALLE_GLOSADO_TOTAL:
                str = "Glosado total";
                break;
            case ESTADO_DETALLE_LISTO_PARA_PAGO:
                str = "Listo para pago";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public boolean hayValorPagado(){
        return this.getValorPagado() != null && 
               this.getValorPagado().compareTo(new BigDecimal(BigInteger.ZERO)) > 0;
    }
    public boolean hayValorPendiente(){
        return this.getValorPendiente() != null && 
               this.getValorPendiente().compareTo(new BigDecimal(BigInteger.ZERO)) > 0;
    }
    
    public boolean hayPosibleCodigoMiPres(){
        return this.getTipoServicio() == TIPO_SERVICIO_PROCEDIMIENTO ||
               this.getTipoServicio() == TIPO_SERVICIO_INSUMOS;
    }
    
    
     public boolean esTipoInsumo(){
        return this.getTipoServicio() == TIPO_SERVICIO_INSUMOS;
    }
    
    public boolean esTipoProcedimiento(){
        return this.getTipoServicio() == TIPO_SERVICIO_PROCEDIMIENTO;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmDetalle)) {
            return false;
        }
        CmDetalle other = (CmDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmDetalle{" + "id=" + id +  ", documento=" + documento + ", nombreCompletoAfiliado=" + nombreCompletoAfiliado + ", maServicioCodigo =" + maServicioCodigo + ", maServicioValor=" + maServicioValor  + ", valorCopago=" + valorCopago + ", valorFacturado=" + valorFacturado + ", valorPagado=" + valorPagado + ", valorPendiente=" + valorPendiente + ", valorAceptadoIps=" + valorAceptadoIPS + ", observacion=" + observacion + ", cmFacturas=" + cmFacturas + ", cmGlosas=" + cmGlosas + '}';
    }

   
}
