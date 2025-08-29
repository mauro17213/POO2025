/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public class AuSeguimiento extends Auditoria {

    public static final String ESTADO_PENDIENTE = "1";
    public static final String ESTADO_EN_GESTION = "2";
    public static final String ESTADO_ASIGNADO_PRESTADOR = "3";
    public static final String ESTADO_ACEPTADO_PRESTADOR = "4";
    public static final String ESTADO_RECHAZADO_PRESTADOR = "5";
    public static final String ESTADO_AUTORIZADO = "6";
    public static final String ESTADO_ENTREGADO = "7";
    public static final String ESTADO_GESTIONADO = "8";
    public static final String ESTADO_CANCELADO = "9";

    private Integer id;
    private Integer estadoTecnologia;
    private Integer maeEstadoSeguimientoId;
    private String maeEstadoSeguimientoCodigo;
    private String maeEstadoSeguimientoValor;
    private int tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private Integer maeAcompananteTipoDocumentoId;
    private String maeAcompananteTipoDocumentoCodigo;
    private String maeAcompananteTipoDocumentoValor;
    private String acompananteDocumento;
    private String acompanantePrimerNombre;
    private String acompananteSegundoNombre;
    private String acompanantePrimerApellido;
    private String acompananteSegundoApellido;
    private String acompananteTelefono;
    private String acompananteDireccionResidencia;
    private String acompananteBarrioResidencia;
    private Integer maeAmbitoAtencionId;
    private String maeAmbitoAtencionCodigo;
    private String maeAmbitoAtencionValor;
    private Boolean aplicaPrestador;
    //2023-12-07 jyperez nuevos campos REQ Filtros y semaforización seguimiento y prestadores.
    private Date fechaHoraPrimeraGestion;
    private Date fechaHoraUltimaGestion;
    private Date fechaHoraAtiende;
    private Date fechaHoraAsignaPrestador;
    private List<AuSolicitudAdjunto> auSolicitudAdjuntosList;
    private AuAnexo3Item auAnexo3ItemsId;
    private AuAnexo3 auAnexos3Id;
    private AuAnexo4 auAnexos4Id;
    private AuGrupo auGruposId;
    private CntPrestadorSede cntPrestadorSedeAsignadoId;
    private Ubicacion gnAcompananteResidenciaUbicacionId;
    private Empresa gnEmpresasId;
    private List<AuSeguimientoServicio> auSeguimientoServicioList;
    private List<AuSeguimientoGestion> auSeguimientoGestionList;
    private AuSeguimientoAfiliado auSeguimientoAfiliadosId;
    private List<AuSeguimientoPrestadorAsignado> auSeguimientoPrestadorAsignadoList;

    public AuSeguimiento() {
    }

    public AuSeguimiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstadoTecnologia() {
        return estadoTecnologia;
    }

    public void setEstadoTecnologia(Integer estadoTecnologia) {
        this.estadoTecnologia = estadoTecnologia;
    }

    public Integer getMaeEstadoSeguimientoId() {
        return maeEstadoSeguimientoId;
    }

    public void setMaeEstadoSeguimientoId(Integer maeEstadoSeguimientoId) {
        this.maeEstadoSeguimientoId = maeEstadoSeguimientoId;
    }

    public String getMaeEstadoSeguimientoCodigo() {
        return maeEstadoSeguimientoCodigo;
    }

    public void setMaeEstadoSeguimientoCodigo(String maeEstadoSeguimientoCodigo) {
        this.maeEstadoSeguimientoCodigo = maeEstadoSeguimientoCodigo;
    }

    public String getMaeEstadoSeguimientoValor() {
        return maeEstadoSeguimientoValor;
    }

    public void setMaeEstadoSeguimientoValor(String maeEstadoSeguimientoValor) {
        this.maeEstadoSeguimientoValor = maeEstadoSeguimientoValor;
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

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    public Integer getMaeAcompananteTipoDocumentoId() {
        return maeAcompananteTipoDocumentoId;
    }

    public void setMaeAcompananteTipoDocumentoId(Integer maeAcompananteTipoDocumentoId) {
        this.maeAcompananteTipoDocumentoId = maeAcompananteTipoDocumentoId;
    }

    public String getMaeAcompananteTipoDocumentoCodigo() {
        return maeAcompananteTipoDocumentoCodigo;
    }

    public void setMaeAcompananteTipoDocumentoCodigo(String maeAcompananteTipoDocumentoCodigo) {
        this.maeAcompananteTipoDocumentoCodigo = maeAcompananteTipoDocumentoCodigo;
    }

    public String getMaeAcompananteTipoDocumentoValor() {
        return maeAcompananteTipoDocumentoValor;
    }

    public void setMaeAcompananteTipoDocumentoValor(String maeAcompananteTipoDocumentoValor) {
        this.maeAcompananteTipoDocumentoValor = maeAcompananteTipoDocumentoValor;
    }

    public String getAcompananteDocumento() {
        return acompananteDocumento;
    }

    public void setAcompananteDocumento(String acompananteDocumento) {
        this.acompananteDocumento = acompananteDocumento;
    }

    public String getAcompanantePrimerNombre() {
        return acompanantePrimerNombre;
    }

    public void setAcompanantePrimerNombre(String acompanantePrimerNombre) {
        this.acompanantePrimerNombre = acompanantePrimerNombre;
    }

    public String getAcompananteSegundoNombre() {
        return acompananteSegundoNombre;
    }

    public void setAcompananteSegundoNombre(String acompananteSegundoNombre) {
        this.acompananteSegundoNombre = acompananteSegundoNombre;
    }

    public String getAcompanantePrimerApellido() {
        return acompanantePrimerApellido;
    }

    public void setAcompanantePrimerApellido(String acompanantePrimerApellido) {
        this.acompanantePrimerApellido = acompanantePrimerApellido;
    }

    public String getAcompananteSegundoApellido() {
        return acompananteSegundoApellido;
    }

    public void setAcompananteSegundoApellido(String acompananteSegundoApellido) {
        this.acompananteSegundoApellido = acompananteSegundoApellido;
    }

    public String getAcompananteTelefono() {
        return acompananteTelefono;
    }

    public void setAcompananteTelefono(String acompananteTelefono) {
        this.acompananteTelefono = acompananteTelefono;
    }

    public String getAcompananteDireccionResidencia() {
        return acompananteDireccionResidencia;
    }

    public void setAcompananteDireccionResidencia(String acompananteDireccionResidencia) {
        this.acompananteDireccionResidencia = acompananteDireccionResidencia;
    }

    public String getAcompananteBarrioResidencia() {
        return acompananteBarrioResidencia;
    }

    public void setAcompananteBarrioResidencia(String acompananteBarrioResidencia) {
        this.acompananteBarrioResidencia = acompananteBarrioResidencia;
    }

    public Integer getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(Integer maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public String getMaeAmbitoAtencionValor() {
        return maeAmbitoAtencionValor;
    }

    public void setMaeAmbitoAtencionValor(String maeAmbitoAtencionValor) {
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
    }

    public Boolean getAplicaPrestador() {
        return aplicaPrestador;
    }

    public void setAplicaPrestador(Boolean aplicaPrestador) {
        this.aplicaPrestador = aplicaPrestador;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjunto> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public List<AuSeguimientoServicio> getAuSeguimientoServicioList() {
        return auSeguimientoServicioList;
    }

    public void setAuSeguimientoServicioList(List<AuSeguimientoServicio> auSeguimientoServicioList) {
        this.auSeguimientoServicioList = auSeguimientoServicioList;
    }

    public List<AuSeguimientoGestion> getAuSeguimientoGestionList() {
        return auSeguimientoGestionList;
    }

    public void setAuSeguimientoGestionList(List<AuSeguimientoGestion> auSeguimientoGestionList) {
        this.auSeguimientoGestionList = auSeguimientoGestionList;
    }

    public List<AuSeguimientoPrestadorAsignado> getAuSeguimientoPrestadorAsignadoList() {
        return auSeguimientoPrestadorAsignadoList;
    }

    public void setAuSeguimientoPrestadorAsignadoList(List<AuSeguimientoPrestadorAsignado> auSeguimientoPrestadorAsignadoList) {
        this.auSeguimientoPrestadorAsignadoList = auSeguimientoPrestadorAsignadoList;
    }

    public AuAnexo3Item getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Item auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
    }

    public AuAnexo3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexo3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AuAnexo4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexo4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public AuGrupo getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupo auGruposId) {
        this.auGruposId = auGruposId;
    }

    public CntPrestadorSede getCntPrestadorSedeAsignadoId() {
        return cntPrestadorSedeAsignadoId;
    }

    public void setCntPrestadorSedeAsignadoId(CntPrestadorSede cntPrestadorSedeAsignadoId) {
        this.cntPrestadorSedeAsignadoId = cntPrestadorSedeAsignadoId;
    }

    public Ubicacion getGnAcompananteResidenciaUbicacionId() {
        return gnAcompananteResidenciaUbicacionId;
    }

    public void setGnAcompananteResidenciaUbicacionId(Ubicacion gnAcompananteResidenciaUbicacionId) {
        this.gnAcompananteResidenciaUbicacionId = gnAcompananteResidenciaUbicacionId;
    }

    public Empresa getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Empresa gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public AuSeguimientoAfiliado getAuSeguimientoAfiliadosId() {
        return auSeguimientoAfiliadosId;
    }

    public void setAuSeguimientoAfiliadosId(AuSeguimientoAfiliado auSeguimientoAfiliadosId) {
        this.auSeguimientoAfiliadosId = auSeguimientoAfiliadosId;
    }

    public boolean validarEstadoGestion() {
        switch (maeEstadoSeguimientoCodigo) {
            case ESTADO_GESTIONADO:
                return true;
            case ESTADO_ASIGNADO_PRESTADOR:
                return true;
            case ESTADO_ACEPTADO_PRESTADOR:
                return true;
            case ESTADO_AUTORIZADO:
                return true;
            case ESTADO_ENTREGADO:
                return true;
            default:
                return false;
        }
    }

    public boolean validarEstadoGestionar() {
        switch (maeEstadoSeguimientoCodigo) {
            case ESTADO_PENDIENTE:
                return true;
            case ESTADO_EN_GESTION:
                return true;
            case ESTADO_GESTIONADO:
                return true;
            case ESTADO_ASIGNADO_PRESTADOR:
                return true;
            case ESTADO_ACEPTADO_PRESTADOR:
                return true;
            case ESTADO_RECHAZADO_PRESTADOR:
                return true;
            case ESTADO_AUTORIZADO:
                return true;
            default:
                return false;
        }
    }

    public boolean validarEstadoCancelar() {
        switch (maeEstadoSeguimientoCodigo) {
            case ESTADO_PENDIENTE:
                return true;
            case ESTADO_EN_GESTION:
                return true;
            default:
                return false;
        }
    }

    public String getTipoTecnologiaStr() {
        if (auAnexo3ItemsId != null) {
            return auAnexo3ItemsId.getTipoTecnologiaStr();
        }
        return "";
    }
    
    public String establecerColorSemaforoSeguimiento() {
        String colorVencimiento = "white";
        try {
            //validamos los estados que no deben verse con el semáforo
            if (!(getMaeEstadoSeguimientoCodigo().equals(ESTADO_ENTREGADO) ||
                    getMaeEstadoSeguimientoCodigo().equals(ESTADO_GESTIONADO) || 
                    getMaeEstadoSeguimientoCodigo().equals(ESTADO_CANCELADO))) {
                float fechaInicio = 0;
                float fechaFin  = 0;
                if (getFechaHoraPrimeraGestion()!= null) {
                    fechaInicio = getFechaHoraPrimeraGestion().getTime();
                } else {
                    return colorVencimiento;
                }
                fechaFin = new Date().getTime();
                float milisecondsByDay = 86400000;
                float dias = (fechaFin-fechaInicio) / milisecondsByDay;  
                if (dias <= 1) {
                    colorVencimiento = "green";
                } else if (dias > 1 && dias <= 2) {
                    colorVencimiento = "yellow";
                }else if(dias > 2){
                    colorVencimiento = "red";
                }
            }
        } catch (Exception e) {
            colorVencimiento = "white";
        }
        return colorVencimiento;
    }
    
    public String establecerColorSemaforoASignacionIPS() {
        String colorVencimiento = "white";
        try {
            //validamos el estado que debe verse con el semáforo
            if (getMaeEstadoSeguimientoCodigo().equals(ESTADO_ASIGNADO_PRESTADOR) ||
                    getMaeEstadoSeguimientoCodigo().equals(ESTADO_ACEPTADO_PRESTADOR) ||
                    getMaeEstadoSeguimientoCodigo().equals(ESTADO_AUTORIZADO)) {
                float fechaInicio = 0;
                float fechaFin  = 0;
                if (getFechaHoraAsignaPrestador()!= null) {
                    fechaInicio = getFechaHoraAsignaPrestador().getTime();
                } else {
                    return colorVencimiento;
                }
                fechaFin = new Date().getTime();

                float milisecondsByDay = 86400000;
                float dias = (fechaFin-fechaInicio) / milisecondsByDay;
                if (dias <= 1) {
                    colorVencimiento = "green";
                } else if (dias > 1 && dias <= 2) {
                    colorVencimiento = "yellow";
                }else if(dias > 2){
                    colorVencimiento = "red";
                }
            }
        } catch (Exception e) {
            colorVencimiento = "white";
        }
        return colorVencimiento;
    }

    @Override
    public String toString() {
        return "AuSeguimiento{" + "id=" + id + ", estadoTecnologia=" + estadoTecnologia + ", maeEstadoSeguimientoId=" + maeEstadoSeguimientoId + ", maeEstadoSeguimientoCodigo=" + maeEstadoSeguimientoCodigo + ", maeEstadoSeguimientoValor=" + maeEstadoSeguimientoValor + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maeTipoServicioId=" + maeTipoServicioId + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", maeAcompananteTipoDocumentoId=" + maeAcompananteTipoDocumentoId + ", maeAcompananteTipoDocumentoCodigo=" + maeAcompananteTipoDocumentoCodigo + ", maeAcompananteTipoDocumentoValor=" + maeAcompananteTipoDocumentoValor + ", acompananteDocumento=" + acompananteDocumento + ", acompanantePrimerNombre=" + acompanantePrimerNombre + ", acompananteSegundoNombre=" + acompananteSegundoNombre + ", acompanantePrimerApellido=" + acompanantePrimerApellido + ", acompananteSegundoApellido=" + acompananteSegundoApellido + ", acompananteTelefono=" + acompananteTelefono + ", acompananteDireccionResidencia=" + acompananteDireccionResidencia + ", acompananteBarrioResidencia=" + acompananteBarrioResidencia + ", maeAmbitoAtencionId=" + maeAmbitoAtencionId + ", maeAmbitoAtencionCodigo=" + maeAmbitoAtencionCodigo + ", maeAmbitoAtencionValor=" + maeAmbitoAtencionValor + ", aplicaPrestador=" + aplicaPrestador
                + ", auAnexo3ItemsId=" + (auAnexo3ItemsId == null ? null : auAnexo3ItemsId.getId())
                + ", auAnexos3Id=" + (auAnexos3Id == null ? null : auAnexos3Id.getId())
                + ", auAnexos4Id=" + (auAnexos4Id == null ? null : auAnexos4Id.getId())
                + ", auGruposId=" + (auGruposId == null ? null : auGruposId.getId())
                + ", cntPrestadorSedeAsignadoId=" + (cntPrestadorSedeAsignadoId == null ? null : cntPrestadorSedeAsignadoId.getId())
                + ", gnEmpresasId=" + (gnEmpresasId == null ? null : gnEmpresasId.getId())
                + ", auSeguimientoAfiliadosId=" + (auSeguimientoAfiliadosId == null ? null : auSeguimientoAfiliadosId.getId())
                + '}';
    }

    /**
     * @return the fechaHoraPrimeraGestion
     */
    public Date getFechaHoraPrimeraGestion() {
        return fechaHoraPrimeraGestion;
    }

    /**
     * @param fechaHoraPrimeraGestion the fechaHoraPrimeraGestion to set
     */
    public void setFechaHoraPrimeraGestion(Date fechaHoraPrimeraGestion) {
        this.fechaHoraPrimeraGestion = fechaHoraPrimeraGestion;
    }

    /**
     * @return the fechaHoraUltimaGestion
     */
    public Date getFechaHoraUltimaGestion() {
        return fechaHoraUltimaGestion;
    }

    /**
     * @param fechaHoraUltimaGestion the fechaHoraUltimaGestion to set
     */
    public void setFechaHoraUltimaGestion(Date fechaHoraUltimaGestion) {
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
    }

    /**
     * @return the fechaHoraAtiende
     */
    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    /**
     * @param fechaHoraAtiende the fechaHoraAtiende to set
     */
    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    /**
     * @return the fechaHoraAsignaPrestador
     */
    public Date getFechaHoraAsignaPrestador() {
        return fechaHoraAsignaPrestador;
    }

    /**
     * @param fechaHoraAsignaPrestador the fechaHoraAsignaPrestador to set
     */
    public void setFechaHoraAsignaPrestador(Date fechaHoraAsignaPrestador) {
        this.fechaHoraAsignaPrestador = fechaHoraAsignaPrestador;
    }

}
