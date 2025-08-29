/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author jyperez
 */
public class CntContratoDetalle extends Auditoria implements Cloneable {

    private Integer id;
    private boolean activo;
    private int tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maServicioHabilitacionId;
    private String maServicioHabilitacionCodigo;
    private String maServicioHabilitacionValor;
    private Integer tipoManualTarifario;
    private Integer maManualTarifarioId;
    private String maManualTarifarioCodigo;
    private String maManualTarifarioValor;
    private Integer maManualTarifarioAgno;
    private BigDecimal valorManual;
    private BigDecimal valorContratado;
    private BigDecimal porcentajeVariacion;
    private Integer complejidad;
    private String observacionIncluye;
    private String observacionExcluye;
    private boolean interdependencia;
    private Integer maeAmbitoId;
    private String maeAmbitoCodigo;
    private String maeAmbitoValor;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private boolean automaticoManual;
    private BigDecimal valorMaximoRegulado;
    private CntContratoSede cntContratoSede;
    private CntPrestadorSede cntPrestadorSedesInterdependencia;
    private CntContrato cntContrato;
    //2022-10-05 jyperez nuevos campos
    private boolean automaticoMasivo;
    private boolean automaticoInteroperabilidad;
    private boolean preautorizacion;
    // campos adicionales
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private String maeModalidadContratoCodigo;
    // campos adicionales carga masiva V2
    private Integer tipoManualTarifarioNuevo;
    private Integer maManualTarifarioIdNuevo;
    private String maManualTarifarioCodigoNuevo;
    private String maManualTarifarioValorNuevo;
    private Integer maManualTarifarioAgnoNuevo;
    private Integer maeAmbitoIdNuevo;
    private String maeAmbitoCodigoNuevo;
    private String maeAmbitoValorNuevo;
    
