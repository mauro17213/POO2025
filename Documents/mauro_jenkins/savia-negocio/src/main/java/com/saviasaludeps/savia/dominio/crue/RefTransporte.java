/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefTransporte extends Auditoria {
    
    private Integer id;
    private Integer maeClaseTransporteId;
    private String maeClaseTransporteCodigo;
    private String maeClaseTransporteValor;
    private Integer maeTipoTransporteId;
    private String maeTipoTransporteCodigo;
    private String maeTipoTransporteValor;
    private Integer maeTransporteLiquidacionId;
    private String maeTransporteLiquidacionCodigo;
    private String maeTransporteLiquidacionValor;
    private String observacion;
    private Date fechaHoraOrigen;
    private Date fechaHoraDestino;
    private List<RefTransporteSeguimiento> listaRefTransporteSeguimiento;
    private List<RefTransporteInsumo> listaRefTransporteInsumo;
    private List<RefTransporteLiquidacion> listaRefTransporteLiquidacion;
    private RefAnexo9 refAnexo9;
    private CntPrestadorSede cntPrestadorSede;

    public RefTransporte() {
    }

    public RefTransporte(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeClaseTransporteId() {
        return maeClaseTransporteId;
    }

    public void setMaeClaseTransporteId(Integer maeClaseTransporteId) {
        this.maeClaseTransporteId = maeClaseTransporteId;
    }

    public Integer getMaeTipoTransporteId() {
        return maeTipoTransporteId;
    }

    public void setMaeTipoTransporteId(Integer maeTipoTransporteId) {
        this.maeTipoTransporteId = maeTipoTransporteId;
    }

    public Integer getMaeTransporteLiquidacionId() {
        return maeTransporteLiquidacionId;
    }

    public void setMaeTransporteLiquidacionId(Integer maeTransporteLiquidacionId) {
        this.maeTransporteLiquidacionId = maeTransporteLiquidacionId;
    }

    public String getMaeTransporteLiquidacionCodigo() {
        return maeTransporteLiquidacionCodigo;
    }

    public void setMaeTransporteLiquidacionCodigo(String maeTransporteLiquidacionCodigo) {
        this.maeTransporteLiquidacionCodigo = maeTransporteLiquidacionCodigo;
    }

    public String getMaeTransporteLiquidacionValor() {
        return maeTransporteLiquidacionValor;
    }

    public void setMaeTransporteLiquidacionValor(String maeTransporteLiquidacionValor) {
        this.maeTransporteLiquidacionValor = maeTransporteLiquidacionValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraOrigen() {
        return fechaHoraOrigen;
    }

    public void setFechaHoraOrigen(Date fechaHoraOrigen) {
        this.fechaHoraOrigen = fechaHoraOrigen;
    }

    public Date getFechaHoraDestino() {
        return fechaHoraDestino;
    }

    public void setFechaHoraDestino(Date fechaHoraDestino) {
        this.fechaHoraDestino = fechaHoraDestino;
    }

    public List<RefTransporteSeguimiento> getListaRefTransporteSeguimiento() {
        return listaRefTransporteSeguimiento;
    }

    public void setListaRefTransporteSeguimiento(List<RefTransporteSeguimiento> listaRefTransporteSeguimiento) {
        this.listaRefTransporteSeguimiento = listaRefTransporteSeguimiento;
    }

    public RefAnexo9 getRefAnexo9() {
        return refAnexo9;
    }

    public void setRefAnexo9(RefAnexo9 refAnexo9) {
        this.refAnexo9 = refAnexo9;
    }

    public String getMaeClaseTransporteCodigo() {
        return maeClaseTransporteCodigo;
    }

    public void setMaeClaseTransporteCodigo(String maeClaseTransporteCodigo) {
        this.maeClaseTransporteCodigo = maeClaseTransporteCodigo;
    }

    public String getMaeClaseTransporteValor() {
        return maeClaseTransporteValor;
    }

    public void setMaeClaseTransporteValor(String maeClaseTransporteValor) {
        this.maeClaseTransporteValor = maeClaseTransporteValor;
    }

    public String getMaeTipoTransporteCodigo() {
        return maeTipoTransporteCodigo;
    }

    public void setMaeTipoTransporteCodigo(String maeTipoTransporteCodigo) {
        this.maeTipoTransporteCodigo = maeTipoTransporteCodigo;
    }

    public String getMaeTipoTransporteValor() {
        return maeTipoTransporteValor;
    }

    public void setMaeTipoTransporteValor(String maeTipoTransporteValor) {
        this.maeTipoTransporteValor = maeTipoTransporteValor;
    }    

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public List<RefTransporteInsumo> getListaRefTransporteInsumo() {
        return listaRefTransporteInsumo;
    }

    public void setListaRefTransporteInsumo(List<RefTransporteInsumo> listaRefTransporteInsumo) {
        this.listaRefTransporteInsumo = listaRefTransporteInsumo;
    }

    public List<RefTransporteLiquidacion> getListaRefTransporteLiquidacion() {
        return listaRefTransporteLiquidacion;
    }

    public void setListaRefTransporteLiquidacion(List<RefTransporteLiquidacion> listaRefTransporteLiquidacion) {
        this.listaRefTransporteLiquidacion = listaRefTransporteLiquidacion;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "RefTransporte{" + "id=" + id + ", maeClaseTransporteId=" + maeClaseTransporteId + ", maeClaseTransporteCodigo=" + maeClaseTransporteCodigo 
                + ", maeClaseTransporteValor=" + maeClaseTransporteValor + ", maeTipoTransporteId=" + maeTipoTransporteId 
                + ", maeTipoTransporteCodigo=" + maeTipoTransporteCodigo + ", maeTipoTransporteValor=" + maeTipoTransporteValor 
                + ", maeTransporteLiquidacionId=" + maeTransporteLiquidacionId + ", maeTransporteLiquidacionCodigo=" + maeTransporteLiquidacionCodigo 
                + ", maeTransporteLiquidacionValor=" + maeTransporteLiquidacionValor  
                + ", observacion=" + observacion + ", fechaHoraOrigen=" + (fechaHoraOrigen != null ? sdf.format(fechaHoraOrigen) : fechaHoraOrigen) 
                + ", fechaHoraDestino=" + (fechaHoraDestino != null ? sdf.format(fechaHoraDestino) : fechaHoraDestino)
                + ", listaRefTransporteSeguimiento=" + listaRefTransporteSeguimiento + ", listaRefTransporteInsumo=" + listaRefTransporteInsumo 
                + ", cntPrestadorSede=" + cntPrestadorSede + '}';
    }
    
}
