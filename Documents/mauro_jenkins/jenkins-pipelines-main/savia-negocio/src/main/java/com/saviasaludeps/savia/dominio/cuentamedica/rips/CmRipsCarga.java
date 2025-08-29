package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CmRipsCarga extends Auditoria {

    public static final int ESTADO_EN_VALIDACION_ESTRUCTURA = 0;
    public static final int ERROR_VALIDACION_ESTRUCTURA = 1;
    public static final int ESTADO_ESTRUCTURA_VALIDA = 2;
    public static final int ESTADO_VALIDACION_NORMATIVA = 3;
    public static final int ESTADO_VALIDADO = 4;
    public static final int ESTADO_RECHAZADO = 5;
    public static final int ERROR_VALIDACION_NORMATIVA = 6;
    public static final int EN_AUDITORIA = 7;
    public static final int ERROR_ENVIO_AUDITORIA = 8;
    public static final int EN_ENVIO_AUDITORIA = 9;
    public static final int DUPLICADA = 10;
    public static final int EN_COLA = 11;
    public static final int EN_COLA_AUDITORIA = 12;
    public static final int ESTADO_ERROR_VERIFICAR_SINCRONIZACION_EN_DBS = 13;
    public static final int ADJUNTANDO = 12;
    public static final int TABLA_CARGA = 0;
    public static final int TABLA_FINAL = 1;

    private Integer id;
    private Empresa empresa;
    private CntContrato cntContrato;
    private CntContratoSede cntContratoSede;
    private int cntTipoContratoId;
    private BigDecimal valorCarga;
    private int estado;
    private CntPrestadorSede gnPrestadorSede;
    private String estadoStr;
    private String maeRegimenCodigo;
    private Integer maeRegimenId;
    private String maeRegimenValor;
    private String maeContratoModalidadCodigo;
    private Integer maeContratoModalidadId;
    private String maeContratoModalidadValor;
    private String maeRegionalCodigo;
    private Integer maeRegionalId;
    private String maeRegionalValor;
    private Integer maeRechazoId;
    private String maeRechazoCodigo;
    private String maeRechazoValor;
    private String observacionRechazo;
    private Boolean multiUsuario;
    private Date fechaPrestacion;
    private Date anioPrestacion;
    private Integer numeroCuenta;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int tiempo;
    private String usuarioAudita;
    private Boolean pbs;
    private Boolean camaFija;
    private int cantidadFactura;
    private boolean tieneContratosActivos;
    private String contrato;
    private Integer coberturaCierre;

    private List<CmRipsEstructuraError> listaCmRipsEstructuraErrores;
    private List<CmRipsSuceso> listaCmRipsSucesos;
    private List<CmRipsCargaEstado> listaCmRipsCargaEstados;
    private List<CmRipsCargaAnexo> listaCmRipsCargaAnexos;

    private List<CmRipsCtControlObj> listaCmRipsCargaCtControles;
    private List<CmRipsAcConsulta> listaCmRipsCargaAcConsultas;
    private List<CmRipsAdServiciosAgrupado> listaCmRipsCargaAdServiciosAgrupados;
    private List<CmRipsAfFactura> listaCmRipsCargaAfFacturas;
    private List<CmRipsAhHospitalizacion> listaCmRipsCargaAhHospitalizaciones;
    private List<CmRipsAmMedicamento> listaCmRipsCargaAmMedicamentos;
    private List<CmRipsAnRecienNacido> listaCmRipsCargaAnRecienNacidos;
    private List<CmRipsApProcedimiento> listaCmRipsApCargaProcedimientos;
    private List<CmRipsAtOtrosServicio> listaCmRipsCargaAtOtrosServicios;
    private List<CmRipsAuUrgencia> listaCmRipsCargaAuUrgencias;
    private List<CmRipsUsUsuario> listacmRipsCargaUsUsuarios;

    private List<CmRipsCtControlObj> listaCmRipsCtControles;
    private List<CmRipsAcConsulta> listaCmRipsAcConsultas;
    private List<CmRipsAdServiciosAgrupado> listaCmRipsAdServiciosAgrupados;
    private List<CmRipsAnRecienNacido> listaCmRipsAnRecienNacidos;
    private List<CmRipsAhHospitalizacion> listaCmRipsAhHospitalizaciones;
    private List<CmRipsAmMedicamento> listaCmRipsAmMedicamentos;
    private List<CmRipsApProcedimiento> listaCmRipsApProcedimientos;
    private List<CmRipsAuUrgencia> listaCmRipsAuUrgencias;
    private List<CmRipsUsUsuario> listaCmRipsUsUsuarios;
    private List<CmRipsAfFactura> listaCmRipsAfFacturas;
    private List<CmRipsAtOtrosServicio> listaCmRipsAtOtrosServicios;

    private List<CmRipsCtControlObj> listaCmRipsCtControl;

    public CmRipsCarga() {

    }

    public CmRipsCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntContrato getCntContrato() {
        if (cntContrato == null) {
            cntContrato = new CntContrato();
        }
        return cntContrato;
    }

    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    public int getCntTipoContratoId() {
        return cntTipoContratoId;
    }

    public void setCntTipoContratoId(int cntTipoContratoId) {
        this.cntTipoContratoId = cntTipoContratoId;
    }

    public BigDecimal getValorCarga() {
        return valorCarga;
    }

    public void setValorCarga(BigDecimal valorCarga) {
        this.valorCarga = valorCarga;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<CmRipsCtControlObj> getListaCmRipsCargaCtControles() {
        return listaCmRipsCargaCtControles;
    }

    public void setListaCmRipsCargaCtControles(List<CmRipsCtControlObj> listaCmRipsCargaCtControles) {
        this.listaCmRipsCargaCtControles = listaCmRipsCargaCtControles;
    }

    public List<CmRipsUsUsuario> getListacmRipsCargaUsUsuarios() {
        return listacmRipsCargaUsUsuarios;
    }

    public void setListacmRipsCargaUsUsuarios(List<CmRipsUsUsuario> listacmRipsCargaUsUsuarios) {
        this.listacmRipsCargaUsUsuarios = listacmRipsCargaUsUsuarios;
    }

    public List<CmRipsAmMedicamento> getListaCmRipsAmMedicamentos() {
        return listaCmRipsAmMedicamentos;
    }

    public void setListaCmRipsAmMedicamentos(List<CmRipsAmMedicamento> listaCmRipsAmMedicamentos) {
        this.listaCmRipsAmMedicamentos = listaCmRipsAmMedicamentos;
    }

    public List<CmRipsAhHospitalizacion> getListaCmRipsAhHospitalizaciones() {
        return listaCmRipsAhHospitalizaciones;
    }

    public void setListaCmRipsAhHospitalizaciones(List<CmRipsAhHospitalizacion> listaCmRipsAhHospitalizaciones) {
        this.listaCmRipsAhHospitalizaciones = listaCmRipsAhHospitalizaciones;
    }

    public List<CmRipsApProcedimiento> getListaCmRipsApProcedimientos() {
        return listaCmRipsApProcedimientos;
    }

    public void setListaCmRipsApProcedimientos(List<CmRipsApProcedimiento> listaCmRipsApProcedimientos) {
        this.listaCmRipsApProcedimientos = listaCmRipsApProcedimientos;
    }

    public List<CmRipsAuUrgencia> getListaCmRipsAuUrgencias() {
        return listaCmRipsAuUrgencias;
    }

    public void setListaCmRipsAuUrgencias(List<CmRipsAuUrgencia> listaCmRipsAuUrgencias) {
        this.listaCmRipsAuUrgencias = listaCmRipsAuUrgencias;
    }

    public List<CmRipsSuceso> getListaCmRipsSucesos() {
        return listaCmRipsSucesos;
    }

    public void setListaCmRipsSucesos(List<CmRipsSuceso> listaCmRipsSucesos) {
        this.listaCmRipsSucesos = listaCmRipsSucesos;
    }

    public List<CmRipsUsUsuario> getListaCmRipsUsUsuarios() {
        return listaCmRipsUsUsuarios;
    }

    public void setListaCmRipsUsUsuarios(List<CmRipsUsUsuario> listaCmRipsUsUsuarios) {
        this.listaCmRipsUsUsuarios = listaCmRipsUsUsuarios;
    }

    public List<CmRipsCargaEstado> getListaCmRipsCargaEstados() {
        return listaCmRipsCargaEstados;
    }

    public void setListaCmRipsCargaEstados(List<CmRipsCargaEstado> listaCmRipsCargaEstados) {
        this.listaCmRipsCargaEstados = listaCmRipsCargaEstados;
    }

    public List<CmRipsAnRecienNacido> getListaCmRipsCargaAnRecienNacidos() {
        return listaCmRipsCargaAnRecienNacidos;
    }

    public void setListaCmRipsCargaAnRecienNacidos(List<CmRipsAnRecienNacido> listaCmRipsCargaAnRecienNacidos) {
        this.listaCmRipsCargaAnRecienNacidos = listaCmRipsCargaAnRecienNacidos;
    }

    public List<CmRipsCargaAnexo> getListaCmRipsCargaAnexos() {
        return listaCmRipsCargaAnexos;
    }

    public void setListaCmRipsCargaAnexos(List<CmRipsCargaAnexo> listaCmRipsCargaAnexos) {
        this.listaCmRipsCargaAnexos = listaCmRipsCargaAnexos;
    }

    public List<CmRipsAuUrgencia> getListaCmRipsCargaAuUrgencias() {
        return listaCmRipsCargaAuUrgencias;
    }

    public void setListaCmRipsCargaAuUrgencias(List<CmRipsAuUrgencia> listaCmRipsCargaAuUrgencias) {
        this.listaCmRipsCargaAuUrgencias = listaCmRipsCargaAuUrgencias;
    }

    public List<CmRipsApProcedimiento> getListaCmRipsApCargaProcedimientos() {
        return listaCmRipsApCargaProcedimientos;
    }

    public void setListaCmRipsApCargaProcedimientos(List<CmRipsApProcedimiento> listaCmRipsApCargaProcedimientos) {
        this.listaCmRipsApCargaProcedimientos = listaCmRipsApCargaProcedimientos;
    }

    public List<CmRipsAnRecienNacido> getListaCmRipsAnRecienNacidos() {
        return listaCmRipsAnRecienNacidos;
    }

    public void setListaCmRipsAnRecienNacidos(List<CmRipsAnRecienNacido> listaCmRipsAnRecienNacidos) {
        this.listaCmRipsAnRecienNacidos = listaCmRipsAnRecienNacidos;
    }

    public List<CmRipsAfFactura> getListaCmRipsCargaAfFacturas() {
        return listaCmRipsCargaAfFacturas;
    }

    public void setListaCmRipsCargaAfFacturas(List<CmRipsAfFactura> listaCmRipsCargaAfFacturas) {
        this.listaCmRipsCargaAfFacturas = listaCmRipsCargaAfFacturas;
    }

    public List<CmRipsAdServiciosAgrupado> getListaCmRipsCargaAdServiciosAgrupados() {
        return listaCmRipsCargaAdServiciosAgrupados;
    }

    public void setListaCmRipsCargaAdServiciosAgrupados(List<CmRipsAdServiciosAgrupado> listaCmRipsCargaAdServiciosAgrupados) {
        this.listaCmRipsCargaAdServiciosAgrupados = listaCmRipsCargaAdServiciosAgrupados;
    }

    public List<CmRipsEstructuraError> getListaCmRipsEstructuraErrores() {
        return listaCmRipsEstructuraErrores;
    }

    public void setListaCmRipsEstructuraErrores(List<CmRipsEstructuraError> listaCmRipsEstructuraErrores) {
        this.listaCmRipsEstructuraErrores = listaCmRipsEstructuraErrores;
    }

    public List<CmRipsAtOtrosServicio> getListaCmRipsCargaAtOtrosServicios() {
        return listaCmRipsCargaAtOtrosServicios;
    }

    public void setListaCmRipsCargaAtOtrosServicios(List<CmRipsAtOtrosServicio> listaCmRipsCargaAtOtrosServicios) {
        this.listaCmRipsCargaAtOtrosServicios = listaCmRipsCargaAtOtrosServicios;
    }

    public List<CmRipsCtControlObj> getListaCmRipsCtControl() {
        return listaCmRipsCtControl;
    }

    public void setListaCmRipsCtControl(List<CmRipsCtControlObj> listaCmRipsCtControl) {
        this.listaCmRipsCtControl = listaCmRipsCtControl;
    }

    public List<CmRipsAhHospitalizacion> getListaCmRipsCargaAhHospitalizaciones() {
        return listaCmRipsCargaAhHospitalizaciones;
    }

    public void setListaCmRipsCargaAhHospitalizaciones(List<CmRipsAhHospitalizacion> listaCmRipsCargaAhHospitalizaciones) {
        this.listaCmRipsCargaAhHospitalizaciones = listaCmRipsCargaAhHospitalizaciones;
    }

    public List<CmRipsAtOtrosServicio> getListaCmRipsAtOtrosServicios() {
        return listaCmRipsAtOtrosServicios;
    }

    public void setListaCmRipsAtOtrosServicios(List<CmRipsAtOtrosServicio> listaCmRipsAtOtrosServicios) {
        this.listaCmRipsAtOtrosServicios = listaCmRipsAtOtrosServicios;
    }

    public List<CmRipsAcConsulta> getListaCmRipsAcConsultas() {
        return listaCmRipsAcConsultas;
    }

    public void setListaCmRipsAcConsultas(List<CmRipsAcConsulta> listaCmRipsAcConsultas) {
        this.listaCmRipsAcConsultas = listaCmRipsAcConsultas;
    }

    public List<CmRipsAcConsulta> getListaCmRipsCargaAcConsultas() {
        return listaCmRipsCargaAcConsultas;
    }

    public void setListaCmRipsCargaAcConsultas(List<CmRipsAcConsulta> listaCmRipsCargaAcConsultas) {
        this.listaCmRipsCargaAcConsultas = listaCmRipsCargaAcConsultas;
    }

    public List<CmRipsAmMedicamento> getListaCmRipsCargaAmMedicamentos() {
        return listaCmRipsCargaAmMedicamentos;
    }

    public void setListaCmRipsCargaAmMedicamentos(List<CmRipsAmMedicamento> listaCmRipsCargaAmMedicamentos) {
        this.listaCmRipsCargaAmMedicamentos = listaCmRipsCargaAmMedicamentos;
    }

    public List<CmRipsAdServiciosAgrupado> getListaCmRipsAdServiciosAgrupados() {
        return listaCmRipsAdServiciosAgrupados;
    }

    public void setListaCmRipsAdServiciosAgrupados(List<CmRipsAdServiciosAgrupado> listaCmRipsAdServiciosAgrupados) {
        this.listaCmRipsAdServiciosAgrupados = listaCmRipsAdServiciosAgrupados;
    }

    public CntPrestadorSede getGnPrestadorSede() {
        return gnPrestadorSede;
    }

    public void setGnPrestadorSede(CntPrestadorSede gnPrestadorSede) {
        this.gnPrestadorSede = gnPrestadorSede;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public List<CmRipsCtControlObj> getListaCmRipsCtControles() {
        return listaCmRipsCtControles;
    }

    public void setListaCmRipsCtControles(List<CmRipsCtControlObj> listaCmRipsCtControles) {
        this.listaCmRipsCtControles = listaCmRipsCtControles;
    }

    public List<CmRipsAfFactura> getListaCmRipsAfFacturas() {
        return listaCmRipsAfFacturas;
    }

    public void setListaCmRipsAfFacturas(List<CmRipsAfFactura> listaCmRipsAfFacturas) {
        this.listaCmRipsAfFacturas = listaCmRipsAfFacturas;
    }

    public Boolean isMultiUsuario() {
        return multiUsuario;
    }

    public void setMultiUsuario(Boolean multiUsuario) {
        this.multiUsuario = multiUsuario;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public Date getAnioPrestacion() {
        return anioPrestacion;
    }

    public void setAnioPrestacion(Date anioPrestacion) {
        this.anioPrestacion = anioPrestacion;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public Integer getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(Integer maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getMaeRegionalCodigo() {
        return maeRegionalCodigo;
    }

    public void setMaeRegionalCodigo(String maeRegionalCodigo) {
        this.maeRegionalCodigo = maeRegionalCodigo;
    }

    public Integer getMaeRegionalId() {
        return maeRegionalId;
    }

    public void setMaeRegionalId(Integer maeRegionalId) {
        this.maeRegionalId = maeRegionalId;
    }

    public String getMaeRegionalValor() {
        return maeRegionalValor;
    }

    public void setMaeRegionalValor(String maeRegionalValor) {
        this.maeRegionalValor = maeRegionalValor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getMaeRechazoId() {
        return maeRechazoId;
    }

    public void setMaeRechazoId(Integer maeRechazoId) {
        this.maeRechazoId = maeRechazoId;
    }

    public String getMaeRechazoCodigo() {
        return maeRechazoCodigo;
    }

    public void setMaeRechazoCodigo(String maeRechazoCodigo) {
        this.maeRechazoCodigo = maeRechazoCodigo;
    }

    public String getMaeRechazoValor() {
        return maeRechazoValor;
    }

    public void setMaeRechazoValor(String maeRechazoValor) {
        this.maeRechazoValor = maeRechazoValor;
    }

    public String getObservacionRechazo() {
        return observacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        this.observacionRechazo = observacionRechazo;
    }

    public boolean getTieneContratosActivos() {
        return tieneContratosActivos;
    }

    public void setTieneContratosActivos(boolean tieneContratosActivos) {
        this.tieneContratosActivos = tieneContratosActivos;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }
    
    

    public String getEstadoStr() {
        String strEstado;
        switch (this.estado) {
            case CmRipsCarga.ESTADO_EN_VALIDACION_ESTRUCTURA:
                strEstado = "VALIDACIÓN ESTRUCTURA - PROCESO";
                break;
            case CmRipsCarga.ESTADO_ESTRUCTURA_VALIDA:
                strEstado = "VALIDACIÓN ESTRUCTURA - OK";
                break;
            case CmRipsCarga.ERROR_VALIDACION_ESTRUCTURA:
                strEstado = "VALIDACIÓN ESTRUCTURA - ERROR";
                break;
            case CmRipsCarga.ESTADO_VALIDACION_NORMATIVA:
                strEstado = "VALIDACIÓN NORMATIVA - PROCESO";
                break;
            case CmRipsCarga.ERROR_VALIDACION_NORMATIVA:
                strEstado = "VALIDACIÓN NORMATIVA - ERROR";
                break;
            case CmRipsCarga.ESTADO_VALIDADO:
                strEstado = "VALIDACIÓN NORMATIVA -OK";
                break;
            case CmRipsCarga.EN_AUDITORIA:
                strEstado = "ENVÍO AUDITORÍA - OK";
                break;
            case CmRipsCarga.ERROR_ENVIO_AUDITORIA:
                strEstado = "ENVÍO AUDITORÍA - ERROR";
                break;
            case CmRipsCarga.EN_ENVIO_AUDITORIA:
                strEstado = "ENVÍO AUDITORÍA - PROCESO";
                break;
            case CmRipsCarga.DUPLICADA:
                strEstado = "ENVÍO AUDITORÍA - DUPLICADO";
                break;
            case CmRipsCarga.ESTADO_RECHAZADO:
                strEstado = "ENVÍO AUDITORÍA - RECHAZADO";
                break;
            case CmRipsCarga.EN_COLA:
                strEstado = "EN COLA RIPS";
                break;
            case CmRipsCarga.EN_COLA_AUDITORIA:
                strEstado = "EN COLA AUDITORIA";
                break;
            case CmRipsCarga.ESTADO_ERROR_VERIFICAR_SINCRONIZACION_EN_DBS:
                strEstado = "SINCRONIZACION DB - ERROR";
                break;         
            default:
                strEstado = "";
                break;
        }
        return strEstado;
    }

    public void setEstadoStr(String estadoStr) {
        this.estadoStr = estadoStr;
    }

    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }

    public String getUsuarioAudita() {
        return usuarioAudita;
    }

    public void setUsuarioAudita(String usuarioAudita) {
        this.usuarioAudita = usuarioAudita;
    }

    public Boolean getPbs() {
        return pbs;
    }

    public void setPbs(Boolean pbs) {
        this.pbs = pbs;
    }

    public Integer getCoberturaCierre() {
        return getPbs() ? CmRipsInhabilitado.COBERTURA_CIERRE_PBS : 
               CmRipsInhabilitado.COBERTURA_CIERRE_NO_PBS;
    }

    public void setCoberturaCierre(Integer coberturaCierre) {
        this.coberturaCierre = coberturaCierre;
    }
    
    

    public String getPbsStr() {
        return pbs != null && pbs ? "Si" : "No";
    }

    public Boolean getCamaFija() {
        return camaFija;
    }

    public String getCamaFijaStr() {
        return camaFija != null && camaFija ? "Si" : "No";
    }

    public void setCamaFija(Boolean camaFija) {
        this.camaFija = camaFija;
    }

    public int getCantidadFactura() {
        return cantidadFactura;
    }

    public void setCantidadFactura(int cantidadFactura) {
        this.cantidadFactura = cantidadFactura;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    @Override
    public String toString() {
        return "CmRipsCarga{" + "id=" + id + ", valorCarga=" + valorCarga + ", estado=" + estado + ", estadoStr=" + estadoStr + ", maeRegimenCodigo=" + maeRegimenCodigo + ", fechaPrestacion=" + fechaPrestacion + ", numeroCuenta=" + numeroCuenta + '}';
    }
}