    //Auxiliares
    private List<AuAnexo4Item> listAutorizacionesContratoDetalle;
    private List<AuAnexo4Item> listAutorizacionesContratoDetalleAfiliados;
    private List<AuAnexo4Item> listAutorizacionesFechasPeriodicasContratoDetalle;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (CntContratoDetalle) super.clone();
    }

    public CntContratoDetalle() {
    }

    public CntContratoDetalle(Integer id) {
        this.id = id;
    }

    public CntContratoDetalle(Integer id, boolean activo, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, boolean interdependencia) {
        this.id = id;
        this.activo = activo;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.interdependencia = interdependencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActivo() {
        return isActivo();
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaServicioHabilitacionId() {
        return maServicioHabilitacionId;
    }

    public void setMaServicioHabilitacionId(Integer maServicioHabilitacionId) {
        this.maServicioHabilitacionId = maServicioHabilitacionId;
    }

    public String getMaServicioHabilitacionCodigo() {
        return maServicioHabilitacionCodigo;
    }

    public void setMaServicioHabilitacionCodigo(String maServicioHabilitacionCodigo) {
        this.maServicioHabilitacionCodigo = maServicioHabilitacionCodigo;
    }

    public String getMaServicioHabilitacionValor() {
        return maServicioHabilitacionValor;
    }

    public void setMaServicioHabilitacionValor(String maServicioHabilitacionValor) {
        this.maServicioHabilitacionValor = maServicioHabilitacionValor;
    }

    public Integer getTipoManualTarifario() {
        return tipoManualTarifario;
    }

    public void setTipoManualTarifario(Integer tipoManualTarifario) {
        this.tipoManualTarifario = tipoManualTarifario;
    }

    public Integer getMaManualTarifarioId() {
        return maManualTarifarioId;
    }

    public void setMaManualTarifarioId(Integer maManualTarifarioId) {
        this.maManualTarifarioId = maManualTarifarioId;
    }

    public String getMaManualTarifarioCodigo() {
        return maManualTarifarioCodigo;
    }

    public void setMaManualTarifarioCodigo(String maManualTarifarioCodigo) {
        this.maManualTarifarioCodigo = maManualTarifarioCodigo;
    }

    public String getMaManualTarifarioValor() {
        return maManualTarifarioValor;
    }

    public void setMaManualTarifarioValor(String maManualTarifarioValor) {
        this.maManualTarifarioValor = maManualTarifarioValor;
    }

    /**
     * @return the maManualTarifarioAgno
     */
    public Integer getMaManualTarifarioAgno() {
        return maManualTarifarioAgno;
    }

    /**
     * @param maManualTarifarioAgno the maManualTarifarioAgno to set
     */
    public void setMaManualTarifarioAgno(Integer maManualTarifarioAgno) {
        this.maManualTarifarioAgno = maManualTarifarioAgno;
    }

    public BigDecimal getValorManual() {
        return valorManual;
    }
    
    public String getValorManualStrFrmMoneda() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.'); 
        DecimalFormat formatter = new DecimalFormat("#,###.00",otherSymbols);
        String valor = "";
        valor = "$ " + formatter.format(valorManual);
        return valor;
    }

    public void setValorManual(BigDecimal valorManual) {
        this.valorManual = valorManual;
    }

    public BigDecimal getValorContratado() {
        return valorContratado;
    }
    
    public String getValorContratadoStrFrmMoneda() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.'); 
        DecimalFormat formatter = new DecimalFormat("#,###.00",otherSymbols);
        String valor = "";
        valor = "$ " + formatter.format(valorContratado);
        return valor;
    }

    public void setValorContratado(BigDecimal valorContratado) {
        this.valorContratado = valorContratado;
    }

    public BigDecimal getPorcentajeVariacion() {
        return porcentajeVariacion;
    }

    public void setPorcentajeVariacion(BigDecimal porcentajeVariacion) {
        this.porcentajeVariacion = porcentajeVariacion;
    }

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public String getObservacionIncluye() {
        return observacionIncluye;
    }

    public void setObservacionIncluye(String observacionIncluye) {
        this.observacionIncluye = observacionIncluye;
    }

    public String getObservacionExcluye() {
        return observacionExcluye;
    }

    public void setObservacionExcluye(String observacionExcluye) {
        this.observacionExcluye = observacionExcluye;
    }

    public boolean getInterdependencia() {
        return isInterdependencia();
    }

    public void setInterdependencia(boolean interdependencia) {
        this.interdependencia = interdependencia;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @return the interdependencia
     */
    public boolean isInterdependencia() {
        return interdependencia;
    }

    /**
     * @return the cntContratoSede
     */
    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    /**
     * @param cntContratoSede the cntContratoSede to set
     */
    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }

    /**
     * @return the cntPrestadorSedesInterdependencia
     */
    public CntPrestadorSede getCntPrestadorSedesInterdependencia() {
        return cntPrestadorSedesInterdependencia;
    }

    /**
     * @param cntPrestadorSedesInterdependencia the
     * cntPrestadorSedesInterdependencia to set
     */
    public void setCntPrestadorSedesInterdependencia(CntPrestadorSede cntPrestadorSedesInterdependencia) {
        this.cntPrestadorSedesInterdependencia = cntPrestadorSedesInterdependencia;
    }

    /**
     * @return the cntContratos
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContratos to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    /**
     * @return the maeAmbitoId
     */
    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    /**
     * @param maeAmbitoId the maeAmbitoId to set
     */
    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    /**
     * @return the maeAmbitoCodigo
     */
    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    /**
     * @param maeAmbitoCodigo the maeAmbitoCodigo to set
     */
    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    /**
     * @return the maeAmbitoValor
     */
    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    /**
     * @param maeAmbitoValor the maeAmbitoValor to set
     */
    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    /**
     * @return the fechaHoraInicio
     */
    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * @param fechaHoraInicio the fechaHoraInicio to set
     */
    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * @return the fechaHoraFin
     */
    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    /**
     * @param fechaHoraFin the fechaHoraFin to set
     */
    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    /**
     * @return the automatico
     */
    public boolean isAutomaticoManual() {
        return automaticoManual;
    }

    /**
     * @param automatico the automatico to set
     */
    public void setAutomaticoManual(boolean automatico) {
        this.automaticoManual = automatico;
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    /**
     * @return the maeModalidadContratoCodigo
     */
    public String getMaeModalidadContratoCodigo() {
        return maeModalidadContratoCodigo;
    }

    /**
     * @param maeModalidadContratoCodigo the maeModalidadContratoCodigo to set
     */
    public void setMaeModalidadContratoCodigo(String maeModalidadContratoCodigo) {
        this.maeModalidadContratoCodigo = maeModalidadContratoCodigo;
    }

    /**
     * @return the valorMaximoRegulado
     */
    public BigDecimal getValorMaximoRegulado() {
        return valorMaximoRegulado;
    }
    
    public String getValorMaximoReguladoStrFrmMoneda() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.'); 
        DecimalFormat formatter = new DecimalFormat("$ #,###.00",otherSymbols);
        String valor = "";
        valor = formatter.format(valorMaximoRegulado);
        return valor;
    }

    /**
     * @param valorMaximoRegulado the valorMaximoRegulado to set
     */
    public void setValorMaximoRegulado(BigDecimal valorMaximoRegulado) {
        this.valorMaximoRegulado = valorMaximoRegulado;
    }

    @Override
    public String toString() {
        return "CntContratoDetalle{" + "id=" + id + ", activo=" + activo + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maServicioHabilitacionId=" + maServicioHabilitacionId + ", maServicioHabilitacionCodigo=" + maServicioHabilitacionCodigo + ", maServicioHabilitacionValor=" + maServicioHabilitacionValor + ", tipoManualTarifario=" + tipoManualTarifario + ", maManualTarifarioId=" + maManualTarifarioId + ", maManualTarifarioCodigo=" + maManualTarifarioCodigo + ", maManualTarifarioValor=" + maManualTarifarioValor + ", maManualTarifarioAgno=" + maManualTarifarioAgno + ", valorManual=" + valorManual + ", valorContratado=" + valorContratado + ", porcentajeVariacion=" + porcentajeVariacion + ", complejidad=" + complejidad + ", observacionIncluye=" + observacionIncluye + ", observacionExcluye=" + observacionExcluye + ", interdependencia=" + interdependencia + ", maeAmbitoId=" + maeAmbitoId + ", maeAmbitoCodigo=" + maeAmbitoCodigo + ", maeAmbitoValor=" + maeAmbitoValor + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", automaticoManual=" + automaticoManual + ", valorMaximoRegulado=" + valorMaximoRegulado + '}';
    }

    public int valorBanderaEntero(boolean bandera) {
        if (bandera) {
            return 1;
        }
        return 0;
    }

    public String validarCadenaNula(String cadena) {
        if (cadena == null) {
            return "";
        }
        return cadena;
    }

    public String validarEnteroNulo(Integer entero) {
        if (entero == null) {
            return "";
        }
        return entero + "";
    }

    public String validarBigDecimalNulo(BigDecimal valor) {
        if (valor == null) {
            return "";
        }
        return valor + "";
    }

    public String generarTextoFormatoCargaMasiva(int consecutivo, int actualizar) {
        String mensaje = "";
        mensaje = consecutivo + "," + actualizar + ",";
        if (cntContrato != null) {
            mensaje = mensaje + cntContrato.getContrato() + ",";
        } else {
            mensaje = mensaje + ",";
        }
        if (cntContratoSede != null) {
            if (cntContratoSede.getCntPrestadorSede() != null) {
                mensaje = mensaje + cntContratoSede.getCntPrestadorSede().getCodigoHabilitacionSede() + ",";
            } else {
                mensaje = mensaje + ",";
            }
            mensaje = mensaje + validarCadenaNula(cntContratoSede.getMaeModalidadContratoCodigo()) + ",";
        } else {
            mensaje = mensaje + ",,";
        }
        mensaje = mensaje + fechaHoraInicio + ",";
        mensaje = mensaje + fechaHoraFin + ",";
        mensaje = mensaje + valorBanderaEntero(activo) + ",";
        mensaje = mensaje + tipoTecnologia + ",";
        mensaje = mensaje + maTecnologiaCodigo + ",";
        mensaje = mensaje + validarCadenaNula(maServicioHabilitacionCodigo) + ",";
        mensaje = mensaje + validarEnteroNulo(tipoManualTarifario) + ",";
        mensaje = mensaje + validarCadenaNula(maManualTarifarioCodigo) + ",";
        mensaje = mensaje + validarEnteroNulo(maManualTarifarioAgno) + ",";
        mensaje = mensaje + validarEnteroNulo(tipoManualTarifario) + ",";
        mensaje = mensaje + validarCadenaNula(maManualTarifarioCodigo) + ",";
        mensaje = mensaje + validarEnteroNulo(maManualTarifarioAgno) + ",";
        mensaje = mensaje + validarBigDecimalNulo(valorManual) + ",";
        mensaje = mensaje + validarBigDecimalNulo(porcentajeVariacion) + ",";
        mensaje = mensaje + validarEnteroNulo(complejidad) + ",";
        mensaje = mensaje + validarCadenaNula(observacionIncluye) + ",";
        mensaje = mensaje + validarCadenaNula(observacionExcluye) + ",";
        mensaje = mensaje + valorBanderaEntero(interdependencia) + ",";
        if (interdependencia) {
            if (cntPrestadorSedesInterdependencia != null) {
                mensaje = mensaje + cntPrestadorSedesInterdependencia.getCodigoHabilitacionSede() + ",";
            } else {
                mensaje = mensaje + ",";
            }
        } else {
            mensaje = mensaje + ",";
        }
        mensaje = mensaje + validarCadenaNula(maeAmbitoCodigo) + "," + validarCadenaNula(maeAmbitoCodigo) + "\n";
        return mensaje;
    }

    /**
     * @return the tipoManualTarifarioNuevo
     */
    public Integer getTipoManualTarifarioNuevo() {
        return tipoManualTarifarioNuevo;
    }

    /**
     * @param tipoManualTarifarioNuevo the tipoManualTarifarioNuevo to set
     */
    public void setTipoManualTarifarioNuevo(Integer tipoManualTarifarioNuevo) {
        this.tipoManualTarifarioNuevo = tipoManualTarifarioNuevo;
    }

    /**
     * @return the maManualTarifarioIdNuevo
     */
    public Integer getMaManualTarifarioIdNuevo() {
        return maManualTarifarioIdNuevo;
    }

    /**
     * @param maManualTarifarioIdNuevo the maManualTarifarioIdNuevo to set
     */
    public void setMaManualTarifarioIdNuevo(Integer maManualTarifarioIdNuevo) {
        this.maManualTarifarioIdNuevo = maManualTarifarioIdNuevo;
    }

    /**
     * @return the maManualTarifarioCodigoNuevo
     */
    public String getMaManualTarifarioCodigoNuevo() {
        return maManualTarifarioCodigoNuevo;
    }

    /**
     * @param maManualTarifarioCodigoNuevo the maManualTarifarioCodigoNuevo to
     * set
     */
    public void setMaManualTarifarioCodigoNuevo(String maManualTarifarioCodigoNuevo) {
        this.maManualTarifarioCodigoNuevo = maManualTarifarioCodigoNuevo;
    }

    /**
     * @return the maManualTarifarioValorNuevo
     */
    public String getMaManualTarifarioValorNuevo() {
        return maManualTarifarioValorNuevo;
    }

    /**
     * @param maManualTarifarioValorNuevo the maManualTarifarioValorNuevo to set
     */
    public void setMaManualTarifarioValorNuevo(String maManualTarifarioValorNuevo) {
        this.maManualTarifarioValorNuevo = maManualTarifarioValorNuevo;
    }

    /**
     * @return the maManualTarifarioAgnoNuevo
     */
    public Integer getMaManualTarifarioAgnoNuevo() {
        return maManualTarifarioAgnoNuevo;
    }

    /**
     * @param maManualTarifarioAgnoNuevo the maManualTarifarioAgnoNuevo to set
     */
    public void setMaManualTarifarioAgnoNuevo(Integer maManualTarifarioAgnoNuevo) {
        this.maManualTarifarioAgnoNuevo = maManualTarifarioAgnoNuevo;
    }

    /**
     * @return the maeAmbitoIdNuevo
     */
    public Integer getMaeAmbitoIdNuevo() {
        return maeAmbitoIdNuevo;
    }

    /**
     * @param maeAmbitoIdNuevo the maeAmbitoIdNuevo to set
     */
    public void setMaeAmbitoIdNuevo(Integer maeAmbitoIdNuevo) {
        this.maeAmbitoIdNuevo = maeAmbitoIdNuevo;
    }

    /**
     * @return the maeAmbitoCodigoNuevo
     */
    public String getMaeAmbitoCodigoNuevo() {
        return maeAmbitoCodigoNuevo;
    }

    /**
     * @param maeAmbitoCodigoNuevo the maeAmbitoCodigoNuevo to set
     */
    public void setMaeAmbitoCodigoNuevo(String maeAmbitoCodigoNuevo) {
        this.maeAmbitoCodigoNuevo = maeAmbitoCodigoNuevo;
    }

    /**
     * @return the maeAmbitoValorNuevo
     */
    public String getMaeAmbitoValorNuevo() {
        return maeAmbitoValorNuevo;
    }

    /**
     * @param maeAmbitoValorNuevo the maeAmbitoValorNuevo to set
     */
    public void setMaeAmbitoValorNuevo(String maeAmbitoValorNuevo) {
        this.maeAmbitoValorNuevo = maeAmbitoValorNuevo;
    }

    public List<AuAnexo4Item> getListAutorizacionesContratoDetalle() {
        return listAutorizacionesContratoDetalle;
    }

    public void setListAutorizacionesContratoDetalle(List<AuAnexo4Item> listAutorizacionesContratoDetalle) {
        this.listAutorizacionesContratoDetalle = listAutorizacionesContratoDetalle;
    }

    public List<AuAnexo4Item> getListAutorizacionesContratoDetalleAfiliados() {
        return listAutorizacionesContratoDetalleAfiliados;
    }

    public void setListAutorizacionesContratoDetalleAfiliados(List<AuAnexo4Item> listAutorizacionesContratoDetalleAfiliados) {
        this.listAutorizacionesContratoDetalleAfiliados = listAutorizacionesContratoDetalleAfiliados;
    }

    public List<AuAnexo4Item> getListAutorizacionesFechasPeriodicasContratoDetalle() {
        return listAutorizacionesFechasPeriodicasContratoDetalle;
    }

    public void setListAutorizacionesFechasPeriodicasContratoDetalle(List<AuAnexo4Item> listAutorizacionesFechasPeriodicasContratoDetalle) {
        this.listAutorizacionesFechasPeriodicasContratoDetalle = listAutorizacionesFechasPeriodicasContratoDetalle;
    }

    // 2022-07-28 cmartins - Se comenta toStringJson ya que se utiliza en DTOCntContratoDetalle
