package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bsteven_gomez
 *
 */
public class GjProceso extends Auditoria {

    private Integer id;
    private String radicado;
    private Date fechaRadicado;
    private Date fechaAdmision;
    private Date fechaNotificacion;
    private int maeJurisdiccionId;
    private String maeJurisdiccionCodigo;
    private String maeJurisdiccionValor;
    private int maeClaseId;
    private String maeClaseCodigo;
    private String maeClaseValor;
    private int maeClaseDescripcionId;
    private String maeClaseDescripcionCodigo;
    private String maeClaseDescripcionValor;
    private String pretencionDescripcion;
    private BigDecimal cuantia;
    private BigDecimal cuantiaObjetiva;
    private Short estado;
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
    private Date fechaUltimaActuacion;
    private byte[] ultimaActuacion;
    private Date fechaContestacion;
    private BigDecimal valorRiesgoCondena;

    private Boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;

    private List<GjProcesoAdjunto> gjProcesoAdjuntosList;
    private List<GjProcesoAbogado> gjProcesoAbogadosList;
    private List<GjProcesoPretencion> gjProcesoPretencionesList;
    private Ubicacion gnUbicacionesProcesoId;
    private TuJuzgado tuJuzgadosId;
    private List<GjProcesoHistorico> gjProcesoHistoricosList;
    private List<GjProcesoTercero> gjProcesoTercerosList;
    private List<GjProcesoGarantia> gjProcesoGarantiasList;

    private GjProceso gjProceso;
    private GjTercero gjTercero;
    private GjAbogado gjAbogado;
    private GjProcesoTercero gjProcesoTercero;
    private GjProcesoGarantia gjProcesoGarantia;
    private GjProcesoHistorico gjProcesoHistorico;
    private GjProcesoAbogado gjProcesoAbogado;
    private GjProcesoPretencion gjProcesoPretencion;
    private GjProcesoAdjunto gjProcesoAdjunto;

    private String tipoEstado;
    private String procesoEstado;
    private String ultimaActuacionStr;
    private String probabilidadStr;
    private String clasificacionRiesgoStr;
    private String ClaseProvicionStr;
    private String estadoCondenaStr;

    private String setCuantiaStr;
    private String setValorSentenciaEjecutoriaStr;
    private String setMontoMedidaStr;
    private String setValorRiesgoCondenaStr;
    private String setCuantiaObjetivaStr;

    private Integer letrasDisponibles;
    private String colorEstado;

    public GjProceso() {
    }

    public GjProceso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    public Date getFechaAdmision() {
        return fechaAdmision;
    }

