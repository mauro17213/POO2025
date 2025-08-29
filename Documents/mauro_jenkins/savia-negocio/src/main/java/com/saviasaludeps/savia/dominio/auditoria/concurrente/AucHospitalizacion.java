/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacion extends Auditoria {

    //Estados Afiliado
    public static final int ESTADO_AFILIADO_HOSPITALIZADO = 1;
    public static final int ESTADO_AFILIADO_EGRESADO = 2;
    public static final int ESTADO_AFILIADO_ANULADO = 3;
    public static final int ESTADO_AFILIADO_HOSPITALIZADO_DEVUELTO = 4;

    //Estado Auditoria
    public static final int ESTADO_AUDITORIA_INGRESADO = 1;
    public static final int ESTADO_AUDITORIA_EN_GESTION = 2;
    public static final int ESTADO_AUDITORIA_EGRESADO = 3;
    public static final int ESTADO_AUDITORIA_CERRADO = 4;
    public static final int ESTADO_AUDITORIA_ANULADO = 5;
    public static final int ESTADO_AUDITORIA_EN_GESTION_DEVUELTO = 6;

    public static final int NO_APLICA_RESCATE = 0;
    public static final int APLICA_RESCATE = 1;
    public static final int GESTION_RESCATE = 2;
    public static final int NO_APTO_RESCATE = 3;

    //Textos
    private static final String HOSPITALIZADO = "Hospitalizado";
    private static final String EGRESADO = "Egresado";
    private static final String ANULADO = "Anulado";
    private static final String INGRESADO = "Ingresado";
    private static final String EN_GESTION = "En Gestión";
    private static final String CERRADO = "Cerrado";

    //Parametros para variables Acta de hospitalización
    public static final String LISTA_DIAGNOSTICOS = "LISTA_DIAGNOSTICOS";
    public static final String LISTA_ESTANCIAS = "LISTA_ESTANCIAS";
    public static final String LISTA_DIAGNOSTICOS_EGRESO = "LISTA_DIAGNOSTICOS_EGRESO";
    public static final String LISTA_SEGUIMIENTO = "LISTA_SEGUIMIENTO";
    public static final String LISTA_ESTANCIAS_PROLONGADAS = "LISTA_ESTANCIAS_PROLONGADAS";

    //Para descripcion corto del devolicion
    public static final int TAMANIO_DESCRIPCION = 30;
    private Integer id;
    private int codigoEvento;
    private int estado;
    private Integer estadoAuditoria;
    private List<AucHospitalizacionAdverso> aucHospitalizacionAdversoList;
    private List<AucHospitalizacionSeguimiento> aucHospitalizacionSeguimientoList;
    private List<AucHospitalizacionSeguimiento> aucHospitalizacionGestorasRegionalesList;
    private List<AucHospitalizacionInoportunidad> aucHospitalizacionInoportunidadList;
    private AucAfiliado aucAfiliadoId;
    private AucEgreso aucEgresoId;
    private AucIngreso aucIngresoId;
    private CntPrestadorSede cntPrestadorSedeId;
    private CntPrestador cntPrestadorId;
    private Empresa gnEmpresaId;
    private Usuario gnUsuariosAuditorId;
    private Date fechaInicioHospitalizacion;
    private Date fechaFinHospitalizacion;
    private Date fechaUltimaNota;
    private Integer diasHospitalizacion;
    private boolean devuelto;
    private String fuenteOrigenEgreso;
    private String observacionDevolucion;
    private String observacionDevolucionCorto;
    
    private Integer maEspecialidadesId;
    private String maEspecialidadesCodigo;
    private String maEspecialidadesValor;
    private BigDecimal valorDiarioAcumulado;
    private int aplicaRescate;
    private List<AucHospitalizacionObjecion> aucHospitalizacionObjecionList;
    private List<AucHospitalizacionPatologia> aucHospitalizacionPatologiaList;
    private List<AucHospitalizacionServicio> aucHospitalizacionServicioList;
    private List<AucHospitalizacionEstancia> aucHospitalizacionEstanciaList;
    private List<AucJustificacionEstanciasProlongada> aucHospitalizacionJustificacionEstanciasProlongadaList;
    private List<AucDiagnostico> aucHospitalizacionDiagnosticoEstanciaTratanteList;
    private List<PePrograma> aucProgramaEspecialList;
    private List<PePrograma> aucSugerirProgramaList;
    private Integer[] aucIdSugerirProgramaList;
    private List<AuAnexo2Rescate> aucRescateList;

    private boolean cierreAuditoria;
    private String usuarioCierreAuditoria;
    private String terminalCierreAuditoria;
    private Date fechaHoraCierreAuditoria;

    //para el color del los dias de vencimiento semofarizacion
    private String colorVencimiento;

    public AucHospitalizacion() {
    }

    public AucHospitalizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Integer estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public List<AucHospitalizacionAdverso> getAucHosptalizacionAdversoList() {
        if (aucHospitalizacionAdversoList == null) {
            aucHospitalizacionAdversoList = new ArrayList<>();
        }
        return aucHospitalizacionAdversoList;
    }

    public void setAucHosptalizacionAdversoList(List<AucHospitalizacionAdverso> aucHospitalizacionAdversoList) {
        this.aucHospitalizacionAdversoList = aucHospitalizacionAdversoList;
    }

    public List<AucHospitalizacionSeguimiento> getAucHospitalizacionSeguimientoList() {
        if (aucHospitalizacionSeguimientoList == null) {
            aucHospitalizacionSeguimientoList = new ArrayList<>();
        }
        return aucHospitalizacionSeguimientoList;
    }

    public void setAucHospitalizacionSeguimientoList(List<AucHospitalizacionSeguimiento> aucHospitalizacionSeguimientoList) {
        this.aucHospitalizacionSeguimientoList = aucHospitalizacionSeguimientoList;
    }

    public List<AucHospitalizacionSeguimiento> getAucHospitalizacionGestorasRegionalesList() {
        if (aucHospitalizacionGestorasRegionalesList == null) {
            aucHospitalizacionGestorasRegionalesList = new ArrayList<>();
        }
        return aucHospitalizacionGestorasRegionalesList;
    }

    public void setAucHospitalizacionGestorasRegionalesList(List<AucHospitalizacionSeguimiento> aucHospitalizacionGestorasRegionalesList) {
        this.aucHospitalizacionGestorasRegionalesList = aucHospitalizacionGestorasRegionalesList;
    }

    public List<AucHospitalizacionInoportunidad> getAucHospitalizacionInoportunidadList() {
        if (aucHospitalizacionInoportunidadList == null) {
            aucHospitalizacionInoportunidadList = new ArrayList<>();
        }
        return aucHospitalizacionInoportunidadList;
    }

    public void setAucHospitalizacionInoportunidadList(List<AucHospitalizacionInoportunidad> aucHospitalizacionInoportunidadList) {
        this.aucHospitalizacionInoportunidadList = aucHospitalizacionInoportunidadList;
    }

    public AucAfiliado getAucAfiliadoId() {
        if (aucAfiliadoId == null) {
            aucAfiliadoId = new AucAfiliado();
        }
        return aucAfiliadoId;
    }

    public void setAucAfiliadoId(AucAfiliado aucAfiliadoId) {
        this.aucAfiliadoId = aucAfiliadoId;
    }

    public AucEgreso getAucEgresoId() {
        if (aucEgresoId == null) {
            aucEgresoId = new AucEgreso();
        }
        return aucEgresoId;
    }

    public void setAucEgresoId(AucEgreso aucEgresoId) {
        this.aucEgresoId = aucEgresoId;
    }

    public AucIngreso getAucIngresoId() {
        if (aucIngresoId == null) {
            aucIngresoId = new AucIngreso();
        }
        return aucIngresoId;
    }

    public void setAucIngresoId(AucIngreso aucIngresoId) {
        this.aucIngresoId = aucIngresoId;
    }

    public CntPrestadorSede getCntPrestadorSedeId() {
        if (cntPrestadorSedeId == null) {
            cntPrestadorSedeId = new CntPrestadorSede();
        }
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(CntPrestadorSede cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public CntPrestador getCntPrestadorId() {
        if (cntPrestadorId == null) {
            cntPrestadorId = new CntPrestador();
        }
        return cntPrestadorId;
    }

    public void setCntPrestadorId(CntPrestador cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public Empresa getGnEmpresaId() {
        if (gnEmpresaId == null) {
            gnEmpresaId = new Empresa();
        }
        return gnEmpresaId;
    }

    public void setGnEmpresaId(Empresa gnEmpresaId) {
        this.gnEmpresaId = gnEmpresaId;
    }

    public Usuario getGnUsuariosAuditorId() {
        if (gnUsuariosAuditorId == null) {
            gnUsuariosAuditorId = new Usuario();
        }
        return gnUsuariosAuditorId;
    }

    public void setGnUsuariosAuditorId(Usuario gnUsuariosAuditorId) {
        this.gnUsuariosAuditorId = gnUsuariosAuditorId;
    }

    public Date getFechaInicioHospitalizacion() {
        return fechaInicioHospitalizacion;
    }

    public void setFechaInicioHospitalizacion(Date fechaInicioHospitalizacion) {
        this.fechaInicioHospitalizacion = fechaInicioHospitalizacion;
    }

    public Date getFechaFinHospitalizacion() {
        return fechaFinHospitalizacion;
    }

    public void setFechaFinHospitalizacion(Date fechaFinHospitalizacion) {
        this.fechaFinHospitalizacion = fechaFinHospitalizacion;
    }

    public Date getFechaUltimaNota() {
        return fechaUltimaNota;
    }

    public void setFechaUltimaNota(Date fechaUltimaNota) {
        this.fechaUltimaNota = fechaUltimaNota;
    }

    public Integer getDiasHospitalizacion() {
        return diasHospitalizacion;
    }

    public void setDiasHospitalizacion(Integer diasHospitalizacion) {
        this.diasHospitalizacion = diasHospitalizacion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public String getFuenteOrigenEgreso() {
        return fuenteOrigenEgreso;
    }

    public void setFuenteOrigenEgreso(String fuenteOrigenEgreso) {
        this.fuenteOrigenEgreso = fuenteOrigenEgreso;
    }

    public String getObservacionDevolucion() {
        return observacionDevolucion;
    }

    public void setObservacionDevolucion(String observacionDevolucion) {
        this.observacionDevolucion = observacionDevolucion;
    }

    public int getAplicaRescate() {
        return aplicaRescate;
    }

    public void setAplicaRescate(int aplicaRescate) {
        this.aplicaRescate = aplicaRescate;
    }

    public String getObservacionDevolucionCorto() {
        if (getObservacionDevolucion() != null) {
            observacionDevolucionCorto = getObservacionDevolucion();
            if (getObservacionDevolucion().length() >= TAMANIO_DESCRIPCION) {
                return observacionDevolucionCorto.substring(0, TAMANIO_DESCRIPCION) + "..";
            } else {
                return observacionDevolucionCorto;
            }
        }
        return observacionDevolucionCorto;
    }

    public void setObservacionDevolucionCorto(String observacionDevolucionCorto) {
        this.observacionDevolucionCorto = observacionDevolucionCorto;
    }

    public Integer getMaEspecialidadesId() {
        return maEspecialidadesId;
    }

    public void setMaEspecialidadesId(Integer maEspecialidadesId) {
        this.maEspecialidadesId = maEspecialidadesId;
    }

    public String getMaEspecialidadesCodigo() {
        return maEspecialidadesCodigo;
    }

    public void setMaEspecialidadesCodigo(String maEspecialidadesCodigo) {
        this.maEspecialidadesCodigo = maEspecialidadesCodigo;
    }

    public String getMaEspecialidadesValor() {
        return maEspecialidadesValor;
    }

    public void setMaEspecialidadesValor(String maEspecialidadesValor) {
        this.maEspecialidadesValor = maEspecialidadesValor;
    }

    public BigDecimal getValorDiarioAcumulado() {
        return valorDiarioAcumulado;
    }

    public void setValorDiarioAcumulado(BigDecimal valorDiarioAcumulado) {
        this.valorDiarioAcumulado = valorDiarioAcumulado;
    }

    public List<AucHospitalizacionObjecion> getAucHospitalizacionObjecionList() {
        if (aucHospitalizacionObjecionList == null) {
            aucHospitalizacionObjecionList = new ArrayList<>();
        }
        return aucHospitalizacionObjecionList;
    }

    public void setAucHospitalizacionObjecionList(List<AucHospitalizacionObjecion> aucHospitalizacionObjecionList) {
        this.aucHospitalizacionObjecionList = aucHospitalizacionObjecionList;
    }

    public List<AucHospitalizacionPatologia> getAucHospitalizacionPatologiaList() {
        if (aucHospitalizacionPatologiaList == null) {
            aucHospitalizacionPatologiaList = new ArrayList<>();
        }
        return aucHospitalizacionPatologiaList;
    }

    public void setAucHospitalizacionPatologiaList(List<AucHospitalizacionPatologia> aucHospitalizacionPatologiaList) {
        this.aucHospitalizacionPatologiaList = aucHospitalizacionPatologiaList;
    }

    public List<AucHospitalizacionServicio> getAucHospitalizacionServicioList() {
        if (aucHospitalizacionServicioList == null) {
            aucHospitalizacionServicioList = new ArrayList<>();
        }
        return aucHospitalizacionServicioList;
    }

    public void setAucHospitalizacionServicioList(List<AucHospitalizacionServicio> aucHospitalizacionServicioList) {
        this.aucHospitalizacionServicioList = aucHospitalizacionServicioList;
    }

    public List<AucHospitalizacionEstancia> getAucHospitalizacionEstanciaList() {
        if (aucHospitalizacionEstanciaList == null) {
            aucHospitalizacionEstanciaList = new ArrayList<>();
        }
        return aucHospitalizacionEstanciaList;
    }

    public void setAucHospitalizacionEstanciaList(List<AucHospitalizacionEstancia> aucHospitalizacionEstanciaList) {
        this.aucHospitalizacionEstanciaList = aucHospitalizacionEstanciaList;
    }

    public String getColorVencimiento() {
        return colorVencimiento;
    }

    public void setColorVencimiento(String colorVencimiento) {
        this.colorVencimiento = colorVencimiento;
    }

    public boolean isCierreAuditoria() {
        return cierreAuditoria;
    }

    public void setCierreAuditoria(boolean cierreAuditoria) {
        this.cierreAuditoria = cierreAuditoria;
    }

    public String getUsuarioCierreAuditoria() {
        return usuarioCierreAuditoria;
    }

    public void setUsuarioCierreAuditoria(String usuarioCierreAuditoria) {
        this.usuarioCierreAuditoria = usuarioCierreAuditoria;
    }

    public String getTerminalCierreAuditoria() {
        return terminalCierreAuditoria;
    }

    public void setTerminalCierreAuditoria(String terminalCierreAuditoria) {
        this.terminalCierreAuditoria = terminalCierreAuditoria;
    }

    public Date getFechaHoraCierreAuditoria() {
        return fechaHoraCierreAuditoria;
    }

    public void setFechaHoraCierreAuditoria(Date fechaHoraCierreAuditoria) {
        this.fechaHoraCierreAuditoria = fechaHoraCierreAuditoria;
    }

    public List<AucJustificacionEstanciasProlongada> getAucHospitalizacionJustificacionEstanciasProlongadaList() {
        if (aucHospitalizacionJustificacionEstanciasProlongadaList == null) {
            aucHospitalizacionJustificacionEstanciasProlongadaList = new ArrayList<>();
        }
        return aucHospitalizacionJustificacionEstanciasProlongadaList;
    }

    public void setAucHospitalizacionJustificacionEstanciasProlongadaList(List<AucJustificacionEstanciasProlongada> aucHospitalizacionJustificacionEstanciasProlongadaList) {
        this.aucHospitalizacionJustificacionEstanciasProlongadaList = aucHospitalizacionJustificacionEstanciasProlongadaList;
    }

    public List<AucDiagnostico> getAucHospitalizacionDiagnosticoEstanciaTratanteList() {
        if (aucHospitalizacionDiagnosticoEstanciaTratanteList == null) {
            aucHospitalizacionDiagnosticoEstanciaTratanteList = new ArrayList<>();
        }
        return aucHospitalizacionDiagnosticoEstanciaTratanteList;
    }

    public void setAucHospitalizacionDiagnosticoEstanciaTratanteList(List<AucDiagnostico> aucHospitalizacionDiagnosticoEstanciaTratanteList) {
        this.aucHospitalizacionDiagnosticoEstanciaTratanteList = aucHospitalizacionDiagnosticoEstanciaTratanteList;
    }

    public List<PePrograma> getAucProgramaEspecialList() {
        if (aucProgramaEspecialList == null) {
            aucProgramaEspecialList = new ArrayList<>();
        }
        return aucProgramaEspecialList;
    }

    public void setAucProgramaEspecialList(List<PePrograma> aucProgramaEspecialList) {
        this.aucProgramaEspecialList = aucProgramaEspecialList;
    }

    public List<PePrograma> getAucSugerirProgramaList() {
        return aucSugerirProgramaList;
    }

    public void setAucSugerirProgramaList(List<PePrograma> aucSugerirProgramaList) {
        this.aucSugerirProgramaList = aucSugerirProgramaList;
    }

    public Integer[] getAucIdSugerirProgramaList() {
        return aucIdSugerirProgramaList;
    }

    public void setAucIdSugerirProgramaList(Integer[] aucIdSugerirProgramaList) {
        this.aucIdSugerirProgramaList = aucIdSugerirProgramaList;
    }

    public List<AuAnexo2Rescate> getAucRescateList() {
        if (aucRescateList == null) {
            aucRescateList = new ArrayList<>();
        }
        return aucRescateList;
    }

    public void setAucRescateList(List<AuAnexo2Rescate> aucRescateList) {
        this.aucRescateList = aucRescateList;
    }

    //Metodos
    public String getEstadoStr() {
        String est = "";
        switch (getEstado()) {
            case ESTADO_AFILIADO_HOSPITALIZADO:
                est = HOSPITALIZADO;
                break;
            case ESTADO_AFILIADO_EGRESADO:
                est = EGRESADO;
                break;
            case ESTADO_AFILIADO_ANULADO:
                est = ANULADO;
                break;

        }
        return est;
    }

    public String getFuenteOrigenEgresoStr() {
        String est = "";
        if (getFuenteOrigenEgreso() != null) {
            switch (getFuenteOrigenEgreso()) {
                case "0":
                    est = "Manual";
                    break;
                case "1":
                    est = "Censo";
                    break;
                case "2":
                    est = "Interoperabilidad";
                    break;
            }
        }
        return est;
    }

    public String getDevolucionStr() {
        String est = "";
        if (isDevuelto()) {
            est = "Si";
        } else {
            est = "No";
        }
        return est;
    }

    public String getEstadoAuditoriaStr() {
        String est = "";
        if (getEstadoAuditoria() != null) {
            switch (getEstadoAuditoria()) {
                case ESTADO_AUDITORIA_INGRESADO:
                    est = INGRESADO;
                    break;
                case ESTADO_AUDITORIA_EN_GESTION:
                    est = EN_GESTION;
                    break;
                case ESTADO_AUDITORIA_EGRESADO:
                    est = EGRESADO;
                    break;
                case ESTADO_AUDITORIA_CERRADO:
                    est = CERRADO;
                    break;
                case ESTADO_AUDITORIA_ANULADO:
                    est = ANULADO;
                    break;

            }
        }

        return est;
    }

    /*public int getCalcularDiasHospitalizacion() {
        Long fechaInicio = null;
        Long fechaFin  = null;
        if (getAucIngresoId() != null) {
            if (getAucIngresoId().getFechaIngreso() != null) {
                fechaInicio = getAucIngresoId().getFechaIngreso().getTime();
            }
        } else {
            return 0;
        }
        if (getAucEgresoId() != null) {
            if (getAucEgresoId().getFechaEgreso() != null) {
                fechaFin = getAucEgresoId().getFechaEgreso().getTime();
            }else{
                fechaFin = new Date().getTime();
            }
        } else {
            fechaFin = new Date().getTime();
        }
        int milisecondsByDay = 86400000;
        return (int) ((fechaFin-fechaInicio) / milisecondsByDay);
    }*/
    public int getCalcularDiasHospitalizacionFin() {
        Long fechaInicio = null;
        Long fechaFin = null;
        if (getFechaInicioHospitalizacion() != null) {
            fechaInicio = getFechaInicioHospitalizacion().getTime();
        } else {
            return 0;
        }
        if (getFechaFinHospitalizacion() != null) {
            fechaFin = getFechaFinHospitalizacion().getTime();
        } else {
            fechaFin = new Date().getTime();
        }
        int milisecondsByDay = 86400000;
        return (int) ((fechaFin - fechaInicio) / milisecondsByDay);
    }

    public String establecerColorHospitalizacionFin() {

        try {
            Long fechaInicio = null;
            Long fechaFin = null;
            if (getFechaInicioHospitalizacion() != null) {
                fechaInicio = getFechaInicioHospitalizacion().getTime();
            } else {
                colorVencimiento = "green";
            }
            if (getFechaFinHospitalizacion() != null) {
                fechaFin = getFechaFinHospitalizacion().getTime();
            } else {
                fechaFin = new Date().getTime();
            }
            int milisecondsByDay = 86400000;
            int dias = (int) ((fechaFin - fechaInicio) / milisecondsByDay);
            if (dias <= 3) {
                colorVencimiento = "green";
            } else if (dias > 3 && dias <= 6) {
                colorVencimiento = "orange";
            } else if (dias >= 7) {
                colorVencimiento = "red";
            }
        } catch (Exception e) {
        }
        return colorVencimiento;
    }
 
    @Override
    public String toString() {
        return "AucHospitalizacion{" + "id=" + id + ", codigoEvento=" + codigoEvento + ", estado=" + estado + ", estadoAuditoria=" + estadoAuditoria + ", cntPrestadorSedeId=" + cntPrestadorSedeId + ", cntPrestadorId=" + cntPrestadorId + ", gnEmpresaId=" + gnEmpresaId + ", gnUsuariosAuditorId=" + gnUsuariosAuditorId + ", fechaInicioHospitalizacion=" + fechaInicioHospitalizacion + ", fechaFinHospitalizacion=" + fechaFinHospitalizacion + ", fechaUltimaNota=" + fechaUltimaNota + ", diasHospitalizacion=" + diasHospitalizacion + ", devuelto=" + devuelto + ", fuenteOrigenEgreso=" + fuenteOrigenEgreso + ", observacionDevolucion=" + observacionDevolucion + ", observacionDevolucionCorto=" + observacionDevolucionCorto + ", maEspecialidadesId=" + maEspecialidadesId + ", maEspecialidadesCodigo=" + maEspecialidadesCodigo + ", maEspecialidadesValor=" + maEspecialidadesValor + ", aplicaRescate=" + aplicaRescate + ", aucSugerirProgramaList=" + aucSugerirProgramaList + ", usuarioCierreAuditoria=" + usuarioCierreAuditoria + ", terminalCierreAuditoria=" + terminalCierreAuditoria + ", fechaHoraCierreAuditoria=" + fechaHoraCierreAuditoria + '}';
    }
}