//    public String toStringJson() {
//        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
//        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
//        gsonBuilderRespuesta.serializeNulls();
//        Gson gson = gsonBuilderRespuesta.create();
////2022-04-26 (rpalacic)Clonar clase y ajustar datos
//        CntContratoDetalle clonDetalle;
//        try {
//            clonDetalle = (CntContratoDetalle) this.clone();
//            clonDetalle.setCntContrato(null);
//            clonDetalle.setCntContratoSede(null);
//            //clonDetalle.setCntPrestadorSedesInterdependencia(null);
//        } catch (CloneNotSupportedException ex) {
//            clonDetalle = this;
//        }
//        return gson.toJson(clonDetalle);
//    }

    /**
     * @return the automaticoMasivo
     */
    public boolean isAutomaticoMasivo() {
        return automaticoMasivo;
    }

    /**
     * @param automaticoMasivo the automaticoMasivo to set
     */
    public void setAutomaticoMasivo(boolean automaticoMasivo) {
        this.automaticoMasivo = automaticoMasivo;
    }

    /**
     * @return the automaticoInteroperabilidad
     */
    public boolean isAutomaticoInteroperabilidad() {
        return automaticoInteroperabilidad;
    }

    /**
     * @param automaticoInteroperabilidad the automaticoInteroperabilidad to set
     */
    public void setAutomaticoInteroperabilidad(boolean automaticoInteroperabilidad) {
        this.automaticoInteroperabilidad = automaticoInteroperabilidad;
    }

    /**
     * @return the preautorizacion
     */
    public boolean isPreautorizacion() {
        return preautorizacion;
    }

    /**
     * @param preautorizacion the preautorizacion to set
     */
    public void setPreautorizacion(boolean preautorizacion) {
        this.preautorizacion = preautorizacion;
    }

}