    public void setFechaAdmision(Date fechaAdmision) {
        this.fechaAdmision = fechaAdmision;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public int getMaeJurisdiccionId() {
        return maeJurisdiccionId;
    }

    public void setMaeJurisdiccionId(int maeJurisdiccionId) {
        this.maeJurisdiccionId = maeJurisdiccionId;
    }

    public String getMaeJurisdiccionCodigo() {
        return maeJurisdiccionCodigo;
    }

    public void setMaeJurisdiccionCodigo(String maeJurisdiccionCodigo) {
        this.maeJurisdiccionCodigo = maeJurisdiccionCodigo;
    }

    public String getMaeJurisdiccionValor() {
        return maeJurisdiccionValor;
    }

    public void setMaeJurisdiccionValor(String maeJurisdiccionValor) {
        this.maeJurisdiccionValor = maeJurisdiccionValor;
    }

    public int getMaeClaseId() {
        return maeClaseId;
    }

    public void setMaeClaseId(int maeClaseId) {
        this.maeClaseId = maeClaseId;
    }

    public String getMaeClaseCodigo() {
        return maeClaseCodigo;
    }

    public void setMaeClaseCodigo(String maeClaseCodigo) {
        this.maeClaseCodigo = maeClaseCodigo;
    }

    public String getMaeClaseValor() {
        return maeClaseValor;
    }

    public void setMaeClaseValor(String maeClaseValor) {
        this.maeClaseValor = maeClaseValor;
    }

    public int getMaeClaseDescripcionId() {
        return maeClaseDescripcionId;
    }

    public void setMaeClaseDescripcionId(int maeClaseDescripcionId) {
        this.maeClaseDescripcionId = maeClaseDescripcionId;
    }

    public String getMaeClaseDescripcionCodigo() {
        return maeClaseDescripcionCodigo;
    }

    public void setMaeClaseDescripcionCodigo(String maeClaseDescripcionCodigo) {
        this.maeClaseDescripcionCodigo = maeClaseDescripcionCodigo;
    }

    public String getMaeClaseDescripcionValor() {
        return maeClaseDescripcionValor;
    }

    public void setMaeClaseDescripcionValor(String maeClaseDescripcionValor) {
        this.maeClaseDescripcionValor = maeClaseDescripcionValor;
    }

    public String getPretencionDescripcion() {
        return pretencionDescripcion;
    }

    public void setPretencionDescripcion(String pretencionDescripcion) {
        this.pretencionDescripcion = pretencionDescripcion;
    }

    public BigDecimal getCuantia() {
        return cuantia;
    }

    public void setCuantia(BigDecimal cuantia) {
        this.cuantia = cuantia;
    }

    public BigDecimal getCuantiaObjetiva() {
        return cuantiaObjetiva;
    }

    public void setCuantiaObjetiva(BigDecimal cuantiaObjetiva) {
        this.cuantiaObjetiva = cuantiaObjetiva;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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

    public Date getFechaUltimaActuacion() {
        return fechaUltimaActuacion;
    }

    public void setFechaUltimaActuacion(Date fechaUltimaActuacion) {
        this.fechaUltimaActuacion = fechaUltimaActuacion;
    }

    public byte[] getUltimaActuacion() {
        return ultimaActuacion;
    }

    public void setUltimaActuacion(byte[] ultimaActuacion) {
        this.ultimaActuacion = ultimaActuacion;
    }

    public Date getFechaContestacion() {
        return fechaContestacion;
    }

    public void setFechaContestacion(Date fechaContestacion) {
        this.fechaContestacion = fechaContestacion;
    }

    public BigDecimal getValorRiesgoCondena() {
        return valorRiesgoCondena;
    }

    public void setValorRiesgoCondena(BigDecimal valorRiesgoCondena) {
        this.valorRiesgoCondena = valorRiesgoCondena;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public List<GjProcesoAdjunto> getGjProcesoAdjuntosList() {
        if (gjProcesoAdjuntosList == null) {
            gjProcesoAdjuntosList = new ArrayList<>();
        }
        return gjProcesoAdjuntosList;
    }

    public void setGjProcesoAdjuntosList(List<GjProcesoAdjunto> gjProcesoAdjuntosList) {
        this.gjProcesoAdjuntosList = gjProcesoAdjuntosList;
    }

    public List<GjProcesoAbogado> getGjProcesoAbogadosList() {
        return gjProcesoAbogadosList;
    }

    public void setGjProcesoAbogadosList(List<GjProcesoAbogado> gjProcesoAbogadosList) {
        this.gjProcesoAbogadosList = gjProcesoAbogadosList;
    }

    public List<GjProcesoPretencion> getGjProcesoPretencionesList() {
        return gjProcesoPretencionesList;
    }

    public void setGjProcesoPretencionesList(List<GjProcesoPretencion> gjProcesoPretencionesList) {
        this.gjProcesoPretencionesList = gjProcesoPretencionesList;
    }

    public Ubicacion getGnUbicacionesProcesoId() {
        if (gnUbicacionesProcesoId == null) {
            gnUbicacionesProcesoId = new Ubicacion();
        }
        return gnUbicacionesProcesoId;
    }

    public void setGnUbicacionesProcesoId(Ubicacion gnUbicacionesProcesoId) {
        this.gnUbicacionesProcesoId = gnUbicacionesProcesoId;
    }

    public TuJuzgado getTuJuzgadosId() {
        if (tuJuzgadosId == null) {
            tuJuzgadosId = new TuJuzgado();
        }
        return tuJuzgadosId;
    }

    public void setTuJuzgadosId(TuJuzgado tuJuzgadosId) {
        this.tuJuzgadosId = tuJuzgadosId;
    }

    public List<GjProcesoHistorico> getGjProcesoHistoricosList() {
        return gjProcesoHistoricosList;
    }

    public void setGjProcesoHistoricosList(List<GjProcesoHistorico> gjProcesoHistoricosList) {
        this.gjProcesoHistoricosList = gjProcesoHistoricosList;
    }

    public List<GjProcesoTercero> getGjProcesoTercerosList() {
        if (gjProcesoTercerosList == null) {
            gjProcesoTercerosList = new ArrayList<>();
        }
        return gjProcesoTercerosList;
    }

    public void setGjProcesoTercerosList(List<GjProcesoTercero> gjProcesoTercerosList) {
        this.gjProcesoTercerosList = gjProcesoTercerosList;
    }

    public List<GjProcesoGarantia> getGjProcesoGarantiasList() {
        if (gjProcesoGarantiasList == null) {
            gjProcesoGarantiasList = new ArrayList<>();
        }
        return gjProcesoGarantiasList;
    }

    public void setGjProcesoGarantiasList(List<GjProcesoGarantia> gjProcesoGarantiasList) {
        this.gjProcesoGarantiasList = gjProcesoGarantiasList;
    }

    public GjProceso getGjProceso() {
        if (gjProceso == null) {
            gjProceso = new GjProceso();
        }
        return gjProceso;
    }

    public void setGjProceso(GjProceso gjProceso) {
        this.gjProceso = gjProceso;
    }

    public GjTercero getGjTercero() {
        if (gjTercero == null) {
            gjTercero = new GjTercero();
        }
        return gjTercero;
    }

    public void setGjTercero(GjTercero gjTercero) {
        this.gjTercero = gjTercero;
    }

    public GjAbogado getGjAbogado() {
        if (gjAbogado == null) {
            gjAbogado = new GjAbogado();
        }
        return gjAbogado;
    }

    public Integer getLetrasDisponibles() {
        return letrasDisponibles;
    }

    public void setLetrasDisponibles(Integer letrasDisponibles) {
        this.letrasDisponibles = letrasDisponibles;
    }

    public void setGjAbogado(GjAbogado gjAbogado) {
        this.gjAbogado = gjAbogado;
    }

    public GjProcesoTercero getGjProcesoTercero() {
        if (gjProcesoTercero == null) {
            gjProcesoTercero = new GjProcesoTercero();
        }
        return gjProcesoTercero;
    }

    public void setGjProcesoTercero(GjProcesoTercero gjProcesoTercero) {
        this.gjProcesoTercero = gjProcesoTercero;
    }

    public GjProcesoGarantia getGjProcesoGarantia() {
        if (gjProcesoGarantia == null) {
            gjProcesoGarantia = new GjProcesoGarantia();
        }
        return gjProcesoGarantia;
    }

    public void setGjProcesoGarantia(GjProcesoGarantia gjProcesoGarantia) {
        this.gjProcesoGarantia = gjProcesoGarantia;
    }
    
    

    public GjProcesoHistorico getGjProcesoHistorico() {
        if (gjProcesoHistorico == null) {
            gjProcesoHistorico = new GjProcesoHistorico();
        }
        return gjProcesoHistorico;
    }

    public void setGjProcesoHistorico(GjProcesoHistorico gjProcesoHistorico) {
        this.gjProcesoHistorico = gjProcesoHistorico;
    }

    public GjProcesoAbogado getGjProcesoAbogado() {
        if (gjProcesoAbogado == null) {
            gjProcesoAbogado = new GjProcesoAbogado();
        }
        return gjProcesoAbogado;
    }

    public void setGjProcesoAbogado(GjProcesoAbogado gjProcesoAbogado) {
        this.gjProcesoAbogado = gjProcesoAbogado;
    }

    public GjProcesoPretencion getGjProcesoPretencion() {
        if (gjProcesoPretencion == null) {
            gjProcesoPretencion = new GjProcesoPretencion();
        }
        return gjProcesoPretencion;
    }

    public void setGjProcesoPretencion(GjProcesoPretencion gjProcesoPretencion) {
        this.gjProcesoPretencion = gjProcesoPretencion;
    }

    public GjProcesoAdjunto getGjProcesoAdjunto() {
        if (gjProcesoAdjunto == null) {
            gjProcesoAdjunto = new GjProcesoAdjunto();
        }
        return gjProcesoAdjunto;
    }

    public void setGjProcesoAdjunto(GjProcesoAdjunto gjProcesoAdjunto) {
        this.gjProcesoAdjunto = gjProcesoAdjunto;
    }

//auxiliar tipoEstadoStr
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
        } else {
            tipoEstado = " ";
        }
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

//auxiliar estadoStr
    public String getProcesoEstado() {

        if (getEstadoProceso() != null) {
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
            procesoEstado = " ";
        }
        return procesoEstado;
    }

    public void setProcesoEstado(String ProcesoEstado) {
        this.procesoEstado = ProcesoEstado;
    }

//Auxiliar para ultima actua str a byte[ ]
    public String getUltimaActuacionStr() {
        return ultimaActuacionStr;
    }

    public void setUltimaActuacionStr(String ultimaActuacionStr) {
        this.ultimaActuacionStr = ultimaActuacionStr;
    }

    public String getProbabilidadStr() {
        if (getProbabilidad() != null) {
            switch (this.getProbabilidad()) {
                case 1:
                    probabilidadStr = "Alto";
                    break;
                case 2:
                    probabilidadStr = "Medio Alto";
                    break;
                case 3:
                    probabilidadStr = "Medio Bajo";
                    break;
                case 4:
                    probabilidadStr = "Bajo";
                    break;
            }
        } else {
            probabilidadStr = " ";
        }
        return probabilidadStr;
    }

    public void setProbabilidadStr(String probabilidadStr) {
        this.probabilidadStr = probabilidadStr;
    }

    public String getClasificacionRiesgoStr() {
        if (getRiesgoClasificacion() != null) {
            switch (this.getRiesgoClasificacion()) {
                case 1:
                    clasificacionRiesgoStr = "Alto";
                    break;
                case 2:
                    clasificacionRiesgoStr = "Medio";
                    break;
                case 3:
                    clasificacionRiesgoStr = "Bajo";
                    break;
                case 4:
                    clasificacionRiesgoStr = "Remoto";
                    break;
                case 5:
                    clasificacionRiesgoStr = "Por Definir";
                    break;
            }
        } else {
            clasificacionRiesgoStr = " ";
        }
        return clasificacionRiesgoStr;
    }

    public void setClasificacionRiesgoStr(String clasificacionRiesgoStr) {
        this.clasificacionRiesgoStr = clasificacionRiesgoStr;
    }

    public String getClaseProvicionStr() {
        if (getClaseProvision() != null) {
            switch (this.getClaseProvision()) {
                case 1:
                    ClaseProvicionStr = "Provision Contable";
                    break;
                case 2:
                    ClaseProvicionStr = "Cuentas De Orden";
                    break;
                case 3:
                    ClaseProvicionStr = "Sin Provision";
                    break;
                case 4:
                    ClaseProvicionStr = "Por Definir";
                    break;
            }
        } else {
            ClaseProvicionStr = " ";
        }
        return ClaseProvicionStr;
    }

    public void setClaseProvicionStr(String ClaseProvicionStr) {
        this.ClaseProvicionStr = ClaseProvicionStr;
    }

    public String getEstadoCondenaStr() {
        if (getEstadoCumplimientoCondena() != null) {
            switch (this.getEstadoCumplimientoCondena()) {
                case 0:
                    estadoCondenaStr = "N/A";
                    break;
                case 1:
                    estadoCondenaStr = "Cumplido";
                    break;
                case 2:
                    estadoCondenaStr = "Pendiente";
                    break;
            }
        } else {
            estadoCondenaStr = " ";
        }
        return estadoCondenaStr;
    }

    public void setEstadoCondenaStr(String estadoCondenaStr) {
        this.estadoCondenaStr = estadoCondenaStr;
    }

    public String getSetCuantiaStr() {
        return setCuantiaStr;
    }

    public void setSetCuantiaStr(String setCuantiaStr) {
        this.setCuantiaStr = setCuantiaStr;
    }

    public String getSetValorSentenciaEjecutoriaStr() {
        return setValorSentenciaEjecutoriaStr;
    }

    public void setSetValorSentenciaEjecutoriaStr(String setValorSentenciaEjecutoriaStr) {
        this.setValorSentenciaEjecutoriaStr = setValorSentenciaEjecutoriaStr;
    }

    public String getSetMontoMedidaStr() {
        return setMontoMedidaStr;
    }

    public void setSetMontoMedidaStr(String setMontoMedidaStr) {
        this.setMontoMedidaStr = setMontoMedidaStr;
    }

    public String getSetValorRiesgoCondenaStr() {
        return setValorRiesgoCondenaStr;
    }

    public void setSetValorRiesgoCondenaStr(String setValorRiesgoCondenaStr) {
        this.setValorRiesgoCondenaStr = setValorRiesgoCondenaStr;
    }

    public String getSetCuantiaObjetivaStr() {
        return setCuantiaObjetivaStr;
    }

    public void setSetCuantiaObjetivaStr(String setCuantiaObjetivaStr) {
        this.setCuantiaObjetivaStr = setCuantiaObjetivaStr;
    }    
    

    public String getColorEstado() {
        return colorEstado;
    }

    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    public boolean exiteRadicado() {
        return this.getId() != null && this.getId() > 0;
    }

    public String establecerColorEstado() {

        try {
            Short st = getEstado();

            if (st != null) {

                if (st == 0) {
                    colorEstado = "blue";
                } else if (st == 1) {
                    colorEstado = "green";
                } else if (st == 2 || st == 3) {
                    colorEstado = "yellow";
                } else if (st > 3) {
                    colorEstado = "red";
                }

            } else {
                colorEstado = " ";
            }
        } catch (Exception e) {

        }

        return colorEstado;
    }

    @Override
    public String toString() {
        return "GjProceso{" + "id=" + id + ", radicado=" + radicado + ", fechaRadicado=" + fechaRadicado + ", fechaAdmision=" + fechaAdmision + ", fechaNotificacion=" + fechaNotificacion + ", maeJurisdiccionId=" + maeJurisdiccionId + ", maeJurisdiccionCodigo=" + maeJurisdiccionCodigo + ", maeJurisdiccionValor=" + maeJurisdiccionValor + ", maeClaseId=" + maeClaseId + ", maeClaseCodigo=" + maeClaseCodigo + ", maeClaseValor=" + maeClaseValor + ", maeClaseDescripcionId=" + maeClaseDescripcionId + ", maeClaseDescripcionCodigo=" + maeClaseDescripcionCodigo + ", maeClaseDescripcionValor=" + maeClaseDescripcionValor + ", pretencionDescripcion=" + pretencionDescripcion + ", cuantia=" + cuantia + ", cuantiaObjetiva=" + cuantiaObjetiva + ", estado=" + estado + ", estadoProceso=" + estadoProceso + ", maeInstanciaId=" + maeInstanciaId + ", maeInstanciaCodigo=" + maeInstanciaCodigo + ", maeInstanciaValor=" + maeInstanciaValor + ", llamamientoGarantia=" + llamamientoGarantia + ", maeMedicaCautelarId=" + maeMedicaCautelarId + ", maeMedicaCautelarCodigo=" + maeMedicaCautelarCodigo + ", maeMedicaCautelarValor=" + maeMedicaCautelarValor + ", montoMedida=" + montoMedida + ", probabilidad=" + probabilidad + ", riesgoClasificacion=" + riesgoClasificacion + ", claseProvision=" + claseProvision + ", fechaTerminacion=" + fechaTerminacion + ", sentidoSentencia=" + sentidoSentencia + ", valorSentenciaEjecutoria=" + valorSentenciaEjecutoria + ", estadoCumplimientoCondena=" + estadoCumplimientoCondena + ", maeActuacionTerminacionId=" + maeActuacionTerminacionId + ", maeActuacionTerminacionCodigo=" + maeActuacionTerminacionCodigo + ", maeActuacionTerminacionValor=" + maeActuacionTerminacionValor + ", valorAcuerdoTransaccion=" + valorAcuerdoTransaccion + ", fechaUltimaActuacion=" + fechaUltimaActuacion + ", ultimaActuacion=" + ultimaActuacion + ", fechaContestacion=" + fechaContestacion + ", gjProcesoAdjuntosList=" + gjProcesoAdjuntosList + ", gjProcesoAbogadosList=" + gjProcesoAbogadosList + ", gjProcesoPretencionesList=" + gjProcesoPretencionesList + ", gnUbicacionesProcesoId=" + gnUbicacionesProcesoId + ", tuJuzgadosId=" + tuJuzgadosId + ", gjProcesoHistoricosList=" + gjProcesoHistoricosList + ", gjProcesoTercerosList=" + gjProcesoTercerosList + '}';
    }

}
